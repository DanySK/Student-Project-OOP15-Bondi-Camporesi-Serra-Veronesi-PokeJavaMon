package controller.installer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * If the current OS is OSX, this class renames a requested library to permit the execution of 
 * the program
 */
public class OSResolver {

    private static final String DIR = "/var/folders";
    private static final String FILE = "liblwjgl.jnilib";
    private static final String EXT = ".dylib";
    private static final String OS = System.getProperty("OS.name", "generic").toLowerCase(Locale.ENGLISH);
    private final List<String> result = new ArrayList<String>();
    private boolean toInit = true;
        
    /**
     * Public constructor, controls the OS, and in case it is OSX it calls the method to rename 
     * necessary file
     */
    public OSResolver() {
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            if (this.toInit) {
            	this.fixOSXLib();
            	this.toInit = false;
            }
        }
    }
    
    /**
     * Main method, searches and renames the requested file
     */
    private void fixOSXLib() {
        searchDirectory(new File(DIR));
        int count = this.result.size();
        
        if (count > 0) {
            for (final String s : this.result) {
                final String string = s.split("\\.")[0];
                final File oldFile = new File(s);
                final File newFile = new File(string + EXT);
                final boolean success = oldFile.renameTo(newFile);
                if (!success) {
                    System.out.println("FAILED RENAMING FILE");
                    return;
                } else {
                    System.out.println("ONE FILE RENAMED SUCCESSFULLY");
                }
            }
        } else {
            System.out.println("NO FILE TO RENAME FOUND");
        }
    }

    /**
     * Searches the file in selected directory
     * @param directory the root directory
     */
    private void searchDirectory(File directory) {
        if (directory.isDirectory()) {
            search(directory);
        } 
    }

    /**
     * Searches the file
     * @param file file to search for
     */
    private void search(File file) {
        if (file.isDirectory())     
            if (file.canRead())
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else {
                        if (FILE.equals(temp.getName().toLowerCase())) {                       
                            this.result.add(temp.getAbsoluteFile().toString());
                        }
                    }
                }
    }
}