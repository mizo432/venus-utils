package org.venuspj.util.collect;

/**
 * {@link IndexedIterator}でイテレートする要素です。
 *
 * @param <T> 要素の型
 * @see IndexedIterator
 */
public class Indexed<T> {

    /**
     * 要素
     */
    private final T element;

    /**
     * 要素のインデックス
     */
    private final int index;

    /**
     * コンストラクタ
     *
     * @param element 要素
     * @param index   要素のインデックス
     */
    public Indexed(final T element, final int index) {
        this.element = element;
        this.index = index;
    }

    /**
     * 要素を返します。
     *
     * @return 要素
     */
    public T getElement() {
        return element;
    }

    /**
     * インデックスを返します。
     *
     * @return インデックス
     */
    public int getIndex() {
        return index;
    }

}
