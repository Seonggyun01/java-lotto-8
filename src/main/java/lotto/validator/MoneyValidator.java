package lotto.validator;

import java.util.regex.Pattern;
import lotto.domain.exception.MoneyErrorMessage;

public class MoneyValidator {
    private static final String DECIMAL_POINT = ".";
    private static final Pattern DIGITS_ONLY = Pattern.compile("^-?[0-9]+$");

    private MoneyValidator() {
    }

    /**
     * 구입 금액 검증 메서드
     *
     * @param rawMoney
     */
    public static int validate(String rawMoney) {
        if (rawMoney == null || rawMoney.isBlank()) {
            throw new IllegalArgumentException(MoneyErrorMessage.EMPTY_INPUT.getMessage());
        }

        String parsedRawMoney = rawMoney.trim();

        if (parsedRawMoney.contains(DECIMAL_POINT)) {
            throw new IllegalArgumentException(MoneyErrorMessage.INVALID_DECIMAL.getMessage());
        }

        if (!DIGITS_ONLY.matcher(parsedRawMoney).matches()) {
            throw new IllegalArgumentException(MoneyErrorMessage.CONTAINS_CHARACTER.getMessage());
        }

        try {
            int money = Integer.parseInt(parsedRawMoney);
            return money;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MoneyErrorMessage.TOO_LARGE.getMessage());
        }
    }
}
