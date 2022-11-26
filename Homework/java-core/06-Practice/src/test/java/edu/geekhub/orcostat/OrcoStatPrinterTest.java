package edu.geekhub.orcostat;

import edu.geekhub.orcostat.model.Orc;
import edu.geekhub.orcostat.model.Tank;
import org.junit.jupiter.api.Test;

class OrcoStatPrinterTest {

    @Test
    void can_print_statistic() {
        OrcoStatService orcoStatService = new OrcoStatService();
        orcoStatService.addDestroyedTank(new Tank());
        orcoStatService.addNegativelyAliveOrc(new Orc());

        OrcoStatPrinter orcoStatPrinter = new OrcoStatPrinter(orcoStatService);

        orcoStatPrinter.printStatistic();
    }

    @Test
    void can_print_money_damage() {
        OrcoStatService orcoStatService = new OrcoStatService();
        orcoStatService.addDestroyedTank(new Tank());
        orcoStatService.addNegativelyAliveOrc(new Orc());

        OrcoStatPrinter orcoStatPrinter = new OrcoStatPrinter(orcoStatService);

        orcoStatPrinter.printMoneyDamage();
    }

}
