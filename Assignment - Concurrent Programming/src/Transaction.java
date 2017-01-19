public class Transaction
{
	public static final int DEPOSIT = 0;
	public static final int WITHDRAWAL = 1;
	int type, value, accountBalance;
	long threadID;
	/**
	 * This class is used to store the transactions as they occur so they can be
	 * printed out or referenced later on in the program's execution. It stores
	 * the transaction's type (deposit or withdrawal), the value of the
	 * transaction, the bank account's balance at the time of the transaction
	 * and the threadID that made the transaction.
	 * 
	 * @author Elliot Lewis
	 * @param type
	 *            Integer that refers to either a deposit or withdrawal.
	 * @param value
	 *            The value of the transaction.
	 * @param accountBalance
	 *            The current balance of the bank account.
	 * @param threadID
	 *            The threadID that made the transaction.
	 */
	public Transaction(int type, int value, int accountBalance, long threadID)
	{
		this.type = type;
		this.value = value;
		this.accountBalance = accountBalance;
		this.threadID = threadID;
	}
	/**
	 * This method gets the type of transaction.
	 * 
	 * @return The type of transaction (deposit or withdrawal)
	 */
	public int getType()
	{
		return type;
	}
	/**
	 * This method gets the value of the transaction.
	 * 
	 * @return The value of the transaction.
	 */
	public int getValue()
	{
		return value;
	}
	/**
	 * This method gets the account balance at the time of the transaction.
	 * 
	 * @return The account balance at the time of the transaction.
	 */
	public int getAccountBalance()
	{
		return accountBalance;
	}
	/**
	 * This method gets the thread ID that made the transaction.
	 * 
	 * @return The thread ID that made the transaction.
	 */
	public long getThreadID()
	{
		return threadID;
	}
}