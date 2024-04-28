package org.venuspj.util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark elements that are meant to be visible and accessible only within
 * the framework.
 *
 * <p>
 * When this annotation is applied to a class, method, field, or any other element, it signifies
 * that the element is intended to be used by the framework or internal classes only. It is not
 * meant to be used or accessed directly by external user code.
 * </p>
 *
 * <p>
 * This annotation can be useful to prevent unnecessary exposure of internal implementation details
 * and to maintain encapsulation and modularity in a framework or library.
 * </p>
 *
 * <p>
 * Usage examples:
 * </p>
 *
 * <pre>
 * {@code
 *   // Apply VisibleForFramework annotation to a method to indicate it should only be called by the framework
 *   @VisibleForFramework
 *   public void initFrameworkState() {
 *       // Implementation details...
 *   }
 *
 *   // Apply VisibleForFramework annotation to a class to indicate it should only be accessed by internal classes
 *   @VisibleForFramework
 *   public class FrameworkInternalClass {
 *       // Implementation details...
 *   }
 * }
 * </pre>
 *
 * <p>
 * Note that using this annotation does not enforce visibility restrictions at compile-time. It is a
 * hint for developers to indicate the intended usage of specific elements.
 * </p>
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface VisibleForFramework {

}
