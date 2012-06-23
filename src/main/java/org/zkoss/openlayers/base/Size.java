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

import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 * 
 */
public class Size extends OLBase {

	private double _width, _height;

	public Size(double width, double height) {
		_width = width;
		_height = height;
	}
	public double getWidth() {
		return _width;
	}
	public double getHeight() {
		return _height;
	}
	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _width, _height);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Size";
	}

}
