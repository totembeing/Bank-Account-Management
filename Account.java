package assignment2;

import java.util.regex.*;

public class Account {

	private int accountNumber;
	private double accountBalance;
	private String accountName;

	public Account() {}
	public Account(String accountName, int accountNumber, double accountBalance) {

		/*If the user input for Account Name is valid, the instance variable is initialized to the given value*/
		if (validateAccountName(accountName)) {
			 this.accountName = accountName;
		}
		/*If the user input for the Account Name is invalid, the instance variable is initialized to a default value of "No Name"*/
		else {
			 this.accountName = "No Name";
		 }

		/*If the user input for Account Number is valid, the instance variable is initialized to the given value*/
		if (validateAccountNumber(accountNumber)) {
			this.accountNumber = accountNumber;
		}
		/*If the user input for Account Number is invalid, the instance variable is initialized to the given value of 0*/
		else {
			this.accountNumber = 0;
		}

		/*If the user input for Account Balance is valid, the instance variable is initialized to the given value*/
		if (validateAccountBalance(accountBalance)) {
			this.accountBalance = accountBalance;
		}
		else {
			this.accountBalance = 0.00;
		}
	}

	public String getAccountName() {
		return accountName;
	}

	public boolean setAccountName(String accountName) {

		//Using a negative lookahead to prevent the occurrence of more than one space and single quote

		if (validateAccountName(accountName)) {
			this.accountName = accountName;
			return true;
		}
		else {
			return false;
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public boolean setAccountNumber(int accountNumber) {

		/*Converting the accountNumber to a string using String.valueOf() method, because Pattern.matches accepts the
		second parameter as a string*/

		if (validateAccountNumber(accountNumber)) {
			this.accountNumber = accountNumber;
			return true;
		}
		else {
			return false;
		}
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public boolean setAccountBalance(double value) {
		if (validateAccountBalance(value)) {
			this.accountBalance = value;
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean equals(Object obj) {
		//Checks if the objects have similar value
		return this.accountNumber == ((Account) obj).accountNumber;
	}

	@Override
	public String toString() {
		return "The Account Number: " + accountNumber + ", named: " + accountName + " has balance: " + accountBalance;
	}

	public static boolean validateAccountNumber(int accountNumber) {
		return Pattern.matches("^[0-9]{5,9}$", String.valueOf(accountNumber));
	}

	public static boolean validateAccountBalance(double accountBalance) {
		return Pattern.matches("^-?\\d+(\\.\\d{1,2})?$", String.valueOf(accountBalance));
	}

	public static boolean validateAccountName(String accountName) {
		return Pattern.matches("^(?!.*'.*')(?!.*\\s.*\\s)[a-zA-Z-]{4,}$", accountName);
	}
}
