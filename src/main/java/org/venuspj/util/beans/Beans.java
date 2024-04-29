package org.venuspj.util.beans;

import java.util.Collection;

/**
 * JavaBeans用のユーティリティクラスです。
 */
public class Beans {

  /**
   * インスタンスを構築します。
   */
  protected Beans() {
  }

  /**
   * プロパティをコピーするオブジェクトを作成します。
   *
   * @param src コピー元
   * @param dest コピー先
   * @return コピー用のオブジェクト
   */
  public static Copy copy(Object src, Object dest) {
    return new Copy(src, dest);
  }

  /**
   * プロパティをコピーするオブジェクトを作成します。
   *
   * @param srcCollection コピー元
   * @param destCollection コピー先
   * @return コピー用のオブジェクト
   */
  public static CopyCollection copyCollection(Collection<?> srcCollection,
      Collection<?> destCollection) {
    return new CopyCollection(srcCollection, destCollection);
  }

  /**
   * Creates a new instance of CreateAndCopy with the specified destination class and source
   * object.
   *
   * @param <T> the type of object to create
   * @param destClass the class of the object to create
   * @param src the source object
   * @return a CreateAndCopy object
   * @throws NullPointerException if destClass or src is null
   */
  public static <T> CreateAndCopy<T> createAndCopy(Class<T> destClass, Object src) {
    return new CreateAndCopy<T>(destClass, src);
  }

  /**
   * Creates a new instance of CreateAndCopyCollection with the specified destination class and
   * source collection.
   *
   * @param <T> the type of object to create
   * @param destClass the class of the object to create
   * @param srcCollection the source collection
   * @return a CreateAndCopyCollection object
   * @throws NullPointerException if destClass or srcCollection is null
   */
  public static <T> CreateAndCopyCollection<T> createAndCopyCollection(Class<T> destClass,
      Collection<?> srcCollection) {
    return new CreateAndCopyCollection<T>(destClass, srcCollection);
  }
}
