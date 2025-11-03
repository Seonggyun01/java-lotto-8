package lotto.domain;

import lotto.domain.exception.MoneyErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @DisplayName("구입 금액이 음수면 예외가 발생한다.")
    @Test
    void 음수_입력_예외_테스트() {
        assertThatThrownBy(() -> new Money(-1000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(MoneyErrorMessage.NEGATIVE_INPUT.getMessage());
    }

    @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다.")
    @Test
    void 최소금액_미만_예외_테스트() {
        assertThatThrownBy(() -> new Money(500))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(MoneyErrorMessage.BELOW_MINIMUM.getMessage());
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 천원단위_아님_예외_테스트() {
        assertThatThrownBy(() -> new Money(2500))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(MoneyErrorMessage.INVALID_UNIT.getMessage());
    }

    @DisplayName("정상 입력 시 Money 객체가 생성된다.")
    @Test
    void 정상_입력_테스트() {
        Money money = new Money(5000);
        assertThat(money.getMoney()).isEqualTo(5000);
    }

    @DisplayName("구입 금액에 따라 구매 가능한 로또 개수가 계산된다.")
    @Test
    void 로또_구매_개수_확인_테스트() {
        Money money = new Money(3000);
        assertThat(money.buyLotto()).isEqualTo(3);
    }
}
