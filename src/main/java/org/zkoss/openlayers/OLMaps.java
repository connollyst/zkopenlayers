/* OLMaps.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 18, 2012 3:35:25 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zul.impl.XulElement;

/**
 * @author jumperchen
 *
 */
public class OLMaps extends XulElement {
	Component[] layers;
	public void onCreate() {
		Template tm = this.getTemplate("layer");
		layers = tm.create(this, null, null, null);
	}
	//super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);

		render(renderer, "layers", layers);
	}

}
