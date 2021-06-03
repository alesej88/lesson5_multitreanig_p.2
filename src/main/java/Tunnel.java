import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{
    private static Semaphore semaphore;

    public Tunnel(int capaticy){
        this.lenght = 80;
        this.description = "Тонель " + lenght + " метров, вместимостью " + capaticy;
        semaphore = new Semaphore(capaticy, true);
    }

    @Override
    public void Go(Car c) {
        try{
            try{
                System.out.println(c.getName() + " готовится к этапу(ждет)" + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап" + description);
                Thread.sleep(lenght / c.getSpeed() * 1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }finally {
            semaphore.release();
            System.out.println(c.getName() + " закончил этап" + description);
        }
    }
}
