# Java Project : poker hand calculator

## Introduction

> Tool which returns what's the best hand between two players

## Code Samples

A poker hand has a constructor that accepts a string containing 5 cards:

```
PokerHand hand = new PokerHand("KS 2H 5C JD TD");
```

and a method to compare itself to another hand

```
public enum Result {
	WIN,
	LOSS,
	TIE;
}

public Result compareWith(PokerHand hand) {
	/*
	 * Your code here
	 */
	 return Result.TIE;
}
```

The characteristics of the string of cards are:
*   A space is used as card seperator
*   Each card consists of two characters
*   The first character is the value of the card, valid characters are: `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `T`(en), `J`(ack), `Q`(ueen), `K`(ing), `A`(ce)
*   The second character represents the suit, valid characters are: `S`(pades), `H`(earts), `D`(iamonds), `C`(lubs)

The result of your poker hand compare can be one of the 3 options defined by the `PokerHand.Result` enum.

The ranking of the hands should follow the [Texas Hold'em rules](https://www.dreamstime.com/stock-illustration-poker-hand-rankings-combination-vector-eps-text-outline-image73600614)

> Algorithm

* Is it a royal flush?
* Is it a straight flush?
* Is it a four of a kind?
* Is it a full house?
* Is it a flush?
* Is it a straight?
* Is it a three of a kind?
* Is it a "two pairs"?
* Is it a pair?

Our object is updated when we test these possible suits:

```
this.valueOfRank //Best rank, for example, the value of our best pair
this.valueOfRank2 //Second possible suit (ex: value of the second pair)
this.tabWithoutHandRank //Tab with our remaining values
```

Two comparators if we have a draw:
```
public Result compareInADraw(PokerHand hand)
public Result compareHighestRank(PokerHand hand)

```

Finally, we use a switch for comparing the hand if we have a draw: 
```
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
            }
```



## Installation


* Java 8 SE on your computer
* JUnit tests included
* You can use : mvn test
* Or using an IDE (Eclipse)