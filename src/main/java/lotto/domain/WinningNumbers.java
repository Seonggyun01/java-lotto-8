package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private static final int WINNING_NUMBER_SIZE = 6;
    private final List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        validate(numbers);
        validateNumber(numbers);
        this.numbers = numbers;
    }
    private static void validateNumber(List<Integer> numbers) {
        for (int num : numbers) {
            NumberPolicy.validate(num);
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
