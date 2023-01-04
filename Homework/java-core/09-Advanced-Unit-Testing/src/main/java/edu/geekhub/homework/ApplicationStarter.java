package edu.geekhub.homework;

import edu.geekhub.homework.analytics.AnalyticsService;
import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;
import edu.geekhub.homework.domain.LosesStatisticService;

import java.util.Random;


public class ApplicationStarter {

    public static void main(String[] args) {
        var losesStatisticService = new LosesStatisticService(
            new LosesStatisticHttpClient(),
            new JsonConverter()
        );

        var losesStatistics = losesStatisticService.getAll();
        var randomStatistic = new Random().nextInt(losesStatistics.size());

        var losesStatistic = losesStatisticService.getById(
            losesStatistics
                .get(randomStatistic)
                .id()
        );
        losesStatisticService.create(losesStatistic);
        losesStatisticService.deleteById(losesStatistic.id());

        var analyticsService = new AnalyticsService();

        print(analyticsService.totalCountOfLosesForStatistic(losesStatistic));
        print(analyticsService.totalCountOfLosesForAllStatistics(losesStatisticService.getAll()));
        print(analyticsService.findStatisticWithMinLosesAmounts(losesStatisticService.getAll()));
        print(analyticsService.findStatisticWithMaxLosesAmounts(losesStatisticService.getAll()));

        losesStatisticService.deleteAll();
    }

    static void print(Object objectToPrint) {
        System.out.println(objectToPrint);
    }
}
