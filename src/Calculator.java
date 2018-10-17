/**
 * Created by hapsi on 04.06.2016.
 * Сделать так чтобы кнопки не получали фокуса ввода
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    private JLabel screenLabel;

    private String command = "";
    private double last = 0.0;

    private Calculator() {
        JFrame program;
        program = new JFrame("Calculator");
        program.setSize(300,400);
        program.setResizable(false);
        program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        program.setLayout(new FlowLayout());
        program.setFocusable(true);

        program.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), e.getKeyChar() + "");
                Calculator.this.actionPerformed(ae);
            }
        });

        //Создание экрана
        screenLabel = new JLabel("0");
        screenLabel.setPreferredSize(new Dimension(program.getWidth() - 15,30));
        program.add(screenLabel);

        //Создание кнопок
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(program.getWidth() - 10,program.getHeight() - 80));

        buttonsPanel.setLayout(new GridLayout(5,4,5,5));
        program.add(buttonsPanel);

        Button buttonCE = new Button("C");
        buttonCE.addActionListener(this);
        buttonCE.setFocusable(false);
        buttonsPanel.add(buttonCE);

        Button buttonC = new Button("(");
        buttonC.addActionListener(this);
        buttonC.setFocusable(false);
        buttonsPanel.add(buttonC);

        Button buttonDel = new Button(")");
        buttonDel.addActionListener(this);
        buttonDel.setFocusable(false);
        buttonsPanel.add(buttonDel);

        Button buttonDiv = new Button("/");
        buttonDiv.addActionListener(this);
        buttonDiv.setFocusable(false);
        buttonsPanel.add(buttonDiv);

        Button button7 = new Button("7");
        button7.addActionListener(this);
        button7.setFocusable(false);
        buttonsPanel.add(button7);

        Button button8 = new Button("8");
        button8.addActionListener(this);
        button8.setFocusable(false);
        buttonsPanel.add(button8);

        Button button9 = new Button("9");
        button9.addActionListener(this);
        button9.setFocusable(false);
        buttonsPanel.add(button9);

        Button buttonMul = new Button("*");
        buttonMul.addActionListener(this);
        buttonMul.setFocusable(false);
        buttonsPanel.add(buttonMul);

        Button button4 = new Button("4");
        button4.addActionListener(this);
        button4.setFocusable(false);
        buttonsPanel.add(button4);

        Button button5 = new Button("5");
        button5.addActionListener(this);
        button5.setFocusable(false);
        buttonsPanel.add(button5);

        Button button6 = new Button("6");
        button6.addActionListener(this);
        button6.setFocusable(false);
        buttonsPanel.add(button6);

        Button buttonSub = new Button("-");
        buttonSub.addActionListener(this);
        buttonSub.setFocusable(false);
        buttonsPanel.add(buttonSub);

        Button button1 = new Button("1");
        button1.addActionListener(this);
        button1.setFocusable(false);
        buttonsPanel.add(button1);

        Button button2 = new Button("2");
        button2.addActionListener(this);
        button2.setFocusable(false);
        buttonsPanel.add(button2);

        Button button3 = new Button("3");
        button3.addActionListener(this);
        button3.setFocusable(false);
        buttonsPanel.add(button3);

        Button buttonAdd = new Button("+");
        buttonAdd.addActionListener(this);
        buttonAdd.setFocusable(false);
        buttonsPanel.add(buttonAdd);

        Button buttonPlusMinus = new Button("+-");
        buttonPlusMinus.addActionListener(this);
        buttonPlusMinus.setFocusable(false);
        buttonsPanel.add(buttonPlusMinus);

        Button button0 = new Button("0");
        button0.addActionListener(this);
        button0.setFocusable(false);
        buttonsPanel.add(button0);

        Button buttonDot = new Button(".");
        buttonDot.addActionListener(this);
        buttonDot.setFocusable(false);
        buttonsPanel.add(buttonDot);

        Button buttonResult = new Button("=");
        buttonResult.addActionListener(this);
        buttonResult.setFocusable(false);
        buttonsPanel.add(buttonResult);

        buttonsPanel.setVisible(true);
        program.setVisible(true);
    }

    private double getCurrent() {
        double current = 0;
        try {
            current = Double.parseDouble(screenLabel.getText());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return current;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);

        if (actionCommand.charAt(0) == 8) { // is backspace
            screenLabel.setText(screenLabel.getText().substring(0, screenLabel.getText().length() - 1));
        }
        else if(Character.isDigit(actionCommand.charAt(0))) {
            if (getCurrent() == 0) {
                screenLabel.setText("");
            }
            screenLabel.setText(screenLabel.getText() + actionCommand);
        }
        else if (actionCommand.equals("+") || actionCommand.equals("-") || actionCommand.equals("/") || actionCommand.equals("*")) {
            command = e.getActionCommand();
            last = getCurrent();
            screenLabel.setText("");
        }
        else if(e.getActionCommand().equals("=") || e.getActionCommand().equals("\n")) {
            switch (command) {
                case "+":
                    last += getCurrent();
                    break;
                case "-":
                    last -= getCurrent();
                    break;
                case "*":
                    last *= getCurrent();
                    break;
                case "/":
                    last /= getCurrent();
                    break;
                default:
                    last = getCurrent();
                    break;
            }
            command = "";
            screenLabel.setText(last + "");
        }
        else if(actionCommand.equals("C")) {
            screenLabel.setText("0");
            last = 0;
            command = "";
        }
        else if(actionCommand.equals("+-")) {
            if (getCurrent() > 0) {
                screenLabel.setText("-" + getCurrent());
            }
            else if (getCurrent() < 0) {
                screenLabel.setText("" + (-getCurrent()));
            }
            if (screenLabel.getText().endsWith(".0")) {
                screenLabel.setText(screenLabel.getText().substring(0, screenLabel.getText().length() - 2));
            }
        }
        else if(actionCommand.equals(".")) {
            if(!screenLabel.getText().contains(".")){
                screenLabel.setText(screenLabel.getText() + '.');
            }
        }
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}