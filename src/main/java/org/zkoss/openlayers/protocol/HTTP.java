package org.zkoss.openlayers.protocol;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.util.Function;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class HTTP extends OLWidget {
	private Map _options;

	public HTTP(Map options) {
		_options = options;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Protocol.HTTP";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _options);
	}

}
