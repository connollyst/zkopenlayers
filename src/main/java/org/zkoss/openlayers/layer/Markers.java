/* Markers.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 5:18:11 PM , Created by jumperchen
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
import org.zkoss.openlayers.marker.Marker;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 *
 */
public class Markers extends Layer {
	private List<Marker> _markers;
	public Markers(String name) {
		this(name, null);
	}
	public Markers(String name, Map options) {
		super(name, options);
		init();
	}
	private void init() {
		_markers = new LinkedList<Marker>();
	}
	public void addMarker(Marker marker) {
		if (marker == null)
			throw new NullPointerException("Marker cannot be null!");
		_markers.add(marker);
		marker.setParent(this);
		if (_map != null) {
			marker.onMapAttached(_map, null);
			_map.invalidate();
		}
	}
	public void removeMarker(Marker marker) {
		if (_markers.remove(marker)) {
			marker.onMapDetached(_map);
			marker.setParent(null);
			if (_map != null)
				_map.invalidate();
		}
	}
	public void clearMarkers() {
		for (Marker m : _markers)
			removeMarker(m);
	}
	@Override
	public String toJSONString() {
		Function fun = getNativeObject();
		if (!_markers.isEmpty()) {
			for (Marker m : _markers)
				fun.invoke("addMarker", m);
		}
		return fun.toJSONString(_map);
	}
	@Override
	public void onMapAttached(Openlayers newMap, Openlayers oldMap) {
		super.onMapAttached(newMap, oldMap);
		for (Marker m : _markers)
			m.onMapAttached(newMap, oldMap);
	}
	@Override
	public void onMapDetached(Openlayers map) {
		super.onMapDetached(map);
		for (Marker m : _markers)
			m.onMapDetached(map);
	}
	
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Layer.Markers";
	}

}
