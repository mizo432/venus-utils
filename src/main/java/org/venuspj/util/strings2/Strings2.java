package org.venuspj.util.strings2;

import org.venuspj.util.base.Joiner;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static org.venuspj.util.collect.Lists2.newArrayList;

public final class Strings2 {
    /**
     * 空文字<code>""</code>です。
     */
    public static final String EMPTY = "";

    /**
     * 文字列型の空の配列です。
     */
    public static final String[] EMPTY_STRINGS = new String[0];

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(String aSrt) {
        return !isEmpty(aSrt);
    }

    public static String repeat(String string, int count) {

        final int len = string.length();
        final long longSize = (long) len * (long) count;
        final int size = (int) longSize;
        if (size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }

        final char[] array = new char[size];
        if (count == 0) return "";
        string.getChars(0, len, array, 0);
        int n;
        for (n = len; n < size - n; n <<= 1) {
            System.arraycopy(array, 0, array, n, n);
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);

    }

    public static String repeat(String string, int count, String aSeparator) {
        return Joiner.on(aSeparator).join(Collections.nCopies(count, string));
    }

    public static boolean isNumber(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        final int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0') {
            if (
                    (chars[start + 1] == 'x') ||
                            (chars[start + 1] == 'X')
            ) {
                int i = start + 2;
                if (i == sz) {
                    return false;
                }
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--;
        int i = start;
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points org dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false;
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    return false;
                }
                return foundDigit;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                return foundDigit && !hasExp && !hasDecPoint;
            }
            return false;
        }
        return !allowSigns && foundDigit;
    }

    public static String surround(String aValue, String s) {
        return new StringBuilder().append(s).append(aValue).append(s).toString();
    }

    /**
     * 文字列を置き換えます。
     *
     * @param text     テキスト
     * @param fromText 置き換え対象のテキスト
     * @param toText   置き換えるテキスト
     * @return 結果
     */
    public static final String replace(final String text,
                                       final String fromText, final String toText) {

        if (text == null || fromText == null || toText == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(100);
        int pos = 0;
        int pos2 = 0;
        while (true) {
            pos = text.indexOf(fromText, pos2);
            if (pos == 0) {
                buf.append(toText);
                pos2 = fromText.length();
            } else if (pos > 0) {
                buf.append(text.substring(pos2, pos));
                buf.append(toText);
                pos2 = pos + fromText.length();
            } else {
                buf.append(text.substring(pos2));
                break;
            }
        }
        return buf.toString();
    }

    /**
     * 文字列を分割します。
     *
     * @param str   文字列
     * @param delim 分割するためのデリミタ
     * @return 分割された文字列の配列
     */
    public static String[] split(final String str, final String delim) {
        if (isEmpty(str)) {
            return EMPTY_STRINGS;
        }
        final List<String> list = newArrayList();
        final StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreElements()) {
            list.add(st.nextElement().toString());
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * JavaBeansの仕様にしたがってデキャピタライズを行ないます。大文字が2つ以上続く場合は、小文字にならないので注意してください。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.capitalize("UserId")  = "userId"
     * Strings2.capitalize("ABC")  = "ABC"
     * </pre>
     *
     * @param name 名前
     * @return 結果の文字列
     */
    public static String decapitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        final char[] chars = name.toCharArray();
        if (chars.length >= 2 && Character.isUpperCase(chars[0])
                && Character.isUpperCase(chars[1])) {
            return name;
        }
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    /**
     * JavaBeansの仕様にしたがってキャピタライズを行ないます。大文字が2つ以上続く場合は、小文字にならないので注意してください。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.capitalize("userId")  = "UserId"
     * Strings2.capitalize("ABC")  = "ABC"
     * </pre>
     *
     * @param name 名前
     * @return 結果の文字列
     */
    public static String capitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        final char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    /**
     * 左側の空白を削ります。
     *
     * @param text テキスト
     * @return 結果の文字列
     */
    public static final String ltrim(final String text) {
        return ltrim(text, null);
    }

    /**
     * 左側の指定した文字列を削ります。
     *
     * @param text     テキスト
     * @param trimText 削るテキスト
     * @return 結果の文字列
     */
    public static final String ltrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        if (text.length() < trimText.length())
            return text;
        if (text.startsWith(trimText))
            return ltrim(text.substring(trimText.length()),trimText);
        return text;
    }

    /**
     * 右側の指定した文字列を削ります。
     *
     * @param text     テキスト
     * @param trimText 削る文字列
     * @return 結果の文字列
     */
    public static String rtrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        if (text.length() < trimText.length())
            return text;
        if (text.endsWith(trimText))
            return rtrim(text.substring(0,text.length() - trimText.length()),trimText);

        return text;

    }
}
