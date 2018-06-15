package com.poker;

import java.util.Arrays;

public class PokerHand {
	
	private int count_word = 1;
	private String[] cards = new String[5];
	private char[] cards_num = new char[5];
	private char[] cards_type = new char[5];
	private Card[] cards_obj = new Card[5];
	private int valueOfRank = 0;
	//for 2pairs, fullhouse
	private int valueOfRank2 = 0;
	private int[] tabWithoutHandRank = new int[5];
	
	public enum Result {
		WIN,
		LOSS,
		TIE;
	}
	
	public enum handRank{
		HIGHCARD, 
		PAIR, 
		TWOPAIR, 
		THREEOFAKIND, 
		STRAIGHT, 
		FLUSH,
		FULLHOUSE, 
		FOUROFAKIND, 
		STRAIGHTFLUSH,
		ROYALFLUSH
	}

	public PokerHand(String hand) throws Exception{		
		if(hand == null) 
		    throw new NullPointerException("Your hand is empty");
		
		if(hand.length() > 14)
			throw new NullPointerException("Your hand should indicate 5 cards with a "
					+ "number/letter for the card's number and a letter for the type of the card");
		
		for (int i = 0; i < hand.length() - 1; i++)
		{
			if ((hand.charAt(i) == ' ') && (hand.charAt(i + 1) != ' '))
			{
				count_word++;
			}
		}
		
		if (count_word != 5)
		       throw new Exception("A Poker hand should have 5 cards");
 		
		this.cards = hand.split(" ");
		
		for (int j = 0; j < cards.length; j++)
		{
			this.cards_num[j] = cards[j].charAt(0);
			this.cards_type[j] = cards[j].charAt(1);
			this.cards_obj[j] = new Card(cards_num[j],cards_type[j]);
		}
		Arrays.sort(this.cards_obj);
		
		this.valueOfRank = 0;
		//for 2pairs, fullhouse
		this.valueOfRank2 = 0;
		this.tabWithoutHandRank = new int[]{0, 0, 0, 0, 0};
	}

	
	public int getValueOfRank() {
		return valueOfRank;
	}


	public void setValueOfRank(int valueOfRank) {
		this.valueOfRank = valueOfRank;
	}


	public int getValueOfRank2() {
		return valueOfRank2;
	}


	public void setValueOfRank2(int valueOfRank2) {
		this.valueOfRank2 = valueOfRank2;
	}


	public int[] getTabWithoutHandRank() {
		return tabWithoutHandRank;
	}


	public void setTabWithoutHandRank(int[] tabWithoutHandRank) {
		this.tabWithoutHandRank = tabWithoutHandRank;
	}


	public boolean isRoyalFlush(PokerHand hand) {
		return (hand.cards_obj[4].getValueCard() == 'A') && isStraightFlush(hand);
	}	

	
	public boolean isStraightFlush(PokerHand hand){
		return isFlush(hand) && isStraight(hand);
	}
	

	public boolean isFourOfAKind(PokerHand hand){
		boolean isFour = false;
		if(hand.cards_obj[0].getValueCard() == hand.cards_obj[3].getValueCard()) {
			hand.setValueOfRank(hand.cards_obj[0].getRank());
			int[] tabForHighestCards = new int[1]; 
			tabForHighestCards[0] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isFour = true;
		}
		else if (hand.cards_obj[1].getValueCard() == hand.cards_obj[4].getValueCard()){
			hand.setValueOfRank(hand.cards_obj[1].getRank());
			int[] tabForHighestCards = new int[1]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isFour = true;
		}
		return isFour;
	}	
	
	
	public boolean isFullHouse(PokerHand hand){
		int card1 = hand.cards_obj[0].getValueCard();
		int card2 = hand.cards_obj[1].getValueCard();
		int card3 = hand.cards_obj[2].getValueCard();
		int card4 = hand.cards_obj[3].getValueCard();
		int card5 = hand.cards_obj[4].getValueCard();
		boolean isFull = false;
		
		if((card1 == card2 && card2 != card3 && card3 == card5)) {
			hand.setValueOfRank(hand.cards_obj[4].getRank());
			hand.setValueOfRank2(hand.cards_obj[0].getRank());
			isFull = true;
		} else if ((card1 == card3 && card3 != card4 && card4 == card5)) {
			hand.setValueOfRank(hand.cards_obj[0].getRank());
			hand.setValueOfRank2(hand.cards_obj[4].getRank());
			isFull = true;			
		}
		return isFull;
	}
	
	
	public boolean isFlush(PokerHand hand) { 	
	    for (int i=0; i<hand.cards_obj.length -1; i++) {
	        if (hand.cards_obj[i+1].getTypeCard() != hand.cards_obj[i].getTypeCard()){
	        	return false;
	        }
	    }
	    return true;
	}
	
	
	public boolean isStraight(PokerHand hand) {
	    int cardsInARowCount = 0;
	    for (int i=0; i<hand.cards_obj.length - 1; i++){
	    	if (hand.cards_obj[i+1].getRank() - hand.cards_obj[i].getRank() == 1){
	    		cardsInARowCount++;
	    		if (cardsInARowCount == 4){
	    			return true;
	    		}
	    	}else{
	    		cardsInARowCount = 0;
	    	} 	
	    }
	    return false;
	}	


