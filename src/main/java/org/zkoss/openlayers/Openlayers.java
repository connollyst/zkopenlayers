/* Openlayers.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 20, 2012 9:58:32 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.control.Control;
import org.zkoss.openlayers.layer.Layer;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.WrongValueException;

/**
 * @author jumperchen
 *
 */
public class Openlayers extends HtmlBasedComponent {
	private Map<String, Layer> _layers;
	private Map<String, Control> _controls;
	private Map _options;
	private Object[] _center;
	private Layer _baseLayer;
	public Openlayers() {
		init();
	}
	private void init() {
		_layers = new LinkedHashMap<String, Layer>();
		_controls = new LinkedHashMap<String, Control>();
	}
	public void setOptions(Map options) {
		if (_options != options) {
			_options = options;
			smartUpdate("options", options);
		}
	}
	public Layer getLayer(String uuid) {
		return null;
	}
	public void addLayer(Layer layer) {
		if (layer == null)
			throw new NullPointerException("Layer cannot be null!");
		
		if (_layers.isEmpty()) {
			_baseLayer = layer;
			invalidate();
		}
		if (!_layers.containsKey(layer.getUuid())) {
			_layers.put(layer.getUuid(), layer);
			((OLWidget)layer).setMap(this);
			smartUpdate("layer", layer);
		}
	}
	public void setBaseLayer(Layer layer) {
		if (layer == null)
			throw new NullPointerException("Layer cannot be null!");
		if (!_layers.containsKey(layer.getUuid()))
			throw new WrongValueException("The layer: ["+layer.getUuid()+"] is not in the current list, please use addLayer() instead!");
		if (_baseLayer != layer) {
			_baseLayer = layer;
			smartUpdate("baseLayer", _baseLayer);
		}
	}
	public Layer getBaseLayer() {
		return _baseLayer;
	}
	public void removeLayer(Layer layer) {
		if (_layers.remove(layer.getUuid()) != null) {
			((OLWidget)layer).setMap(null);
			smartUpdate("removeLayer", layer.getUuid());
		}
	}
	public void removeControl(Control control) {
		if (_controls.remove(control.getUuid()) != null) {
			((OLWidget)control).setMap(null);
			smartUpdate("removeControl", control.getUuid());
		}
	}
	/**package*/ void removeOLWidget(OLWidget widget) {
		if (widget instanceof Layer) {
			if (_layers.remove(widget.getUuid()) != null) {
				smartUpdate("removeLayer", widget.getUuid());
			}
		} else if (widget instanceof Control) {
			if (_controls.remove(widget.getUuid()) != null) {
				smartUpdate("removeControl", widget.getUuid());
			}
		}
	}
	public Control getControl(String uuid) {
		return null;
	}
	public void addControl(Control control) {
		if (!_controls.containsKey(control.getUuid())) {
			_controls.put(control.getUuid(), control);
			((OLWidget)control).setMap(this);
			smartUpdate("control", control);
		}
	}
	public void setCenter(LonLat lonlat, int zoom, boolean dragging, boolean forceZoomChage) {
		Object[] center = new Object[]{lonlat, zoom, dragging, forceZoomChage};
		if (!Arrays.equals(_center, center)) {
			_center = center;
			smartUpdate("center", _center);
		}
	}
	public LonLat getCenter() {
		return _center != null ? (LonLat)_center[0] : null;
	}
	public Projection getProjection() {
		if (this._baseLayer != null)
			return this._baseLayer.getProjection();
		return null;
	}
	public void clientUpdate(OLWidget widget, String attr, Object value) {
		smartUpdate("clientUpdate", new Object[] {widget.getUuid(), attr, value});
	}
	// -- ComponentCtrl --//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
			throws java.io.IOException {
		super.renderProperties(renderer);
		if (!_layers.isEmpty()) {
			renderer.render("layers", new LinkedList<Layer>(_layers.values()));
			if (!_baseLayer.getUuid().equals(_layers.keySet().iterator().next())) {
				render(renderer, "baseLayer", _baseLayer);
			}
		}
		if (!_controls.isEmpty())
			renderer.render("controls", new LinkedList<Control>(_controls.values()));
		render(renderer, "center", _center);
	}
	
	
}
