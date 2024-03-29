# Chat App Client

The Chat App Client is the server-side component of the Chat App application, enabling communication between clients. This server application should be launched before the client side. Once both the server and client sides are running, users can engage in a chat.

## How to Run

1. Ensure that the Java Development Kit (JDK) is installed on your system.
2. Launch the server by executing the [`ChatApp_Server`](https://github.com/hrsh1302/ChatApp_Server) class first. The server will start listening on port 8080.
3. Once the server is running, launch the ChatApp_Client application.
4. Communicate with other users by sending and receiving messages.

## Features

- **GUI Interface:** The server application provides a graphical user interface created using JavaFX.
- **Real-time Communication:** Messages are sent and received in real-time, allowing users to chat seamlessly.

## Usage

1. Launch the server application.
2. Launch the client application on the client side.
3. Enter messages in the provided text field.
4. Click the "Send" button or press "Enter" to send messages to the connected client.

## Important Notes

- The server application displays a start message when launched, indicating the server's start time.
- Communication occurs over port 8080 by default.
- The server accepts a single client connection for simplicity in this example.

## License

This Chat App Client is released under the [MIT License](LICENSE).

Feel free to customize, extend, and enhance this code for your specific needs. If you encounter issues or have suggestions, please submit them in the [issues](https://github.com/yourusername/yourrepository/issues) section.
