package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @DisplayName("1등 테스트")
    @Test
    void first_rank() {
        assertThat(Rank.checkRank(6, false)).isEqualTo(Rank.FIRST);
    }

    @DisplayName("2등 테스트")
    @Test
    void second_rank() {
        assertThat(Rank.checkRank(5, true)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("3등 테스트")
    @Test
    void third_rank() {
        assertThat(Rank.checkRank(5, false)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4등 테스트")
    @Test
    void fourth_rank() {
        assertThat(Rank.checkRank(4, false)).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("5등 테스트")
    @Test
    void fifth_rank() {
        assertThat(Rank.checkRank(3, false)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("FAIL 테스트")
    @Test
    void fail_rank() {
        assertThat(Rank.checkRank(2, false)).isEqualTo(Rank.FAIL);
        assertThat(Rank.checkRank(1, false)).isEqualTo(Rank.FAIL);
        assertThat(Rank.checkRank(0, false)).isEqualTo(Rank.FAIL);
    }
}
