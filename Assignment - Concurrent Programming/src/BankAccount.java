import java.util.*;
public class BankAccount
{
	int balance;
	ArrayList<Transaction> transactions;
	/**
	 * The bank account class is the class that the account threads will be
	 * simultaneously accessing, it contains a balance and various methods to
	 * alter that balance, such as deposit and withdraw.
	 * 
	 * @author Elliot Lewis
	 * @param balance
	 *            Starting balance for the account.
	 */
	public BankAccount(int balance)
	{
		this.balance = balance;
		this.transactions = new ArrayList<>();
	}
	/**
	 * This method withdraws money from the bank account. If there isn't enough
	 * balance in the account to withdraw, the thread will wait for a deposit to
	 * be made and then it will reattempt the withdrawal.
	 * 
	 * @param value
	 *            The amount to be withdrawn.
	 */
	public void withdraw(int value)
	{
		if(balance - value < 0) {
			try {
				System.out.println("Thread waiting!");
				wait();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			withdraw(value);
		}
		else {
			balance -= value;
		}
	}
	/**
	 * This method deposits money into the bank account, it also notifies other
	 * threads that were waiting to make a withdrawal allowing them to reattempt
	 * that withdrawal.
	 * 
	 * @param value
	 *            The amount to be deposited.
	 */
	public void deposit(int value)
	{
		balance += value;
		notifyAll();
	}
	/**
	 * This method gets the account balance.
	 * 
	 * @return Account balance.
	 */
	public int getBalance()
	{
		return balance;
	}
	/**
	 * This method stores a new transaction in the transactions ArrayList, this
	 * is done so that the transactions can all be printed out at the end rather
	 * than as they go. It stores in each transaction object, the type of
	 * transaction (deposit or withdrawal), the amount of that transaction, the
	 * current balance of the account and the thread ID that made the
	 * transaction.
	 * 
	 * @param type
	 *            Integer that refers to either a deposit or withdrawal.
	 * @param value
	 *            The value of the transaction.
	 * @param threadID
	 *            The threadID that made the transaction.
	 */
	public void addTransaction(int type, int value, long threadID)
	{
		transactions.add(new Transaction(type, value, getBalance(), threadID - 10));
	}
	/**
	 * This method prints out the table of transactions. It iterates through the
	 * transactions ArrayList and as it goes, it prints the details of each one
	 * out to the console. The table formatting style is done with
	 * `System.out.format`.
	 */
	public void printTransactions()
	{
		System.out.format("%-12s%-12s%-12s%-12s" + System.getProperty("line.separator"), "Transaction", "Withdrawal",
				"Deposit", "Balance");
		for(int i = 0; i < transactions.size(); i++) {
			Transaction t = transactions.get(i);
			if(t.getType() == Transaction.WITHDRAWAL) {
				System.out.format("%-12s£%-11d%-12s£%-11d" + System.getProperty("line.separator"),
						(i + 1) + "(" + t.getThreadID() + ")", t.getValue(), "", t.getAccountBalance());
			}
			else if(t.getType() == Transaction.DEPOSIT) {
				System.out.format("%-12s%-12s£%-11d£%-11d" + System.getProperty("line.separator"),
						(i + 1) + "(" + t.getThreadID() + ")", "", t.getValue(), t.getAccountBalance());
			}
		}
	}
}