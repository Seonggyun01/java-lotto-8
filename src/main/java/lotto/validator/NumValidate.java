package lotto.validator;

import java.util.regex.Pattern;
import lotto.domain.exception.NumErrorMessage;

public class NumValidate {
    private static final String DECIMAL_POINT = ".";
    private static final Pattern DIGIT_ONLY = Pattern.compile("^-?[0-9]+$");

    private NumValidate() {
    }

    /**
     * 번호 검증 메서드
     *
     * @param rawNumber
     */
    public static int validate(String rawNumber) {
        if (rawNumber == null || rawNumber.isBlank()) {
            throw new IllegalArgumentException(NumErrorMessage.EMPTY_INPUT.getMessage());
        }

        String parsedRawNumber = rawNumber.trim();

        if (rawNumber.contains(DECIMAL_POINT)) {
            throw new IllegalArgumentException(NumErrorMessage.INVALID_DECIMAL.getMessage());
        }

        if (!DIGIT_ONLY.matcher(rawNumber).matches()) {
            throw new IllegalArgumentException(NumErrorMessage.CONTAINS_CHARACTER.getMessage());
        }

        try {
            return Integer.parseInt(parsedRawNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NumErrorMessage.OUT_OF_RANGE.getMessage());
        }
    }
}
