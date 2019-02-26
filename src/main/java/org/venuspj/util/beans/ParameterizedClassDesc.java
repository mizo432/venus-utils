package org.venuspj.util.beans;

import java.lang.reflect.ParameterizedType;

/**
 * パラメタ化されたクラスを扱うためのインターフェースです。
 */
public interface ParameterizedClassDesc {

    /**
     * このインスタンスが表現するクラスがパラメタ化されていれば<code>true</code>を返します。
     *
     * @return このインスタンスが表現するクラスがパラメタ化されていれば<code>true</code>
     */
    boolean isParameterizedClass();

    /**
     * 原型となるクラスを返します。
     *
     * @param <T> 原型となるクラスの型
     * @return 原型となるクラス
     * @see ParameterizedType#getRawType()
     */
    <T> Class<T> getRawClass();

    /**
     * 型引数を表す{@link ParameterizedClassDesc}の配列を返します。
     * <p>
     * このインスタンスが表現するクラスがパラメタ化されたクラスでない場合は、{@literal null}を返します。
     * </p>
     *
     * @return 型引数を表す{@link ParameterizedClassDesc}の配列
     * @see ParameterizedType#getActualTypeArguments()
     */
    ParameterizedClassDesc[] getArguments();

}
