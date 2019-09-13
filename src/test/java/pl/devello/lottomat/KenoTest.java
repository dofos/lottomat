package pl.devello.lottomat;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KenoTest {
    @Test
    void kenoLuckyNumbersCanBe1To10() {
        Lottomat lottomat = new Lottomat(new Random(1));
        {
            Stream<List<String>> luckyNumbers = lottomat.kenoLuckyNumbers(1, 1);
            List<List<String>> luckySets = luckyNumbers.collect(Collectors.toList());

            assertEquals(1, luckySets.size());
            assertEquals(1, luckySets.get(0).size());
        }
        {
            Stream<List<String>> luckyNumbers = lottomat.kenoLuckyNumbers(2, 1);
            List<List<String>> luckySets = luckyNumbers.collect(Collectors.toList());

            assertEquals(2, luckySets.get(0).size());
        }
        {
            Stream<List<String>> luckyNumbers = lottomat.kenoLuckyNumbers(9, 1);
            List<List<String>> luckySets = luckyNumbers.collect(Collectors.toList());

            assertEquals(9, luckySets.get(0).size());
        }
    }
    @Test
    void specifyingMoreThan10NumbersDefaultsTo10() {
        Lottomat lottomat = new Lottomat(new Random(1));
        Stream<List<String>> luckyNumbers = lottomat.kenoLuckyNumbers(11, 1);
        List<List<String>> luckySets = luckyNumbers.collect(Collectors.toList());

        assertEquals(10, luckySets.get(0).size());
    }

    @Test
    void specifyingLessThen1NumberDefaultsTo1() {
        Lottomat lottomat = new Lottomat(new Random(1));
        Stream<List<String>> luckyNumbers = lottomat.kenoLuckyNumbers(0, 1);
        List<List<String>> luckySets = luckyNumbers.collect(Collectors.toList());

        assertEquals(1, luckySets.get(0).size());
    }


}
