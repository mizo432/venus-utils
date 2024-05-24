module venus.utils.main {
  requires org.jetbrains.annotations;
  requires java.logging;
  requires java.desktop;
  requires java.sql;
  requires org.slf4j;
  exports org.venuspj.util.base;
  exports org.venuspj.util.beans;
  exports org.venuspj.util.convert;
  exports org.venuspj.util.collect;
  exports org.venuspj.util.builder;
  exports org.venuspj.util.exception;
  exports org.venuspj.util.dateProvider;
  exports org.venuspj.util.primitives;
  exports org.venuspj.util.primitives.application;
  exports org.venuspj.util.io;
  exports org.venuspj.util.lang;
  exports org.venuspj.util.misc;
  exports org.venuspj.util.math;
  exports org.venuspj.util.objects2;
  exports org.venuspj.util.uuidProvider;
  exports org.venuspj.util.text;
  exports org.venuspj.util.beans.factory;
  exports org.venuspj.util.ipadress;
  exports org.venuspj.util.taple;
  exports org.venuspj.util.snowflake;
  exports org.venuspj.util.precondition;
}