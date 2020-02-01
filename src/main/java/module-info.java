module venus.utils {
    exports org.venuspj.util.annotations;
    exports org.venuspj.util.base;
    exports org.venuspj.util.beans;
    exports org.venuspj.util.beans.factory;
    exports org.venuspj.util.builder;
    exports org.venuspj.util.collect;
    exports org.venuspj.util.convert;
    exports org.venuspj.util.dateProvider;
    exports org.venuspj.util.exception;
    exports org.venuspj.util.lang;
    exports org.venuspj.util.message;
    exports org.venuspj.util.objects2;
    exports org.venuspj.util.primitives;
    exports org.venuspj.util.reflection;
    exports org.venuspj.util.strings2;
    exports org.venuspj.util.uuidProvider;
    exports org.venuspj.util.validate;
    requires java.sql;
    requires java.desktop;
//    requires kotlin.stdlib;
    opens org.venuspj.util.beans;
    opens org.venuspj.util.beans.impl;
    opens org.venuspj.util.beans.factory;
    opens org.venuspj.util.reflection;
    opens org.venuspj.util.lang;


}