/* VectorFeaturesComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 23, 2012 9:31:55 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;
import static org.zkoss.openlayers.util.Helper.mergeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.feature.Vector;
import org.zkoss.openlayers.geometry.LineString;
import org.zkoss.openlayers.geometry.LinearRing;
import org.zkoss.openlayers.geometry.Point;
import org.zkoss.openlayers.geometry.Polygon;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Vector Features Demo
 * @author jumperchen
 */
public class VectorFeaturesComposer extends SelectorComposer<Window> {
	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new WMS("OpenLayers WMS",
				"http://vmap0.tiles.osgeo.org/wms/vmap0", toMap(pair("layers",
						"basic"))));
		Map layer_style = Vector.getStyle("default");
		layer_style = mergeMap(layer_style, pair("fillOpacity", 0.2),
				pair("graphicOpacity", 1));

		/*
		 * Blue style
		 */
		Map style_blue = mergeMap(layer_style, pair("strokeColor", "blue"),
				pair("fillColor", "blue"), pair("graphicName", "star"),
				pair("pointRadius", 10), pair("strokeWidth", 3),
				pair("rotation", 45), pair("strokeLinecap", "butt"));

		/*
		 * Green style
		 */
		Map style_green = toMap(pair("strokeColor", "#00FF00"),
				pair("strokeWidth", 3), pair("strokeDashstyle", "dashdot"),
				pair("pointRadius", 6), pair("pointerEvents", "visiblePainted"));

		/*
		 * Mark style
		 */
		Map style_mark = mergeMap(Vector.getStyle("default"),
				pair("graphicWidth", 24), pair("graphicHeight", 20),
				pair("graphicXOffset", 10),
				// default is -(style_mark.graphicWidth/2);
				pair("graphicYOffset", -20),
				pair("externalGraphic", Executions.getCurrent().getContextPath() + "/img/marker.png"),
				// graphicTitle only works in Firefox and Internet Explorer
				pair("graphicTitle", "this is a test tooltip"));

		org.zkoss.openlayers.layer.Vector vectorLayer = new org.zkoss.openlayers.layer.Vector(
				"Simple Geometry", toMap(
						pair("style", layer_style),
						pair("renderers",
								new Object[] { "SVG", "VML", "Canvas" })));

		// create a point feature
		Point point = new Point(-111.04, 45.68);
		Vector pointFeature = new Vector(point, null, style_blue);
		Point point2 = new Point(-105.04, 49.68);
		Vector pointFeature2 = new Vector(point2, null, style_green);
		Point point3 = new Point(-105.04, 49.68);
		Vector pointFeature3 = new Vector(point3, null, style_mark);

		// create a line feature from a list of points
		List<Point> pointList = new ArrayList<Point>();
		Point newPoint = point;
		for (int p = 0; p < 15; ++p) {
			newPoint = new Point(newPoint.getX() + Math.random(),
					newPoint.getY() + Math.random());
			pointList.add(newPoint);
		}

		Vector lineFeature = new Vector(new LineString(pointList), null,
				style_green);

		// create a polygon feature from a linear ring of points
		List<Point> point2List = new ArrayList<Point>();
		for (int p = 0; p < 6; ++p) {
			double a = p * (2 * Math.PI) / 7;
			double r = Math.random() + 1;
			Point newPoint1 = new Point(point.getX() + (r * Math.cos(a)),
					point.getY() + (r * Math.sin(a)));
			point2List.add(newPoint1);
		}
		point2List.add(point2List.get(0));

		LinearRing linearRing = new LinearRing(point2List);
		List<LinearRing> ringList = new ArrayList<LinearRing>();
		ringList.add(linearRing);
		Vector polygonFeature = new Vector(new Polygon(ringList));

		map.addLayer(vectorLayer);
		map.setCenter(new LonLat(point.getX(), point.getY()), 5);
		vectorLayer.addFeatures(Arrays.asList(pointFeature, pointFeature3,
				pointFeature2, lineFeature, polygonFeature));
	}
}
