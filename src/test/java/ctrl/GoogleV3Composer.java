/* GoogleV3Composer.java

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
import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.GoogleV3;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

/**
 * A Google v3 layer demo
 * @author jumperchen
 */
public class GoogleV3Composer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new GoogleV3("Google Streets",  toMap(pair("type", GoogleV3.Type.ROADMAP))));
		map.addLayer(new GoogleV3("Google Satellite", toMap(pair("numZoomLevels", 20))));
		map.addLayer(new GoogleV3("Google Hybrid", toMap(pair("type", GoogleV3.Type.HYBRID), pair("numZoomLevels", 20))));
		map.addLayer(new GoogleV3("Google Physical", toMap(pair("type", GoogleV3.Type.SATELLITE), pair("numZoomLevels", 22))));
		map.addControl(new LayerSwitcher());
		map.setCenter(new LonLat(10.2, 48.9).transform(new Projection("EPSG:4326"),
				map.getProjection()), 5, false, false);
	}
}
