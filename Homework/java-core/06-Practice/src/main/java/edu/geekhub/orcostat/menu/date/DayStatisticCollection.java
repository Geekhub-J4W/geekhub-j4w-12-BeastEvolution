package edu.geekhub.orcostat.menu.date;

import edu.geekhub.orcostat.OrcoStatService;
import edu.geekhub.orcostat.exeptions.IllegalOptionException;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class DayStatisticCollection {
    DayStatistic[] daysStatistic;

    public DayStatisticCollection() {
        daysStatistic = new DayStatistic[0];
    }

    public void add(DayStatistic dayStatistic) {
        if (Objects.isNull(dayStatistic)) {
            throw new IllegalArgumentException("MenuNode cannot be null");
        }

        if (this.contains(dayStatistic.getDate())) {
            throw new IllegalArgumentException("Statistics fir that day already in collections");
        }
        daysStatistic = Arrays.copyOf(daysStatistic, daysStatistic.length + 1);
        daysStatistic[daysStatistic.length - 1] = dayStatistic;
    }

    public int length() {
        return daysStatistic.length;
    }

    public DayStatistic get(int index) {
        return daysStatistic[index];
    }

    public DayStatistic getByDate(Date date) {
        for (DayStatistic dayStatistic:
             daysStatistic) {
            if(dayStatistic.getDate().equals(date)) {
                return dayStatistic;
            }
        }

        throw new IllegalOptionException("No DayStatistic with this date");
    }
    public DayStatistic[] getAll() {
        return daysStatistic;
    }

    public boolean contains(Date date) {
        for (DayStatistic dayStatistic:
            daysStatistic) {
            if(dayStatistic.getDate().equals(date)) {
                return true;
            }
        }

        return false;
    }

    public void changeStatistic(Date date, OrcoStatService orcoStatService) {
        for (int i = 0; i < daysStatistic.length; i++) {
            if(daysStatistic[i].getDate().equals(date)) {
                daysStatistic[i] = new DayStatistic(date,orcoStatService);
                return;
            }
        }

        throw new IllegalOptionException("No DayStatistic with this date");
    }
}
