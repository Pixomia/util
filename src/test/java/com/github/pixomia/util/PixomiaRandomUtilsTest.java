package com.github.pixomia.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PixomiaRandomUtilsTest {

    @Test
    void testGetRandomBetween() {
	for (int i = 0; i < 100; i++) {
	    final int randomBetween = PixomiaRandomUtils.getRandomBetween(0, 100);
	    Assertions.assertTrue(0 <= randomBetween && randomBetween <= 100);
	}
    }

    @Test
    void testGetRandomValueFromList() {
	for (int i = 0; i < 100; i++) {
	    final List<String> source = new ArrayList<>();
	    for (int j = 0; j < 100; j++) {
		source.add(PixomiaIdUtils.genUuId());
	    }
	    final String random = PixomiaRandomUtils.getRandomValueFromList(source);
	    Assertions.assertTrue(source.contains(random));
	}
    }
}
