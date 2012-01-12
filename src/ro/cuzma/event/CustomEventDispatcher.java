package ro.cuzma.event;

import java.util.ArrayList;

public class CustomEventDispatcher {
	ArrayList								listeners	= new ArrayList();
	private static CustomEventDispatcher	unicDispatcher;

	public static CustomEventDispatcher getCustomEventDispatcher() {
		if (unicDispatcher == null)
			unicDispatcher = new CustomEventDispatcher();
		return unicDispatcher;
	}

	private CustomEventDispatcher() {
	}

	public void addNewListener(CustomEventListener ce) {
		listeners.add(ce);
	}

	public void dispatchMyEvent(CustomEvent me) { // maybe needs some args?
		for (int j = 0; j < listeners.size(); j++) {
			CustomEventListener mel = (CustomEventListener) listeners.get(j);
			if (mel != null) {
				//CustomEvent me = new CustomEvent(this);
				mel.handleCustomEvent(me);
			}
		}
	}
}
