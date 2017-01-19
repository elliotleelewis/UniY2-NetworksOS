public class AccountThread extends Thread
{
	BankAccount account;
	int localBalance;
	/**
	 * This class is the account thread that actually performs the random
	 * withdrawals and deposits. Multiple instances of this class will be
	 * running simultaneously and therefore this class has its critical section
	 * synchronised. The constructor here sets the account variable to be equal
	 * to the one passed in through its argument. This same account will be
	 * passed in to all the threads and therefore is a shared resource.
	 * 
	 * @author Elliot Lewis
	 * @param account Same bank account used for all account threads.
	 */
	public AccountThread(BankAccount account)
	{
		this.account = account;
		this.localBalance = 0;
	}
	/**
	 * This is the run method inside the account thread. It is what is run when
	 * the thread is started. This method contains the logic for the
	 * simultaneous account withdrawals and deposits and makes sure that the
	 * BankAccount is shared correctly. The critical sections are in a
	 * "synchronized" block, which means that if two threads access these
	 * sections at once, one of them will be blocked until the other is
	 * completed. This is to ensure that the shared resource (the BankAccount)
	 * isn't accessed incorrectly. Each iteration of the loop inside this
	 * function is then paused for 200ms before it continues on to the next
	 * iteration. When all 20 iterations have been completed, a message is
	 * output to the console and the thread is completed.
	 */
	public void run()
	{
		for(int i = 0; i < 20; i++) {
			int value = (int) (Math.random() * 10);
			if(Math.random() > 0.5) {
				synchronized(account) {
					account.withdraw(value);
					account.addTransaction(Transaction.WITHDRAWAL, value, getId());
					localBalance += value;
				}
			}
			else {
				synchronized(account) {
					account.deposit(value);
					account.addTransaction(Transaction.DEPOSIT, value, getId());
					localBalance -= value;
				}
			}
			try {
				sleep(200);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("THREAD " + getId() + " " + localBalance);
	}
}