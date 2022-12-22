package edu.geekhub.homework.domain;

import edu.geekhub.homework.client.JsonConverter;
import edu.geekhub.homework.client.LosesStatisticHttpClient;
import edu.geekhub.homework.domain.exceptions.ServerRequestException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static edu.geekhub.homework.util.NotImplementedException.TODO;
import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;

/**
 * Service should fetch loses statistic data as a {@link String} object, then convert it into a
 * {@link LosesStatistic} by using {@link } class and return a result if possible.
 * <br/>
 * <br/>
 * IMPORTANT: DO NOT CHANGE METHODS SIGNATURE
 */
public class LosesStatisticService {

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
            throw new ServerRequestException("Can't get data form server");
        }
    }

    public LosesStatistic getById(Integer id) {
        return TODO_TYPE("Implement method");
    }

    public void deleteAll() {
        TODO("Implement method");
    }

    public void deleteById(int id) {
        TODO("Implement method");
    }

    public void create(LosesStatistic losesStatistic) {
        TODO("Implement method");
    }
}
