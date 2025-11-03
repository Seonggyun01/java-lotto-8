package lotto.domain.exception;

public enum NumErrorMessage {
    EMPTY_INPUT("[ERROR] 번호를 입력해주세요."),
    INVALID_DECIMAL("[ERROR] 정수를 입력해주세요."),
    CONTAINS_CHARACTER("[ERROR] 번호에 문자는 입력할 수 없습니다."),
    ZERO_NOT_ALLOWED("[ERROR] 번호에 0을 입력할 수 없습니다."),
    OUT_OF_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_NUMBER("[ERROR] 로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    WINNING_NUMBER_SIZE("[ERROR] 당첨 번호는 6개여야 합니다."),
    BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE("[ERROR] 보너스 번호가 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    NumErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
