package vsushko.runtime;

import java.io.File;

/**
 * Java program to illustrate Process exec() method of Runtime class
 * <p>
 * This method executes given command in a separate process
 *
 * @author vsushko
 */
public class Exec {

    public static void main(String[] args) {
        try {
            // create a process and execute google-chrome
            Process process1 = Runtime.getRuntime().exec("google-chrome");
            System.out.println("Google Chrome successfully started");

            String[] cmd1 = new String[2];
            cmd1[0] = "atom";
            cmd1[1] = "File.java";
            // create a process and execute cmdArray
            Process process2 = Runtime.getRuntime().exec(cmd1);

            // print another message
            System.out.println("File.java opening in atom");

            // create a file with the working directory we wish
            File f1 = new File("/home/saket/Desktop");

            // create a process and execute gedit and currect environment
            Process process = Runtime.getRuntime().exec("gedit", null, f1);
            System.out.println("Gedit opening.");

            // create a file with the working directory we wish
            File f2 = new File("/home/saket/Desktop");

            // create a process and execute gedit and currect environment
            Process process3 = Runtime.getRuntime().exec("gedit", null);
            System.out.println("Gedit opening.");

            String[] cmd2 = new String[2];
            cmd2[0] = "atom";
            cmd2[1] = "File.java";
            // create a file with the working directory we wish
            File dir1 = new File("/home/saket/Desktop");
            // create a process and execute cmdArray
            Process process4 = Runtime.getRuntime().exec(cmd2, null, dir1);
            System.out.println("File.java opening.");

            String[] cmd3 = new String[2];
            cmd3[0] = "atom";
            cmd3[1] = "File.java";
            // create a file with the working directory we wish
            File dir2 = new File("/home/saket/Desktop");
            // create a process and execute cmdArray
            Process process5 = Runtime.getRuntime().exec(cmd3, null);
            System.out.println("File.java opening.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
