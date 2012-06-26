/* BingComposer.java

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
import org.zkoss.openlayers.layer.VirtualEarth;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Bing layer demo
 * 
 * @author jumperchen
 */
public class BingComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new VirtualEarth("Shaded", toMap(pair("type",
				VirtualEarth.Type.SHADED))));
		map.addLayer(new VirtualEarth("Hybrid", toMap(pair("type",
				VirtualEarth.Type.HYBRID))));
		map.addLayer(new VirtualEarth("Aerial", toMap(pair("type",
				VirtualEarth.Type.AERIAL))));

		map.addControl(new LayerSwitcher());
		map.setCenter(new LonLat(-110, 45), 3, false, false);

	}
}
