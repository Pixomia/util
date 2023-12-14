package com.github.pixomia.util;

import java.nio.file.Path;

import lombok.Data;

@Data
public class HashFile {
	public HashFile(String hash, Path currPath) {
		this.hash = hash;
		this.path = currPath;
	}

	private String hash;
	private Path path;
}
