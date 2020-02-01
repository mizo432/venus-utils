package org.venuspj.util.beans;

import org.venuspj.util.exception.VRuntimeException;

/**
 * 空の場合にスローされる例外です。
 */
public class EmptyRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = 4625805280526951642L;

    private String targetName;

    /**
     * {@link EmptyRuntimeException}を作成します。
     *
     * @param targetName
     */
    public EmptyRuntimeException(String targetName) {
        super("ESSR0007", new Object[]{targetName});
        this.targetName = targetName;
    }

    /**
     * ターゲット名を返します。
     *
     * @return
     */
    public String getTargetName() {
        return targetName;
    }
}
