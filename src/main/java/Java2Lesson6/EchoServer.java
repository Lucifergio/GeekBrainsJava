package Java2Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) {

        Socket socket = null;
        Scanner sc = new Scanner(System.in);

        try (ServerSocket serverSocket = new ServerSocket(8089)){
            System.out.println("Сервер ожидает подключения... ");
            socket = serverSocket.accept(); //Мы ждем подключения. [БЛОКИРУЕМСЯ]
            System.out.println("Клиент подключился! ");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Thread messageFromServer = new Thread(() -> {

                while (true) {
                    System.out.println("Введите сообщения для клиента: ");
                    String message = sc.nextLine();
                    if (message.equals("/end")) {
                        break;
                    }
                    try {
                        dataOutputStream.writeUTF("Сообщение сервера: " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
            messageFromServer.setDaemon(true);
            messageFromServer.start();


            while (true) {
                String message = dataInputStream.readUTF();
                //poison pill
                if (message.equals("/end")) {
                    dataOutputStream.writeUTF(message);
                    break;
                }

                System.out.println("Клиент прислал " + message);
                dataOutputStream.writeUTF("Echo: " + message);
            }


        }catch (IOException e) {
            e.printStackTrace();
        } //finally (serverSocket.close())


    }
}
