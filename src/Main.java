
public class Main {
    public static void main(String[] args) {
        Exchange exchange = new Exchange();
        for (int i = 0;i < 3; i++){
            new Thread(new Broker(exchange)).start();
        }
    }
}