/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author HP
 */
public class FullRoundButton extends JButton {

    public FullRoundButton() {

        init();
        
    }

    private void init() {
        
        this.putClientProperty(FlatClientProperties.STYLE, "arc:999");
    
    }

}
