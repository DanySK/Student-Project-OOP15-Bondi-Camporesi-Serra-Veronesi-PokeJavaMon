package controller.installer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * If the current OS is OSX, this class renames a requested lib to permit the execution of 
 * the program
 */
public class OSResolver {

    private static final String DIR = "/var/folders";
    private static final String FILE = "liblwjgl.jnilib";
    private static final String EXT = ".dylib";
    private static final String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
    private List<String> result = new ArrayList<String>();
        
    /**
     * Public constructor, controls the OS, and in case itis OSX it calls the method to rename 
     * necessary file
     */
    public OSResolver() {
        if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0)) {
            this.fixOSXLib();
        }
    }
    
    /**
     * Main method, searches and renames the requested file
     */
    private void fixOSXLib() {
        OSResolver fileSearch = new OSResolver();
        fileSearch.searchDirectory(new File(DIR));
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
                    System.out.println("FILE RENAMED SUCCESSFULLY");
                }
            }
        } else {
            System.out.println("NO FILE FOUND");
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