package org.venuspj.util.objects2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.collect.Maps2.newHashMap;
import static org.venuspj.util.objects2.Objects2.toStringHelper;
import static org.venuspj.util.strings2.Strings2.EMPTY_STRING_ARRAY;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.venuspj.util.objects2.Objects2.ToStringHelper;

/**
 * このクラスは、{@link ToStringHelper}クラスのテストメソッドを含みます。
 */
public class ToStringHelperTest {


  @Test
  public void addAllFields() {
    TargetClass targetClass = createClassType();
    String actual = toStringHelper(targetClass).addAllFields().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void multiLine_classType() {
    TargetClass targetClass = createClassType();
    String actual = toStringHelper(targetClass).addAllDeclaredFields().multiLine().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void nonMultiLine_classType() {
    TargetClass targetClass = createClassType();
    String actual = toStringHelper(targetClass).addAllDeclaredFields().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void multiLine_recordType() {
    TargetRecord targetRecord = createRecordType();
    String actual = toStringHelper(targetRecord).addAllDeclaredFields().multiLine().omitNullValues()
        .toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void omitNullValues_classType() {
    TargetClass targetClass = createClassType();
    String actual = toStringHelper(targetClass).addAllFields().omitNullValues().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  private TargetClass createClassType() {
    AnyDateTimeClass anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
    TargetChildClass child = new TargetChildClass("1", 1, 0,
        anyDateTime, Succeed.SUCCESS);
    return new TargetClass(child, "1", 1, 0,
        anyDateTime, Succeed.SUCCESS);

  }

  private TargetRecord createRecordType() {
    AnyDateTimeClass anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
    TargetChildRecord child = new TargetChildRecord("1", 1, 0,
        anyDateTime, null, Succeed.SUCCESS);
    return new TargetRecord(child, "1", 1, 0,
        anyDateTime, null, Succeed.SUCCESS);

  }

  @Test
  public void addAllDeclaredFields() {
    TargetClass targetClass = createClassType();
    String actual = toStringHelper(targetClass).addAllDeclaredFields().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void listClass() {
    AnyDateTimeClass anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
    TargetChildClass child = new TargetChildClass("1", 1, 0,
        anyDateTime, Succeed.SUCCESS);
    ListClass targetClass = new ListClass();
    targetClass.list.addAll(newArrayList(child, child, child, child, child, child));
    String actual = toStringHelper(targetClass).addAllDeclaredFields().multiLine().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void integerClass() {
    Integer target = 1;
    String actual = toStringHelper(target)
        .defaultConfig()
        .toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void intClass() {
    int target = 1;
    String actual = toStringHelper(target).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void bigDecimalDirect() {
    BigDecimal given = BigDecimal.valueOf(1.1d);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull().isEqualTo("1.1");
  }

  @Test
  public void bigIntegerDirect() {
    BigInteger given = BigInteger.valueOf(1);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull().isEqualTo("1");
  }

  @Test
  public void simpleDirect() {
    InputStream given = new ByteArrayInputStream(new byte[0]);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void enumDirect() {
    Succeed given = Succeed.FAILURE;
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull().isEqualTo("FAILURE");
    System.out.println(actual);
  }

  @Test
  public void listDirect() {
    List<String> given = newArrayList("A", "B");
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void listDirect_nonMultiLine() {
    List<String> given = newArrayList("A", "B", "C", "D", "E", "F");
    String actual = toStringHelper(given).addAllDeclaredFields().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void listDirect_multiLine() {
    List<String> given = newArrayList("A", "B", "C", "D", "E", "F");
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void arrayDirect() {
    String[] given = newArrayList("A", "B").toArray(EMPTY_STRING_ARRAY);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void arrayDirect_nonMultiLine() {
    String[] given = newArrayList("A", "B", "C", "D", "E", "F").toArray(EMPTY_STRING_ARRAY);
    String actual = toStringHelper(given).addAllDeclaredFields().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void arrayDirect_multiLine() {
    String[] given = newArrayList("A", "B", "C", "D", "E", "F").toArray(EMPTY_STRING_ARRAY);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void mapDirect() {
    Map<String, Integer> given = newHashMap();
    given.put("A", 1);
    given.put("B", 2);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void mapDirect_multiLine() {
    Map<String, Integer> given = newHashMap();
    given.put("A", 1);
    given.put("B", 2);
    given.put("C", 3);
    given.put("D", 4);
    given.put("E", 5);
    given.put("F", 6);
    String actual = toStringHelper(given).defaultConfig().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void mapDirect_nonMultiLine() {
    Map<String, Integer> given = newHashMap();
    given.put("A", 1);
    given.put("B", 2);
    given.put("C", 3);
    given.put("D", 4);
    given.put("E", 5);
    given.put("F", 6);
    String actual = toStringHelper(given).addAllDeclaredFields().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  @Test
  public void helper() {
    TargetClass targetClass = createClassType();
    String actual = toStringHelper(targetClass)
        .add("integerField", targetClass.integerField)
        .add("stringField", targetClass.stringField)
        .add("anyDateTime", targetClass.anyDateTime)
        .multiLine().toString();
    assertThat(actual).isNotNull();
    System.out.println(actual);
  }

  private static abstract class AbstractTargetClass {

    protected AbstractTargetClass(String stringField,
        Integer integerField,
        int intField,
        AnyDateTimeClass anyDateTime, Succeed succeed) {
      this.stringField = stringField;
      this.integerField = integerField;
      this.intField = intField;
      this.anyDateTime = anyDateTime;
      this.nullValue = null;
      this.succeed = succeed;
    }

    public String stringField;
    public Integer integerField;
    public int intField;
    public AnyDateTimeClass anyDateTime;
    public Object nullValue;
    public Succeed succeed;
  }

  private static class TargetClass extends AbstractTargetClass {

    private TargetChildClass child;

    TargetClass(TargetChildClass child, String stringField, Integer integerField, int intField,
        AnyDateTimeClass anyDateTime, Succeed succeed) {
      super(stringField,
          integerField,
          intField, anyDateTime, succeed);
      this.child = child;
    }
  }

  private static class TargetChildClass {

    public String stringField;
    public Integer integerField;
    public int intField;
    public AnyDateTimeClass anyDateTime;
    public Object nullValue;
    public Succeed succeed;

    TargetChildClass(String stringField,
        Integer integerField,
        int intField,
        AnyDateTimeClass anyDateTime, Succeed succeed) {
      this.stringField = stringField;
      this.integerField = integerField;
      this.intField = intField;
      this.anyDateTime = anyDateTime;
      this.nullValue = null;
      this.succeed = succeed;


    }


  }

  private static class ListClass {

    public List<TargetChildClass> list = newArrayList();

  }

  private static class AnyDateTimeClass {

    private LocalDateTime value;

    AnyDateTimeClass(LocalDateTime aValue) {
      value = LocalDateTime.now();

    }

  }

  private record TargetRecord(TargetChildRecord childRecord, String stringField,
                              Integer integerField, int intField,
                              AnyDateTimeClass anyDateTime, Object nullValue, Succeed succeed) {

  }

  private record TargetChildRecord(String stringField, Integer integerField, int intField,
                                   AnyDateTimeClass anyDateTime, Object nullValue,
                                   Succeed succeed) {

  }

  private enum Succeed {
    UNKNOWN, SUCCESS, FAILURE;
  }
}