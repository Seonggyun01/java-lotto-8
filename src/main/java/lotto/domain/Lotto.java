package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lotto.domain.exception.NumErrorMessage;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int MATCH = 1;
    private static final int NOT_MATCH = 0;
    private static final int INITIAL_MATCH_COUNT = 0;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        List<Integer> copy = new ArrayList<>(numbers);
        validate(copy);
        validateNumber(copy);
        validateDuplicate(numbers);
        sortNumbers(copy);
        this.numbers = copy;
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(NumErrorMessage.DUPLICATE_NUMBER.getMessage());
        }
    }

    private static void validateNumber(List<Integer> numbers) {
        for (int num : numbers) {
            NumberPolicy.validate(num);
        }
    }

    /**
     * 로또 번호와 당첨 번호를 비교하여 일치하는 개수를 반환한다.
     *
     * @param winningNumbers 당첨 번호 목록
     * @return 일치하는 번호의 개수 (0 ~ 6)
     */
    public int compare(WinningNumbers winningNumbers) {
        int matchCount = INITIAL_MATCH_COUNT;
        for (Integer winningNum : winningNumbers.getNumbers()) {
            matchCount += checkWinningNumber(winningNum);
        }
        return matchCount;
    }


    private int checkWinningNumber(Integer winningNum) {
        if (numbers.contains(winningNum)) {
            return MATCH;
        }
        return NOT_MATCH;
    }

    /**
     * 로또 번호에 보너스 번호 있는지 확인하는 함수
     *
     * @param bonusNum 보너스 번호
     * @return 존재 유무 (T or F)
     */
    public boolean checkBonusNumber(int bonusNum) {
        if (numbers.contains(bonusNum)) {
            return true;
        }
        return false;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(NumErrorMessage.LOTTO_NUMBER_SIZE.getMessage());
        }
    }
    
    private static void sortNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
