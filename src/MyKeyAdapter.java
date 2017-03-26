/**
 * Created by hapsidra on 05.06.2016.
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    private Calculator calculator;
    public MyKeyAdapter(Calculator calculator){
        this.calculator=calculator;
    }
    public void keyPressed(KeyEvent e) {
        if(Character.isDigit(e.getKeyChar())) {
            calculator.actionDigitInput(e.getKeyChar());
        }
        else if(e.getKeyChar()=='+'||e.getKeyChar()=='-'||e.getKeyChar()=='/'||e.getKeyChar()=='*'){
            char commandChar=e.getKeyChar();
            int commandCode=Calculator.COMMAND_COM;
            switch (commandChar){
                case '+':
                    commandCode=Calculator.COMMAND_ADD;
                    break;
                case '-':
                    commandCode=Calculator.COMMAND_SUB;
                    break;
                case '*':
                    commandCode=Calculator.COMMAND_MUL;
                    break;
                case '/':
                    commandCode=Calculator.COMMAND_MUL;
                    break;
            }
            calculator.actionCompute(commandCode);
        }
        else if(e.getKeyChar()=='.')
        {
            calculator.actionDotInput();
        }
        else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            calculator.actionCEInput();
        }
        else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
            calculator.actionBackSpaceInput();
        }
        else if(e.getKeyCode()==KeyEvent.VK_ENTER){
            calculator.actionCompute(Calculator.COMMAND_COM);
        }
    }
}


