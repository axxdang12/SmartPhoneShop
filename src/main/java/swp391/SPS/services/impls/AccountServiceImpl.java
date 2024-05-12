package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.SPS.entities.Account;
import swp391.SPS.repositories.AccountRepository;
import swp391.SPS.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String getUserName(int accountId) {
        Account account = accountRepository.findById(accountId).get();
        return account.getName();
    }

    @Override
    public Account addAccount(Account account) {
        Account accountAdded = accountRepository.save(account);
        return accountAdded;
    }
}
