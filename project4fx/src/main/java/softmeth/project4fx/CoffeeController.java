package softmeth.project4fx;

import javafx.beans.value.ObservableDoubleValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CoffeeController {

    @FXML
    private CheckBox caramel;

    @FXML
    private CheckBox frenchVanilla;

    @FXML
    private RadioButton grandeSize;

    @FXML
    private CheckBox irishCream;

    @FXML
    private CheckBox mocha;

    @FXML
    private TextField quantity;

    @FXML
    private TextField runningTotal;

    @FXML
    private RadioButton shortSize;

    @FXML
    private ToggleGroup size;

    @FXML
    private CheckBox sweetCream;

    @FXML
    private RadioButton tallSize;

    @FXML
    private RadioButton ventiSize;

    //private Order order;

    private OrderArchive orderArchive;

    public void initData(OrderArchive orderArchive){
        this.orderArchive = orderArchive;
    }

    private double subTotal = 0;
    private double basePrice = 0;

    public static final int SMALL = 0;
    public static final int TALL = 1;
    public static final int GRANDE = 2;
    public static final int VENTI = 3;
    public static final int NOT_FOUND = -1;

    private boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    private int getSelectedSize(){
        if(shortSize.isSelected()){
            return 0;
        } else if (tallSize.isSelected()){
            return 1;
        } else if (grandeSize.isSelected()) {
            return 2;
        } else if (ventiSize.isSelected()) {
            return 3;
        }
        return NOT_FOUND;
    }

    private void reset(){
        shortSize.setSelected(false);
        tallSize.setSelected(false);
        grandeSize.setSelected(false);
        ventiSize.setSelected(false);
        sweetCream.setSelected(false);
        frenchVanilla.setSelected(false);
        irishCream.setSelected(false);
        caramel.setSelected(false);
        mocha.setSelected(false);
        runningTotal.clear();
        quantity.clear();
        basePrice = 0;
        subTotal = 0;
    }

    private void addToOrder(Coffee coffeePtr, Order order){
        if(order.getList().contains(coffeePtr)){
            Coffee coffee = order.getCoffee(coffeePtr);
            coffee.addQuantity(coffeePtr.getQuantity());
        } else {
            order.getList().add(coffeePtr);
        }
    }
    @FXML
    void add(ActionEvent event) {
        int archiveIndex = orderArchive.getCurrent();
        if(getSelectedSize() != NOT_FOUND){
            int size = getSelectedSize();
            if(isInt(quantity.getText())) {
                int coffeeQuantity = Integer.parseInt(quantity.getText());
                if(coffeeQuantity > 0 && coffeeQuantity < 6) {
                    Coffee coffee = new Coffee(size);
                    coffee.setQuantity(coffeeQuantity);
                    if (sweetCream.isSelected()) coffee.addIn("sweet cream");
                    if (frenchVanilla.isSelected()) coffee.addIn("french vanilla");
                    if (irishCream.isSelected()) coffee.addIn("irish cream");
                    if (caramel.isSelected()) coffee.addIn("caramel");
                    if (mocha.isSelected()) coffee.addIn("mocha");
                    addToOrder(coffee, orderArchive.getArchive().get(archiveIndex));
                    reset();
                }
            }
        }
    }

    @FXML
    void caramelSelect(ActionEvent event) {
        if(!caramel.isSelected()){
            subTotal -= 0.30;
        } else {
            subTotal += 0.30;
        }
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void frenchVanillaSelect(ActionEvent event) {
        if(!frenchVanilla.isSelected()){
            subTotal -= 0.30;
        } else {
            subTotal += 0.30;
        }
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void grandeSelect(ActionEvent event) {
        basePrice = 2.69;
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void irishCreamSelect(ActionEvent event) {
        if(!irishCream.isSelected()){
            subTotal -= 0.30;
        } else {
            subTotal += 0.30;
        }
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void mochaSelect(ActionEvent event) {
        if(!mocha.isSelected()){
            subTotal -= 0.30;
        } else {
            subTotal += 0.30;
        }
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void shortSelect(ActionEvent event) {
        basePrice = 1.89;
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void sweetCreamSelect(ActionEvent event) {
        if(!sweetCream.isSelected()){
            subTotal -= 0.30;
        } else {
            subTotal += 0.30;
        }
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void tallSelect(ActionEvent event) {
        basePrice = 2.29;
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

    @FXML
    void ventiSelect(ActionEvent event) {
        basePrice = 3.09;
        runningTotal.setText(String.format("%.2f",subTotal + basePrice));
    }

}
