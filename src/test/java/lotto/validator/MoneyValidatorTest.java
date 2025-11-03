package lotto.validator;

import lotto.domain.exception.MoneyErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyValidatorTest {

    @DisplayName("null이면 예외가 발생한다.")
    @Test
    void null_입력_예외_테스트() {
        assertThatThrownBy(() -> MoneyValidator.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.EMPTY_INPUT.getMessage());
    }

    @DisplayName("빈 문자열이면 예외가 발생한다.")
    @Test
    void 빈문자열_예외_테스트() {
        assertThatThrownBy(() -> MoneyValidator.validate(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.EMPTY_INPUT.getMessage());
    }

    @DisplayName("공백만 입력되면 예외가 발생한다.")
    @Test
    void 공백_입력_예외_테스트() {
        assertThatThrownBy(() -> MoneyValidator.validate("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.EMPTY_INPUT.getMessage());
    }

    @DisplayName("소수점이 포함되면 예외가 발생한다.")
    @Test
    void 소수점_입력_예외_테스트() {
        assertThatThrownBy(() -> MoneyValidator.validate("1000.5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.INVALID_DECIMAL.getMessage());
    }

    @DisplayName("문자가 포함되면 예외가 발생한다.")
    @Test
    void 숫자_외_문자_입력_예외_테스트() {
        assertThatThrownBy(() -> MoneyValidator.validate("1000a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.CONTAINS_CHARACTER.getMessage());
    }

    @DisplayName("숫자가 너무 크면 예외가 발생한다.")
    @Test
    void int범위_초과_예외_테스트() {
        assertThatThrownBy(() -> MoneyValidator.validate("999999999999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MoneyErrorMessage.TOO_LARGE.getMessage());
    }

    @DisplayName("정상 숫자는 trim 후 정수로 반환된다.")
    @Test
    void 앞뒤_공백_trim_정수_반환() {
        int result = MoneyValidator.validate(" 2000 ");
        assertThat(result).isEqualTo(2000);
    }

    @DisplayName("음수 문자열은 정수로 변환되어 반환된다. (금액 검증은 Money 클래스에서 처리)")
    @Test
    void 음수_문자열_변환_테스트() {
        int result = MoneyValidator.validate("-1000");
        assertThat(result).isEqualTo(-1000);
    }
}
