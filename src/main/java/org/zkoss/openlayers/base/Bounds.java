/* Bounds.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 1:01:25 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.base;

import java.util.Arrays;
import java.util.List;
import static org.zkoss.lang.Generics.cast;

import org.zkoss.openlayers.geometry.LinearRing;
import org.zkoss.openlayers.geometry.Point;
import org.zkoss.openlayers.geometry.Polygon;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 * 
 */
public class Bounds extends OLBase {

	private double _left, _bottom, _right, _top;

	public Bounds(double left, double bottom, double right, double top) {
		_left = left;
		_bottom = bottom;
		_right = right;
		_top = top;
	}

	public double getWidth() {
		return _right - _left;
	}

	public double getHeight() {
		return _top - _bottom;
	}

	public Bounds transform(Projection source, Projection dest) {
		getNativeObject().invoke("transform", source, dest);
		return this;
	}

	public Size getSize() {
		return new Size(getWidth(), getHeight());
	}

	public Polygon toGeometry() {
		List<Point> points = cast(Arrays.asList(
				new Point(_left, _bottom), new Point(_right, _bottom),
				new Point(_right, _top), new Point(_left, _top)));
		return new Polygon(Arrays.asList(new LinearRing(points)));
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Bounds";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _left, _bottom, _right, _top);
	}

	public static Bounds fromArray(List<Double> bbox) {
		return new Bounds(bbox.get(0), bbox.get(1), bbox.get(2), bbox.get(3));
	}

	public static Bounds fromSize(Size size) {
		return new Bounds(0, size.getHeight(), size.getWidth(), 0);
	}
}
