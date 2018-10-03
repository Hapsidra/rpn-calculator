/**
 * Created by hapsi on 04.06.2016.
 * Сделать так чтобы кнопки не получали фокуса ввода
 */
import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Calculator implements ActionListener {

    private JLabel currentNumberField;

    private char command = ' ';
    private double last = 0.0;

    private Calculator() {
        JFrame program;
        program = new JFrame("Calculator");
        program.setSize(300,400);
        program.setResizable(false);
        program.setVisible(true);
        program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        program.setLayout(new FlowLayout());
        program.setFocusable(true);

        program.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                ActionEvent ae = new ActionEvent(e.getSource(),e.getID(), e.getKeyChar() + "");
                Calculator.this.actionPerformed(ae);
            }
        });

        /////////Создание экрана//////////
        currentNumberField = new JLabel("0");
        currentNumberField.setPreferredSize(new Dimension(program.getWidth() - 15,30));
        program.add(currentNumberField);
        ////////////////////////////////

        /////////////Создание кнопок////////////////
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(program.getWidth() - 10,program.getHeight() - 80));

        buttonsPanel.setLayout(new GridLayout(5,4,5,5));
        buttonsPanel.setVisible(true);

        Button buttonCE=new Button("CE");
        buttonCE.addActionListener(this);
        buttonCE.setFocusable(false);
        buttonsPanel.add(buttonCE);

        Button buttonC=new Button("C");
        buttonC.addActionListener(this);
        buttonC.setFocusable(false);
        buttonsPanel.add(buttonC);

        Button buttonDel=new Button("<");
        buttonDel.addActionListener(this);
        buttonDel.setFocusable(false);
        buttonsPanel.add(buttonDel);

        Button buttonDiv=new Button("/");
        buttonDiv.addActionListener(this);
        buttonDiv.setFocusable(false);
        buttonsPanel.add(buttonDiv);

        Button button7=new Button("7");
        button7.addActionListener(this);
        button7.setFocusable(false);
        buttonsPanel.add(button7);

        Button button8=new Button("8");
        button8.addActionListener(this);
        button8.setFocusable(false);
        buttonsPanel.add(button8);

        Button button9=new Button("9");
        button9.addActionListener(this);
        button9.setFocusable(false);
        buttonsPanel.add(button9);

        Button buttonMul=new Button("*");
        buttonMul.addActionListener(this);
        buttonMul.setFocusable(false);
        buttonsPanel.add(buttonMul);

        Button button4=new Button("4");
        button4.addActionListener(this);
        button4.setFocusable(false);
        buttonsPanel.add(button4);

        Button button5=new Button("5");
        button5.addActionListener(this);
        button5.setFocusable(false);
        buttonsPanel.add(button5);

        Button button6=new Button("6");
        button6.addActionListener(this);
        button6.setFocusable(false);
        buttonsPanel.add(button6);

        Button buttonSub=new Button("-");
        buttonSub.addActionListener(this);
        buttonSub.setFocusable(false);
        buttonsPanel.add(buttonSub);

        Button button1=new Button("1");
        button1.addActionListener(this);
        button1.setFocusable(false);
        buttonsPanel.add(button1);

        Button button2=new Button("2");
        button2.addActionListener(this);
        button2.setFocusable(false);
        buttonsPanel.add(button2);

        Button button3=new Button("3");
        button3.addActionListener(this);
        button3.setFocusable(false);
        buttonsPanel.add(button3);

        Button buttonAdd=new Button("+");
        buttonAdd.addActionListener(this);
        buttonAdd.setFocusable(false);
        buttonsPanel.add(buttonAdd);

        Button buttonPlusMinus=new Button("+-");
        buttonPlusMinus.addActionListener(this);
        buttonPlusMinus.setFocusable(false);
        buttonsPanel.add(buttonPlusMinus);

        Button button0=new Button("0");
        button0.addActionListener(this);
        button0.setFocusable(false);
        buttonsPanel.add(button0);

        Button buttonDot=new Button(".");
        buttonDot.addActionListener(this);
        buttonDot.setFocusable(false);
        buttonsPanel.add(buttonDot);

        Button buttonResult=new Button("=");
        buttonResult.addActionListener(this);
        buttonResult.setFocusable(false);
        buttonsPanel.add(buttonResult);

        program.add(buttonsPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char c = e.getActionCommand().charAt(0);
        System.out.println(c + "");
        double current = Double.parseDouble(currentNumberField.getText());
        if(Character.isDigit(c)) {
            if (current == 0) {
                currentNumberField.setText("");
            }
            currentNumberField.setText(currentNumberField.getText() + c);
        }
        else if (c == '+' || c == '-' || c =='/' || c == '*') {
            compute();
            this.command = e.getActionCommand().charAt(0);
        }
        else if(e.getActionCommand().equals("=") || e.getActionCommand().equals("\n")) {
            compute();
        }
        else if(e.getActionCommand().equals("CE")) {
            actionCEInput();
        }
        else if(e.getActionCommand().equals("C")) {
            actionCInput();
        }
        else if(e.getActionCommand().equals("<")) {
            actionBackSpaceInput();
        }
        else if(e.getActionCommand().equals("+-")) {
            actionSetSign();
        }
        /*
        else if(e.getActionCommand().equals(".")) {
            actionDotInput();
        }
        */
    }

    private  void actionDotInput(){
        if(!isDouble()){
            currentNumberField.setText(currentNumberField.getText()+'.');
        }
    }

    private  void actionCEInput(){
        currentNumberField.setText("0");
        last =0;
    }

    private  void actionCInput(){
        currentNumberField.setText("0");
        last =0;
    }

    private  void actionBackSpaceInput(){
        if(currentNumberField.getText().length()==1){
            currentNumberField.setText("0");
        }
        else {
            String temp = "";
            for (int i = 0; i < currentNumberField.getText().length() - 1; i++)
                temp = temp + currentNumberField.getText().charAt(i);
            currentNumberField.setText(temp);
        }
    }

    private  void actionSetSign(){
        if(currentNumberField.getText().charAt(0)=='-')
        {
            String temp = "";
            for (int i = 1; i < currentNumberField.getText().length(); i++)
                temp = temp + currentNumberField.getText().charAt(i);
            currentNumberField.setText(temp);
        }
        else
        {
            String temp = "-";
            for (int i = 0; i < currentNumberField.getText().length(); i++)
                temp = temp + currentNumberField.getText().charAt(i);
            currentNumberField.setText(temp);
        }
    }

    private  void compute() {
        double current = Double.parseDouble(currentNumberField.getText());
        switch (command) {
            case '+':
                last += current;
                break;
            case '-':
                last -= current;
                break;
            case '*':
                last *= current;
                break;
            case '/':
                last /= current;
            default:
                last = current;
                break;
        }
        currentNumberField.setText(last + "");
        /*
        if(!needClear || newCommand==COMMAND_COM) {
            if (command == COMMAND_ADD) {
                last_command = COMMAND_ADD;
                double current = Double.parseDouble(currentNumberField.getText());
                last += current;
                this.current = current;
            } else if (command == COMMAND_SUB) {
                last_command = COMMAND_SUB;
                double current = Double.parseDouble(currentNumberField.getText());
                last -= current;
                this.current = current;
            } else if (command == COMMAND_MUL) {
                last_command = COMMAND_MUL;
                double current = Double.parseDouble(currentNumberField.getText());
                last *= current;
                this.current = current;
            } else if (command == COMMAND_DIV) {
                last_command = COMMAND_DIV;
                double current = Double.parseDouble(currentNumberField.getText());
                last /= current;
                this.current = current;
            } else if (command == COMMAND_COM) {
                if (newCommand == COMMAND_COM) {
                    switch (last_command) {
                        case COMMAND_ADD:
                            last_command = COMMAND_ADD;
                            last += current;
                            break;
                        case COMMAND_SUB:
                            last_command = COMMAND_SUB;
                            last -= current;
                            break;
                        case COMMAND_MUL:
                            last_command = COMMAND_MUL;
                            last *= current;
                            break;
                        case COMMAND_DIV:
                            last_command = COMMAND_DIV;
                            last /= current;
                            break;
                        case COMMAND_COM:
                            double current = Double.parseDouble(currentNumberField.getText());
                            last = current;
                            break;
                    }
                } else {
                    double current = Double.parseDouble(currentNumberField.getText());
                    last = current;
                }
            }
            currentNumberField.setText(String.valueOf(last));
            command = newCommand;
            needClear = true;
        }else{
            command=newCommand;
        }
        */
    }

    private boolean isDouble(){
        for(int i = 0; i< currentNumberField.getText().length(); i++) {
            if (currentNumberField.getText().charAt(i) == '.') {
                return true;
            }
        }
        return false;
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