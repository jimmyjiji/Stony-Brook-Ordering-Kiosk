import java.util.Scanner;

/**
 * Created by Jimmy on 4/5/2015 in PACKAGE_NAME
 * 109259420
 * Homework 5
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */
public class Tree {
    private TreeNode root = new TreeNode("root");

    /**
     * Returns root of the tree node
     * @return root of the tree node
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Default constructor for a tree
     */
    public Tree() {
    }

    /**
     * Finds the tree node through the parameter String name
     * @param name is the node to be found
     * @return the tree node. null if not found
     */
    public TreeNode findNode(String name) {
        return root.findANode(name, root);
    }

    /**
     * Add a node to the tree
     * @param name is the name of the node to be added
     * @param selection is the selection of the node to be added
     * @param message is the message of the node to be added
     * @param parentName is the parent name of the node to be added
     * @return true if the node has been added, false if not.
     */
    public boolean addNode(String name, String selection, String message, String parentName) {
        TreeNode cursor = findNode(parentName);
        if(cursor!= null && !name.equals("root")) {
            if (cursor.getLeft() == null) {
                findNode(parentName).setLeft(new TreeNode(name, message, selection));
                return true;
            } else if (cursor.getMiddle() == null) {
                findNode(parentName).setMiddle(new TreeNode(name, message, selection));
                return true;
            } else if (cursor.getRight() == null) {
                findNode(parentName).setRight(new TreeNode(name, message, selection));
                return true;
            }
        } else if (name.equals("root")) {
            root = new TreeNode(name, message, selection);
            return true;
        }
        return false;
    }

    /**
     * Prints the menu header
     * Then recursively calls another printMenu method to recursively print all leafs
     */
    public void printMenu() {
        System.out.println("Menu:");
        System.out.printf("%-10s%-70s%-10s","Dining","Selection","Price");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------");
        printMenu("", root, 0);
    }

    /**
     * Recursive printMenu method that prints all leafs while storing parentInfo
     * @param parentInfo is the information of all parent nodes
     * @param node is the current node
     * @param length of the parent info so that it can delete info when it pops off the stack
     */
    public void printMenu(String parentInfo, TreeNode node, int length) {
        if (node.getLeft() != null) {
            printMenu(parentInfo += " " + node.getLeft().getSelection(), node.getLeft(), parentInfo.length());
            parentInfo = parentInfo.substring(0, length);
        }
        if (node.getMiddle() != null) {
            printMenu(parentInfo += " " + node.getMiddle().getSelection(), node.getMiddle(), parentInfo.length());
            parentInfo = parentInfo.substring(0, length);
        }
        if (node.getRight() != null) {
            printMenu(parentInfo += " " + node.getRight().getSelection(), node.getMiddle(), parentInfo.length());
            parentInfo = parentInfo.substring(0, length);
        }

        if (node.isLeaf()) {
            String s = String.format("%-80s%-10s", parentInfo, node.getMessage());
            System.out.println(s);
        }

    }

    /**
     * Begins the help session which traverses the tree based on user input
     * Prints the final order when the traversal reaches a leaf
     * @throws IllegalAccessException when there is an invalid input
     * @throws IllegalAccessError to shut down the session
     */
    public void beginSession() throws IllegalAccessException {
        System.out.println("Help session starting...\n");
        TreeNode cursor = root;
        String order = "The order at ";
        while (!cursor.isLeaf()) {
            System.out.println(cursor.getMessage());
            for (int i = 0; i < cursor.getNumberOfChildren(); i++) {
                if (i == 0)
                    System.out.println("1: " + cursor.getLeft().getSelection());
                if (i == 1)
                    System.out.println("2: " + cursor.getMiddle().getSelection());
                if (i == 2)
                    System.out.println("3: " + cursor.getRight().getSelection());
            }

            System.out.println("0: Exit session\n");
            Scanner input = new Scanner(System.in);
            System.out.print("Choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    cursor = cursor.getLeft();
                    order += cursor.getSelection() + ", ";
                    break;
                case 2:
                    cursor = cursor.getMiddle();
                    order += cursor.getSelection() + ", ";
                    break;
                case 3:
                    cursor = cursor.getRight();
                    order += cursor.getSelection() + ", ";
                    break;
                case 0:
                    throw new IllegalAccessError("Exiting Session...");
                default:
                    throw new IllegalAccessException("No choice");
            }
        }
        System.out.print(order + "has been sent to the kitchen. The total is ");
        System.out.println(cursor.getMessage());
    }

}
