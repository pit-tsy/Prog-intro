package game;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tournament {
    public static void playTournament(PrintStream out, Board board, Scanner in) {
        int numberOfPlayers;
        while (true) {
            numberOfPlayers = UserContact.enterParameter(out, "Number of players", in);
            if (numberOfPlayers > 1) break;
            out.println("Number of participants must be greater than 2. Please try again: ");
        }
        List<Participant> participants = new ArrayList<>();

        out.println("Enter player names or \"_bot_\" if it's bot:");
        for (int i = 1; i <= numberOfPlayers; i++) {
            participants.add(enterParticipant(out, i, in));
        }

        while (numberOfPlayers > 1) {
            shuffleParticipants(participants, numberOfPlayers);
            int result;
            for (int first = 0; first < numberOfPlayers / 2; first++) {
                int second = numberOfPlayers - first - 1;
                do {
                    board.clear();
                    out.println(participants.get(first).getName() + " - X:");
                    out.println(participants.get(second).getName() + " - O:");
                    final Game game = new Game(false, participants.get(first).getPlayer(), participants.get(second).getPlayer());
                    result = game.play(board);
                } while (result == 0);

                if (result == 2) swap(participants, first, second);
            }
            board.clear();
            numberOfPlayers = (numberOfPlayers + 1) / 2;
        }

        participants.get(0).setPlace(1);
        participants.get(1).setPlace(2);
        for (int i = 2; i < participants.size(); i++) {
            int place = participants.get(i / 2).getPlace() + 1;
            participants.get(i).setPlace(place);
        }

        printPlaces(out, participants, 3);
    }

    private static void printPlaces(PrintStream out, List<Participant> participants, int places) {
        int id = 0;
        for (var player : participants) {
            if (player.getPlace() > places) break;
            out.println(player.getName() + " - " + player.getPlace() + " place");
        }
    }

    private static void shuffleParticipants(List<Participant> participants, int numberOfPlayers) {
        Random random = new Random();
        for (int i = 0; i < numberOfPlayers; i++) {
            int j = random.nextInt(numberOfPlayers);
            swap(participants, i, j);
        }
    }

    private static void swap(List<Participant> participants, int i, int j) {
        Participant temp = participants.get(j);
        participants.set(j, participants.get(i));
        participants.set(i, temp);
    }

    private static Participant enterParticipant(PrintStream out, int number, Scanner in) {
        out.print("Player" + number + ": ");
        String name = in.next();

        Player player;
        if (name.equals("_bot_")) {
            player = new RandomPlayer();
            name = "Bot" + number;
        } else {
            player = new HumanPlayer(out, in);
        }

        return new Participant(player, name.isEmpty() ? ("Player" + number) : name);
    }
}
