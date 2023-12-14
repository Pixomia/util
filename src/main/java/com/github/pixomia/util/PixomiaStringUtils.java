package com.github.pixomia.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PixomiaStringUtils {

    private static final String SPACE = "_";

    public static String buildFestLenghtStringLeft(final int toFillUp, final int len) {

	return PixomiaStringUtils.buildFestLenghtStringLeft(toFillUp + "", len);
    }

    public static String buildFestLenghtStringLeft(final String toFillUp, final int len) {
	if (len <= 0) {
	    return "";
	}
	if (toFillUp.length() >= len) {
	    return toFillUp;
	}
	final StringBuilder sb = new StringBuilder();
	sb.append(toFillUp);
	while (sb.toString().length() < len) {
	    sb.insert(0, "0");
	}
	return sb.toString();
    }

    public static String buildFestLenghtStringLeftSpace(final String toFillUp, final int len) {
	if (len <= 0) {
	    return "";
	}
	if (toFillUp.length() >= len) {
	    return toFillUp;
	}
	final StringBuilder sb = new StringBuilder();
	sb.append(toFillUp);
	while (sb.toString().length() < len) {
	    sb.insert(0, " ");
	}
	return sb.toString();
    }

    public static String buildFestLenghtStringRightSpace(final String toFillUp, final int len) {
	if (len <= 0) {
	    return "";
	}
	if (toFillUp.length() >= len) {
	    return toFillUp;
	}
	final StringBuilder sb = new StringBuilder();
	sb.append(toFillUp);
	while (sb.toString().length() < len) {
	    sb.append(" ");
	}
	return sb.toString();
    }

    public static boolean checkIfIsLong(final String isLong) {

	try {
	    Long.parseLong(isLong.trim());
	    return true;
	} catch (final NumberFormatException e) {
	    return false;
	}

    }

    public static String cleanUpString(final String toClean) {

	String helper = toClean.replace("\r\n", "");
	helper = helper.replace("\n", "");
	return helper.replace("\r", "");
    }

    /*
     * combine two maps Key is an String and the value is an integer
     */
    public static Map<String, Integer> combineTwoMapsWithFrequencies(final Map<String, Integer> first,
	    final Map<String, Integer> second) {
	final Map<String, Integer> result = new HashMap<>();
	for (final Entry<String, Integer> currFirstEntrySet : first.entrySet()) {
	    final String currFirstKey = currFirstEntrySet.getKey();
	    final Integer currFirstValue = currFirstEntrySet.getValue();
	    if (result.get(currFirstKey) == null) {
		result.put(currFirstKey, currFirstValue);
	    } else {
		result.put(currFirstKey, result.get(currFirstKey) + currFirstValue);
	    }
	}
	for (final Entry<String, Integer> currSecondEntrySet : second.entrySet()) {
	    final String currSecoundKey = currSecondEntrySet.getKey();
	    final Integer currSecoundValue = currSecondEntrySet.getValue();
	    if (result.get(currSecoundKey) == null) {
		result.put(currSecoundKey, currSecoundValue);
	    } else {
		result.put(currSecoundKey, result.get(currSecoundKey) + currSecoundValue);
	    }
	}
	return result;
    }

    public static String convertCamelToSnake(final String srcCamel) {

	final String helper = srcCamel.replaceAll("(\\p{Ll})(\\p{Lu})", "$1_$2");
	return helper.toLowerCase();
    }

    public static String convertCamelToSnakeWithPrefix(final String srcCamel, final String prefix) {

	String helper = "";
	boolean prefixFound = false;
	if (srcCamel.startsWith(prefix)) {
	    helper = prefix.toLowerCase();
	    prefixFound = true;
	}
	if (prefixFound) {
	    return helper + PixomiaStringUtils.SPACE
		    + srcCamel.substring(prefix.length()).replaceAll("(\\p{Ll})(\\p{Lu})", "$1_$2").toLowerCase();
	}
	return PixomiaStringUtils.convertCamelToSnake(srcCamel);
    }

    public static String convertSnakeToCamel(final String srcSnake) {

	String helper = srcSnake;
	if (helper.startsWith("_")) {
	    helper = helper.substring(1);
	}
	if (helper.endsWith("_")) {
	    helper = helper.substring(0, helper.length() - 1);
	}
	while (helper.contains(PixomiaStringUtils.SPACE)) {
	    helper = helper.replaceFirst("_[a-z]",
		    String.valueOf(Character.toUpperCase(helper.charAt(helper.indexOf(PixomiaStringUtils.SPACE) + 1))));
	}
	return helper;

    }

    public static String convertSnakeToTRUMP(final String srcSnake) {

	return srcSnake.toUpperCase();
    }

    public static String convertToPosFileName(final String text) {
	String help = text.trim();
	help = help.replace(' ', '_');
	if (help.endsWith(PixomiaStringUtils.SPACE)) {
	    help = help.substring(0, help.length() - 1);
	}
	return help;
    }

    public static String cutOffString(final String toCut, final int max) {
	if (max < 0) {
	    return "";
	}
	if (toCut.length() <= max) {
	    return toCut;
	}
	return toCut.substring(0, max);
    }

    public static Map<String, Integer> findLetterFrequence(final String text) {
	final int one = 1;
	final Map<String, Integer> result = new HashMap<>();
	final Stream<String> stringStream = text.codePoints().mapToObj(c -> String.valueOf((char) c));
	stringStream.forEachOrdered(curr -> {
	    if (result.containsKey(curr)) {
		result.put(curr, result.get(curr) + one);
	    } else {
		result.put(curr, one);
	    }
	});
	return result;
    }

    public static String firstCharToLower(final String source) {

	if (source == null) {
	    return "";
	}
	if (source.length() <= 1) {
	    return source.toLowerCase();
	}
	return source.substring(0, 1).toLowerCase() + source.substring(1);
    }

    public static String firstCharToUpper(final String source) {

	if (source == null) {
	    return "";
	}
	if (source.length() <= 1) {
	    return source.toUpperCase();
	}
	return source.substring(0, 1).toUpperCase() + source.substring(1);
    }

    public static long parseToLong(final String isLong) {

	return Long.parseLong(isLong.trim());
    }

    public static String removeAllAfterLast(final String toClean, final String tag) {
	if (!toClean.contains(tag)) {
	    return toClean;
	}
	return toClean.substring(0, toClean.lastIndexOf(tag));
    }

    public static String removeDoublettes(final String toClean, final String cleanFrom) {

	String helper = toClean;
	while (helper.contains(cleanFrom + cleanFrom + "")) {
	    helper = helper.replace(cleanFrom + cleanFrom + "", cleanFrom);
	}
	return helper;
    }
}
