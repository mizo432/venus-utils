package org.venuspj.util.io.ipadress;

import java.util.concurrent.atomic.AtomicReference;

public class StaticIpAddressProvider extends IpAddressProvider {

  private static final AtomicReference<String> ipAddress = new AtomicReference<>();

  public StaticIpAddressProvider(String ipAddress) {
    super();
    StaticIpAddressProvider.ipAddress.set(ipAddress);
  }

  public static void initialize(String ipAddress) {
    StaticIpAddressProvider instance = new StaticIpAddressProvider(ipAddress);
    new IpAddressProvider(instance);
  }

  @Override
  protected String ipHostAddress() {
    return StaticIpAddressProvider.ipAddress.get();
  }

}
