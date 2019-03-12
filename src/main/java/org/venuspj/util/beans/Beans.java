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
     * @param src  コピー元
     * @param dest コピー先
     * @return コピー用のオブジェクト
     */
    public static Copy copy(Object src, Object dest) {
        return new Copy(src, dest);
    }

    /**
     * プロパティをコピーするオブジェクトを作成します。
     *
     * @param srcCollection  コピー元
     * @param destCollection コピー先
     * @return コピー用のオブジェクト
     */
    public static CopyCollection copyCollection(Collection<?> srcCollection, Collection<?> destCollection) {
        return new CopyCollection(srcCollection, destCollection);
    }

    /**
     * JavaBeansやMapを作成しプロパティをコピーするオブジェクトを作成します。
     *
     * @param <T>
     * @param destClass 作成対象クラス
     * @param src       コピー元
     * @return 作成用のオブジェクト
     */
    public static <T> CreateAndCopy<T> createAndCopy(Class<T> destClass, Object src) {
        return new CreateAndCopy<T>(destClass, src);
    }

    /**
     * JavaBeansやMapを作成しプロパティをコピーするオブジェクトを作成します。
     *
     * @param <T>
     * @param destClass 作成対象クラス
     * @param srcCollection       コピー元
     * @return 作成用のオブジェクト
     */
    public static <T> CreateAndCopyCollection<T> createAndCopyCollection(Class<T> destClass, Collection<?> srcCollection) {
        return new CreateAndCopyCollection<T>(destClass, srcCollection);
    }
}
