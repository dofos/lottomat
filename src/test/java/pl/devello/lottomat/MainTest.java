package pl.devello.lottomat;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    private ByteArrayOutputStream byteOutputStream;
    private PrintStream stdOut;
    @BeforeAll
    static void seedTheRandomness() {
        Main.setRandomGenerator(new Random(1));
    }
    @BeforeEach
    void setupTheStdOut() {
        byteOutputStream = new ByteArrayOutputStream();
        stdOut = System.out;
        System.setOut(new PrintStream(byteOutputStream));
    }

    @AfterEach
    void restoreTheStdOut() {
        System.setOut(stdOut);
    }

    @Test
    void IcanInteceptStdOut() {

        System.out.println("Tytus");
        System.out.println("Romek");
        System.out.println("i A'Tomek");

        assertEquals("Tytus\nRomek\ni A'Tomek\n", byteOutputStream.toString());
    }

    @Test
    void itPrintsMessageAboutLuckyNumbers() {
        Main.main(new String[]{});

        assertTrue(byteOutputStream.toString().startsWith("Your happy numbers are:\n"));
    }
    @Test
    void nextItPrintsYourTenLuckyKenoNumbers() throws IOException {
        Main.main(new String[]{});

        ByteArrayInputStream b = new ByteArrayInputStream(byteOutputStream.toByteArray());
        InputStreamReader reader = new InputStreamReader(b);

        BufferedReader br = new BufferedReader(reader);
        br.readLine();
        String line = br.readLine();

        assertEquals(10, line.split(" ").length);
    }
    @Test
    void theNumbersCannotRepeat() throws IOException {
        Main.main(new String[]{});

        ByteArrayInputStream b = new ByteArrayInputStream(byteOutputStream.toByteArray());
        InputStreamReader reader = new InputStreamReader(b);

        BufferedReader br = new BufferedReader(reader);
        br.readLine();
        String line = br.readLine();
        Set<String> targetSet = new HashSet<String>();
        Collections.addAll(targetSet, line.split(" "));
        assertEquals(10, targetSet.size());
    }

}
