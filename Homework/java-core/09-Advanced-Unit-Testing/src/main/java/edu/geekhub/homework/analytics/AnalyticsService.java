package edu.geekhub.homework.analytics;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

import edu.geekhub.homework.domain.LosesStatistic;
import edu.geekhub.homework.domain.StatisticItem;
import java.util.Comparator;
import java.util.List;


/**
 * Service shows interesting analytics information
 */
public class AnalyticsService {
    public LosesStatistic findStatisticWithMaxLosesAmounts(List<LosesStatistic> losesStatistics) {
        return losesStatistics.stream()
            .max(new LosesStatisticAmountsComparator())
            .orElseThrow(() -> new IllegalArgumentException(
                "List of Statistic is empty"
            ));
    }

    class LosesStatisticAmountsComparator implements Comparator<LosesStatistic> {
        @Override
        public int compare(LosesStatistic o1, LosesStatistic o2) {
            int o1LosesAmounts = o1.getStatisticItems().stream()
                .mapToInt(StatisticItem::value)
                .sum();
            int o2LosesAmounts = o2.getStatisticItems().stream()
                .mapToInt(StatisticItem::value)
                .sum();

            return o1LosesAmounts - o2LosesAmounts;
        }
    }

    public LosesStatistic findStatisticWithMinLosesAmounts(List<LosesStatistic> losesStatistics) {
        return losesStatistics.stream()
            .min(new LosesStatisticAmountsComparator())
            .orElseThrow(() -> new IllegalArgumentException(
                "List of Statistic is empty"
            ));
    }

    public int totalCountOfLosesForStatistic(LosesStatistic losesStatistic) {
        return losesStatistic
            .getStatisticItems()
            .stream()
            .mapToInt(StatisticItem::value)
            .sum();
    }

    public int totalCountOfLosesForAllStatistics(List<LosesStatistic> losesStatistics) {
        return TODO_TYPE("Implement method");
    }

}
