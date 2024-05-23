package org.venuspj.util.misc;

import static org.venuspj.util.base.Preconditions.checkNotNull;

import java.beans.Introspector;
import java.util.Deque;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.venuspj.util.collect.Lists2;

/**
 * アプリケーションの終了時にリソースを破棄するためのユーティリティクラスです。
 * <p>
 * アプリケーションの終了時に破棄しなければならないリソースがある場合は、 {@link Disposable}を実装したクラスを作成し、 このクラスに登録します。
 * </p>
 */
public abstract class Disposables {

  private final static Logger log = LoggerFactory.getLogger(Disposables.class);
  /**
   * 登録済みの{@link Disposable}
   */
  protected static final Deque<Disposable> disposables = Lists2.newLinkedList();

  /**
   * 破棄可能なリソースを登録します。
   *
   * @param disposable 破棄可能なリソース。{@literal null}であってはいけません
   */
  public static synchronized void add(@NotNull final Disposable disposable) {
    checkNotNull(disposable, "disposable");
    disposables.addLast(disposable);
  }

  /**
   * 破棄可能なリソースを先頭に登録します。
   * <p>
   * リソースは登録された逆順に破棄されるため、先頭に登録されたリソースは最後に破棄されることになります。
   * </p>
   *
   * @param disposable 破棄可能なリソース。{@literal null}であってはいけません
   */
  public static synchronized void addFirst(@NotNull final Disposable disposable) {
    checkNotNull(disposable, "disposable");
    disposables.addFirst(disposable);
  }

  /**
   * 破棄可能なリソースを登録解除します。
   *
   * @param disposable 破棄可能なリソース。{@literal null}であってはいけません
   */
  public static synchronized void remove(@NotNull final Disposable disposable) {
    checkNotNull(disposable, "disposable");
    disposables.remove(disposable);
  }

  /**
   * 登録済みのリソースを全て破棄します。
   */
  public static synchronized void dispose() {
    while (!disposables.isEmpty()) {
      final Disposable disposable = disposables.removeLast();
      try {
        disposable.dispose();
      } catch (final Throwable t) {
        log.error("Error disposing " + disposable, t);
      }
    }
    Introspector.flushCaches();
  }

}

