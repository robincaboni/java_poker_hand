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
		PokerHand hand5 = new PokerHand("Tc 9d 5s Ac Tc");
		PokerHand hand6 = new PokerHand("Jc Qd As Kc 9c");
		PokerHand hand7 = new PokerHand("Tc Td Ts Ac Tc");
		PokerHand hand8 = new PokerHand("Tc Td Ts Kc Tc");

		assertEquals(Result.WIN, hand1.compareWith(hand2));
		assertEquals(Result.LOSS, hand2.compareWith(hand1));
		assertEquals(Result.WIN, hand4.compareWith(hand3));
		assertEquals(Result.WIN, hand5.compareWith(hand6));
		assertEquals(Result.WIN, hand7.compareWith(hand8));
	}
}
