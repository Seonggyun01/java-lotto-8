package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Yield {
    private Yield() {
    }

    /**
     * 수익률 계산(소수점 2자리에서 올림)
     *
     * @param winningPrize Long
     * @param money        int
     * @return 수입률 BigDecimal
     */
    public static BigDecimal calculate(Long winningPrize, int money) {
        BigDecimal winningPrizeDecimal = BigDecimal.valueOf(winningPrize);
        BigDecimal moneyDecimal = BigDecimal.valueOf(money);

        return winningPrizeDecimal
                .multiply(new BigDecimal("100"))
                .divide(moneyDecimal, 1, RoundingMode.HALF_UP);
    }
}
