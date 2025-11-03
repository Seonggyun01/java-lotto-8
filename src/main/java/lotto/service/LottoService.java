package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class LottoService {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    /**
     * 수량만큼 로또 생성
     * @param count
     * @return
     */
    public List<Lotto> createLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = lottoNumberGenerator.generate();
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }
}
