/* OverviewMapComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 11:40:10 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;
import static org.zkoss.openlayers.util.Helper.mergeMap;

import java.util.Arrays;
import java.util.Map;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.control.Control;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.control.OverviewMap;
import org.zkoss.openlayers.layer.Layer;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Overview Map demo
 * 
 * @author jumperchen
 * 
 */
public class OverviewMapComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map1;

	@Wire
	private Openlayers map2;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		// set up some layers
		Layer ol = new WMS(
	            "OpenLayers WMS", 
	            "http://vmap0.tiles.osgeo.org/wms/vmap0",
	            toMap(pair("layers", "basic"))
	        );
		Layer jpl = new WMS(
				"NASA Global Mosaic", 
	            "http://t1.hypercube.telascience.org/cgi-bin/landsat7",
	            toMap(pair("layers", "landsat7"))
	        );
                
        // A clone of the above layer that we will use as overview for map2.
        // We need to clone jpl before the it gets added to a map, so the
        // clone can have its own maxExtent and maxResolution instead of
        // getting these settings initialized from map1.
		Layer jplOverview = (Layer)jpl.clone();
        
        // A more detailled layer of Manhattan for map2
        Layer ny = new WMS(
            "Manhattan", 
            "http://demo.opengeo.org/geoserver/wms",
            toMap(pair("layers", "tiger-ny"), pair("format", "image/png"))
        );
                
        map1.addLayers(Arrays.asList(ol, jpl));
        map1.addControl(new LayerSwitcher());
        
        // create an overview map control with the default options
        Control overview1 = new OverviewMap(toMap(pair("maximized", true)));
        map1.addControl(overview1);
        
        map1.setCenter(new LonLat(0, 0), 2);
        
        // create the bottom map (with advanced overview map control)
        Map mapOptions = toMap(
        		pair("maxExtent", new Bounds(-8242894.927728, 4965204.031195, -8227290.161511, 4994963.723637)),
        		pair("maxResolution", 116.2487986015621),
        		pair("projection", "EPSG:900913"),
        		pair("units", "m")        );

        
        map2.setOptions(mapOptions);
        map2.addLayer(ny);
        
        
        // create an overview map control with non-default options
        Map controlOptions = toMap(
    		pair("maximized", true),
    		pair("mapOptions", 
    				mergeMap(mapOptions, 
    					pair("maxResolution", 156543.0339),
    					pair("maxExtent", new Bounds(-20037508.34, -20037508.34,
                             20037508.34, 20037508.34)))
    		),
    		pair("layers", Arrays.asList(jplOverview)
        ));
        Control overview2 = new OverviewMap(controlOptions);
        map2.addControl(overview2);
        
        map2.setCenter(new LonLat(-8233165.3575055, 4980298.21113769), 3);
	}
}
