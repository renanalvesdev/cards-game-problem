import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class MiddleStack {

	 private int code ;
	 private Queue<Card> cards = new LinkedList<Card>();
	
	 public MiddleStack(int code) {
		super();
		this.code = code;
	}

	public void add(Card card) {
		 cards.add(card);
	 }
	 
	 public void addAll(List<Card> cards) {
		 this.cards.addAll(cards);
		 System.out.println(cards);
	 }
	 
	 public Card remove() {
		 return this.cards.remove();
	 }

	 public boolean hasCards() {
		 return !cards.isEmpty();
	 }
	 
	 public int size() {
		 return cards.size();
	 }
	 
	 public boolean hasSpace() {
		return size() < maxSize(); 
	 }
	 
	 protected abstract int maxSize();
	 
	@Override
	public String toString() {
		return "MiddleStack [code=" + code + ", cards=" + cards + "]";
	}

}
