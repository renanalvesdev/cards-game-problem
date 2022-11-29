
public class Card {

	private CardSuit cardSuit;
	private CardType cardType;
	
	public Card(CardSuit cardSuit, CardType cardType) {
		this.cardSuit = cardSuit;
		this.cardType = cardType;
	}
	
	public CardSuit getCardSuit() {
		return cardSuit;
	}
	
	public CardType getCardType() {
		return cardType;
	}

	@Override
	public String toString() {
		return "["+cardType.getType() + ", "+ cardSuit + "]";
	}
	
	public boolean sameType(Card card) {
		return this.cardType.equals(card.getCardType());
	}
	
	private boolean sameSuit(Card card) {
		return this.cardSuit.equals(card.getCardSuit());
	}
	
	@Override
	public boolean equals(Object obj) {
		Card card = (Card) obj;
		return sameType(card) && sameSuit(card);
	}
}
