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



    public Node fuse(Node other){
        Node parent = new Node(null, this.getFreq() + other.getFreq());
        parent.setRightChild(other);
        parent.setLeftChild(this);
        return parent;

    }

    public static Node create_abr(List<Node> l_node){

        while (l_node.size()>1){
            Boolean is_insert=false;
            Node n1=l_node.get(0);

            Node n2=l_node.get(1);
            Node n3=n1.fuse(n2);
            l_node.remove(n1);
            l_node.remove(n2);
            for(int i=0;i<l_node.size();i++){
                if(l_node.get(i).getFreq()==n3.getFreq()){
                    l_node.add(i,n3);
                    is_insert=true;
                    break;

                }

            }

            if(!is_insert){
                l_node.add(n3);
            }
            //Collections.sort(l_node);

            //System.out.println(l_node);
        }

        return l_node.get(0);
    }


    public Map<String,String> parcour_abr(String res,Map m){
        String res2;
        if(this.right_child!=null) {
            res2=res+"1";
            this.right_child.parcour_abr(res2,m);
        }
        if(this.left_child!=null){
            res2=res+"0";
            this.left_child.parcour_abr(res2,m);
        }

        if(this.right_child==null && this.left_child==null){
            m.put(this.label,res);
        }

        return m;
    }









}
