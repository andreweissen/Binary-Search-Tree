/**
 * BinarySearchTree.java - Constructs the search tree
 * Begun 11/21/17
 * @author Andrew Eissen
 */

package binarysearchtreeproject3;

import java.util.ArrayList;

/**
 * Class for the construction of BinarySearchTrees
 * @see java.lang.Comparable
 */
final class BinarySearchTree<T extends Comparable<T>> {

    private Node root;
    private final ArrayList<String> sortedList;

    /**
     * Parameterized constructor
     * @param values
     */
    public BinarySearchTree(T[] values) {
        for (T value : values) {
            this.add(value);
        }
        this.sortedList = new ArrayList<>();
    }

    /**
     * Setter for <code>root</code>
     * @param value
     * @return void
     */
    private void setRoot(T value) {
        this.root = new Node(value);
    }

    /**
     * Getter for <code>root</code>
     * @return this.root
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * One of two such overloaded methods for inserting <code>Nodes</code>
     * @param value
     * @return void
     */
    private void add(T value) {
        if (this.root == null) {
            this.setRoot(value);
        } else {
            this.add(this.root, value);
        }
    }

    /**
     * One of two such overloaded methods for inserting <code>Nodes</code>
     * @param node
     * @param value
     * @return node
     */
    private Node add(Node node, T value) {
        if (node == null) {
            return new Node(value);
        } else {
            if (value.compareTo(node.getValue()) < 0) {
                node.setLeft(this.add(node.getLeft(), value));
            } else {
                node.setRight(this.add(node.getRight(), value));
            }
            return node;
        }
    }

    /**
     * Built on the idea of an inorderTraversal method, this method returns a string of set members
     * arranging in ascending order, joined by spaces.
     * @param node
     * @return String
     */
    public String inAscendingOrder(Node node) {
        if (node != null) {
            this.inAscendingOrder(node.getLeft());
            this.sortedList.add(node.getValue().toString());
            this.inAscendingOrder(node.getRight());
        }
        return String.join(" ", this.sortedList);
    }

    /**
     * Built on the idea of an inorderTraversal method, this method returns a string of set members
     * arranging in descending order, joined by spaces.
     * @param node
     * @return String
     */
    public String inDescendingOrder(Node node) {
        if (node != null) {
            this.inDescendingOrder(node.getRight());
            this.sortedList.add(node.getValue().toString());
            this.inDescendingOrder(node.getLeft());
        }
        return String.join(" ", this.sortedList);
    }

    /**
     * Node class for individual BST nodes, implemented in a manner similar to the suggested design
     * from the Project 3 Indications pseudocode.
     */
    final class Node {

        private T value;
        private Node left;
        private Node right;

        /**
         * Parameterized constructor
         * @param value
         */
        public Node(T value) {
            this.value = value;
        }

        // Setters

        /**
         * Setter for <code>value</code>
         * @param value
         * @return void
         */
        public void setValue(T value) {
            this.value = value;
        }

        /**
         * Setter for <code>left</code>
         * @param left
         * @return void
         */
        private void setLeft(Node left) {
            this.left = left;
        }

        /**
         * Setter for <code>right</code>
         * @param right
         * @return void
         */
        private void setRight(Node right) {
            this.right = right;
        }

        // Getters

        /**
         * Getter for <code>value</code>
         * @return this.value
         */
        public T getValue() {
            return this.value;
        }

        /**
         * Getter for <code>left</code>
         * @return this.left
         */
        public Node getLeft() {
            return this.left;
        }

        /**
         * Getter for <code>right</code>
         * @return this.right
         */
        public Node getRight() {
            return this.right;
        }
    }
}