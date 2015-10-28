/**
 * Created by Jimmy on 4/5/2015 in PACKAGE_NAME
 * 109259420
 * Homework 5
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * TreeNode that has instance variables:
 * name
 * selection
 * message
 * left
 * middle
 * right
 */
public class TreeNode {


    private String name;
    private String selection;
    private String message;
    private TreeNode left;
    private TreeNode middle;
    private TreeNode right;

    /**
     * TreeNode constructor that creates a tree node object from just a String name parameter
     * @param name is the parameter that sets the name of the TreeNode
     */
    public TreeNode(String name) {
        this.name = name;
    }

    /**
     * TreeNode constructor that creates a tree node object from name, message and selection
     * @param name is the parameter that sets the name of the TreeNode
     * @param message is the message that sets the message of the TreeNode
     * @param selection is the selection that sets the selection of the TreeNode
     */
    public TreeNode(String name, String message, String selection) {
        this.name = name;
        this.message = message;
        this.selection = selection;
    }

    /**
     * Returns left reference of tree node
     * @return left reference of tree node
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Sets left reference of tree node
     * @param left reference of tree node
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Returns middle reference of tree node
     * @return middle reference of tree node
     */
    public TreeNode getMiddle() {
        return middle;
    }

    /**
     * Sets middle reference of tree node
     * @param middle reference of tree node
     */
    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    /**
     * returns message of the tree node
     * @return message of the tree node
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns name of the tree node
     * @return name of the tree node
     */
    public String getName() {
        return name;
    }

    /**
     * Returns right reference of the tree node
     * @return right reference of the tree node
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Sets right reference of the tree node
     * @param right reference of the tree node
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Returns the selection of the tree node
     * @return the selection of the tree node
     */
    public String getSelection() {
        return selection;
    }

    /**
     * Returns if the node is a leaf
     * @return if the node is a leaf
     */
    public boolean isLeaf() {
        return left == null && right == null && middle == null;
    }

    /**
     * Finds the node in the tree from a starting node using recursion
     * @param name is the name of the node to be found
     * @param node is the starting node
     * @return the node that is found
     */
    public TreeNode findANode(String name, TreeNode node) {
        TreeNode answer = null;
        if(node.getName().equals(name))
            return node;
        else {
            if(node.left != null)
                answer = findANode(name, node.left);
            if(node.middle !=null && answer == null)
                answer = findANode(name, node.middle);
            if (node.right !=null && answer == null)
                answer = findANode(name, node.right);

        }
        return answer;
    }

    /**
     * Returns number of children this node has
     * @return number of children this node has
     */
    public int getNumberOfChildren() {
        if (left == null)
            return 0;
        if (middle == null)
            return 1;
        if (right == null)
            return 2;
        else
            return 3;
    }
}
