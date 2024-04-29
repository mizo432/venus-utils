package org.venuspj.util.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GenericsesTest {

  /**
   * Method under test: {@link Genericses#isTypeOf(Type, Class)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void isTypeOf() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Genericses.isTypeOf(null, clazz);
  }

  /**
   * Method under test: {@link Genericses#isTypeOf(Type, Class)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void isTypeOf2() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: clazz is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$1(Genericses.java:35)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:35)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    Class<Object> type = Object.class;

    // Act
    Genericses.isTypeOf(type, null);
  }

  /**
   * Method under test: {@link Genericses#isTypeOf(Type, Class)}
   */
  @Test
  void isTypeOf3() {
    // Arrange
    Class<Object> type = Object.class;
    Class<Class> clazz = Class.class;

    // Act and Assert
    assertFalse(Genericses.isTypeOf(type, clazz));
  }

  /**
   * Method under test: {@link Genericses#isTypeOf(Type, Class)}
   */
  @Test
  void isTypeOf4() {
    // Arrange
    Class<Class> type = Class.class;
    Class<Class> clazz = Class.class;

    // Act and Assert
    assertTrue(Genericses.isTypeOf(type, clazz));
  }

  /**
   * Method under test: {@link Genericses#getRawClass(Type)}
   */
  @Test
  void getRawClass() {
    // Arrange and Act
    Class<?> actualRawClass = Genericses.getRawClass(null);

    // Assert
    assertNull(actualRawClass);
  }

  /**
   * Method under test: {@link Genericses#getRawClass(Type)}
   */
  @Test
  void getRawClass2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<?> actualRawClass = Genericses.getRawClass(type);

    // Assert
    Class<Object> expectedRawClass = Object.class;
    assertEquals(expectedRawClass, actualRawClass);
  }

  /**
   * Method under test: {@link Genericses#getGenericParameters(Type)}
   */
  @Test
  void getGenericParameters() {
    // Arrange, Act and Assert
    assertNull(Genericses.getGenericParameters(null));
  }

  /**
   * Method under test: {@link Genericses#getGenericParameter(Type, int)}
   */
  @Test
  void getGenericParameter() {
    // Arrange, Act and Assert
    assertNull(Genericses.getGenericParameter(null, 1));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfArray(Type)}
   */
  @Test
  void getElementTypeOfArray() {
    // Arrange, Act and Assert
    assertNull(Genericses.getElementTypeOfArray(null));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfCollection(Type)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getElementTypeOfCollection() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getElementTypeOfCollection(Genericses.java:161)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getElementTypeOfCollection(null);
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfCollection(Type)}
   */
  @Test
  void getElementTypeOfCollection2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(Genericses.getElementTypeOfCollection(type));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfCollection(Type)}
   */
  @Test
  void getElementTypeOfCollection3() {
    // Arrange
    Class<Collection> type = Collection.class;

    // Act and Assert
    assertNull(Genericses.getElementTypeOfCollection(type));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfList(Type)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getElementTypeOfList() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getElementTypeOfList(Genericses.java:177)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getElementTypeOfList(null);
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfList(Type)}
   */
  @Test
  void getElementTypeOfList2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(Genericses.getElementTypeOfList(type));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfList(Type)}
   */
  @Test
  void getElementTypeOfList3() {
    // Arrange
    Class<List> type = List.class;

    // Act and Assert
    assertNull(Genericses.getElementTypeOfList(type));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfSet(Type)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getElementTypeOfSet() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getElementTypeOfSet(Genericses.java:193)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getElementTypeOfSet(null);
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfSet(Type)}
   */
  @Test
  void getElementTypeOfSet2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(Genericses.getElementTypeOfSet(type));
  }

  /**
   * Method under test: {@link Genericses#getElementTypeOfSet(Type)}
   */
  @Test
  void getElementTypeOfSet3() {
    // Arrange
    Class<Set> type = Set.class;

    // Act and Assert
    assertNull(Genericses.getElementTypeOfSet(type));
  }

  /**
   * Method under test: {@link Genericses#getKeyTypeOfMap(Type)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getKeyTypeOfMap() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getKeyTypeOfMap(Genericses.java:209)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getKeyTypeOfMap(null);
  }

  /**
   * Method under test: {@link Genericses#getKeyTypeOfMap(Type)}
   */
  @Test
  void getKeyTypeOfMap2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(Genericses.getKeyTypeOfMap(type));
  }

  /**
   * Method under test: {@link Genericses#getKeyTypeOfMap(Type)}
   */
  @Test
  void getKeyTypeOfMap3() {
    // Arrange
    Class<Map> type = Map.class;

    // Act and Assert
    assertNull(Genericses.getKeyTypeOfMap(type));
  }

  /**
   * Method under test: {@link Genericses#getValueTypeOfMap(Type)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getValueTypeOfMap() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getValueTypeOfMap(Genericses.java:225)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getValueTypeOfMap(null);
  }

  /**
   * Method under test: {@link Genericses#getValueTypeOfMap(Type)}
   */
  @Test
  void getValueTypeOfMap2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(Genericses.getValueTypeOfMap(type));
  }

  /**
   * Method under test: {@link Genericses#getValueTypeOfMap(Type)}
   */
  @Test
  void getValueTypeOfMap3() {
    // Arrange
    Class<Map> type = Map.class;

    // Act and Assert
    assertNull(Genericses.getValueTypeOfMap(type));
  }

  /**
   * Method under test: {@link Genericses#getTypeVariableMap(Class)}
   */
  @Test
  void getTypeVariableMap() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Map<TypeVariable<?>, Type> actualTypeVariableMap = Genericses.getTypeVariableMap(clazz);

    // Assert
    assertTrue(actualTypeVariableMap.isEmpty());
  }

  /**
   * Method under test: {@link Genericses#getTypeVariableMap(Class)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getTypeVariableMap2() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   org.venuspj.util.exception.NullArgumentException: [EUTL0008]clazz
    //       at org.venuspj.util.misc.Assertions.assertArgumentNotNull(Assertions.java:29)
    //       at org.venuspj.util.lang.Genericses.getTypeVariableMap(Genericses.java:239)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getTypeVariableMap(null);
  }

  /**
   * Method under test: {@link Genericses#getTypeVariableMap(Class)}
   */
  @Test
  void getTypeVariableMap3() {
    // Arrange
    Class<Class> clazz = Class.class;

    // Act
    Map<TypeVariable<?>, Type> actualTypeVariableMap = Genericses.getTypeVariableMap(clazz);

    // Assert
    assertEquals(2, actualTypeVariableMap.size());
  }

  /**
   * Method under test: {@link Genericses#gatherTypeVariables(Class, Type, Map)}
   */
  @Test
  void gatherTypeVariables() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Genericses.gatherTypeVariables(clazz, null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#gatherTypeVariables(Class, Type, Map)}
   */
  @Test
  void gatherTypeVariables2() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange and Act
    Genericses.gatherTypeVariables(null, null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#gatherTypeVariables(Class, Type, Map)}
   */
  @Test
  void gatherTypeVariables3() {
    // Arrange
    Class<Class> clazz = Class.class;
    HashMap<TypeVariable<?>, Type> map = new HashMap<>();

    // Act
    Genericses.gatherTypeVariables(clazz, null, map);

    // Assert
    assertEquals(1, map.size());
  }

  /**
   * Method under test: {@link Genericses#gatherTypeVariables(Type, Map)}
   */
  @Test
  void gatherTypeVariables4() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange and Act
    Genericses.gatherTypeVariables(null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#getActualClass(Type, Map)}
   */
  @Test
  void getActualClass() {
    // Arrange and Act
    Class<?> actualActualClass = Genericses.getActualClass(null, new HashMap<>());

    // Assert
    assertNull(actualActualClass);
  }

  /**
   * Method under test: {@link Genericses#getActualClass(Type, Map)}
   */
  @Test
  void getActualClass2() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<?> actualActualClass = Genericses.getActualClass(type, new HashMap<>());

    // Assert
    Class<Object> expectedActualClass = Object.class;
    assertEquals(expectedActualClass, actualActualClass);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfArray(Type, Map)}
   */
  @Test
  void getActualElementClassOfArray() {
    // Arrange and Act
    Class<?> actualActualElementClassOfArray = Genericses.getActualElementClassOfArray(null,
        new HashMap<>());

    // Assert
    assertNull(actualActualElementClassOfArray);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfCollection(Type, Map)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getActualElementClassOfCollection() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getActualElementClassOfCollection(Genericses.java:405)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getActualElementClassOfCollection(null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfCollection(Type, Map)}
   */
  @Test
  void getActualElementClassOfCollection2() {
    // Arrange
    Class<Object> type = Object.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualElementClassOfCollection = Genericses.getActualElementClassOfCollection(
        type, map);

    // Assert
    assertNull(actualActualElementClassOfCollection);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfCollection(Type, Map)}
   */
  @Test
  void getActualElementClassOfCollection3() {
    // Arrange
    Class<Collection> type = Collection.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualElementClassOfCollection = Genericses.getActualElementClassOfCollection(
        type, map);

    // Assert
    assertNull(actualActualElementClassOfCollection);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfList(Type, Map)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getActualElementClassOfList() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getActualElementClassOfList(Genericses.java:429)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getActualElementClassOfList(null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfList(Type, Map)}
   */
  @Test
  void getActualElementClassOfList2() {
    // Arrange
    Class<Object> type = Object.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualElementClassOfList = Genericses.getActualElementClassOfList(type, map);

    // Assert
    assertNull(actualActualElementClassOfList);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfList(Type, Map)}
   */
  @Test
  void getActualElementClassOfList3() {
    // Arrange
    Class<List> type = List.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualElementClassOfList = Genericses.getActualElementClassOfList(type, map);

    // Assert
    assertNull(actualActualElementClassOfList);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfSet(Type, Map)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getActualElementClassOfSet() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getActualElementClassOfSet(Genericses.java:453)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getActualElementClassOfSet(null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfSet(Type, Map)}
   */
  @Test
  void getActualElementClassOfSet2() {
    // Arrange
    Class<Object> type = Object.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualElementClassOfSet = Genericses.getActualElementClassOfSet(type, map);

    // Assert
    assertNull(actualActualElementClassOfSet);
  }

  /**
   * Method under test: {@link Genericses#getActualElementClassOfSet(Type, Map)}
   */
  @Test
  void getActualElementClassOfSet3() {
    // Arrange
    Class<Set> type = Set.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualElementClassOfSet = Genericses.getActualElementClassOfSet(type, map);

    // Assert
    assertNull(actualActualElementClassOfSet);
  }

  /**
   * Method under test: {@link Genericses#getActualKeyClassOfMap(Type, Map)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getActualKeyClassOfMap() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getActualKeyClassOfMap(Genericses.java:477)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getActualKeyClassOfMap(null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#getActualKeyClassOfMap(Type, Map)}
   */
  @Test
  void getActualKeyClassOfMap2() {
    // Arrange
    Class<Object> type = Object.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualKeyClassOfMap = Genericses.getActualKeyClassOfMap(type, map);

    // Assert
    assertNull(actualActualKeyClassOfMap);
  }

  /**
   * Method under test: {@link Genericses#getActualKeyClassOfMap(Type, Map)}
   */
  @Test
  void getActualKeyClassOfMap3() {
    // Arrange
    Class<Map> type = Map.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualKeyClassOfMap = Genericses.getActualKeyClassOfMap(type, map);

    // Assert
    assertNull(actualActualKeyClassOfMap);
  }

  /**
   * Method under test: {@link Genericses#getActualValueClassOfMap(Type, Map)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void getActualValueClassOfMap() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: type is null
    //       at org.venuspj.util.lang.Genericses.lambda$isTypeOf$0(Genericses.java:34)
    //       at org.venuspj.util.base.Preconditions.checkNotNull(Preconditions.java:42)
    //       at org.venuspj.util.lang.Genericses.isTypeOf(Genericses.java:34)
    //       at org.venuspj.util.lang.Genericses.getActualValueClassOfMap(Genericses.java:501)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    Genericses.getActualValueClassOfMap(null, new HashMap<>());
  }

  /**
   * Method under test: {@link Genericses#getActualValueClassOfMap(Type, Map)}
   */
  @Test
  void getActualValueClassOfMap2() {
    // Arrange
    Class<Object> type = Object.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualValueClassOfMap = Genericses.getActualValueClassOfMap(type, map);

    // Assert
    assertNull(actualActualValueClassOfMap);
  }

  /**
   * Method under test: {@link Genericses#getActualValueClassOfMap(Type, Map)}
   */
  @Test
  void getActualValueClassOfMap3() {
    // Arrange
    Class<Map> type = Map.class;

    HashMap<TypeVariable<?>, Type> map = new HashMap<>();
    map.putAll(new HashMap<>());

    // Act
    Class<?> actualActualValueClassOfMap = Genericses.getActualValueClassOfMap(type, map);

    // Assert
    assertNull(actualActualValueClassOfMap);
  }

}