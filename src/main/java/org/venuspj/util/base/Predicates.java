package org.venuspj.util.base;

import static org.venuspj.util.precondition.Preconditions.checkNotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.venuspj.util.objects2.Objects2;

/**
 * Static utility methods pertaining to {@code Predicate} instances.
 *
 * <p>All methods return serializable predicates as long as they're given serializable parameters.
 *
 * <p>See the Guava User Guide article on <a
 * href="https://github.com/google/guava/wiki/FunctionalExplained">the use of
 * {@code Predicate}</a>.
 */
public final class Predicates {

  private Predicates() {
  }

  // interface which specifies an accept(PredicateVisitor) method.

  /**
   * Returns a predicate that always evaluates to {@code true}.
   */
  public static <T> Predicate<T> alwaysTrue() {
    return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
  }

  /**
   * Returns a predicate that always evaluates to {@code false}.
   */
  public static <T> Predicate<T> alwaysFalse() {
    return ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the object reference being tested is
   * null.
   */
  public static <T> Predicate<T> isNull() {
    return ObjectPredicate.IS_NULL.withNarrowedType();
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the object reference being tested is not
   * null.
   */
  public static <T> Predicate<T> notNull() {
    return ObjectPredicate.NOT_NULL.withNarrowedType();
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the given predicate evaluates to
   * {@code false}.
   */
  public static <T> Predicate<T> not(Predicate<T> predicate) {
    return new NotPredicate<T>(predicate);
  }

  /**
   * Returns a predicate that evaluates to {@code true} if each of its components evaluates to
   * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
   * as soon as a false predicate is found. It defensively copies the iterable passed in, so future
   * changes to it won't alter the behavior of this predicate. If {@code components} is empty, the
   * returned predicate will always evaluate to {@code true}.
   */
  public static <T> Predicate<T> and(Iterable<? extends Predicate<? super T>> components) {
    return new AndPredicate<T>(defensiveCopy(components));
  }

  /**
   * Returns a predicate that evaluates to {@code true} if each of its components evaluates to
   * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
   * as soon as a false predicate is found. It defensively copies the array passed in, so future
   * changes to it won't alter the behavior of this predicate. If {@code components} is empty, the
   * returned predicate will always evaluate to {@code true}.
   */
  @SafeVarargs
  public static <T> Predicate<T> and(Predicate<? super T>... components) {
    return new AndPredicate<T>(defensiveCopy(components));
  }

  /**
   * Returns a predicate that evaluates to {@code true} if both of its components evaluate to
   * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
   * as soon as a false predicate is found.
   */
  public static <T> Predicate<T> and(Predicate<? super T> first, Predicate<? super T> second) {
    return new AndPredicate<T>(Predicates.<T>asList(checkNotNull(first), checkNotNull(second)));
  }

  /**
   * Returns a predicate that evaluates to {@code true} if any one of its components evaluates to
   * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
   * as soon as a true predicate is found. It defensively copies the iterable passed in, so future
   * changes to it won't alter the behavior of this predicate. If {@code components} is empty, the
   * returned predicate will always evaluate to {@code false}.
   */
  public static <T> Predicate<T> or(Iterable<? extends Predicate<? super T>> components) {
    return new OrPredicate<T>(defensiveCopy(components));
  }

  /**
   * Returns a predicate that evaluates to {@code true} if any one of its components evaluates to
   * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
   * as soon as a true predicate is found. It defensively copies the array passed in, so future
   * changes to it won't alter the behavior of this predicate. If {@code components} is empty, the
   * returned predicate will always evaluate to {@code false}.
   */
  @SafeVarargs
  public static <T> Predicate<T> or(Predicate<? super T>... components) {
    return new OrPredicate<T>(defensiveCopy(components));
  }

  /**
   * Returns a predicate that evaluates to {@code true} if either of its components evaluates to
   * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
   * as soon as a true predicate is found.
   */
  public static <T> Predicate<T> or(Predicate<? super T> first, Predicate<? super T> second) {
    return new OrPredicate<T>(Predicates.<T>asList(checkNotNull(first), checkNotNull(second)));
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the object being tested {@code equals()}
   * the given target or both are null.
   */
  public static <T> Predicate<T> equalTo(T target) {
    return (target == null) ? Predicates.<T>isNull() : new IsEqualToPredicate<T>(target);
  }

  public static Predicate<Object> instanceOf(Class<?> clazz) {
    return new InstanceOfPredicate(clazz);
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the class being tested is assignable to
   * (is a subtype of) {@code clazz}. Example:
   *
   * <pre>{@code
   * List<Class<?>> classes = Arrays.asList(
   *     Object.class, String.class, Number.class, Long.class);
   * return Iterables.filter(classes, subtypeOf(Number.class));
   * }</pre>
   * <p>
   * The code above returns an iterable containing {@code Number.class} and {@code Long.class}.
   * <p>
   * (since 10.0 under the incorrect name {@code assignableFrom})
   */
  public static Predicate<Class<?>> subtypeOf(Class<?> clazz) {
    return new SubtypeOfPredicate(clazz);
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the object reference being tested is a
   * member of the given collection. It does not defensively copy the collection passed in, so
   * future changes to it will alter the behavior of the predicate.
   *
   * <p>This method can technically accept any {@code Collection<?>}, but using a typed collection
   * helps prevent bugs. This approach doesn't block any potential users since it is always possible
   * to use {@code Predicates.<Object>in()}.
   *
   * @param target the collection that may contain the function input
   */
  public static <T> Predicate<T> in(Collection<? extends T> target) {
    return new InPredicate<T>(target);
  }

  /**
   * Returns the composition of a function and a predicate. For every {@code x}, the generated
   * predicate returns {@code predicate(function(x))}.
   *
   * @return the composition of the provided function and predicate
   */
  public static <A, B> Predicate<A> compose(
      Predicate<B> predicate, Function<A, ? extends B> function) {
    return new CompositionPredicate<>(predicate, function);
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the {@code CharSequence} being tested
   * contains any match for the given regular expression pattern. The test used is equivalent to
   * {@code Pattern.compile(pattern).matcher(arg).find()}
   *
   * @throws IllegalArgumentException if the pattern is invalid
   * @since 3.0
   */
  public static Predicate<CharSequence> containsPattern(String pattern) {
    return new ContainsPatternFromStringPredicate(pattern);
  }

  /**
   * Returns a predicate that evaluates to {@code true} if the {@code CharSequence} being tested
   * contains any match for the given regular expression pattern. The test used is equivalent to
   * {@code pattern.matcher(arg).find()}
   *
   * @since 3.0
   */
  public static Predicate<CharSequence> contains(Pattern pattern) {
    return new ContainsPatternPredicate(new JdkPattern(pattern));
  }

  // End public API, begin private implementation classes.

  // Package private for GWT serialization.
  enum ObjectPredicate implements Predicate<Object> {
    /**
     * @see Predicates#alwaysTrue()
     */
    ALWAYS_TRUE {
      @Override
      public boolean test(Object o) {
        return true;
      }

      @Override
      public String toString() {
        return "Predicates.alwaysTrue()";
      }
    },
    /**
     * @see Predicates#alwaysFalse()
     */
    ALWAYS_FALSE {
      @Override
      public boolean test(Object o) {
        return false;
      }

      @Override
      public String toString() {
        return "Predicates.alwaysFalse()";
      }
    },
    /**
     * @see Predicates#isNull()
     */
    IS_NULL {
      @Override
      public boolean test(Object o) {
        return o == null;
      }

      @Override
      public String toString() {
        return "Predicates.isNull()";
      }
    },
    /**
     * @see Predicates#notNull()
     */
    NOT_NULL {
      @Override
      public boolean test(Object o) {
        return o != null;
      }

      @Override
      public String toString() {
        return "Predicates.notNull()";
      }
    };

    @SuppressWarnings("unchecked")
      // safe contravariant cast
    <T> Predicate<T> withNarrowedType() {
      return (Predicate<T>) this;
    }
  }

  /**
   * @see Predicates#not(Predicate)
   */
  private static class NotPredicate<T> implements Predicate<T>, Serializable {

    final Predicate<T> predicate;

    NotPredicate(Predicate<T> predicate) {
      this.predicate = checkNotNull(predicate);
    }

    @Override
    public boolean test(T t) {
      return !predicate.test(t);
    }

    @Override
    public int hashCode() {
      return ~predicate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof NotPredicate) {
        NotPredicate<?> that = (NotPredicate<?>) obj;
        return predicate.equals(that.predicate);
      }
      return false;
    }

    @Override
    public String toString() {
      return "Predicates.not(" + predicate + ")";
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#and(Iterable)
   */
  private static class AndPredicate<T> implements Predicate<T>, Serializable {

    private final List<? extends Predicate<? super T>> components;

    private AndPredicate(List<? extends Predicate<? super T>> components) {
      this.components = components;
    }

    @Override
    public boolean test(T t) {
      // Avoid using the Iterator to avoid generating garbage (issue 820).
      for (int i = 0; i < components.size(); i++) {
        if (!components.get(i).test(t)) {
          return false;
        }
      }
      return true;
    }

    @Override
    public int hashCode() {
      // add a random number to avoid collisions with OrPredicate
      return components.hashCode() + 0x12472c2c;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof AndPredicate) {
        AndPredicate<?> that = (AndPredicate<?>) obj;
        return components.equals(that.components);
      }
      return false;
    }

    @Override
    public String toString() {
      return toStringHelper("and", components);
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#or(Iterable)
   */
  private static class OrPredicate<T> implements Predicate<T>, Serializable {

    private final List<? extends Predicate<? super T>> components;

    private OrPredicate(List<? extends Predicate<? super T>> components) {
      this.components = components;
    }

    @Override
    public boolean test(T t) {
      // Avoid using the Iterator to avoid generating garbage (issue 820).
      for (int i = 0; i < components.size(); i++) {
        if (components.get(i).test(t)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public int hashCode() {
      // add a random number to avoid collisions with AndPredicate
      return components.hashCode() + 0x053c91cf;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof OrPredicate) {
        OrPredicate<?> that = (OrPredicate<?>) obj;
        return components.equals(that.components);
      }
      return false;
    }

    @Override
    public String toString() {
      return toStringHelper("or", components);
    }

    private static final long serialVersionUID = 0;
  }

  private static String toStringHelper(String methodName, Iterable<?> components) {
    StringBuilder builder = new StringBuilder("Predicates.").append(methodName).append('(');
    boolean first = true;
    for (Object o : components) {
      if (!first) {
        builder.append(',');
      }
      builder.append(o);
      first = false;
    }
    return builder.append(')').toString();
  }

  /**
   * @see Predicates#equalTo(Object)
   */
  private static class IsEqualToPredicate<T> implements Predicate<T>, Serializable {

    private final T target;

    private IsEqualToPredicate(T target) {
      this.target = target;
    }

    @Override
    public boolean test(T t) {
      return target.equals(t);
    }

    @Override
    public int hashCode() {
      return target.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof IsEqualToPredicate) {
        IsEqualToPredicate<?> that = (IsEqualToPredicate<?>) obj;
        return target.equals(that.target);
      }
      return false;
    }

    @Override
    public String toString() {
      return "Predicates.equalTo(" + target + ")";
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#instanceOf(Class)
   */
  private static class InstanceOfPredicate implements Predicate<Object>, Serializable {

    private final Class<?> clazz;

    private InstanceOfPredicate(Class<?> clazz) {
      this.clazz = checkNotNull(clazz);
    }

    @Override
    public boolean test(Object o) {
      return clazz.isInstance(o);
    }

    @Override
    public int hashCode() {
      return clazz.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof InstanceOfPredicate) {
        InstanceOfPredicate that = (InstanceOfPredicate) obj;
        return clazz == that.clazz;
      }
      return false;
    }

    @Override
    public String toString() {
      return "Predicates.instanceOf(" + clazz.getName() + ")";
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#subtypeOf(Class)
   */
  private static class SubtypeOfPredicate implements Predicate<Class<?>>, Serializable {

    private final Class<?> clazz;

    private SubtypeOfPredicate(Class<?> clazz) {
      this.clazz = checkNotNull(clazz);
    }

    @Override
    public boolean test(Class<?> input) {
      return clazz.isAssignableFrom(input);
    }

    @Override
    public int hashCode() {
      return clazz.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof SubtypeOfPredicate) {
        SubtypeOfPredicate that = (SubtypeOfPredicate) obj;
        return clazz == that.clazz;
      }
      return false;
    }

    @Override
    public String toString() {
      return "Predicates.subtypeOf(" + clazz.getName() + ")";
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#in(Collection)
   */
  private static class InPredicate<T> implements Predicate<T>, Serializable {

    private final Collection<?> target;

    private InPredicate(Collection<?> target) {
      this.target = checkNotNull(target);
    }

    @Override
    public boolean test(T t) {
      try {
        return target.contains(t);
      } catch (NullPointerException | ClassCastException e) {
        return false;
      }
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof InPredicate) {
        InPredicate<?> that = (InPredicate<?>) obj;
        return target.equals(that.target);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return target.hashCode();
    }

    @Override
    public String toString() {
      return "Predicates.in(" + target + ")";
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#compose(Predicate, Function)
   */
  private static class CompositionPredicate<A, B> implements Predicate<A>, Serializable {

    final Predicate<B> p;
    final Function<A, ? extends B> f;

    private CompositionPredicate(Predicate<B> p, Function<A, ? extends B> f) {
      this.p = checkNotNull(p);
      this.f = checkNotNull(f);
    }

    @Override
    public boolean test(A a) {
      return p.test(f.apply(a));
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof CompositionPredicate) {
        CompositionPredicate<?, ?> that = (CompositionPredicate<?, ?>) obj;
        return f.equals(that.f) && p.equals(that.p);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return f.hashCode() ^ p.hashCode();
    }

    @Override
    public String toString() {
      return p + "(" + f + ")";
    }

    @Serial
    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#contains(Pattern)
   */
  private static class ContainsPatternPredicate implements Predicate<CharSequence>, Serializable {

    final CommonPattern pattern;

    ContainsPatternPredicate(CommonPattern pattern) {
      this.pattern = checkNotNull(pattern);
    }

    @Override
    public boolean test(CharSequence t) {
      return pattern.matcher(t).find();
    }

    @Override
    public int hashCode() {
      // Pattern uses Object.hashCode, so we have to reach
      // inside to build a hashCode consistent with equals.

      return Objects2.hash(pattern.pattern(), pattern.flags());
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof ContainsPatternPredicate) {
        ContainsPatternPredicate that = (ContainsPatternPredicate) obj;

        // Pattern uses Object (identity) equality, so we have to reach
        // inside to compare individual fields.
        return Objects2.equal(pattern.pattern(), that.pattern.pattern())
            && pattern.flags() == that.pattern.flags();
      }
      return false;
    }

    @Override
    public String toString() {
      String patternString =
          Objects2.toStringHelper(pattern)
              .add("pattern", pattern.pattern())
              .add("pattern.flags", pattern.flags())
              .toString();
      return "Predicates.contains(" + patternString + ")";
    }

    @Serial
    private static final long serialVersionUID = 0;
  }

  /**
   * @see Predicates#containsPattern(String)
   */
  private static class ContainsPatternFromStringPredicate extends ContainsPatternPredicate {

    ContainsPatternFromStringPredicate(String string) {
      super(Platform.compilePattern(string));
    }

    @Override
    public String toString() {
      return "Predicates.containsPattern(" + pattern.pattern() + ")";
    }

    private static final long serialVersionUID = 0;
  }

  private static <T> List<Predicate<? super T>> asList(
      Predicate<? super T> first, Predicate<? super T> second) {
    return Arrays.<Predicate<? super T>>asList(first, second);
  }

  @SafeVarargs
  private static <T> List<T> defensiveCopy(T... array) {
    return defensiveCopy(Arrays.asList(array));
  }

  static <T> List<T> defensiveCopy(Iterable<T> iterable) {
    ArrayList<T> list = new ArrayList<T>();
    for (T element : iterable) {
      list.add(checkNotNull(element));
    }
    return list;
  }
}
