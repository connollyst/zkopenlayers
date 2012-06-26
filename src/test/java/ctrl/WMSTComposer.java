/* WMSTComposer.java

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
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * A WMS layer with Time demo
 * 
 * @author jumperchen
 */
public class WMSTComposer extends SelectorComposer<Window> {


	@Wire
	private Openlayers map;

	@Wire
	private Textbox year;

	@Wire
	private Textbox month;

	@Wire
	private Textbox day;

	@Wire
	private Textbox hour;

	@Wire
	private Textbox minute;

	private WMS ia_wms;
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new WMS("OpenLayers WMS",
				"http://vmap0.tiles.osgeo.org/wms/vmap0?", toMap(pair("layers",
						"basic"))));

		map.addLayer(new WMS("NASA Global Mosaic",
				"http://t1.hypercube.telascience.org/cgi-bin/landsat7", toMap(
						pair("layers", "landsat7"), pair("visible", false))));
		ia_wms = new WMS(
				"Nexrad",
				"http://mesonet.agron.iastate.edu/cgi-bin/wms/nexrad/n0r-t.cgi?",
				toMap(pair("layers", "nexrad-n0r-wmst"),
						pair("transparent", true), pair("format", "image/png"),
						pair("time", "2005-08-29T13:00:00Z")));
		map.addLayer(ia_wms);
		map.addControl(new LayerSwitcher());
		map.zoomToExtent(new Bounds(-100.898437,22.148438,-78.398437,39.726563), false);
	}

	@Listen("onChange = textbox; onOK = textbox")
	public void changeTime(Event evt) {
		final String formatted = String.format("%s-%s-%sT%s:%s:00Z", year.getValue(),
				month.getValue(), day.getValue(), hour.getValue(),
				minute.getValue());
		ia_wms.mergeNewParams(toMap(pair("time", 
				formatted)));
	}
}
