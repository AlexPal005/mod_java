import java.util.concurrent.Semaphore;

public class Broker implements Runnable{
    private Exchange exchange;
    private Semaphore semaphore;
    private int count_shares;
    public Broker(Exchange exchange){
        this.exchange = exchange;
        this.semaphore = new Semaphore(1);
        this.count_shares = (int) ( Math.random() * 51 + 50 );
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                semaphore.acquire();

                Thread.sleep(3000);
                int rand = (int) ( Math.random() * 2);
                int count = (int) ( 1+ Math.random() * 10);

                if(rand == 0){
                    exchange.sell();
                    System.out.println(Thread.currentThread().getName() + " " + "Продає " + count +" акцій по ціні " +
                            exchange.getCost() + " Залишок акцій: "+ count_shares + " Індекс біржи: " + exchange.getIndex());
                    count_shares -= count;
                }else{
                    exchange.buy();
                    System.out.println(Thread.currentThread().getName() + " " + "Купує " + count +" акцій по ціні " +
                            exchange.getCost() + " Залишок акцій: "+ count_shares + " Індекс біржи: " + exchange.getIndex());
                    count_shares += count;
                }

                if(exchange.getIndex() <= 0){
                    Thread.currentThread().interrupt();
                }
                exchange.sell();
                semaphore.release();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
