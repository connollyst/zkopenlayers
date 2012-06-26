/* EditingToolbar.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 12:20:41 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.control;

import static org.zkoss.openlayers.util.Helper.mergeMap;
import static org.zkoss.openlayers.util.Helper.pair;

import java.util.Map;

import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 */
public class EditingToolbar extends Panel {
	private Layer _layer;

	public EditingToolbar(Layer layer) {
		this(layer, null);
	}

	public EditingToolbar(Layer layer, Map options) {
		super(options);
		_layer = layer;
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Control.EditingToolbar";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _layer != null ? _layer.toClientWidget() : null, mergeMap(getOptions(),
				pair("uuid", getUuid())));
	}
}
