package lotto.service;

import lotto.domain.Money;

public class PurchaseService {
    /**
     * 금액에 맞는 로또 수량 구하기
     * @param moneyAccount
     * @return
     */
    public int run(int moneyAccount){
        Money money = new Money(moneyAccount);
        return money.buyLotto();
    }
}
