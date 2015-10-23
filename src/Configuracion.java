
import java.io.File;
import java.io.IOException;
import utilerias.CfgUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nimiranda
 */
public class Configuracion {
    
    /*final static String comentUserNameOrigen = "";
    final static String comentHostOrigen = "";
    final static String comentUrlOrigen = "";
    final static String comentPortOrigen = "";
    */
    
    public static File getFile(){
        return new File("config.cfg");
    }
    
    public static void writeCfgFile(boolean reemplazar) throws IOException{
        File file = getFile();
        if (!file.exists()) {
            file.createNewFile();
        }
        else{
            if (reemplazar) {
                file.delete();
                file.createNewFile();
            }
            else{
                return;
            }
        }
        
        CfgUtil cfgUtil = new CfgUtil();
        
        //add section for more options
        cfgUtil.addSection(file, "OTHERS");
        cfgUtil.writeStringToFileInTheEnd(file, "LOADSAVED=");
        cfgUtil.writeStringToFileInTheEnd(file, "URLSERVERLOCATION=");
        
        //add section for origin server
        cfgUtil.addSection(file, "FTPORIGEN");
        cfgUtil.writeStringToFileInTheEnd(file, "HOST=");
        cfgUtil.writeStringToFileInTheEnd(file, "USERNAME=");
        cfgUtil.writeStringToFileInTheEnd(file, "PORT=");
        cfgUtil.writeStringToFileInTheEnd(file, "URL=");
 
        
        //add section for target server
        cfgUtil.addSection(file, "FTPDESTINO");
        cfgUtil.writeStringToFileInTheEnd(file, "HOST=");
        cfgUtil.writeStringToFileInTheEnd(file, "USERNAME=");
        cfgUtil.writeStringToFileInTheEnd(file, "PORT=");
        cfgUtil.writeStringToFileInTheEnd(file, "URL=");

  
    }
    
    public static boolean getLoadSaved(){
        String loadSavedVaule = CfgUtil.leer(getFile().getAbsolutePath(), "OTHERS", "LOADSAVED"); 
        
        if (loadSavedVaule.length() == 0){
            return false;
        }
        
        try{
            if (Integer.parseInt(loadSavedVaule) == 1) {
                return true;
            }
        }catch(Exception e){
            return false;
        }
        
        return false;
    }
    
    
    //get ftp origen
    public static String getUserNameOrigen(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPORIGEN", "USERNAME");
    }
    
    public static String getHostOrigen(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPORIGEN", "HOST");
    }
    
    public static String getUrlOrigen(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPORIGEN", "URL");
    }
    
    public static String getPortOrigen(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPORIGEN", "PORT");
    }
    
    //set ftp origen
    public static void setUserNameOrigen(String pUserName){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPORIGEN", "USERNAME", pUserName, "");
    }
    
    public static void setHostOrigen(String pHost){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPORIGEN", "HOST", pHost, "");
    }
    
    public static void setUrlOrigen(String pUrl){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPORIGEN", "URL", pUrl, "");
    }
    
    public static void setPortOrigen(String pPort){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPORIGEN", "PORT", pPort, "");
    }
    
    //get ftp destino
    public static String getTargetUserName(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPDESTINO", "USERNAME");
    }
    
    public static String getTargetHost(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPDESTINO", "HOST");
    }
    
    public static String getTargetUrl(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPDESTINO", "URL");
    }
    
    public static String getTartgetPort(){
        return CfgUtil.leer(getFile().getAbsolutePath(), "FTPDESTINO", "PORT");
    }
    
    //set ftp destino
    public static void setUserNameDestino(String pUserName){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPDESTINO", "USERNAME", pUserName, "");
    }
    
    public static void setHostDestino(String pHost){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPDESTINO", "HOST", pHost, "");
    }
    
    public static void setUrlDestino(String pUrl){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPDESTINO", "URL", pUrl, "");
    }
    
    public static void setPortDestino(String pPort){
         CfgUtil.escribir(getFile().getAbsolutePath(), "FTPDESTINO", "PORT", pPort, "");
    }
    
    //get Others
    public static String getUrlServerLocation(){
         return CfgUtil.leer(getFile().getAbsolutePath(), "OTHERS", "URLSERVERLOCATION");
    }
    
    
    //set others
    public static void setUrlServerLocation(String pUrlServerLocation){
        CfgUtil.escribir(getFile().getAbsolutePath(), "OTHERS", "URLSERVERLOCATION", pUrlServerLocation, "");
    }
    
    public static void setLoadSaved(String pLoadSaved){
        CfgUtil.escribir(getFile().getAbsolutePath(), "OTHERS", "LOADSAVED", pLoadSaved, "");
    }
    
}
