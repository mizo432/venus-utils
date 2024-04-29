package org.venuspj.util.strings2;

import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.objects2.Objects2.isNull;

import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import org.venuspj.util.base.Joiner;

public final class Strings2 {

  /**
   * 空文字<code>""</code>です。
   */
  public static final String EMPTY = "";
  public static final String BLANK = " ";

  public static final String[] EMPTY_STRING_ARRAY = new String[0];

  /**
   * 文字列型の空の配列です。
   */
  public static final String[] EMPTY_STRINGS = new String[0];

  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.isEmpty();
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
    if (count == 0) {
      return "";
    }
    string.getChars(0, len, array, 0);
    int n;
    for (n = len; n < size - n; n <<= 1) {
      System.arraycopy(array, 0, array, n, n);
    }
    System.arraycopy(array, 0, array, n, size - n);
    return new String(array);

  }

  /**
   * Repeats the given string a specified number of times, separated by a given separator.
   *
   * @param string the string to repeat
   * @param times the number of times to repeat the string
   * @param separator the separator to use between repeated strings
   * @return the repeated string separated by the specified separator
   */
  public static String repeat(String string, int times, String separator) {
    return Joiner.on(separator).join(Collections.nCopies(times, string));
  }

  /**
   * Returns a default value if the given string is null or empty.
   *
   * @param string the string to check for null or empty
   * @param defaultValue the default value to return if the string is null or empty
   * @return the original string if it is neither null nor empty, otherwise the default value
   */
  public static String defaultIfEmpty(String string, String defaultValue) {
    if (string == null || string.isEmpty()) {
      return defaultValue;
    }
    return string;
  }

  /**
   * Returns the given string if it is non-null; the default value otherwise.
   *
   * @param string the string to test and possibly return
   * @param defaultValue the default value to be returned if the given string is null
   * @return the given string itself if it is non-null; the default value if it is null
   */
  public static String defaultIfNull(String string, String defaultValue) {
    if (string == null) {
      return defaultValue;
    }
    return string;
  }

  /**
   * 配列文字列をジョインする
   *
   * @param stringSupplier ジョイン時のデリミタサプライア
   * @param strings 配列文字列
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
   * @param strings コレクション文字列
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
   * @param delimiter デリミタ
   * @param stringList 文字列のリスト
   * @return ジョイン後の文字列
   */
  public static String join(CharSequence delimiter, Collection<String> stringList) {
    StringBuilder sb = new StringBuilder();
    for (String string : stringList) {
      sb.append(string).append(delimiter);
    }
    if (!stringList.isEmpty()) {
      sb.setLength(sb.length() - delimiter.length());
    }
    return sb.toString();
  }


  /**
   * 配列文字列をジョインする
   *
   * @param c ジョイン時のデリミタ
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
   * @param c ジョイン時のデリミタ
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

  public static String surround(String value, String surroundString) {
    return surroundString + value + surroundString;
  }

  /**
   * 文字列を置き換えます。
   *
   * @param text テキスト
   * @param fromText 置き換え対象のテキスト
   * @param toText 置き換えるテキスト
   * @return 結果
   */
  public static String replace(final String text,
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
   * @param str 文字列
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
    return list.toArray(EMPTY_STRING_ARRAY);
  }

  /**
   * Decapitalizes the first character of a String.
   *
   * @param name the input String
   * @return the input String with the first character decapitalized if it is not already
   * decapitalized, or the same input String if it is empty or the first two characters are
   * uppercase
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
   * @param inputText テキスト
   * @return 結果の文字列
   */
  public static String trimTextAtStart(final String inputText) {
    return trimTextAtStart(inputText, BLANK);
  }

  /**
   * Trims the given string from the start of the input string.
   *
   * @param inputText the input text
   * @param trimString the substring to be removed
   * @return the resulting string
   */
  public static String trimTextAtStart(final String inputText, String trimString) {
    if (inputText == null) {
      return null;
    }
    if (trimString == null) {
      trimString = " ";
    }
    if (inputText.length() < trimString.length()) {
      return inputText;
    }

    String result = inputText;

    while (result.startsWith(trimString)) {
      result = result.substring(trimString.length());
    }

    return result;
  }

  /**
   * Trims the specified string from the right side of the given text string.
   *
   * @param text the text string from which to trim
   * @param trimString the string to trim off the right side of the text string
   * @return the result string after trimming, or the original string if nothing to trim
   */
  public static String trimTextAtEnd(final String inputString) {
    return trimTextAtEnd(inputString, BLANK);
  }

  /**
   * Trims the specified string from the right side of the given text string.
   *
   * @param text the text string from which to trim
   * @param trimString the string to trim off the right side of the text string
   * @return the result string after trimming, or the original string if nothing to trim
   */
  public static String trimTextAtEnd(final String text, String trimString) {
    if (text == null) {
      return null;
    }

    if (text.length() < (trimString == null ? 1 : trimString.length())) {
      return text;
    }

    trimString = (trimString == null) ? " " : trimString;

    if (text.endsWith(trimString)) {
      return trimTextAtEnd(text.substring(0, text.length() - trimString.length()), trimString);
    }

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
   * @param str 検索対象の文字列
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
   * @param str 検索対象の文字列
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
   * Returns true if the given text starts with the specified prefix, ignoring case considerations.
   *
   * @param text the text to check
   * @param prefix the prefix to compare
   * @return true if the text starts with the given prefix (ignoring case), otherwise false
   */
  public static boolean startsWithIgnoreCase(final String text, final String prefix) {
    return Optional.ofNullable(text)
        .filter(t -> t.length() >= Optional.ofNullable(prefix).map(String::length).orElse(0))
        .map(t -> t.substring(0, Optional.ofNullable(prefix).map(String::length).orElse(0)))
        .map(t -> t.equalsIgnoreCase(prefix))
        .orElse(false);
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

  /**
   * Checks if all characters in a given string are digits.
   *
   * @param input The input string to be checked.
   * @return true if all characters in the input string are digits, false otherwise.
   */
  public static boolean isAllDigits(String input) {
    if (input == null) {
      return false;
    }
    return input.chars().allMatch(Character::isDigit);
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

          return list.toArray(EMPTY_STRING_ARRAY);
        }
      }
    }
  }

  static String[] splitWorker(String str, String separatorChars, int max,
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
