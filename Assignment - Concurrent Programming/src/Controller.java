public class Controller
{
	/**
	 * This is the controller class, it is the main class for the application
	 * and contains the main method.
	 * 
	 * @author Elliot Lewis
	 * @version 1.0
	 */
	/**
	 * The main method is the method that is run when the program is run. It
	 * takes in the thread count and starting balance of the accounts as program
	 * arguments, makes sure that they are valid and then stores them in
	 * variables. It then creates and initialises the bank account and account
	 * threads. It then starts and joins the threads and then once the threads
	 * have been successfully joined, the transactions are printed out at the
	 * end.
	 * 
	 * @param args
	 *            Program arguments
	 */
	public static void main(String[] args)
	{
		if(args.length != 2) {
			System.err.println("Invalid usage. Usage is: Controller [threadCount] [initialBalance]");
			System.exit(0);
		}
		try {
			if(Integer.parseInt(args[0]) <= 0) {
				System.out.println("Invalid usage. Thread count must be a positive integer.");
			}
			Integer.parseInt(args[1]);
		}
		catch(Exception e) {
			System.err.println("Invalid usage. Usage is: Controller [threadCount] [initialBalance]");
			System.exit(0);
		}
		int threadCount = Integer.parseInt(args[0]);
		int initBalance = Integer.parseInt(args[1]);
		BankAccount account = new BankAccount(initBalance);
		AccountThread[] threads = new AccountThread[threadCount];
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new AccountThread(account);
		}
		for(AccountThread thread: threads) {
			thread.start();
		}
		for(AccountThread thread: threads) {
			try {
				thread.join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(System.getProperty("line.separator") + "Complete!" + System.getProperty("line.separator"));
		account.printTransactions();
	}
}