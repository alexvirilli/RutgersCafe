package softmeth.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.PrintWriter;
import java.util.Formatter;

public class Order {

    private int orderNumber;
    private ObservableList<MenuItem> list;
    private boolean orderPlaced;

    public static final double njSalesTax = 0.06625;

    public Order(int orderNumber){
        this.orderNumber = orderNumber;
        this.list = FXCollections.observableArrayList();
        this.orderPlaced = false;
    }
    public Order(int orderNumber, ObservableList<MenuItem> list){
        this.orderNumber = orderNumber;
        this.list = list;
        this.orderPlaced = false;
    }

    //getters
    public boolean getOrderPlaced(){
        return this.orderPlaced;
    }
    public int getOrderNumber(){
        return this.orderNumber;
    }
    public ObservableList<MenuItem> getList(){
        return this.list;
    }

    public void add(MenuItem menuItem){
        if(list.contains(menuItem)){
            if(menuItem instanceof Donut){
                Donut donut = getDonut((Donut)menuItem);
                donut.addQuantity(((Donut) menuItem).getQuantity());
            } else {
                Coffee coffee = getCoffee((Coffee)menuItem);
                coffee.addQuantity(((Coffee) menuItem).getQuantity());
            }
        } else {
            list.add(menuItem);
        }
    }

    public void remove(MenuItem menuItem){
        if(menuItem instanceof Donut){
            Donut donutPtr = (Donut) menuItem;
            if(contains(donutPtr)){
                Donut listedDonut = getDonut(donutPtr);
                int quantityToRemove = ((Donut) menuItem).getQuantity();
                if(listedDonut.getQuantity() > quantityToRemove) listedDonut.removeQuantity(quantityToRemove);
                else if(listedDonut.getQuantity() == quantityToRemove) list.remove(menuItem);
            }
        }
        if(menuItem instanceof Coffee){
            Coffee coffeePtr = (Coffee) menuItem;
            if(contains(coffeePtr)){
                Coffee listedCoffee = getCoffee(coffeePtr);
                int quantityToRemove = ((Coffee) menuItem).getQuantity();
                if(listedCoffee.getQuantity() > quantityToRemove) listedCoffee.removeQuantity(quantityToRemove);
                else if(listedCoffee.getQuantity() == quantityToRemove) list.remove(menuItem);
            }
        }
    }

    public double getDonutCost(){
        double cost = 0;
        for (MenuItem menuItem : list) {
            if (menuItem instanceof Donut) {
                cost += menuItem.itemPrice();
            }
        }
        return cost;
    }

    public double getCoffeeCost(){
        double cost = 0;
        for(MenuItem menuItem : list){
            if(menuItem instanceof  Coffee){
                cost += menuItem.itemPrice();
            }
        }
        return cost;
    }

    public double getSubTotal(){
        double cost = 0;
        for(MenuItem menuItem : list){
            cost += menuItem.itemPrice();
        }
        return cost;
    }

    public boolean contains(Donut donut){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Donut){
                if(list.get(i).equals(donut)) return true;
            }
        }
        return false;
    }

    public boolean contains(Coffee coffee){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Coffee){
                if(list.get(i).equals(coffee)) return true;
            }
        }
        return false;
    }

    public Donut getDonut(Donut donut){

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Donut){
                if(list.get(i).equals(donut)) return (Donut) list.get(i);
            }
        }
        return null;

    }

    public Coffee getCoffee(Coffee coffee){

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Coffee){
                if(list.get(i).equals(coffee)) return (Coffee) list.get(i);
            }
        }
        return null;

    }

    public void printOrder(){
        for(MenuItem menuItem : list){
            System.out.println(menuItem.toString());
        }
    }

    public ObservableList<String> getObservableList(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for(int i = 0; i < list.size(); i++){
            observableList.add(list.get(i).toString());
        }
        return observableList;
    }

    public double getSalesTax(){
        return getSubTotal() * njSalesTax;
    }

    public double getTotal(){
        return getSubTotal() + getSalesTax();
    }

    public void printwriteOrder(PrintWriter output){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for(int i = 0; i < list.size(); i++){
            observableList.add(list.get(i).toString());
            output.println(observableList.get(i));
        }
    }

    //setter
    public void setOrderPlaced(boolean orderPlaced){
        this.orderPlaced = orderPlaced;
    }

}
