package com.poker;

public class Card implements Comparable{

	private int rank;
	private char value_card;
	private char type_card;
	
	public Card() {
		// TODO Auto-generated constructor stub
	}
	
	public Card(char val, char type){
		rank = calcRank(val);
		value_card = val;
		type_card = type;
	}

	private int calcRank(char value_card2) {
		switch(value_card2){
		case '1': 
			return 1;
		case '2': 
			return 2;
		case '3': 
			return 3;
		case '4': 
			return 4;
		case '5': 
			return 5;
		case '6': 
			return 6;
		case '7': 
			return 7;
		case '8': 
			return 8;
		case '9': 
			return 9;
		case 'T': 
			return 10;
		case 'J': 
			return 11;
		case 'Q': 
			return 12;
		case 'K': 
			return 13;
		case 'A': 
			return 14;
		default:
			return 0;
		}
	}

	public int getRank(){
		return rank;
	}
	
	public char getValueCard(){
		return value_card;
	}
	
	public char getTypeCard(){
		return type_card;
	}

	
	 @Override
	    public int compareTo(Object o) {
	         return this.getRank() - (((Card) o).getRank());
	    }
	
}
