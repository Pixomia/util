package com.github.pixomia.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PixomiaIdUtils {
    static final String DELIMITER = "|";

    public static String genHumandReadableId() {
	final List<String> characters = Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
	Collections.shuffle(characters);
	final StringBuilder sb = new StringBuilder();
	for (int i = 0; i < 8; i++) {
	    sb.append(characters.get(i));
	}
	sb.append("-");
	sb.append(PixomiaTimeUtils.getCurrYear());
	sb.append("-");
	sb.append(PixomiaTimeUtils.getCurrMonthString());
	sb.append("-");
	sb.append(PixomiaTimeUtils.getCurrDayString());
	sb.append("-");
	sb.append(PixomiaTimeUtils.getCurrHourString());
	sb.append("-");
	sb.append(PixomiaTimeUtils.getCurrMinuteString());
	return sb.toString();
    }

    public static String genUuId() {
	return UUID.randomUUID().toString();
    }

    public String genNewIdFor(final String tennantId) {
	return UUID.nameUUIDFromBytes((tennantId + PixomiaIdUtils.DELIMITER + PixomiaIdUtils.genUuId()).getBytes())
		.toString();
    }

    public String genNewIdFor(final String tennantId, final String foreignKey) {
	return UUID.nameUUIDFromBytes((tennantId + PixomiaIdUtils.DELIMITER + foreignKey).getBytes()).toString();
    }
}
