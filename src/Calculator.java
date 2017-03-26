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
        double current = Double.parseDouble(currentNumberField.getText());
        if (command == COMMAND_ADD) {
            result += current;
            System.out.print("+");
        } else if (command == COMMAND_SUB) {
            result -= current;
            System.out.print("-");
        } else if (command == COMMAND_MUL) {
            System.out.print("*");
            result *= current;
        } else if (command == COMMAND_DIV) {
            System.out.print("/");
            result /= current;
        } else if (command == COMMAND_COM) {
            System.out.print("=");
        }
        System.out.print(current);

        currentNumberField.setText(String.valueOf(result));
        if(newCommand!=COMMAND_COM)
            command=newCommand;
        needClear=true;
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