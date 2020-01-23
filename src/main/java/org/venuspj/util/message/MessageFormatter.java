package org.venuspj.util.message;

import org.venuspj.util.io.ResourceBundles;
import org.venuspj.util.misc.Disposable;
import org.venuspj.util.misc.Disposables;
import org.venuspj.util.strings2.Strings2;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * メッセージコードと引数からメッセージを組み立てるクラスです。
 */
public abstract class MessageFormatter {

    /**
     * メッセージコードの数値部の長さ
     */
    protected static final int CODE_NUMBER_LENGTH = 4;

    /**
     * メッセージコードに対応するリソースバンドル名の接尾辞
     */
    protected static final String MESSAGES = "Messages";

    /**
     * 初期化済みを示すフラグ
     */
    protected static volatile boolean initialized;

    /**
     * メッセージを返します。
     *
     * @param messageCode メッセージコード
     * @param args        引数
     * @return メッセージ
     */
    public static String getMessage(final String messageCode,
                                    final Object... args) {
        return getFormattedMessage(
                messageCode == null ? "" : messageCode,
                getSimpleMessage(messageCode, args));
    }

    /**
     * メッセージコードつきのメッセージを返します。
     *
     * @param messageCode   メッセージコード
     * @param simpleMessage 引数が展開された単純なメッセージ
     * @return メッセージコードつきのメッセージ
     */
    public static String getFormattedMessage(final String messageCode,
                                             final String simpleMessage) {
        return "[" + messageCode + "]" + simpleMessage;
    }

    /**
     * 引数を展開してメッセージコードなしの単純なメッセージを返します。
     *
     * @param messageCode メッセージコード
     * @param args        引数
     * @return メッセージコードなしの単純なメッセージ
     */
    public static String getSimpleMessage(final String messageCode,
                                          final Object... args) {
        try {
            final String pattern = getPattern(messageCode);
            if (pattern != null) {
                return MessageFormat.format(pattern, args);
            }
            return getNoPatternMessage(args);
        } catch (final Throwable ignore) {
            return getNoPatternMessage(args);
        }
    }

    /**
     * メッセージコードに対応するパターン文字列を返します。
     *
     * @param messageCode メッセージコード
     * @return パターン文字列
     */
    protected static String getPattern(final String messageCode) {
        if (Strings2.isEmpty(messageCode)) {
            return null;
        }
        final ResourceBundle resourceBundle =
                getResourceBundle(getSystemName(messageCode));
        if (resourceBundle == null) {
            return null;
        }

        final int length = messageCode.length();
        if (length > CODE_NUMBER_LENGTH) {
            final String key =
                    messageCode.charAt(0)
                            + messageCode.substring(length - CODE_NUMBER_LENGTH);
            final String pattern =
                    ResourceBundles.getString(resourceBundle, key);
            if (pattern != null) {
                return pattern;
            }
        }
        return resourceBundle.getString(messageCode);
    }

    /**
     * システム名を返します。
     *
     * @param messageCode メッセージコード
     * @return システム名
     */
    protected static String getSystemName(final String messageCode) {
        return messageCode.substring(
                1,
                Math.max(1, messageCode.length() - CODE_NUMBER_LENGTH));
    }

    /**
     * リソースバンドルを返します。
     *
     * @param systemName システム名
     * @return リソースバンドル
     */
    protected static ResourceBundle getResourceBundle(final String systemName) {
        if (!initialized) {
            initialize();
        }
        return ResourceBundles.getBundle(systemName + MESSAGES);
    }

    /**
     * パターンを使用しないで引数を並べたメッセージを返します。
     *
     * @param args 引数
     * @return 引数を並べたメッセージ
     */
    protected static String getNoPatternMessage(final Object... args) {
        if (args == null || args.length == 0) {
            return "";
        }
        final StringBuilder buffer = new StringBuilder();
        for (final Object arg : args) {
            buffer.append(arg + ", ");
        }
        buffer.setLength(buffer.length() - ", ".length());
        return new String(buffer);
    }

    /**
     * 初期化します。
     */
    protected static synchronized void initialize() {
        if (!initialized) {
            Disposables.add(new Disposable() {
                @Override
                public void dispose() {
                    ResourceBundle.clearCache();
                    initialized = false;
                }
            });
            initialized = true;
        }
    }
}
