/* BingTilesComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 4:47:53 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.Bing;
import org.zkoss.openlayers.layer.VirtualEarth;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Bing Tiles layer demo
 * 
 * @author jumperchen
 */
public class BingTilesComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		String apiKey = "AqTGBsziZHIJYYxgivLBf0hVdrAk9mWO5cQcb8Yux8sW5M8c8opEC2lZqKR1ZZXf";

		map.addLayer(new Bing(toMap(pair("key", apiKey),
				pair("type", "Road"),
				// custom metadata parameter to request the new map style - only
				// useful
				// before May 1st, 2011
				pair("metadataParams", toMap(pair("mapVersion", "v1"))))));

		map.addLayer(new Bing(toMap(pair("key", apiKey),
				pair("type", "Aerial"))));
		map.addLayer(new Bing(toMap(pair("key", apiKey),
				pair("type", "AerialWithLabels"),
				pair("name", "Bing Aerial With Labels"))));

		map.addControl(new LayerSwitcher());
		map.setCenter(new LonLat(-71.147, 42.472).transform(new Projection(
				"EPSG:4326"), map.getProjection()), 11);

	}
}
