public class MyHashTable<K, V> {
    // Nested class to represent each node in the hash table
    public static class HashNode<K, V> {
        private K key;
        private V value;
        public HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }

    private HashNode<K, V>[] bucketArray;
    private int M = 11; // Default capacity
    private int size; // Number of elements in the hash table

    // Default constructor
    public MyHashTable() {
        bucketArray = (HashNode<K, V>[]) new HashNode[M];
    }

    // Constructor with specified number of buckets
    public MyHashTable(int numBuckets) {
        this.M = numBuckets;
        bucketArray = (HashNode<K, V>[]) new HashNode[numBuckets];
    }

    // Method to compute the index for a given key
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % M);
    }

    // Method to add a key-value pair to the hash table
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (bucketArray[bucketIndex] == null) {
            bucketArray[bucketIndex] = newNode;
        } else {
            HashNode<K, V> current = bucketArray[bucketIndex];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update value for existing key
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value; // Update value for existing key at last node
            } else {
                current.next = newNode;
            }
        }
        size++;
    }

    // Method to retrieve the value associated with a given key
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> current = bucketArray[bucketIndex];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Key not found
    }

    // Method to remove a key-value pair from the hash table
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> current = bucketArray[bucketIndex];
        HashNode<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    bucketArray[bucketIndex] = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null; // Key not found
    }

    // Method to check if a value is present in the hash table
    public boolean contains(V value) {
        for (HashNode<K, V> headNode : bucketArray) {
            HashNode<K, V> current = headNode;
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false; // Value not found
    }

    // Method to get the key associated with a given value
    public K getKey(V value) {
        for (HashNode<K, V> node : bucketArray) {
            HashNode<K, V> current = node;
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null; // Value not found
    }

    // Method to get the underlying array of buckets
    public HashNode<K, V>[] getBucketArray() {
        return bucketArray;
    }
}
