package tree;

/**
 * @author tanwenhai@outlook.com
 * @since 2022/4/27
 */
public class Node implements Comparable<Node> {

    Integer data;

    Node left;

    Node right;

    // 向上查找
    Node parent;

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
