/* NavigationControlComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 21, 2012 18:42:53, Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.control.Navigation;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;
/**
 * A Navigation Control demo
 * 
 * @author jumperchen
 */
public class NavigationControlComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers olComp;

	@Wire
	private Checkbox ck;

	@Wire
	private Label msg;
	private Navigation nav;
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		olComp.addLayer(new WMS("OpenLayers WMS",
				"http://vmap0.tiles.osgeo.org/wms/vmap0", toMap(pair("layers",
						"basic"))));
		nav = new Navigation(toMap(pair("zoomWheelEnabled", false)));
		olComp.addControl(nav);
		olComp.zoomToMaxExtent();

	}
	@Listen("onCheck = #ck")
	public void onChange(Event evt) {
		if (ck.isChecked()) {
			msg.setValue("Turn on");
			nav.enableZoomWheel();
		} else {
			msg.setValue("Turn off");
			nav.disableZoomWheel();
		}
	}
}
