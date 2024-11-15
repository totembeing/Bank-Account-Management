package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Bank {

	private String bankName;
	private String branchLocation;
	private List<Account> accounts = new ArrayList<Account>();

	public static enum BranchLocations {
		MAPLEVIEW_DRIVE_W,
		YONGE_ST,
		ARDAGH_RD,
		MAPLEVIEW_DRIVE_E,
		COLLIER_ST,
		BAYFIELD_ST,
		CUNDLES_RD
	};
	
	public Bank() {}

	public Bank(String bankName, String branchLocation) {
		if (validateBankName(bankName)) {
			this.bankName = bankName;
		}
		else {
			this.bankName = "ABC Bank";
		}

		if (validateBranchLocation(branchLocation)) {
			this.branchLocation = branchLocation;
		}
		else {
			this.branchLocation = "MAPLEVIEW_DRIVE_E";
		}
	}

	public Bank(String bankName, BranchLocations branchLocation) {
		if (validateBankName(bankName)) {
			this.bankName = bankName;
		} else {
			this.bankName = "ABC Bank";
		}

		//String.valueOf() converts BranchLocations enum value to its String representation, which is stored in the instance variable
		this.branchLocation = String.valueOf(branchLocation);
	}

	public String getBankName() {
		return bankName;
	}

	public boolean setBankName(String bankName) {
		if (validateBankName(bankName)) {
			this.bankName = bankName;
			return true;
		} else {
			return false;
		}
	}

	public boolean setBranchLocation(String branchLocation) {
		if (validateBranchLocation(branchLocation)) {
			this.branchLocation = branchLocation;
			return true;
		} else {
			return false;
		}
	}

	public String getBranchLocation() {
		return "The branch is located at: " + branchLocation;
	}

	/*Since the Bank Branch Location is already present in the enum, there is no need to validate it. Instead, we just save the
	  constant value from the enum to the variable and return true */
	public boolean setBranchLocation(BranchLocations branchLocation) {
		this.branchLocation = String.valueOf(branchLocation);
		return true;
	}

	public Account getAccountByNumber(int accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return new Account();
    }
	
	public boolean addAccount(Account account) {
		for (Account existingAccount : accounts) {
			if (existingAccount.getAccountNumber() == account.getAccountNumber()) {
				return false;
			}
		}
		accounts.add(account);
		return true;
	}

	public boolean addAccount(String accountName, int accountNumber, double accountBalance) {
		for (Account account: accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return false;
			}
		}
		accounts.add(new Account(accountName, accountNumber, accountBalance));
		return true;
	}

	public Account viewAccount(int accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return new Account();
	}

	public Account viewAccount(byte index) {

		//Checks if the index lies within the size of collection and is positive in nature
		if (index < accounts.size() && index >= 0) {
			return accounts.get(index);
		}
		return new Account();
	}

	/*All the following functions will be written considering that the first parameter is the locator of the account
	  and the second value is the account property that needs to be changed/*/

	public boolean modifyAccount(int accountNumber, String accountName) {
		if (Account.validateAccountNumber(accountNumber) && Account.validateAccountName(accountName)) {
			for (Account account : accounts) {
				if (account.getAccountNumber() == accountNumber) {
					account.setAccountName(accountName);

				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	public boolean modifyAccount(int accountNumber, double accountBalance) {
		if (Account.validateAccountNumber(accountNumber) && Account.validateAccountBalance(accountBalance)) {
			for (Account account : accounts) {
				if (account.getAccountNumber() == accountNumber) {
					account.setAccountBalance(accountBalance);
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {
		if (Account.validateAccountNumber(accountNumber) && Account.validateAccountBalance(accountBalance) && Account.validateAccountName(accountName)) {
			for (Account account : accounts) {
				if (account.getAccountNumber() == accountNumber) {
					account.setAccountBalance(accountBalance);
					account.setAccountName(accountName);
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	public boolean modifyAccount(byte index, String accountName) {
		if (Account.validateAccountName(accountName)) {
			accounts.get(index).setAccountName(accountName);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean modifyAccount(byte index, double accountBalance) {
		if (Account.validateAccountBalance(accountBalance)) {
			accounts.get(index).setAccountBalance(accountBalance);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean modifyAccount(byte index, String accountName, double accountBalance) {
		if (Account.validateAccountName(accountName) && Account.validateAccountBalance(accountBalance)) {
			accounts.get(index).setAccountBalance(accountBalance);
			accounts.get(index).setAccountName(accountName);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean deleteAccount(int accountNumber) {
		for (Account account: accounts) {
			if (account.getAccountNumber() == accountNumber) {
				accounts.remove(account);
				return true;
			}
		}
		return false;
	}

	public boolean deleteAccount(byte index) {
		if (index >= 0 && index < accounts.size()) {
			accounts.remove(index);
			return true;
		}
		else {
			return false;
		}
	}


	public boolean validateBankName(String bankName) {
		/*Using lookaheads to ensure that the string has a minimum of 8 characters and maximum of 3 digits in it*/
		return Pattern.matches("(?=.{8,}$)(?=.[0-9]{0,3})[a-zA-Z&]*\\s?", bankName);
	}

	public boolean validateBranchLocation(String branchLocation) {
		try {
			/*valueOf() searches the enum for the corresponding string input and if it matches, returns the constant, if not,
			  the program will throw and IllegalArgumentException Error, meaning there is no constant in the enum that matches the string.*/
			BranchLocations.valueOf(branchLocation);
			return true;
		}
		catch (IllegalArgumentException e) {
			return false;
		}
	}
}
