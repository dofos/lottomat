package pl.devello.lottomat;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottomat {
    static final int KENO_MAXIMUM_SET_SIZE = 10;
    static final int KENO_MINIMUM_SET_SIZE = 1;

    private Random randomGenerator;

    static final int KENO_POOL_SIZE = 70;
    public Lottomat(Random random) {
        randomGenerator = random;
    }

    public Stream<List<String>> kenoLuckyNumbers(int setSize, int noGames) {
        final int _setSize;

        if(setSize > KENO_MAXIMUM_SET_SIZE) {
            _setSize = KENO_MAXIMUM_SET_SIZE;
        } else if (setSize < KENO_MINIMUM_SET_SIZE) {
            _setSize = KENO_MINIMUM_SET_SIZE;
        } else {
            _setSize = setSize;
        }

        return Stream.
            generate(
                ()->generate(_setSize, KENO_POOL_SIZE)
            ).
            parallel().
            limit(noGames);
    }

    private  List<String> generate(int setSize, int poolSize) {
        return randomGenerator.
            ints().
            parallel().
            map(
                (i)->{
                    int luckyNo = Math.abs(i) % poolSize;
                    luckyNo++;

                    return luckyNo;
                }
            ).
            distinct().
            limit(setSize).
            sorted().
            mapToObj((i)->String.format("%d", i)).
            collect(
                Collectors.toList()
            );
    }
}
