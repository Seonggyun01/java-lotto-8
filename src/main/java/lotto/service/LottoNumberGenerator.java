package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {
    private static final int LOTTO_NUMBER_START_RANGE = 1;
    private static final int LOTTO_NUMBER_LAST_RANGE = 45;
    private static final int LOTTO_SIZE = 6;

    /**
     * 로또 번호 랜덤 생성
     * @return
     */
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_START_RANGE, LOTTO_NUMBER_LAST_RANGE, LOTTO_SIZE);
    }
}
