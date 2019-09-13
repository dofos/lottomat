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
        //given the implicit call to beforeEach annotated method is done

        //when
        System.out.println("Tytus");
        System.out.println("Romek");
        System.out.println("i A'Tomek");

        //Then
        assertEquals("Tytus\nRomek\ni A'Tomek\n", byteOutputStream.toString());
    }

    @Test
    void itPrintsMessageAboutLuckyNumbers() throws IOException {
        //given
        Main.main(new String[]{});

        //when
        String line = readFirstLine();

        //then
        assertTrue(line.startsWith("Your happy numbers are:"));
    }

    @Test
    void nextItPrintsYourTenLuckyKenoNumbers() throws IOException {
        //given
        Main.main(new String[]{});

        //when
        String line = readSecondLine();

        //then
        assertEquals(10, line.split(" ").length);
    }

    @Test
    void theNumbersCannotRepeat() throws IOException {
        //given
        Main.main(new String[]{});

        //when
        String line = readSecondLine();

        //then
        Set<String> targetSet = new HashSet<String>();
        Collections.addAll(targetSet, line.split(" "));
        assertEquals(10, targetSet.size());
    }

    @Test
    void iCanGet2SetsOfLuckyNumbers() throws IOException {
        //given
        Main.main(new String[]{"2"});
        //when
        String line = readThirdLine();

        //then
        Set<String> targetSet = new HashSet<String>();
        Collections.addAll(targetSet, line.split(" "));

        assertEquals(10, targetSet.size());
    }
    @Test
    void iCanGet3x3SetsOfLuckyNumbers() throws IOException {
        //given
        Main.main(new String[]{"3", "3"});
        //when
        String line = readThirdLine();

        //then
        //then
        Set<String> targetSet = new HashSet<String>();
        Collections.addAll(targetSet, line.split(" "));

        assertEquals(3, targetSet.size());
    }

    private String readFirstLine() throws IOException {
        return readNthLine(1);
    }

    private String readSecondLine() throws IOException {
        return readNthLine(2);
    }

    private String readThirdLine() throws IOException {
        return readNthLine(3);
    }

    private String readNthLine(int lineIndex) throws IOException {
        BufferedReader br = getReader();
        String line = null;
        while (lineIndex-- > 0) {
            line = br.readLine();
        }

        return line;
    }

    private BufferedReader getReader() {
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                byteOutputStream.toByteArray()
                        )
                )
        );
    }
}
