package org.venuspj.util.beans;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;

/**
 * コンストラクタを扱うためのインターフェースです。
 */
public interface ConstructorDesc {

    /**
     * このコンストラクタを所有するクラスの{@link BeanDesc}を返します。
     *
     * @return {@link BeanDesc}
     */
    BeanDesc getBeanDesc();

    /**
     * コンストラクタを返します。
     *
     * @param <T> Beanの型
     * @return コンストラクタ
     */
    <T> Constructor<T> getConstructor();

    /**
     * コンストラクタの引数型の配列を返します。
     *
     * @return コンストラクタの引数型の配列
     */
    Class<?>[] getParameterTypes();

    /**
     * {@literal public}コンストラクタの場合は{@literal true}を返します。
     *
     * @return {@literal public}コンストラクタの場合は{@literal true}
     */
    boolean isPublic();

    /**
     * コンストラクタの引数型がパラメタ化された型の場合は{@literal true}を返します。
     *
     * @param index 引数のインデックス
     * @return 引数型がパラメタ化された型の場合は{@literal true}
     */
    boolean isParameterized(int index);

    /**
     * メソッドの引数型を表現する{@link ParameterizedClassDesc}の配列を返します。
     *
     * @return メソッドの引数型を表現する{@link ParameterizedClassDesc}の配列
     */
    ParameterizedClassDesc[] getParameterizedClassDescs();

    /**
     * メソッドの引数型がパラメタ化された{@link Collection}の場合、その要素型を返します。
     *
     * @param index 引数のインデックス
     * @return メソッドの引数型がパラメタ化された{@link Collection}の場合はその要素型、そうでない場合は
     * {@literal null}
     */
    Class<?> getElementClassOfCollection(int index);

    /**
     * メソッドの引数型がパラメタ化された{@link Map}の場合、そのキー型を返します。
     *
     * @param index 引数のインデックス
     * @return メソッドの引数がパラメタ化された{@link Map}の場合はそのキー型、そうでない場合は{@literal null}
     */
    Class<?> getKeyClassOfMap(int index);

    /**
     * メソッドの引数型がパラメタ化された{@link Map}の場合、その値型を返します。
     *
     * @param index 引数のインデックス
     * @return メソッドの引数型がパラメタ化された{@link Map}の場合はその値型、そうでない場合は{@literal null}
     */
    Class<?> getValueClassOfMap(int index);

    /**
     * コンストラクタを呼び出して生成したインスタンスを返します。
     *
     * @param <T>  生成するBeanの型
     * @param args メソッドの引数
     * @return コンストラクタを呼び出して生成したインスタンス
     */
    <T> T newInstance(Object... args);

}
