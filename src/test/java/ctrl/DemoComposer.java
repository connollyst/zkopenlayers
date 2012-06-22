package ctrl;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.ext.Selectable;

public class DemoComposer extends SelectorComposer<Borderlayout> {
	@Wire
	private Listbox listdemo;

	@Wire
	private Iframe xcontents;

	public void doAfterCompose(Borderlayout comp) throws Exception {
		super.doAfterCompose(comp);

		final String curPath = comp.getDesktop().getWebApp()
				.getServletContext().getRealPath("/example");
		File root = new File(curPath);
		File[] zuls = root.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.isFile() && f.getName().endsWith(".zul");
			}
		});
		Arrays.sort(zuls, new java.util.Comparator<File>() {
			public int compare(File a, File b) {
				String aname = a.getName();
				String bname = b.getName();
				return aname.compareTo(bname);
			}
		});

		ListModel listmodel = new ListModelList(zuls);
		ListitemRenderer render = new ListitemRenderer() {
			public void render(Listitem item, final Object data, int index) {
				new Listcell(((File) data).getName()).setParent(item);
				item.addEventListener("onClick",
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event event) throws Exception {
								if ("google.zul".equals(((File) data).getName())
										&& (!"8080".equals(Executions
												.getCurrent().getServerPort()) || !"localhost"
												.equals(Executions.getCurrent()
														.getServerName()))) {
									Messagebox
											.show("Please change your server to localhost:8080 for Google Map V2 version");
								} else {
									xcontents.setSrc("/example/"
											+ ((File) data).getName());
								}
							}
						});
			}
		};
		((Selectable) listmodel).addToSelection(listmodel.getElementAt(0));
		listdemo.setModel(listmodel);
		listdemo.setItemRenderer(render);
	}
}