package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class YieldTest {

    @DisplayName("수익이 투자금과 같으면 수익률은 100.0이다.")
    @Test
    void 수익률_100퍼센트_계산_테스트() {
        BigDecimal result = Yield.calculate(1000L, 1000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(100.0));
    }

    @DisplayName("수익이 투자금의 두 배이면 수익률은 200.0이다.")
    @Test
    void 수익률_200퍼센트_계산_테슽() {
        BigDecimal result = Yield.calculate(2000L, 1000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(200.0));
    }

    @DisplayName("수익이 투자금의 절반이면 수익률은 50.0이다.")
    @Test
    void 수익률_50퍼센트_계산_테스트() {
        BigDecimal result = Yield.calculate(500L, 1000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(50.0));
    }

    @DisplayName("수익이 0이면 수익률은 0.0이다.")
    @Test
    void 수익이_0일_때_수익률_0퍼센트_테스트() {
        BigDecimal result = Yield.calculate(0L, 1000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(0.0));
    }

    @DisplayName("소수점 둘째 자리에서 내림되는 경우 (33.34 → 33.3)")
    @Test
    void 소수점_둘째자리_내림_테스트() {
        BigDecimal result = Yield.calculate(3334L, 10000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(33.3));
    }


    @DisplayName("소수점 둘째 자리에서 반올림되는 경우 (33.35 → 33.4)")
    @Test
    void 소수점_둘째자리_반올림_테스트1() {
        BigDecimal result = Yield.calculate(3335L, 10000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(33.4));
    }

    @DisplayName("소수점 둘째 자리에서 올림되는 경우 (33.36 → 33.4)")
    @Test
    void 소수점_둘째자리_반올림_테스트2() {
        BigDecimal result = Yield.calculate(3336L, 10000);
        assertThat(result).isEqualTo(BigDecimal.valueOf(33.4));
    }

}
