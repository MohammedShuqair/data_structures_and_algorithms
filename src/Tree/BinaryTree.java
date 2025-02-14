package Tree;

public class BinaryTree {
    TreeNode root;

    public TreeNode deleteElement(int searchValue, TreeNode root) {
        TreeNode p, q, s;
        if (root == null) {
            System.out.println(searchValue + " Not found");
            return root;
        } else if (searchValue < root.data) {
            root.left = deleteElement(searchValue, root.left);
            return root;
        } else if (searchValue > root.data) {
            root.right = deleteElement(searchValue, root.right);
            return root;
        } else // ELEMENT FOUND!!!!!
        {
            q = root;
            s = root;
            if (q.right == null)
                root = q.left; // root WITH ONE LEFT CHILD OR root IS NULL
// root WITH ONE RIGHT CHILD OR root IS NULL
            else if (q.left == null)
                root = q.right; // root WITH ONE RIGHT CHILD OR root IS NULL
            else // root HAS TWO CHILDREN
            {
                p = q.right;
// P POINTS TO THE NODE N’ AND S.LEFT WILLPOINTS TO THE RIGHT
// CHILD OF N’ IF P HAS RIGHT CHILD, NULL OTHERWISE....
                while (p.left != null) {
                    s = p;
                    p = p.left;
                }
// REPLACE N WITH N’
                root = p;

                root.left = q.left;
                s.left = p.right;
                if (q.right == p)
                    root.right = p.right;
                else
                    root.right = q.right;
            }
            return root;
        }
    }

    public void delete(int a) {
        root = deleteElement(a, root);
    }

    public TreeNode iterativeInsert(int newValue) {
        return iterativeInsert(newValue, root);

    }

    public TreeNode iterativeInsert(int newValue, TreeNode root) {
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
            parent = current;
            if (newValue > current.data) {
                current = current.right;
            } else if (newValue < current.data) {
                current = current.left;
            } else {
                // Value already exists in the tree
                return root;
            }
        }
        // Create a new node with the new value
        TreeNode newNode = new TreeNode(newValue, null, null);
        // Insert the new node as a child of the parent node
        if (parent == null) {
            // The tree was empty, so the new node becomes the root
            root = newNode;
        } else if (newValue > parent.data) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        return root;
    }

    public TreeNode recursiveInsert(int newValue, TreeNode root) {
        TreeNode temp;
        if (root == null) {
            temp = new TreeNode();
            temp.data = newValue;
            temp.left = null;
            temp.right = null;
            return temp;
        } else if (newValue < root.data) {
            root.left = recursiveInsert(newValue, root.left);
            return root;
        } else if (newValue > root.data) {
            root.right = recursiveInsert(newValue, root.right);
            return root;
        } else {
            System.out.println(newValue + " already presents ");
            return null;
        }
    }

    public void recursiveInsert(int a) {
        root = recursiveInsert(a, root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            visit(node.data);
            inOrder(node.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(TreeNode node) {
        if (node != null) {
            visit(node.data);
            preOrder(node.left);
            preOrder(node.right);

        }
    }

    public void postOrder() {
        postOrder(root);
    }

    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            visit(node.data);
        }
    }

    public void visit(int data) {
        System.out.println(data);
    }

}
