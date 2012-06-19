/* OLLayer.js

	Purpose:
		
	Description:
		
	History:
		Mon, Jun 18, 2012  5:19:38 PM, Created by jumperchen

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
(function () {

openlayers.layer.OLLayer = zk.$extends(zk.Widget, {
	$define: {
		props: null,
		vendor: null
	},
	getRealVendor: function () {
		return openlayers.configs.layers[this._vendor];
	},
	toOLWidget: function () {
		var vendor = this.getRealVendor();
		zk.log('vendor', vendor);
//		var fn = $eval("function () {return new " + vendor.className + "(arguments);};");
		return new vendor.className(this.uuid, this.getOptions());
	},
	getOptions: function () {
		return this.evalOptions(this._props || {});
	},
	evalOptions: function (props) {
		var gprops = this.getRealVendor().props;
		for (var nm in props) {
			if (gprops[nm] == 'eval') {
				zk.log('eval props', nm);
				props[nm] = $eval(gprops[nm]);
			}
		}
		return props;
	}
});
})();