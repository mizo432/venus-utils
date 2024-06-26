package org.venuspj.util.beans.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import org.venuspj.util.precondition.Preconditions;
import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.FieldDesc;
import org.venuspj.util.beans.ParameterizedClassDesc;
import org.venuspj.util.beans.factory.ParameterizedClassDescFactory;
import org.venuspj.util.exception.FieldNotStaticRuntimeException;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.lang.Fields;

/**
 * {@link FieldDesc}の実装クラスです。
 */
public class FieldDescImpl implements FieldDesc {

  /**
   * このフィールドを所有するクラスの{@link BeanDesc}
   */
  protected final BeanDesc beanDesc;

  /**
   * フィールド
   */
  protected final Field field;

  /**
   * フィールド名
   */
  protected final String fieldName;

  /**
   * フィールドの型
   */
  protected final Class<?> fieldType;

  /**
   * パラメータ化された型の情報
   */
  protected final ParameterizedClassDesc parameterizedClassDesc;

  /**
   * インスタンスを構築します。
   *
   * @param beanDesc このフィールドを所有するクラスの{@link BeanDesc}。{@literal null}であってはいけません
   * @param field フィールド。{@literal null}であってはいけません
   */
  public FieldDescImpl(final BeanDesc beanDesc, final Field field) {
    Preconditions.checkNotNull(beanDesc, () -> new NullArgumentException("beanDesc"));
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    this.beanDesc = beanDesc;
    this.field = field;
    fieldName = field.getName();
    fieldType = field.getType();
    parameterizedClassDesc =
        ParameterizedClassDescFactory.createParameterizedClassDesc(
            field,
            beanDesc.getTypeVariables());
  }

  @Override
  public BeanDesc getBeanDesc() {
    return beanDesc;
  }

  @Override
  public Field getField() {
    return field;
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> Class<T> getFieldType() {
    return (Class<T>) fieldType;
  }

  @Override
  public boolean isPublic() {
    return Fields.isPublicField(field);
  }

  @Override
  public boolean isStatic() {
    return !Fields.isInstanceField(field);
  }

  @Override
  public boolean isFinal() {
    return Fields.isFinalField(field);
  }

  @Override
  public boolean isParameterized() {
    return parameterizedClassDesc != null
        && parameterizedClassDesc.isParameterizedClass();
  }

  @Override
  public ParameterizedClassDesc getParameterizedClassDesc() {
    return parameterizedClassDesc;
  }

  @Override
  public Class<?> getElementClassOfCollection() {
    if (!Collection.class.isAssignableFrom(fieldType) || !isParameterized()) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDesc.getArguments()[0];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getKeyClassOfMap() {
    if (!Map.class.isAssignableFrom(fieldType) || !isParameterized()) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDesc.getArguments()[0];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getValueClassOfMap() {
    if (!Map.class.isAssignableFrom(fieldType) || !isParameterized()) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDesc.getArguments()[1];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getFieldValue(final Object target) {
    Preconditions.checkNotNull(target, () -> new NullArgumentException("target"));

    return (T) Fields.get(field, target);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getStaticFieldValue() {
    if (!isStatic()) {
      throw new FieldNotStaticRuntimeException(
          beanDesc.getBeanClass(),
          fieldName);
    }
    return (T) Fields.get(field);
  }

  @Override
  public void setFieldValue(final Object target, final Object value) {
    Preconditions.checkNotNull(target, () -> new NullArgumentException("target"));

    Fields.set(field, target, value);
  }

  @Override
  public void setStaticFieldValue(final Object value) {
    if (!isStatic()) {
      throw new FieldNotStaticRuntimeException(
          beanDesc.getBeanClass(),
          fieldName);
    }
    Fields.set(field, value);
  }

}
