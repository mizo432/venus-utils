package org.venuspj.util.io.ipadress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

public class IpAddressProvider {

  private final static AtomicReference<IpAddressProvider> ipAddressProvider =
      new AtomicReference<>(new IpAddressProvider());

  IpAddressProvider() {

  }


  public static String ipAddress() throws UnknownHostException {
    return IpAddressProvider
        .ipAddressProvider
        .get()
        .ipHostAddress();
  }


  protected IpAddressProvider(IpAddressProvider ipAddressProvider) {
    IpAddressProvider.setIpAddressProvider(ipAddressProvider);
  }

  public static void setIpAddressProvider(IpAddressProvider ipAddressProvider) {
    IpAddressProvider.ipAddressProvider.set(ipAddressProvider);
  }

  /**
   * 現在日時をLocalDateTime型で取得します
   *
   * @return 現在日時
   */
  protected String ipHostAddress() throws UnknownHostException {
    return InetAddress.getLocalHost().getHostAddress();

  }

  /**
   * DateProviderを初期化する
   */
  public static void clear() {
    IpAddressProvider.ipAddressProvider.set(new IpAddressProvider());

  }

}
