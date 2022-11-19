package src;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;



/*
 * Класс Singletone, отвечающий работу с файлом.
 * FileService instance - instance.
 */
public class FileService {
    private static FileService instance;

    public static synchronized FileService getInstance() {
        if(instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    /*
     * Метод осуществляет чтение файла базы данных.
     */
    public static List<Account> readCSV(String path) {
        List<List<String>> accounts = new ArrayList<List<String>>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                List<Account> item = new ArrayList<Account>();
                FileService.writeCSV(item, path);
                return item;
            }
            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String data = buff.readLine();
            while ( (data = buff.readLine()) != null ) {
                accounts.add(Arrays.asList(data.split(",")));
            }
            buff.close();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        return accounts.stream().map(rec -> new Account(rec.get(0), rec.get(1), rec.get(2), rec.get(3))).collect(Collectors.toList());
    }

     /*
     * Метод осуществляет запись в файл базы данных.
     */
    public static void writeCSV(List<Account> dataset, String path) {
        try {
            BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            buff.write("name,birthDate,email,password\n");
            for (Account item: dataset) {
                buff.write(String.format("%s,%s,%s,%s\n", item.getName(), item.getBirthDate(), item.getEmail(), item.getPassword()));
            }
            buff.close();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
