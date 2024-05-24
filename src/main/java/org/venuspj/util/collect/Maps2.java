package org.venuspj.util.collect;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.venuspj.util.primitives.Ints;

/**
 * Maps2クラスは、HashMapおよびLinkedHashMapの作成と操作のためのユーティリティメソッドを提供します。
 */
public final class Maps2 {


  /**
   * 指定されたマップで新しいHashMapインスタンスを作成します。
   *
   * @param map このHashMapに配置されるマッピングのマップ
   * @param <K> マップが保持するキーのタイプ
   * @param <V> マップされた値のタイプ
   * @return 与えられたマップのマッピングを含む新しいHashMapインスタンス
   */
  public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
    return new HashMap<>(map);
  }

  /**
   * 指定されたキーと値の関数を、 与えられたコレクションの要素に適用して新たなHashMapを生成します。
   *
   * @param <K> マップに保持されるキーのタイプ
   * @param <V> マッピングされる値のタイプ
   * @param <I> コレクションの要素のタイプ
   * @param collection このマップにマッピングされる要素を持つコレクション
   * @param keyFunction 要素からキーを抽出するための関数
   * @param valueFunction 要素から値を抽出するための関数
   * @return キーと値の関数によってマッピングされたコレクションの要素を含む新しいHashMap
   */
  public static <K, V, I> HashMap<K, V> newHashMap(Collection<? extends I> collection,
      Function<I, K> keyFunction, Function<I, V> valueFunction) {
    HashMap<K, V> map = newHashMapWithExpectedSize(collection.size());
    map.putAll(collection.stream().collect(Collectors.toMap(keyFunction, valueFunction)));
    return map;

  }

  /**
   * LinkedHashMapの新しいインスタンスを作成します。
   *
   * @param <K> リンクされたハッシュマップに保持されるキーの型
   * @param <V> マッピングされた値の型
   * @return LinkedHashMapの新しいインスタンス
   */
  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
    return new LinkedHashMap<>();

  }

  /**
   * 期待サイズを持つ新しいLinkedHashMapのインスタンスを作成します。
   *
   * @param expectedSize LinkedHashMapの期待サイズ
   * @param <K> LinkedHashMapによって維持されるキーのタイプ
   * @param <V> 返されるマップの値のタイプ
   * @return 期待サイズを持つ新しいLinkedHashMapのインスタンス
   */
  public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
    return new LinkedHashMap<>(capacity(expectedSize));
  }

  /**
   * 指定されたコレクション、キー関数、および値関数を使用して、新しいLinkedHashMapインスタンスを作成します。
   *
   * @param <K> マップのキーのタイプ
   * @param <V> マップの値のタイプ
   * @param <I> コレクションの要素のタイプ
   * @param collection 要素をマップするためのコレクション
   * @param keyFunction 要素からキーを抽出するための関数
   * @param valueFunction 要素から値を抽出するための関数
   * @return キー関数と値関数を使用してコレクションのマッピングを持つ新しいLinkedHashMapのインスタンス
   */
  public static <K, V, I> LinkedHashMap<K, V> newLinkedHashMap(Collection<? extends I> collection,
      Function<I, K> keyFunction, Function<I, V> valueFunction) {
    LinkedHashMap<K, V> map = newLinkedHashMapWithExpectedSize(collection.size());
    map.putAll(collection.stream().collect(Collectors.toMap(keyFunction, valueFunction)));
    return map;

  }

  static int capacity(int expectedSize) {
    if (expectedSize < 3) {
      CollectPreconditions.checkNonNegative(expectedSize, "expectedSize");
      return expectedSize + 1;
    }
    if (expectedSize < Ints.MAX_POWER_OF_TWO) {
      return (int) ((float) expectedSize / 0.75F + 1.0F);
    }
    return Integer.MAX_VALUE; // any large value
  }

  /**
   * デフォルトの初期容量 (16) とロードファクター (0.75) で新しいHashMapインスタンスを作成します。
   *
   * @param <K> このマップに保持されるキーの型
   * @param <V> マップされる値の型
   * @return 新しいHashMapインスタンス
   */
  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap<>();
  }

  /**
   * 指定された期待サイズを持つ新しいHashMapのインスタンスを作成します。
   *
   * @param expectedSize HashMapの期待サイズ
   * @param <K> HashMapが保持するキーの型
   * @param <V> HashMapのマップされた値の型
   * @return 指定された期待サイズを持つ新しいHashMapのインスタンス
   */
  public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
    return new HashMap<>(capacity(expectedSize));
  }
}
