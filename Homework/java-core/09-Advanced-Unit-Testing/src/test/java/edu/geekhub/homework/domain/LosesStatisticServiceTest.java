package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;
import edu.geekhub.homework.domain.exceptions.ServerRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LosesStatisticServiceTest {
    @Mock
    LosesStatisticHttpClient losesStatisticHttpClient;

    @Spy
    JsonConverter jsonConverter;
    LosesStatisticService losesStatisticService;

    @BeforeEach
    void setUp() {
        losesStatisticService = new LosesStatisticService(
            losesStatisticHttpClient,
            jsonConverter);
    }

    @Test
    @Tag("Null")
    @Tag("new")
    void fail_create_loses_statistic_service_http_client_is_null() {
        assertThatThrownBy(() -> new LosesStatisticService(null, jsonConverter))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("LosesStatisticHttpClient is null");
    }

    @Test
    @Tag("Null")
    @Tag("new")
    void fail_create_loses_statistic_service_json_convertor_is_null() {

        assertThatThrownBy(() -> new LosesStatisticService(losesStatisticHttpClient, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("JsonConverter is null");
    }

    @Test
    @Tag("Correct work")
    @Tag("getAll")
    void get_all_statistics() throws IOException, InterruptedException {
        when(losesStatisticHttpClient.getAll())
            .thenReturn("[{\"tanks\":\"1\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"3\",\"multipleRocketLaunchers\":\"4\",\"antiAirDefenseDevices\":\"5\",\"planes\":\"6\",\"helicopters\":\"7\",\"drones\":\"8\",\"cruiseMissiles\":\"9\",\"shipsOrBoats\":\"10\",\"carsAndTankers\":\"11\",\"specialEquipment\":\"12\",\"personnel\":\"13\",\"id\":\"14\"}]");

        List<LosesStatistic> expectedStatistics = List.of(
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
                14)
        );


        List<LosesStatistic> actualStatistics =  losesStatisticService.getAll();

        assertThat(actualStatistics).isEqualTo(expectedStatistics);
    }

    @Test
    @Tag("Error")
    @Tag("getAll")
    void fail_get_all_statistics_server_error() throws IOException, InterruptedException {
        when(losesStatisticHttpClient.getAll())
            .thenThrow(IOException.class);

        assertThatThrownBy(() -> losesStatisticService.getAll())
            .isInstanceOf(ServerRequestException.class)
            .hasMessage("Can't get all statistics. Failed to send request to server");
    }

    @Test
    @Tag("Correct work")
    @Tag("getById")
    void get_statistics_by_id() throws IOException, InterruptedException {
        when(losesStatisticHttpClient.getById(14))
            .thenReturn("{\"tanks\":\"1\",\"armouredFightingVehicles\":\"2\",\"cannons\":\"3\",\"multipleRocketLaunchers\":\"4\",\"antiAirDefenseDevices\":\"5\",\"planes\":\"6\",\"helicopters\":\"7\",\"drones\":\"8\",\"cruiseMissiles\":\"9\",\"shipsOrBoats\":\"10\",\"carsAndTankers\":\"11\",\"specialEquipment\":\"12\",\"personnel\":\"13\",\"id\":\"14\"}");

        LosesStatistic expectedStatistic = new LosesStatistic(
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


        LosesStatistic actualStatistic =  losesStatisticService.getById(14);


        assertThat(actualStatistic).isEqualTo(expectedStatistic);
    }
    @Test
    @Tag("Error")
    @Tag("getById")
    void fail_get_statistics_by_id_incorrect_id() throws IOException, InterruptedException {
        when(losesStatisticHttpClient.getById(1))
            .thenReturn("Something went wrong while parsing response JSON");

        assertThatThrownBy(() -> losesStatisticService.getById(1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("No loses statistic with this ID");
    }

    @Test
    @Tag("Error")
    @Tag("getById")
    void fail_get_statistics_by_id_server_error() throws IOException, InterruptedException {
        when(losesStatisticHttpClient.getById(anyInt()))
            .thenThrow(IOException.class);

        assertThatThrownBy(() -> losesStatisticService.getById(1))
            .isInstanceOf(ServerRequestException.class)
            .hasMessage("Can't get statistic. Failed to send request to server");
    }

    @Test
    @Tag("Correct work")
    @Tag("deleteAll")
    void delete_all_statistics() throws IOException, InterruptedException {
        losesStatisticService.deleteAll();
        verify(losesStatisticHttpClient).deleteAll();
    }

    @Test
    @Tag("Error")
    @Tag("deleteAll")
    void fail_delete_all_statistics_server_error() throws IOException, InterruptedException {
        doThrow(IOException.class)
            .when(losesStatisticHttpClient)
            .deleteAll();

        assertThatThrownBy(() ->losesStatisticService.deleteAll())
            .isInstanceOf(ServerRequestException.class)
            .hasMessage("Can't delete all statistics. Failed to send request to server");
    }

    @Test
    @Tag("Correct work")
    @Tag("deleteById")
    void delete_statistic_by_id() throws IOException, InterruptedException {
        losesStatisticService.deleteById(1);

        verify(losesStatisticHttpClient)
            .deleteById(1);
    }

    @Test
    @Tag("Error")
    @Tag("deleteById")
    void fail_delete_statistic_by_id_server_error() throws IOException, InterruptedException {
        doThrow(IOException.class)
            .when(losesStatisticHttpClient)
            .deleteById(anyInt());

        assertThatThrownBy(() ->losesStatisticService.deleteById(1))
            .isInstanceOf(ServerRequestException.class)
            .hasMessage("Can't delete statistic. Failed to send request to server");
    }

    @Test
    @Tag("Correct work")
    @Tag("create")
    void create_json_statistic() throws IOException, InterruptedException {
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

        losesStatisticService.create(losesStatistic);

        verify(losesStatisticHttpClient)
            .create(anyString());
        verify(jsonConverter).convertToJson(losesStatistic);
    }

    @Test
    @Tag("Error")
    @Tag("deleteById")
    void fail_create_json_statistic_server_error() throws IOException, InterruptedException {
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

        doThrow(IOException.class)
            .when(losesStatisticHttpClient)
            .create(anyString());

        assertThatThrownBy(() ->losesStatisticService.create(losesStatistic))
            .isInstanceOf(ServerRequestException.class)
            .hasMessage("Can't create statistic. Failed to send request to server");
    }
}
