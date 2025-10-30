package lotto.validator;

import java.util.Map;
import java.util.regex.Pattern;

public class MoneyValidate {
    private static final Pattern ALLOWED = Pattern.compile("^[0-9]+$");

    private MoneyValidate() {
    }

    /**
     * 구입 금액 검증 메서드
     *
     * @param rawMoney
     */
    public static void validate(String rawMoney) {
        int money;

        // 숫자 앞뒤 공백은 제거하고 사용한다(사용자 친화적). trim()
        String parsedRawMoney = rawMoney.trim();

        // 숫자에 소수가 들어있는 경우 “소수는 입력할수 없습니다.”
        if (parsedRawMoney.contains(".")) {
            throw new IllegalArgumentException("구입 금액은 1,000원 이상 자연수를 입력해주세요.");
        }

        // 숫자가 아닌 문자가 들어올 경우 “구입 금액에 문자는 입력할 수 없습니다.”
        if (!ALLOWED.matcher(parsedRawMoney).matches()) {
            throw new IllegalArgumentException("구입 금액에 숫자 외 문자는 입력할 수 없습니다.");
        }

        // int 사용 범위 초과 “너무 큰 금액입니다.”
        try {
            money = Integer.parseInt(parsedRawMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("너무 큰 금액입니다.");
        }

        // 숫자에 0이 들어있는 경우 “1,000원 이상을 입력해주세요.”
        if (money == 0) {
            throw new IllegalArgumentException("1,000원 이상 금액을 입력해주세요.");
        }

        // 숫자에 음수가 들어있는 경우 “구입 금액에 음수는 입력할수 없습니다”
        if (money < 0) {
            throw new IllegalArgumentException("구입 금액에 음수는 입력할수 없습니다.");
        }

        // 1,000으로 나누어 떨어지지 않을 경우 “구입 금액은 1,000원 단위입니다.”
        if (money / 1000.0)
    }
}
