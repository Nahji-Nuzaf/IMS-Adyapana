/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class RoundTextField extends JTextField{
    
    public RoundTextField(){
        init();
    }
    
    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }
    
}
