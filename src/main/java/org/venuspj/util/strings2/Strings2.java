package org.venuspj.util.strings2;

import org.venuspj.util.base.Joiner;

import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Supplier;

import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.objects2.Objects2.isNull;

public final class Strings2 {
    /**
     * 空文字<code>""</code>です。
     */
    public static final String EMPTY = "";

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

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
    /**
     * 配列文字列をジョインする
     *
     * @param stringSupplier ジョイン時のデリミタサプライア
     * @param strings        配列文字列
     * @return ジョイン後文字列
     */
    public static String join(Supplier<String> stringSupplier, String... strings) {
        CharSequence c = stringSupplier.get();
        return join(c, strings);
    }

    /**
     * コレクションをデリミタサプライヤを使用しジョインする.
     * <pre>
     *     {@code
     *         String actual = Strings2.join(() -> ", \n", newArrayList("ABC","DEF"));
     *     }
     * </pre>
     *
     * @param delimiterSupplier ジョイン時のデリミタサプライア
     * @param strings           コレクション文字列
     * @return ジョイン後文字列
     */
    public static String join(Supplier<String> delimiterSupplier, Collection<String> strings) {
        CharSequence delimiter = delimiterSupplier.get();
        return join(delimiter, strings);

    }

    /**
     * コレクションをデリミタでつなぎジョインする。
     * <pre>
     *     {@code
     *         String actual = Strings2.join(", \n", newArrayList("ABC","DEF"));
     *     }
     * </pre>
     *
     * @param delimiter  デリミタ
     * @param stringList 文字列のリスト
     * @return ジョイン後の文字列
     */
    public static String join(CharSequence delimiter, Collection<String> stringList) {
        StringBuilder sb = new StringBuilder();
        for (String string : stringList) {
            sb.append(string).append(delimiter);
        }
        if (stringList.size() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }


    /**
     * 配列文字列をジョインする
     *
     * @param c       ジョイン時のデリミタ
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(CharSequence c, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append(c);
        }
        if (strings.length > 0) {
            sb.setLength(sb.length() - c.length());
        }
        return sb.toString();
    }

    /**
     * 配列文字列をジョインする
     *
     * @param c       ジョイン時のデリミタ
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(char c, String... strings) {
        CharSequence delimiter = CharBuffer.wrap(new char[]{c});
        return Strings2.join(delimiter, strings);

    }

    /**
     * System.lineSeparator()をデリミタに使用し、配列文字列をジョインする
     *
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(String... strings) {
        String delimiter = System.lineSeparator();
        return join(delimiter, strings);
    }

    /**
     * System.lineSeparator()をデリミタに使用し、配列文字列をジョインする
     *
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(Collection<String> strings) {
        String delimiter = System.lineSeparator();
        return join(delimiter, strings);

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
    /**
     * サーチキャラクターが検索対象の文字列に含まれているかを返却する.
     *
     * <pre>
     *    このメソッドでは{@link String#indexOf(int)}を使用する.
     * </pre>
     *
     * <pre>
     * <code>
     * null
     * </code>
     *    または 空白 (&quot;&quot;) の文字列は
     * <code>
     * false
     * </code>
     * を返却する
     * </pre>
     *
     * <pre>
     *     Strings2.contains(null, *)    = false
     *     Strings2.contains(&quot;&quot;, *)      = false
     *     Strings2.contains(&quot;abc&quot;, 'a') = true
     *     Strings2.contains(&quot;abc&quot;, 'z') = false
     * </pre>
     *
     * @param str        検索対象の文字列
     * @param searchChar サーチキャラクター
     * @return 見つかった場合true 見つからない場合falseを返却する
     */
    public static boolean contains(String str, char searchChar) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    /**
     * サーチ文字列が検索対象の文字列に含まれているかを返却する.
     *
     * <pre>
     *    このメソッドでは{@link String#indexOf(int)}を使用する.
     * </pre>
     *
     * <pre>
     * <code>
     * null
     * </code>
     *    または 空白 (&quot;&quot;) の文字列は
     * <code>
     * false
     * </code>
     * を返却する
     * </pre>
     *
     * <pre>
     *     Strings2.contains(null, *)     = false
     *     Strings2.contains(*, null)     = false
     *     Strings2.contains(&quot;&quot;, &quot;&quot;)      = true
     *     Strings2.contains(&quot;abc&quot;, &quot;&quot;)   = true
     *     Strings2.contains(&quot;abc&quot;, &quot;a&quot;)  = true
     *     Strings2.contains(&quot;abc&quot;, &quot;z&quot;)  = false
     * </pre>
     *
     * @param str       検索対象の文字列
     * @param searchStr サーチキャラクター
     * @return 見つかった場合true 見つからない場合falseを返却する
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.contains(searchStr);
    }
    /**
     * 大文字小文字を無視して特定の文字で始まっているのかどうかを返します。
     *
     * @param target1 テキスト
     * @param target2 比較する文字列
     * @return 大文字小文字を無視して特定の文字で始まっていれば{@literal true}
     */
    public static boolean startsWithIgnoreCase(final String target1, final String target2) {
        if (isNull(target1) || isNull(target2)) {
            return false;
        }
        final int length1 = target1.length();
        final int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        final String s1 = target1.substring(0, target2.length());
        return s1.equalsIgnoreCase(target2);
    }
    /**
     * 特定の文字で始まっているのかどうかを返します。
     *
     * @param target1 テキスト
     * @param target2 比較する文字列
     * @return 特定の文字で始まっていれば{@literal true}
     */
    public static boolean startsWith(final String target1, final String target2) {
        if (isNull(target1) || isNull(target2)) {
            return false;
        }
        final int length1 = target1.length();
        final int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        final String s1 = target1.substring(0, target2.length());
        return s1.equals(target2);
    }
    public static String[] split(String str) {
        return split(str, (String) null, -1);
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return new String[]{};
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = newArrayList();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while (true) {
                    while (i < len) {
                        if (str.charAt(i) == separatorChar) {
                            if (match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if (match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return list.toArray(new String[list.size()]);
                }
            }
        }
    }

    private static String[] splitWorker(String str, String separatorChars, int max,
                                        boolean preserveAllTokens) {
        if (isNull(str)) {
            return new String[]{};
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = newArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if (separatorChars != null) {
                    if (separatorChars.length() != 1) {
                        label87:
                        while (true) {
                            while (true) {
                                if (i >= len) {
                                    break label87;
                                }

                                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while (true) {
                            while (true) {
                                if (i >= len) {
                                    break label71;
                                }

                                if (str.charAt(i) == sep) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while (true) {
                        while (true) {
                            if (i >= len) {
                                break label103;
                            }

                            if (Character.isWhitespace(str.charAt(i))) {
                                if (match || preserveAllTokens) {
                                    lastMatch = true;
                                    if (sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return list.toArray(new String[0]);
            }
        }
    }

}
