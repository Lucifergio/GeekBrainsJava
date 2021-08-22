package Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterApp extends JFrame {

    private  int value;

    private static final String COUNTER_NORMAL = "Счетчик в норме";
    private static final String COUNTER_IS_TO_BIG = "Счетчик слишком большой";
    private static final String COUNTER_IS_TO_LOW = "Вы натыкали очень мало";

    public CounterApp(int initialValue) {
        this.value = initialValue;
        setBounds(500,500,300,150);
        setTitle("Simple counter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//Создадим шрифт.
        Font font = new Font("Arial", Font.BOLD, 64);

//Создадим счетчик.
        JLabel counterValue = new JLabel(String.valueOf(initialValue));
        counterValue.setFont(font);
        counterValue.setHorizontalAlignment(SwingConstants.CENTER);
        add(counterValue, BorderLayout.CENTER);

        JLabel infoPane = new JLabel(COUNTER_NORMAL);
        infoPane.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoPane, BorderLayout.NORTH);

//Создадим кнопку уменьшения
        JButton x2decrementButton = new JButton("<");
        x2decrementButton.setFont(font);
        add(x2decrementButton, BorderLayout.WEST);

//Создадим кнопку увеличения
        JButton x2incrementButton = new JButton(">");
        x2incrementButton.setFont(font);
        add(x2incrementButton, BorderLayout.EAST);

//Создадим кнопку обнуления счетчика.
        JButton zeroButton = new JButton("Обнулить");
        add(zeroButton, BorderLayout.SOUTH);


//Добавляем слушателей
    x2decrementButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       value -= 2;
       counterValue.setText(String.valueOf(value));
        if (value < -10) {
            infoPane.setText(COUNTER_IS_TO_LOW);
        }else {
            infoPane.setText(COUNTER_NORMAL);
        }
    }
});

    x2incrementButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        value += 2;
        counterValue.setText(String.valueOf(value));
        if (value > 10) {
            infoPane.setText(COUNTER_IS_TO_BIG);
        }
        else {
            infoPane.setText(COUNTER_NORMAL);
        }
        }
    });

    zeroButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        value = 0;
        counterValue.setText(String.valueOf(value));
        infoPane.setText(COUNTER_NORMAL);
        }
    });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CounterApp(0);
    }

}
