package org.venuspj.util.base;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.venuspj.util.base.Preconditions.checkNotNull;

public final class Throwables {
    static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
    private static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";
    private static final Object jla = getJLA();
    private static final Method getStackTraceElementMethod = (jla == null) ? null : getGetMethod();
    private static final Method getStackTraceDepthMethod = (jla == null) ? null : getSizeMethod();

    private Throwables() {
    }

    public static <X extends Throwable> void throwIfInstanceOf(
            Throwable throwable, Class<X> declaredType) throws X {
        checkNotNull(throwable);
        if (declaredType.isInstance(throwable)) {
            throw declaredType.cast(throwable);
        }
    }

    public static <X extends Throwable> void propagateIfInstanceOf(
             Throwable throwable, Class<X> declaredType) throws X {
        if (throwable != null) {
            throwIfInstanceOf(throwable, declaredType);
        }
    }

    public static void throwIfUnchecked(Throwable throwable) {
        checkNotNull(throwable);
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
    }

    public static void propagateIfPossible( Throwable throwable) {
        if (throwable != null) {
            throwIfUnchecked(throwable);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(
             Throwable throwable, Class<X> declaredType) throws X {
        propagateIfInstanceOf(throwable, declaredType);
        propagateIfPossible(throwable);
    }

    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(
             Throwable throwable, Class<X1> declaredType1, Class<X2> declaredType2)
            throws X1, X2 {
        checkNotNull(declaredType2);
        propagateIfInstanceOf(throwable, declaredType1);
        propagateIfPossible(throwable, declaredType2);
    }

    @Deprecated
    public static RuntimeException propagate(Throwable throwable) {
        throwIfUnchecked(throwable);
        throw new RuntimeException(throwable);
    }

    public static Throwable getRootCause(Throwable throwable) {
        Throwable slowPointer = throwable;
        boolean advanceSlowPointer = false;

        Throwable cause;
        while ((cause = throwable.getCause()) != null) {
            throwable = cause;

            if (throwable == slowPointer) {
                throw new IllegalArgumentException("Loop in causal chain detected.", throwable);
            }
            if (advanceSlowPointer) {
                slowPointer = slowPointer.getCause();
            }
            advanceSlowPointer = !advanceSlowPointer; // only advance every other iteration
        }
        return throwable;
    }

    public static List<Throwable> getCausalChain(Throwable throwable) {
        checkNotNull(throwable);
        List<Throwable> causes = new ArrayList<>(4);
        causes.add(throwable);

        Throwable slowPointer = throwable;
        boolean advanceSlowPointer = false;

        Throwable cause;
        while ((cause = throwable.getCause()) != null) {
            throwable = cause;
            causes.add(throwable);

            if (throwable == slowPointer) {
                throw new IllegalArgumentException("Loop in causal chain detected.", throwable);
            }
            if (advanceSlowPointer) {
                slowPointer = slowPointer.getCause();
            }
            advanceSlowPointer = !advanceSlowPointer; // only advance every other iteration
        }
        return Collections.unmodifiableList(causes);
    }

    public static <X extends Throwable> X getCauseAs(
            Throwable throwable, Class<X> expectedCauseType) {
        try {
            return expectedCauseType.cast(throwable.getCause());
        } catch (ClassCastException e) {
            e.initCause(throwable);
            throw e;
        }
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static List<StackTraceElement> lazyStackTrace(Throwable throwable) {
        return lazyStackTraceIsLazy()
                ? jlaStackTrace(throwable)
                : unmodifiableList(asList(throwable.getStackTrace()));
    }

    public static boolean lazyStackTraceIsLazy() {
        return getStackTraceElementMethod != null && getStackTraceDepthMethod != null;
    }

    private static List<StackTraceElement> jlaStackTrace(final Throwable t) {
        checkNotNull(t);
        return new AbstractList<StackTraceElement>() {
            @Override
            public StackTraceElement get(int n) {
                return (StackTraceElement)
                        invokeAccessibleNonThrowingMethod(getStackTraceElementMethod, jla, t, n);
            }

            @Override
            public int size() {
                return (Integer) invokeAccessibleNonThrowingMethod(getStackTraceDepthMethod, jla, t);
            }
        };
    }

    private static Object invokeAccessibleNonThrowingMethod(
            Method method, Object receiver, Object... params) {
        try {
            return method.invoke(receiver, params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw propagate(e.getCause());
        }
    }

    private static Object getJLA() {
        try {
            Class<?> sharedSecrets = Class.forName(SHARED_SECRETS_CLASSNAME, false, null);
            Method langAccess = sharedSecrets.getMethod("getJavaLangAccess");
            return langAccess.invoke(null);
        } catch (ThreadDeath death) {
            throw death;
        } catch (Throwable t) {
            return null;
        }
    }

    private static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, int.class);
    }

    private static Method getSizeMethod() {
        try {
            Method getStackTraceDepth = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (getStackTraceDepth == null) {
                return null;
            }
            getStackTraceDepth.invoke(getJLA(), new Throwable());
            return getStackTraceDepth;
        } catch (UnsupportedOperationException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    private static Method getJlaMethod(String name, Class<?>... parameterTypes) throws ThreadDeath {
        try {
            return Class.forName(JAVA_LANG_ACCESS_CLASSNAME, false, null).getMethod(name, parameterTypes);
        } catch (ThreadDeath death) {
            throw death;
        } catch (Throwable t) {
            /*
             * Either the JavaLangAccess class itself is not found, or the method is not supported on the
             * JVM.
             */
            return null;
        }
    }
}
