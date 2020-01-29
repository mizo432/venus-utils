package org.venuspj.util.lang;

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

import static org.venuspj.util.collect.Sets2.newHashSet;

/**
 * パッケージ配下に存在するクラスの一覧を取得するユーティリティ
 */
public class ClassPath {
    public static <T> T uncheckCall(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<Class<?>> listClasses(String packageName) {

        final String resourceName = packageName.replace('.', '/');
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final URL root = classLoader.getResource(resourceName);

        if ("file".equals(root.getProtocol())) {
            File[] files = new File(root.getFile()).listFiles((dir, name) -> name.endsWith(".class"));
            return Arrays.asList(files).stream()
                    .map(file -> file.getName())
                    .map(name -> name.replaceAll(".class$", ""))
                    .map(name -> packageName + "." + name)
                    .map(fullName -> uncheckCall(() -> Class.forName(fullName)))
                    .collect(Collectors.toSet());
        }
        if ("jar".equals(root.getProtocol())) {
            try (JarFile jarFile = ((JarURLConnection) root.openConnection()).getJarFile()) {
                return Collections.list(jarFile.entries()).stream()
                        .map(jarEntry -> jarEntry.getName())
                        .filter(name -> name.startsWith(resourceName))
                        .filter(name -> name.endsWith(".class"))
                        .map(name -> name.replace('/', '.').replaceAll(".class$", ""))
                        .map(fullName -> uncheckCall(() -> classLoader.loadClass(fullName)))
                        .collect(Collectors.toSet());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return newHashSet();
    }
}
