package com.github.pixomia.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PixomiaFileUtilsTest {

    @Test
    void testCalcFileSuffix() {
	Assertions.assertEquals("bongo", PixomiaFileUtils.calcFileSuffix("Bingo.bongo"));
	Assertions.assertEquals("", PixomiaFileUtils.calcFileSuffix("Bingobongo"));
    }
}
