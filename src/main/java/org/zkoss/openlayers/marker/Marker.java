/* Marker.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 5:24:12 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.marker;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Icon;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.layer.Markers;
import org.zkoss.openlayers.util.Function;

/**
 * @author jumperchen
 * 
 */
public class Marker extends OLWidget {
	private LonLat _lonlat;

	private Icon _icon;

	private Markers _parent;

	public Marker(LonLat lonlat, Icon icon) {
		_lonlat = lonlat;
		_icon = icon;
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _lonlat, _icon);
	}
	
	public void setOpacity(double opacity) {
		getNativeObject().invoke("setOpacity", opacity);
	}

	@Override
	protected void setMap(Openlayers map) {
		throw new UnsupportedOperationException(
				"Unsupport setMap() method, please use Markers#addMarker() instead!");
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
	public void setParent(Markers parent) {
		if (_parent != parent) {
			if (_parent != null)
				_parent.removeMarker(this);
			_parent = parent;
		}
	}

	@Override
	protected String getNativeClass() {
		return "OpenLayers.Marker";
	}

}
