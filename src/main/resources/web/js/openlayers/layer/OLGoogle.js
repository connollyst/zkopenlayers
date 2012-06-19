/** OLGoogle.js

	Purpose:
		
	Description:
		
	History:
		Mon, Jun 18, 2012  5:14:20 PM, Created by jumperchen

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
(function () {
openlayers.layer.OLGoogle = zk.$extends(openlayers.layer.OLLayer, {
	$define: {
		type: null
	},
	toOLWidget: function () {
		return new OpenLayers.Layer.Google(this.uuid, this.getOptions());
	},
	getOptions: function () {
		var options = this.$supers('getOptions', arguments);
		if (this._type) {
			options.type = $eval(this._type);
		}
		return options;
	}
});
})();