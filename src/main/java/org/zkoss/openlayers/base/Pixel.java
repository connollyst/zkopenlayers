/* Pixel.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 5:19:02 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.base;

import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Pixel extends OLBase {
	private double _x, _y;
	public Pixel(double x, double y) {
		_x = x;
		_y = y;
	}
	public double getX() {
		return _x;
	}
	public double getY() {
		return _y;
	}
	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _x, _y);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Pixel";
	}

}
