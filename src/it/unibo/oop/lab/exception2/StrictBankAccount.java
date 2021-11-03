package it.unibo.oop.lab.exception2;

/**
 * Class modeling a BankAccount with strict policies: getting money is allowed
 * only with enough founds, and there are also a limited number of free ATM
 * transaction (this number is provided as a input in the constructor).
 * 
 */
public class StrictBankAccount implements BankAccount {

    private final int usrID;
    private double balance;
    private int totalTransactionCount;
    private final int maximumAllowedATMTransactions;
    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;

    /**
     * 
     * @param usrID                         user id
     * @param balance                       initial balance
     * @param maximumAllowedAtmTransactions max no of ATM transactions allowed
     */
    public StrictBankAccount(final int usrID, final double balance, final int maximumAllowedAtmTransactions) {
	this.usrID = usrID;
	this.balance = balance;
	this.maximumAllowedATMTransactions = maximumAllowedAtmTransactions;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @throws WrongAccountHolderException
     */
    public void deposit(final int usrID, final double amount) throws WrongAccountHolderException {
	checkUser(usrID);
	this.balance += amount;
	increaseTransactionsCount();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @throws WrongAccountHolderException
     * @throws NotEnoughFoundsException
     */
    public void withdraw(final int usrID, final double amount)
	    throws WrongAccountHolderException, NotEnoughFoundsException {
	checkUser(usrID);
	isWithdrawAllowed(amount);
	this.balance -= amount;
	increaseTransactionsCount();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @throws TransactionsOverQuotaException
     * @throws WrongAccountHolderException
     */
    public void depositFromATM(final int usrID, final double amount)
	    throws TransactionsOverQuotaException, WrongAccountHolderException {
	if (totalTransactionCount < maximumAllowedATMTransactions) {
	    this.deposit(usrID, amount - StrictBankAccount.ATM_TRANSACTION_FEE);
	    increaseTransactionsCount();
	} else {
	    throw new TransactionsOverQuotaException("Reached maximum allowed transactions");
	}
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @throws TransactionsOverQuotaException
     * @throws NotEnoughFoundsException
     * @throws WrongAccountHolderException
     */
    public void withdrawFromATM(final int usrID, final double amount)
	    throws TransactionsOverQuotaException, WrongAccountHolderException, NotEnoughFoundsException {
	if (totalTransactionCount < maximumAllowedATMTransactions) {
	    this.withdraw(usrID, amount + StrictBankAccount.ATM_TRANSACTION_FEE);
	} else {
	    throw new TransactionsOverQuotaException("Reached maximum allowed transactions");
	}
    }

    /**
     * 
     * {@inheritDoc}
     */
    public double getBalance() {
	return this.balance;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getTransactionCount() {
	return totalTransactionCount;
    }

    /**
     * 
     * @param usrID id of the user related to these fees
     * @throws WrongAccountHolderException
     * @throws NotEnoughFoundsException
     */
    public void computeManagementFees(final int usrID) throws WrongAccountHolderException, NotEnoughFoundsException {
	final double feeAmount = MANAGEMENT_FEE + (totalTransactionCount * StrictBankAccount.TRANSACTION_FEE);
	checkUser(usrID);
	isWithdrawAllowed(feeAmount);
	balance -= MANAGEMENT_FEE + totalTransactionCount * StrictBankAccount.TRANSACTION_FEE;
	totalTransactionCount = 0;
    }

    private void checkUser(final int id) throws WrongAccountHolderException {
	if (this.usrID != id) {
	    throw new WrongAccountHolderException("ID mismatch");
	}
    }

    private void isWithdrawAllowed(final double amount) throws NotEnoughFoundsException {
	if (balance <= amount) {
	    throw new NotEnoughFoundsException(amount + " requested out of " + balance + "available");
	}
    }

    private void increaseTransactionsCount() {
	totalTransactionCount++;
    }
}
