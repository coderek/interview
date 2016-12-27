package me.derekzeng.code;


import java.util.*;
import java.time.*;


class LFUCache {

    class Item {
        int value=-1;
        int key;
        int frequency=0;
        Item prev;
        Item next;

        LocalDateTime lastUsed=LocalDateTime.now();

        Item(int k, int v) {
            value = v;
            key = k;
        }
        public String toString() {
            return "(" + key + "," + value + ") ";
        }

    }

    class CacheList {
        Item head;
        Item tail;
        int len = 0;

        void add(Item i) {
            if (head == null) {
                head = i;
                tail = i;
            } else {
                tail.next = i;
                i.prev = tail;
                tail = i;
            }
            len++;
        }

        int size() {
            return len;
        }

        void insertAfter(Item who, Item i) {
            if (who == null && i == head) {
                return;
            }

            if (who == i.prev) {
                return;
            }

            Item before = i.prev;
            if (before !=null) {
                before.next = i.next;
            }

            Item behind = i.next;
            if (behind != null) {
                behind.prev = i.prev;
            }
            i.prev = null;
            i.next = null;

            if (i == tail) {
                tail = before;
            }

            if (who == null) {
                i.next = head;
                head.prev = i;
                head = i;
            } else {
                Item tmp = who.next;
                who.next = i;
                i.prev = who;

                if (tmp != null) {
                    tmp.prev = i;
                }

                i.next = tmp;
            }
        }

        Item removeLast() {
            if (len > 0) {
                Item last = tail;
                tail = tail.prev;
                last.prev = null;

                if (tail == null) {
                    head = null;
                } else {
                    tail.next = null;
                }

                len--;
                return last;
            } else {
                return null;
            }
        }
        void debug() {
            Item i = head;
            System.out.print("Queue: ");
            while (i!=null) {
                System.out.print(i);
                i = i.next;
            }
            System.out.println();
            // System.out.println("Tail: " + tail + " Head: " + head );
        }
    }

    Map<Integer, Item> idMap;
    CacheList q;
    int capacity;

    public LFUCache(int capacity) {
        idMap = new HashMap<>();
        q = new CacheList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!idMap.containsKey(key)) {
            return -1;
        }
        Item i = idMap.get(key);
        i.frequency += 1;
        i.lastUsed = LocalDateTime.now();
        fix(i);
        q.debug();
        return i.value;
    }

    private void fix(Item i) {
        Item prev = i.prev;
        while (prev != null) {
            if (prev.frequency < i.frequency) {
                prev = prev.prev;
            } else if (prev.frequency == i.frequency && prev.lastUsed.compareTo(i.lastUsed) <= 0) {
                prev = prev.prev;
            } else {
                break;
            }
        }
        q.insertAfter(prev, i);
    }

    public void set(int key, int value) {
        if (!idMap.containsKey(key)) {
            Item item = new Item(key, value);
            if (capacity <= q.size()) {
                Item i = q.removeLast();
                if (i != null) {
                    // System.out.format("Removed %s \n", i.value);
                    idMap.remove(i.key);
                }
            }
            if (capacity > q.size()) {
                idMap.put(key, item);
                q.add(item);
                fix(item);
            }
        } else {
            Item i = idMap.get(key);
            i.value = value;
            i.lastUsed = LocalDateTime.now();
            i.frequency += 1;
            fix(i);
        }
        q.debug();
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.set(2, 1);
        cache.set(1, 1);
        cache.set(2, 3);
        cache.set(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
