/**
 * Created by hapsi on 05.06.2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ButtonsPanel extends JPanel implements ActionListener{
    private Calculator calculator;
    public ButtonsPanel(Calculator calculator){
        this.calculator=calculator;
        this.setLayout(new GridLayout(5,4,5,5));
        this.setVisible(true);

        Button buttonCE=new Button("CE");
        buttonCE.addActionListener(this);
        buttonCE.addFocusListener(new MyFocusListener(buttonCE));
        this.add(buttonCE);

        Button buttonC=new Button("C");
        buttonC.addActionListener(this);
        buttonC.addFocusListener(new MyFocusListener(buttonC));

        this.add(buttonC);

        Button buttonDel=new Button("<");
        buttonDel.addActionListener(this);
        buttonDel.addFocusListener(new MyFocusListener(buttonDel));

        this.add(buttonDel);

        Button buttonDiv=new Button("/");
        buttonDiv.addActionListener(this);
        buttonDiv.addFocusListener(new MyFocusListener(buttonDiv));

        this.add(buttonDiv);

        Button button7=new Button("7");
        button7.addActionListener(this);
        button7.addFocusListener(new MyFocusListener(button7));

        this.add(button7);

        Button button8=new Button("8");
        button8.addActionListener(this);
        button8.addFocusListener(new MyFocusListener(button8));

        this.add(button8);

        Button button9=new Button("9");
        button9.addActionListener(this);
        button9.addFocusListener(new MyFocusListener(button9));
        this.add(button9);

        Button buttonMul=new Button("*");
        buttonMul.addActionListener(this);
        buttonMul.addFocusListener(new MyFocusListener(buttonMul));

        this.add(buttonMul);

        Button button4=new Button("4");
        button4.addActionListener(this);
        button4.addFocusListener(new MyFocusListener(button4));
        this.add(button4);

        Button button5=new Button("5");
        button5.addActionListener(this);
        button5.addFocusListener(new MyFocusListener(button5));
        this.add(button5);

        Button button6=new Button("6");
        button6.addActionListener(this);
        button6.addFocusListener(new MyFocusListener(button6));
        this.add(button6);

        Button buttonSub=new Button("-");
        buttonSub.addActionListener(this);
        buttonSub.addFocusListener(new MyFocusListener(buttonSub));
        this.add(buttonSub);

        Button button1=new Button("1");
        button1.addActionListener(this);
        button1.addFocusListener(new MyFocusListener(button1));
        this.add(button1);

        Button button2=new Button("2");
        button2.addActionListener(this);
        button2.addFocusListener(new MyFocusListener(button2));
        this.add(button2);

        Button button3=new Button("3");
        button3.addActionListener(this);
        button3.addFocusListener(new MyFocusListener(button3));
        this.add(button3);

        Button buttonAdd=new Button("+");
        buttonAdd.addActionListener(this);
        buttonAdd.addFocusListener(new MyFocusListener(buttonAdd));
        this.add(buttonAdd);

        Button buttonPlusMinus=new Button("+-");
        buttonPlusMinus.addActionListener(this);
        buttonPlusMinus.addFocusListener(new MyFocusListener(buttonPlusMinus));
        this.add(buttonPlusMinus);

        Button button0=new Button("0");
        button0.addActionListener(this);
        button0.addFocusListener(new MyFocusListener(button0));
        this.add(button0);

        Button buttonDot=new Button(".");
        buttonDot.addActionListener(this);
        buttonDot.addFocusListener(new MyFocusListener(buttonDot));
        this.add(buttonDot);

        Button buttonResult=new Button("=");
        buttonResult.addActionListener(this);
        buttonResult.addFocusListener(new MyFocusListener(buttonResult));
        this.add(buttonResult);
    }
    public void actionPerformed(ActionEvent ae){
        if(Character.isDigit(ae.getActionCommand().charAt(0))) {
            calculator.actionDigitInput(ae.getActionCommand().charAt(0));
        }
        else if(ae.getActionCommand()=="+"||ae.getActionCommand()=="-"||ae.getActionCommand()=="/"||ae.getActionCommand()=="*"){
            calculator.actionCommandInput(ae.getActionCommand().charAt(0));
        }
        else if(ae.getActionCommand()=="CE")
        {
            calculator.actionCEInput();
        }
        else if(ae.getActionCommand()=="C")
        {
            calculator.actionCInput();
        }
        else if(ae.getActionCommand()=="<")
        {
            calculator.actionBackSpaceInput();
        }
        else if(ae.getActionCommand()=="+-")
        {
            calculator.actionSetSign();
        }
        else if(ae.getActionCommand()=="="){
            calculator.actionCompute();
        }
        else if(ae.getActionCommand()==".")
        {
            calculator.actionDotInput();
        }
    }
}
