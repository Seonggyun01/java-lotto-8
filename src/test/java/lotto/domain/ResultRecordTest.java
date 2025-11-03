package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ResultRecordTest {

    @DisplayName("초기 상태에서는 모든 Rank의 카운트가 0이다.")
    @Test
    void 초기값_확인_테스트() {
        ResultRecord resultRecord = new ResultRecord();
        Map<Rank, Integer> map = resultRecord.getMap();

        for (Rank rank : Rank.values()) {
            assertThat(map.get(rank)).isEqualTo(0);
        }
    }

    @DisplayName("record 호출 시 해당 Rank 카운트가 1 증가한다.")
    @Test
    void record_1번_테스트() {
        ResultRecord resultRecord = new ResultRecord();
        resultRecord.record(Rank.FIRST);

        Map<Rank, Integer> map = resultRecord.getMap();
        assertThat(map.get(Rank.FIRST)).isEqualTo(1);
    }

    @DisplayName("같은 Rank를 여러 번 record하면 정상적으로 누적된다.")
    @Test
    void record_3번_테스트() {
        ResultRecord resultRecord = new ResultRecord();
        resultRecord.record(Rank.SECOND);
        resultRecord.record(Rank.SECOND);
        resultRecord.record(Rank.SECOND);

        Map<Rank, Integer> map = resultRecord.getMap();
        assertThat(map.get(Rank.SECOND)).isEqualTo(3);
    }

    @DisplayName("여러 Rank에 대해 카운트가 각각 정상적으로 증가한다.")
    @Test
    void 여러_Rank_record_테스트() {
        ResultRecord resultRecord = new ResultRecord();
        resultRecord.record(Rank.FIRST);
        resultRecord.record(Rank.FOURTH);
        resultRecord.record(Rank.FOURTH);

        Map<Rank, Integer> map = resultRecord.getMap();

        assertThat(map.get(Rank.FIRST)).isEqualTo(1);
        assertThat(map.get(Rank.FOURTH)).isEqualTo(2);
        assertThat(map.get(Rank.THIRD)).isEqualTo(0);
        assertThat(map.get(Rank.FAIL)).isEqualTo(0);
    }
}
