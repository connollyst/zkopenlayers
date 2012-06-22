/* OLBase.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 22, 2012 5:32:58 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.base;

import org.zkoss.openlayers.OLWidget;
import org.zkoss.openlayers.Openlayers;

/**
 * @author jumperchen
 *
 */
public abstract class OLBase extends OLWidget {


	@Override
	protected void setMap(Openlayers map) {
		throw new UnsupportedOperationException(
				"Unsupport setMap() method!");
	}

}
