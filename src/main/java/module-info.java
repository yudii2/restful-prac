module com.restfulprac.restfulprac {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javax.persistence;


    opens com.restfulprac to javafx.fxml;
    exports com.restfulprac;
}