/* GoogleComposer.java

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

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.Google;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

/**
 * A Google v2 layer demo
 * @author jumperchen
 */
public class GoogleComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers olComp;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		olComp.addLayer(new Google("Google Streets",  toMap(pair("type", Google.Type.NORMAL))));
		olComp.addLayer(new Google("Google Satellite", toMap(pair("numZoomLevels", 20))));
		olComp.addLayer(new Google("Google Hybrid", toMap(pair("type", Google.Type.HYBRID), pair("numZoomLevels", 20))));
		olComp.addLayer(new Google("Google Physical", toMap(pair("type", Google.Type.SATELLITE), pair("numZoomLevels", 22))));
		olComp.addControl(new LayerSwitcher());
		olComp.setCenter(new LonLat(10.2, 48.9), 5, false, false);
	}
}
