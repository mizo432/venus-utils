package org.venuspj.exception;

import org.venuspj.util.message.MessageFormatter;

/**
 * venus-utilsの例外のベースクラスです。
 */
public class VRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -4452607868694297329L;

    private final String messageCode;

    private final Object[] args;

    private String message;

    private final String simpleMessage;

    /**
     * {@link VRuntimeException}を作成します。
     *
     * @param messageCode メッセージコード
     * @param args        引数
     */
    public VRuntimeException(final String messageCode, final Object[] args) {
        this(messageCode, args, null);

    }

    /**
     * {@link VRuntimeException}を作成します。
     *
     * @param messageCode メッセージコード
     * @param args        引数
     * @param cause       原因となった例外
     */
    public VRuntimeException(final String messageCode, final Object[] args,
                             final Throwable cause) {
        super(cause);
        this.messageCode = messageCode;
        this.args = args;
        simpleMessage = MessageFormatter.getSimpleMessage(messageCode, args);
        message = "[" + messageCode + "]" + simpleMessage;
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

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    /**
     * メッセージコードなしの単純なメッセージを返します。
     *
     * @return メッセージコードなしの単純なメッセージ
     */
    public final String getSimpleMessage() {
        return simpleMessage;
    }
}
