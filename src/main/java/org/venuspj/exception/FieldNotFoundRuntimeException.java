package org.venuspj.exception;

import java.lang.reflect.Field;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * {@link Field}が見つからない場合にスローされる例外です。
 */
public class FieldNotFoundRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -2715036865146285893L;

    private final Class<?> targetClass;

    private final String fieldName;

    /**
     * {@link FieldNotFoundRuntimeException}を作成します。
     *
     * @param targetClass
     *            ターゲットクラス
     * @param fieldName
     *            フィールド名
     */
    public FieldNotFoundRuntimeException(final Class<?> targetClass,
                                         final String fieldName) {
        super("EUTL0070", asArray(targetClass.getName(), fieldName));
        this.targetClass = targetClass;
        this.fieldName = fieldName;
    }

    /**
     * ターゲットクラスを返します。
     *
     * @return ターゲットクラス
     */
    public Class<?> getTargetClass() {
        return targetClass;
    }

    /**
     * フィールド名を返します。
     *
     * @return フィールド名
     */
    public String getFieldName() {
        return fieldName;
    }

}

