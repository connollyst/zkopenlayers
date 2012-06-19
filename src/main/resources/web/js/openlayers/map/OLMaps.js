/** Openlayers.js

	Purpose:
		
	Description:
		
	History:
		Mon, Jun 18, 2012 11:48:01 AM, Created by jumperchen

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
(function () {
	OpenLayers.ImgPath = zk.ajaxURI('/web/js/openlayers/ext/img/', {au:true}); 

openlayers.map.OLMaps = zk.$extends(zul.Widget, {
	$init: function () {
		this.$supers('$init', arguments);
		this._layers = [];
		this._controls = [];
		this._popups = [];
	},
	$define: {
	},
	bind_: function () {
		this._map = map = new OpenLayers.Map(this.uuid, {theme: null});
//		var wms = new OpenLayers.Layer.WMS(
//				  "OpenLayers WMS",
//				  "http://vmap0.tiles.osgeo.org/wms/vmap0",
//				  {'layers':'basic'} );
//		this._map.addLayer(wms);
	for (var layers = this.layerIter(); layers.hasNext();) {
		map.addLayer(layers.next().toOLWidget());
	}			
//		map.addControl(new OpenLayers.Control.LayerSwitcher());
	    
//	    var gphy = new OpenLayers.Layer.Google(
//	        "Google Physical",
//	        {type: google.maps.MapTypeId.TERRAIN}
//	    );
//	    var gmap = new OpenLayers.Layer.Google(
//	        "Google Streets", // the default
//	        {numZoomLevels: 20}
//	    );
//	    var ghyb = new OpenLayers.Layer.Google(
//	        "Google Hybrid",
//	        {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 20}
//	    );
//	    var gsat = new OpenLayers.Layer.Google(
//	        "Google Satellite",
//	        {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22}
//	    );
//
//	    map.addLayers([gphy, gmap, ghyb, gsat]);
//
//	            
	this._map.setCenter(new OpenLayers.LonLat(10.2, 48.9).transform(
	        new OpenLayers.Projection("EPSG:4326"),
	        map.getProjectionObject()
	    ), 5);
	zWatch.listen({onSize: this});
		this.$supers(openlayers.map.OLMaps,'bind_', arguments);
	},
	onSize: function () {

	    // Google.v3 uses EPSG:900913 as projection, so we have to
	    // transform our coordinates
	   this._map.setCenter(new OpenLayers.LonLat(10.2, 48.9).transform(
	        new OpenLayers.Projection("EPSG:4326"),
	        map.getProjectionObject()
	    ), 5);
	},
	unbind_: function () {
		zWatch.unlisten({onSize: this});
		this._map.destroy();
		this._map = null;
		this.$supers(openlayers.map.OLMaps,'unbind_', arguments);
	},
	firstLayer: function () {
		var wgt;
		if (zk.isLoaded('openlayers.layer')) {
			for (var w = this.firstChild; w; w = w.nextSibling)
				if (w.$instanceof(openlayers.layer.OLLayer)) {
					wgt = w;
					break;
				}
		}
		return wgt;
	},
	/**
	 * Returns the next layer.
	 * @return openlayers.layer.OLLayer
	 * @param zk.Widget wgt
	 */
	nextLayer: function (wgt) {
		if (wgt)
			while ((wgt = wgt.nextSibling)
			&& (!zk.isLoaded('openlayers.layer') || !wgt.$instanceof(openlayers.layer.OLLayer)))
				;
		return wgt;
	},
	layerIter: function () {
		return new openlayers.LayerIter(this);
	}
});

openlayers.LayerIter = zk.$extends(zk.Object, {
	$init: function (wgt, opts) {
		this.wgt = wgt;
		this.opts = opts;
	},
	_init: function () {
		if (!this._isInit) {
			this._isInit = true;
			var p = this.wgt.firstLayer();
			if(this.opts && this.opts.skipHidden)
				for (; p && !p.isVisible(); p = p.nextSibling) {}
			this.p = p;
		}
	},
	 /**
     * Returns <tt>true</tt> if the iteration has more elements
     * @return boolean
     */
	hasNext: function () {
		this._init();
		return this.p;
	},
	/**
     * Returns the next element in the iteration.
     *
     * @return openlayers.layer.OLLayer the next element in the iteration.
     */
	next: function () {
		this._init();
		var p = this.p,
			q = p ? p.parent.nextLayer(p) : null;
		if (this.opts && this.opts.skipHidden)
			for (; q && !q.isVisible(); q = q.parent.nextLayer(q)) {}
		if (p) 
			this.p = q;
		return p;
	}
});
})();