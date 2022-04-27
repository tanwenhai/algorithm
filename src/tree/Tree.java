package tree;

/**
 * 二叉树的查找、删除、插入操作，前、中、后序遍历
 * @author tanwenhai@outlook.com
 * @since 2022/4/27
 */
public class Tree {
    private Node root;

    public void insert(Integer data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        Node p = root;
        Node pp = root;
        while (p != null) {
            pp = p;
            if (node.compareTo(p) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        node.parent = pp;
        if (node.compareTo(pp) > 0) {
            pp.left = node;
        } else {
            pp.right = node;
        }
    }

    public Node find(Integer data) {
        Node p = root;
        Node searchNode = new Node(data);
        while (p != null) {
            int c = searchNode.compareTo(p);
            if (c == 0) {
                break;
            } else if (c > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        return p;
    }

    public boolean delete(Integer data) {
        Node node = find(data);
        if (node == null) {
            return false;
        }
        Node parent = node.parent;
        // 找到左树的最小节点升级为子树的根节点，也就是替换当前node
        if (node.left != null && node.right != null) {
            Node minP = node.left;
            while (minP.right != null) {
                minP = minP.right;
            }
            // 复制data，实际删除的是minP
            node.data = minP.data;
            minP.parent.right = null;
            parent = minP.parent;
            minP.parent = null;
        }
        Node child;
        if (node.left != null) {
            child = node.left;
            child.parent = parent;
        } else if (node.right != null) {
            child = node.right;
            child.parent = parent;
        } else {
            // 叶子节点
            child = null;
        }
        if (parent == null) {
            root = child;
        } else if (parent.left == node) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        return true;
    }

    public static class Node implements Comparable<Node> {

        private Integer data;

        private Node left;

        private Node right;

        // 向上查找
        private Node parent;

        public Node(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.data.compareTo(o.data);
        }

        @Override
        public String toString() {
            return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
        }
    }

    @Override
    public String toString() {
        return String.valueOf(root);
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        for (int i : new int[] {5, 4, 6, 8, 1, -1, 100, 88, 66, 99, 98}) {
            tree.insert(i);
        }
        System.out.println(tree.find(8));
        System.out.println(tree.delete(99));
        System.out.println(tree.find(99));
        System.out.println(tree);
    }
}
