public class Exchange {
    private double cost = 10;
    private int index = 20;
    public void sell(){
        if(cost <= 0){
            cost = 10;
        }
        cost -= 1;
        index -= 1;
    }
    public void buy(){
        if(cost <= 0){
            cost = 10;
        }
        cost += 1;
        index += 1;
    }
    public double getCost(){
        return cost;
    }
    public int getIndex(){
        return index;
    }
}
