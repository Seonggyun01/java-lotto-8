package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.exception.NumErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @DisplayName("1~45범의 숫자 정상 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20, 30, 45})
    void 정상_생성_테스트_1에서_45사이_테스트(int n) {
        assertSimpleTest(() -> {
            BonusNumber bonusNumber = new BonusNumber(n);
            assertThat(bonusNumber.getNumber()).isEqualTo(n);
        });
    }

    @Test
    @DisplayName("0 이하의 숫자는 예외가 발생한다")
    void 범위_예외_테스트_0이하_테스트() {
        assertThatThrownBy(() -> new BonusNumber(0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.ZERO_NOT_ALLOWED.getMessage());

        assertThatThrownBy(() -> new BonusNumber(-3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @Test
    @DisplayName("45 초과 숫자는 예외가 발생한다")
    void 범위_예외_테스트_45초과_테스트() {
        assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }
}