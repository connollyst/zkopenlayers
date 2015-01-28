package ctrl;

import static org.zkoss.openlayers.util.Helper.*;

import java.util.Arrays;
import java.util.Collections;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.Bounds;
import org.zkoss.openlayers.base.Size;
import org.zkoss.openlayers.control.Control;
import org.zkoss.openlayers.control.EditingToolbar;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.control.SelectFeature;
import org.zkoss.openlayers.layer.Image;
import org.zkoss.openlayers.layer.Vector;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

/**
 * Class for initiliazing and displaying image, together with annotations
 * 
 * @author Jakub Kudla (kubakudla)
 * 
 */
public class ImageComposer extends SelectorComposer<Window> {

	@Wire
	private Openlayers map;

	private Image image;

	private Vector annotations;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);

		initAnnotations();
		initImage();
		initMap();
	}

	private void initAnnotations() {

		annotations = new Vector("annotations");

		Control selectAnnotations = new SelectFeature(annotations);
		map.addControl(selectAnnotations);
		selectAnnotations.activate();

	}

	public void initImage() {
		Size size = new Size(500, 375);
		map.setWidth(String.valueOf(size.getWidth()) + "px");
		map.setHeight(String.valueOf(size.getHeight()) + "px");
		image = new Image("Image with annotations", Executions.getCurrent()
				.getContextPath() + "/data/beach.png", new Bounds(0, 0,
				size.getWidth(), size.getHeight()), size, null);
	}

	private void initMap() {

		map.setOptions(toMap(pair("controls", Collections.EMPTY_LIST)));
		map.addControl(new LayerSwitcher());
		map.addLayers(Arrays.asList(image, annotations));
		map.addControl(new EditingToolbar(annotations));
		map.zoomToMaxExtent();
	}

}
