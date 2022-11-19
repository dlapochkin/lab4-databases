package src;

/*
 * Исключение, возникающее при попытке создания записи с уже использвованной почтой.
 */
public class AccountAlreadyExistsException extends Exception {
    AccountAlreadyExistsException(String email) {
        System.out.printf("Электронная почта %s уже используется.\n", email);
        printStackTrace();
    }
}
