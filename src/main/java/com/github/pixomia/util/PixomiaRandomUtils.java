package com.github.pixomia.util;

import java.util.List;
import java.util.Random;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PixomiaRandomUtils {
    public static <T> T getRandomValueFromList(final List<T> source) {
	return source.get(PixomiaRandomUtils.getRandomBetween(0, source.size()));
    }

    public static int getRandomBetween(final int min, final int max) {
	final Random random = new Random();
	return random.ints(min, max).findFirst().getAsInt();
    }

}
