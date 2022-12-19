package edu.geekhub.homework.analytics;

import static org.assertj.core.api.Assertions.assertThat;

import edu.geekhub.homework.domain.LosesStatistic;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AnalyticsServiceTest {
    AnalyticsService analyticsService;

    @BeforeEach
    void setUp() {
        analyticsService = new AnalyticsService();
    }

    @Test
    @Tag("Correct work")
    @Tag("findStatisticWithMaxLosesAmounts")
    void find_statistic_with_max_loses_amount() {
        LosesStatistic minStatistic = new LosesStatistic(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14
        );

        LosesStatistic maxStatistic = new LosesStatistic(
            100,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14
        );

        List<LosesStatistic> losesStatistics = List.of(
            minStatistic,
            maxStatistic
        );


        LosesStatistic actualStatistic =
            analyticsService.findStatisticWithMaxLosesAmounts(losesStatistics);


        assertThat(actualStatistic).isEqualTo(maxStatistic);
    }

    @Test
    @Tag("Empty")
    @Tag("findStatisticWithMaxLosesAmounts")
    void fail_find_statistic_with_max_loses_amount_list_is_empty() {
        List<LosesStatistic> losesStatistics = new ArrayList<>();

        Assertions.assertThatThrownBy(
            () -> analyticsService.findStatisticWithMaxLosesAmounts(losesStatistics)
        ).hasMessage("List of Statistic is empty");
    }


}
