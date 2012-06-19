/* ConfigParser.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 19, 2012 11:40:28 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.openlayers.init;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.locks.ReentrantLock;

import org.zkoss.idom.Document;
import org.zkoss.idom.Element;
import org.zkoss.idom.input.SAXBuilder;

/**
 * @author jumperchen
 * 
 */
public class ConfigParser {
	private static ReentrantLock _lock = new ReentrantLock();

	public static Configuration parser(URL url) throws IOException {
		try {
			final Document document = new SAXBuilder(false, false, true)
					.build(url);
			paserLayer(document);
		} catch (Exception ex) {
			if (ex instanceof IOException)
				throw (IOException) ex;
			if (ex instanceof RuntimeException)
				throw (RuntimeException) ex;
			final IOException ioex = new IOException("Unable to load " + url);
			ioex.initCause(ex);
			throw ioex;
		}
		return Configuration.getInstance();
	}

	private static void paserLayer(Document doc) {
		Configuration config = Configuration.getInstance();
		Element root = doc.getRootElement();
		_lock.lock();
		try {
			for (Element layer : root.getElements("layer")) {
				String vendor = layer.getAttribute("vendor");
				String className = layer.getAttribute("class");
				Layer lay = config.getLayer(vendor);
				if (lay == null)
					lay = new Layer(vendor, className);
				System.out.printf("Vendor: %s, class: %s\n", vendor, className);
				for (Element props : layer.getElements("props")) {
					String propName = props.getAttribute("name");
					String propType = props.getAttribute("type");
					lay.addProp(propName, propType);
					System.out.printf("Name: %s, Type: %s\n", propName,
							propType);
				}
				config.addLayer(lay);
			}
		} finally {
			_lock.unlock();
		}
	}
}
