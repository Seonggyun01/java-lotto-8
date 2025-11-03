package lotto.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.ResultRecord;
import lotto.domain.WinningNumbers;
import lotto.domain.Yield;

public class ResultService {

    /**
     * 로또 결과 전송 dto
     * @param resultRecord
     * @param yieldPercent
     */
    public static record Summary(
            ResultRecord resultRecord,
            BigDecimal yieldPercent
    ){}

    /**
     * Summary 생성 (로또 결과 및 수익률)
     * @param lottos
     * @param winningNumbers
     * @param bonusNumber
     * @param money
     * @return
     */
    public Summary calculateSummary(List<Lotto> lottos, WinningNumbers winningNumbers,
                       BonusNumber bonusNumber, Money money){
        ResultRecord resultRecord = evaluate(lottos,winningNumbers,bonusNumber);
        Long totalPrize = calculateTotalPrize(resultRecord);
        BigDecimal yield = calculateYield(totalPrize, money);

        return new Summary(resultRecord, yield);
    }

    /**
     * 로또 게임 결과 ResultRecord에 저장
     * @param lottos
     * @param winningNumbers
     * @param bonusNumber
     * @return
     */
    public ResultRecord evaluate(List<Lotto> lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber){
        ResultRecord resultRecord = new ResultRecord();

        for(Lotto lotto: lottos){
            checkLottoRank(winningNumbers, bonusNumber, lotto, resultRecord);
        }
        return resultRecord;
    }

    /**
     * 각 로또 결과를 ResultRecord.record()
     * @param winningNumbers
     * @param bonusNumber
     * @param lotto
     * @param resultRecord
     */
    private static void checkLottoRank(WinningNumbers winningNumbers, BonusNumber bonusNumber, Lotto lotto,
                                  ResultRecord resultRecord) {
        int matchCount = lotto.compare(winningNumbers);
        boolean checkBonusNumber = lotto.checkBonusNumber(bonusNumber.getNumber());
        Rank rank = Rank.checkRank(matchCount,checkBonusNumber);

        resultRecord.record(rank);
    }

    /**
     * 전체 상금 계산
     * @param resultRecord
     * @return
     */
    public Long calculateTotalPrize(ResultRecord resultRecord){
        Long total = 0L;

        for(Map.Entry<Rank, Integer> entry: resultRecord.getMap().entrySet()){
            Rank rank = entry.getKey();
            int count = entry.getValue();
            total += rank.getPrize() * count;
        }
        return total;
    }

    /**
     * 수익률 계산
     * @param totalPrize
     * @param money
     * @return
     */
    public BigDecimal calculateYield(Long totalPrize, Money money){
        return Yield.calculate(totalPrize, money.getMoney());
    }
}
