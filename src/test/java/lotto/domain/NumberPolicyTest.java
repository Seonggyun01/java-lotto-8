package lotto.domain;

import lotto.domain.exception.NumErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NumberPolicyTest {

    @DisplayName("0을 입력하면 예외가 발생한다.")
    @Test
    void zero_input_throws_exception() {
        assertThatThrownBy(() -> NumberPolicy.validate(0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.ZERO_NOT_ALLOWED.getMessage());
    }

    @DisplayName("음수를 입력하면 예외가 발생한다.")
    @Test
    void negative_number_throws_exception() {
        assertThatThrownBy(() -> NumberPolicy.validate(-5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @DisplayName("45 초과 입력 시 예외가 발생한다.")
    @Test
    void above_max_range_throws_exception() {
        assertThatThrownBy(() -> NumberPolicy.validate(50))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @DisplayName("1 미만 입력 시 예외가 발생한다.")
    @Test
    void below_min_range_throws_exception() {
        assertThatThrownBy(() -> NumberPolicy.validate(0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.ZERO_NOT_ALLOWED.getMessage());

        assertThatThrownBy(() -> NumberPolicy.validate(-1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @DisplayName("1~45 범위의 숫자는 예외가 발생하지 않는다.")
    @Test
    void valid_range_passes() {
        assertThatCode(() -> NumberPolicy.validate(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> NumberPolicy.validate(45))
                .doesNotThrowAnyException();

        assertThatCode(() -> NumberPolicy.validate(23))
                .doesNotThrowAnyException();
    }
}
