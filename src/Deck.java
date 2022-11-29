import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> deck = new ArrayList<Card>();

	public Deck() {
		generateDeck();
	}

	private void generateDeck() {
		System.out.println("Gerando baralho.");
		for (CardType cardType : CardType.values()) {
			for (CardSuit cardSuit : CardSuit.values()) {
				deck.add(new Card(cardSuit, cardType));
			}
		}
		Collections.shuffle(deck);
		System.out.println(deck);
	}

	public List<Card> removeCards(int number) {
		
		List<Card> removedCards = new ArrayList<Card>();
		
		for(int i = 0; i < number ; i++) {
			removedCards.add(removeFirst());
		}
		
		return removedCards;
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	
	private Card removeFirst() {
		return deck.remove(0);
	}

	@Override
	public String toString() {
		return "Deck [deck=" + deck + "]";
	}
	
	
}
