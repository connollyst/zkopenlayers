package org.zkoss.openlayers.handler;

import java.util.Map;

/**
 * 
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class RegularPolygon extends Handler {

	public RegularPolygon(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Handler.RegularPolygon";
	}

	@Override
	public String toJSONString() {
		return getNativeClass();
	}

}
