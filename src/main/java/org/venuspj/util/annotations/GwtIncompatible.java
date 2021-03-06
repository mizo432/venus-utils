package org.venuspj.util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The presence of this annotation on an API indicates that the method may <em>not</em> be used with
 * the <a href="http://www.gwtproject.org/">Google Web Toolkit</a> (GWT).
 *
 * <p>This annotation behaves identically to <a href=
 * "http://www.gwtproject.org/javadoc/latest/com/google/gwt/core/shared/GwtIncompatible.html">the
 * {@code @GwtIncompatible} annotation in GWT itself</a>.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Documented
@GwtCompatible
public @interface GwtIncompatible {
    /**
     * Describes why the annotated element is incompatible with GWT. Since this is generally due to a
     * dependence on a type/method which GWT doesn't support, it is sufficient to simply reference the
     * unsupported type/method. E.g. "Class.isInstance".
     */
    String value() default "";
}
