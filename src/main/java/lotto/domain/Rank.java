package lotto.domain;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    FAIL(0, 0L);

    private int matchCount;
    private Long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    /**
     * 맞은 숫자 개수와 보너스 번호 유무에 따라서 Rank 반환
     *
     * @param matchCount
     * @param matchBonusNum
     * @return
     */
    public static Rank checkRank(int matchCount, boolean matchBonusNum) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && matchBonusNum) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return FAIL;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Long getPrize() {
        return prize;
    }

}
