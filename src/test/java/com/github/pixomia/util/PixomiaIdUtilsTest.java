package com.github.pixomia.util;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PixomiaIdUtilsTest {

    @Test
    void testGenHumandReadableId() {
	Assertions.assertEquals(25, PixomiaIdUtils.genHumandReadableId().length());
    }

    @Test
    void testGenNewIdFor() {
	Assertions.assertEquals(36, PixomiaIdUtils.genNewIdFor("s").length());
    }

    @Test
    void testGenNewInternalId() {
	Assertions.assertEquals(36, PixomiaIdUtils.genNewIdFor("bingo", "bongo").length());
	Assertions.assertEquals(
		UUID.nameUUIDFromBytes(("bingo" + PixomiaIdUtils.DELIMITER + "bongo").getBytes()).toString(),
		PixomiaIdUtils.genNewIdFor("bingo", "bongo"));
    }

    @Test
    void testGenUuId() {
	Assertions.assertEquals(36, PixomiaIdUtils.genUuId().length());
    }

}
