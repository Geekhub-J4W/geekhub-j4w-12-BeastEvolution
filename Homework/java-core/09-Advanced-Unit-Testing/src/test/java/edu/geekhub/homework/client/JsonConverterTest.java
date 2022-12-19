package edu.geekhub.homework.client;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import edu.geekhub.homework.domain.LosesStatistic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JsonConverterTest {

    JsonConverter jsonConverter;

    @Mock
    LosesStatisticHttpClient losesStatisticHttpClient;

    @BeforeEach
    void setUp() {
        jsonConverter = new JsonConverter();
    }

    @Tag("Correct work")
    @Tag("convertToEntity")
    @Test
    void convert_statistic_to_entity() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{\"tanks\":\"2\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");

        LosesStatistic actualLosesStatistic =
            jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1));

        assertThat(actualLosesStatistic).isEqualTo(
            new LosesStatistic(
                2,
                2,
                1,
                1,
                9,
                5,
                9,
                3,
                5,
                2,
                6,
                3,
                3,
                8
            )
        );
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_object_is_null() {

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(null)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't convert string with null value");
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_object_without_curly_bracket() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("\"tanks\":\"2\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Statistic object must be in square brackets");
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_object_without_key_of_element() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{\"2\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("All items of statistic must have key:value");
    }


    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_object_without_value_of_element() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{\"tanks\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("All items of statistic must have key:value");
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_object_with_incorrect_value_of_element() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{\"tanks\":\"a\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("All statistic items value must be integer");
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_string_without_quotation_marks() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{tanks:\"2\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("All Json string must delimited with double quotation marks");
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_incorrect_key_of_item() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{\"a\":\"2\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input Json element keys don't math statistic items names");
    }

    @Tag("error")
    @Tag("convertToEntity")
    @Test
    void fail_convert_statistic_to_entity_json_incorrect_number_of_items() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("{\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"}");
        String losesStatisticJson = losesStatisticHttpClient.getById(1);

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(
                "Statistics must have "
                + LosesStatistic.numbersOfStatisticItems
                +  " items"
            );
    }

    @Tag("Correct work")
    @Tag("convertToEntities")
    @Test
    void convert_statistics_to_entities() throws Exception {
        List<LosesStatistic> expectedLosesStatistics = List.of(new LosesStatistic(
            5,
            4,
            9,
            5,
            3,
            5,
            3,
            3,
            3,
            6,
            3,
            1,
            1,
            12
        ));

        when(losesStatisticHttpClient.getAll())
            .thenReturn("[{\"tanks\":\"5\",\"armouredFightingVehicles\":\"4\",\"cannons\":\"9\",\"multipleRocketLaunchers\":\"5\",\"antiAirDefenseDevices\":\"3\",\"planes\":\"5\",\"helicopters\":\"3\",\"drones\":\"3\",\"cruiseMissiles\":\"3\",\"shipsOrBoats\":\"6\",\"carsAndTankers\":\"3\",\"specialEquipment\":\"1\",\"personnel\":\"1\",\"id\":\"12\"}]");
        List<LosesStatistic> actualLosesStatistics =
            jsonConverter.convertToEntities(losesStatisticHttpClient.getAll());

        assertThat(actualLosesStatistics)
            .isEqualTo(expectedLosesStatistics);
    }

    @Tag("error")
    @Tag("convertToEntities")
    @Test
    void fail_convert_statistics_to_entities_json_object_is_null() {

        assertThatThrownBy(
            () -> jsonConverter.convertToEntities(null)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't convert string with null value");
    }

    @Tag("error")
    @Tag("convertToEntities")
    @Test
    void fail_convert_statistics_to_entities_json_incorrect_key_of_item() throws Exception {
        when(losesStatisticHttpClient.getAll())
            .thenReturn("{\"tanks\":\"5\",\"armouredFightingVehicles\":\"4\",\"cannons\":\"9\",\"multipleRocketLaunchers\":\"5\",\"antiAirDefenseDevices\":\"3\",\"planes\":\"5\",\"helicopters\":\"3\",\"drones\":\"3\",\"cruiseMissiles\":\"3\",\"shipsOrBoats\":\"6\",\"carsAndTankers\":\"3\",\"specialEquipment\":\"1\",\"personnel\":\"1\",\"id\":\"12\"}]");
        String losesStatisticsJson = losesStatisticHttpClient.getAll();

        assertThatThrownBy(
            () -> jsonConverter.convertToEntities(losesStatisticsJson)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Statistics array must be in square brackets");
    }

    @Tag("Correct work")
    @Tag("convertToJson")
    @Test
    void fail_convert_statistics_to_json_format() throws Exception {
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

        String jsonStatistic = jsonConverter.convertToJson(losesStatistic);

        assertThat(jsonStatistic).
            isEqualTo("{\"tanks\":\"1\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"3\",\"multipleRocketLaunchers\":\"4\",\"antiAirDefenseDevices\":\"5\",\"planes\":\"6\",\"helicopters\":\"7\",\"drones\":\"8\",\"cruiseMissiles\":\"9\",\"shipsOrBoats\":\"10\",\"carsAndTankers\":\"11\",\"specialEquipment\":\"12\",\"personnel\":\"13\",\"id\":\"14\"}");
    }

    @Tag("Null")
    @Tag("convertToJson")
    @Test
    void fail_convert_statistics_to_json_format_object_is_null() throws Exception {
        assertThatThrownBy(
            () -> jsonConverter.convertToJson(null)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Can't convert object with null value");
    }
}
