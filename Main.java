import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        String content=get_content_file("data/extraitalice_freq.txt");

        Map<String,Integer>l_res=create_list_cara(content);
        //System.out.println(l_res);
        List<Node>l_node=create_list_node(l_res);


    }

    public static String get_content_file(String filename) throws Exception{
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }

    public static Map<String, Integer> create_list_cara(String content){
        String lineSeparator = "\r\n";

        String[] lines = content.split(lineSeparator);
        Map<String, Integer> frequency = new HashMap<>();
        System.out.println(lines.length);
        for(int i = 1; i < lines.length; i++){
            System.out.println(lines[i]);
            String[] elements = lines[i].split(" ");

            if(elements.length == 3){ // If it is a space
                frequency.put(" ", Integer.valueOf(elements[2].strip()));
            }
            else if(elements.length == 2){
                frequency.put(elements[0], Integer.valueOf(elements[1].strip()));
            }
        }
        return frequency;
    }

    public static List<Node> create_list_node(Map map){
        List<Node> l_node=new ArrayList<>();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry)it.next();

            Node n1=new Node(entry.getKey(),entry.getValue());
            l_node.add(n1);
            //System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println(l_node);
        Collections.sort(l_node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });

        Collections.sort(l_node);



        System.out.println(l_node);
        return l_node;
    }


}