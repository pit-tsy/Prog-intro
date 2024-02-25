package game;

public class Participant {
    private final Player player;
    private final String name;
    private int place;

    public Participant(Player player, String name) {
        this.player = player;
        this.name = name;
    }


    public Move move(Position position, Cell cell) {
        return player.move(position, cell);
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Player getPlayer() {
        return player;
    }
}
