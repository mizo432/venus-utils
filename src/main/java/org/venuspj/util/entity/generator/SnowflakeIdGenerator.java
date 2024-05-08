package org.venuspj.util.entity.generator;

import org.venuspj.util.dateProvider.DateProvider;

public class SnowflakeIdGenerator {

  private static final Long EPOCH = 1609459200000L;
  private static final long MAX_WORKER_ID = 1023L;

  private final long workerId;
  private static long sequence = 0L;

  private static long lastTimestamp = -62167252739000L;

  public SnowflakeIdGenerator(Long workerId) {
    if (workerId < 0 || workerId > MAX_WORKER_ID) {
      throw new IllegalArgumentException(
          "worker ID must be between 0 and " + MAX_WORKER_ID + " but workerId is "
              + workerId);
    }
    this.workerId = workerId;
  }

  public synchronized long generateID() {
    long timestamp = DateProvider.currentTimeMillis();

    if (timestamp < lastTimestamp) {
      throw new RuntimeException(
          "Clock moved backwards. Refusing to generate ID. timestamp:" + timestamp
              + ", lastTimestamp:" + lastTimestamp);
    }
    if (timestamp == lastTimestamp) {
      sequence = (sequence + 1) & 4095L;
      if (sequence == 0L) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0L;
    }
    lastTimestamp = timestamp;
    return ((timestamp - EPOCH) << 22) | (workerId << 12) | sequence;

  }

  private long tilNextMillis(long lastTimestamp) {
    long timestamp = System.currentTimeMillis();
    while (timestamp <= lastTimestamp) {
      timestamp = System.currentTimeMillis();
    }
    return timestamp;
  }

  public void initialise() {
    lastTimestamp = -62167252739000L;

  }
}
