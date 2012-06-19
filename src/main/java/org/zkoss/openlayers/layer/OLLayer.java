/* OLLayer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 18, 2012 4:59:27 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.openlayers.layer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zkoss.lang.Objects;
import org.zkoss.openlayers.init.Configuration;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.ext.DynamicPropertied;
import org.zkoss.zk.ui.sys.ComponentsCtrl;

/**
 * @author jumperchen
 *
 */
public class OLLayer extends AbstractComponent implements DynamicPropertied {

	private Map<String, Object> _props;
	private String _vendor;
	
	public void setVendor(String vendor) {
		if (!Objects.equals(_vendor, vendor)) {
			if (null == Configuration.getInstance().getLayer(vendor))
				throw new WrongValueException("Unknown vendor: "+vendor);
			_vendor = vendor;
			invalidate();
		}
	}
	
	public String getVendor() {
		return _vendor;
	}
	
	public boolean hasDynamicProperty(String name) {
		return ComponentsCtrl.isReservedAttribute(name);
	}
	/** Returns the dynamic property, or null if not found.
	 * Note: it must be a String object or null.
	 */
	public Object getDynamicProperty(String name) {
		return _props != null ? _props.get(name): null;
	}
	/** Sets the dynamic property.
	 * Note: it converts the value to a string object (by use of
	 * {@link Objects#toString}).
	 *
	 * <p>Note: it handles the style property specially. Refer to {@link #setStyle}
	 * for details.
	 */
	public void setDynamicProperty(String name, Object value)
	throws WrongValueException {
		if (name == null)
			throw new WrongValueException("name is required");
		if (!hasDynamicProperty(name))
			throw new WrongValueException("Attribute not allowed: "+name+"\nSpecify the ZK namespace if you want to use special ZK attributes");

		String sval = Objects.toString(value);
		setDynaProp(name, value);
		smartUpdate("dynamicProperty", new String[] {name, sval});
	}
	
	/** Set the dynamic property 'blindly'. */
	private void setDynaProp(String name, Object value) {
		if (value == null) {
			if (_props != null) _props.remove(name);
		} else {
			if (_props == null)
				_props = new LinkedHashMap<String, Object>();
			_props.put(name, value);
		}
	}
	
	//super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
		
		render(renderer, "vendor", _vendor);
		render(renderer, "props", _props);
	}
}
