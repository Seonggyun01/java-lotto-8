package lotto.dto;

import java.util.List;

public record PurchasedDto(
        List<List<Integer>> lottos,
        int count
) {
}
