package org.venuspj.util.misc;

import org.venuspj.util.exception.*;
import org.venuspj.util.base.Preconditions;
import org.venuspj.util.collect.Arrays2;

import java.util.Collection;
import java.util.Map;

import static org.venuspj.util.collect.Arrays2.asArray;
import static org.venuspj.util.objects2.Objects2.isNull;

/**
 * 表明についてのユーティリティクラスです。
 */
@Deprecated
public abstract class Assertions {

    /**
     * 引数が<code>null</code>でないことを表明します。
     *
     * @param argName  {@code null} であってはならない引数の名前
     * @param argValue 引数の値
     * @throws NullArgumentException 引数が<code>null</code>の場合。
     */
    public static void assertArgumentNotNull(final String argName,
                                             final Object argValue) {
        if (argValue == null) {
            throw new NullArgumentException(argName);
        }
    }

    /**
     * 引数が<code>null</code>でも空文字列でもないことを表明します。
     *
     * @param argName  {@code null} でも空文字列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空文字列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final String argValue) {
        Preconditions.checkNotEmpty(argValue, () -> new EmptyArgumentException(
                argName,
                "EUTL0010",
                asArray(argName)));
    }

    /**
     * 引数が<code>null</code>でも空文字列でもないことを表明します。
     *
     * @param argName  {@code null} でも空文字列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空文字列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final CharSequence argValue) {
        Preconditions.checkNotEmpty(argValue, () -> new EmptyArgumentException(
                argName,
                "EUTL0010",
                asArray(argName)));
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final Object[] argValue) {
        if (Arrays2.of(argValue).isEmpty()) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final boolean[] argValue) {
        if (Arrays2.of(argValue).isEmpty()) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final byte[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final short[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final int[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final long[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final float[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final double[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の配列でもないことを表明します。
     *
     * @param argName  {@code null} でも空の配列でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の配列の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final char[] argValue) {
        if (Arrays2.isEmpty(argValue)) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0011",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の{@link Collection}でもないことを表明します。
     *
     * @param argName  {@code null} でも空の{@link Collection}でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の{@link Collection}の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final Collection<?> argValue) {
        if (isNull(argValue) || argValue.isEmpty()) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0012",
                    asArray(argName));
        }
    }

    /**
     * 引数が<code>null</code>でも空の{@link Map}でもないことを表明します。
     *
     * @param argName  {@code null} でも空の{@link Map}でもあってはならない引数の名前
     * @param argValue 引数の値
     * @throws EmptyArgumentException 引数が<code>null</code>または空の{@link Map}の場合。
     */
    public static void assertArgumentNotEmpty(final String argName,
                                              final Map<?, ?> argValue) {
        if (isNull(argValue) || argValue.isEmpty()) {
            throw new EmptyArgumentException(
                    argName,
                    "EUTL0013",
                    asArray(argName));
        }
    }

    /**
     * インデックスが不正でないことを表明します。
     *
     * @param argName   {@code null} であってはならない引数の名前
     * @param argValue  インデックスの値
     * @param arraySize インデックスが参照する配列の長さ
     * @throws VIllegalArgumentException 引数が配列のインデックスとして不正な場合場合。
     */
    public static void assertArgumentArrayIndex(final String argName,
                                                final int argValue, final int arraySize) {
        if (argValue < 0) {
            throw new VIllegalArgumentException(
                    argName,
                    "EUTL0014",
                    asArray(argName));
        }
        if (argValue >= arraySize) {
            throw new VIllegalArgumentException(argName, "EUTL0015", asArray(
                    argName,
                    arraySize));
        }
    }

    /**
     * 引数が不正でないことを表明します。
     *
     * @param argName     不正であってはならない引数の名前
     * @param expression  事前条件
     * @param description 不正な引数であることの説明
     * @throws VIllegalArgumentException {@code expression}がfalseの場合。
     */
    public static void assertArgument(final String argName,
                                      final boolean expression, final String description) {
        if (!expression) {
            throw new VIllegalArgumentException(argName, "EUTL0009", asArray(
                    argName,
                    description));
        }
    }

    /**
     * 状態が不正でないことを表明します。
     *
     * @param expression  事前条件
     * @param description 不正な状態であることの説明
     * @throws VIllegalStateException {@code expression}がfalseの場合。
     */
    public static void assertState(final boolean expression,
                                   final String description) {
        if (!expression) {
            throw new VIllegalStateException(description);
        }
    }

    /**
     * indexが不正でないことを表明します。
     *
     * @param expression  事前条件
     * @param description 不正なindexであることの説明
     * @throws VIndexOutOfBoundsException {@code expression}がfalseの場合。
     */
    public static void assertIndex(final boolean expression,
                                   final String description) {
        if (!expression) {
            throw new VIndexOutOfBoundsException(description);
        }
    }

}
