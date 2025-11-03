package lotto.domain;

import lotto.domain.exception.NumErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @DisplayName("당첨 번호가 6개를 초과하면 예외가 발생한다.")
    @Test
    void 당첨번호_개수_초과_예외_테스트() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호가 6개 미만이면 예외가 발생한다.")
    @Test
    void 당첨번호_개수_미만_예외_테스트() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 당첨번호_중복_예외_테스트() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.DUPLICATE_NUMBER.getMessage());
    }

    @DisplayName("번호는 오름차순 정렬된다.")
    @Test
    void 오름차순_정렬_테스트() {
        WinningNumbers winning = new WinningNumbers(List.of(8, 1, 45, 3, 4, 10));
        assertThat(winning.getNumbers()).containsExactly(1, 3, 4, 8, 10, 45);
    }

    @DisplayName("0이 포함되면 NumberPolicy에 의해 예외가 발생한다.")
    @Test
    void 당첨번호_제로_포함_예외_테스트() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.ZERO_NOT_ALLOWED.getMessage());
    }

    @DisplayName("범위를 벗어난 숫자(>45)가 포함되면 NumberPolicy에 의해 예외가 발생한다.")
    @Test
    void 당첨번호_범위초과_예외_테스트() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @DisplayName("음수가 포함되면 NumberPolicy에 의해 예외가 발생한다.")
    @Test
    void 당첨번호_음수_포함() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(-1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @DisplayName("경계값 1과 45는 유효하다.")
    @Test
    void 경계값_테스트_1과_45_입력_테스트() {
        WinningNumbers winning = new WinningNumbers(List.of(1, 10, 20, 30, 40, 45));
        assertThat(winning.getNumbers()).containsExactly(1, 10, 20, 30, 40, 45);
    }
}
