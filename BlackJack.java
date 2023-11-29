// Name: Huy Truong Ngo
// CS 145
// Lab 3 deck of cards
import java.util.Scanner;
import java.util.Stack;

public class BlackJack extends Deck {
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.playARound();
    }
    public BlackJack() {
        super();
    }

    public void playARound() {
        super.shuffle(); // shuffles deck
        Scanner input = new Scanner(System.in);
        BJHand player = new BJHand("player");
        iniateHand(player);

        BJHand dealer = new BJHand("dealer");
        iniateHand(dealer);

        System.out.println("Dealer has " + dealer.getHandVal());

        String request = "yes"; // yes" to enter the loop.
        while (request.equalsIgnoreCase("yes") && player.getHandVal() < 21) {
            System.out.println("Do you wish to hit? You are at " + player.getHandVal());
            request = input.next();
            if (request.equalsIgnoreCase("yes")) {
                player.dealt(super.draw());
            }
        }

        int dealerVal = dealerHand(dealer);
        System.out.println("Dealer has " + dealerVal);
        int playerVal = player.getHandVal();
        System.out.print("You have " + playerVal);
        if (dealerVal > 21) {
            System.out.println(" you win!");
        } else if (playerVal <= 21 && playerVal > dealerVal) {
            System.out.println(" you win!");
        } else {
            System.out.println(" you lose!");
        }
        System.out.println("Round has ended.");
    }

    private int dealerHand(BJHand dealer) {
        int dealerVal = dealer.getHandVal();
        while (dealerVal <= 17) {
            int cardVal = super.draw().getValue();
            if (cardVal > 10) {
                cardVal = 10;
            }
            dealerVal += cardVal;
        }
        return dealerVal;
    }

    private void iniateHand(BJHand current) {
        current.dealt(super.draw());
        current.dealt(super.draw());
    }
    //represents a players hand in BlackJack
    private class BJHand {
        private String name;
        private Stack<Card> hand;
        private int handVal;

        public BJHand(String name) {
            this.name = name;
            this.hand = new Stack<>();
            this.handVal = 0;
        }

        public void dealt(Card add) {
            if (canAdd()) {
                int temp = add.getValue();
                if (temp == 1 && handVal == 10) {
                    handVal = 21;
                } else if (temp > 10) {
                    handVal += 10;
                } else {
                    handVal += temp;
                }
                System.out.println("Your card is " + add);
                twentyOne();
                hand.push(add);
            } else {
                if (handVal == 21) {
                    System.out.println("You already have 21!");
                } else {
                    System.out.println("You already have Bust!");
                }
            }
        }

        public int getHandVal() {
            return handVal;
        }

        private void twentyOne() {
            if (handVal == 21) {
                System.out.println("Congrats, you're at 21!");
            } else if (handVal > 21) {
                System.out.println("You Bust!");
            }
        }

        public boolean canAdd() {
            return handVal < 21;
        }
    }
}
