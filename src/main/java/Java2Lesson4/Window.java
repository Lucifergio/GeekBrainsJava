package Java2Lesson4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame{

    private final static String newLine = "\n";

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
        add(txtField);

//Поле ввода сообщения

        JTextField txtSendField = new JTextField();
        txtSendField.setBounds(20, 550, 820, 100);
        add(txtSendField);

        txtSendField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = 0;
                if (txtSendField.getText() != null) {
                    txtField.append(txtSendField.getText() + newLine);
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
                System.out.println("Клик");
                if (txtSendField.getText() != null) {
                    txtField.setText(txtSendField.getText());
                }
            }
        });

        setVisible(true);
    }

}
