package vsushko.apache.commons;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import java.io.IOException;

public class ShellScriptsExecutor {

    public static void main(String[] args) {
        ShellScriptsExecutor testScript = new ShellScriptsExecutor();
        testScript.runScript();
    }

    private void runScript() {
        CommandLine commandLine = CommandLine.parse("sh /home/vsushko/echo.sh");

        DefaultExecutor defaultExecutor = new DefaultExecutor();
        defaultExecutor.setExitValue(0);
        try {
            defaultExecutor.execute(commandLine);
            System.out.println();
        } catch (ExecuteException e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("permission denied.");
            e.printStackTrace();
        }
    }
}
