package org.example.hw;

import org.example.hw.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExercisesTest {

    private Exercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new Exercises(new CitiesImp());
    }

    @Test
    @Tag("correct work")
    @Tag(("mostPopulatedCity"))
    void get_most_populated_city() {
        City expectedCity = new City(1024, "Mumbai (Bombay)", "IND", 10500000);

        City actualCity = exercises.mostPopulatedCity();

        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Tag("error")
    @Tag(("mostPopulatedCity"))
    void fail_find_most_populated_city() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::mostPopulatedCity);

        assertEquals("No cities found", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("minPopulatedCity"))
    void get_min_populated_city() {
        City expectedCity = new City(2912, "Adamstown", "PCN", 42);

        City actualCity = exercises.minPopulatedCity();

        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Tag("error")
    @Tag(("minPopulatedCity"))
    void fail_find_min_populated_city() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::minPopulatedCity);

        assertEquals("No cities found", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("mostPopulatedCountry"))
    void get_most_populated_country() {
        String expectedCountry = "ZWE";

        String actualCountry = exercises.mostPopulatedCountry();

        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    @Tag("error")
    @Tag(("mostPopulatedCountry"))
    void fail_find_most_populated_country() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::mostPopulatedCountry);

        assertEquals("No cities found", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("minPopulatedCountry"))
    void get_min_populated_country() {
        String expectedCountry = "ABW";

        String actualCountry = exercises.minPopulatedCountry();

        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    @Tag("error")
    @Tag(("minPopulatedCountry"))
    void fail_find_min_populated_country() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::minPopulatedCountry);

        assertEquals("No cities found", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("totalPopulation"))
    void get_total_population() {
        Long expectedTotalPopulation = 1429559884L;

        Long actualTotalPopulation = exercises.totalPopulation();


        assertEquals(expectedTotalPopulation, actualTotalPopulation);
    }

    @Test
    @Tag("error")
    @Tag(("totalPopulation"))
    void fail_find_total_population() {
        exercises = new Exercises(new CitiesMock());

        NotFoundException thrown = assertThrows(NotFoundException.class, exercises::totalPopulation);

        assertEquals("No cities found", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("populationOfEachCountry"))
    void get_population_of_each_country() {
        String expectedPopulationOfEachCountry = "{NZL=1847600, FJI=77366, PNG=247000, GLP=75380, STP=49541, WLF=1137, MHL=28000, CUB=4629925, SDN=4752187, GMB=144926, MYS=4605141, MYT=12000, TWN=13569336, POL=11687431, SUR=112000, OMN=537096, ARE=1728336, KEN=3522793, ARG=19996563, GNB=241000, UZB=5183900, ARM=1633100, TGO=375000, SEN=2770458, BTN=22000, IRL=609041, IRN=26032990, FLK=1636, QAT=355000, BDI=300000, NLD=5180049, SVK=784143, IRQ=8926041, SVN=386518, GNQ=40000, THA=7953161, ABW=29034, ASM=7523, SWE=2891431, ISL=109184, MKD=444299, BEL=1609322, LIE=10389, KWT=261252, ISR=2813000, BEN=968503, DZA=5192179, RUS=69150700, ATG=24000, SWZ=61000, ITA=15087019, TZA=2944034, PAK=31546745, PAN=786755, CXR=700, BFA=1229000, UKR=20074000, SGP=4017733, KGZ=812100, CHE=914200, DJI=383000, REU=131480, PRI=1564174, CHL=9717970, PRK=6476751, CHN=175953614, MLI=809552, HRV=1168883, BWA=314822, KHM=805055, IDN=37485695, PRT=1145011, VNM=9364813, TJK=685500, MLT=28518, PRY=1020020, CYM=19600, SHN=1500, CYP=349400, SYC=41000, RWA=286000, BGD=8569906, AUS=11313666, AUT=2384273, PSE=902360, LKA=1545000, ZWE=2730420, GAB=419000, YUG=2189507, BGR=2696915, SYR=4477784, CZE=2634711, NOR=1100028, CIV=3191137, MMR=6203000, TKL=300, KIR=7281, TKM=972600, GRD=4621, GRC=1972843, PCN=42, HTI=1517338, YEM=1743700, GRL=13445, AFG=2332100, MNG=773700, NPL=1132403, BHS=172000, BHR=148000, MNP=9200, GBR=22436673, SJM=1438, DMA=16243, HUN=2953310, BIH=599106, AGO=2561600, WSM=35900, FRA=9244494, TMP=47900, MOZ=3143145, NAM=169000, PER=12147242, DNK=1215945, GTM=1225188, FRO=14542, VAT=455, SLB=50100, SLE=850000, NRU=4609, GUF=50699, AIA=1556, SLV=1138231, GUM=10639, FSM=30600, DOM=2438276, CMR=3522554, GUY=254000, AZE=2464000, MAC=437500, GEO=1880900, TON=22400, NCL=76293, SMR=7096, ERI=431000, KNA=11600, MAR=8757562, VCT=17100, BLR=4741000, MRT=764900, BLZ=62915, PHL=30934791, COD=9864615, COG=1450000, ESH=169000, PYF=51441, URY=1236000, COK=11900, COM=36000, COL=20250990, USA=78625774, ESP=16669189, EST=505227, BMU=3000, MSR=2000, ZMB=2473500, KOR=38999893, SOM=1177000, VUT=33700, ECU=5744142, ALB=270000, MCO=14388, ETH=3190334, NER=653857, LAO=628452, VEN=12251091, GHA=1819889, CPV=94800, MDA=1193300, MTQ=94050, SPM=5808, MDG=1123161, NFK=800, LBN=1340000, LBR=850000, MDV=71000, BOL=3378644, GIB=27025, LBY=2697007, HKG=3300633, NGA=17366900, LSO=297000, CAF=524000, MUS=337280, LCA=2301, JOR=1847677, GIN=1090610, VGB=8000, ROM=7469006, CAN=12673840, TCA=4800, TCD=630465, AND=21189, CRI=339131, IND=123298526, MEX=59752521, KAZ=5484200, SAU=10636700, ANT=2345, JPN=77965107, LTU=1473317, TTO=99997, PLW=12000, MWI=914119, NIC=1269223, FIN=1532919, CCK=670, TUN=1798500, UGA=890800, LUX=80700, TUR=28327028, BRA=85876862, BRB=6070, TUV=4600, DEU=26245483, LVA=968596, EGY=20083079, JAM=314140, NIU=682, VIR=13000, ZAF=15196370, HND=1287000, BRN=21484}";

        String actualPopulationOfEachCountry =
            exercises.populationOfEachCountry()
                .toString();

        assertEquals(expectedPopulationOfEachCountry, actualPopulationOfEachCountry);
    }
}
