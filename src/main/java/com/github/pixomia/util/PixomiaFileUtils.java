
package com.github.pixomia.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.digest.DigestUtils;

import com.github.pixomia.util.exec.NonRecoverTechnicException;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class PixomiaFileUtils {
    static final String FILE_READ_ISSUE = "File Read Issue";

    public static void appendLinesToFile(final List<String> lines, final File targetFileName)
	    throws NonRecoverTechnicException {

	try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFileName, true));) {
	    for (final String currLine : lines) {
		writer.write(currLine);
		writer.newLine();
	    }
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}

    }

    public static void appendLinesToFile(final List<String> lines, final String targetFileName)
	    throws NonRecoverTechnicException {

	PixomiaFileUtils.appendLinesToFile(lines, new File(targetFileName));
    }

    public static String calcFileSuffix(final String fileName) {

	if (!fileName.contains(".")) {
	    return "";
	}
	return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static List<HashFile> calcHashes(final String sourceDirPath) throws NonRecoverTechnicException {

	final var pp = Paths.get(sourceDirPath);
	final List<Path> paths = new ArrayList<>();
	final List<HashFile> results = new ArrayList<>();
	try {
	    Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> paths.add(path));
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
	for (final Path currPath : paths) {
	    try {
		final var digest = DigestUtils.md5Hex(Files.readAllBytes(currPath));
		results.add(new HashFile(digest, currPath));
	    } catch (final IOException e) {
		PixomiaFileUtils.log.atDebug().log(e);
		throw new NonRecoverTechnicException("error while File IOP", e);
	    }
	}
	return results;
    }

    public static HashFile calcHashFromFile(final File sourceFile) throws NonRecoverTechnicException {

	return PixomiaFileUtils.calcHashFromFile(sourceFile.getAbsolutePath());
    }

    public static HashFile calcHashFromFile(final Path filePath) throws NonRecoverTechnicException {

	try {
	    final var digest = DigestUtils.md5Hex(Files.readAllBytes(filePath));
	    return new HashFile(digest, filePath);
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }

    public static HashFile calcHashFromFile(final String filePath) throws NonRecoverTechnicException {

	try {
	    final var path = Paths.get(filePath);
	    final var digest = DigestUtils.md5Hex(Files.readAllBytes(path));
	    return new HashFile(digest, path);
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }

    public static void changeInFile(final File sourceFile, final List<ChangeInFile> toChange)
	    throws NonRecoverTechnicException {

	PixomiaFileUtils.changeInFile(sourceFile, toChange, sourceFile);
    }

    public static void changeInFile(final File sourceFile, final List<ChangeInFile> toChange, final File targetFileName)
	    throws NonRecoverTechnicException {

	final var lines = PixomiaFileUtils.readFileToList(sourceFile);
	final List<String> result = new ArrayList<>();
	for (final String currLine : lines) {
	    for (final ChangeInFile changeInFile : toChange) {
		result.add(currLine.replaceAll(changeInFile.getSource(), changeInFile.getTarget()));
	    }
	}
	PixomiaFileUtils.writeLinesToFile(lines, targetFileName);
    }

    public static void changeInFile(final String sourceFile, final List<ChangeInFile> toChange)
	    throws NonRecoverTechnicException {

	PixomiaFileUtils.changeInFile(new File(sourceFile), toChange, new File(sourceFile));
    }

    public static ByteArrayOutputStream createAsZipFile(final String inputData, final String inputDataFileName)
	    throws NonRecoverTechnicException {

	final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	try (final ByteArrayInputStream bis = new ByteArrayInputStream(inputData.getBytes());
		final ZipOutputStream zipOut = new ZipOutputStream(bos);) {

	    final ZipEntry zipEntry = new ZipEntry(inputDataFileName);
	    zipOut.putNextEntry(zipEntry);
	    final var bytes = new byte[1024];
	    int length;
	    while ((length = bis.read(bytes)) >= 0) {
		zipOut.write(bytes, 0, length);
	    }
	    return bos;
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }

    public static void doZipDir(final String sourceDir, final String targetDir) throws IOException {

	final var helper = sourceDir.replace('/', '_');
	PixomiaFileUtils.pack(sourceDir,
		targetDir + File.separator + PixomiaTimeUtils.getCurrTimeString() + "_" + helper + ".zip");
    }

    public static void doZipFile(final String inputFile, final String outputPath) throws NonRecoverTechnicException {

	var helper = inputFile.replace('/', '_'); //
	helper = helper.replace(".", "_");
	final var outputFilename = PixomiaTimeUtils.getCurrTimeString() + "_" + helper + ".zip";

	final var sourceFile = inputFile;
	final var fileToZip = new File(sourceFile);
	try (final var fos = new FileOutputStream(outputPath + File.separator + outputFilename);
		final var fis = new FileInputStream(fileToZip);
		final var zipOut = new ZipOutputStream(fos);) {

	    final var zipEntry = new ZipEntry(fileToZip.getName());
	    zipOut.putNextEntry(zipEntry);
	    final var bytes = new byte[1024];
	    int length;
	    while ((length = fis.read(bytes)) >= 0) {
		zipOut.write(bytes, 0, length);
	    }
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }

    public static long getFileSizeInKb(final File currFile) {
	if (currFile == null || currFile.isDirectory() || !currFile.exists()) {
	    return 0;
	}
	return currFile.length() / 1024;
    }

    public static void mkdirs(final String dir) {
	new File(dir).mkdirs();
    }

    public static void moveFile(final File sourceFileName, final String targetDir)
	    throws IOException, NonRecoverTechnicException {
	final Path temp = Files.move(Paths.get(sourceFileName.getAbsolutePath()), Paths.get(targetDir));
	if (temp == null) {
	    throw new NonRecoverTechnicException("Failed to move the file");
	}
    }

    public static void moveFile(final String sourceFileName, final String targetFileName)
	    throws IOException, NonRecoverTechnicException {
	final String helper = targetFileName.substring(0, targetFileName.lastIndexOf("/"));
	new File(helper).mkdirs();
	final Path temp = Files.move(Paths.get(sourceFileName), Paths.get(targetFileName));

	if (temp == null) {
	    throw new NonRecoverTechnicException("Failed to move the file");
	}
    }

    public static void moveFileWithOverideProtection(final File sourceFileName, final String targetFile)
	    throws IOException, NonRecoverTechnicException {
	if (new File(targetFile).exists()) {
	    return;
	}
	PixomiaFileUtils.moveFile(sourceFileName, targetFile);
    }

    public static void pack(final String sourceDirPath, final String zipFilePath) throws IOException {

	final var p = Files.createFile(Paths.get(zipFilePath));
	try (var zs = new ZipOutputStream(Files.newOutputStream(p))) {
	    final var pp = Paths.get(sourceDirPath);
	    Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
		final var zipEntry = new ZipEntry(pp.relativize(path).toString());
		try {
		    zs.putNextEntry(zipEntry);
		    Files.copy(path, zs);
		    zs.closeEntry();
		} catch (final IOException e) {
		    PixomiaFileUtils.log.atDebug().log(e);
		}
	    });
	}
    }

    public static List<File> readAllBOTHRecu(final String sourceDirPath) throws NonRecoverTechnicException {

	final List<File> foundedFiles = new ArrayList<>();
	final var pp = Paths.get(sourceDirPath);
	try {
	    Files.walk(pp).forEach(path -> {
		foundedFiles.add(path.toFile());
	    });
	} catch (final IOException e) {
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return foundedFiles;
    }

    public static List<File> readAllDirsRecu(final String sourceDirPath) throws NonRecoverTechnicException {

	final List<File> foundedFiles = new ArrayList<>();
	final var pp = Paths.get(sourceDirPath);
	try {
	    Files.walk(pp).filter(Files::isDirectory).forEach(path -> {
		foundedFiles.add(path.toFile());
	    });
	} catch (final IOException e) {
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return foundedFiles;
    }

    public static List<File> readAllFilesRecu(final String sourceDirPath) throws NonRecoverTechnicException {

	final List<File> foundedFiles = new ArrayList<>();
	final var pp = Paths.get(sourceDirPath);
	try {
	    Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> foundedFiles.add(path.toFile()));
	} catch (final IOException e) {
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return foundedFiles;
    }

    private static byte[] readFileToByteArray(final File sourceFile) throws NonRecoverTechnicException {

	byte[] fileContent;
	try {
	    fileContent = Files.readAllBytes(sourceFile.toPath());
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return fileContent;
    }

    public static byte[] readFileToByteArray(final String filePath) throws NonRecoverTechnicException {
	return PixomiaFileUtils.readFileToByteArray(new File(filePath));
    }

    public static List<String> readFileToList(final File sourceFile) throws NonRecoverTechnicException {

	final List<String> lines = new ArrayList<>();
	try (var reader = new BufferedReader(new FileReader(sourceFile));) {
	    while (reader.ready()) {
		lines.add(reader.readLine());
	    }
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}
	return lines;
    }

    public static List<String> readFileToList(final String sourceFile) throws NonRecoverTechnicException {

	return PixomiaFileUtils.readFileToList(new File(sourceFile));
    }

    public static String readFileToString(final File sourceFile) throws NonRecoverTechnicException {

	return new String(PixomiaFileUtils.readFileToByteArray(sourceFile));
    }

    public static String readFileToString(final String filePath) throws NonRecoverTechnicException {

	return new String(PixomiaFileUtils.readFileToByteArray(filePath));
    }

    public static void saveAsZipFile(final byte[] inputObj, final String sourceFileName, final String targetPath)
	    throws NonRecoverTechnicException {

	try (final var fos = new FileOutputStream(targetPath); final var zipOut = new ZipOutputStream(fos);) {
	    final var zipEntry = new ZipEntry(sourceFileName);
	    zipOut.putNextEntry(zipEntry);
	    zipOut.write(inputObj);
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }

    public static void saveAsZipFile(final String inputPath, final String targetPath)
	    throws NonRecoverTechnicException {

	final var fileToZip = new File(inputPath);
	try (final var fos = new FileOutputStream(targetPath);
		final var zipOut = new ZipOutputStream(fos);
		final var fis = new FileInputStream(fileToZip);) {
	    final var zipEntry = new ZipEntry(fileToZip.getName());
	    zipOut.putNextEntry(zipEntry);
	    final var bytes = new byte[1024];
	    int length;
	    while ((length = fis.read(bytes)) >= 0) {
		zipOut.write(bytes, 0, length);
	    }
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }

    public static String splitFileNameGetName(final String fileName) {

	if (!fileName.contains(".")) {
	    return fileName;
	}
	return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static String splitFileNameGetSuffx(final String fileName) {

	if (!fileName.contains(".")) {
	    return "";
	}
	return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static void writeLinesToFile(final List<String> lines, final File targetFileName)
	    throws NonRecoverTechnicException {

	try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFileName));) {
	    for (final String currLine : lines) {
		writer.write(currLine);
		writer.newLine();
	    }
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException(PixomiaFileUtils.FILE_READ_ISSUE, e);
	}

    }

    public static void writeLinesToFile(final List<String> lines, final String targetFileName)
	    throws NonRecoverTechnicException {

	PixomiaFileUtils.writeLinesToFile(lines, new File(targetFileName));
    }

    public static void zipFile(final File fileToZip, final String fileName, final ZipOutputStream zipOut)
	    throws NonRecoverTechnicException {

	if (fileToZip.isHidden()) {
	    return;
	}
	if (fileToZip.isDirectory()) {
	    final var children = fileToZip.listFiles();
	    for (final File childFile : children) {
		PixomiaFileUtils.zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
	    }
	    return;
	}
	try (final var fis = new FileInputStream(fileToZip);) {

	    final var zipEntry = new ZipEntry(fileName);
	    zipOut.putNextEntry(zipEntry);
	    final var bytes = new byte[1024];
	    int length;
	    while ((length = fis.read(bytes)) >= 0) {
		zipOut.write(bytes, 0, length);
	    }
	} catch (final IOException e) {
	    PixomiaFileUtils.log.atDebug().log(e);
	    throw new NonRecoverTechnicException("error while File IOP", e);
	}
    }
}
