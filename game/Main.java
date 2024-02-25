package game;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final Board board = UserContact.chooseBoard(System.out, in);
        if (UserContact.chooseOptions(System.out, in, "game type", "tournament", "withFriend") == 1) {
            Tournament.playTournament(System.out, board, in);
        } else {
            while (true) {
                final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
                int result = game.play(board);
                System.out.println("Game result: " + UserContact.resultToString(result));

                if (!UserContact.ask(System.out, "Do you want continue the game?", in)) break;
                board.clear();
            }
        }
    }
}
