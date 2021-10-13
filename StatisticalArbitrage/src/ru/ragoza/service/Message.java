package ru.ragoza.service;

import ru.ragoza.instruments.Instrument;
import ru.statarb.statistics.EasyMath;

public class Message {
	private String type; // received or to send !remake to enum
	private String content;
	//received
	private double MsgOffer;
	private String MsgSecCode; 
	private boolean onclose = false;
	
	public Message(String type, String content) {
		this.content = content;
		this.type = type;
		if(type.equals("r")) {
			if (content!="close" && getMessageLength()>=2) /* regex*/ {
				MsgSecCode = content.split(" ")[0];
				MsgOffer = Double.valueOf(content.split(" ")[1]);// regex
			} else {
				onclose = true;
			}
		}
		if(type.equals("s")) {
			this.content = content;
			this.type = type;
		}
		
	}
	public static String conditionMessage(Instrument first, Instrument second) {
		StringBuilder builder = new StringBuilder();
		builder.append("Instrument: " + first.getSecCode()+"   "+
		"Offer Price: " + first.getOffer() + "\n");
		builder.append("Instrument: " + second.getSecCode()+"   "+
		"Offer Price: " + second.getOffer() + "\n");
		try {
			builder.append(first.getSecCode() + "/" + second.getSecCode() + " Ratio is:    " +
			EasyMath.getRatio(first, second) + "\n");
		} catch (Exception e) {
			return e.getMessage();
		}
		return builder.toString();
		
	}
	public void processMsg() {
		
	}
	public String getType() {
		return type;
	}
	public String getContent() {
		return content;
	}
	public double getMsgOffer() {
		return MsgOffer;
	}
	public String getMsgSecCode() {
		return MsgSecCode;
	}
	public boolean isOnclose() {
		return onclose;
	}
	public int getMessageLength() {
		return getContent().split(" ").length;
	}
	public static int getMessageLength(Message newMessage) {
		return newMessage.getContent().split(" ").length;
	}
	
	
	
}
