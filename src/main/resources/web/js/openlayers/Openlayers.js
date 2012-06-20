/* Openlayers.js

	Purpose:
		
	Description:
		
	History:
		Wed, Jun 20, 2012  9:59:38 AM, Created by jumperchen

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
(function () {
OpenLayers.ImgPath = zk.ajaxURI('/web/js/openlayers/ext/img/', {au:true});

openlayers.Openlayers = zk.$extends(zul.Widget, {
	$init: function () {
		this.$supers('$init', arguments);
		this._layers = [];
		this._controls = [];
		this._popups = [];
	},
	$define: {
		layer: function () {
			this._layers.push(this._layer);
			if (this._map) {
				var lay = this._layer;
				this._map.addLayer(new lay.pkg(lay.name, lay.options));
				
				// redraw to avoid blank screen
				if (this._map.baseLayer)
					this._map.baseLayer.redraw();
			}
		},
		control: function () {
			this._controls.push(this._control);
			if (this._map) {
				var ctrl = this._control;
				this._map.addControl(new ctrl.pkg(ctrl.options));
			}
		},
		center: function () {
			if (this._map) {
				this._map.setCenter.apply(this._map, this._center);
				this._map.render(this.$n());
			}
		},
		baseLayer: function () {
			if (this._map) {
				var lay = this._map.getLayersBy("uuid", this._baseLayer.id);
				this._map.setBaseLayer(lay);
			}
		}
	},
	setLayers: function (layers) {
		this._layers = layers;
	},
	setControls: function (controls) {
		this._controls = controls;
	},
	bind_: function () {
		this._map = new OpenLayers.Map(this.uuid, {theme: null});
		var layers, controls;
		if (layers = this._layers) {
			for (var i = 0, j = layers.length; i < j; i++) {
				var lay = layers[i],
					pkg = lay.pkg;
				this._map.addLayer(new pkg(lay.name, lay.options));
			}
		}
		if (controls = this._controls) {
			for (var i = 0, j = controls.length; i < j; i++) {
				var ctrl = controls[i],
					pkg = ctrl.pkg;
				this._map.addControl(new pkg(ctrl.options));
			}
		}
		if (layers && this._center) {
			this._map.setCenter.apply(this._map, this._center);
			//this._map.setCenter(this._center[0], this._center[1], this._center[2], this._center[3]);
		}
//		if (layers.length) {
//		this._map.setCenter(new OpenLayers.LonLat(10.2, 48.9).transform(
//		        new OpenLayers.Projection("EPSG:4326"),
//		        this._map.getProjectionObject()
//		    ), 5);
//		}
		zWatch.listen({onSize: this});
			this.$supers(openlayers.Openlayers,'bind_', arguments);
	},
	onSize: function () {
	    this._map.updateSize();
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		this._map.destroy();
		this._map = this._controls = this._layers = null;
		this.$supers(openlayers.Openlayers,'unbind_', arguments);
	},
	redraw: function (out) {
		out.push('<div', this.domAttrs_(), '></div>');
	}
});

})();