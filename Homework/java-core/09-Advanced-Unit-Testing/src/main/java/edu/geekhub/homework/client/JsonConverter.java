package edu.geekhub.homework.client;

import edu.geekhub.homework.domain.LosesStatistic;
import edu.geekhub.homework.domain.StatisticItem;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class is responsible for conversion of {@link String} objects into a
 * {@link LosesStatistic} objects and otherwise
 */
public class JsonConverter {

    private static final int BEGINING_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int KEY_INDEX = 0;

    public List<LosesStatistic> convertToEntities(String losesStatisticsJson) {
        if (Objects.isNull(losesStatisticsJson)) {
            throw new IllegalArgumentException("Can't convert string with null value");
        }

        losesStatisticsJson = deleteSquareBracket(losesStatisticsJson);
        List<String> statistics = divideArrayElements(losesStatisticsJson);

        return statistics
            .stream()
            .map(this::convertToEntity)
            .toList();
    }

    public LosesStatistic convertToEntity(String losesStatisticJson) {
        if (Objects.isNull(losesStatisticJson)) {
            throw new IllegalArgumentException("Can't convert string with null value");
        }

        losesStatisticJson = deleteCurlyBracket(losesStatisticJson);

        List<String> statisticItems = divideObjectElements(losesStatisticJson);
        Map<String, Integer> itemsMap = elementsToMap(statisticItems);

        checkStatisticItemsKeys(itemsMap.keySet());

        return new LosesStatistic(
            itemsMap.get("tanks"),
            itemsMap.get("armouredFightingVehicles"),
            itemsMap.get("cannons"),
            itemsMap.get("multipleRocketLaunchers"),
            itemsMap.get("antiAirDefenseDevices"),
            itemsMap.get("planes"),
            itemsMap.get("helicopters"),
            itemsMap.get("drones"),
            itemsMap.get("cruiseMissiles"),
            itemsMap.get("shipsOrBoats"),
            itemsMap.get("carsAndTankers"),
            itemsMap.get("specialEquipment"),
            itemsMap.get("personnel"),
            itemsMap.get("id")
        );
    }

    public String convertToJson(LosesStatistic losesStatistic) {
        if (Objects.isNull(losesStatistic)) {
            throw new IllegalArgumentException("Can't convert object with null value");
        }

        List<StatisticItem> items = losesStatistic.getStatisticItems();

        String jsonStatistic = items.stream()
            .map(item -> addKey(item.key(), item.value().toString()))
            .reduce(this::separateWithCommas)
            .orElseThrow(
                () -> new IllegalArgumentException("Statistic must contain items")
            );

        jsonStatistic = addCurlyBracket(jsonStatistic);

        return jsonStatistic;
    }

    private String deleteSquareBracket(String input) {
        if (input.startsWith("[") && input.endsWith("]")) {
            return input.substring(BEGINING_INDEX + 1, input.length() - 1);
        }

        throw new IllegalArgumentException("Statistics array must be in square brackets");
    }

    private List<String> divideArrayElements(String input) {
        List<String> result = new ArrayList<>(LosesStatistic.numbersOfStatisticItems);

        Matcher matcher = Pattern.compile("\\{.+?\\}").matcher(input);

        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    private String deleteCurlyBracket(String input) {
        if (input.startsWith("{") && input.endsWith("}")) {
            return input.substring(BEGINING_INDEX + 1, input.length() - 1);
        }

        throw new IllegalArgumentException("Statistic object must be in square brackets");
    }

    private List<String> divideObjectElements(String input) {
        List<String> result = Arrays.asList(input.split(","));

        if (result.size() != LosesStatistic.numbersOfStatisticItems) {
            throw new IllegalArgumentException(
                "Statistics must have "
                + LosesStatistic.numbersOfStatisticItems
                +  " items"
            );
        }

        return result;
    }

    private Map<String, Integer> elementsToMap(List<String> elements) {
        return elements
            .stream()
            .collect(Collectors.toConcurrentMap(this::getElementKey, this::getElementValue));
    }

    private String getElementKey(String element) {
        String elementKey = getElementContent(element)[KEY_INDEX];
        elementKey = deleteQuotationMarks(elementKey);

        return elementKey;
    }

    private String[] getElementContent(String element) {
        String[] elementContent = element.split(":");

        boolean isNotHaveKeyAndValue = elementContent.length != 2;
        if (isNotHaveKeyAndValue) {
            throw new IllegalArgumentException("All items of statistic must have key:value");
        }

        return elementContent;
    }

    private Integer getElementValue(String element) {
        try {
            String elementValue = getElementContent(element)[VALUE_INDEX];
            elementValue = deleteQuotationMarks(elementValue);

            return Integer.parseInt(elementValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("All statistic items value must be integer");
        }
    }

    private String deleteQuotationMarks(String jsonString) {
        if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
            return jsonString.substring(BEGINING_INDEX + 1, jsonString.length() - 1);
        }

        throw new IllegalArgumentException(
            "All Json string must delimited with double quotation marks"
        );
    }

    private void checkStatisticItemsKeys(Set<String> keys) {
        boolean inputKeysIsNotEqualsToStatisticItemsNames =
            !keys.equals(LosesStatistic.statisticItemsNames);

        if (inputKeysIsNotEqualsToStatisticItemsNames) {
            throw new IllegalArgumentException(
                "Input Json element keys don't math statistic items names"
            );
        }
    }

    private String addKey(String key, String value) {
        key = addQuotationMarks(key);
        value = addQuotationMarks(value);

        return new StringBuilder(key)
            .append(":")
            .append(value)
            .toString();
    }

    private String addQuotationMarks(String string) {
        return new StringBuilder(string)
            .insert(BEGINING_INDEX, "\"")
            .append("\"")
            .toString();
    }

    private String separateWithCommas(String firstItem, String secondItem) {
        return new StringBuilder(firstItem)
            .append(",")
            .append(secondItem)
            .toString();
    }

    private String addCurlyBracket(String jsonStatistic) {
        return new StringBuilder(jsonStatistic)
            .insert(BEGINING_INDEX, "{")
            .append("}")
            .toString();
    }
}
