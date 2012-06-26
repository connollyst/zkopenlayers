/* BoxesVectorComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 5:17:37 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import java.util.Arrays;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.control.Control;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.control.SelectFeature;
import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.layer.Vector;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.util.CollectionsX.ArrayList;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Boxes Vector Demo
 * 
 * @author jumperchen
 * 
 */
public class BoxesVectorComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);

		Double[][] box_extents = new Double[][] {
				new Double[] { -10.0, 50.0, 5.0, 60.0 },
				new Double[] { -75.0, 41.0, -71.0, 44.0 },
				new Double[] { -122.6, 37.6, -122.3, 37.9 },
				new Double[] { 10.0, 10.0, 20.0, 20.0 } };
		Layer ol_wms = new WMS("OpenLayers WMS",
				"http://vmap0.tiles.osgeo.org/wms/vmap0", toMap(pair("layers",
						"basic")));
		Vector boxes = new Vector("Boxes");

		for (int i = 0; i < box_extents.length; i++) {
			Bounds bounds = Bounds.fromArray(new ArrayList(box_extents[i]));
			org.zkoss.openlayers.feature.Vector box = new org.zkoss.openlayers.feature.Vector(
					bounds.toGeometry());
			boxes.addFeature(box);
		}

		map.addLayers(Arrays.asList(ol_wms, boxes));
		map.addControl(new LayerSwitcher());
		Control sf = new SelectFeature(boxes);
		map.addControl(sf);
		sf.activate();
		map.zoomToMaxExtent();

	}

}
