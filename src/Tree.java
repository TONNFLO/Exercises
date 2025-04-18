
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){this.val = x;}    
}

public class Tree {
    TreeNode n1Node = new TreeNode(1);
    TreeNode n2Node = new TreeNode(2);
    TreeNode n3Node = new TreeNode(3);
    TreeNode n4Node = new TreeNode(4);
    TreeNode n5Node = new TreeNode(5);

    n1Node.left = n2Node;
    n1Node.right = n3Node;

}
