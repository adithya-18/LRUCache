package main;
import main.Cache;

public class Main {

    public static void main(String[] args) {

        Cache lruCache = new Cache(5);
        //Test code to demonstrate cache eviction and generics
        lruCache.put(1,2);
        lruCache.put(1,"Hello");
        lruCache.put(5,"Sample");
        lruCache.get(1);
        lruCache.get(5);
        lruCache.put(6,7);
        lruCache.put(2,4);
        lruCache.put(10,100);
        lruCache.get(1);
        lruCache.put(20,200);
        lruCache.get(5);
        lruCache.get(20);
        Object current = lruCache.get(1);
        if(String.class.equals(current.getClass())){
            System.out.println("Get key:1 value is type String :"+current);
        }
        lruCache.put(1,3);
        current = lruCache.get(1);
        if(Integer.class.equals(current.getClass())){
            System.out.println("Get key:1 value is type Integer :"+current);
        }
    }
}
