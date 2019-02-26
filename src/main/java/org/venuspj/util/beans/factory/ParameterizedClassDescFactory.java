package org.venuspj.util.beans.factory;

import org.venuspj.util.beans.*;
import org.venuspj.util.beans.impl.ParameterizedClassDescImpl;
import org.venuspj.util.collect.Indexed;

import java.lang.reflect.*;
import java.util.Map;

import static org.venuspj.util.collect.ArrayIterator.iterable;
import static org.venuspj.util.collect.IndexedIterator.indexed;
import static org.venuspj.util.lang.Genericses.*;
import static org.venuspj.util.misc.Assertions.assertArgumentArrayIndex;
import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

/**
 * フィールの型やメソッドの引数型、戻り値型を表現する{@link ParameterizedClassDesc}を作成するファクトリです。
 * <p>
 * このクラスでは{@link ParameterizedClassDesc}のインスタンスをキャッシュしません。 {@link BeanDesc}経由で
 * {@link ParameterizedClassDesc}を取得するようにしてください。
 * </p>
 *
 * @see BeanDesc#getTypeVariables()
 * @see PropertyDesc#getParameterizedClassDesc()
 * @see FieldDesc#getParameterizedClassDesc()
 * @see ConstructorDesc#getParameterizedClassDescs()
 * @see MethodDesc#getParameterizedClassDesc()
 * @see MethodDesc#getParameterizedClassDescs()
 */
public abstract class ParameterizedClassDescFactory {

    /**
     * パラメータ化された型(クラスまたはインタフェース)が持つ型変数をキー、型引数を値とする{@link Map}を返します。
     * <p>
     * 型がパラメタ化されていない場合は空の{@link Map}を返します。
     * </p>
     *
     * @param beanClass パラメータ化された型(クラスまたはインタフェース)。{@literal null}であってはいけません
     * @return パラメータ化された型が持つ型変数をキー、型引数を値とする{@link Map}
     */
    public static Map<TypeVariable<?>, Type> getTypeVariables(
            final Class<?> beanClass) {
        assertArgumentNotNull("beanClass", beanClass);

        return getTypeVariableMap(beanClass);
    }

    /**
     * フィールドの型を表現する{@link ParameterizedClassDesc}を作成して返します。
     *
     * @param field フィールド。{@literal null}であってはいけません
     * @param map   パラメータ化された型が持つ型変数をキー、型引数を値とする{@link Map}。{@literal null}
     *              であってはいけません
     * @return フィールドの型を表現する{@link ParameterizedClassDesc}
     */
    public static ParameterizedClassDesc createParameterizedClassDesc(
            final Field field, final Map<TypeVariable<?>, Type> map) {
        assertArgumentNotNull("field", field);
        assertArgumentNotNull("map", map);

        return createParameterizedClassDesc(field.getGenericType(), map);
    }

    /**
     * コンストラクタの引数型を表現する{@link ParameterizedClassDesc}を作成して返します。
     *
     * @param constructor コンストラクタ。{@literal null}であってはいけません
     * @param index       引数の位置
     * @param map         パラメータ化された型が持つ型変数をキー、型引数を値とする{@link Map}。{@literal null}
     *                    であってはいけません
     * @return メソッドの引数型を表現する{@link ParameterizedClassDesc}
     */
    public static ParameterizedClassDesc createParameterizedClassDesc(
            final Constructor<?> constructor, final int index,
            final Map<TypeVariable<?>, Type> map) {
        assertArgumentNotNull("constructor", constructor);
        assertArgumentNotNull("map", map);

        return createParameterizedClassDesc(
                constructor.getGenericParameterTypes()[index],
                map);
    }

    /**
     * メソッドの引数型を表現する{@link ParameterizedClassDesc}を作成して返します。
     *
     * @param method メソッド。{@literal null}であってはいけません
     * @param index  引数の位置
     * @param map    パラメータ化された型が持つ型変数をキー、型引数を値とする{@link Map}。{@literal null}
     *               であってはいけません
     * @return メソッドの引数型を表現する{@link ParameterizedClassDesc}
     */
    public static ParameterizedClassDesc createParameterizedClassDesc(
            final Method method, final int index,
            final Map<TypeVariable<?>, Type> map) {
        assertArgumentNotNull("method", method);
        assertArgumentArrayIndex(
                "index",
                index,
                method.getParameterTypes().length);
        assertArgumentNotNull("map", map);

        return createParameterizedClassDesc(
                method.getGenericParameterTypes()[index],
                map);
    }

    /**
     * メソッドの戻り値型を表現する{@link ParameterizedClassDesc}を作成して返します。
     *
     * @param method メソッド。{@literal null}であってはいけません
     * @param map    パラメータ化された型が持つ型変数をキー、型引数を値とする{@link Map}。{@literal null}
     *               であってはいけません
     * @return メソッドの戻り値型を表現する{@link ParameterizedClassDesc}
     */
    public static ParameterizedClassDesc createParameterizedClassDesc(
            final Method method, final Map<TypeVariable<?>, Type> map) {
        assertArgumentNotNull("method", method);
        assertArgumentNotNull("map", map);

        return createParameterizedClassDesc(method.getGenericReturnType(), map);
    }

    /**
     * {@link Type}を表現する{@link ParameterizedClassDesc}を作成して返します。
     *
     * @param type 型
     * @param map  パラメータ化された型が持つ型変数をキー、型引数を値とする{@link Map}
     * @return 型を表現する{@link ParameterizedClassDesc}
     */
    protected static ParameterizedClassDesc createParameterizedClassDesc(
            final Type type, final Map<TypeVariable<?>, Type> map) {
        final Class<?> rowClass = getActualClass(type, map);
        if (rowClass == null) {
            return null;
        }
        final ParameterizedClassDescImpl desc =
                new ParameterizedClassDescImpl(rowClass);
        final Type[] parameterTypes = getGenericParameters(type);
        if (parameterTypes == null) {
            return desc;
        }
        final ParameterizedClassDesc[] parameterDescs =
                new ParameterizedClassDesc[parameterTypes.length];
        for (final Indexed<Type> parameterType : indexed(iterable(parameterTypes))) {
            parameterDescs[parameterType.getIndex()] =
                    createParameterizedClassDesc(parameterType.getElement(), map);
        }
        desc.setArguments(parameterDescs);
        return desc;
    }

}

