package BankAttributes;

import java.util.Random;

/**
 * This class represents the attributes of a stock.
 */
public class Stock {
    private double interestRate;

    public Stock() {
        this.setInterestRate();
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate() {
        Random seed = new Random();

        // Generate a random interest rate within [0, 1)
        double interestRate = seed.nextDouble();

        // If true, interestRate is positive / zero; otherwise it is negative / zero
        interestRate = seed.nextBoolean() ? interestRate : -interestRate;

        this.interestRate = interestRate;
    }
}
