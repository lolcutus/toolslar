package ro.cuzma.event;

public class TestCustomEvent implements CustomEventListener {
	public static void main(String[] args) {
		TestCustomEvent tst = new TestCustomEvent();
		CustomEventDispatcher.getCustomEventDispatcher().addNewListener(tst);
		CustomEvent.dispatchEvent(tst,"TYPE","VALUE");
	}

	public void handleCustomEvent(CustomEvent me) {
		System.out.println(me.getEventType() + "-" + me.getEventValue());
		
	}
}
