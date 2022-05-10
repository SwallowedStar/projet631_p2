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
        String filename="data/extraitalice_freq.txt";
        String[] words_split = filename.split("/");
        String [] words2_split=words_split[1].split("_");
        String file=words2_split[0];

        String content=get_content_file(filename);

        Map<String,Integer>l_res=create_list_cara(content);

        List<Node>l_node=create_list_node(l_res);
        Node n=Node.create_abr(l_node);

        String str=str_bin("data/"+file+"_comp.bin");
        Map<String,String> m_res=new HashMap();

        Map<String,String> map=n.parcour_abr("",m_res);

        String res=recreate_text(map,str);
        write_file(res,file);
        get_moyenne(map);


    }

    /**
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public static String get_content_file(String filename) throws Exception{

        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }

    /**
     *
     * @param content
     * @return
     */
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

    /**
     *
     * @param map
     * @return
     */
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

    /**
     *
     * @param file_bin
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String str_bin(String file_bin)throws FileNotFoundException,IOException{

        FileInputStream file=new FileInputStream(new File(file_bin));
        BufferedInputStream buffer=new BufferedInputStream(file);

        int n;
        String str_res="";
        while((n=buffer.read())!=-1){
            //System.out.println(Integer.toBinaryString(n));
            if(Integer.toBinaryString(n).length()!=8){
                String str_temp="";
                String res="";
                for(int i =0;i<(8-Integer.toBinaryString(n).length());i++){
                    str_temp+="0";
                }
                res=str_temp+Integer.toBinaryString(n);
                //System.out.println("New version : "+res);
                str_res+=res;
            }else{

                str_res+=Integer.toBinaryString(n);
            }


        }
        buffer.close();
        return str_res;
    }

    /**
     *
     * @param m
     * @param str_bin
     * @return
     */
    public static String recreate_text(Map m, String str_bin){
        String str_res = "";
        while (str_bin.length()!=1 && !str_bin.isEmpty() && str_bin!="" ) {
            //System.out.println(str_bin);
            Iterator it = m.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = (Map.Entry)it.next();
                //System.out.println(entry.getValue());
                String value = entry.getValue();
                //System.out.println(str_bin);
                if(str_bin.startsWith(value)){
                    //System.out.println("J AI TROUVCER");
                    //System.out.println(entry.getKey());
                    String str_n="";
                    //System .out.println(entry.getKey());
                    str_n=entry.getKey().replace("\\n","\n");
                    str_res+=str_n;


                    str_bin=str_bin.substring(value.length());
                }
            }
        }

        return str_res;
    }

    /**
     *
     * @param map
     * @return
     */
    public static double get_moyenne(Map map){
        double diviseur=8;
        double moyenne=0;
        double compteur= 0;
        double res=0;
        Iterator it=map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry)it.next();
            moyenne+=Integer.valueOf(entry.getValue().length());
            compteur+=1;
        }
        System.out.println(moyenne/(diviseur*compteur));

        res=moyenne/(diviseur* compteur);
        System.out.println(String.format("%.4f", res));
        return res;


    }

    /**
     *
     * @param str
     * @param file
     */
    public static  void write_file(String str,String file){
        try {
            FileWriter myWriter = new FileWriter("res/decompr_"+file+".txt");
            myWriter.write(str);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }







}