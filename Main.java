import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter the name of the file : ");
        String str_user = reader.next(); // Scans the next token of the input as an int.
        reader.close();
        String filename="data/"+str_user+"_freq.txt";
        String[] words_split = filename.split("/");
        String [] words2_split=words_split[1].split("_");
        String file=words2_split[0];

        String content=get_content_file(filename);

        Map<String,Integer>l_res=create_list_cara(content);

        List<Node>l_node=create_list_node(l_res);

        Node n=Node.create_abr(l_node);

        String str=str_bin("data/"+file+"_comp.bin",file);

        Map<String,String> m_res=new HashMap();

        Map<String,String> map=n.create_map("",m_res);


        String res4=n.parcour_abr_2(str);

        write_file(res4,file);
        get_moyenne(map);

        get_compression(file);


    }

    /**
     *
     * @param filename the name of the file
     * @return content of the ****_freq file
     * @throws Exception
     */
    public static String get_content_file(String filename) throws Exception{

        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }

    /**
     *
     * @param content The content of the ****_freq.txt file
     * @return a map of character and their frequency
     */
    public static Map<String, Integer> create_list_cara(String content){
        String lineSeparator = "\r\n";

        String[] lines = content.split(lineSeparator);
        Map<String, Integer> frequency = new HashMap<>();

        for(int i = 1; i < lines.length; i++){

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
     * @param map A map of the character and their frequency
     * @return a sorted list of Node
     */
    public static List<Node> create_list_node(Map map){
        List<Node> l_node=new ArrayList<>();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry)it.next();

            Node n1=new Node(entry.getKey(),entry.getValue());
            l_node.add(n1);
        }
        Collections.sort(l_node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });

        Collections.sort(l_node);
        return l_node;
    }

    /**
     *
     * @param file_bin name of the compressed file
     * @return A string wich contains the content of the compressed file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String str_bin(String file_bin,String file_name)throws FileNotFoundException,IOException{

        FileInputStream file=new FileInputStream(new File(file_bin));
        BufferedInputStream buffer=new BufferedInputStream(file);

        int n;
        int pass=0;
        String str_res="";

        while((n=buffer.read())!=-1){
                    if(pass==0){
                        pass++;
                        str_res+=Integer.toBinaryString(n);
                    }else{
                        if(Integer.toBinaryString(n).length()!=8){
                            String str_temp="";
                            String res="";
                            for(int i =0;i<(8-Integer.toBinaryString(n).length());i++){
                                str_temp+="0";
                            }
                            res=Integer.toBinaryString(n);
                            str_res+=(str_temp+res);
                        }else{

                            str_res+=Integer.toBinaryString(n);
                        }
                    }
                }
        buffer.close();
        return str_res;
    }

    /**
     * This function show the byte average of the characters
     * @param map A map with character and their encoding
     */
    public static void get_moyenne(Map map){
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


        res=moyenne/(diviseur* compteur);
        System.out.println("Moyenne d'octet : "+String.format("%.4f", res)+" o");



    }

    /**
     * This function write the decoded content in a text file
     * @param str A string that contain the decoded content
     * @param file name of the file
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

    /**
     *
     * @param file the name of the file
     * @return  The difference between before and after decompression
     */
    public static double get_compression(String file){
        File f =new File("data/"+file+"_comp.bin");
        double lenght_before=f.length();

        File f2=new File("res/decompr_"+file+".txt");
        double lengh_after=f2.length();

        double res=-100*(1-(lengh_after/lenght_before));
        System.out.println("Pourcentage pour la compression : "+res+" %");
        return lenght_before;
    }







}