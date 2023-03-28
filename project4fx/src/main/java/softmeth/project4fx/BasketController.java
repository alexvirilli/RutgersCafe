package softmeth.project4fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BasketController {

    @FXML
    private ListView<String> orderList;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField total;
    @FXML
    private TextArea messageArea;

    private OrderArchive orderArchive;

    public static final double njSalesTax = 0.06625;

    private void updateCostFields(){
        int archiveIndex = orderArchive.getCurrent();
        subtotal.setText("$"+String.format("%.2f",orderArchive.getArchive().get(archiveIndex).getSubTotal()));
        double calculatedTax = orderArchive.getArchive().get(archiveIndex).getSubTotal() * njSalesTax;
        salesTax.setText("$"+String.format("%.2f",calculatedTax));
        double calculatedTotal = orderArchive.getArchive().get(archiveIndex).getSubTotal() +  calculatedTax;
        total.setText("$"+String.format("%.2f",calculatedTotal));
    }

    @FXML
    public void initialize(OrderArchive orderArchive){
        this.orderArchive = orderArchive;
        int index = orderArchive.getCurrent();
        orderList.setItems(orderArchive.getArchive().get(index).getObservableList());
        updateCostFields();
        orderList.getSelectionModel().select(0);
    }

    @FXML
    void placeOrder(ActionEvent event) {
        if(!orderList.getItems().isEmpty()) {
            orderArchive.placeOrder();
            orderList.getItems().clear();
            subtotal.clear();
            salesTax.clear();
            total.clear();
            messageArea.setText("Order placed!");
        } else {
            messageArea.setText("Basket empty!");
        }
    }

    @FXML
    void remove(ActionEvent event) {
        if(!orderList.getItems().isEmpty()) {
            int index = orderList.getSelectionModel().getSelectedIndex();
            int archiveIndex = orderArchive.getCurrent();
            orderArchive.getArchive().get(archiveIndex).remove(orderArchive.getArchive().get(archiveIndex).getList().get(index));
            orderList.setItems(orderArchive.getArchive().get(archiveIndex).getObservableList());
            updateCostFields();
        } else {
            messageArea.setText("Basket empty!");
        }
    }

}
