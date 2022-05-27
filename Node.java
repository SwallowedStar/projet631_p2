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


    /**
     *
     * @param other the Node to be compared.
     * @return the difference in frequency between the two nodes, which will be used to sort a list of nodes
     */
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


    /**
     *
     * @param other A node to merge
     * @return A new node that is the fusion of two nodes and has them as children
     */
    public Node fuse(Node other){
        Node parent = new Node(null, this.getFreq() + other.getFreq());
        parent.setRightChild(other);
        parent.setLeftChild(this);
        return parent;

    }

    /**
     *
     * @param l_node A sorted node list
     * @return A huffman tree
     */
    public static Node create_abr(List<Node> l_node){

        while (l_node.size()>1){
            Boolean is_insert=false;

            Node n1=l_node.get(0);

            Node n2=l_node.get(1);
            Node n3=n1.fuse(n2);
            l_node.remove(n1);
            l_node.remove(n2);
            //This code will insert the new node at the right place
            for(int i=0;i<l_node.size();i++){
                if(l_node.get(i).getFreq()==n3.getFreq()){
                    l_node.add(i,n3);
                    is_insert=true;
                    break;

                }

            }
            //If the new node needs to be inserted at the end of the list
            if(!is_insert){
                l_node.add(n3);
            }

        }

        return l_node.get(0);
    }


    /**
     *
     * @param res The encoding of a character
     * @param m A map ( empty ) which will be filled with its encoding for a character
     * @return A map which will be filled with its encoding for a character
     */
    public Map<String,String> create_map(String res,Map m){
        String res2;
        if(this.right_child!=null) {
            res2=res+"1";
            this.right_child.create_map(res2,m);
        }
        if(this.left_child!=null){
            res2=res+"0";
            this.left_child.create_map(res2,m);
        }

        if(this.right_child==null && this.left_child==null){
            m.put(this.label,res);
        }

        return m;
    }

    /**
     * This function go through the tree to reconstitute the original text
     * @param str_bin A string of binary
     * @return the content decode
     */
    public String parcour_abr_2(String str_bin){
        Node racine=this;
        String res="";
        int stop=0;
        str_bin=str_bin+"0";
        while (str_bin.length()>0){
            if(str_bin.equals("")){
                stop++;
            }
            if(str_bin.charAt(0)=='0'){
                if(racine.left_child !=null){
                    str_bin=str_bin.substring(1);
                    racine=racine.left_child;

                }else if(racine.left_child==null && racine.right_child==null ){

                    res+=racine.getLabel();
                    racine=this;
                }
            } else if (str_bin.charAt(0)=='1') {
                if(racine.right_child!=null){
                    str_bin=str_bin.substring(1);
                    racine=racine.right_child;

                }else if(racine.right_child==null && racine.left_child==null){
                    res+=racine.getLabel();
                    racine=this;
                }
            }

            if(stop==1){
                break;
            }
        }

        return res.replace("\\n","\n");
    }










}
