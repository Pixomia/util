package com.github.pixomia.util;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import com.github.pixomia.util.exec.TecAwtException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MouseMover {
    public static void showVitalSign() throws TecAwtException {
	Robot robot;
	try {
	    robot = new Robot();
	    final Point location = MouseInfo.getPointerInfo().getLocation();
	    final int xPos = location.x;
	    final int yPos = location.y;
	    robot.mouseMove(xPos + 30, yPos + 30);
	    robot.mouseMove(xPos, yPos);
	} catch (final AWTException e) {
	    throw new TecAwtException();
	}

    }
}
