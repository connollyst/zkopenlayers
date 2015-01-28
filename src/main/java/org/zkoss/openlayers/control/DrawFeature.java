package org.zkoss.openlayers.control;

import static org.zkoss.openlayers.util.Helper.*;

import java.util.Map;

import org.zkoss.openlayers.handler.Handler;
import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.util.Function;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class DrawFeature extends Panel {
	private Layer _layer;
	private Handler _handler;

	public DrawFeature(Layer layer, Handler handler) {
		this(layer, handler, null);
	}

	public DrawFeature(Layer layer, Handler handler, Map options) {
		super(options);
		_layer = layer;
		_handler = handler;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.DrawFeature";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(),
				_layer != null ? _layer.toClientWidget() : null, _handler,
				mergeMap(getOptions(), pair("uuid", getUuid())));
	}

}
