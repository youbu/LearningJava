package com.yangwu.designpattern.factoryPattern;

public class FactoryPatternTest {

	public static void main(String[] args) {
		ShapeFactory factory = new ShapeFactory();

		Shape shape = factory.getShape("RECTANGLE");
		shape.draw();

		shape = factory.getShape("SQUARE");
		shape.draw();
		
		
		shape = factory.getShape(Rectangle.class);
		shape.draw();
		
		shape = factory.getShape(Square.class);
		shape.draw();
	}

}
