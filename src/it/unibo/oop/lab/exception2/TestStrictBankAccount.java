package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * JUnit test to test {@link StrictBankAccount}.
 * 
 */
public final class TestStrictBankAccount {

    /**
     * Used to test Exceptions on {@link StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
	/*
	 * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a scelta,
	 * con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
	 * 
	 * 2) Effetture un numero di operazioni a piacere per verificare che le
	 * eccezioni create vengano lanciate soltanto quando opportuno, cioè in presenza
	 * di un id utente errato, oppure al superamento del numero di operazioni ATM
	 * gratuite.
	 */
	AccountHolder pippo = new AccountHolder("Pippo", "Pippi", 0);
	AccountHolder pluto = new AccountHolder("Pluto", "Pluti", 1);

	BankAccount acc1 = new StrictBankAccount(pippo.getUserID(), 10000, 10);
	//BankAccount acc2 = new StrictBankAccount(pluto.getUserID(), 10000, 10);

	// Correct id test
	try {
	    acc1.deposit(pippo.getUserID(), 10);
	} catch (WrongAccountHolderException e) {
	    fail("Should not have thrown exception:" + e.getMessage());
	}

	// Wrong id test
	try {
	    acc1.deposit(pluto.getUserID(), 10);
	    fail("Should have thrown exception");
	} catch (WrongAccountHolderException e) {
	    assertNotNull(e.getMessage());
	}

	// Withdraw too much
	try {
	    acc1.withdraw(pippo.getUserID(), 11000);
	    fail("Should have thrown exception");
	} catch (NotEnoughFoundsException e) {
	    assertNotNull(e.getMessage());
	} catch (WrongAccountHolderException e) {
	    fail("Wrong exception thrown");
	}

	// Withdraw from ATM too many times
	try {
	    for (int i = 0; i < 10; i++) {
		acc1.depositFromATM(pippo.getUserID(), 10);
	    }
	    fail("Should have thrown exception");
	} catch (TransactionsOverQuotaException e) {
	    assertNotNull(e.getMessage());
	} catch (WrongAccountHolderException e) {
	    fail("Wrong exception thrown");
	}
    }
}
