/* LonLat.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 3:20:10 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.base;

import org.zkoss.openlayers.OLWidget;

/**
 * @author jumperchen
 *
 */
public class LonLat extends OLWidget {

	private double _lon = 0.0;
	private double _lat = 0.0;
	private StringBuilder _buffer;
	
	public LonLat(double lon, double lat) {
		_lon = lon;
		_lat = lat;
	}
	
	private void setBuffer(StringBuilder buffer) {
		_buffer = buffer;
	}
	
	public LonLat add(double lon, double lat) {
		return new LonLat(this._lon + lon, this._lat + lat); 
	}

	public LonLat transform(Projection source, Projection dest) {
		if (_buffer == null)
			_buffer = new StringBuilder(64);
		_buffer.append(".transform(").append(source  != null ? source.toJSONString() : "null")
		.append(',').append(dest != null ? dest.toJSONString() : "null").append(')');
		return this;
	}
	@Override
	public String toJSONString() {
		return toJSONFun("new " + getNativeClass() + "(" + this._lon + ", " +
				this._lat + ")" + (_buffer != null ? _buffer.toString() : ""));
	}

	@Override
	protected String getNativeClass() {
		// TODO Auto-generated method stub
		return "OpenLayers.LonLat";
	}

}
