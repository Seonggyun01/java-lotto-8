package lotto.domain.exception;

public enum NumErrorMessage {
    EMPTY_INPUT("번호를 입력해주세요."),
    INVALID_DECIMAL("정수를 입력해주세요."),
    CONTAINS_CHARACTER("번호에 문자는 입력할 수 없습니다."),
    ZERO_NOT_ALLOWED("번호에 0을 입력할 수 없습니다."),
    OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다.");

    private final String message;

    NumErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
