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

/**
 * @author jumperchen
 * 
 */
public class Bounds extends OLBase {

	private double _left, _bottom, _right, _top;
	private StringBuilder _buffer;

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
		if (_buffer == null)
			_buffer = new StringBuilder(64);
		_buffer.append(".transform(").append(source  != null ? source.toJSONString() : "null")
		.append(',').append(dest != null ? dest.toJSONString() : "null").append(')');
		return this;
	}
	
	public Size getSize() {
		return new Size(getWidth(), getHeight());
	}
	
	@Override
	public String toJSONString() {
		return toJSONFun(getNativeClass(), _left, _bottom, _right, _top) + (_buffer != null ? _buffer.toString() : "");
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Bounds";
	}
	
}
