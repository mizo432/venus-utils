package org.venuspj.util.beans;

import static org.venuspj.util.objects2.Objects2.isNull;

import java.util.HashMap;
import java.util.Map;
import org.venuspj.util.lang.Classes;
import org.venuspj.util.lang.Modifiers;

/**
 * JavaBeansやMapを作成し、プロパティをコピーするクラスです。
 *
 * @param <T> 作成するタイプ
 */
public class CreateAndCopy<T> extends AbstractCopy<CreateAndCopy<T>> {

  /**
   * 作成対象クラス
   */
  protected Class<T> destClass;

  /**
   * コピー元です。
   */
  protected Object src;

  /**
   * インスタンスを構築します。
   *
   * @param destClass 作成対象クラス
   * @param src コピー元
   * @throws NullPointerException 引数が<code>null</code>だった場合
   */
  public CreateAndCopy(Class<T> destClass, Object src)
      throws NullPointerException {
    if (isNull(destClass)) {
      throw new NullPointerException("destClass");
    }
    if (isNull(src)) {
      throw new NullPointerException("srcCollection");
    }
    this.destClass = destClass;
    this.src = src;
  }

  /**
   * JavaBeansやMapを作成し、プロパティをコピーします。
   *
   * @return 作成結果
   */
  @SuppressWarnings("unchecked")
  public T execute() {
    if (Map.class.isAssignableFrom(destClass)) {
      Map dest = null;
      if (Modifiers.isAbstract(destClass)) {
        dest = new HashMap();
      } else {
        dest = (Map) Classes.newInstance(destClass);
      }
      if (src instanceof Map) {
        copyMapToMap((Map) src, dest);
      } else {
        copyBeanToMap(src, dest);
      }
      return (T) dest;
    }
    T dest = (T) Classes.newInstance(destClass);
    if (src instanceof Map) {
      copyMapToBean((Map) src, dest);
    } else {
      copyBeanToBean(src, dest);
    }
    return dest;
  }
}