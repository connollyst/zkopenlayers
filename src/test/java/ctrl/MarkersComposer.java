/* MarkersComposer.java

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
import org.zkoss.openlayers.base.Icon;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.base.Pixel;
import org.zkoss.openlayers.base.Size;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.Markers;
import org.zkoss.openlayers.layer.Text;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.openlayers.marker.Marker;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Markers layer demo
 * 
 * @author jumperchen
 */
public class MarkersComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new WMS( "OpenLayers WMS", 
             "http://vmap0.tiles.osgeo.org/wms/vmap0", toMap(pair("layers", "basic"))));
             
        map.setCenter(new LonLat(0, 0), 0);
        map.addLayer(new Text("text", toMap(pair("location", Executions.getCurrent().getContextPath() + "/data/textfile.txt"))));

        Markers markers = new Markers("Markers");
        map.addLayer(markers);

        Size size = new Size(21,25);
        Pixel offset = new Pixel(-(size.getWidth()/2), -size.getHeight());
        Icon icon = new Icon("http://www.openlayers.org/dev/img/marker.png",size,offset);
        markers.addMarker(new Marker(new LonLat(0,0),icon));

        Icon halfIcon = (Icon) icon.clone();
         markers.addMarker(new Marker(new LonLat(0,45),halfIcon));

         Marker marker = new Marker(new LonLat(90,10), (Icon)icon.clone());
         marker.setOpacity(0.2);
//         marker.events.register('mousedown', marker, function(evt) { alert(this.icon.url); OpenLayers.Event.stop(evt); });
         markers.addMarker(marker); 
         map.addControl(new LayerSwitcher());
         map.zoomToMaxExtent();

         halfIcon.setOpacity(0.5);
	}
}
