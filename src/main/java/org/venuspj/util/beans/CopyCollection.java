package org.venuspj.util.beans;

import java.util.Collection;
import java.util.Iterator;


/**
 * プロパティのコピーをするクラスです。
 */
public class CopyCollection extends AbstractCopy<CopyCollection> {

    /**
     * コピー元です。
     */
    protected Collection<?> srcCollection;

    /**
     * コピー先です。
     */
    protected Collection<?> destCollection;

    /**
     * インスタンスを構築します。
     *
     * @param srcCollection  コピー元
     * @param destCollection コピー先
     * @throws NullPointerException 引数が<code>null</code>だった場合
     */
    public CopyCollection(Collection<?> srcCollection, Collection<?> destCollection) throws NullPointerException {
        if (srcCollection == null) {
            throw new NullPointerException("srcCollection");
        }
        if (destCollection == null) {
            throw new NullPointerException("destCollection");
        }
        this.srcCollection = srcCollection;
        this.destCollection = destCollection;
    }

    /**
     * プロパティをコピーします。
     */
    @SuppressWarnings("unchecked")
    public void execute() {
        Iterator<?> destIterator = destCollection.iterator();
        for (Object src:srcCollection) {
            Copy copy = Beans.copy(src, destIterator.next());
            copy.assignSourceProperties(this);
            copy.execute();
        }


    }
}