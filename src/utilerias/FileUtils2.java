/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author nimiranda
 */
public class FileUtils2 {
  public static boolean copyFile(final File toCopy, final File destFile) {
    try {
      return FileUtils2.copyStream(new FileInputStream(toCopy),
          new FileOutputStream(destFile));
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean copyFilesRecusively(final File toCopy,
      final File destDir) {
    assert destDir.isDirectory();

    if (!toCopy.isDirectory()) {
      return FileUtils2.copyFile(toCopy, new File(destDir, toCopy.getName()));
    } else {
      final File newDestDir = new File(destDir, toCopy.getName());
      if (!newDestDir.exists() && !newDestDir.mkdir()) {
        return false;
      }
      for (final File child : toCopy.listFiles()) {
        if (!FileUtils2.copyFilesRecusively(child, newDestDir)) {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean copyJarResourcesRecursively(final File destDir,
      final JarURLConnection jarConnection) throws IOException {

    final JarFile jarFile = jarConnection.getJarFile();

    for (final Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
      final JarEntry entry = e.nextElement();
      if (entry.getName().startsWith(jarConnection.getEntryName())) {
        final String filename = removeStart(entry.getName(), //
            jarConnection.getEntryName());

        final File f = new File(destDir, filename);
        if (!entry.isDirectory()) {
          final InputStream entryInputStream = jarFile.getInputStream(entry);
          if(!FileUtils2.copyStream(entryInputStream, f)){
            return false;
          }
          entryInputStream.close();
        } else {
          if (!FileUtils2.ensureDirectoryExists(f)) {
            throw new IOException("Could not create directory: "
                + f.getAbsolutePath());
          }
        }
      }
    }
    return true;
  }

  public static boolean copyResourcesRecursively( //
      final URL originUrl, final File destination) {
    try {
      final URLConnection urlConnection = originUrl.openConnection();
      if (urlConnection instanceof JarURLConnection) {
        return FileUtils2.copyJarResourcesRecursively(destination,
            (JarURLConnection) urlConnection);
      } else {
        return FileUtils2.copyFilesRecusively(new File(originUrl.getPath()),
            destination);
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean copyStream(final InputStream is, final File f) {
    try {
      return FileUtils2.copyStream(is, new FileOutputStream(f));
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean copyStream(final InputStream is, final OutputStream os) {
    try {
      final byte[] buf = new byte[1024];

      int len = 0;
      while ((len = is.read(buf)) > 0) {
        os.write(buf, 0, len);
      }
      is.close();
      os.close();
      return true;
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean ensureDirectoryExists(final File f) {
    return f.exists() || f.mkdir();
  }
  
   public static String removeStart(String str, String remove) {
      if (isEmpty(str) || isEmpty(remove)) {
          return str;
      }
      if (str.startsWith(remove)){
          return str.substring(remove.length());
      }
      return str;
  }
  public static boolean isEmpty(CharSequence cs) {
      return cs == null || cs.length() == 0;
  }
  
    public static void deleteAllInDirectoryExcept(File directory, File excepyDir) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    
                    
                    if (excepyDir.getAbsolutePath().contains(files[i].getAbsolutePath())) {
                        System.out.println("continue por "+excepyDir.getAbsolutePath()+" contains "
                        +files[i].getAbsolutePath());
                        continue;
                    }
                    
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
    }
    public static boolean deleteDirectory(File directory) {
    if(directory.exists()){
        File[] files = directory.listFiles();
        if(null!=files){
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
    }
    return(directory.delete());
}
}
