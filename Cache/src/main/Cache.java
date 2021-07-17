package main;
import java.util.*;

public class Cache <K,V> {

    private DLLNode head, tail;
    private Map<K,DLLNode> map;
    private int size;
    private int capacity;

    class DLLNode {
        K key;
        V value;
        DLLNode next;
        DLLNode prev;
    }

    public Cache(int capacity){
        map = new HashMap<>();
        head = new DLLNode();
        tail = new DLLNode();
        head.next = tail;
        tail.prev = head;
        this.size = 0;
        this.capacity = capacity;
    }

    private void addNode(DLLNode node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    private DLLNode removeNode(DLLNode node){
        DLLNode prev = node.prev;
        DLLNode next = node.next;
        prev.next = next;
        next.prev = prev;
        node.next = node.prev = null;
        return node;
    }

    public V get(K key){
        if(!map.containsKey(key)){
            System.out.println("Getting key:"+key+" value:null");
            return null;
        }
        DLLNode node = map.get(key);
        removeNode(node);
        addNode(node);
        System.out.println("Getting key:"+key+" value:"+node.value);
        return node.value;
    }

    public K put(K key, V value){
        System.out.println("Setting key:"+key+" value:"+value);
        DLLNode current = map.get(key);
        if(current!=null){
            current.value = value;
            removeNode(current);
        }
        else{
            current = new DLLNode();
            size++;
            if(size>capacity){
                DLLNode removed = removeNode(tail.prev);
                map.remove(removed.key);
            }
            current.key = key;
            current.value = value;
        }
        addNode(current);
        map.put(key,current);
        return key;
    }

    public K remove(K key){
        if(!map.containsKey(key)){
            return null;
        }
        DLLNode current = map.get(key);
        removeNode(current);
        map.remove(key);
        return key;
    }
}
