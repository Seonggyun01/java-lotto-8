package lotto.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import lotto.domain.exception.NumErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumValidateTest {
    @DisplayName("null, 빈문자열, 공백 예외테스트")
    @ParameterizedTest
    @ValueSource(strings = { "", " ", "   " })
    void 공백관련_입력_예외_테스트(String raw) {
        assertThatThrownBy(() -> NumValidate.validate(raw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.EMPTY_INPUT.getMessage());
    }

    @Test
    @DisplayName("null 입력 예외")
    void null_입력_예외_테스트() {
        assertThatThrownBy(() -> NumValidate.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.EMPTY_INPUT.getMessage());
    }

    @DisplayName("소수 입력 예외")
    @ParameterizedTest
    @ValueSource(strings = {"1.0", "3.14", ".5", "0.", " 2.5"})
    void 소수_입력_예외_테스트(String raw) {
        assertThatThrownBy(() -> NumValidate.validate(raw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.INVALID_DECIMAL.getMessage());
    }

    @DisplayName("숫자 외 문자 포함 예외")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "1,2", "1_2", "+3", "--1"})
    void 숫자외_문자_포함_예외_테스트(String raw) {
        assertThatThrownBy(() -> NumValidate.validate(raw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.CONTAINS_CHARACTER.getMessage());
    }

    @DisplayName("int 범위 초과 예외")
    @Test
    void int범위_초과_예외_테스트() {
        assertThatThrownBy(() -> NumValidate.validate("999999999999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

}