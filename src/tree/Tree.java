package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 二叉树的查找、删除、插入操作，前、中、后序遍历
 * @author tanwenhai@outlook.com
 * @since 2022/4/27
 */
public class Tree implements Iterable<Node> {
    private Node root;

    private VisitType visitType = VisitType.FIRST;

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

    public void setVisitType(VisitType visitType) {
        this.visitType = visitType;
    }

    @Override
    public Iterator<Node> iterator() {
        return new It(visitType);
    }

    private class It implements Iterator<Node> {
        private final VisitType visitType;

        private Node p = root;
        private List<Node> list = new ArrayList<>();
        private Iterator<Node> it;

        public It(VisitType visitType) {
            this.visitType = visitType;
            if (visitType == VisitType.FIRST) {
                f(root);
            }
            if (visitType == VisitType.MID) {
                m(p);
            }
            if (visitType == VisitType.LAST) {
                l(p);
            }

            it = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Node next() {
            return it.next();
        }

        private void f(Node node) {
            if (node == null) {
                return;
            }
            list.add(node);
            f(node.left);
            f(node.right);
        }

        private void m(Node node) {
            if (node == null) {
                return;
            }
            m(node.left);
            list.add(node);
            m(node.right);
        }

        private void l(Node node) {
            if (node == null) {
                return;
            }
            l(node.left);
            l(node.right);
            list.add(node);
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
        tree.setVisitType(VisitType.MID);
        tree.iterator().forEachRemaining(n -> System.out.print(n.data + " "));
    }
}
