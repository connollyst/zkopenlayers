package ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DemoWindowComposer extends GenericForwardComposer {
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
//	
//	public void onClick$myComp (ForwardEvent event) {
//		MouseEvent mouseEvent = (MouseEvent) event.getOrigin();
//		alert("You listen onClick: " + mouseEvent.getTarget());
//	}
}