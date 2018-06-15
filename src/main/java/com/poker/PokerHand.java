package com.poker;

import java.util.Arrays;

public class PokerHand {
	
	private String[] cards = new String[5];
	private Card[] cardsObj = new Card[5];

	private int bestHandRankValue = 0;
	//for 2pairs, fullhouse
	private int secondBestHandRankValue = 0;
	private int[] leftOverCards = new int[5];
	
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

	public PokerHand(String hand) throws Exception {
		boolean hasNoCard = (hand == null);
		boolean hasMoreOrLessThan14Characters = (hand.length() > 14);
		if(hasNoCard)
		    throw new NullPointerException("Your hand is empty");

		if(hasMoreOrLessThan14Characters)
			throw new NullPointerException("Your hand should indicate 5 cards with a "
					+ "number/letter for the card's number and a letter for the type of the card");

		this.cards = hand.split(" ");
		for (int j = 0; j < cards.length; j++)
		{
			this.cardsObj[j] = new Card();
			this.cardsObj[j].setValueCard(cards[j].charAt(0));
			this.cardsObj[j].setTypeCard(cards[j].charAt(1));
		}
		
		Arrays.sort(this.cardsObj);

		// initialize
		this.bestHandRankValue = 0;
		//for 2pairs, fullhouse
		this.secondBestHandRankValue = 0;
		this.leftOverCards = new int[]{0, 0, 0, 0, 0};
	}

	
	public int getBestHandRankValue() {
		return bestHandRankValue;
	}


	public void setBestHandRankValue(int bestHandRankValue) {
		this.bestHandRankValue = bestHandRankValue;
	}


	public int getSecondBestHandRankValue() {
		return secondBestHandRankValue;
	}


	public void setSecondBestHandRankValue(int secondBestHandRankValue) {
		this.secondBestHandRankValue = secondBestHandRankValue;
	}


	 public int[] getLeftOverCards() {
		return leftOverCards;
	 }

	public void addLeftOverCardIndex(PokerHand hand, int leftOverIndex, int handIndex) {
		this.leftOverCards[leftOverIndex] = hand.cardsObj[handIndex].getRank();
	}

	public boolean isRoyalFlush(PokerHand hand) {
		return (hand.cardsObj[4].getValueCard() == 'A') && isStraightFlush(hand);
	}	

	
	public boolean isStraightFlush(PokerHand hand){
		return isFlush(hand) && isStraight(hand);
	}
	

