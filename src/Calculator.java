/**
 * Created by hapsi on 04.06.2016.
 * Сделать так чтобы кнопки не получали фокуса ввода
 */
import  javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Calculator {
    private JLabel numberField;
    private double A=0,B=0;
    private Character command=' ';
    private boolean repeating=false;
    private MyKeyAdapter myKeyAdapter;

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
        numberField=new JLabel("0.0");
        numberField.setPreferredSize(new Dimension(program.getWidth()-15,30));
        program.add(numberField);
        ////////////////////////////////

        /////////////Создание кнопок////////////////
        ButtonsPanel buttonsPanel=new ButtonsPanel(this);
        buttonsPanel.setPreferredSize(new Dimension(program.getWidth()-10,program.getHeight()-80));
        program.add(buttonsPanel);
        ///////////////////////////////////////////
    }

    public  void actionDigitInput(char key){
        if(numberField.getText()=="0.0" || repeating) {
            numberField.setText(Character.toString(key));
        }
        else {
            numberField.setText(numberField.getText() + key);
        }
        repeating=false;
    }
    public  void actionCommandInput(char command){
        A = Double.parseDouble(numberField.getText());
        numberField.setText("0.0");
        this.command=command;
        repeating=false;
    }
    public  void actionDotInput(){
        if(!isDouble()){
            numberField.setText(numberField.getText()+'.');
        }
    }
    public  void actionCEInput(){
        numberField.setText("0.0");
        A=0.0;
        B=0.0;
        command=null;
        repeating=false;
    }
    public  void actionCInput(){
        numberField.setText("0.0");
    }
    public  void actionBackSpaceInput(){
        if(numberField.getText().length()==1){
            numberField.setText("0.0");
        }
        else {
            String temp = "";
            for (int i = 0; i < numberField.getText().length() - 1; i++)
                temp = temp + numberField.getText().charAt(i);
            numberField.setText(temp);
        }
    }
    public  void actionSetSign(){
        if(numberField.getText().charAt(0)=='-')
        {
            String temp = "";
            for (int i = 1; i < numberField.getText().length(); i++)
                temp = temp + numberField.getText().charAt(i);
            numberField.setText(temp);
        }
        else
        {
            String temp = "-";
            for (int i = 0; i < numberField.getText().length(); i++)
                temp = temp + numberField.getText().charAt(i);
            numberField.setText(temp);
        }
    }
    public  void actionCompute(){
        if(command!=' ') {
            if(!repeating)
                B = Double.parseDouble(numberField.getText());
            double result = 0;
            if (command == '+') {
                result = A + B;
            } else if (command == '-') {
                result = A - B;
            } else if (command == '*') {
                result = A * B;
            } else if (command == '/') {
                result = A / B;
            }
            numberField.setText(String.valueOf(result));
            repeating=true;
            A=result;
        }
    }
    private  boolean isDouble(){
        for(int i = 0; i< numberField.getText().length(); i++) {
            if (numberField.getText().charAt(i) == '.') {
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