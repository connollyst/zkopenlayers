/* Vector.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 5:30:55 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.layer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.feature.Feature;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 * 
 */
public class Vector extends Layer {
	private List<Feature> _features;

	public Vector(String name) {
		this(name, null);
	}

	public Vector(String name, Map options) {
		super(name, options);
		init();
	}

	private void init() {
		_features = new LinkedList<Feature>();
	}

	public void addFeatures(List<? extends Feature> features) {
		for (Feature f : features)
			addFeature(f);
	}

	public void addFeature(Feature feature) {
		if (feature == null)
			throw new NullPointerException("Feature cannot be null!");
		_features.add(feature);
		feature.setParent(this);
		if (_map != null) {
			feature.onMapAttached(_map, null);
			_map.invalidate();
		}

	}

	public void drawFeature(org.zkoss.openlayers.feature.Vector feature) {
		clientUpdate("drawFeature", feature);
	}

	public void removeFeatures(List<Feature> features) {
		for (Feature f : features)
			removeFeature(f);
	}

	public void removeFeature(Feature feature) {
		if (_features.remove(feature)) {
			feature.onMapDetached(_map);
			feature.setParent(null);
			if (_map != null)
				_map.invalidate();
		}
	}

	public void removeFeatures() {
		for (Feature f : _features)
			removeFeature(f);
	}

	public void refresh() {
		clientUpdate("refresh");
	}

	public void removeAllFeatures() {
		clientUpdate("removeAllFeatures");
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Vector";
	}

	@Override
	public String toJSONString() {
		Function fun = getNativeObject();
		if (!_features.isEmpty()) {
			fun.invoke("addFeatures", _features);
		}
		return fun.toJSONString(_map);
	}

	@Override
	public void onMapAttached(Openlayers newMap, Openlayers oldMap) {
		super.onMapAttached(newMap, oldMap);
		for (Feature m : _features)
			m.onMapAttached(newMap, oldMap);
	}

	@Override
	public void onMapDetached(Openlayers map) {
		super.onMapDetached(map);
		for (Feature m : _features)
			m.onMapDetached(map);
	}

}
