package ru.statarb.runmanager;

import ru.statarb.instruments.Instrument;
import ru.statarb.serverside.Connection;
import ru.statarb.service.Message;

public class Run {
	protected static boolean isRun = true; // IsRun
	
	public static void main(String args[]) {
		Instrument firstInstr = new Instrument("MTLRP");// add seccode
		Instrument secondInstr = new Instrument("MTLR");// add seccode
		while (isRun) {
			Connection newConnnection = new Connection(8000);
			Message newReceivedMessage;
			try {
				while ((newReceivedMessage = 
						new Message("r", newConnnection.getMessage())).getContent() != "close") { // remake this
					Instrument.setFromMessage(firstInstr, secondInstr, newReceivedMessage);
					System.out.println(Message.conditionMessage(firstInstr, secondInstr)); 
				}
			} catch (NullPointerException e) {
				newConnnection.closeConnection();
				isRun = false;
				System.out.println("Lost connection from Lua, please restart");
				break;
			}
			newConnnection.closeConnection();
		}
	}
	
	
	
	
	
	

}
