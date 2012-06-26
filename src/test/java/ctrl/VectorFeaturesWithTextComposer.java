/* VectorFeaturesWithTextComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jun 25, 2012 5:38:38 PM , Created by jumperchen
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
import org.zkoss.openlayers.base.StyleMap;
import org.zkoss.openlayers.feature.Feature;
import org.zkoss.openlayers.feature.Vector;
import org.zkoss.openlayers.geometry.Collection;
import org.zkoss.openlayers.geometry.LineString;
import org.zkoss.openlayers.geometry.LinearRing;
import org.zkoss.openlayers.geometry.Point;
import org.zkoss.openlayers.geometry.Polygon;
import org.zkoss.openlayers.layer.WMS;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * A Labeled Features demo
 * 
 * @author jumperchen
 * 
 */
public class VectorFeaturesWithTextComposer extends SelectorComposer<Window> {
	@Wire
	private Openlayers map;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		map.addLayer(new WMS("OpenLayers WMS",
				"http://vmap0.tiles.osgeo.org/wms/vmap0", toMap(pair("layers",
						"basic"))));

		Map layer_style = Vector.getStyle("default");

		org.zkoss.openlayers.layer.Vector vectorLayer = new org.zkoss.openlayers.layer.Vector(
				"Simple Geometry",
				toMap(pair(
						"styleMap",
						new StyleMap(
								toMap(pair(
										"default",
										toMap(pair("strokeColor", "#00FF00"),
												pair("strokeOpacity", 1),
												pair("strokeWidth", 3),
												pair("fillColor", "#FF5500"),
												pair("fillOpacity", 0.5),
												pair("pointRadius", 6),
												pair("pointerEvents",
														"visiblePainted"),
												// label with \n linebreaks
												pair("label",
														"name: ${name}\n\nage: ${age}"),
												pair("fontColor", "${favColor}"),
												pair("fontSize", "12px"),
												pair("fontFamily",
														"Courier New, monospace"),
												pair("fontWeight", "bold"),
												pair("labelAlign", "${align}"),
												pair("labelXOffset",
														"${xOffset}"),
												pair("labelYOffset",
														"${yOffset}")))))),
						pair("renderers",
								new Object[] { "SVG", "VML", "Canvas" })));

		Point point = new Point(-111.04, 45.68);
		Feature pointFeature = new Vector(point, toMap(pair("name", "toto"),
				pair("age", 20), pair("favColor", "red"), pair("align", "cm")));

		// create a polygon feature from a linear ring of points
		List<Point> pointList = new ArrayList<Point>();
		for (int p = 0; p < 6; ++p) {
			double a = p * (2 * Math.PI) / 7;
			double r = Math.random() + 1;
			Point newPoint = new Point(point.getX() + 5 + (r * Math.cos(a)),
					point.getY() + 5 + (r * Math.sin(a)));
			pointList.add(newPoint);
		}

		pointList.add(pointList.get(0));

		LinearRing linearRing = new LinearRing(pointList);
		Vector polygonFeature = new Vector(new Polygon(
				Arrays.asList(linearRing)), toMap(pair("name", "dude"),
				pair("age", 21), pair("favColor", "purple"),
				pair("align", "lb")));

		Vector multiFeature = new Vector(new Collection(Arrays.asList(
				new LineString(Arrays.asList(new Point(-105, 40.0), new Point(
						-95, 45.0))), new Point(-105, 40))), toMap(
				pair("name", "ball-and-chain"), pair("age", 30),
				pair("favColor", "black"), pair("align", "rt")));

		// Create a point feature to show the label offset options
		Point labelOffsetPoint = new Point(-101.04, 35.68);
		Vector labelOffsetFeature = new Vector(labelOffsetPoint, toMap(
				pair("name", "offset"), pair("age", 22),
				pair("favColor", "blue"), pair("align", "cm"),
				// positive value moves the label to the right
				pair("xOffset", 50),
				// negative value moves the label down
				pair("xOffset", -15)));
		Vector nullFeature = new Vector(null, toMap(
				pair("name", "toto is some text about the world"),
				pair("age", 20), pair("favColor", "red"), pair("align", "cm")));
		map.addLayer(vectorLayer);
		vectorLayer.drawFeature(multiFeature);
		map.setCenter(new LonLat(-109.370078125, 43.39484375), 4);
		vectorLayer.addFeatures(Arrays.asList(pointFeature, polygonFeature,
				multiFeature, labelOffsetFeature, nullFeature));
	}
}
