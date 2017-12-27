/**
 * Fraction.java - Class for Fraction objects
 * Begun 11/21/17
 * @author Andrew Eissen
 */

package binarysearchtreeproject3;

/**
 * Fraction class
 * @see java.lang.Comparable
 */
final class Fraction implements Comparable<Fraction> {

    private String value;
    private int numerator;
    private int denominator;

    /**
     * Parameterized constructor
     * @param fractionString
     */
    public Fraction(String fractionString) {
        String[] operandsArray = fractionString.split("/");

        this.setNumerator(Integer.parseInt(operandsArray[0]));
        this.setDenominator(Integer.parseInt(operandsArray[1]));
        this.setValue(fractionString);
    }

    /**
     * Setter for <code>numerator</code>
     * @param numerator
     * @return void
     */
    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Setter for <code>denominator</code>
     * @param denominator
     * @return void
     */
    private void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * Setter for <code>value</code>
     * @param value
     * @return void
     */
    private void setValue(String value) {
        this.value = value;
    }

    /**
     * Per rubric, class has an overridden <code>toString()</code> method
     * @return String - this.value
     */
    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Overridden <code>Comparable</code> method <code>compareTo</code>
     * @param otherFraction
     * @return int
     */
    @Override
    public int compareTo(Fraction otherFraction) {
        int valueOne = this.numerator * otherFraction.denominator;
        int valueTwo = otherFraction.numerator * this.denominator;
        return valueOne - valueTwo;
    }
}