package org.venuspj.util.entity.generator;

import java.net.UnknownHostException;
import java.util.Objects;
import org.venuspj.util.io.ipadress.IpAddressProvider;
import org.venuspj.util.primitives.application.ApplicationInfo;

public class NodeIdGenerator {

  private static long nodeId;

  static {
    try {
      String hostName = IpAddressProvider.ipAddress();
      String applicationName = ApplicationInfo.name();
      Long serverPort = ApplicationInfo.port();
      nodeId = Math.abs((long) Objects.hash(hostName, applicationName, serverPort));
    } catch (UnknownHostException e) {
      nodeId = (int) (Math.random() * (Math.pow(2, 10) - 1));
    }
    nodeId = nodeId & 1023;
  }

  public static long generateNodeId() {
    return nodeId;

  }
}
