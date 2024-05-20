package org.example.milleniumproject.model;

import javafx.scene.paint.Color;
import org.example.milleniumproject.presentation.RectangleConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RectangleConstructorTest {

	private RectangleConstructor rectangleConstructor;

	@BeforeEach
	public void setUp() {
		rectangleConstructor = new RectangleConstructor(100, 200, 0, 0);
	}

	@Test
	public void testRectangleConstructor() {
		assertNotNull(rectangleConstructor.getRectangle());
		assertEquals(100, rectangleConstructor.getRectangle().getWidth());
		assertEquals(200, rectangleConstructor.getRectangle().getHeight());
		assertEquals(50, rectangleConstructor.getRectangle().getArcWidth());
		assertEquals(50, rectangleConstructor.getRectangle().getArcHeight());
		assertEquals(Color.TRANSPARENT, rectangleConstructor.getRectangle().getFill());
		assertEquals(4, rectangleConstructor.getRectangle().getStrokeWidth());
		assertEquals(Color.TRANSPARENT, rectangleConstructor.getRectangle().getStroke());
		assertEquals(0, rectangleConstructor.getRectangle().getTranslateX());
		assertEquals(0, rectangleConstructor.getRectangle().getTranslateY());
	}

	@Test
	public void testSetStrokeColor() {
		rectangleConstructor.setStrokeColor(Color.RED);
		assertEquals(Color.RED, rectangleConstructor.getStrokeColor());
	}
}
