public class Card implements Comparable<Card> {
    private int value;
    private String shape;

    private final String[] shapes = { "Heart", "Diamond", "Club", "Spade" };

    public Card(int value, int shape) {
        this.value = value;
        this.shape = shapes[shape];
    }

    public int getValue() {
        return value;
    }


    public String getShape() {
        return shape;
    }


    public String toString() {
        String card = " of " + shape;
        switch (value) {
            case 1:
                card = "Ace" + card;
                break;
            case 11:
                card = "Jack" + card;
                break;
            case 12:
                card = "Queen" + card;
                break;
            case 13:
                card = "King" + card;
                break;
            default:
                card = value + card;
        }
        return card;
    }


    public int compareTo(Card other) {
        int thisCard = this.value;
        int otherCard = other.value;
        if (thisCard == 1) {
            thisCard = 14;
        }
        if (otherCard == 1) {
            otherCard = 14;
        }
        return -(thisCard - otherCard);
    }
}
