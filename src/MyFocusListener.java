import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by hapsi on 21.09.2016.
 */
public class MyFocusListener extends FocusAdapter {
    private Button button;
    public MyFocusListener(Button button){
        this.button=button;
    }
    public void focusGained(FocusEvent fe){
        button.setFocusable(false);
    }
}
