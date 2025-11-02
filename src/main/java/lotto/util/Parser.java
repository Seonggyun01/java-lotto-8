package lotto.util;

import static java.util.List.copyOf;

import java.util.List;
import java.util.stream.Stream;

public class Parser {
    private Parser() {
    }

    /**
     * 당첨 번호 쉽표 기준으로 나누기
     *
     * @param rawWinningNumbers String
     * @return List<Integer>
     */
    public static List<String> parse(String rawWinningNumbers) {
        List<String> parsedWinningNumbers = Stream.of(rawWinningNumbers.split(",", -1))
                .map(String::trim)
                .toList();

        return copyOf(parsedWinningNumbers);
    }
}
