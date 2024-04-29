package org.venuspj.util.assigner;


import static org.venuspj.util.collect.Lists2.newArrayList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.venuspj.util.exception.NoSuchConstructorRuntimeException;
import org.venuspj.util.lang.Classes;

/**
 *
 */
public class Assigner {

  /**
   * senderのクローンを作成する.
   * <pre>
   * シャローコピーとなる。
   * </pre>
   *
   * @param sender 送り元
   * @param <T> 対象のクラス
   * @return 送り元の型と同じ
   */
  @SuppressWarnings("unchecked")
  public static <T> T assign(T sender) {
    try {
      T destination = (T) sender.getClass().getConstructor().newInstance();
      assignTo(sender, destination);
      return destination;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException | InvocationTargetException e) {
      throw new NoSuchConstructorRuntimeException(sender.getClass(), Classes.empty(), e);
    }

  }

  /**
   * オブジェクトの各フィルドを送り元から送り先にコピーする。
   * <pre>
   * 送り元のフィールドの値がnullの場合には設定しない。
   * </pre>
   * <pre>
   * 利用方法
   * Person party = personMapper.findById(personId);
   * AdderssId AdderssId = party.getAddress().getAddressId()
   * Address address = addressRepository.findById(personId);
   * Assginer.assignTo(address, party.getAddress());
   * </pre>
   *
   * @param sender 送り元
   * @param destination 送り先
   * @param <T> 対象のクラス
   */
  public static <T> void assignTo(T sender, T destination) {
    Class<? extends Object> inputClazz = sender.getClass();
    List<Field> fields = createFields(inputClazz);
    for (Field field : fields) {
      field.setAccessible(true);
      Object obj;
      try {
        obj = field.get(sender);
        if (obj != null) {
          field.set(destination, obj);
        }
      } catch (IllegalArgumentException | IllegalAccessException e) {
        throw new RuntimeException(" can't write " + field.getName(), e);
      }
    }

  }

  /**
   * オブジェクトの各フィルドを送り元から送り先にコピーする。
   * <pre>
   * 送り元のフィールドの値がnullの場合には設定しない。
   * </pre>
   * <pre>
   * 利用方法
   * Person party = personMapper.findById(personId);
   * AdderssId AdderssId = party.getAddress().getAddressId()
   * Optional<Address> addressOptional = addressRepository.findById(personId);
   * Assginer.assignTo(addressOptional, party.getAddress());
   * </pre>
   *
   * @param senderOptional 送り元
   * @param destination 送り先
   * @param <T> 相性のクラス
   */
  public static <T> void assignTo(Optional<T> senderOptional, T destination) {
    senderOptional.ifPresent(t -> assignTo(t, destination));
  }

  /**
   * 対象クラスのフィールドの一覧を取得する.
   *
   * @param aClass 対象クラス
   * @return フィールドの一覧
   */
  private static List<Field> createFields(Class<? extends Object> aClass) {
    List<Field> results = newArrayList();

    for (Class<?> clazz = aClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
      try {
        Field[] fields = clazz.getDeclaredFields();
        results.addAll(Arrays.asList(fields));
      } catch (SecurityException ignored) {
      }
    }

    return results;
  }
}
