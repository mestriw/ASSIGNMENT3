import java.util.Random;

public class TestingClass {

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> hashTable = new MyHashTable<>();
        Random randomGenerator = new Random();

        // Populate the hash table with random keys and student values
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(randomGenerator.nextInt(1000));
            Student value = new Student("Student " + i);
            hashTable.put(key, value);
        }

        // Print the number of elements in each bucket of the hash table
        MyHashTable.HashNode<MyTestingClass, Student>[] buckets = hashTable.getBucketArray();
        for (int i = 0; i < buckets.length; i++) {
            int elementCount = 0;
            MyHashTable.HashNode<MyTestingClass, Student> currentNode = buckets[i];
            while (currentNode != null) {
                elementCount++;
                currentNode = currentNode.next;
            }
            System.out.println("Bucket " + i + ": " + elementCount + " elements");
        }

        // Create and populate the binary search tree (BST)
        bst<Integer, String> binarySearchTree = new bst<>();

        for (int i = 0; i < 10000; i++) {
            int key = randomGenerator.nextInt(1000);
            String value = "Value " + i;
            binarySearchTree.put(key, value);
        }

        // Print the size of the BST
        System.out.println("Size of BST: " + binarySearchTree.size());

        // Iterate over and print all elements in the BST
        for (Integer key : binarySearchTree.iterator()) {
            System.out.println("Key: " + key + ", Value: " + binarySearchTree.get(key));
        }
    }

    // Class representing a key for the hash table
    static class MyTestingClass {
        int value;

        public MyTestingClass(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MyTestingClass other = (MyTestingClass) obj;
            return value == other.value;
        }
    }

    // Class representing a student as the value in the hash table
    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

