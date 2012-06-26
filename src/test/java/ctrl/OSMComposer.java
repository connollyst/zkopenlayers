/* OSMComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 18:46:52 , Created by jumperchen
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
import org.zkoss.openlayers.layer.OSM;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A OSM layer demo
 * 
 * @author jumperchen
 */
public class OSMComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new OSM("Simple OSM Map"));
		map.setCenter(new LonLat(-71.147, 42.472).transform(
                new Projection("EPSG:4326"),
                map.getProjection()), 12, false, false);

	}
}
