import sun.misc.Signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

public class Race {
    private ArrayList<Stage> stages;
    private ArrayList<Car> participants;
    private HashMap<SignalType, Signal> signals;
    private AtomicReference<Car> winner;

    public Race(Stage[] stages, int participantQuality){
        Car[] cars = new Car[participantQuality];
        for (int i = 0; i < cars.length; i++) {
          cars[i]  = new Car(20 + (int) (Math.random() * 10));
          }
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.participants = new ArrayList<>(Arrays.asList(cars));
        this.signals = new HashMap<>();
        this.winner = new AtomicReference<>();

        signals.put(SignalType.START, new Signal("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> гОНКА НАЧАЛАСЬ!!!", participantQuality));
        signals.put(SygnalType.FINISH, new Signal("Важное объявление >>> Гонка завершилась!!!!", participantQuality));
    }
    public ArrayList<Stage> getStages(){
        return stages;
    }
    public CyclicBarrier getStartSignal(){
        return signals.get(SingalType.START.getBarrier);
    }
    public CyclicBarrier getFinishSignal(){
        return signals.get(SignalType.FINISH.getBarrier);
    }
    public boolean setWinner(Car winner){
        boolean result = this.winner.compareAndSet(null, winner);
        if(result){
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> " + winner.getName() + "победил!!!");
        }
        return result;
    }
    public void start(){
        for(Car participant : participants) {
            new  Thread(participant). start();
        }
    }
    private class Signal{
        private CyclicBarrier barrier;

        private Signal(String massage, int size){
        this.barrier = new CyclicBarrier(size, () -> System.out.println(massage));
        }
        public CyclicBarrier getBarrier(){
            return barrier;
        }
        private enum Signaltype{
            START, FINISH
        }
    }
}
