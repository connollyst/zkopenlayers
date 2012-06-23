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

import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class LonLat extends OLBase {

	private double _lon = 0.0;
	private double _lat = 0.0;
	private StringBuilder _buffer;
	
	public LonLat(double lon, double lat) {
		_lon = lon;
		_lat = lat;
	}
	
	public double getLon() {
		return _lon;
	}
	public double getLat() {
		return _lat;
	}
	
	public LonLat add(double lon, double lat) {
		return new LonLat(this._lon + lon, this._lat + lat); 
	}

	public LonLat transform(Projection source, Projection dest) {
		getNativeObject().invoke("transform", source, dest);
		return this;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.LonLat";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), this._lon,
				this._lat);
	}

}
