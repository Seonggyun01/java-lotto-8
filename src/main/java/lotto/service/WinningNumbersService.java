package lotto.service;

import java.util.List;
import lotto.domain.WinningNumbers;

public class WinningNumberService {

    public WinningNumbers createWinningNumber(List<Integer> numbers){
        return new WinningNumbers(numbers);
    }

    public void ensureBonusNotDuplicated(WinningNumbers winning, int bonusNumber) {
        if (winning.getNumbers().contains(bonusNumber)) {   // 도메인 메서드 사용 (권장)
            throw new IllegalStateException("보너스 번호가 당첨 6개와 중복될 수 없습니다.");
        }
    }
}
