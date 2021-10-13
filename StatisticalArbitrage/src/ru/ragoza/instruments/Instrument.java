package ru.ragoza.instruments;

import ru.ragoza.service.Message;

public class Instrument {
	private double offer;
	private String secCode;
	
	public Instrument(String secCode) {
		this.secCode = secCode;
	}
	public double getOffer() {
		return offer;
	}

	public String getSecCode() {
		return secCode;
	}
	
	public static void setFromMessage(Instrument first, Instrument second, Message newMessage) { // add another msg exception!
		if (newMessage.getMsgSecCode().equals(first.secCode)) {
			first.offer = newMessage.getMsgOffer();
		} else if(newMessage.getMsgSecCode().equals(second.secCode)) {
			second.offer = newMessage.getMsgOffer();
		}
		
		
		
		
	}
	
	
}
