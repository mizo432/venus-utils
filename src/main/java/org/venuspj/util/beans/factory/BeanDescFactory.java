package org.venuspj.util.beans.factory;

import static org.venuspj.util.collect.Collections3.newConcurrentHashMap;
import static org.venuspj.util.collect.Collections3.putIfAbsent;

import java.util.concurrent.ConcurrentMap;
import org.venuspj.util.base.Preconditions;
import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.impl.BeanDescImpl;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.misc.Disposable;
import org.venuspj.util.misc.Disposables;

/**
 * {@link BeanDesc}を生成するクラスです。
 * <p>
 * 指定されたJavaBeansのメタデータを扱う{@link BeanDesc}を返します。
 * </p>
 *
 * <pre>
 * BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Foo.class);
 * </pre>
 * <p>
 * {@link BeanDesc}はキャッシュされます。 キャッシュをクリアするには{@link Disposables#dispose()} を呼び出してください。
 * </p>
 *
 * @see BeanDesc
 * @see Disposables
 */
public abstract class BeanDescFactory {

  /**
   * 初期化済みなら{@literal true}
   */
  private static volatile boolean initialized;

  /**
   * {@link BeanDesc}のキャッシュ
   */
  private static final ConcurrentMap<Class<?>, BeanDesc> beanDescCache =
      newConcurrentHashMap(1024);

  static {
    initialize();
  }

  /**
   * {@link BeanDesc}を返します。
   *
   * @param clazz Beanクラス。{@literal null}であってはいけません
   * @return {@link BeanDesc}
   */
  public static BeanDesc getBeanDesc(final Class<?> clazz) {
    Preconditions.checkNotNull(clazz, () -> new NullArgumentException("clazz is null"));

    if (!initialized) {
      initialize();
    }
    BeanDesc beanDesc = beanDescCache.get(clazz);
    if (beanDesc == null) {
      beanDesc =
          putIfAbsent(beanDescCache, clazz, new BeanDescImpl(clazz));
    }
    return beanDesc;
  }

  /**
   * 初期化を行ないます。
   */
  public static void initialize() {
    synchronized (BeanDescFactory.class) {
      if (!initialized) {
        Disposables.add(new Disposable() {
          @Override
          public void dispose() {
            clear();
          }
        });
        initialized = true;
      }
    }
  }

  /**
   * キャッシュをクリアします。
   */
  public static void clear() {
    beanDescCache.clear();
    initialized = false;
  }

}

