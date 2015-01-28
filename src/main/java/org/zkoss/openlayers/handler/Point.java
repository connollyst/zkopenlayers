package org.zkoss.openlayers.handler;

import java.util.Map;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class Point extends Handler {

	public Point(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Handler.Point";
	}

	@Override
	public String toJSONString() {
		return getNativeClass();
	}

}
