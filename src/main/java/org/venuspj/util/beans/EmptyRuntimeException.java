package org.venuspj.util.beans;

import java.io.Serial;
import org.venuspj.util.exception.VRuntimeException;

/**
 * 空の場合にスローされる例外です。
 */
public class EmptyRuntimeException extends VRuntimeException {

  @Serial
  private static final long serialVersionUID = 4625805280526951642L;

  private final String targetName;

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
   * ターゲット名を取得します。
   *
   * @return ターゲット名。
   */
  public String getTargetName() {
    return targetName;
  }
}
