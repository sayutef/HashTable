package models;

import models.FuncionHash1;
import models.FuncionHash2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private int size;
    private LinkedList<Entry>[] table;
    private FuncionHash1 hashFunction1;
    private FuncionHash2 hashFunction2;

    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[this.size];
        for (int i = 0; i < this.size; i++) {
            table[i] = new LinkedList<>();
        }
        this.hashFunction1 = new FuncionHash1();
        this.hashFunction2 = new FuncionHash2();
    }

    public void put(Object key, Object value, int hashFunction) {
        int hash = (hashFunction == 1) ? hashFunction1.hashAscii(key.toString()) : hashFunction2.hashDivision(key.toString());
        int index = findIndex(hash);

        LinkedList<Entry> bucket = table[index];
        for (Entry entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry(key, value));
    }

    public Object get(Object key, int hashFunction) {
        int hash = (hashFunction == 1) ? hashFunction1.hashAscii(key.toString()) : hashFunction2.hashDivision(key.toString());
        int index = findIndex(hash);

        LinkedList<Entry> bucket = table[index];
        for (Entry entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public List<Integer> searchAndGetIndices(Object key) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            LinkedList<Entry> bucket = table[i];
            for (Entry entry : bucket) {
                if (entry.getKey().equals(key)) {
                    indices.add(i);
                    break;
                }
            }
        }
        return indices;
    }

    public List<String> getDataAtIndex(int index) {
        List<String> data = new ArrayList<>();
        if (index >= 0 && index < size) {
            LinkedList<Entry> bucket = table[index];
            for (Entry entry : bucket) {
                data.add(entry.getValue().toString());
            }
        } else {
            System.err.println("Index " + index + " is out of bounds.");
        }
        return data;
    }

    private int findIndex(int hash) {
        int index = hash % size;
        if (index < 0) {
            index += size;
        }
        return index;
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            LinkedList<Entry> bucket = table[i];
            if (!bucket.isEmpty()) {
                System.out.print("Index " + i + ": ");
                for (Entry entry : bucket) {
                    System.out.print("[" + entry.getKey() + " = " + entry.getValue() + "] ");
                }
                System.out.println();
            }
        }
    }


}