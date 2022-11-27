package edu.geekhub.orcostat;

public class OrcoStatPrinter {

    private final OrcoStatService service;

    public OrcoStatPrinter(OrcoStatService service) {
        this.service = service;
    }

    public void printStatistic() {
        printTroopsStatistic();
        printTanksStatistic();
    }

    public void printMoneyDamage() {
        System.out.println("money");
        System.out.println(service.getLosesInDollars());
    }

    private void printTanksStatistic() {
        System.out.println("tanks count");
        System.out.println(service.getDestroyedTanksCount());
    }

    private void printTroopsStatistic() {
        System.out.println("troops count");
        System.out.println(service.getNegativelyAliveOrcCount());
    }
}
