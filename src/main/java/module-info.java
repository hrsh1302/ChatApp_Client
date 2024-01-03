module client.chatapp_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens client.chatapp_client to javafx.fxml;
    exports client.chatapp_client;
}