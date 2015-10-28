import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Jimmy on 4/5/2015 in PACKAGE_NAME
 * 109259420
 * Homework 5
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

public class Kiosk {

    /**
     * Main method that runs the program
     * Catches all exceptions and restart the loop
     * @param args command line
     * @throws Exception which is super class of all exceptions that can be thrown
     */
    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();
        do {
            try {
                run(tree);
                System.out.println();
            } catch (FileNotFoundException ex) {
                System.out.println("This file doesn't exist\n");
            } catch (InputMismatchException ex) {
                System.out.println("Incorrect format\n");
            } catch (NullPointerException ex) {
                System.out.println("The menu hasn't been loaded yet!\n");
            } catch (IllegalAccessError ex) {
                System.out.println("Exiting session...");
            } catch (IllegalAccessException ex) {
                System.out.println("Invalid Choice. Restarting Menu");
            } catch (Exception ex) {
                System.out.println("Invalid input\n");
            }
        } while (true);
    }

    /**
     * Reads a text file into the tree
     * @param tree is the tree to be added
     * @param file is String representation of the file to be added
     * @throws FileNotFoundException when the file cannot be found/does not exist
     * @throws InputMismatchException when the format of the file is incorrect.
     */
    public static void addIntoTree(Tree tree, String file) throws FileNotFoundException, InputMismatchException {
        Scanner reader = new Scanner(new File(file));

        String rootName = reader.nextLine();
        String rootSelection = reader.nextLine();
        String rootMessage = reader.nextLine();
        tree.addNode(rootName, rootSelection, rootMessage, "");
        while (reader.hasNext()){
            String parentNode = reader.next();
            int children = reader.nextInt();
            if(children > 10)
                throw new IllegalArgumentException("Over 10 references");
            reader.nextLine();
            for (int i = 0; i < children; i++) {
                String nodeName = reader.nextLine();
                String nodeSelection = reader.nextLine();
                String nodeMessage = reader.nextLine();
                tree.addNode(nodeName, nodeSelection, nodeMessage, parentNode);
            }
        }
    }

    /**
     * Run the menu program which has direct access to the rest of the methods in tree
     * @param tree is the tree which the menu is run on
     * @throws Exception for general exceptions
     */
    public static void run(Tree tree) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("L) Load a Tree\n" +
                "P) Print menu\n" +
                "S) Start service\n" +
                "Q) Quit");
        System.out.print("Choice:");
        String s = input.next();
        char c = Character.toUpperCase(s.charAt(0));
        switch(c) {
            case 'L': System.out.print("Enter the name of the file:");
                    input.nextLine();
                    String line = input.nextLine();
                    addIntoTree(tree, line);
                    System.out.println("Menu has been updated!"); break;
            case 'P':
                if (!tree.getRoot().isLeaf())
                    tree.printMenu();
                else
                    throw new NullPointerException("The root is null");
                System.out.println(); break;
            case 'S':
                if (!tree.getRoot().isLeaf())
                    tree.beginSession();
                else
                    throw new NullPointerException("The root is null");
                System.out.println(); break;
            case 'Q': System.out.println("Kiosk shutting down...");
                System.exit(1);
            default:
                System.out.println("Invalid input");
        }
    }
}
