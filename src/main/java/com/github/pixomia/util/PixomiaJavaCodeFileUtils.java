
package com.github.pixomia.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.pixomia.util.exec.NonRecoverTechnicException;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class PixomiaJavaCodeFileUtils {
    private static boolean checkIsJavaFile(final Path currPath) {
	final var currFile = currPath.toFile();
	if (!currFile.isFile()) {
	    return false;
	}
	final var absolutePath = currFile.getAbsolutePath();
	final List<String> tagForFalse = new ArrayList<>();
	tagForFalse.add(".git");
	tagForFalse.add(File.separator + "target" + File.separator);
	tagForFalse.add(".settings");
	tagForFalse.add(".classpath");
	tagForFalse.add(".project");

	for (final String currTag : tagForFalse) {
	    if (absolutePath.contains(currTag)) {
		return false;
	    }
	}
	if (absolutePath.endsWith(".java")) {
	    return true;
	}
	return false;
    }

    private static boolean checkIsJavaPomFile(final Path currPath) {
	final var currFile = currPath.toFile();
	if (!currFile.isFile()) {
	    return false;
	}
	final var absolutePath = currFile.getAbsolutePath();
	final List<String> tagForFalse = new ArrayList<>();
	tagForFalse.add(".git");
	tagForFalse.add(File.separator + "target" + File.separator);
	tagForFalse.add(".settings");
	tagForFalse.add(".classpath");
	tagForFalse.add(".project");

	for (final String currTag : tagForFalse) {
	    if (absolutePath.contains(currTag)) {
		return false;
	    }
	}
	return absolutePath.endsWith("pom.xml");
    }

    public static List<File> readJavaFiles(final String sourceDirPath) throws NonRecoverTechnicException {

	final List<File> foundedFiles = new ArrayList<>();
	final var pp = Paths.get(sourceDirPath);
	try {
	    Files.walk(pp).filter(PixomiaJavaCodeFileUtils::checkIsJavaFile).forEach(path -> {
		foundedFiles.add(path.toFile());
	    });
	} catch (final IOException e) {
	    PixomiaJavaCodeFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return foundedFiles;
    }

    public static List<File> readJavaPomFiles(final String sourceDirPath) throws NonRecoverTechnicException {

	final List<File> foundedFiles = new ArrayList<>();
	final var pp = Paths.get(sourceDirPath);
	try {
	    Files.walk(pp).filter(PixomiaJavaCodeFileUtils::checkIsJavaPomFile).forEach(path -> {
		foundedFiles.add(path.toFile());
	    });
	} catch (final IOException e) {
	    PixomiaJavaCodeFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return foundedFiles;
    }
}
