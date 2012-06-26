/* WMTSComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 03:42:53 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.OSM;
import org.zkoss.openlayers.layer.WMTS;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A WMTS layer demo
 * 
 * @author jumperchen
 */
public class WMTSComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.setOptions(toMap(
				pair("projection", "EPSG:900913"),
				pair("units", "m"),
				pair("maxExtent", new Bounds(-20037508.34, -20037508.34,
						20037508.34, 20037508.34)),
				pair("maxResolution", 156543.0339)));

		map.addLayer(new OSM());
		String[] matrixIds = new String[26];
		for (int i = 0; i < 26; ++i) {
			matrixIds[i] = "EPSG:900913:" + i;
		}
		map.addLayer(new WMTS(
				toMap(pair("name", "Medford Buildings"),
						pair("url",
								"http://v2.suite.opengeo.org/geoserver/gwc/service/wmts/"),
						pair("layer", "medford:buildings"),
						pair("matrixSet", "EPSG:900913"),
						pair("matrixIds", matrixIds),
						pair("format", "image/png"), pair("style", "_null"),
						pair("opacity", 0.7), pair("isBaseLayer", false))));
		map.addControl(new LayerSwitcher());
		map.setCenter(new LonLat(-13677832, 5213272), 13, false, false);

	}
}
