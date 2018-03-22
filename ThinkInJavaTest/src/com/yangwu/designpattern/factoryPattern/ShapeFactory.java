package com.yangwu.designpattern.factoryPattern;

public class ShapeFactory {
	public Shape getShape(String type) {
		Shape shape = null;
		if (type == null || type.isEmpty()) {
			return null;
		}

		switch (type) {
		case "RECTANGLE":
			shape = new Rectangle();
			break;
		case "SQUARE":
			shape = new Square();
			break;
		default:
			break;
		}

		return shape;
	}
	
	public Shape getShape(Class<? extends Shape>clz) {
		Shape shape = null;
		
		try {
			shape = (Shape) Class.forName(clz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return shape;
	}
}
