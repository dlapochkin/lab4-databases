package src;

import java.util.List;

/*
 * Класс, являющийся реализацией интерфейса AccountManager.
 * String path - путь к базе данных.
 * List<Account> accounts - список аккаунтов базы данных.
 */
public class FileAccountManager implements AccountManager {
    private String path;
    private List<Account> accounts;

    FileAccountManager(String path) {
        this.path = path;
        this.accounts = FileService.readCSV(this.path);
    } 

    public void register(Account account) throws AccountAlreadyExistsException {
        Account item = this.accounts.stream().filter(rec -> rec.getEmail().equals(account.getEmail())).findFirst().orElse(null);
        if (item != null) {
            throw new AccountAlreadyExistsException(account.getEmail());
        };
        this.accounts.add(account);
        FileService.writeCSV(this.accounts, this.path);
    }

    public Account login(String email, String password) throws WrongCredentialsException, AccountBlockedException {
        Account account = this.accounts.stream().filter(rec -> rec.getEmail().equals(email)).findFirst().orElse(null);
        if (account != null) {
            if (!account.getPassword().equals(password)) {
                FailedLoginCounter.count(account);
                throw new WrongCredentialsException(account.getEmail());
            }
            else if (account.getBlocked()) {
                throw new AccountBlockedException(account.getEmail());
            }
            else {
                FailedLoginCounter.clear(account);
                return account;
            }
        }
        else {
            throw new WrongCredentialsException(null);
        }
    }

    public void removeAccount(String email, String password) throws WrongCredentialsException {
        Account item = this.accounts.stream().filter(rec -> rec.getEmail().equals(email) & rec.getPassword().equals(password)).findFirst().orElse(null);
        if (item != null) {
            this.accounts.remove(item);
            FileService.writeCSV(this.accounts, this.path);
        }
        else {
            throw new WrongCredentialsException(null);
        }
    }
}
