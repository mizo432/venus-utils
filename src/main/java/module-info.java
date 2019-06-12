module venus.utils {
    exports org.venuspj.util.logger;
    exports org.venuspj.util.base;
    exports org.venuspj.util.beans;
    exports org.venuspj.util.builder;
    exports org.venuspj.util.collect;
    exports org.venuspj.util.convert;
    exports org.venuspj.util.dateProvider;
    exports org.venuspj.util.lang;
    exports org.venuspj.util.message;
    exports org.venuspj.util.objects2;
    exports org.venuspj.util.primitives;
    exports org.venuspj.util.strings2;
    exports org.venuspj.util.uuidProvider;
    requires java.sql;
    requires java.desktop;
    requires slf4j.api;

}