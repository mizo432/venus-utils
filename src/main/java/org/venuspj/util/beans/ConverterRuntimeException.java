package org.venuspj.util.beans;

import org.venuspj.exception.VRuntimeException;

/**
 * {@link Converter}でエラーが起きた場合にスローされる例外です。
 *
 * @author higa
 */
public class ConverterRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = 1L;

    private String propertyName;

    private Object value;

    /**
     * インスタンスを構築します。
     *
     * @param propertyName プロパティ名
     * @param value        値
     * @param cause        原因
     */
    public ConverterRuntimeException(String propertyName, Object value,
                                     Throwable cause) {
        super("ESSR0097", new Object[]{propertyName, value, cause}, cause);
        this.propertyName = propertyName;
        this.value = value;
    }

    /**
     * プロパティ名を返します。
     *
     * @return プロパティ名
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 値を返します。
     *
     * @return 値
     */
    public Object getValue() {
        return value;
    }
}