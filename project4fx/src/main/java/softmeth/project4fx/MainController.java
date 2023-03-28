package softmeth.project4fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.application.Application.launch;

public class MainController {

//    private int orderNumber = 0;
//    private Order order = new Order(generateOrderNumber());
//
//    public int generateOrderNumber(){
//        orderNumber++;
//        return orderNumber;
//    }

    Stage stage = new Stage();
    OrderArchive orderArchive = new OrderArchive();

    @FXML
    void basketButton(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("basket-view.fxml"));
        Parent basketParent = loader.load();
        Scene basketScene = new Scene(basketParent);
        BasketController controller = loader.getController();
        controller.initialize(orderArchive);

        //Stage stage = new Stage();
        stage.setTitle("Basket");
        stage.setScene(basketScene);
        stage.show();

    }

    @FXML
    void coffeeButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("coffee-view.fxml"));
        Parent coffeeParent = loader.load();
        Scene coffeeScene = new Scene(coffeeParent, 350, 400);
        CoffeeController controller = loader.getController();
        controller.initData(orderArchive);

        //Stage stage = new Stage();
        stage.setTitle("Coffee Order");
        stage.setScene(coffeeScene);
        stage.show();
    }

    @FXML
    void donutButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("donut-view.fxml"));
        Parent donutParent = loader.load();
        Scene donutScene = new Scene(donutParent);
        DonutController controller = loader.getController();
        controller.initData(orderArchive);

        //Stage stage = new Stage();
        stage.setTitle("Donut Order");
        stage.setScene(donutScene);
        stage.show();
    }

    @FXML
    void storeOrderButton(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("storeorders-view.fxml"));
        Parent storeOrdersParent = loader.load();
        Scene storeOrdersScene = new Scene(storeOrdersParent);
        StoreOrdersController controller = loader.getController();
        controller.initData(orderArchive);

        //Stage stage = new Stage();
        stage.setTitle("Store Orders");
        stage.setScene(storeOrdersScene);
        stage.show();

    }

}
