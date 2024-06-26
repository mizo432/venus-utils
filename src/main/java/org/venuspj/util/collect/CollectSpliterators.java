package org.venuspj.util.collect;

import static org.venuspj.util.precondition.Preconditions.checkArgument;
import static org.venuspj.util.precondition.Preconditions.checkNotNull;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

final class CollectSpliterators {

  private CollectSpliterators() {
  }

  static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function) {
    return indexed(size, extraCharacteristics, function, null);
  }

  static <T> Spliterator<T> indexed(
      int size,
      int extraCharacteristics,
      IntFunction<T> function,
      Comparator<? super T> comparator) {
    if (comparator != null) {
      checkArgument((extraCharacteristics & (Spliterator.SORTED)) != 0);
    }
    class WithCharacteristics implements Spliterator<T> {

      private final Spliterator<T> delegate;

      WithCharacteristics(Spliterator<T> delegate) {
        this.delegate = delegate;
      }

      @Override
      public boolean tryAdvance(Consumer<? super T> action) {
        return delegate.tryAdvance(action);
      }

      @Override
      public void forEachRemaining(Consumer<? super T> action) {
        delegate.forEachRemaining(action);
      }

      @Override
      public Spliterator<T> trySplit() {
        Spliterator<T> split = delegate.trySplit();
        return (split == null) ? null : new WithCharacteristics(split);
      }

      @Override
      public long estimateSize() {
        return delegate.estimateSize();
      }

      @Override
      public int characteristics() {
        return delegate.characteristics() | extraCharacteristics;
      }

      @Override
      public Comparator<? super T> getComparator() {
        if (hasCharacteristics(Spliterator.SORTED)) {
          return comparator;
        } else {
          throw new IllegalStateException();
        }
      }
    }
    return new WithCharacteristics(IntStream.range(0, size).mapToObj(function).spliterator());
  }

  /**
   * Returns a {@code Spliterator} over the elements of {@code fromSpliterator} mapped by
   * {@code function}.
   */
  static <F, T> Spliterator<T> map(
      Spliterator<F> fromSpliterator, Function<? super F, ? extends T> function) {
    checkNotNull(fromSpliterator);
    checkNotNull(function);
    return new Spliterator<T>() {

      @Override
      public boolean tryAdvance(Consumer<? super T> action) {
        return fromSpliterator.tryAdvance(
            fromElement -> action.accept(function.apply(fromElement)));
      }

      @Override
      public void forEachRemaining(Consumer<? super T> action) {
        fromSpliterator.forEachRemaining(fromElement -> action.accept(function.apply(fromElement)));
      }

      @Override
      public Spliterator<T> trySplit() {
        Spliterator<F> fromSplit = fromSpliterator.trySplit();
        return (fromSplit != null) ? map(fromSplit, function) : null;
      }

      @Override
      public long estimateSize() {
        return fromSpliterator.estimateSize();
      }

      @Override
      public int characteristics() {
        return fromSpliterator.characteristics()
            & ~(Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.SORTED);
      }
    };
  }

  static <T> Spliterator<T> filter(Spliterator<T> fromSpliterator, Predicate<? super T> predicate) {
    checkNotNull(fromSpliterator);
    checkNotNull(predicate);
    class Splitr implements Spliterator<T>, Consumer<T> {

      T holder = null;

      @Override
      public void accept(T t) {
        this.holder = t;
      }

      @Override
      public boolean tryAdvance(Consumer<? super T> action) {
        while (fromSpliterator.tryAdvance(this)) {
          try {
            if (predicate.test(holder)) {
              action.accept(holder);
              return true;
            }
          } finally {
            holder = null;
          }
        }
        return false;
      }

      @Override
      public Spliterator<T> trySplit() {
        Spliterator<T> fromSplit = fromSpliterator.trySplit();
        return (fromSplit == null) ? null : filter(fromSplit, predicate);
      }

      @Override
      public long estimateSize() {
        return fromSpliterator.estimateSize() / 2;
      }

      @Override
      public Comparator<? super T> getComparator() {
        return fromSpliterator.getComparator();
      }

      @Override
      public int characteristics() {
        return fromSpliterator.characteristics()
            & (Spliterator.DISTINCT
            | Spliterator.NONNULL
            | Spliterator.ORDERED
            | Spliterator.SORTED);
      }
    }
    return new Splitr();
  }

  /**
   * Returns a {@code Spliterator} that iterates over the elements of the spliterators generated by
   * applying {@code function} to the elements of {@code fromSpliterator}.
   */
  static <F, T> Spliterator<T> flatMap(
      Spliterator<F> fromSpliterator,
      Function<? super F, Spliterator<T>> function,
      int topCharacteristics,
      long topSize) {
    checkArgument(
        (topCharacteristics & Spliterator.SUBSIZED) == 0,
        "flatMap does not support SUBSIZED characteristic");
    checkArgument(
        (topCharacteristics & Spliterator.SORTED) == 0,
        "flatMap does not support SORTED characteristic");
    checkNotNull(fromSpliterator);
    checkNotNull(function);
    class FlatMapSpliterator implements Spliterator<T> {

      Spliterator<T> prefix;
      final Spliterator<F> from;
      int characteristics;
      long estimatedSize;

      FlatMapSpliterator(
          Spliterator<T> prefix, Spliterator<F> from, int characteristics, long estimatedSize) {
        this.prefix = prefix;
        this.from = from;
        this.characteristics = characteristics;
        this.estimatedSize = estimatedSize;
      }

      @Override
      public boolean tryAdvance(Consumer<? super T> action) {
        while (true) {
          if (prefix != null && prefix.tryAdvance(action)) {
            if (estimatedSize != Long.MAX_VALUE) {
              estimatedSize--;
            }
            return true;
          } else {
            prefix = null;
          }
          if (!from.tryAdvance(fromElement -> prefix = function.apply(fromElement))) {
            return false;
          }
        }
      }

      @Override
      public void forEachRemaining(Consumer<? super T> action) {
        if (prefix != null) {
          prefix.forEachRemaining(action);
          prefix = null;
        }
        from.forEachRemaining(fromElement -> function.apply(fromElement).forEachRemaining(action));
        estimatedSize = 0;
      }

      @Override
      public Spliterator<T> trySplit() {
        Spliterator<F> fromSplit = from.trySplit();
        if (fromSplit != null) {
          int splitCharacteristics = characteristics & ~Spliterator.SIZED;
          long estSplitSize = estimateSize();
          if (estSplitSize < Long.MAX_VALUE) {
            estSplitSize /= 2;
            this.estimatedSize -= estSplitSize;
            this.characteristics = splitCharacteristics;
          }
          Spliterator<T> result =
              new FlatMapSpliterator(this.prefix, fromSplit, splitCharacteristics, estSplitSize);
          this.prefix = null;
          return result;
        } else if (prefix != null) {
          Spliterator<T> result = prefix;
          this.prefix = null;
          return result;
        } else {
          return null;
        }
      }

      @Override
      public long estimateSize() {
        if (prefix != null) {
          estimatedSize = Math.max(estimatedSize, prefix.estimateSize());
        }
        return Math.max(estimatedSize, 0);
      }

      @Override
      public int characteristics() {
        return characteristics;
      }
    }
    return new FlatMapSpliterator(null, fromSpliterator, topCharacteristics, topSize);
  }
}