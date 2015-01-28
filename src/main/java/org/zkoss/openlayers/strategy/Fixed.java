package org.zkoss.openlayers.strategy;

import java.util.Map;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class Fixed extends Strategy {
	public Fixed() {
		this(null);
	}

	public Fixed(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Strategy.Fixed";
	}

}
