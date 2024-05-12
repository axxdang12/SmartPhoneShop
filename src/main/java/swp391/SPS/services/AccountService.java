package swp391.SPS.services;

import swp391.SPS.entities.Account;

public interface AccountService {
    String getUserName(int accountId);
    Account addAccount(Account account);
}
