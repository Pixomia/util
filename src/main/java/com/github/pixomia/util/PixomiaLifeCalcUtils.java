package com.github.pixomia.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PixomiaLifeCalcUtils {

    public static long calcDayDiff(final long start, final long end) {

	return PixomiaLifeCalcUtils.calcHourDiff(start, end) / 24;
    }

    public static long calcHourDiff(final long start, final long end) {

	return PixomiaLifeCalcUtils.calcMinDiff(start, end) / 60;
    }

    public static long calcMinDiff(final long start, final long end) {

	return PixomiaLifeCalcUtils.calcSecDiff(start, end) / 60;
    }

    public static long calcSecDiff(final long startEpochSec, final long endEpochSec) {
	return endEpochSec - startEpochSec;
    }

}
