public class BST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    // private fields
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int nodesInSubtree;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            nodesInSubtree = 0;
        }
    }

    public BST() {
        root = null;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node current, Key key, Value val) {
        if (current == null) {
            Node theNewNode = new Node(key, val);
            theNewNode.nodesInSubtree = 1;
            return theNewNode;
        }

        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            current.left = put(current.left, key, val);
        } else if (cmp > 0) {
            current.right = put(current.right, key, val);
        } else {
            current.value = val;
        }

        current.nodesInSubtree = size(current.left) + size(current.right) + 1;
        return current;
    }

    @Override
    public Value get(Key keyToFind) {
        return get(root, keyToFind);
    }

    private Value get(Node current, Key keyToFind) {
        if (current == null) {
            return null;
        }
        int cmp = keyToFind.compareTo(current.key);
        if (cmp < 0) {
            return get(current.left, keyToFind);
        } else if (cmp > 0) {
            return get(current.right, keyToFind);
        } else {
            return current.value;
        }
    }

    public Value getIterative(Key keyToFind) {
        Node current = root;
        while (current != null) {
            int cmp = keyToFind.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }


    /**
     * Remove key (and it's value) from the table.
     *
     * @param key
     */
    @Override
    public void delete(Key key) {
        SymbolTable.super.delete(key);
    }


    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node current) {
        if (current == null) {
            return 0;
        } else {
            return current.nodesInSubtree;
        }
    }

    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedQueue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node current, Queue<Key> q) {
        if (current == null) {
            return;
        }
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }
}
