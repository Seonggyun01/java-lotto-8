package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Yield {
    private static final String PERCENT_MULTIPLIER = "100";
    private static final int DECIMAL_SCALE = 1;
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
                .multiply(new BigDecimal(PERCENT_MULTIPLIER))
                .divide(moneyDecimal, DECIMAL_SCALE, RoundingMode.HALF_UP);
    }
}
