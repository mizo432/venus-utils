package org.venuspj.util.beans;

/**
 * 文字列とオブジェクトの変換を行なうインターフェースです。
 */
public interface Converter<T> {

  /**
   * 値を文字列として返します。
   *
   * @param value 値
   * @return 文字列としての値
   */
  String getAsString(Object value);

  /**
   * 値をオブジェクトとして返します。
   *
   * @param value 値
   * @return オブジェクトとしての値
   */
  T getAsObject(String value);

  /**
   * 対象の型かどうかを返します。 対象のプロパティを指定しない場合に呼び出されます。
   *
   * @param clazz
   * @return
   */
  boolean isTarget(Class<?> clazz);
}
