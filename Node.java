import java.util.*;


public class Node implements Comparable<Node> {
    private String label;
    private Node left_child;
    private Node right_child;
    private int freq;
    private String direction;


    public Node(String label, int freq) {
        this.label = label;
        this.left_child = left_child;
        this.right_child = right_child;
        this.freq = freq;
        this.direction = direction;
    }

    public int getFreq()
    {
        return this.freq;
    }

    public void setRightChild(Node right_child){
        this.right_child = right_child;
    }

    public void setLeftChild(Node left_child){
        this.left_child = left_child;
    }


    public int compareTo(Node other) {
        return this.freq - other.getFreq();
    }

    public String getLabel() {
        return label;
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
