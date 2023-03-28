package softmeth.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.PrintWriter;

public class OrderArchive {

    private ObservableList<Order> orders;
    private int current;
    private int newOrder;

    public static final int defaultIndex = 0;

    //constructor
    public OrderArchive(){
        this.orders = FXCollections.observableArrayList();
        this.current = defaultIndex;
        this.newOrder = defaultIndex;
        Order order = new Order(current);
        orders.add(order);
    }

    //getters
    public int getCurrent(){
        return this.current;
    }
    public ObservableList<Order> getArchive(){
        return this.orders;
    }
    //setter
    public void decrementCurrent(){
        if(current > 0 ) this.current--;
    }

    //place the order, keep reference of order, and start a new order
    public void placeOrder(){
        orders.get(current).setOrderPlaced(true);
        this.current++;
        this.newOrder++;
        Order order = new Order(newOrder);
        orders.add(order);
    }

    public Order getOrder(int orderNumber){
        for(Order order : orders){
            if(order.getOrderNumber() == orderNumber) return order;
        }
        return null;
    }

    public void displayOrders(ListView<String> listView){
        for(Order order : orders) {
            if(order.getList().size() > 0 && order.getOrderPlaced()) {
                listView.getItems().add("order no. " + order.getOrderNumber());
                listView.getItems().addAll(order.getObservableList());
                listView.getItems().add("subtotal: $" + String.format("%.2f", order.getSubTotal()) + " sales tax: $" + String.format("%.2f", order.getSalesTax()) + " total: $" + String.format("%.2f", order.getTotal()));
                //listView.getItems().add("" + order.getOrderPlaced());
            }
        }

    }

    public void printwriteOrders(PrintWriter output){
        for(Order order : orders) {
            if(order.getList().size() > 0 && order.getOrderPlaced()) {
                output.println("order no. " + order.getOrderNumber());
                order.printwriteOrder(output);
                output.println("subtotal: $" + String.format("%.2f", order.getSubTotal()) + " sales tax: $" + String.format("%.2f", order.getSalesTax()) + " total: $" + String.format("%.2f", order.getTotal()));
            }
        }

    }



}
