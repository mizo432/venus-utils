package org.venuspj.util.beans;

import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.objects2.Objects2.isNull;

import java.util.Collection;
import java.util.List;

/**
 * JavaBeansやMapを作成し、プロパティをコピーするクラスです。
 *
 * @param <T> 作成するタイプ
 */
public class CreateAndCopyCollection<T> extends AbstractCopy<CreateAndCopyCollection<T>> {

  /**
   * 作成対象クラス
   */
  protected Class<T> destClass;

  /**
   * コピー元です。
   */
  protected Collection<?> srcCollection;

  /**
   * インスタンスを構築します。
   *
   * @param destClass 作成対象クラス
   * @param srcCollection コピー元
   * @throws NullPointerException 引数が<code>null</code>だった場合
   */
  public CreateAndCopyCollection(Class<T> destClass, Collection<?> srcCollection)
      throws NullPointerException {
    if (isNull(destClass)) {
      throw new NullPointerException("destClass");
    }
    if (isNull(srcCollection)) {
      throw new NullPointerException("srcCollection");
    }
    this.destClass = destClass;
    this.srcCollection = srcCollection;
  }

  /**
   * JavaBeansやMapを作成し、プロパティをコピーします。
   *
   * @return 作成結果
   */
  @SuppressWarnings("unchecked")
  public List<T> execute() {

    List<T> resultList = newArrayList();
    for (Object src : srcCollection) {
      CreateAndCopy<T> createAndCopy = Beans.createAndCopy(destClass, src);
      createAndCopy.assignSourceProperties(this);
      resultList.add(createAndCopy.execute());
    }

    return resultList;

  }
}