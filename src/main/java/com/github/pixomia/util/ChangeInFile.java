package com.github.pixomia.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor 
public class ChangeInFile {
	private String source;
	private String target;
}
