package ngordnet.ngrams;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    // TODO: Add any necessary static/instance variables.

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();

        // I think no need to check if it goes beyond bounds, trivially empty if invalid
        // if (startYear < MIN_YEAR || startYear > MAX_YEAR || endYear < MIN_YEAR || endYear > MAX_YEAR) { return; }

        if (startYear > endYear) { return; }

        List<Integer> years = ts.years();
        List<Double> data = ts.data();

        for (int i = 0, size = ts.size(); i < size; i++) {
            Integer year = years.get(i);
            Double datapoint = data.get(i);

            if (year >= startYear && year <= endYear) {
                put(year, datapoint);
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<Integer>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        return new ArrayList<Double>(values());
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries resultSum = new TimeSeries();

        // Merge adds the given key-value mapping, if mapping exists then plus the value
        for (Map.Entry<Integer, Double> entry : entrySet()) {
            Integer year = entry.getKey();
            Double value = entry.getValue();

            resultSum.merge(year, value, (v1, v2) -> value + resultSum.get(year));
        }

        for (Map.Entry<Integer, Double> entry : ts.entrySet()) {
            Integer year = entry.getKey();
            Double value = entry.getValue();

            resultSum.merge(year, value, (v1, v2) -> value + resultSum.get(year));
        }
        return resultSum;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        return null;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
