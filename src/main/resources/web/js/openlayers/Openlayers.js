/* Openlayers.js

	Purpose:
		
	Description:
		The Openlayers version is 2.11 - http://openlayers.org/
		
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
			if (this.map) {
				this.map.addLayer(this._layer);
				this.redrawLayer();
			}
		},
		control: function () {
			this._controls.push(this._control);
			if (this.map)
				this.map.addControl(this._control);
		},
		center: function () {
			if (this.map) {
				this.map.setCenter.apply(this.map, this._center);
				this.map.render(this.$n());
			}
		},
		baseLayer: function () {
			if (this.map) {
				var lay = this.map.getLayersBy("uuid", this._baseLayer.id);
				this.map.setBaseLayer(lay);
			}
		},
		removeControl: function () {
			if (this.map) {
				var ctrl = this.map.getControlsBy("uuid", this._removeControl.id);
				this.map.removeControl(ctrl);
			}
		},
		removeLayer: function () {
			if (this.map) {
				var lay = this.map.getLayersBy("uuid", this._removeLayer.id);
				this.map.removeLayer(lay);
			}
		},
		mapEval: function (data) {
			if (this.map) {
				this.map[data[0]].apply(this.map, data[1]);
			}
		},
		clientUpdate: function (data) {
			if (this.map) {
				var olWidget = this.getOLWidget(data[0]);
				if (olWidget)
					olWidget[data[1]].apply(olWidget, data[2]);
			}
		},
		options: function (options) {
			if (this.map)
				this.map.setOptions(options);
		}
	},
	getOLWidget: function (uuid) {
		if (this.map) {
			var w = this.map.getLayersBy("uuid", uuid);
			if (w.length)
				return w[0];
			w = this.map.getControlsBy("uuid", uuid);
			if (w.length)
				return w[0];
		}
		return null;
	},
	redrawLayer: function (timeout) {
		// redraw to avoid blank screen
		var self = this;
		if (self.map && self.map.baseLayer)
			if (timeout)
				setTimeout(function () {
					if (self.map && self.map.baseLayer)
						self.map.baseLayer.redraw();
				}, timeout);
			else
				self.map.baseLayer.redraw();
	},
	setLayers: function (layers) {
		this._layers = layers;
	},
	setControls: function (controls) {
		this._controls = controls;
	},
	bind_: function (desktop, skipper, after) {
		this.$supers(openlayers.Openlayers,'bind_', arguments);
		var options = this._options || {};
		options.theme = null;
		this.map = new OpenLayers.Map(this.uuid, options);
		var layers, controls;
		if (layers = this._layers) {
			this.map.addLayers(layers);
		}
		if (controls = this._controls) {
			this.map.addControls(controls);
		}
		if (layers && this._center) {
			var self = this;
			after.push(function () {
				if (self.map)
					self.map.setCenter.apply(self.map, self._center);
			});
		}
		
		/** 
		 * Supported map event types:
		 * movestart - triggered after the start of a drag, pan, or zoom
	     * move - triggered after each drag, pan, or zoom
	     * moveend - triggered after a drag, pan, or zoom completes
	     * zoomend - triggered after a zoom completes
	     * mouseover - triggered after mouseover the map
	     * mouseout - triggered after mouseout the map
	     * mousemove - triggered after mousemove the map
	     * changebaselayer - triggered after the base layer changes
	     */
		this.map.events.on({
			changebaselayer: this.proxy(this.onChangeBaseLayer)
		});
		zWatch.listen({onSize: this});
	},
	onSize: function () {
	    this.map.updateSize();
	    this.redrawLayer(200);
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		this.map.destroy();
		this.map = this._controls = this._layers = null;
		this.$supers(openlayers.Openlayers,'unbind_', arguments);
	},
	/// map events
	onChangeBaseLayer: function (evt) {
		this.fire('onChangeBaseLayer', evt.layer.uuid);
	},
	redraw: function (out) {
		out.push('<div', this.domAttrs_(), '></div>');
	}
});

})();