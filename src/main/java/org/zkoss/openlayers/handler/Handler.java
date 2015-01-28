package org.zkoss.openlayers.handler;

import static org.zkoss.openlayers.util.Helper.*;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.util.Function;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public abstract class Handler extends OLWidget {

	private Map _options;

	public Handler(Map options) {
		_options = options;
	}

	public void activate() {
		clientUpdate("activate");
	}

	public void deactivate() {
		clientUpdate("deactivate");
	}

	protected Map getOptions() {
		return _options;
	}

	protected Function newNativeObject() {
		return new Function(getNativeClass(), mergeMap(getOptions(),
				pair("uuid", getUuid())));
	}

}
