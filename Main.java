import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Node n1=new Node("a","5");
        //System.out.println(n1);
        String content=get_content_file("data/extraitalice.txt");
        Map<Character,Integer>l_res=create_list_cara(content);
        System.out.println(l_res);
        /*List<String> l_res2=create_list_freq(content);
        System.out.println(l_res2);*/
        create_list_trie(l_res);



    }

    public static String get_content_file(String filename) throws Exception{
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }

    public static Map<Character, Integer> create_list_cara(String content){

        Map<Character, Integer> frequency = new HashMap<>();
        for (char c: content.toCharArray()) {

                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }

        return frequency;
    }
}
