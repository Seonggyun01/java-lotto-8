package lotto.dto;

import java.util.List;

public record PurchasedLottosDto(
        List<List<Integer>> lottos
) {
}
