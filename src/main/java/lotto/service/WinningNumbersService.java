package lotto.service;

import java.util.List;
import lotto.domain.WinningNumbers;
import lotto.domain.exception.NumErrorMessage;

public class WinningNumbersService {
    /**
     * 당첨 번호 생성
     * @param numbers
     * @return
     */
    public WinningNumbers createWinningNumber(List<Integer> numbers){
        return new WinningNumbers(numbers);
    }

    /**
     * 보너스 번호 생성(당첨 번호와 중복인지 체크)
     * @param winning
     * @param bonusNumber
     */
    public void ensureBonusNotDuplicated(WinningNumbers winning, int bonusNumber) {
        if (winning.getNumbers().contains(bonusNumber)) {   // winning.contains()메서드 추가?
            throw new IllegalStateException(NumErrorMessage.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE.getMessage());
        }
    }
}
