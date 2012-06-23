/* Point.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 8:32:51 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.geometry;

import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Point extends Geometry {

	private double _x, _y;
	public Point(double x, double y) {
		_x = x;
		_y = y;
	}
	public double getX() {
		return _x;
	}
	public double getY() {
		return _y;
	}

	public Point transform(Projection source, Projection dest) {
		getNativeObject().invoke("transform", source, dest);
		return this;
	}
	
	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _x, _y);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Geometry.Point";
	}

}
