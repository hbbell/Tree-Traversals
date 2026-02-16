/**
 * This program makes a tree of generics, then runs different traversals on the tree
 * @author Hannah Bell
 * Last Modified: 2/16/26
 */

import java.util.ArrayList;

public class TreeMart <E extends Comparable<E>> implements BinarySearchTreeInterface<E> {

    private class Node{
        private Node left;
        private Node right;
        private E obj;
    }

    Node root;
    int size;

    public int getSize(){
        return size;
    }

    /**
     * This method calls its helper method, and then adds element in correct spot on the tree and increments size
     * @param obj the Element to add to the tree.
     */
    public void add(E obj) {
        if (root == null){
            root = new Node();
            root.obj = obj;
            size++;
        }
        else {
            addHelper(obj, root);
        }
    }

    /**
     * Helper for add method recursively checking where to add new obj
     * @param obj generic you want to add to the tree
     * @param search the start of where searching if in the tree or not
     */
    private void addHelper(E obj, Node search){
        if(search.obj.compareTo(obj) == 0){
            return;
        }
        if (search.obj.compareTo(obj) < 0){
            if (search.right != null) {
                addHelper(obj, search.right);
                return;
            }
            else {
                Node newNode = new Node();
                newNode.obj = obj;
                search.right = newNode;
                size++;

            }
        }
        if (search.obj.compareTo(obj) > 0){
            if (search.left != null) {
                addHelper(obj, search.left);
                return;
            }
            else {
                Node newNode = new Node();
                newNode.obj = obj;
                search.left = newNode;
                size++;
            }
        }
    }


    /**
     * This method returns true if obj is in tree, and false if not
     * @param obj the item were checking if its in the tree or not
     * @return boolean if element in tree or not
     */
    public boolean contains(E obj) {
        Node search = root;
        while (search != null) {
            if (obj.compareTo(search.obj) == 0) {
                System.out.println("Tree Contains: " + obj);
                return true;
            }
            else if (obj.compareTo(search.obj) < 0) {
                search = search.left;
            }
            else {
                search = search.right;
            }

        }
        System.out.println(obj + " not found");
        return false;
    }

    /**
     * This method calls its helper method, and check for element and then removes if its in the tree
     * @param obj the item we are looking to remove
     * @return boolean true if removed element from tree, and false otherwise
     */
    public boolean remove (E obj) {
        int oldSize = size;
        root = removeHelper(root, obj);
        System.out.println(size < oldSize);
        return size < oldSize;
    }

    /**
     * This is the helper method to remove, and goes recursively checking if element exists if so removes it
     * @param node the start of the node that we are going to start checking if it's what we want to remove
     * @param obj the generic we want to remove from the tree
     * @return the node that needs to be remove
     */
    private Node removeHelper(Node node, E obj) {
        if (node == null) {
            return null;
        }

        int cmp = obj.compareTo(node.obj);

        if (cmp < 0) {
            node.left = removeHelper(node.left, obj);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, obj);
        } else {
            if (node.left != null && node.right != null) {
                Node ctd = node.right;
                while (ctd.left != null) {
                    ctd = ctd.left;
                }
                node.obj = ctd.obj;
                node.right = removeHelper(node.right, ctd.obj);
            }
            else if (node.left != null) {
                size--;
                return node.left;
            }
            else if (node.right != null) {
                size--;
                return node.right;
            }
            else {
                size--;
                return null;
            }
        }
        return node;
    }

    /**
     * This method runs a Pre Order Traversal, and calls its helper
     */
    public void preOrderTraversal(){
        preOrderHelper(root);
    }

    /**
     * This method is the helper to preOrderTraversal, visiting ourselves before each child
     * @param n the root node we are starting at to go through the tree
     */
    private void preOrderHelper(Node n){
        if(n == null){;
            return;
        }
        System.out.println(n.obj);
        preOrderHelper(n.left);
        preOrderHelper(n.right);
    }

    /**
     * This method runs a inOrderTraversal calling its helper method inOrderHelper
     */
    public void inOrderTraversal(){
        inOrderHelper(root);
    }

    /**
     * This method runs an inOrderTraversal and prints left subtree, itself, then right subtree
     * @param n the root node we are starting at to go through the tree
     */
    private void inOrderHelper(Node n){
        if(n == null){
            return;
        }
        inOrderHelper(n.left);
        System.out.println(n.obj);
        inOrderHelper(n.right);

    }

    /**
     * This method runs a postOrderTraversal and calls its helper method
     */
    public void postOrderTraversal(){
        postOrderHelper(root);
    }
    /**
     * This method runs an postOrderTraversal and prints our children before ourselves
     * @param n the root node we are starting at to go through the tree
     */
    private void postOrderHelper(Node n){
       if(n == null){
           return;
       }
       postOrderHelper(n.left);
       postOrderHelper(n.right);
       System.out.println(n.obj);
    }

    /**
     * This method runs a breadthFirstSearch using an ArrayList and calls its helper method
     */
    public void breadthFirstSearch(){
        breadthFirstSearchHelper(root);
    }

    /**
     * This method is the helper for breadthFirstSearch, runs through and prints out layer by layer from left to right
     * @param n the root node we are starting at to go through the tree
     */
    private void breadthFirstSearchHelper(Node n){
        if(n == null){
            return;
        }
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(n);

        while (!queue.isEmpty()) {
            Node current = queue.remove(0);
            System.out.println(current.obj);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }

        }
    }

    /**
     * This method runs a depthFirstSearch, and runs its helper method
     */
    public void depthFirstSearch(){
        depthFirstSearchHelper(root);
    }

    /**
     * his method is the helper for depthFirstSearch, runs like preorder traversal and prints ourselves before the children
     * @param n the root node we are starting at to go through the tree
     */
    private void depthFirstSearchHelper(Node n){
        if(n == null){
            return;
        }
        ArrayList<Node> stack = new ArrayList<>();
        stack.add(n);

        while (!stack.isEmpty()) {
            Node current = stack.remove(stack.size() - 1);
            System.out.println(current.obj);
            if (current.right != null) {
                stack.add(current.right);
            }
            if (current.left != null) {
                stack.add(current.left);
            }
        }
    }

    /**
     * The main method, place tests here!
     */
    public static void main(String[] args){
        TreeMart<Integer> tree = new TreeMart<>(); 

        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(20);

        System.out.println("Size of Tree: " + tree.getSize() + "\n");

        System.out.println("Removing Object?");
        tree.remove(7);

        System.out.println();

        System.out.println("Size of Tree: " + tree.getSize());

        System.out.println();

        System.out.println("Contains: ");
        tree.contains(78);

        System.out.println();

        System.out.println("Pre-Order Traversal:");
        tree.preOrderTraversal();

        System.out.println();

        System.out.println("In-Order Traversal:");
        tree.inOrderTraversal();

        System.out.println();

        System.out.println("Post-Order Traversal:");
        tree.postOrderTraversal();

        System.out.println();

        System.out.println("Breadth First Search:");
        tree.breadthFirstSearch();

        System.out.println();

        System.out.println("Depth First Search:");
        tree.depthFirstSearch();



    }
}


