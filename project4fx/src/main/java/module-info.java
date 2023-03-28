module softmeth.project4fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens softmeth.project4fx to javafx.fxml;
    exports softmeth.project4fx;
}