package org.venuspj.util.lang;

import org.venuspj.util.objects2.Objects2;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

import static org.venuspj.util.collect.Sets2.newHashSet;

/**
 * パッケージ配下に存在するクラスの一覧を取得するユーティリティ
 */
public class ClassPath {
    private static final String PACKAGE_SEPARATOR = ".";

    private static final String CLASS_SUFFIX = ".class";

    private static final String STATIC_CLASS_SUFFIX = ".class$";

    private static <T> T uncheckCall(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * パッケージ名で指定したパッケージに存在するクラスを一覧化する
     *
     * @param packageName パッケージ名
     * @return クラスの一覧
     */
    public static Set<Class<?>> listClasses(String packageName) {

        final String resourceName = packageName.replace(PACKAGE_SEPARATOR, File.separator);
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final URL root = classLoader.getResource(resourceName);

        if(root == null) {
            return newHashSet();
        }



        if ("file".equals(root.getProtocol())) {
            File[] files = new File(root.getFile()).listFiles((dir, name) -> name.endsWith(CLASS_SUFFIX));
            assert files != null;
            return Arrays.stream(files)
                    .map(File::getName)
                    .map(name -> name.replaceAll(STATIC_CLASS_SUFFIX, ""))
                    .map(name -> packageName + PACKAGE_SEPARATOR + name)
                    .map(fullName -> uncheckCall(() -> Class.forName(fullName)))
                    .collect(Collectors.toSet());
        }
        if ("jar".equals(root.getProtocol())) {
            try (JarFile jarFile = ((JarURLConnection) root.openConnection()).getJarFile()) {
                return Collections.list(jarFile.entries()).stream()
                        .map(ZipEntry::getName)
                        .filter(name -> name.startsWith(resourceName))
                        .filter(name -> name.endsWith(CLASS_SUFFIX))
                        .map(name -> name.replace(File.separator, PACKAGE_SEPARATOR).replaceAll(STATIC_CLASS_SUFFIX, ""))
                        .map(fullName -> uncheckCall(() -> classLoader.loadClass(fullName)))
                        .collect(Collectors.toSet());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return newHashSet();
    }

    public static Set<Class<?>> listRecursiveClasses(Class<?> aClass) {

        Set<String> packageNameSet = Packages.getPackageNames(aClass);

        Set<Class<?>> result = newHashSet();
        for (String packageName : packageNameSet) {
            result.addAll(ClassPath.listClasses(packageName));
        }
        result.add(aClass);

        return result;

    }

    public static Set<Class<?>> listRecursiveClasses(String aPackageName) {

        Set<String> packageNameSet = Packages.getPackageNames(aPackageName);

        Set<Class<?>> result = newHashSet();
        for (String packageName : packageNameSet) {
            result.addAll(ClassPath.listClasses(packageName));
        }

        return result;

    }
}
