/* Feature.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 9:00:46 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.feature;

import java.util.Map;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.util.Function;
import org.zkoss.openlayers.layer.Vector;

/**
 * @author jumperchen
 *
 */
public class Feature extends OLWidget {
	private Layer _layer;
	private LonLat _lonlat;
	private Map _data;
	private Vector _parent;
	public Feature(Layer layer, LonLat lonlat, Map data) {
		_layer = layer;
		_lonlat = lonlat;
		_data = data;
		
	}
	
	public Map getData() {
		return _data;
	}
	
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Feature";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _layer, _lonlat, _data);
	}

	@Override
	protected void setMap(Openlayers map) {
		throw new UnsupportedOperationException(
				"Unsupport setMap() method, please use Vector#addFeatures() instead!");
	}

	@Override
	public void onMapAttached(Openlayers newMap, Openlayers oldMap) {
		_map = newMap;
		super.onMapAttached(newMap, oldMap);
	}

	@Override
	public void onMapDetached(Openlayers map) {
		_map = null;
		super.onMapDetached(map);
	}

	/**
	 * Internal use only.
	 * 
	 * @param parent
	 */
	public void setParent(Vector parent) {
		if (_parent != parent) {
			if (_parent != null)
				_parent.removeFeature(this);
			_parent = parent;
		}
	}

}
