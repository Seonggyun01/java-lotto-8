package lotto.domain.exception;

public enum MoneyErrorMessage {
    EMPTY_INPUT("[ERROR] 구입 금액을 입력해주세요."),
    INVALID_DECIMAL("[ERROR] 구입 금액은 1,000원 이상 자연수를 입력해 주세요."),
    CONTAINS_CHARACTER("[ERROR] 구입 금액에 문자는 입력할 수 없습니다."),
    TOO_LARGE("[ERROR] 너무 큰 금액입니다."),
    NEGATIVE_INPUT("[ERROR] 구입 금액에 음수는 입력할 수 없습니다."),
    BELOW_MINIMUM("[ERROR] 1,000원 이상 금액을 입력해주세요."),
    INVALID_UNIT("[ERROR] 구입 금액은 1,000원 단위입니다.");

    private String message;

    MoneyErrorMessage(String errorMessage) {
        this.message = errorMessage;
    }

    public String getMessage() {
        return message;
    }
}
