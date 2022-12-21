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
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("List of Statistic is empty");
    }

    @Test
    @Tag("Null")
    @Tag("findStatisticWithMaxLosesAmounts")
    void fail_find_statistic_with_max_loses_amount_list_is_null() {
        Assertions.assertThatThrownBy(
            () -> analyticsService.findStatisticWithMaxLosesAmounts(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Tag("Correct work")
    @Tag("findStatisticWithMinLosesAmounts")
    void find_statistic_with_min_loses_amount() {
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
            analyticsService.findStatisticWithMinLosesAmounts(losesStatistics);


        assertThat(actualStatistic).isEqualTo(minStatistic);
    }

    @Test
    @Tag("Empty")
    @Tag("findStatisticWithMinLosesAmounts")
    void fail_find_statistic_with_min_loses_amount_list_is_empty() {
        List<LosesStatistic> losesStatistics = new ArrayList<>();

        Assertions.assertThatThrownBy(
                () -> analyticsService.findStatisticWithMinLosesAmounts(losesStatistics)
            ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("List of Statistic is empty");
    }

    @Test
    @Tag("Null")
    @Tag("findStatisticWithMinLosesAmounts")
    void fail_find_statistic_with_min_loses_amount_list_is_null() {
        Assertions.assertThatThrownBy(
            () -> analyticsService.findStatisticWithMinLosesAmounts(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Tag("Correct work")
    @Tag("totalCountOfLosesForStatistic")
    void total_count_of_loses_for_statistic() {
        LosesStatistic losesStatistic = new LosesStatistic(
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
        int expectedTotalCount = 91;

        int actualTotalCount = analyticsService.totalCountOfLosesForStatistic(losesStatistic);

        assertThat(actualTotalCount).isEqualTo(expectedTotalCount);
    }

    @Test
    @Tag("Null")
    @Tag("totalCountOfLosesForStatistic")
    void fail_total_count_of_loses_for_statistic_statistic_is_null() {
        Assertions.assertThatThrownBy(
            () -> analyticsService.totalCountOfLosesForStatistic(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Tag("Correct work")
    @Tag("totalCountOfLosesForAllStatistics")
    void get_total_count_of_loses_for_all_statistics() {
        List<LosesStatistic> losesStatistics = List.of(
            new LosesStatistic(
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
            ),
            new LosesStatistic(
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26,
                27,
                28
            )
        );

        int expectedTotalCount = 364;


        int actualTotalCount = analyticsService.totalCountOfLosesForAllStatistics(losesStatistics);


        assertThat(actualTotalCount).isEqualTo(expectedTotalCount);
    }

    @Test
    @Tag("Empty")
    @Tag("totalCountOfLosesForAllStatistics")
    void total_count_of_loses_for_all_statistics_with_empty_list() {
        List<LosesStatistic> losesStatistics = new ArrayList<>();

        int expectedTotalCount = 0;


        int actualTotalCount = analyticsService.totalCountOfLosesForAllStatistics(losesStatistics);


        assertThat(actualTotalCount).isEqualTo(expectedTotalCount);
    }
}
