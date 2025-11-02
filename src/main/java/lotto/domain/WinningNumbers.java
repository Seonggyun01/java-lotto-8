package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lotto.domain.exception.NumErrorMessage;

public class WinningNumbers {
    private static final int WINNING_NUMBER_SIZE = 6;
    private final List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        List<Integer> copy = new ArrayList<>(numbers);
        validate(copy);
        validateNumber(copy);
        validateDuplicate(numbers);
        sortNumbers(copy);
        this.numbers = copy;
    }

    private static void sortNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(NumErrorMessage.DUPLICATE_NUMBER.getMessage());
        }
    }

    private static void validateNumber(List<Integer> numbers) {
        for (int num : numbers) {
            NumberPolicy.validate(num);
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(NumErrorMessage.WINNING_NUMBER_SIZE.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
