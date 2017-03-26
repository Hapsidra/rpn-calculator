/**
 * Created by hapsi on 04.06.2016.
 * Сделать так чтобы кнопки не получали фокуса ввода
 */
import  javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Calculator {
    public static final int COMMAND_COM=0;
    public static final int COMMAND_ADD=1;
    public static final int COMMAND_DIV=2;
    public static final int COMMAND_MUL=3;
    public static final int COMMAND_SUB=4;

    private JLabel currentNumberField;
    private MyKeyAdapter myKeyAdapter;

    private int command= COMMAND_COM;
    private double result=0.0;
    private double last=0.0;
    private int last_command=COMMAND_COM;
    private boolean needClear=true;

    public Calculator(){
        ////////Создание программы///////////
        JFrame program;
        program=new JFrame("Calculator");
        program.setSize(300,400);
        program.setResizable(false);
        program.setVisible(true);
        program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        program.setLayout(new FlowLayout());
        program.setFocusable(true);
        program.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                program.setFocusable(true);
            }
        });
        /////////////////////////////////////

        /////////////Создание считывателя нажиманий////////////////
        myKeyAdapter=new MyKeyAdapter(this);
        program.addKeyListener(myKeyAdapter);
        //////////////////////////////////////////////////////////

        /////////Создание экрана//////////
        currentNumberField =new JLabel("0");
        currentNumberField.setPreferredSize(new Dimension(program.getWidth()-15,30));
        program.add(currentNumberField);
        ////////////////////////////////

        /////////////Создание кнопок////////////////
        ButtonsPanel buttonsPanel=new ButtonsPanel(this);
        buttonsPanel.setPreferredSize(new Dimension(program.getWidth()-10,program.getHeight()-80));
        program.add(buttonsPanel);
        ///////////////////////////////////////////
    }

    public  void actionDigitInput(char key){
        //Очищаем поле ввода если мы до этого получили результат
        if(needClear) {
            currentNumberField.setText("");
        }
        currentNumberField.setText(currentNumberField.getText() + key);
        needClear=false;
    }
    public  void actionDotInput(){
        if(!isDouble()){
            currentNumberField.setText(currentNumberField.getText()+'.');
        }
    }
    public  void actionCEInput(){
        currentNumberField.setText("0");
        result=0;
        command= COMMAND_COM;
    }
    public  void actionCInput(){
        currentNumberField.setText("0");
        result=0;
    }
    public  void actionBackSpaceInput(){
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
    public  void actionSetSign(){
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
    public  void actionCompute(int newCommand) {
        if (command == COMMAND_ADD) {
            last_command = COMMAND_ADD;
            double current = Double.parseDouble(currentNumberField.getText());
            result += current;
            last = current;
            System.out.print("+");
        } else if (command == COMMAND_SUB) {
            last_command = COMMAND_SUB;
            double current = Double.parseDouble(currentNumberField.getText());
            result -= current;
            last = current;
            System.out.print("-");
        } else if (command == COMMAND_MUL) {
            last_command = COMMAND_MUL;
            double current = Double.parseDouble(currentNumberField.getText());
            System.out.print("*");
            result *= current;
            last = current;
        } else if (command == COMMAND_DIV) {
            last_command = COMMAND_DIV;
            double current = Double.parseDouble(currentNumberField.getText());
            System.out.print("/");
            result /= current;
            last = current;
        } else if (command == COMMAND_COM) {
            if (newCommand == COMMAND_COM) {
                switch (last_command) {
                    case COMMAND_ADD:
                        last_command = COMMAND_ADD;
                        result += last;
                        break;
                    case COMMAND_SUB:
                        last_command = COMMAND_SUB;
                        result -= last;
                        break;
                    case COMMAND_MUL:
                        last_command = COMMAND_MUL;
                        result *= last;
                        break;
                    case COMMAND_DIV:
                        last_command = COMMAND_DIV;
                        result /= last;
                        break;
                    case COMMAND_COM:
                        double current = Double.parseDouble(currentNumberField.getText());
                        result = current;
                        break;
                }
            } else {
                double current = Double.parseDouble(currentNumberField.getText());
                result = current;
            }
            System.out.print("=");
        }
        currentNumberField.setText(String.valueOf(result));
        command = newCommand;
        needClear = true;
    }
    private  boolean isDouble(){
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