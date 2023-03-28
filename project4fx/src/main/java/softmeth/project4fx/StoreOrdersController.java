package softmeth.project4fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StoreOrdersController {

    @FXML
    private ListView<String> orders;

    @FXML
    private TextArea messageArea;

    private OrderArchive orderArchive;

    public static final int defaultIndex = 0;
    public void initData(OrderArchive orderArchive){
        this.orderArchive = orderArchive;
        orderArchive.displayOrders(orders);
        orders.getSelectionModel().select(defaultIndex);
    }

    @FXML
    void cancelOrder(ActionEvent event) {
        if(!orders.getItems().isEmpty()) {
            String orderToCancel = orders.getSelectionModel().getSelectedItem();
            if (orderToCancel.contains("no.")) {
                int indexOffset = 2;
                int orderNumber = Integer.parseInt(orderToCancel.substring(orderToCancel.indexOf(". ") + indexOffset));
                Order order = orderArchive.getOrder(orderNumber);
                orderArchive.getArchive().remove(order);
                orders.getItems().clear();
                orderArchive.displayOrders(orders);
                orderArchive.decrementCurrent();
                messageArea.setText(orderToCancel + " cancelled");
            } else {
                messageArea.setText("Select an item with \"order no. x\"\n");
            }
        } else {
            messageArea.setText("No orders!");
        }
    }

    @FXML
    void saveAndExport(ActionEvent event) throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select File to Write");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        File file = chooser.showSaveDialog(new Stage());
        if(file != null){
            try {
                PrintWriter output = new PrintWriter(file);
                orderArchive.printwriteOrders(output);
                output.close();
                messageArea.setText("Export successful");
            } catch (FileNotFoundException ex) {
                messageArea.setText("err: file not found");
            }

        }
    }

}
