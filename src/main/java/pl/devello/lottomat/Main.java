package pl.devello.lottomat;

import java.util.Random;

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

        int noSets = args.length == 0 ? 1 : Integer.parseInt(args[0], 10);
        int setSize = args.length <=1 ? 10: Integer.parseInt(args[1], 10);

        new Lottomat(randomGenerator).
            kenoLuckyNumbers(setSize, noSets).
            forEach(
                (set)->{
                    System.out.println(String.join(" ", set));
                }
            );
    }
}
