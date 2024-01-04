package client.chatapp_client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * The Chat_App class represents the main client application for the Chat App.
 * It being the client side of the application, it needs to be launched after the Chat_Server app.
 * Once the server and the client side are launched, they can interact with each other.
 * */
public class Chat_App extends Application {

    protected App_Controller controller;
    private DataInputStream fromServer = null;
    private DataOutputStream toServer = null;

    /**
     * The start method is called when the application is launched.
     * It initializes the GUI, starts the server on a separate thread,
     * and handles user input.
     *
     * @param stage The main stage for the application.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        //Load FXML and create GUI interface
        FXMLLoader fxmlLoader = new FXMLLoader(Chat_App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 357);
        stage.setTitle("Client Chat App!");
        stage.setScene(scene);
        stage.show();

        controller = fxmlLoader.getController();

        new Thread(() -> {
            try {
                //Create ServerSocket and prompts the connection message
                ServerSocket serverSocket = new ServerSocket(8000);
                String startMessage = "Server started at " + new Date() + "\n";
                Platform.runLater(() -> controller.taServer.appendText(startMessage));

                //Accepts client connection
                Socket socket = serverSocket.accept();

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    //Receives and sends messages
                    String txt = inputStream.readUTF();
                    outputStream.writeUTF(txt);
                    Platform.runLater(() -> controller.taServer.appendText("- " + txt + "\n"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //Event handler triggered when the "Send" button is clicked
        controller.btSend.setOnAction(e -> sendMessage());

        //Event handler triggered when the "Enter" key is pressed
        controller.tfClient.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
    }

    /**
     * The sendMessage method sends the user's message to the connected client.
     */
    public void sendMessage() {
        String txt = null;
        txt = controller.tfClient.getText().trim();

        if (toServer == null) {
            try {
                Socket socket = new Socket("localhost", 8080);

                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());

                InetAddress inetAddress = socket.getInetAddress();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        try {
            toServer.writeUTF(txt);
            toServer.flush();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        controller.tfClient.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}