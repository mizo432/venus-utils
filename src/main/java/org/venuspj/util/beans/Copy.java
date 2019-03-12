package org.venuspj.util.beans;

import java.util.Map;

/**
 * プロパティのコピーをするクラスです。
 */
public class Copy extends AbstractCopy<Copy> {

    /**
     * コピー元です。
     */
    protected Object src;

    /**
     * コピー先です。
     */
    protected Object dest;

    /**
     * インスタンスを構築します。
     *
     * @param src  コピー元
     * @param dest コピー先
     * @throws NullPointerException 引数が<code>null</code>だった場合
     */
    public Copy(Object src, Object dest) throws NullPointerException {
        if (src == null) {
            throw new NullPointerException("srcCollection");
        }
        if (dest == null) {
            throw new NullPointerException("destCollection");
        }
        this.src = src;
        this.dest = dest;
    }

    /**
     * プロパティをコピーします。
     */
    @SuppressWarnings("unchecked")
    public void execute() {
        if (src instanceof Map && dest instanceof Map) {
            copyMapToMap((Map) src, (Map) dest);
        } else if (src instanceof Map) {
            copyMapToBean((Map) src, dest);
        } else if (dest instanceof Map) {
            copyBeanToMap(src, (Map) dest);
        } else {
            copyBeanToBean(src, dest);
        }
    }
}