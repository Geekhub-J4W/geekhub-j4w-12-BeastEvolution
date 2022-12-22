package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;
import edu.geekhub.homework.domain.exceptions.ServerRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LosesStatisticServiceTest {
    @Mock
    LosesStatisticHttpClient losesStatisticHttpClient;
    JsonConverter jsonConverter;
    LosesStatisticService losesStatisticService;

    @BeforeEach
    void setUp() {
        jsonConverter = new JsonConverter();

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


}
