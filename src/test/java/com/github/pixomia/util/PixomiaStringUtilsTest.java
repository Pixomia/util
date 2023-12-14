package com.github.pixomia.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PixomiaStringUtilsTest {

    @Test
    void testBuildFestLenghtStringLeftIntInt() {
	Assertions.assertEquals("", PixomiaStringUtils.buildFestLenghtStringLeft(1, 0));
	Assertions.assertEquals("1", PixomiaStringUtils.buildFestLenghtStringLeft(1, 1));
	Assertions.assertEquals("0001", PixomiaStringUtils.buildFestLenghtStringLeft(1, 4));
	Assertions.assertEquals("00001", PixomiaStringUtils.buildFestLenghtStringLeft(1, 5));
    }

    @Test
    void testBuildFestLenghtStringLeftSpace() {
	Assertions.assertEquals("", PixomiaStringUtils.buildFestLenghtStringLeftSpace("1", 0));
	Assertions.assertEquals("1", PixomiaStringUtils.buildFestLenghtStringLeftSpace("1", 1));
	Assertions.assertEquals("   1", PixomiaStringUtils.buildFestLenghtStringLeftSpace("1", 4));
	Assertions.assertEquals("    1", PixomiaStringUtils.buildFestLenghtStringLeftSpace("1", 5));
    }

    @Test
    void testBuildFestLenghtStringRightSpace() {
	Assertions.assertEquals("", PixomiaStringUtils.buildFestLenghtStringRightSpace("1", 0));
	Assertions.assertEquals("1", PixomiaStringUtils.buildFestLenghtStringRightSpace("1", 1));
	Assertions.assertEquals("1   ", PixomiaStringUtils.buildFestLenghtStringRightSpace("1", 4));
	Assertions.assertEquals("1    ", PixomiaStringUtils.buildFestLenghtStringRightSpace("1", 5));
    }

    @Test
    void testCheckIfIsLong() {
	Assertions.assertEquals(true, PixomiaStringUtils.checkIfIsLong("1"));
	Assertions.assertEquals(false, PixomiaStringUtils.checkIfIsLong("XXX"));
	Assertions.assertEquals(true, PixomiaStringUtils.checkIfIsLong("-11111"));
    }

    @Test
    void testCleanUpString() {
	Assertions.assertEquals("aaabbbccc", PixomiaStringUtils.cleanUpString("aaa\r\nbbb\rccc\n"));
    }

    @Test
    void testCombineTwoMapsWithFrequencies() {
	{
	    final Map<String, Integer> first = new HashMap<>();
	    first.put("BINGO", 12);
	    final Map<String, Integer> second = new HashMap<>();
	    second.put("FOOBAR", 23);
	    Assertions.assertEquals(12, PixomiaStringUtils.combineTwoMapsWithFrequencies(first, second).get("BINGO"));
	    Assertions.assertEquals(23, PixomiaStringUtils.combineTwoMapsWithFrequencies(first, second).get("FOOBAR"));
	}
	{
	    final Map<String, Integer> first = new HashMap<>();
	    first.put("BINGO", 12);
	    final Map<String, Integer> second = new HashMap<>();
	    second.put("BINGO", 23);
	    Assertions.assertEquals(12 + 23,
		    PixomiaStringUtils.combineTwoMapsWithFrequencies(first, second).get("BINGO"));
	}
    }

    @Test
    void testConvertCamelToSnake() {
	Assertions.assertEquals("bingo_bongo", PixomiaStringUtils.convertCamelToSnake("BingoBongo"));
	Assertions.assertEquals("bingo", PixomiaStringUtils.convertCamelToSnake("Bingo"));
	Assertions.assertEquals("boo", PixomiaStringUtils.convertCamelToSnake("BOO"));
    }

    @Test
    void testConvertCamelToSnakeWithPrefix() {
	Assertions.assertEquals("xy_bingo_bongo",
		PixomiaStringUtils.convertCamelToSnakeWithPrefix("XYBingoBongo", "XY"));
	Assertions.assertEquals("xyz_bingo_bongo",
		PixomiaStringUtils.convertCamelToSnakeWithPrefix("XYZBingoBongo", "XYZ"));
	Assertions.assertEquals("foobingo_bongo",
		PixomiaStringUtils.convertCamelToSnakeWithPrefix("FOOBingoBongo", "XYZ"));
    }

    @Test
    void testConvertSnakeToCamel() {
	Assertions.assertEquals("bingoBongo", PixomiaStringUtils.convertSnakeToCamel("bingo_bongo"));
	Assertions.assertEquals("bingoBongo", PixomiaStringUtils.convertSnakeToCamel("bingo_bongo_"));
	Assertions.assertEquals("bingoBongo", PixomiaStringUtils.convertSnakeToCamel("_bingo_bongo"));
    }

    @Test
    void testConvertSnakeToTRUMP() {
	Assertions.assertEquals("BINGO_BONGO", PixomiaStringUtils.convertSnakeToTRUMP("bingo_bongo"));
    }

    @Test
    void testConvertToPosFileName() {
	Assertions.assertEquals("la_la_la_foobar", PixomiaStringUtils.convertToPosFileName("la la la foobar     "));
	Assertions.assertEquals("la_la_la__foobar", PixomiaStringUtils.convertToPosFileName("la la la  foobar     "));
    }

    @Test
    void testCutOffStringStringInt() {
	Assertions.assertEquals("bi", PixomiaStringUtils.cutOffString("bingo_bongo", 2));
	Assertions.assertEquals("b", PixomiaStringUtils.cutOffString("bingo_bongo", 1));
	Assertions.assertEquals("", PixomiaStringUtils.cutOffString("bingo_bongo", 0));
	Assertions.assertEquals("", PixomiaStringUtils.cutOffString("bingo_bongo", -1));
	Assertions.assertEquals("bingo_bongo", PixomiaStringUtils.cutOffString("bingo_bongo", 200));
    }

    @Test
    void testFindLetterFrequence() {
	final HashMap<String, Integer> letterFrequence = PixomiaStringUtils.findLetterFrequence("aaaaaabbbcccc");
	Assertions.assertEquals(6, letterFrequence.get("a"));
	Assertions.assertEquals(3, letterFrequence.get("b"));
	Assertions.assertEquals(4, letterFrequence.get("c"));
    }

    @Test
    void testFirstCharToLower() {
	Assertions.assertEquals("foo", PixomiaStringUtils.firstCharToLower("Foo"));
	Assertions.assertEquals("12", PixomiaStringUtils.firstCharToLower("12"));
    }

    @Test
    void testFirstCharToUpper() {
	Assertions.assertEquals("Foo", PixomiaStringUtils.firstCharToUpper("foo"));
	Assertions.assertEquals("12", PixomiaStringUtils.firstCharToUpper("12"));
    }

    @Test
    void testParseToLong() {
	Assertions.assertEquals(23, PixomiaStringUtils.parseToLong("23"));
	Assertions.assertThrows(NumberFormatException.class, () -> {
	    PixomiaStringUtils.parseToLong("XXX");
	});
    }

    @Test
    void testRemoveAllAfterLast() {
	Assertions.assertEquals("BINGO", PixomiaStringUtils.removeAllAfterLast("BINGOBINGO", "BINGO"));
	Assertions.assertEquals("", PixomiaStringUtils.removeAllAfterLast("BINGOFoobar", "BINGO"));
	Assertions.assertEquals("BINGOFoo", PixomiaStringUtils.removeAllAfterLast("BINGOFooXbar", "X"));
    }

    @Test
    void testRemoveDoublettes() {
	Assertions.assertEquals("BINGO", PixomiaStringUtils.removeDoublettes("BINGOBINGO", "BINGO"));
	Assertions.assertEquals("BINGO",
		PixomiaStringUtils.removeDoublettes("BINGOBINGOBINGOBINGOBINGOBINGO", "BINGO"));
	Assertions.assertEquals("BINGOXX",
		PixomiaStringUtils.removeDoublettes("BINGOBINGOBINGOBINGOBINGOBINGOXX", "BINGO"));
    }

}
