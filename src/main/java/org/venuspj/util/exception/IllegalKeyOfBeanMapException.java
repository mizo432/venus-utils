package org.venuspj.util.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.util.Map;

/**
 * {@literal BeanMap}に含まれていないキーを使用した場合にスローされる例外です。
 */
public class IllegalKeyOfBeanMapException extends VIllegalArgumentException {

  private static final long serialVersionUID = 3456740832476626338L;

  /**
   * インスタンスを構築します。
   *
   * @param key マップのキー
   * @param map マップ
   */
  public IllegalKeyOfBeanMapException(final Object key, final Map<?, ?> map) {
    super("key", "EUTL0016", asArray(key, map));
  }

}
