/* WMSComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 11:42:53 AM , Created by jumperchen
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
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A WMS layer demo
 * 
 * @author jumperchen
 */
public class WMSComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new WMS("OpenLayers WMS",
				"http://vmap0.tiles.osgeo.org/wms/vmap0", toMap(pair("layers",
						"basic"))));
		map.addControl(new LayerSwitcher());
		map.setCenter(new LonLat(5, 40), 5, false, false);

	}
}
