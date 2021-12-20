package plus.wcj.heifer.boot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/15
 */
public class MommandLine {
    public static final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            builder.command("cmd.exe", "/c", "dir");
        } else {
            builder.command("sh", "-c", "pwd");
        }
        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();
        new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(System.out::println);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }
}
