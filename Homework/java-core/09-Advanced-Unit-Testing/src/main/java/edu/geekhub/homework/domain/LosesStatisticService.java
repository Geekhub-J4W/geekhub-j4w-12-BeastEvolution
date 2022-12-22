package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;
import edu.geekhub.homework.domain.exceptions.ServerRequestException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
/**
 * Service should fetch loses statistic data as a {@link String} object, then convert it into a
 * {@link LosesStatistic} by using {@link } class and return a result if possible.
 * <br/>
 * <br/>
 * IMPORTANT: DO NOT CHANGE METHODS SIGNATURE
 */
public class LosesStatisticService {

    public static final String SERVER_RESPONSE_IF_ID_ILLEGAL = "Something went wrong while parsing response JSON";
    private final LosesStatisticHttpClient losesStatisticHttpClient;
    private final JsonConverter jsonConverter;

    public LosesStatisticService(LosesStatisticHttpClient losesStatisticHttpClient, JsonConverter jsonConverter) {
        if(Objects.isNull(losesStatisticHttpClient)) {
            throw new IllegalArgumentException("LosesStatisticHttpClient is null");
        }

        if(Objects.isNull(jsonConverter)) {
            throw new IllegalArgumentException("JsonConverter is null");
        }
        this.losesStatisticHttpClient = losesStatisticHttpClient;
        this.jsonConverter = jsonConverter;
    }

    public List<LosesStatistic> getAll() {
        try {
            return jsonConverter.convertToEntities(losesStatisticHttpClient.getAll());
        } catch (IOException | InterruptedException e) {
            throw new ServerRequestException(
                "Can't get all statistics. Failed to send request to server"
            );
        }
    }

    public LosesStatistic getById(Integer id) {
        try {
            String jsonLosesStatistic = losesStatisticHttpClient.getById(id);

            if(jsonLosesStatistic.equals(SERVER_RESPONSE_IF_ID_ILLEGAL)) {
                throw new IllegalArgumentException("No loses statistic with this ID");
            }

            return jsonConverter
                .convertToEntity(jsonLosesStatistic);
        } catch (IOException | InterruptedException e) {
            throw new ServerRequestException(
                "Can't get statistic. Failed to send request to server"
            );
        }
    }

    public void deleteAll() {
        try {
            losesStatisticHttpClient.deleteAll();
        } catch (IOException | InterruptedException e) {
            throw new ServerRequestException("Can't delete all statistics. Failed to send request to server");
        }
    }

    public void deleteById(int id) {
        try {
            losesStatisticHttpClient.deleteById(id);
        } catch (IOException | InterruptedException e) {
            throw new ServerRequestException(
                "Can't delete statistic. Failed to send request to server"
            );
        }
    }

    public void create(LosesStatistic losesStatistic) {
        try {
            losesStatisticHttpClient
                .create(jsonConverter.convertToJson(losesStatistic));
        } catch (IOException | InterruptedException e) {
            throw new ServerRequestException(
                "Can't create statistic. Failed to send request to server"
            );
        }
    }
}
