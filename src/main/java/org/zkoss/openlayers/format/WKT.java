package org.zkoss.openlayers.format;

import java.util.Map;

import org.zkoss.openlayers.util.Function;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class WKT extends Format {
	private Map _options;

	public WKT(Map options) {
		_options = options;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Format.WKT";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _options);
	}

}
