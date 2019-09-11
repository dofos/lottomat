package pl.devello.lottomat;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    private static Random randomGenerator = null;
    public static Random setRandomGenerator(Random random) {
        randomGenerator = random;

        return random;
    }
    public static void main(String[] args) {
        if (randomGenerator==null) {
            randomGenerator = new Random();
        }
        System.out.println("Your happy numbers are:");
        List<String> luckyNumbers = randomGenerator.
                ints().
                map(
                        (i)->{
                            int luckyNo = Math.abs(i) % 70;
                            luckyNo++;

                            return luckyNo;
                        }
                ).
                distinct().
                limit(10).
                sorted().
                mapToObj((i)->String.format("%d", i)).
                collect(
                    Collectors.toList()
                );
        System.out.println(String.join(" ", luckyNumbers));
    }
}
