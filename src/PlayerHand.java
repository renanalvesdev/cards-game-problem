import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerHand {

	private List<Card> cards;
	private Card pickedCard;

	public PlayerHand() {
		this.cards = new ArrayList<Card>();
	}

	public List<Card> getOldestCards() {
		return get().stream().filter(card -> !card.equals(pickedCard)).toList();
	}

	public List<Card> get() {
		return Collections.unmodifiableList(cards);
	}

	public void addAll(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public void add(Card card) {
		pickedCard = card;
		this.cards.add(pickedCard);
		sortCardsByFrequencyOfType();
	}

	private void sortCardsByFrequencyOfType() {

		Map<CardType, List<Card>> cardsPerType = new HashMap<CardType, List<Card>>();

		List<Card> cardsSortedByFrequency = new ArrayList<Card>();
		
		cardsPerType = cards.stream().collect(Collectors.groupingBy(Card::getCardType));
		System.out.println("cards per type: " + cardsPerType);

		cardsPerType.entrySet().stream().sorted((k1, k2) -> Integer.compare(k1.getValue().size(), k2.getValue().size()))
				.forEach(e -> cardsSortedByFrequency.addAll(e.getValue()));
		
		cards = cardsSortedByFrequency;
		System.out.println("cards sorted by frequency: " + cards);
	}

	public Card removeFirst() {
		return cards.remove(0);
	}
	
	public Card removeLessFrequent() {
		Card lessFrequent = cards.stream().filter(card -> !card.equals(pickedCard)).findFirst().orElse(null);
		System.out.println("Removed card: " + lessFrequent);
		return cards.remove(cards.indexOf(lessFrequent));
	}

	@Override
	public String toString() {
		return "PlayerHand [cards=" + cards + "]";
	}

}
