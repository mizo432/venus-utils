package org.venuspj.util.exception;

import org.venuspj.util.message.MessageFormatter;

/**
 * {@link IllegalArgumentException}をラップする例外です。
 */
public class VIllegalArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = -3701473506893554853L;

    /**
     * {@code null} である引数の名前
     */
    protected final String argName;

    /**
     * メッセージコード
     */
    protected final String messageCode;

    /**
     * メッセージの引数
     */
    protected final Object[] args;

    /**
     * {@link VIllegalArgumentException}を作成します。
     *
     * @param argName     引数の名前
     * @param messageCode メッセージコード
     * @param args        引数の配列
     */
    public VIllegalArgumentException(final String argName,
                                     final String messageCode, final Object[] args) {
        this(argName, messageCode, args, null);
    }

    /**
     * {@link VIllegalArgumentException}を作成します。
     *
     * @param argName     引数の名前
     * @param messageCode メッセージコード
     * @param args        引数の配列
     * @param cause       原因となった例外
     */
    public VIllegalArgumentException(final String argName,
                                     final String messageCode, final Object[] args, final Throwable cause) {
        super(MessageFormatter.getMessage(messageCode, args), cause);
        this.argName = argName;
        this.messageCode = messageCode;
        this.args = args;
    }

    /**
     * 引数の名前を返します。
     *
     * @return 引数の名前
     */
    public String getArgName() {
        return argName;
    }

    /**
     * メッセージコードを返します。
     *
     * @return メッセージコード
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * 引数の配列を返します。
     *
     * @return 引数の配列
     */
    public Object[] getArgs() {
        return args;
    }

}
