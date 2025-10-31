package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class ResultRecord {
    private static final int INITIAL_COUNT = 0;
    private final Map<Rank, Integer> resultRecord = new EnumMap<>(Rank.class);

    public ResultRecord() {
        for (Rank rank : Rank.values()) {
            resultRecord.put(rank, INITIAL_COUNT);
        }
    }

    public void record(Rank rank) {
        Integer value = resultRecord.get(rank);
        value++;
        resultRecord.put(rank, value);
    }

    public Map<Rank, Integer> getMap(){
        return resultRecord;
    }
}
