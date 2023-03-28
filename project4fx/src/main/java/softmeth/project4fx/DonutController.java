package softmeth.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Formatter;

public class DonutController {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ImageView donutImage;
    @FXML
    private ListView<String> donutList;
    @FXML
    private TextField runningTotal;
    @FXML
    private TextField quantity;

//    int orderNumber = mc.generateOrderNumber();
//    private Order order = new Order(orderNumber);
    private OrderArchive orderArchive;

    public void initData(OrderArchive orderArchive){
        this.orderArchive = orderArchive;
    }

    ObservableList<String> yeastFlavors = FXCollections.observableArrayList("Plain","Chocolate","Vanilla","Powder","Strawberry","Jelly");
    ObservableList<String> cakeFlavors = FXCollections.observableArrayList("Vanilla","Chocolate","Red Velvet");
    ObservableList<String> donutHoleFlavors = FXCollections.observableArrayList("Glazed","Chocolate","Powdered");

    private boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    @FXML
    public void initialize(){
        comboBox.getItems().addAll("Yeast","Cake","Donut Holes");
    }

    @FXML
    void comboBoxSelection(ActionEvent event){
        if(comboBox.getSelectionModel().isSelected(0)){
            Image image = new Image("file:src/main/resources/softmeth/project4fx/yeastdonut.jpg");
            donutImage.setImage(image);
            donutList.setItems(yeastFlavors);
        } else if(comboBox.getSelectionModel().isSelected(1)){
            Image image = new Image("file:src/main/resources/softmeth/project4fx/cakedonut.jpg");
            donutImage.setImage(image);
            donutList.setItems(cakeFlavors);
        } else if(comboBox.getSelectionModel().isSelected(2)){
            Image image = new Image("file:src/main/resources/softmeth/project4fx/donutholes.jpeg");
            donutImage.setImage(image);
            donutList.setItems(donutHoleFlavors);
        }
    }

    private String getSelectedDonutType(){
        if(comboBox.getSelectionModel().isSelected(0)){
            return "yeast";
        } else if (comboBox.getSelectionModel().isSelected(1)){
            return "cake";
        } else if (comboBox.getSelectionModel().isSelected(2)){
            return "donutHole";
        } else {
            return null;
        }
    }
    private String getSelectedFlavor(){
        return donutList.getSelectionModel().getSelectedItem();
    }

    private void addToOrder(Donut donutPtr, Order order){
        if(order.getList().contains(donutPtr)){
            Donut donut = order.getDonut(donutPtr);
            donut.addQuantity(donutPtr.getQuantity());
        } else {
            order.getList().add(donutPtr);
        }
    }

    @FXML
    void add(ActionEvent event){
        int archiveIndex = orderArchive.getCurrent();
        String donutType = getSelectedDonutType();
        String flavor = getSelectedFlavor();
        if(flavor != null) {
            if (isInt(quantity.getText()) && Integer.parseInt(quantity.getText()) > 0) {
                int quantityDonuts = Integer.parseInt(quantity.getText());
                Donut donut = new Donut(donutType, quantityDonuts);
                donut.setFlavor(flavor);
                addToOrder(donut, orderArchive.getArchive().get(archiveIndex));
                runningTotal.setText(String.format("%.2f", orderArchive.getArchive().get(archiveIndex).getDonutCost()));
                quantity.clear();
            }
        }
    }

    private void removeFromOrder(Donut donutPtr, Order order){
        if(order.contains(donutPtr)){
            Donut listedDonut = order.getDonut(donutPtr);
            int quantityToRemove = donutPtr.getQuantity();
            if(listedDonut.getQuantity() > quantityToRemove) listedDonut.removeQuantity(quantityToRemove);
            else if(listedDonut.getQuantity() == quantityToRemove) order.getList().remove(donutPtr);
        }
    }

    @FXML
    void remove(ActionEvent event){
        int archiveIndex = orderArchive.getCurrent();
        String donutType = getSelectedDonutType();
        String flavor = getSelectedFlavor();
        if(isInt(quantity.getText())){
            int quantityDonuts = Integer.parseInt(quantity.getText());
            Donut donut = new Donut(donutType,quantityDonuts);
            donut.setFlavor(flavor);
            removeFromOrder(donut, orderArchive.getArchive().get(archiveIndex));
            runningTotal.setText(String.format("%.2f", orderArchive.getArchive().get(archiveIndex).getDonutCost()));
            quantity.clear();
        }
    }


}
