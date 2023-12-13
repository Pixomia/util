package com.github.pixomia.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PixomiaTimeUtilsTest {
    private final OffsetDateTime now = OffsetDateTime.now(ZoneId.of("UCT"));

    private void checkEndOfDay(final OffsetDateTime objUnderTest) {
	Assertions.assertEquals(23, objUnderTest.getHour());
	Assertions.assertEquals(59, objUnderTest.getMinute());
	Assertions.assertEquals(59, objUnderTest.getSecond());
	Assertions.assertEquals(999999999, objUnderTest.getNano());
    }

    private void checkIsSameDay(final OffsetDateTime objUnderTest) {
	Assertions.assertEquals(now.getDayOfMonth(), objUnderTest.getDayOfMonth());
	Assertions.assertEquals(now.getMonth(), objUnderTest.getMonth());
	Assertions.assertEquals(now.getYear(), objUnderTest.getYear());
    }

    private void checkStartOfDay(final OffsetDateTime objUnderTest) {
	Assertions.assertEquals(0, objUnderTest.getHour());
	Assertions.assertEquals(0, objUnderTest.getMinute());
	Assertions.assertEquals(0, objUnderTest.getSecond());
	Assertions.assertEquals(0, objUnderTest.getNano());

    }

    @Test
    void testCalcEndOfDay() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcEndOfDay(now);
	checkEndOfDay(objUnderTest);
	checkIsSameDay(objUnderTest);
    }

    @Test
    void testCalcEndOfMonth() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcEndOfMonth(now);
	checkEndOfDay(objUnderTest);
	Assertions.assertEquals(now.getMonth(), objUnderTest.getMonth());

    }

    @Test
    void testCalcEndOfYear() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcEndOfYear(now);
	checkEndOfDay(objUnderTest);
	Assertions.assertEquals(now.getYear(), objUnderTest.getYear());
	Assertions.assertEquals(12, objUnderTest.getMonthValue());
	Assertions.assertEquals(31, objUnderTest.getDayOfMonth());
    }

    @Test
    void testCalcStartOfDay() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcStartOfDay(now);
	checkStartOfDay(objUnderTest);
	checkIsSameDay(objUnderTest);
    }

    @Test
    void testCalcStartOfNextDay() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcStartOfNextDay(now);
	checkStartOfDay(objUnderTest);
    }

    @Test
    void testCalcStartOfNextMonth() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcStartOfNextMonth(now);
	checkStartOfDay(objUnderTest);

    }

    @Test
    void testCalcStartOfNextYear() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils.calcStartOfNextYear(now);
	checkStartOfDay(objUnderTest);
    }

    @Test
    void testGetDay() {
	final int objUnderTest = PixomiaTimeUtils.getCurrDay();
	Assertions.assertEquals(now.getDayOfMonth(), objUnderTest);
    }

    @Test
    void testGetDayString() {
	final String objUnderTest = PixomiaTimeUtils.getCurrDayString();
	Assertions.assertEquals(2, objUnderTest.length());
    }

    @Test
    void testGetHour() {
	final int objUnderTest = PixomiaTimeUtils.getCurrHour();
	Assertions.assertEquals(now.getHour(), objUnderTest);
    }

    @Test
    void testGetHourString() {
	final String objUnderTest = PixomiaTimeUtils.getCurrHourString();
	Assertions.assertEquals(2, objUnderTest.length());
    }

    @Test
    void testGetMinute() {
	final int objUnderTest = PixomiaTimeUtils.getCurrMinute();
	Assertions.assertEquals(now.getMinute(), objUnderTest);
    }

    @Test
    void testGetMonth() {
	final int objUnderTest = PixomiaTimeUtils.getCurrMonth();
	Assertions.assertEquals(now.getMonthValue(), objUnderTest);
    }

    @Test
    void testGetMonthString() {
	final int objUnderTest = PixomiaTimeUtils.getCurrMonth();
	Assertions.assertEquals(now.getMonthValue(), objUnderTest);
    }

    @Test
    void testGetOffsetDateTime() {
	final OffsetDateTime objUnderTest = PixomiaTimeUtils
		.getCurrOffsetDateTime(PixomiaTimeUtils.getCurrTimeString(now));
	Assertions.assertEquals(now.toEpochSecond(), objUnderTest.toEpochSecond());
    }

    @Test
    void testGetTimeString() {
	final String objUnderTest = PixomiaTimeUtils.getCurrTimeString();
	Assertions.assertEquals(30, objUnderTest.length());
    }

    @Test
    void testGetTimeStringOffsetDateTime() {
	final String objUnderTest = PixomiaTimeUtils.getCurrTimeString(now);
	Assertions.assertEquals(30, objUnderTest.length());
    }

    @Test
    void testGetYear() {
	final int objUnderTest = PixomiaTimeUtils.getCurrYear();
	Assertions.assertEquals(now.getYear(), objUnderTest);
    }

}
