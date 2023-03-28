package softmeth.project4fx;

public abstract class MenuItem {

    private double price;

    public static final double defaultValue = 0;
    public MenuItem(){
        this.price = defaultValue;
    }

    public MenuItem(double price){
        this.price = price;
    }

    public abstract double itemPrice();

    public abstract void addQuantity(int quantity);

    public abstract void removeQuantity(int amount);

}
