package pl.devello.lottomat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    private ByteArrayOutputStream byteOutputStream;
    private PrintStream stdOut;

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

        assertEquals("Your happy numbers are:\n", byteOutputStream.toString());
    }
}
