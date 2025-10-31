package lotto.domain;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        NumberPolicy.validate(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
