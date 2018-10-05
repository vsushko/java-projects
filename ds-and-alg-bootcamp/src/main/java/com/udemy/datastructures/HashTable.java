package com.udemy.datastructures;

/**
 * @author vsushko
 */
public class HashTable {

    private int INITIAL_SIZE = 16;
    private HashEntry[] data; // LinkedList

    class HashEntry {
        String key;
        String value;
        HashEntry next;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashTable() {
        data = new HashEntry[INITIAL_SIZE];
    }

    public void put(String key, String value) {
        // get the index
        int index = getIndex(key);

        // create the linked list entry
        HashEntry entry = new HashEntry(key, value);

        // if no entry there - add it
        if (data[index] == null) {
            data[index] = entry;
        } else {
            // else handle collision by adding to end of linked list
            HashEntry dataEntry = data[index];
            // walk to the end...
            while (dataEntry.next != null) {
                dataEntry = dataEntry.next;
            }
            // add our new entry there
            dataEntry.next = entry;
        }
    }

    public String get(String key) {
        // get the index
        int index = getIndex(key);

        // get the current list of entries
        HashEntry entries = data[index];

        // if we have existing entries against this key...
        if (entries != null) {
            // else walk chain until find a match
            while (!entries.key.equals(key) && entries.next != null) {
                entries = entries.next;
            }
            // then return it
            return entries.value;
        }
        // it we have no entries against this key...
        return null;
    }

    private int getIndex(String key) {
        // get the hash code
        int hashCode = key.hashCode();
        System.out.println("hashCode = " + hashCode);

        // convert to index
        // (hash & 0x7FFFFFFF) will result in a positive integer
//        int index = (hashCode & 0x7fffffff) % INITIAL_SIZE;
//        int index = hashCode % INITIAL_SIZE;
        int index = hashCode & (data.length - 1) % INITIAL_SIZE;
        System.out.println("index = " + index);

        // hack to force collision for testing
        if (key.equals("John Smith") || key.equals("Sandra Dee")) {
            index = 4;
        }

        return index;
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : data) {
            if (entry == null) {
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while (temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }
}
