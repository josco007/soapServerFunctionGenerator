
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nimiranda
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Configuracion.writeCfgFile(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al crear archivo de configuración", "Warning", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Vista v = new Vista();
        v.setVisible(true);
    }
    
}
