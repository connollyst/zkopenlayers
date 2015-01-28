package org.zkoss.openlayers.handler;

import java.util.Map;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class Box extends Handler {

	public Box(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Handler.Box";
	}

	@Override
	public String toJSONString() {
		return getNativeClass();
	}
}