	public boolean isFourOfAKind(PokerHand hand){
		boolean isFour = false;
		if(hand.cardsObj[0].getValueCard() == hand.cardsObj[3].getValueCard()) {
			hand.setBestHandRankValue(hand.cardsObj[0].getRank());
			hand.addLeftOverCardIndex(hand, 0, 4);
			isFour = true;
		}
		else if (hand.cardsObj[1].getValueCard() == hand.cardsObj[4].getValueCard()){
			hand.setBestHandRankValue(hand.cardsObj[1].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			isFour = true;
		}
		return isFour;
	}	
	
	
	public boolean isFullHouse(PokerHand hand){
		int card1 = hand.cardsObj[0].getValueCard();
		int card2 = hand.cardsObj[1].getValueCard();
		int card3 = hand.cardsObj[2].getValueCard();
		int card4 = hand.cardsObj[3].getValueCard();
		int card5 = hand.cardsObj[4].getValueCard();
		boolean isFull = false;
		
		if((card1 == card2 && card2 != card3 && card3 == card5)) {
			hand.setBestHandRankValue(hand.cardsObj[4].getRank());
			hand.setSecondBestHandRankValue(hand.cardsObj[0].getRank());
			isFull = true;
		} else if ((card1 == card3 && card3 != card4 && card4 == card5)) {
			hand.setBestHandRankValue(hand.cardsObj[0].getRank());
			hand.setSecondBestHandRankValue(hand.cardsObj[4].getRank());
			isFull = true;			
		}
		return isFull;
	}
	
	
	public boolean isFlush(PokerHand hand) {
	    for (int i=0; i<hand.cardsObj.length -1; i++) {
	        if (hand.cardsObj[i+1].getTypeCard() != hand.cardsObj[i].getTypeCard()) {
	        	return false;
	        }
	    }
	    return true;
	}
	
	
	public boolean isStraight(PokerHand hand) {
	    int cardsInARowCount = 0;
	    for (int i=0; i<hand.cardsObj.length - 1; i++){
	    	boolean gapBetweenCardsIsEqualOne = (hand.cardsObj[i+1].getRank() - hand.cardsObj[i].getRank() == 1);
	    	if (gapBetweenCardsIsEqualOne){
	    		cardsInARowCount++;
	    		if (cardsInARowCount == 4){
	    			return true;
	    		}
	    	} else {
	    		cardsInARowCount = 0;
	    	} 	
	    }
	    return false;
	}	


	public boolean isThreeOfAKind(PokerHand hand){
		int card1 = hand.cardsObj[0].getValueCard();
		int card2 = hand.cardsObj[1].getValueCard();
		int card3 = hand.cardsObj[2].getValueCard();
		int card4 = hand.cardsObj[3].getValueCard();
		int card5 = hand.cardsObj[4].getValueCard();
		boolean isThree = false;
		
		if(card1 == card3) {
			hand.setBestHandRankValue(hand.cardsObj[0].getRank());
			hand.addLeftOverCardIndex(hand, 0, 3);
			hand.addLeftOverCardIndex(hand, 1, 4);
			isThree = true;
		} else if (card2 == card4){
			hand.setBestHandRankValue(hand.cardsObj[1].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			hand.addLeftOverCardIndex(hand, 1, 4);
			isThree = true;
		} else if (card3 == card5){
			hand.setBestHandRankValue(hand.cardsObj[2].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			hand.addLeftOverCardIndex(hand, 1, 1);
			isThree = true;
		}
		return isThree;
	}
	

	public boolean isTwoPair(PokerHand hand) {
		int card1 = hand.cardsObj[0].getValueCard();
		int card2 = hand.cardsObj[1].getValueCard();
		int card3 = hand.cardsObj[2].getValueCard();
		int card4 = hand.cardsObj[3].getValueCard();
		int card5 = hand.cardsObj[4].getValueCard();
		boolean isTwo = false;
		
		if((card1 == card2 && card3 == card4 && card2!=card3 && card4 != card5)) {
			hand.setBestHandRankValue(hand.cardsObj[2].getRank());
			hand.setSecondBestHandRankValue(hand.cardsObj[0].getRank());
			hand.addLeftOverCardIndex(hand, 0, 4);
			isTwo = true;
		} else if ((card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5)){
			hand.setBestHandRankValue(hand.cardsObj[3].getRank());
			hand.setSecondBestHandRankValue(hand.cardsObj[1].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			isTwo = true;
		} 
		return isTwo;
	}


	public boolean isPair(PokerHand hand){
		int card1 = hand.cardsObj[0].getValueCard();
		int card2 = hand.cardsObj[1].getValueCard();
		int card3 = hand.cardsObj[2].getValueCard();
		int card4 = hand.cardsObj[3].getValueCard();
		int card5 = hand.cardsObj[4].getValueCard();
		boolean isOne = false;
		
		if((card1 == card2 && card2 != card3)) {
			hand.setBestHandRankValue(hand.cardsObj[0].getRank());
			hand.addLeftOverCardIndex(hand, 0, 2);
			hand.addLeftOverCardIndex(hand, 1, 3);
			hand.addLeftOverCardIndex(hand, 2, 4);
			isOne = true;
		} else if ((card2 == card3 && card3 != card4 && card2 != card1)){
			hand.setBestHandRankValue(hand.cardsObj[1].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			hand.addLeftOverCardIndex(hand, 1, 3);
			hand.addLeftOverCardIndex(hand, 2, 4);
			isOne = true;
		} else if ((card3 == card4 && card4 != card5 && card3 != card2)){
			hand.setBestHandRankValue(hand.cardsObj[2].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			hand.addLeftOverCardIndex(hand, 1, 1);
			hand.addLeftOverCardIndex(hand, 2, 4);
			isOne = true;
		} else if ((card4 == card5 && card4 != card3)){
			hand.setBestHandRankValue(hand.cardsObj[3].getRank());
			hand.addLeftOverCardIndex(hand, 0, 0);
			hand.addLeftOverCardIndex(hand, 1, 1);
			hand.addLeftOverCardIndex(hand, 2, 2);
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
		for (int i=hand.cardsObj.length-1 ; i >= 0; i--) {
			if (this.cardsObj[i].getRank() > hand.cardsObj[i].getRank()) {
				return Result.WIN;
			}
			if (this.cardsObj[i].getRank() < hand.cardsObj[i].getRank()) {
				return Result.LOSS;
			} 
		}
		return Result.TIE;
	}

	public Result compareInADraw(PokerHand hand) {

		boolean hasBestRankGreaterThanOtherHandBestRank = this.getBestHandRankValue() > hand.getBestHandRankValue();
		boolean hasBestRankLowerThanOtherHandBestRank = this.getBestHandRankValue() < hand.getBestHandRankValue();
		boolean hasSecondBestRankGreaterThanOtherHandSecondBestRank = this.getSecondBestHandRankValue() > hand.getSecondBestHandRankValue();
		boolean hasSecondBestRankLowerThanOtherHandSecondBestRank = this.getSecondBestHandRankValue() < hand.getSecondBestHandRankValue();

		if (hasBestRankGreaterThanOtherHandBestRank) {
			return Result.WIN;
		} else if (hasBestRankLowerThanOtherHandBestRank) {
			return Result.LOSS;
		} else if (hasSecondBestRankGreaterThanOtherHandSecondBestRank) {
			return Result.WIN;
		} else if (hasSecondBestRankLowerThanOtherHandSecondBestRank) {
			return Result.LOSS;
		}
		else {
			for (int i=this.getLeftOverCards().length-1; i >= 0  ; i--) {
				if (this.getLeftOverCards()[i] > hand.getLeftOverCards()[i]) {
					return Result.WIN;
				} else if (this.getLeftOverCards()[i] < hand.getLeftOverCards()[i]) {
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