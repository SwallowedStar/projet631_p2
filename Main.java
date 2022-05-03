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



    }


    public static String get_content_file(String filename) throws Exception{
        /*File doc =new File(filename);
        Scanner obj = new Scanner(doc);
        String str="";
        while (obj.hasNextLine()){
            if(obj.hasNextLine()){
                str+=obj.nextLine();
            }

        }*/
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        /*FileReader fr =
                new FileReader(filename);

        int i;
        String str="";
        while ((i=fr.read()) != -1){
            str+=((char) i);
        }*/


        return content;
    }

    public static Map<Character, Integer> create_list_cara(String content){

        Map<Character, Integer> frequency = new HashMap<>();
        for (char c: content.toCharArray()) {

                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }

        return frequency;
    }

    /*public static List<String> create_list_freq(String content){
        List<String> l_freq= new ArrayList<>();
        List<String> l_cara=new ArrayList<>();
        for (int i=0;i<content.length();i++){
            char une_l=content.charAt(i);
            if(!l_cara.contains(String.valueOf(une_l))){
                //System.out.println(String.valueOf(une_l));
                long freq=content.chars().filter(ch->ch==une_l).count();
                String freq2=String.valueOf(freq);
                l_cara.add(String.valueOf(une_l));
                l_freq.add(freq2);
            }

        }

        return l_freq;
    }*/

}
