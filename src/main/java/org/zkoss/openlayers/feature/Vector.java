/* Vector.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 9:05:02 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.feature;

import java.util.Collections;
import java.util.Map;
import org.zkoss.openlayers.geometry.Geometry;
import org.zkoss.openlayers.util.Function;
import static org.zkoss.openlayers.util.Helper.toMap;
import static org.zkoss.openlayers.util.Helper.pair;

/**
 * @author jumperchen
 *
 */
public class Vector extends Feature {
	
	private Geometry _geometry;
	private Map _style;
	public Vector(Geometry geometry) {
		this(geometry, null, null);
	}
	public Vector(Geometry geometry, Map data) {
		this(geometry, data, null);
	}
	public Vector(Geometry geometry, Map data, Map style) {
		super(null, null, data);
		_geometry = geometry;
		_style = style;
	}
	
	@Override
	protected String getNativeClass() {
		return "OpenLayers.Feature.Vector";
	}

	@Override
	protected Function newNativeObject() {
		return new Function(getNativeClass(), _geometry, getData(), _style);
	}
	public static Map getStyle(String name) {
		if ("default".equals(name)) {
			return toMap(pair("fillColor", "#ee9900"),
					pair("fillOpacity", 0.4), 
					pair("hoverFillColor", "white"),
					pair("hoverFillOpacity", 0.8),
					pair("strokeColor", "#ee9900"),
					pair("strokeOpacity", 1),
					pair("strokeWidth", 1),
					pair("strokeLinecap", "round"),
					pair("strokeDashstyle", "solid"),
					pair("hoverStrokeColor", "red"),
					pair("hoverStrokeOpacity", 1),
					pair("hoverStrokeWidth", 0.2),
					pair("pointRadius", 6),
					pair("hoverPointRadius", 1),
					pair("hoverPointUnit", "%"),
					pair("pointerEvents", "visiblePainted"),
					pair("cursor", "inherit"));
			
		} else if ("select".equals(name)) {
			return toMap(pair("fillColor", "blue"),
					pair("fillOpacity", 0.4), 
					pair("hoverFillColor", "white"),
					pair("hoverFillOpacity", 0.8),
					pair("strokeColor", "blue"),
					pair("strokeOpacity", 1),
					pair("strokeWidth", 2),
					pair("strokeLinecap", "round"),
					pair("strokeDashstyle", "solid"),
					pair("hoverStrokeColor", "red"),
					pair("hoverStrokeOpacity", 1),
					pair("hoverStrokeWidth", 0.2),
					pair("pointRadius", 6),
					pair("hoverPointRadius", 1),
					pair("hoverPointUnit", "%"),
					pair("pointerEvents", "visiblePainted"),
					pair("cursor", "pointer"));
		} else if ("temporary".equals(name)) {
			return toMap(pair("fillColor", "#66cccc"),
					pair("fillOpacity", 0.2) ,
					pair("hoverFillColor", "white"),
					pair("hoverFillOpacity", 0.8),
					pair("strokeColor", "#66cccc"),
					pair("strokeOpacity", 1),
					pair("strokeLinecap", "round"),
					pair("strokeWidth", 2),
					pair("strokeDashstyle", "solid"),
					pair("hoverStrokeColor", "red"),
					pair("hoverStrokeOpacity", 1),
					pair("hoverStrokeWidth", 0.2),
					pair("pointRadius", 6),
					pair("hoverPointRadius", 1),
					pair("hoverPointUnit", "%"),
					pair("pointerEvents", "visiblePainted"),
					pair("cursor", "inherit"));
		} else if ("delete".equals(name)) {
			return toMap(pair("display", "none"));
		}
		return Collections.EMPTY_MAP;
	}
}
