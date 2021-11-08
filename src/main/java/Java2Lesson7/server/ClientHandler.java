package Java2Lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Обработчик для конкретного клиента.
 */

public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;


    public ClientHandler(MyServer server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new  DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentication();
                    readMessage();

                }catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    closeConnection();
                }
            }).start();
        }catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика.");
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);

                if(nick != null) {
                    //Авторизовались
                    name = nick;
                    sendMessage(Constants.AUTH_OK_COMMAND);
                    server.broadcastMessage(nick + " вошел в чат");
                    server.subscribe(this);
                    return;

                }else {
                    sendMessage("Неверный логин/пароль");
                }
            }



        }
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage () throws IOException {

        while (true) {

                String[] privMessToken;
                String privName = null;
                String token = "";

                String messageFromClient = in.readUTF();

            if (messageFromClient.startsWith(Constants.PRIVATE_MESSAGE)) {
                privMessToken = messageFromClient.split("\\s+");
                token = privMessToken[0];
                privName = privMessToken[1];
                System.out.println(privName);
            }

           if (token.equals(Constants.PRIVATE_MESSAGE)) {
                   server.privBroadcastMessage("Личное собщение от " + name + ": " + messageFromClient, privName, name);
            } else {
               System.out.println("Сообщение от " + name + " : " + messageFromClient);
                server.broadcastMessage(name + " " + messageFromClient);
            }

            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            }
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
        try {
            in.close();
        }catch (IOException ex) {
            //Ignore
        }
        try {
            out.close();
        }catch (IOException ex) {
            //Ignore
        }
        try {
            socket.close();
        }catch (IOException ex) {
            //Ignore
        }
    }
}

