package com.poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.poker.PokerHand.Result;

/**
 * Unit test for simple App.
 */
public class PokerHandTest {
	
	@Test (expected = Exception.class)
	public void newHandTestNull() throws Exception {
		new PokerHand(null);
	}
	
	@Test
	public void highCardWin() throws Exception {
		PokerHand hand1 = new PokerHand("Ks Kh Kc Qd Td");
		PokerHand hand2 = new PokerHand("Kc Ks Kh Jh Tc");
		PokerHand hand3 = new PokerHand("2c 2c 1s Kh 6c");
		PokerHand hand4 = new PokerHand("7c 3d 2s Kc 2c");

		assertEquals(Result.WIN, hand1.compareWith(hand2));
		assertEquals(Result.LOSS, hand2.compareWith(hand1));
		assertEquals(Result.WIN, hand4.compareWith(hand3));
	}
}
