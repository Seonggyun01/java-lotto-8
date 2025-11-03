package lotto.domain;

import lotto.domain.exception.NumErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.LOTTO_NUMBER_SIZE.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.DUPLICATE_NUMBER.getMessage());
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개가_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NumErrorMessage.LOTTO_NUMBER_SIZE.getMessage());
    }

    @DisplayName("숫자에 0이 포함되면 예외가 발생한다.")
    @Test
    void 숫자에_0이_포함되면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.ZERO_NOT_ALLOWED.getMessage());
    }

    @DisplayName("숫자가 45를 초과하면 예외가 발생한다.")
    @Test
    void 숫자가_45를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(NumErrorMessage.OUT_OF_RANGE.getMessage());
    }

    @DisplayName("번호는 오름차순 정렬된다.")
    @Test
    void 번호는_오름차순으로_정렬된다() {
        Lotto lotto = new Lotto(List.of(8, 1, 45, 3, 4, 10));
        assertThat(lotto.getNumbers()).containsExactly(1, 3, 4, 8, 10, 45);
    }

}
