package BankAttributes;

/**
 * This interface contains the interest rate of all types of transactions.
 */
public interface Interest {
    double payInterestRate = 0.05; // When a single deposit >= 1000$, pay 5% interest
    double chargeInterestRate = 0.2; // Charge 20% interest on every type of transaction or stock selling
}
