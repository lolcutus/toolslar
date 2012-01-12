package ro.cuzma.event;

public class CustomEvent extends java.util.EventObject {
	private String eventType;
	private String eventValue;
	public String getEventType() {
		return eventType;
	}
	public String getEventValue() {
		return eventValue;
	}
	// field(s) here
	private CustomEvent(Object source,String eventType,String eventValue) {
		super(source);
		this.eventType = eventType;
		this.eventValue = eventValue;
		CustomEventDispatcher.getCustomEventDispatcher().dispatchMyEvent(this);
	}
	
	public static void dispatchEvent(Object source,String eventType,String eventValue){
		new CustomEvent(source,eventType,eventValue);
	}
}
