package org.zkoss.openlayers.control;

import static org.zkoss.openlayers.util.Helper.*;

import java.util.Map;

import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.util.Function;

/**
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class ModifyFeature extends Control {
	private Layer _layer;

	public ModifyFeature(Layer layer) {
		this(layer, null);
	}

	public ModifyFeature(Layer layer, Map options) {
		super(options);
		_layer = layer;
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(),
				_layer != null ? _layer.toClientWidget() : null, mergeMap(
						getOptions(), pair("uuid", getUuid())));
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.ModifyFeature";
	}
}
