package vsushko.geeksforgeeks.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vsushko
 */
public class BlockSynchronization {

    public static void main(String[] args) {
        Geek geek = new Geek();
        List<String> list = new ArrayList<>();
        geek.geekName("Vasya", list);
        System.out.println(geek.name);
    }
}

class Geek {
    String name = "";
    public int count = 0;

    public void geekName(String geek, List<String> list) {
        // only one thread is permitted to change geek's name at a time
        synchronized (this) {
            name = geek;
            // how many threads change geek's name
            count++;
        }
        // all other threads are permitted to add geek name into list
        list.add(geek);
    }
}
