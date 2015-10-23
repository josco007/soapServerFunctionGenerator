package utilerias;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nimiranda
 */
public class CfgUtil {

    List<String> abajoArriba = new ArrayList<String>();
    List<String> arribaAbajo = new ArrayList<String>();


    public static String leer(String pRutaArchivo, String seccion, String key) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        boolean seccionOk = false;
        boolean keyOk = false;
        String valor = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(pRutaArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // buscar seccion
            String lineaLeida;
            while ((lineaLeida = br.readLine()) != null) {
                if (lineaLeida.toString().equals("[" + seccion + "]")) {
                    seccionOk = true;
                }

                if (lineaLeida.contains("=") && lineaLeida.substring(0,lineaLeida.lastIndexOf("=")).equals(key) && seccionOk) {
                   System.out.println("key encntrada "+lineaLeida);
                    keyOk = true;
                  
                    valor = lineaLeida.substring(lineaLeida.lastIndexOf("=") + 1, lineaLeida.length());//le quitamo el nombre y el = 
                    //si tiene comentarios, se los quitamos
                    /*int i;
                    for (i = 0; i <= valor.length(); i++) {
                        //System.out.println("valor "+valor +" keke = "+ i);
                        if (valor.substring(i, i + 1).equals("/")) {
                            break;
                        }
                    }
                    */
                    //valor = valor.substring(0, i);
                    //seccionOk = false;
                    break;
                    
                }
                if(keyOk){
                    break;
                }
            }
            if (!seccionOk) {
                JOptionPane.showMessageDialog(null, "Error al leer configuracion.\n"
                        + "Seccion no encontrada", "Error seccion" + seccion, 3);
                throw new Exception("Exception: Seccion no encontrada");
            }
            if (!keyOk) {
                JOptionPane.showMessageDialog(null, "Error al leer configuracion.\n"
                        + "Key no encontrada", "Error key:" + key, 3);
                throw new Exception("Exception: Seccion no encontrada");
            } 


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return valor;
    }

    
    
    public static void escribir(String pRutaArchivo, String seccion, String key, String value, String comentarios) {
   
        boolean seccionOk = false;
        boolean keyOk = false;
        
        String ext = FilenameUtils.getExtension(pRutaArchivo);
        String fileNameWithOutExt = FilenameUtils.removeExtension(pRutaArchivo);

        String oldFileName = pRutaArchivo;
        String tmpFileName = fileNameWithOutExt + "_temp." + ext;

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(oldFileName));
            bw = new BufferedWriter(new FileWriter(tmpFileName));
            String line;
            while ((line = br.readLine()) != null) {

                String compareLine = line;
                System.out.println("compare line = "+compareLine);
               if (compareLine.equals("[" + seccion + "]")) {
                   System.out.println("seccion encontrada");
                    seccionOk = true;
                }
                if (compareLine.contains("=") && compareLine.substring(0,compareLine.lastIndexOf("=")).equals(key) && seccionOk) {
                        System.out.println("key encontrada");
                        keyOk = true;
                        line = compareLine.substring(0,compareLine.lastIndexOf("=")) + "="+value; 
                        seccionOk = false;
                }
                

                bw.write(line + "\n");
            }
        } catch (Exception e) {
            return;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                //
            }
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                //
            }
        }
        // Once everything is complete, delete old file..
        File oldFile = new File(oldFileName);
        oldFile.delete();

        // And rename tmp file's name to old file name
        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);
    }
    
    public void addSection(File pFile, String pSection) throws IOException{
        FileUtils.writeStringToFile(pFile, "\n["+pSection+"]",true);
    }
    
    public void writeStringToFileInTheEnd(File pFile, String pString) throws IOException{
         FileUtils.writeStringToFile(pFile, "\n"+pString,true);
    }
}
