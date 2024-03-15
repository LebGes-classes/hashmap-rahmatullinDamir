public class HashMap<K,V> {
    private static final int defaultCapacity = 16;
    private static final double defaultLoadFactor = 0.75;
    private int capacity;
    private double loadFactor;
    private int size;
    private Node<K, V>[] table;

    private class Node<K, V> {
        final K key;
        V value;
        int hash;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash =  key.hashCode() % capacity;
        }
    }

    public HashMap() {
        this.capacity = defaultCapacity;
        this.loadFactor = defaultLoadFactor;
        this.table = new Node[defaultCapacity];
    }


    private int indexFromKey(K key) {
        return key.hashCode() % capacity;
    }

    public void put(K key, V value) {
        int index = indexFromKey(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        Node<K, V> newNode = new Node<>(key, value, table[index]);
        table[index] = newNode;
        size++;
        if (size > capacity * loadFactor) {
            resize();
        }
    }

    private void resize() {
        int tempCapacity = capacity * 2;
        Node<K, V>[] tempTable = new Node[capacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                Node<K, V> next = node.next;
                int index = indexFromKey(node.key);
                node.next = tempTable[index];
                tempTable[index] = node;
                node = next;
            }
        }
        table = tempTable;
        capacity = tempCapacity;
    }

    public V get(K key) {
        int index = indexFromKey(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        int index = indexFromKey(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public void remove(K key) {
        int index = indexFromKey(key);
        Node<K, V> node = table[index];
        Node<K, V> prevNode = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prevNode == null) {
                    table[index] = node.next;
                } else {
                    prevNode.next = node.next;
                }
                size--;
                return;
            }
            prevNode = node;
            node = node.next;
        }

    }
}
