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
            calculator.actionCommandInput(e.getKeyChar());
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
            calculator.actionCompute();
        }
    }
}


