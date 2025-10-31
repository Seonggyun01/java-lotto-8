package lotto.domain;

import lotto.domain.exception.MoneyErrorMessage;

public class Money {
    private static final int MIN_MONEY = 1000;
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    public void validate(int money){
        // 숫자에 음수가 들어있는 경우 “구입 금액에 음수는 입력할수 없습니다”
        if (money < 0) {
            throw new IllegalStateException(MoneyErrorMessage.NEGATIVE_INPUT.getMessage());
        }

        // 1000원 보다 작은 경우 “1,000원 이상을 입력해주세요.”
        if (money < MIN_MONEY) {
            throw new IllegalStateException(MoneyErrorMessage.BELOW_MINIMUM.getMessage());
        }

        // 1,000으로 나누어 떨어지지 않을 경우 “구입 금액은 1,000원 단위입니다.”
        if (money % MIN_MONEY != 0) {
            throw new IllegalStateException(MoneyErrorMessage.INVALID_UNIT.getMessage());
        }
    }

    public int buyLotto() {
        return money / 1000;
    }
}