	public boolean isThreeOfAKind(PokerHand hand){
		int card1 = hand.cards_obj[0].getValueCard();
		int card2 = hand.cards_obj[1].getValueCard();
		int card3 = hand.cards_obj[2].getValueCard();
		int card4 = hand.cards_obj[3].getValueCard();
		int card5 = hand.cards_obj[4].getValueCard();
		boolean isThree = false;
		
		if(card1 == card3) {
			hand.setValueOfRank(hand.cards_obj[0].getRank());
			int[] tabForHighestCards = new int[2]; 
			tabForHighestCards[0] = hand.cards_obj[3].getRank();
			tabForHighestCards[1] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isThree = true;
		} else if (card2 == card4){
			hand.setValueOfRank(hand.cards_obj[1].getRank());
			int[] tabForHighestCards = new int[2]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			tabForHighestCards[1] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isThree = true;
		} else if (card3 == card5){
			hand.setValueOfRank(hand.cards_obj[2].getRank());
			int[] tabForHighestCards = new int[2]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			tabForHighestCards[1] = hand.cards_obj[1].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isThree = true;
		}
		return isThree;
	}
	

	public boolean isTwoPair(PokerHand hand){
		int card1 = hand.cards_obj[0].getValueCard();
		int card2 = hand.cards_obj[1].getValueCard();
		int card3 = hand.cards_obj[2].getValueCard();
		int card4 = hand.cards_obj[3].getValueCard();
		int card5 = hand.cards_obj[4].getValueCard();
		boolean isTwo = false;
		
		if((card1 == card2 && card3 == card4 && card2!=card3 && card4 != card5)) {
			hand.setValueOfRank(hand.cards_obj[2].getRank());
			hand.setValueOfRank2(hand.cards_obj[0].getRank());
			int[] tabForHighestCards = new int[1]; 
			tabForHighestCards[0] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isTwo = true;
		} else if ((card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5)){
			hand.setValueOfRank(hand.cards_obj[3].getRank());
			hand.setValueOfRank2(hand.cards_obj[1].getRank());
			int[] tabForHighestCards = new int[1]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isTwo = true;
		} 
		return isTwo;
	}


