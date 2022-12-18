package edu.geekhub.homework.client;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import edu.geekhub.homework.domain.LosesStatistic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        Assertions.assertThat(actualLosesStatistic).isEqualTo(
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
    void fail_convert_statistic_to_entity_json_object_without_curly_bracket() throws Exception {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenReturn("\"tanks\":\"2\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"1\",\"multipleRocketLaunchers\":\"1\",\"antiAirDefenseDevices\":\"9\",\"planes\":\"5\",\"helicopters\":\"9\",\"drones\":\"3\",\"cruiseMissiles\":\"5\",\"shipsOrBoats\":\"2\",\"carsAndTankers\":\"6\",\"specialEquipment\":\"3\",\"personnel\":\"3\",\"id\":\"8\"");

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1))
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

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1))
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

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1))
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

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1))
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

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1))
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

        assertThatThrownBy(
            () -> jsonConverter.convertToEntity(losesStatisticHttpClient.getById(1))
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input Json element keys don't math statistic items names");
    }
}
