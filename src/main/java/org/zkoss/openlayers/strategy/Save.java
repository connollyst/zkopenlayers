package org.zkoss.openlayers.strategy;

import java.util.Map;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class Save extends Strategy {
	public Save() {
		this(null);
	}

	public Save(Map options) {
		super(options);
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Strategy.Save";
	}
}
