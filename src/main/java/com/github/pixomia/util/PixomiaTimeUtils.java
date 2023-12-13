package com.github.pixomia.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PixomiaTimeUtils {

    public static OffsetDateTime calcEndOfDay(final OffsetDateTime currentTime) {
	final OffsetDateTime helper = currentTime;
	return PixomiaTimeUtils.setValueToEndOfDay(helper);

    }

    public static OffsetDateTime calcEndOfMonth(final OffsetDateTime currentTime) {

	return PixomiaTimeUtils.calcStartOfNextMonth(currentTime).minusNanos(1);
    }

    public static OffsetDateTime calcEndOfYear(final OffsetDateTime currentTime) {
	OffsetDateTime helper = currentTime;
	helper = helper.withMonth(12);
	helper = helper.withDayOfMonth(31);
	return PixomiaTimeUtils.setValueToEndOfDay(helper);
    }

    public static OffsetDateTime calcStartOfDay(final OffsetDateTime currentTime) {
	final OffsetDateTime helper = currentTime;
	return PixomiaTimeUtils.setValueToStartOfDay(helper);
    }

    public static OffsetDateTime calcStartOfNextDay(final OffsetDateTime currentTime) {

	final OffsetDateTime calcEndOfDay = PixomiaTimeUtils.calcEndOfDay(currentTime);
	OffsetDateTime helper = calcEndOfDay;
	helper = helper.withNano(0);
	return helper.plusSeconds(1);
    }

    public static OffsetDateTime calcStartOfNextMonth(final OffsetDateTime currentTime) {
	OffsetDateTime helper = currentTime;
	helper = helper.plusMonths(1);
	helper = helper.withDayOfMonth(1);

	return PixomiaTimeUtils.calcStartOfDay(helper);
    }

    public static OffsetDateTime calcStartOfNextYear(final OffsetDateTime currentTime) {

	return PixomiaTimeUtils.calcEndOfYear(currentTime).plusNanos(1);
    }

    public static OffsetDateTime getCurr() {
	return OffsetDateTime.now(ZoneId.of("UCT"));
    }

    public static int getCurrDay() {

	return PixomiaTimeUtils.getCurr().getDayOfMonth();
    }

    public static String getCurrDayString() {
	final int curr = PixomiaTimeUtils.getCurr().getDayOfMonth();
	if (curr <= 9) {
	    return "0" + curr;
	}
	return curr + "";
    }

    public static long getCurrentTime() {

	return PixomiaTimeUtils.getCurr().toEpochSecond();
    }

    public static long getCurrentTimePlusMin(final int minutes) {

	return PixomiaTimeUtils.getCurrentTime() + 1000 * 60 * minutes;
    }

    public static int getCurrHour() {

	return PixomiaTimeUtils.getCurr().getHour();
    }

    public static String getCurrHourString() {

	final int curr = PixomiaTimeUtils.getCurr().getHour();
	if (curr <= 9) {
	    return "0" + curr;
	}
	return curr + "";
    }

    public static int getCurrMinute() {

	return PixomiaTimeUtils.getCurr().getMinute();
    }

    public static String getCurrMinuteString() {

	final int curr = PixomiaTimeUtils.getCurr().getMinute();
	if (curr <= 9) {
	    return "0" + curr;
	}
	return curr + "";
    }

    public static int getCurrMonth() {

	return PixomiaTimeUtils.getCurr().getMonthValue();
    }

    public static String getCurrMonthString() {

	final int curr = PixomiaTimeUtils.getCurr().getMonthValue();
	if (curr <= 9) {
	    return "0" + curr;
	}
	return curr + "";
    }

    public static OffsetDateTime getCurrOffsetDateTime(final String offsetDateTimeString) {
	final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	return OffsetDateTime.parse(offsetDateTimeString, dateTimeFormatter);
    }

    public static String getCurrTimeString() {
	return PixomiaTimeUtils.getCurrTimeString(PixomiaTimeUtils.getCurr());
    }

    public static String getCurrTimeString(final OffsetDateTime timePoint) {
	return timePoint.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static int getCurrYear() {
	return PixomiaTimeUtils.getCurr().getYear();
    }

    private static OffsetDateTime setValueToEndOfDay(OffsetDateTime helper) {
	helper = helper.withHour(23);
	helper = helper.withMinute(59);
	helper = helper.withSecond(59);
	return helper.withNano(999999999);
    }

    private static OffsetDateTime setValueToStartOfDay(OffsetDateTime helper) {
	helper = helper.withHour(0);
	helper = helper.withMinute(0);
	helper = helper.withSecond(0);
	return helper.withNano(0);
    }
}