	public boolean isPair(PokerHand hand){
		int card1 = hand.cards_obj[0].getValueCard();
		int card2 = hand.cards_obj[1].getValueCard();
		int card3 = hand.cards_obj[2].getValueCard();
		int card4 = hand.cards_obj[3].getValueCard();
		int card5 = hand.cards_obj[4].getValueCard();
		boolean isOne = false;
		
		if((card1 == card2 && card2 != card3)) {
			hand.setValueOfRank(hand.cards_obj[0].getRank());
			int[] tabForHighestCards = new int[3]; 
			tabForHighestCards[0] = hand.cards_obj[2].getRank();
			tabForHighestCards[1] = hand.cards_obj[3].getRank();
			tabForHighestCards[2] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isOne = true;
		} else if ((card2 == card3 && card3 != card4 && card2 != card1)){
			hand.setValueOfRank(hand.cards_obj[1].getRank());
			int[] tabForHighestCards = new int[3]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			tabForHighestCards[1] = hand.cards_obj[3].getRank();
			tabForHighestCards[2] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isOne = true;
		} else if ((card3 == card4 && card4 != card5 && card3 != card2)){
			hand.setValueOfRank(hand.cards_obj[2].getRank());
			int[] tabForHighestCards = new int[3]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			tabForHighestCards[1] = hand.cards_obj[1].getRank();
			tabForHighestCards[2] = hand.cards_obj[4].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isOne = true;
		} else if ((card4 == card5 && card4 != card3)){
			hand.setValueOfRank(hand.cards_obj[3].getRank());
			int[] tabForHighestCards = new int[3]; 
			tabForHighestCards[0] = hand.cards_obj[0].getRank();
			tabForHighestCards[1] = hand.cards_obj[1].getRank();
			tabForHighestCards[2] = hand.cards_obj[2].getRank();
			hand.setTabWithoutHandRank(tabForHighestCards);
			isOne = true;
		} 
		return isOne;
	}
	
	
	public handRank getHandRank(){
		if (isRoyalFlush(this)) return handRank.STRAIGHTFLUSH;
		else if (isStraightFlush(this)) return handRank.STRAIGHTFLUSH;
		else if (isFourOfAKind(this)) return handRank.FOUROFAKIND;
		else if (isFullHouse(this)) return handRank.FULLHOUSE;
		else if (isFlush(this)) return handRank.FLUSH;
		else if (isStraight(this)) return handRank.STRAIGHT;
		else if (isThreeOfAKind(this)) return handRank.THREEOFAKIND;
		else if (isTwoPair(this)) return handRank.TWOPAIR;
		else if (isPair(this)) return handRank.PAIR;
		else return handRank.HIGHCARD;
	}
	
	public Result compareHighestRank(PokerHand hand) {
		for (int i=hand.cards_obj.length-1 ; i >= 0; i--) {
			if (this.cards_obj[i].getRank() > hand.cards_obj[i].getRank()) {
				return Result.WIN;
			}
			if (this.cards_obj[i].getRank() < hand.cards_obj[i].getRank()) {
				return Result.LOSS;
			}
		}
		return Result.TIE;
	}

	public Result compareInADraw(PokerHand hand) {
		if (this.getValueOfRank() > hand.getValueOfRank()) {
			return Result.WIN;
		} else if (this.getValueOfRank() < hand.getValueOfRank()) {
			return Result.LOSS;
		} else if (this.getValueOfRank2() < hand.getValueOfRank2()) {
			return Result.LOSS;
		} else if (this.getValueOfRank2() < hand.getValueOfRank2()) {
			return Result.LOSS;
		}
		else {
			for (int i=this.getTabWithoutHandRank().length-1; i >= 0  ; i--) {
				if (this.getTabWithoutHandRank()[i] > hand.getTabWithoutHandRank()[i]) {
					return Result.WIN;
				} else if (this.getTabWithoutHandRank()[i] < hand.getTabWithoutHandRank()[i]) {
					return Result.LOSS;
				}
			}
		}
		return Result.TIE;
	}
	
	public Result compareWith(PokerHand hand) {
		int compare = (this.getHandRank()).compareTo(hand.getHandRank());
		Result res = Result.TIE;
		if(compare > 1)
			return Result.WIN;
		else if(compare < 0)
			return Result.LOSS;
		else {
			switch(this.getHandRank()) {
			case STRAIGHTFLUSH:
				res = this.compareHighestRank(hand);
				break;
			case FOUROFAKIND:
				res = this.compareInADraw(hand);
				break;
			case FULLHOUSE:
				res = this.compareInADraw(hand);
				break;
			case FLUSH:
				res = this.compareHighestRank(hand);
				break;
			case STRAIGHT:
				res = this.compareHighestRank(hand);
				break;
			case THREEOFAKIND:
				res = this.compareInADraw(hand);
				break;
			case TWOPAIR:
				res = this.compareInADraw(hand);
				break;
			case PAIR:
				res = this.compareInADraw(hand);
				break;
			case HIGHCARD:
				res = this.compareHighestRank(hand);
				break;
			default:
				break;
			}
		}
		return res;
	}
}