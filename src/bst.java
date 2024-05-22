import java.util.ArrayList;

public class bst<K extends Comparable<K>, V> {
    private Node root;

    // Node class represents each node in the binary search tree
    private class Node {
        K key;
        V val;
        Node left, right;
        int size;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    // Returns the number of nodes in the tree
    public int size() {
        return size(root);
    }

    // Helper method to return the size of a given node
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    // Inserts a key-value pair into the tree
    public void put(K key, V val) {
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    // Helper method to insert a key-value pair starting from a given node
    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    // Retrieves the value associated with the given key
    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    // Deletes the node with the given key from the tree
    public void delete(K key) {
        root = delete(root, key);
    }

    // Helper method to delete a node starting from a given node
    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    // Finds the node with the minimum key
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    // Deletes the node with the minimum key
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    // Returns an iterable collection of keys in the tree
    public Iterable<K> iterator() {
        ArrayList<K> keys = new ArrayList<>();
        inorder(root, keys);
        return keys;
    }

    // Helper method to perform in-order traversal and collect keys
    private void inorder(Node x, ArrayList<K> keys) {
        if (x == null) return;
        inorder(x.left, keys);
        keys.add(x.key);inorder(x.right, keys);
    }
}
