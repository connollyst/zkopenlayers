/* WebAppInit.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 19, 2012 11:37:29 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.init;

import org.zkoss.zk.ui.WebApp;

/**
 * @author jumperchen
 *
 */
public class WebAppInit implements org.zkoss.zk.ui.util.WebAppInit {
	private static String PATH = "/metainfo/zk/openlayers.xml";
	public void init(WebApp wapp) throws Exception {
		ConfigParser.parser(this.getClass().getResource(PATH));
	}
}
