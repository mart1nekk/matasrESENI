import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sada ukolu v metode init()
 * Pro splneni ukolu je mozne vytvaret vlastni tridy, vlastni kolekce ci jen doplnit metody
 */
public class SongMerger {
        static List<File> sortFiles(List<File> files) {
            files.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return (int)(o1.length()- o2.length());
                }
            });
            return files;
        }
    static void init(){
        // TODO: nactete soubry slozky taskStuff, nacist pouze soubory < 500 kB
        String dirToRead = "taskStuff";
        System.out.println("Neserazene soubory:");
        List<File> smallFiles = new ArrayList<>();

        try{
            File f = new File(dirToRead);
            File[] files= f.listFiles();
            for (File file : files){
                if (file.isFile() && (file.length()/1024<500)){
                    smallFiles.add(file);
                }
            }
            System.out.println(smallFiles);
        }catch (NullPointerException  e){

        }

//        System.out.println(files.toString()); //lze pojmenovat jinak nez files

        System.out.println("Serazene soubory");

        // TODO: Metoda pro serazeni dle velikosti souboru
       List<File> sorted= sortFiles(smallFiles);
        System.out.println(sorted);
        //pozn: lze pouzit kolekci jako List
        // lze ale vyuzit i obycejne pole a radit pomoci Arrays.sort(pole, comparator)
//        System.out.println(files.toString()); //kontrola, dle velikosti sol<om<on


        // TODO: Vytvoreni souboru s nazvem a obsahem spojenym z *textovych* souboru
        String name = "";
        for (int i = 0; i < sorted.size(); i++) {
            if (smallFiles.get(i).getName().endsWith(".txt")){
                name += smallFiles.get(i).getName().replace(".txt", "");
            }
        }
        try {
            System.out.println(name);
            File s = new File(name);
            if (s.exists()){
                s.delete();
            }
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(name,true)));
            String line;
            for (int i = 0; i < sorted.size(); i++) {
                BufferedReader bw = new BufferedReader(new FileReader(smallFiles.get(i).toPath().toString()));
                if (smallFiles.get(i).getName().endsWith(".txt")){
                    while ((line= bw.readLine())!=null){
                        pw.println(line);
                    }

                }
                bw.close();
            }
            pw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
