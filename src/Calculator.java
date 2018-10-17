/**
 * Created by hapsi on 04.06.2016.
 * Сделать так чтобы кнопки не получали фокуса ввода
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    private JLabel screenLabel;

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

        String[][]buttons = new String[][] {
                {"C", "(", ")", "/"},
                {"7", "8", "9", "*"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"+-","0", "<", "="}
        };

        buttonsPanel.setLayout(new GridLayout(buttons.length, buttons[0].length,5,5));
        program.add(buttonsPanel);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button button = new Button(buttons[i][j]);
                button.addActionListener(this);
                button.setFocusable(false);
                buttonsPanel.add(button);
            }
        }
        buttonsPanel.setVisible(true);
        program.setVisible(true);
    }

    private Character getLastChar() {
        if (screenLabel.getText().isEmpty()) {
            return null;
        }
        return screenLabel.getText().charAt(screenLabel.getText().length() - 1);
    }

    private double getLastNumber() {
        StringBuilder s = new StringBuilder();
        String screenText = screenLabel.getText();
        int i = screenText.length() - 1;
        while(i>= 0 && (Character.isDigit(screenText.charAt(i)) || screenText.charAt(i) == '.' || screenText.charAt(i) == '-')) {
            s.insert(0, screenText.charAt(i--));
        }
        return Double.parseDouble(s.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);

        if (actionCommand.charAt(0) == 8 || actionCommand.equals("<")) { // is backspace
            screenLabel.setText(screenLabel.getText().substring(0, screenLabel.getText().length() - 1));
        }
        else if(Character.isDigit(actionCommand.charAt(0))) {
            if (screenLabel.getText().equals("0") || screenLabel.getText().equals("0.0") ) {
                screenLabel.setText("");
            }
            screenLabel.setText(screenLabel.getText() + actionCommand);
        }
        else if (actionCommand.equals("+") || actionCommand.equals("-") || actionCommand.equals("/") || actionCommand.equals("*")) {
            if (getLastChar() != null && (Character.isDigit(getLastChar()) || getLastChar() == ')')) {
                screenLabel.setText(screenLabel.getText() + actionCommand);
            }
            else if (actionCommand.equals("-") && getLastChar() != null && getLastChar() == '(') {
                screenLabel.setText(screenLabel.getText() + actionCommand);
            }
        }
        else if (actionCommand.equals("(")) {
            if (getLastChar() == null || getLastChar() == '+' || getLastChar() == '-' || getLastChar() == '/' || getLastChar() == '*') {
                screenLabel.setText(screenLabel.getText() + actionCommand);
            }
        }
        else if (actionCommand.equals(")")) {
            if (getLastChar() != null && Character.isDigit(getLastChar())) {
                screenLabel.setText(screenLabel.getText() + actionCommand);
            }
        }
        else if(e.getActionCommand().equals("=") || e.getActionCommand().equals("\n")) {
            screenLabel.setText("" + RPN.solve(screenLabel.getText()));
            if (screenLabel.getText().endsWith(".0")) {
                screenLabel.setText(screenLabel.getText().substring(0, screenLabel.getText().length() - 2));
            }
        }
        else if(actionCommand.equals("+-")) {
            if (getLastNumber() > 0) {
                screenLabel.setText("-" + getLastNumber());
            }
            else if (getLastNumber() < 0) {
                screenLabel.setText("" + (-getLastNumber()));
            }
            if (screenLabel.getText().endsWith(".0")) {
                screenLabel.setText(screenLabel.getText().substring(0, screenLabel.getText().length() - 2));
            }
        }
        else if(actionCommand.equals("C")) {
            screenLabel.setText("0");
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