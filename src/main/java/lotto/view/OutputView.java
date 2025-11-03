package lotto.view;

import java.util.List;
import java.util.Objects;
import lotto.domain.Rank;
import lotto.dto.FinalResultDto;
import lotto.dto.PurchasedDto;
import lotto.dto.RankResultDto;

public class OutputView {
    public static final String ASK_MONEY = "구입금액을 입력해 주세요.";
    public static final String ASK_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    public static final String ASK_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    public static final String PURCHASE_RESULT = "%d개를 구매했습니다.";
    public static final String PURCHASE_LOTTO_FORMAT = "[%d, %d, %d, %d, %d, %d]";
    public static final String RESULT_HEADER = "당첨 통계";
    public static final String RESULT_DIVIDER = "---";
    public static final String YIELD_FORMAT = "총 수익률은 %s입니다.";
    public static final String DEFAULT_RESULT_FORMAT = "%d개 일치 (%,d원) - %d개";
    public static final String SECOND_RESULT_FORMAT = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    public static final Long SECOND_PRIZE = 30_000_000L;


    public static void printAskMoney() {
        System.out.println(ASK_MONEY);
    }

    public static void printAskWinningNumbers() {
        System.out.println(ASK_WINNING_NUMBERS);
    }

    public static void printAskBonusNumber() {
        System.out.println(ASK_BONUS_NUMBER);
    }

    public static void printPurchaseResult(int count) {
        System.out.println(String.format(PURCHASE_RESULT, count));
    }

    public static void printPurchaseLottoFormat(PurchasedDto lottoDto) {
        for (List<Integer> numbers : lottoDto.lottos()) {
            System.out.println(String.format(PURCHASE_LOTTO_FORMAT,
                    numbers.get(0),
                    numbers.get(1),
                    numbers.get(2),
                    numbers.get(3),
                    numbers.get(4),
                    numbers.get(5)));
        }
    }

    public static void printResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public static void printResultDivider() {
        System.out.println(RESULT_DIVIDER);
    }

    public static void printRankResult(FinalResultDto finalResultDto) {
        for (RankResultDto rankResultDto : finalResultDto.ranks()) {
            if(Objects.equals(rankResultDto.prize(), SECOND_PRIZE)){
                System.out.println(String.format(SECOND_RESULT_FORMAT,
                        rankResultDto.matchCount(),
                        rankResultDto.prize(),
                        rankResultDto.count()
                ));
                continue;
            }
            System.out.println(String.format(DEFAULT_RESULT_FORMAT,
                    rankResultDto.matchCount(),
                    rankResultDto.prize(),
                    rankResultDto.count()
            ));
        }
    }

    public static void printYieldFormat(String yield) {
        System.out.println(String.format(YIELD_FORMAT, yield));
    }

    public static void printErrorMessage(String message){
        System.out.println(message);
    }

    public static void printLineBreak(){
        System.out.println();
    }
}
