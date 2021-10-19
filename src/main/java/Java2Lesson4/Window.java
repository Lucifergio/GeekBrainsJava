package Java2Lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame{

    private final static String newLine = "\n";
    Font font = new Font("Italic", Font.ITALIC, 24);
    Font font2 = new Font("Italic", Font.ITALIC, 14);

    public Window() {

        setBounds(500, 500, 1000, 700);
        setTitle("Online chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

//Поле переписки

        JTextArea txtField = new JTextArea();
        txtField.setBounds(20, 20, 960, 500);
        txtField.setEditable(false);
        txtField.setFont(font);
        add(txtField);

//Поле ввода сообщения

        JTextField txtSendField = new JTextField();
        txtSendField.setBounds(20, 550, 820, 100);
        txtSendField.setFont(font2);
        add(txtSendField);

        txtSendField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSendField.getText().equals("")) {
                    System.out.println("Пустая строка");
                }
                else {
                    txtField.append("Вы: " + txtSendField.getText() + newLine);
                    txtSendField.setText(null);
                }
            }
        });

// Кнопка
        JButton button = new JButton("Отправить");
        button.setBounds(850,550 , 130, 99);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSendField.getText().equals("")) {
                    System.out.println("Пустая строка");
                }
                else {
                    txtField.append("Вы: " + txtSendField.getText() + newLine);
                    txtSendField.setText(null);
                }
            }
        });

        setVisible(true);
    }

}
