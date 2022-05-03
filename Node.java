public class Node {
    private String label;
    private Node left_child;
    private Node right_child;
    private String freq;
    private String direction;


    public Node(String label, String freq) {
        this.label = label;
        this.left_child = left_child;
        this.right_child = right_child;
        this.freq = freq;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                ", left_child=" + left_child +
                ", right_child=" + right_child +
                ", freq='" + freq + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }

}
