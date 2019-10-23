public class BST<T extends Comparable<T>> {
    public class TreeNode {
        T value;
        TreeNode left;
        TreeNode right;

        TreeNode(T item){
            value = item;
            left = null;
            right = null;

        }
}

    private  TreeNode root;


    public BST(){
        root = null;
    }

    public void delete(T item){
        if (root == null)
            return ;
        else if (root.value != item) {
            delete(item, root.left, root);
            delete(item, root.right, root);
            return ;
        }

        TreeNode newRoot = new TreeNode(item);
        newRoot.right = root;
        delete(item, root, newRoot);
        root = newRoot.right;

    }


    private void  delete(T item, TreeNode node, TreeNode parent){
        if (item.compareTo(node.value) < 0) {
            if (node.left != null)
                delete(item, node.left, node);
        } else if (item.compareTo(node.value) > 0){
            if (node.right != null)
                delete(item, node.right, node);
        } else {
            if (node.left != null && node.right != null){
                node.value = oneGreater(node);
                delete(node.value, node.right, node);
            } else if (parent.left != null && parent.left.value == item) {
                parent.left = (node.left != null) ? node.left : node.right;
            } else if (parent.right != null && parent.right.value == item){
                parent.right = (node.left != null) ? node.left : node.right;
            }
        }
    }

    public boolean find(T item){
        return find(item, root);
    }

    private boolean find(T item, TreeNode node){
        if (node == null)
            return false;
        if (node.value == item)
            return true;

        // inclusive or returns true if either is true
        return find(item, node.left) || find(item, node.right);
    }


    public void insert(T item){
        if (root == null)
            root = new TreeNode(item);
        else
            insert(item, root);
    }

    private void insert(T item, TreeNode node){
        if (item.compareTo(node.value) >= 0){
            if (node.right == null)
                node.right = new TreeNode(item);
            else
                insert(item, node.right);
        }

        else {
            if (node.left == null)
                node.left = new TreeNode(item);
            else
                insert(item, node.left);
        }
    }


    private T oneGreater(TreeNode n){
        TreeNode tmp = n.right;

        while(true) {
            if (tmp.left == null)
                return tmp.value;
            tmp = tmp.left;
        }
    }

    public void print(){
        print(root);
    }

    public void print(TreeNode node){
        if (node == null)
            return ;
        print(node.left);
        System.out.println(node.value);
        print(node.right);
    }
}
