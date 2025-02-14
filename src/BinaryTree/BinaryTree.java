package BinaryTree;


public class BinaryTree {
    TreeNode root;

    public static void main(String[] args) throws Exception {

        BinaryTree tree = new BinaryTree();
        tree.insert(18);
//        tree.insert(3);
//        tree.insert(8);
//        tree.insert(4);
//        tree.insert(15);
//        tree.insert(9);
//        tree.insert(17);
//        tree.insert(30);
//        tree.insert(25);
//        tree.insert(20);
//        tree.insert(27);
//        tree.insert(35);

//        tree.preOrder();
//        System.out.println("********");
        tree.gbtDelete(18);
        tree.preOrder();
    }

    public void setRoot(TreeNode node) {
        root = node;
    }

    public TreeNode getInsertNode(int data) {
        TreeNode node = root;
        TreeNode insertionNode = null;

        while (node != null) {
            insertionNode = node;
            if (data < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return insertionNode;
    }

    public void insert(int data) throws TreeException {
        TreeNode newNode = new TreeNode(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode insertionNode = getInsertNode(data);
            if (data < insertionNode.data) {
                insertionNode.left = newNode;
            } else if (data > insertionNode.data) {
                insertionNode.right = newNode;
            } else {
                throw new TreeException("key must be unique");
            }
        }

    }

    public TreeNode recursiveInsert(int value, TreeNode insertionNode) {
        if (insertionNode == null) {
            return new TreeNode(value, null, null);
        } else if (value < insertionNode.data) {
            insertionNode.left = recursiveInsert(value, insertionNode.left);
            return insertionNode;
        } else if (value > insertionNode.data) {
            insertionNode.right = recursiveInsert(value, insertionNode.right);
            return insertionNode;
        } else {
            System.out.println(value + " already present");
            return null;
        }
    }

    public void gbtDelete(int value) throws TreeException {
        if (root == null) return;

        Tween<TreeNode> dTween = getNodeAndParent(value);
        TreeNode deletionNode = dTween.first;
        TreeNode deletionNodeParent = dTween.second;

        if (deletionNode == null) {
            throw new TreeException("Value is not in tree");
        }

        if (deletionNode.left != null && deletionNode.right != null) {
            Tween<TreeNode> goalNodeTween = findGoalNode(deletionNode);
            TreeNode goalNode = goalNodeTween.first;
            TreeNode goalParent = goalNodeTween.second;

            if (deletionNodeParent == null) {
                root = goalNode;
            } else if (value < deletionNodeParent.data) {
                deletionNodeParent.left = goalNode;
            } else {
                deletionNodeParent.right = goalNode;
            }

            if (goalNode.right != null) {
                if (goalNode.data < goalParent.data) {
                    goalParent.left = goalNode.right;
                } else {
                    goalParent.right = goalNode.right;
                }
            } else {
                if (goalNode.data < goalParent.data) {
                    goalParent.left = null;
                } else {
                    goalParent.right = null;
                }
            }

            goalNode.left = deletionNode.left;
            goalNode.right = deletionNode.right;
        } else {
            TreeNode childNode = (deletionNode.left != null) ? deletionNode.left : deletionNode.right;
            if (deletionNodeParent == null) {
                root = childNode;
            } else if (value < deletionNodeParent.data) {
                deletionNodeParent.left = childNode;
            } else {
                deletionNodeParent.right = childNode;
            }
        }
    }

    private Tween<TreeNode> findGoalNode(TreeNode node) {
        TreeNode goalNode = node.right;
        TreeNode goalParent = node;
        while (goalNode.left != null) {
            goalParent = goalNode;
            goalNode = goalNode.left;
        }
        return new Tween<>(goalNode, goalParent);
    }

    private Tween<TreeNode> getNodeAndParent(int value) {
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null && node.data != value) {
            parent = node;
            if (value < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return new Tween<>(node, parent);
    }


    public void delete(int value) throws TreeException {
        if (root == null) return;
        Tween<TreeNode> dTween = getNodeAndParent(value);
        TreeNode deletionNode = dTween.first;
        TreeNode deletionNodeParent = dTween.second;

        if (deletionNode == null) {
            throw new TreeException("value is not in tree");
        }
        if (deletionNodeParent == null) {
            if (deletionNode.left == null && deletionNode.right == null) {
                root = null;
            } else if (deletionNode.left != null && deletionNode.right != null) {
                Tween<TreeNode> tween = findGoalNode(deletionNode);
                TreeNode goalNode = tween.first;
                TreeNode goalParent = tween.second;
                root = goalNode;
                if (goalNode.right != null) {
                    if (goalNode.data < goalParent.data) {
                        goalParent.left = goalNode.right;
                    } else {
                        goalParent.right = goalNode.right;
                    }
                } else {
                    if (goalNode.data < goalParent.data) {
                        goalParent.left = null;
                    } else {
                        goalParent.right = null;
                    }
                }

                goalNode.left = deletionNode.left;
                goalNode.right = deletionNode.right;
            } else {
                if (deletionNode.left != null) {
                    root = deletionNode.left;
                } else {
                    root = deletionNode.right;
                }
            }
        } else if (value < deletionNodeParent.data) {
            if (deletionNode.left == null && deletionNode.right == null) {
                deletionNodeParent.left = null;
            } else if (deletionNode.left != null && deletionNode.right != null) {
                Tween<TreeNode> tween = findGoalNode(deletionNode);
                TreeNode goalNode = tween.first;
                TreeNode goalParent = tween.second;
                deletionNodeParent.left = goalNode;
                if (goalNode.right != null) {
                    if (goalNode.data < goalParent.data) {
                        goalParent.left = goalNode.right;
                    } else {
                        goalParent.right = goalNode.right;
                    }
                } else {
                    if (goalNode.data < goalParent.data) {
                        goalParent.left = null;
                    } else {
                        goalParent.right = null;
                    }
                }

                goalNode.left = deletionNode.left;
                goalNode.right = deletionNode.right;
            } else {
                if (deletionNode.left != null) {
                    deletionNodeParent.left = deletionNode.left;
                } else {
                    deletionNodeParent.left = deletionNode.right;
                }
            }

        } else {
            if (deletionNode.left == null && deletionNode.right == null) {
                deletionNodeParent.right = null;
            } else if (deletionNode.left != null && deletionNode.right != null) {
                Tween<TreeNode> tween = findGoalNode(deletionNode);
                TreeNode replacementNode = tween.first;
                TreeNode replacementNodeParent = tween.second;
                deletionNodeParent.right = replacementNode;
                if (replacementNode.right != null) {
                    if (replacementNode.data < replacementNodeParent.data) {
                        replacementNodeParent.left = replacementNode.right;
                    } else {
                        replacementNodeParent.right = replacementNode.right;
                    }
                } else {
                    if (replacementNode.data < replacementNodeParent.data) {
                        replacementNodeParent.left = null;
                    } else {
                        replacementNodeParent.right = null;
                    }
                }
                replacementNode.left = deletionNode.left;
                replacementNode.right = deletionNode.right;
            } else {
                if (deletionNode.left != null) {
                    deletionNodeParent.right = deletionNode.left;
                } else {
                    deletionNodeParent.right = deletionNode.right;
                }
            }

        }
    }

    void inOrder() {
        inOrder(root);
    }

    void preOrder() {
        preOrder(root);
    }

    void postOrder() {
        postOrder(root);
    }

    void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data);
            inOrder(node.right);

        }
    }

    void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }
}
