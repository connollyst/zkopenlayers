/* CanvasComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 18:00:13 , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.base.StyleMap;
import org.zkoss.openlayers.layer.Google;
import org.zkoss.openlayers.layer.Vector;
import org.zkoss.openlayers.protocol.WFS;
import org.zkoss.openlayers.strategy.BBOX;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Canvas renderer demo
 * 
 * @author jumperchen
 */
public class CanvasComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	@SuppressWarnings("unchecked")
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.setOptions(toMap(
				pair("projection", new Projection("EPSG:900913")),
				pair("displayProjection", new Projection("EPSG:4326")),
				pair("units", "m"),
				pair("maxResolution", 156543.0339),
				pair("maxExtent", new Bounds(-20037508, -20037508, 20037508,
						20037508))));

		map.addLayer(new Google("Google Layer", toMap(pair(
				"sphericalMercator", true))));

		// prepare to style the data
		StyleMap styleMap = new StyleMap(toMap(pair("strokeColor", "black"),
				pair("strokeWidth", 2), pair("strokeOpacity", 0.5),
				pair("fillOpacity", 0.2)));

		// create a color table for state FIPS code
		String[] colors = new String[] { "red", "orange", "yellow", "green",
				"blue", "purple" };
		String code;
		Map<String, Map<String, String>> fips = new HashMap<String, Map<String, String>>();
		for (int i = 1; i <= 66; ++i) {
			code = "0" + i;
			code = code.substring(code.length() - 2);
			fips.put(code, toMap(pair("fillColor", colors[i % colors.length])));
		}
		// add unique value rules with your color lookup
		styleMap.addUniqueValueRules("default", "STATE_FIPS", fips, null);

		// create a vector layer using the canvas renderer (where available)
		map.addLayer(new Vector(
				"States",
				toMap(pair("strategies", new Object[] { new BBOX() }),
					pair("protocol",
					new WFS(toMap(pair("version", "1.1.0"),
							pair("srsName", "EPSG:900913"),
							pair("url",
								"http://v2.suite.opengeo.org/geoserver/wfs"),
							pair("featureType", "states"),
							pair("featureNS",
								"http://usa.opengeo.org")))),
						pair("styleMap", styleMap),
						pair("renderers",
							new String[] { "Canvas", "SVG", "VML" }))));

		// if you want to use Geographic coords, transform to ESPG:900913
		map.zoomToExtent(new Bounds(-73.839111, 40.287907, -68.214111,
				44.441624).transform(map.getDisplayProjection(),
				map.getProjection()), false);

	}
}
