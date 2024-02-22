package ngordnet.ngrams;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i++) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    // Own tests
    @Test
    public void testQuotient() {
        TimeSeries a = new TimeSeries();
        a.put(2000, 1.0);
        a.put(2001, 2.0);
        a.put(2002, 3.0);
        a.put(2003, 4.0);

        TimeSeries b = new TimeSeries();
        b.put(2000, 5.0);
        b.put(2001, 2.0);
        b.put(2002, 6.0);
        b.put(2003, 5.0);

        TimeSeries quotient = a.dividedBy(b);

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(2000, 2001, 2002, 2003));

        List<Double> expectedQuotient = new ArrayList<>
                (Arrays.asList(0.2, 1.0, 0.5, 0.8));

        assertThat(quotient.years()).isEqualTo(expectedYears);

        for (int i = 0; i < expectedQuotient.size(); i++) {
            assertThat(quotient.data().get(i)).isWithin(1E-10).of(expectedQuotient.get(i));
        }
    }

    @Test
    public void testPlus() {
        // Try using the merge() method of TreeMap
        TimeSeries a = new TimeSeries();
        a.put(2000, 1.0);
        a.put(2001, 2.0);
        a.put(2002, 3.0);
        a.put(2003, 4.0);

        TimeSeries b = new TimeSeries();
        b.put(2001, 2.0);
        b.put(2002, 3.0);
        b.put(2003, 4.0);

        TimeSeries sum = a.plus(b);

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(2000, 2001, 2002, 2003));

        List<Double> expectedSum = new ArrayList<>
                (Arrays.asList(1.0, 4.0, 6.0, 8.0));

        assertThat(sum.years()).isEqualTo(expectedYears);

        for (int i = 0; i < expectedSum.size(); i++) {
            assertThat(sum.data().get(i)).isWithin(1E-10).of(expectedSum.get(i));
        }
    }

    @Test
    public void testTimeSeriesConstructor() {
        TimeSeries familyBirthdays = new TimeSeries();
        familyBirthdays.put(1997, 2.0);
        familyBirthdays.put(1995, 1.0);
        familyBirthdays.put(2003, 3.0);
        familyBirthdays.put(1987, 0.0);

        TimeSeries familyBirthdays20th = new TimeSeries(familyBirthdays, 1900, 1999);

        List<Integer> years = familyBirthdays20th.years();
        List<Double> data = familyBirthdays20th.data();

        assertThat(familyBirthdays.years().containsAll(years)).isTrue();
        assertThat(familyBirthdays.data().containsAll(data)).isTrue();

        for (int i = 0; i < familyBirthdays20th.size(); i++) {
            Integer year = years.get(i);
            assertThat(year >= 1900 && year <= 1999).isTrue();

            Double datapoint = data.get(i);

            assertThat(familyBirthdays.get(year)).isWithin(1E-10).of(datapoint);
        }
    }

    @Test
    public void testYearsData() {
        TimeSeries familyBirthdays = new TimeSeries();
        familyBirthdays.put(1997, 2.0);
        familyBirthdays.put(1995, 1.0);
        familyBirthdays.put(2003, 3.0);
        familyBirthdays.put(1987, 0.0);

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1987, 1995, 1997, 2003));

        List<Double> expectedData = new ArrayList<>
                (Arrays.asList(0.0, 1.0, 2.0, 3.0));

        assertThat(familyBirthdays.years()).isEqualTo(expectedYears);

        for (int i = 0; i < familyBirthdays.size(); i++) {
            assertThat(familyBirthdays.data().get(i)).isWithin(1E-10).of(expectedData.get(i));
        }
    }

    @Test
    public void testSumAll() {
        TimeSeries ts = new TimeSeries();
        ts.put(1997, 2.0);
        ts.put(1995, 1.0);
        ts.put(2003, 3.0);
        ts.put(1987, 4.0);

        assertThat(ts.sumAll().equals(10.0)).isTrue();
    }
} 