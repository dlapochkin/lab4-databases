package src;

/*
 * Исключение, возникающее при попытке входа на заблокированный аккаунт.
 */
public class AccountBlockedException extends Exception {
    AccountBlockedException(String email) {
        System.out.printf("Аккаунт %s заблокирован.", email);
        printStackTrace();
    }
}
