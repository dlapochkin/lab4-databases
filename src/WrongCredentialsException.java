package src;

/*
 * Исключение, возникающее при вводе неверных данных во время входа.
 */
public class WrongCredentialsException extends Exception {
    WrongCredentialsException (String email) {
        if (email != null) {
            System.out.printf("Неверно введен пароль для пользователя %s.\n", email);
            printStackTrace();
        }
        else {
            System.out.println("Неверно введены данные.");
            printStackTrace();
        }
    }
}
