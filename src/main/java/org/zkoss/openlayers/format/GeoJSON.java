package org.zkoss.openlayers.format;

import java.util.Map;

import org.zkoss.openlayers.util.Function;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class GeoJSON extends Format {

	private Map _options;

	public GeoJSON(Map options) {
		_options = options;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Format.GeoJSON";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _options);
	}

}
