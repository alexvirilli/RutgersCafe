package softmeth.project4fx;

public class Donut extends MenuItem{

    private String donutType; //must be yeast, cake, or donutHole
    private String flavor;
    private int quantity;

    public static final double yeastPrice = 1.59;
    public static final double cakePrice = 1.79;
    public static final double donutHolePrice = 0.39;

    //getters
    public String getDonutType() {
        return donutType;
    }
    public String getFlavor(){
        return flavor;
    }
    public int getQuantity(){
        return quantity;
    }

    public Donut(String donutType){
        this.donutType = donutType;
        this.quantity = 1;
        this.flavor = "no flavor selected";
    }
    public Donut(String donutType, int quantity){
        this.donutType = donutType;
        this.quantity = quantity;
        this.flavor = "no flavor selected";
    }
    //setter for flavor
    public void setFlavor(String flavor){
        this.flavor = flavor;
    }

    public double itemPrice(){
        if(this.donutType.equalsIgnoreCase("yeast")){
            return yeastPrice * quantity;
        } else if(this.donutType.equalsIgnoreCase("cake")){
            return cakePrice * quantity;
        } else { //donutHole
            return donutHolePrice * quantity;
        }
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity){
        this.quantity -= quantity;
    }

    //to string
    @Override
    public String toString(){
        return "DONUT | TYPE: " + donutType + " FLAVOR: " + flavor + " QUANTITY: " + quantity + " PRICE: $" + String.format("%.2f",itemPrice());
    }

    //equals
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Donut)) return false;
        Donut donut = (Donut) obj;
        return (this.donutType.equalsIgnoreCase(donut.getDonutType()) && this.flavor.equalsIgnoreCase(donut.getFlavor()));
    }

}
