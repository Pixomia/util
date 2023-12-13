package com.github.pixomia.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PixomiaLifeCalcUtilsTest {

    @Test
    void testCalcDayDiff() {
	final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	{
	    final long days = PixomiaLifeCalcUtils.calcDayDiff(
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-14T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(1, days);
	}
	{
	    final long days = PixomiaLifeCalcUtils.calcDayDiff(
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-15T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(2, days);
	}
    }

    @Test
    void testCalcHourDiff() {
	final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	{
	    final long curr = PixomiaLifeCalcUtils.calcHourDiff(
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-13T16:54:32.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(1, curr);
	}
	{
	    final long curr = PixomiaLifeCalcUtils.calcHourDiff(
		    OffsetDateTime.parse("2023-12-13T10:44:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-13T20:44:32.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(10, curr);
	}
    }

    @Test
    void testCalcMinDiff() {
	final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	{
	    final long min = PixomiaLifeCalcUtils.calcMinDiff(
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-13T15:55:32.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(1, min);
	}
	{
	    final long min = PixomiaLifeCalcUtils.calcMinDiff(
		    OffsetDateTime.parse("2023-12-13T15:44:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(10, min);
	}
    }

    @Test
    void testCalcSecDiff() {
	final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	{
	    final long sec = PixomiaLifeCalcUtils.calcSecDiff(
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-13T15:54:33.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(1, sec);
	}
	{
	    final long sec = PixomiaLifeCalcUtils.calcSecDiff(
		    OffsetDateTime.parse("2023-12-13T15:54:32.369543988Z", dateTimeFormatter).toEpochSecond(),
		    OffsetDateTime.parse("2023-12-13T15:54:42.369543988Z", dateTimeFormatter).toEpochSecond());
	    Assertions.assertEquals(10, sec);
	}
    }
}
