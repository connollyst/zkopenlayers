package ctrl;

import static org.zkoss.openlayers.util.Helper.pair;
import static org.zkoss.openlayers.util.Helper.toMap;

import org.zkoss.openlayers.Openlayers;
import org.zkoss.openlayers.base.LonLat;
import org.zkoss.openlayers.base.Projection;
import org.zkoss.openlayers.control.LayerSwitcher;
import org.zkoss.openlayers.layer.GoogleV3;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ItemRenderer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class DemoWindowComposer extends SelectorComposer<Window> {
	@Wire
	private Openlayers myComp;
	@Wire
	private Button btn;
	@Wire
	private Selectbox types;
	@Wire
	private Textbox name;
	@Wire
	private Doublebox lon;
	@Wire
	private Doublebox lat;
	@Wire
	private Intbox zoom;
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		/**
		 * <olgoogle type="G_PHYSICAL_MAP" />
				<olgoogle numZoomLevels="20"/>
				<olgoogle type="G_HYBRID_MAP" numZoomLevels="20"/>
				<olgoogle type="G_SATELLITE_MAP" numZoomLevels="22"/>
		 */
//		Map a = new HashMap();
//		a.put("type", Google.Type.PHYSICAL);
//		Map b = new HashMap();
//		b.put("numZoomLevels", 20);
//		Map c = new HashMap();
//		c.put("type", Google.Type.HYBRID);
//		c.put("numZoomLevels", 20);
//		Map d= new HashMap();
//		d.put("type", Google.Type.SATELLITE);
//		d.put("numZoomLevels", 22);
//		myComp.addLayer(new Google(null, a));
//		myComp.addLayer(new Google(null, b));
//		myComp.addLayer(new Google(null, c));
//		myComp.addLayer(new Google(null, d));
		
//		myComp.addLayer(new GoogleV3("acb",  toMap(pair("type", GoogleV3.Type.TERRAIN))));
//		myComp.addLayer(new GoogleV3(toMap(pair("numZoomLevels", 20))));
//		myComp.addLayer(new GoogleV3(toMap(pair("type", GoogleV3.Type.HYBRID), pair("numZoomLevels", 20))));
//		myComp.addLayer(new GoogleV3(toMap(pair("type", GoogleV3.Type.SATELLITE), pair("numZoomLevels", 22))));
		myComp.addControl(new LayerSwitcher());
		

		ListModelList model = new ListModelList();
		model.add(new Object[]{"Google Streets", GoogleV3.Type.ROADMAP});
		model.add(new Object[]{"Google Satellite", GoogleV3.Type.SATELLITE});
		model.add(new Object[]{"Google Hybrid", GoogleV3.Type.HYBRID});
		model.add(new Object[]{"Google Physical", GoogleV3.Type.TERRAIN});
		model.addSelection(model.get(0));
		types.setModel(model);
		types.setItemRenderer(new ItemRenderer() {

			@Override
			public String render(Component owner, Object data, int index)
					throws Exception {
				// TODO Auto-generated method stub
				return (String)((Object[]) data)[0];
			}
			
		});
		
	}
	@Listen("onClick=#center")
	public void centerMap(Event evt) {
		if (lat.getValue() == null && lon.getValue() == null)
			myComp.setCenter(new LonLat(116, 25.9).transform(new Projection("EPSG:4326"), myComp.getProjection()), zoom.getValue(), false, false);
		else
			myComp.setCenter(new LonLat(lon.doubleValue(), lat.doubleValue()).transform(new Projection("EPSG:4326"), myComp.getProjection()), zoom.getValue(), false, false);
		//myComp.invalidate();
	}
	@Listen("onClick=#office")
	public void gotoOffice(Event evt) {
		myComp.setCenter(new LonLat(121.5272, 25.0495).transform(new Projection("EPSG:4326"), myComp.getProjection()), 20, false, true);
		
	}
	
	
	@Listen("onClick=#btn")
	public void addLayer(Event evt) {
		System.out.println("onClick ");
		myComp.addLayer(new GoogleV3(name.getValue(), toMap(pair("type", getLayerType()), pair("numZoomLevels", 20))));
	}
	
	private GoogleV3.Type getLayerType() {
		Object[] type = (Object[])types.getModel().getElementAt(types.getSelectedIndex());
		return (GoogleV3.Type) type[1];
	}
}