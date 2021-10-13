package ru.statarb.statistics;

import ru.ragoza.instruments.Instrument;

public class EasyMath {

	public static double getRatio(Instrument first, Instrument second) throws WrongValueException{
		if((first.getOffer() == 0) || (second.getOffer() == 0)) {
			throw new WrongValueException("Waiting for price initialization");
		}
		return first.getOffer()/second.getOffer();
	}
}
