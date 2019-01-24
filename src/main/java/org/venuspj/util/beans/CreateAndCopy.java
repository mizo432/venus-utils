package org.venuspj.util.beans;

import org.venuspj.util.lang.ClassUtil;
import org.venuspj.util.lang.ModifierUtil;

import java.util.HashMap;
import java.util.Map;

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
     * @param destClass
     *            作成対象クラス
     * @param src
     *            コピー元
     * @throws NullPointerException
     *             引数が<code>null</code>だった場合
     */
    public CreateAndCopy(Class<T> destClass, Object src)
            throws NullPointerException {
        if (destClass == null) {
            throw new NullPointerException("destClass");
        }
        if (src == null) {
            throw new NullPointerException("src");
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
            if (ModifierUtil.isAbstract(destClass)) {
                dest = new HashMap();
            } else {
                dest = (Map) ClassUtil.newInstance(destClass);
            }
            if (src instanceof Map) {
                copyMapToMap((Map) src, dest);
            } else {
                copyBeanToMap(src, dest);
            }
            return (T) dest;
        }
        T dest = (T) ClassUtil.newInstance(destClass);
        if (src instanceof Map) {
            copyMapToBean((Map) src, dest);
        } else {
            copyBeanToBean(src, dest);
        }
        return dest;
    }
}