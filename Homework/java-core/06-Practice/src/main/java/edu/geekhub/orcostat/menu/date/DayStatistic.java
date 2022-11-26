package edu.geekhub.orcostat.menu.date;

import edu.geekhub.orcostat.OrcoStatService;

import java.util.Date;

public class DayStatistic {
    Date date;
    OrcoStatService orcoStatService;

    public DayStatistic(Date date, OrcoStatService orcoStatService) {
        this.date = date;
        this.orcoStatService = orcoStatService;
    }

    public Date getDate() {
        return date;
    }

    public OrcoStatService getOrcoStatService() {
        return orcoStatService;
    }
}
