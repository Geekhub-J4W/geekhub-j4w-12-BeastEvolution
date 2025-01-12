package edu.geekhub.homework;

import edu.geekhub.homework.CitiesImp;
import edu.geekhub.homework.CitiesMock;
import edu.geekhub.homework.City;
import edu.geekhub.homework.Exercises;
import edu.geekhub.homework.exceptions.NotFoundException;
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

        NotFoundException thrown = assertThrows(
            NotFoundException.class,
            exercises::mostPopulatedCity);

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

        NotFoundException thrown = assertThrows(
            NotFoundException.class,
            exercises::minPopulatedCity
        );

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

        NotFoundException thrown = assertThrows(
            NotFoundException.class,
            exercises::mostPopulatedCountry
        );

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

        NotFoundException thrown = assertThrows(
            NotFoundException.class,
            exercises::minPopulatedCountry
        );

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

        NotFoundException thrown = assertThrows(
            NotFoundException.class,
            exercises::totalPopulation
        );

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

    @Test
    @Tag("correct work")
    @Tag(("populationOfSpecificCountry"))
    void get_population_of_Nld() {
        String expectedPopulationOfNld = "5180049";
        String country = "NLD";

        String actualPopulationOfNld =
            exercises.populationOfSpecificCountry(country)
                .toString();


        assertEquals(expectedPopulationOfNld, actualPopulationOfNld);
    }

    @Test
    @Tag("correct work")
    @Tag(("populationOfSpecificCountry"))
    void get_population_of_incorrect_country() {
        String expectedPopulationOfCountry = "0";
        String country = "A";

        String actualPopulationOfCountry =
            exercises.populationOfSpecificCountry(country)
                .toString();

        assertEquals(expectedPopulationOfCountry, actualPopulationOfCountry);
    }

    @Test
    @Tag("correct work")
    @Tag(("specificCity"))
    void get_specific_city() {
        String expectedCity = "City [id=12, name=Breda, population=160398, countryCode=NLD]";
        String city = "Breda";

        String actualCity =
            exercises.specificCity(city)
                .toString();

        assertEquals(expectedCity, actualCity);
    }

    @Test
    @Tag("error")
    @Tag(("specificCity"))
    void fail_find_specific_city() {
        exercises = new Exercises(new CitiesMock());
        String city = "Breda";

        NotFoundException thrown =
            assertThrows(
                NotFoundException.class,
                () -> exercises.specificCity(city)
            );

        assertEquals("Not found " + city + " in this structure", thrown.getMessage());
    }

    @Test
    @Tag("correct work")
    @Tag(("getCountryCitiesCount"))
    void get_country_cities_count() {
        String expectedCountryCitiesCount = "{NZL=9, FJI=1, PNG=1, GLP=2, STP=1, WLF=1, MHL=1, CUB=14, SDN=12, GMB=2, MYS=18, MYT=1, TWN=42, POL=44, SUR=1, OMN=5, ARE=5, KEN=8, ARG=57, GNB=1, UZB=17, ARM=3, TGO=1, SEN=9, BTN=1, IRL=2, IRN=67, FLK=1, QAT=1, BDI=1, NLD=28, SVK=3, IRQ=15, SVN=2, GNQ=1, THA=12, ABW=1, ASM=2, SWE=15, ISL=1, MKD=1, BEL=9, LIE=2, KWT=3, ISR=14, BEN=4, DZA=18, RUS=189, ATG=1, SWZ=1, ITA=58, TZA=10, PAK=59, PAN=2, CXR=1, BFA=3, UKR=57, SGP=1, KGZ=2, CHE=5, DJI=1, REU=1, PRI=9, CHL=29, PRK=13, CHN=363, MLI=1, HRV=4, BWA=2, KHM=3, IDN=85, PRT=5, VNM=22, TJK=2, MLT=2, PRY=5, CYM=1, SHN=1, CYP=2, SYC=1, RWA=1, BGD=24, AUS=14, AUT=6, PSE=6, LKA=7, ZWE=6, GAB=1, YUG=8, BGR=10, SYR=11, CZE=10, NOR=5, CIV=5, MMR=16, TKL=1, KIR=2, TKM=4, GRD=1, GRC=8, PCN=1, HTI=4, YEM=6, GRL=1, AFG=4, MNG=1, NPL=5, BHS=1, BHR=1, MNP=1, GBR=81, SJM=1, DMA=1, HUN=9, BIH=3, AGO=5, WSM=1, FRA=40, TMP=1, MOZ=12, NAM=1, PER=22, DNK=5, GTM=4, FRO=1, VAT=1, SLB=1, SLE=1, NRU=2, GUF=1, AIA=2, SLV=7, GUM=2, FSM=2, DOM=6, CMR=7, GUY=1, AZE=4, MAC=1, GEO=5, TON=1, NCL=1, SMR=2, ERI=1, KNA=1, MAR=22, VCT=1, BLR=16, MRT=2, BLZ=2, PHL=136, COD=18, COG=2, ESH=1, PYF=2, URY=1, COK=1, COM=1, COL=38, USA=274, ESP=59, EST=2, BMU=2, MSR=1, ZMB=7, KOR=70, SOM=3, VUT=1, ECU=15, ALB=1, MCO=2, ETH=7, NER=3, LAO=2, VEN=41, GHA=5, CPV=1, MDA=4, MTQ=1, SPM=1, MDG=5, NFK=1, LBN=2, LBR=1, MDV=1, BOL=8, GIB=1, LBY=4, HKG=2, NGA=64, LSO=1, CAF=1, MUS=3, LCA=1, JOR=5, GIN=1, VGB=1, ROM=29, CAN=49, TCA=1, TCD=2, AND=1, CRI=1, IND=341, MEX=173, KAZ=21, SAU=24, ANT=1, JPN=248, LTU=5, TTO=2, PLW=1, MWI=2, NIC=4, FIN=7, CCK=2, TUN=8, UGA=1, LUX=1, TUR=62, BRA=250, BRB=1, TUV=1, DEU=93, LVA=3, EGY=37, JAM=3, NIU=1, VIR=1, ZAF=44, HND=3, BRN=1}";

        String actualCountryCitiesCount =
            exercises.getCountryCitiesCount()
                .toString();

        assertEquals(expectedCountryCitiesCount, actualCountryCitiesCount);
    }
}
