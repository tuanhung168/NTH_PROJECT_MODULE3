package ra.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public static final String PATH_PRODUCT = "/Users/tuanhung168/IdeaProjects/Tuanhung_project_module03_carshop/src/ra/database/product.txt";
    public static final String PATH_USER = "/Users/tuanhung168/IdeaProjects/Tuanhung_project_module03_carshop/src/ra/database/user.txt";
    public static final String PATH_USERLOGIN = "/Users/tuanhung168/IdeaProjects/Tuanhung_project_module03_carshop/src/ra/database/userLogin.txt";
    public static final String PATH_PRODUCER = "/Users/tuanhung168/IdeaProjects/Tuanhung_project_module03_carshop/src/ra/database/producer.txt";
    public static final String PATH_PAY_HISTORY = "/Users/tuanhung168/IdeaProjects/Tuanhung_project_module03_carshop/src/ra/database/payHistory.txt";
    public List<T> readFormFile(String path) {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream fos = new FileInputStream(path);
            ObjectInputStream oos = new ObjectInputStream(fos);
            list = (List<T>) oos.readObject();
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (EOFException eof) {
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void writeFormFile(String path, List<T> list) {
        try {
            FileOutputStream fis = new FileOutputStream(path);
            ObjectOutputStream ois = new ObjectOutputStream(fis);
            ois.writeObject(list);
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
