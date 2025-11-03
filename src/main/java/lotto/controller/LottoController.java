package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.WinningNumbers;
import lotto.dto.FinalResultDto;
import lotto.service.LottoCoordinator;
import lotto.service.LottoCoordinator.PurchaseResult;
import lotto.service.LottoNumberGenerator;
import lotto.service.LottoService;
import lotto.service.NumberParserService;
import lotto.service.PurchaseService;
import lotto.service.ResultService;
import lotto.service.WinningNumbersService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoCoordinator lottoCoordinator;

    public LottoController() {
        this.lottoCoordinator = new LottoCoordinator(
                new PurchaseService(),
                new LottoService(new LottoNumberGenerator()),
                new WinningNumbersService(),
                new ResultService(),
                new NumberParserService()
        );
    }

    public void run() {

        //구입 금액 입력 파트
        PurchaseResult purchaseResult = askPurchase();
        OutputView.printLineBreak();
        OutputView.printPurchaseResult(purchaseResult.purchasedDto().count());
        OutputView.printPurchaseLottoFormat(purchaseResult.purchasedDto());

        //당첨 번호 입력
        WinningNumbers winningNumbers = askWinningNumbers();

        //보너스 번호 입력
        BonusNumber bonusNumber = askBonusNumber(winningNumbers);

        FinalResultDto finalResultDto = lottoCoordinator.finalizeResult(purchaseResult, winningNumbers, bonusNumber);
        OutputView.printResultHeader();
        OutputView.printResultDivider();
        OutputView.printRankResult(finalResultDto);
        OutputView.printYieldFormat(finalResultDto.yieldPercent());
    }

    private BonusNumber askBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                OutputView.printLineBreak();
                OutputView.printAskBonusNumber();
                String rawBonusNumber = InputView.readBonusNumber();
                return lottoCoordinator.parseBonusNumber(rawBonusNumber, winningNumbers);
            }catch (Exception e){
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningNumbers askWinningNumbers() {
        while (true) {
            try {
                OutputView.printLineBreak();
                OutputView.printAskWinningNumbers();
                String rawWinningNumbers = InputView.readWinningNumbers();
                return lottoCoordinator.parseAndBuildWinningNumbers(rawWinningNumbers);
            } catch (Exception e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private PurchaseResult askPurchase() {
        while (true) {
            try {
                OutputView.printAskMoney();
                String raw = InputView.readMoney();
                int money = lottoCoordinator.parseMoney(raw);
                return lottoCoordinator.purchase(money);
            } catch (IllegalArgumentException | IllegalStateException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
