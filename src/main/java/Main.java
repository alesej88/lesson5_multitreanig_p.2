public class Main {
    public static final int CARS_COUNT= 5;

    public static void main(String[] args) {
        Stage[] stages = {new road, new tunnel(CARS_COUNT / 2), new road(40) };
        Race race = new Race(stage, CARS_COUNT);
    }
}
