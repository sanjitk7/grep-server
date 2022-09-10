package com.grepservermp.app;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.unix4j.unix.Grep;
import org.unix4j.unix.grep.GrepOptionSet_Fcilnvx;

public class GrepCoreTest {

    private String folderPath = "src/test/java/com/grepservermp/app/output/";
    private String logFilePath = folderPath + "sample.txt";

    /**
     * TC #1: Verify the argument parsing functionality of the grep module
     */
    @Test
    public void testArgParse() {
        String command = "grep -cFx GET " + logFilePath;
        GrepCore grepCore = new GrepCore(command);
        grepCore.doGrep();

        GrepOptionSet_Fcilnvx expectedOptions = Grep.Options.c.F.x;

        assertEquals(grepCore.getGrepFileName(), logFilePath);
        assertEquals(grepCore.getGrepPattern(), "GET");
        assertEquals(grepCore.getGrepOptions(), expectedOptions);
    }

    /**
     * TC #2: Verify the grep command output for -c flag (i.e. match count)
     */

    @Test
    public void testGrepOutputC() {
        String cmd = "grep -c GET " + logFilePath;
        GrepCore grepCore = new GrepCore(cmd);
        grepCore.doGrep();
        assertEquals(grepCore.getGrepResult(), "5");
    }

    /**
     * TC #3: Verify the grep command output for -L flag (i.e. file name)
     */

    @Test
    public void testGrepOutputL() {
        String cmd = "grep -l GET " + logFilePath;
        GrepCore grepCore = new GrepCore(cmd);
        grepCore.doGrep();
        assertEquals(grepCore.getGrepResult(), "./" + logFilePath);
    }

    /**
     * TC #4: Verify the grep command output for -F flag (i.e. Fixed pattern matching)
     */

    @Test
    public void testGrepOutputF() {
        String cmd = "grep -F GET " + logFilePath;
        GrepCore grepCore = new GrepCore(cmd);
        grepCore.doGrep();
        try {
            Path path = Paths.get(folderPath + "expected_output_F.txt");
            String expectedOutput = Files.readString(path, StandardCharsets.US_ASCII);
            assertEquals(grepCore.getGrepResult(), expectedOutput);
        } catch (IOException e) {
            System.out.print("IOException");
        }
    }

    /**
     * TC #5: Verify the grep command output for -v flag (i.e. inverse match)
     */

    @Test
    public void testGrepOutputV() {
        String cmd = "grep -v GET " + logFilePath;
        GrepCore grepCore = new GrepCore(cmd);
        grepCore.doGrep();
        try {
            Path path = Paths.get(folderPath + "expected_output_V.txt");
            String expectedOutput = Files.readString(path, StandardCharsets.US_ASCII);
            assertEquals(grepCore.getGrepResult(), expectedOutput);
        } catch (IOException e) {
            System.out.print("IOException");
        }
    }

    /**
     * TC #6: Verify the grep command output for -n flag (i.e. line number)
     */

    @Test
    public void testGrepOutputN() {
        String cmd = "grep -n GET " + logFilePath;
        GrepCore grepCore = new GrepCore(cmd);
        grepCore.doGrep();
        try {
            Path path = Paths.get(folderPath + "expected_output_n.txt");
            String expectedOutput = Files.readString(path, StandardCharsets.US_ASCII);
            assertEquals(grepCore.getGrepResult(), expectedOutput);
        } catch (IOException e) {
            System.out.print("IOException");
        }
    }
}
