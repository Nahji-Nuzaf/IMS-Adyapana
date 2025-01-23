
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;


public class RoundButton extends JButton {
    
    public RoundButton(){
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:15");
    }
    
}
