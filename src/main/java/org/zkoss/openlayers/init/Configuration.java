/* Configuration.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 19, 2012 11:42:47 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.init;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jumperchen
 *
 */
public class Configuration {
	private Map<String,Layer> _layers;
	private Configuration() {
		_layers = new ConcurrentHashMap<String,Layer>(20);
	}
	private static Configuration INSTANCE = new Configuration();
	public static Configuration getInstance() {
		return INSTANCE;
	}
	public void addLayer(Layer layer) {
		_layers.put(layer.getVendor(), layer);
	}
	public Layer getLayer(String brand) {
		return _layers.get(brand);
	}
	public Map<String, Layer> getLayers() {
		return _layers;
	}
}
