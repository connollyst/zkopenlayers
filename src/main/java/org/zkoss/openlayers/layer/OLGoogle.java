/* OLGoogleV3.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 18, 2012 4:59:11 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.AbstractComponent;

/**
 * @author jumperchen
 *
 */
public class OLGoogle extends OLLayer {
	private String _type;
	public OLGoogle() {}
	public OLGoogle(String type) {
		_type = type;
	}
	public void setType(String type) {
		if (!Objects.equals(_type, type)) {
			_type = type;
			smartUpdate("type", _type);
		}
	}
	//super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);

		render(renderer, "type", _type);
	}

}
