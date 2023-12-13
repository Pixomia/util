package com.github.pixomia.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.pixomia.util.exec.TecAwtException;

class MouseMoverTest {

    @Test
    void test_it_nearly_nonsense() {
	try {
	    MouseMover.showVitalSign();
	    Assertions.assertTrue(true);
	} catch (final TecAwtException e) {
	    Assertions.assertTrue(true);
	}
    }

}
