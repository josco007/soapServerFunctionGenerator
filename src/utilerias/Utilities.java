/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

/**
 *
 * @author nimiranda
 */
public class Utilities {

    public String getRunningJarName() {
        String className = this.getClass().getName().replace('.', '/');
        String classJar
                = this.getClass().getResource("/" + className + ".class").toString();
        if (classJar.startsWith("jar:")) {
            String vals[] = classJar.split("/");
            for (String val : vals) {
                if (val.contains("!")) {
                    return val.substring(0, val.length() - 1);
                }
            }
        }
        return null;
    }

}
