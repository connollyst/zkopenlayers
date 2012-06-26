/* MultimapMercatorComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 12:27:47 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import java.util.Arrays;
import java.util.Map;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.control.EditingToolbar;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.Bing;
import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.layer.Vector;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A MultiMap SphericalMercator demo
 * 
 * @author jumperchen
 * 
 */
public class MultimapMercatorComposer extends SelectorComposer<Window> {
	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		String apiKey = "AqTGBsziZHIJYYxgivLBf0hVdrAk9mWO5cQcb8Yux8sW5M8c8opEC2lZqKR1ZZXf";
		Map options = toMap(
				pair("projection", "EPSG:900913"),
				pair("units", "m"),
				pair("maxResolution", 156543.0339),
				pair("maxExtent", new Bounds(-20037508, -20037508, 20037508,
						20037508)));
		map.setOptions(options);
		
		// OpenLayers.Layer.MultiMap is deprecated, use Bing instead.
		Layer ve = new Bing(toMap(pair("key", apiKey),
				pair("type", "Road"),
				// custom metadata parameter to request the new map style - only
				// useful
				// before May 1st, 2011
				pair("metadataParams", toMap(pair("mapVersion", "v1"))),
				pair("sphericalMercator",
						true)));
		
		Layer merc = new WMS("World Map",
				"http://maps.opengeo.org/geowebcache/service/wms", toMap(pair(
						"layers", "bluemarble")), toMap(pair("opacity", 0.4),
						pair("isBaseLayer", false), pair("wrapDateLine", true)));

		// create a vector layer for drawing
		Layer vector = new Vector("Editable Vectors");

		map.addLayers(Arrays.asList(ve, merc, vector));
		map.addControl(new LayerSwitcher());
		map.addControl(new EditingToolbar(vector));
		map.zoomToMaxExtent();
	}
}
