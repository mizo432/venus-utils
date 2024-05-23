package org.venuspj.util.misc;

/**
 * 破棄可能なリソースを表現します。
 * <p>
 * コンテナの終了時に破棄しなければならないリソースがある場合は、 このインタフェースを実装したクラスを作成し、 {@link Disposables}に登録します。
 * </p>
 */
public interface Disposable {

  /**
   * このオブジェクトが保持しているリソースを破棄します。
   */
  void dispose();

}
