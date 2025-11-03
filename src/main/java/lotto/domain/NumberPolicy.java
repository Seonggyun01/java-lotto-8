package lotto.domain;

import lotto.domain.exception.NumErrorMessage;

public class NumberPolicy {
    private static final int MIN_NUMBER_RANGE = 1;
    private static final int MAX_NUMBER_RANGE = 45;
    private static final int ZERO_NUMBER = 0;

    private NumberPolicy() {
    }

    public static void validate(int number) {
        //숫자에 0이 들어있는 경우 “번호에 0을 입력할 수 없습니다.”
        if (number == ZERO_NUMBER) {
            throw new IllegalStateException(NumErrorMessage.ZERO_NOT_ALLOWED.getMessage());
        }
        // 숫자가 범위를 벗어난 경우 “로또 번호는 1부터 45 사이의 숫자여야 합니다.”
        if (number < MIN_NUMBER_RANGE || number > MAX_NUMBER_RANGE) {
            throw new IllegalStateException(NumErrorMessage.OUT_OF_RANGE.getMessage());
        }
    }
}
