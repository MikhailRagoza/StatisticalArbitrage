package ru.ragoza.runmanager;

import ru.ragoza.instruments.Instrument;
import ru.ragoza.serverside.Connection;
import ru.ragoza.service.Message;

public class Run {
	protected static boolean isRun = true;
	
	/*public static void main(String args[]) {
		Connection newCon = new Connection(8000);
		while (!newCon.getClientSocket().isClosed()) {
			String mes;
			while ((mes = newCon.getMessage()) != null) {
				System.out.println(mes);
			}
			newCon.closeConnection();
		}
		
	}
	*/
	
	
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
