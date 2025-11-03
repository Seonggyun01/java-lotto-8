package lotto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.ResultRecord;
import lotto.domain.WinningNumbers;
import lotto.dto.FinalResultDto;
import lotto.dto.PurchasedDto;
import lotto.dto.RankResultDto;
import lotto.service.ResultService.Summary;
import lotto.validator.MoneyValidator;

public class LottoCoordinator {
    private final LottoService lottoService;
    private final WinningNumbersService winningNumbersService;
    private final ResultService resultService;
    private final NumberParserService numberParserService;

    public LottoCoordinator(LottoService lottoService,
                            WinningNumbersService winningNumbersService, ResultService resultService,
                            NumberParserService numberParserService) {
        this.lottoService = lottoService;
        this.winningNumbersService = winningNumbersService;
        this.resultService = resultService;
        this.numberParserService = numberParserService;
    }

    public int parseMoney(String rawMoney) {
        return MoneyValidator.validate(rawMoney);
    }

    public PurchaseResult purchase(int moneyAmount) {
        Money money = new Money(moneyAmount);
        int lottoCount = money.buyLotto();

        List<Lotto> lottos = lottoService.createLottos(lottoCount);

        List<List<Integer>> purchasedNumbers = new ArrayList<>();
        for (Lotto lotto : lottos) {
            purchasedNumbers.add(lotto.getNumbers());
        }
        PurchasedDto purchasedDto = new PurchasedDto(purchasedNumbers, lottoCount);
        return new PurchaseResult(purchasedDto, lottos, money);
    }

    public record PurchaseResult(
            PurchasedDto purchasedDto,
            List<Lotto> lottos,
            Money money
    ) {
    }

    public WinningNumbers parseAndBuildWinningNumbers(String rawWinningNumbers){
        //당첨 번호 검증 및 생성
        List<Integer> validatedNumbers = numberParserService.parseNumbers(rawWinningNumbers);
        return winningNumbersService.createWinningNumber(validatedNumbers);
    }

    public BonusNumber parseBonusNumber(String rawBonusNumber, WinningNumbers winningNumbers){
        //보너스 번호 검증 및 생성 및 당첨 번호와 중복 체크
        int validateBonusNum = numberParserService.parseSingleNumber(rawBonusNumber);
        winningNumbersService.ensureBonusNotDuplicated(winningNumbers, validateBonusNum);
        return new BonusNumber(validateBonusNum);
    }

    public FinalResultDto finalizeResult(PurchaseResult purchaseResult, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        List<Lotto> lottos = purchaseResult.lottos();
        Money money = purchaseResult.money();

        Summary summary = resultService.calculateSummary(lottos, winningNumbers, bonusNumber, money);

        return toFinalResultDto(summary);
    }

    private FinalResultDto toFinalResultDto(Summary summary) {
        ResultRecord resultRecord = summary.resultRecord();
        BigDecimal yieldPercent = summary.yieldPercent();

        Map<Rank, Integer> resultRecordMap = resultRecord.getMap();
        List<RankResultDto> ranks = new ArrayList<>();

        addRankDto(ranks, resultRecordMap, Rank.FIFTH);
        addRankDto(ranks, resultRecordMap, Rank.FOURTH);
        addRankDto(ranks, resultRecordMap, Rank.THIRD);
        addRankDto(ranks, resultRecordMap, Rank.SECOND);
        addRankDto(ranks, resultRecordMap, Rank.FIRST);

        String yieldText = yieldPercent.toPlainString() + "%";

        return new FinalResultDto(ranks, yieldText);
    }

    private static void addRankDto(List<RankResultDto> list, Map<Rank, Integer> map, Rank rank) {
        if (rank == Rank.FAIL) {
            return;
        }
        int count = map.getOrDefault(rank, 0);

        list.add(new RankResultDto(rank.getMatchCount(), rank.getPrize(), count));
    }
}
