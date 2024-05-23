package org.venuspj.util.strings2;

import static java.util.logging.Level.WARNING;
import static org.venuspj.util.base.Preconditions.checkNotNull;
import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.objects2.Objects2.isNull;

import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.jetbrains.annotations.VisibleForTesting;
import org.venuspj.util.base.Joiner;
import org.venuspj.util.base.Platform;

public final class Strings2 {

  /**
   * 空文字<code>""</code>です。
   */
  public static final String EMPTY = "";
  public static final String BLANK = " ";

  /**
   * 文字列型の空の配列です。
   */
  public static final String[] EMPTY_STRING_ARRAY = new String[0];

  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.isEmpty();

  }

  public static boolean isNotEmpty(String aSrt) {
    return !isEmpty(aSrt);
    
  }

  /**
   * 指定された数だけ指定された文字列を繰り返します。
   *
   * @param string 繰り返すべき文字列。
   * @param count 文字列が繰り返されるべき回数。
   * @return 繰り返された文字列。
   * @throws ArrayIndexOutOfBoundsException 必要な配列のサイズが大きすぎる場合。
   */
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
   * 指定された数だけ与えられた文字列を繰り返し、指定された区切り文字で分けます。
   *
   * @param string 繰り返す文字列
   * @param times 文字列を繰り返す回数
   * @param separator 繰り返しの文字列の間に使用する区切り文字
   * @return 指定された区切り文字で分けられた繰り返し文字列
   */
  public static String repeat(String string, int times, String separator) {
    return Joiner.on(separator).join(Collections.nCopies(times, string));
  }

  /**
   * 与えられた文字列がnullまたは空の場合、デフォルト値を返します。
   *
   * @param string nullまたは空をチェックする文字列
   * @param defaultValue 文字列がnullまたは空の場合に返すデフォルト値
   * @return 元の文字列がnullでも空でもない場合は元の文字列、それ以外の場合はデフォルト値
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

  /**
   * 与えられた文字列が数値として解析できるかどうかを判断します。
   *
   * @param str チェックする文字列
   * @return 文字列が数値として解析できる場合は {@code true}、そうでない場合は {@code false}
   */
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

  /**
   * 指定された文字列で与えられた値を囲みます。
   *
   * @param value 囲む対象の値
   * @param surroundString 値を囲むために使われる文字列
   * @return 値が囲まれた結果の文字列
   */
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
      return EMPTY_STRING_ARRAY;
    }
    final List<String> list = newArrayList();
    final StringTokenizer st = new StringTokenizer(str, delim);
    while (st.hasMoreElements()) {
      list.add(st.nextElement().toString());
    }
    return list.toArray(EMPTY_STRING_ARRAY);
  }

  /**
   * 文字列の最初の文字を小文字に変換します。
   *
   * @param str 入力文字列
   * @return 最初の文字が大文字である場合、その文字を小文字に変換した入力文字列を返します。文字列が空であるか、最初の2文字が大文字である場合、同じ入力文字列をそのまま返します。
   */
  public static String decapitalize(final String str) {
    if (isEmpty(str)) {
      return str;
    }
    final char[] chars = str.toCharArray();
    if (chars.length >= 2 && Character.isUpperCase(chars[0])
        && Character.isUpperCase(chars[1])) {
      return str;
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
   * 与えられた入力文字列の末尾のテキストをトリムします。
   *
   * @param inputString トリムする文字列。
   * @return トリムされた文字列。
   */
  public static String trimTextAtEnd(final String inputString) {
    return trimTextAtEnd(inputString, BLANK);
  }

  /**
   * 指定された文字列を与えられたテキスト文字列の右側からトリムします。
   *
   * @param text トリムするテキスト文字列
   * @param trimString テキスト文字列の右側からトリムする文字列
   * @return トリム後の結果の文字列、またはトリムするものがない場合は元の文字列
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
   * 指定されたテキストが指定されたプレフィックスで始まる場合は真を返し、それ以外の場合は偽を返します。
   * <p>
   * 大文字と小文字は区別されません。
   *
   * @param text チェックするテキスト
   * @param prefix 比較するプレフィックス
   * @return テキストが与えられたプレフィックスで始まる場合（大文字・小文字を区別しない）、真を返します。それ以外の場合は偽を返します。
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
   * 与えられた文字列のすべての文字が数字であるかどうかを確認します。
   *
   * @param input 確認する入力文字列。
   * @return input 文字列のすべての文字が数字である場合は true、そうでない場合は false。
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

  /**
   * 与えられた文字列がnullでなければそのまま返し、nullであれば空文字列を返します。
   *
   * @param string テストし、可能なら返すべき文字列
   * @return {@code string} それ自体がnullでない場合、nullである場合は {@code ""}
   */
  public static String nullToEmpty(String string) {
    return Platform.nullToEmpty(string);
  }

  /**
   * 与えられた文字列が非空であればそのまま返します。それ以外の場合は{@code null}を返します。
   *
   * @param string テストし、おそらく返す文字列
   * @return {@code string}が非空であればそれ自体を返し、空またはnullであれば{@code null}を返します
   */
  public static String emptyToNull(String string) {
    return Platform.emptyToNull(string);
  }

  /**
   * 与えられた文字列が null または空文字列の場合、 {@code true} を返します。
   *
   * <p>文字列参照を {@link #nullToEmpty} で正規化することを検討してください。そうすれば、
   * このメソッドの代わりに {@link String#isEmpty()} を使用でき、 {@link String#toUpperCase} のような特別な null
   * 安全な形式のメソッドも必要なくなります。 また、空の文字列を {@code null} に変換して「逆方向」に正規化したい場合、 {@link #emptyToNull}
   * を使用することができます。
   *
   * @param string チェックする文字列参照
   * @return 文字列が null または空文字列の場合は {@code true}
   */
  public static boolean isNullOrEmpty(String string) {
    return Platform.stringIsNullOrEmpty(string);
  }

  /**
   * 引数 {@code string} の前方に {@code padChar} を必要な複数回追加し、 結果の文字列の長さが少なくとも {@code minLength}
   * になるような文字列を返します。
   * <p>
   * 例えば、
   *
   * <ul>
   * <li>{@code padStart("7", 3, '0')} は {@code "007"} を返します。
   * <li>{@code padStart("2010", 3, '0')} は {@code "2010"} を返します。
   * </ul>
   *
   * <p>{@link java.util.Formatter} にはより多くのフォーマット能力があります。
   *
   * @param string 結果の文字列の末尾に現れるべき文字列
   * @param minLength 結果の文字列が必ず持つべき最小長。ゼロまたは負の場合でも、入力文字列は常に返されます。
   * @param padChar 結果の最小長に達するまで結果の先頭に挿入する文字
   * @return パディングされた文字列
   */
  public static String padStart(String string, int minLength, char padChar) {
    checkNotNull(string); // eager for GWT.
    if (string.length() >= minLength) {
      return string;
    }
    StringBuilder sb = new StringBuilder(minLength);
    for (int i = string.length(); i < minLength; i++) {
      sb.append(padChar);
    }
    sb.append(string);
    return sb.toString();
  }

  /**
   * Returns a string, of length at least {@code minLength}, consisting of {@code string} appended
   * with as many copies of {@code padChar} as are necessary to reach that length. For example,
   *
   * <ul>
   * <li>{@code padEnd("4.", 5, '0')} returns {@code "4.000"}
   * <li>{@code padEnd("2010", 3, '!')} returns {@code "2010"}
   * </ul>
   *
   * <p>See {@link java.util.Formatter} for a richer set of formatting capabilities.
   *
   * @param string the string which should appear at the beginning of the result
   * @param minLength the minimum length the resulting string must have. Can be zero or negative, in
   * which case the input string is always returned.
   * @param padChar the character to append to the end of the result until the minimum length is
   * reached
   * @return the padded string
   */
  public static String padEnd(String string, int minLength, char padChar) {
    checkNotNull(string); // eager for GWT.
    if (string.length() >= minLength) {
      return string;
    }
    StringBuilder sb = new StringBuilder(minLength);
    sb.append(string);
    for (int i = string.length(); i < minLength; i++) {
      sb.append(padChar);
    }
    return sb.toString();
  }

  /**
   * 入力された{@code CharSequence} aとbの最長の共通文字列プレフィックスを返します。
   *
   * @param a 最初のCharSequence
   * @param b 二番目のCharSequence
   * @return aとbの共通プレフィックス
   */
  public static String commonPrefix(CharSequence a, CharSequence b) {
    checkNotNull(a);
    checkNotNull(b);

    int maxPrefixLength = Math.min(a.length(), b.length());
    int p = 0;
    while (p < maxPrefixLength && a.charAt(p) == b.charAt(p)) {
      p++;
    }
    if (validSurrogatePairAt(a, p - 1) || validSurrogatePairAt(b, p - 1)) {
      p--;
    }
    return a.subSequence(0, p).toString();
  }

  /**
   * 与えられた {@code CharSequence} オブジェクト {@code a} と {@code b} の最長の共通の接尾辞を返します。
   *
   * @param a 最初の CharSequence
   * @param b 二番目の CharSequence
   * @return {@code a} と {@code b} の共通の接尾辞
   */
  public static String commonSuffix(CharSequence a, CharSequence b) {
    checkNotNull(a);
    checkNotNull(b);

    int maxSuffixLength = Math.min(a.length(), b.length());
    int s = 0;
    while (s < maxSuffixLength && a.charAt(a.length() - s - 1) == b.charAt(b.length() - s - 1)) {
      s++;
    }
    if (validSurrogatePairAt(a, a.length() - s - 1)
        || validSurrogatePairAt(b, b.length() - s - 1)) {
      s--;
    }
    return a.subSequence(a.length() - s, a.length()).toString();
  }

  /**
   * 指定されたインデックスの文字が、指定された文字列で有効な代理ペアであるかどうかを調べます。
   *
   * @param string チェックするCharSequence
   * @param index チェックする文字のインデックス
   * @return 指定されたインデックスの文字が有効な代理ペアである場合はtrue、そうでない場合はfalse
   */
  @VisibleForTesting
  static boolean validSurrogatePairAt(CharSequence string, int index) {
    return index >= 0
        && index <= (string.length() - 2)
        && Character.isHighSurrogate(string.charAt(index))
        && Character.isLowSurrogate(string.charAt(index + 1));
  }

  /**
   * 寛容なアプローチを使用して文字列をフォーマットします。
   * <p>
   * このメソッドは、テンプレート文字列中の"%s"というプレースホルダーに引数を代入します。
   * テンプレート文字列に十分なプレースホルダーがない場合、追加の引数は結果の文字列の末尾に四角括弧で追加されます。
   *
   * @param template フォーマットするテンプレート文字列
   * @param args テンプレート文字列に代入する引数
   * @return フォーマットされた文字列
   */
  public static String lenientFormat(
      String template, Object... args) {
    template = String.valueOf(template); // null -> "null"

    if (args == null) {
      args = new Object[]{"(Object[])null"};
    } else {
      for (int i = 0; i < args.length; i++) {
        args[i] = lenientToString(args[i]);
      }
    }

    // start substituting the arguments into the '%s' placeholders
    StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
    int templateStart = 0;
    int i = 0;
    while (i < args.length) {
      int placeholderStart = template.indexOf("%s", templateStart);
      if (placeholderStart == -1) {
        break;
      }
      builder.append(template, templateStart, placeholderStart);
      builder.append(args[i++]);
      templateStart = placeholderStart + 2;
    }
    builder.append(template, templateStart, template.length());

    // if we run out of placeholders, append the extra args in square braces
    if (i < args.length) {
      builder.append(" [");
      builder.append(args[i++]);
      while (i < args.length) {
        builder.append(", ");
        builder.append(args[i++]);
      }
      builder.append(']');
    }

    return builder.toString();
  }


  /**
   * 柔軟なアプローチを使用して、指定されたオブジェクトの文字列表現を返します。
   * 文字列表現を取得する際に例外が発生した場合、このメソッドはオブジェクトのデフォルトのtoString()の振る舞いにフォールバックします。
   *
   * @param o 文字列表現を取得するためのオブジェクト
   * @return オブジェクトの文字列表現
   */
  private static String lenientToString(Object o) {
    try {
      return String.valueOf(o);
    } catch (Exception e) {
      // Default toString() behavior - see Object.toString()
      String objectToString =
          o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
      // Logger is created inline with fixed name to avoid forcing Proguard to create another class.
      Logger.getLogger("com.google.common.base.Strings")
          .log(WARNING, "Exception during lenientFormat for " + objectToString, e);
      return "<" + objectToString + " threw " + e.getClass().getName() + ">";
    }
  }
}
