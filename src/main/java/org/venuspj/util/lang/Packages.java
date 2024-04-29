package org.venuspj.util.lang;

import static org.venuspj.util.collect.Sets2.newHashSet;
import static org.venuspj.util.strings2.Strings2.replace;
import static org.venuspj.util.strings2.Strings2.trimTextAtStart;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.venuspj.util.base.Preconditions;

public class Packages {

  public static Set<String> getPackageNames(Class<?> clazz) {
    Preconditions.checkNotNull(clazz, () -> new NullPointerException("clazz is null"));
    Package rootPackage = clazz.getPackage();
    return getPackageNames(rootPackage.getName());

  }

  public static Set<String> getPackageNames(String aPackageName) {
    String rootPackage = aPackageName;
    String rootPackagePath = replace(rootPackage, ".", File.separator);

    String filePath =
        new File(".").getAbsolutePath() + File.separator + "src/test/java/" + rootPackagePath;
    File[] files = new File(filePath).listFiles();
    Set<String> packageNames = Arrays.stream(files)
        .filter(File::isDirectory)
        .map(File::getPath)
        .map(s -> trimTextAtStart(s, filePath + File.separator))
        .map(s -> rootPackage + "." + s)
        .collect(Collectors.toSet());
    if (packageNames.isEmpty()) {
      return packageNames;
    }

    Set<String> result = newHashSet();
    result.addAll(packageNames);
    for (String packageName : packageNames) {
      result.addAll(getPackageNames(packageName));
    }

    return result;

  }

}
