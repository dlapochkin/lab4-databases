package src;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/*
 * Класс Main, тело работы программы.
 */
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String path = null;

        /*
         * Для демонстрации работы программы были использованы
         *  System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n") (ожидание отклика клавиатуры на каждом шаге)
         *  пустые catch(...) {} (демонстрация исключений)
         */

        /*
         * Описание пути к базе данных.
         * String path - путь к папке проекта.
         * String database - файл базы данных.
         */
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException exception) {}
        
        System.out.println("Введите имя файла с базой данных. \"data.csv\", к примеру.");
        String database = "\\" + input.nextLine();

        /*
         * Удаляем файл с базой данных (если существует) с целью демонстрации процесса с момента создания базы данных. 
         */
        File file = new File(path + database);
        if (file.exists()) {
            file.delete();
            System.out.printf("\nУдален файл %s%s.\n", path, database);
            System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
            input.nextLine();
        }
            
        /* 
         * Создание экземпляра manager класса FileAccountManager.
         */
        FileAccountManager manager = new FileAccountManager(path + database);
        
        System.out.println("Начало работы с FileAccountManager.");
        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        /*
         * Создание аккаунтов A и B.
         * A: Иванов Иван Иванович, 13.05.1997, i.ivanov@gmail.com, ivanov
         * B: Петров Петр Петрович, 10.10.2000, p.petrov@gmail.com, petrov
         */

        try {
            manager.register(new Account("Иванов Иван Иванович", "13.05.1997", "i.ivanov@gmail.com", "ivanov"));
            manager.register(new Account("Петров Петр Петрович", "10.10.2000", "p.petrov@gmail.com", "petrov"));
        } catch (AccountAlreadyExistsException exception) {}

        System.out.println("Созданы аккаунты A и B.");
        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        /*
         * Повторное создание аккаунта A.
         * Пустой блок catch(AccountAlreadyExistsException exception) используется для демонстрации работы программы.
         */
        try {
            manager.register(new Account("Иванов Иван Иванович", "13.05.1997", "i.ivanov@gmail.com", "ivanov"));
        } catch(AccountAlreadyExistsException exception) {}
        
        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        /*
         * Цепочка вызовов логина
         * А(н) -> B(н) -> A(н) -> A(н) -> A(н) -> A(н) blocked -> A(у) ex -> B(н) -> B(у) -> B(н) -> B(н) -> B(н) -> B(н) -> B(у)
         * Пустые блоки catch используются для демонстрации работы программы.
         */
        try {
            manager.login("i.ivanov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}
        
        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {    
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("i.ivanov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("i.ivanov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("i.ivanov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("i.ivanov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("i.ivanov@gmail.com", "ivanov");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            Account petrov = manager.login("p.petrov@gmail.com", "petrov");
            System.out.printf("Осуществлен успешный вход в %s.\n", petrov.getEmail());
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.login("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            Account petrov = manager.login("p.petrov@gmail.com", "petrov");
            System.out.printf("Осуществлен успешный вход в %s.\n", petrov.getEmail());
        } catch(WrongCredentialsException exception) {       
        } catch (AccountBlockedException exception) {}

        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        /*
         * Удаление аккаунтов A и B.
         */
        try {
        manager.removeAccount("i.ivanov@gmail.com", "ivanov");
        System.out.println("Удален аккаунт A.");
        } catch (WrongCredentialsException exception) {}
        
        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();

        try {
            manager.removeAccount("p.petrov@gmail.com", "wrong_password");
        } catch(WrongCredentialsException exception) {}
        
        System.out.printf("\n\033[3mНажмите Enter для перехода на следующий шаг.\033[0m\n");
        input.nextLine();
        
        try {
            manager.removeAccount("p.petrov@gmail.com", "petrov");
            System.out.println("Удален аккаунт A.");
        } catch (WrongCredentialsException exception) {}

        input.close();
    }
}
