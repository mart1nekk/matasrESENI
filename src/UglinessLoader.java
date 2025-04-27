

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Scenar je nasledujici:
 * Jste general, ktery se rozhoduje pred bojem s cizi armadou
 * Bojovnik ma silu a inteligenci
 * Mate 3 vzorky 20 nepratelskych bojovniku. Nepratelska kontrarozvedka vam
 * do souboru hodila nekolik spionu, ti jsou schovani v zavorkach, jejich atributy ignorujte.
 * Vy musite nacist potrebne udaje, abyste meli dostatek informaci,
 * zda nepratelskeho bojovnika umlatit palici nebo umlatit knihou
 */
public class UglinessLoader {

    static ArrayList<Enemy> loadInfo(String filePath){
        //seznam pro ulozeni
        ArrayList<Enemy> enemies = new ArrayList<>();
        //A zde je do seznamu ulozte
        try{
            ArrayList<String> cleanArray = new ArrayList<>();
            Scanner sc = new Scanner(new File(filePath));
            boolean telo = false;
            int counter = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                line = line.replaceAll("[^0-9() ]", "");
                //System.out.println(line);
                String finalLine="";
                String[] splitted = line.split("");

                for (int i = 0; i < splitted.length; i++) {
                    //kontrola mezer
                    if (i!=0){
                        if (splitted[i-1].equals(" ") && splitted[i].equals(" ")) continue;
                    }

                    if (splitted[i].equals("(")){
                        counter++;
                        telo = true;
                    }
                    if (!telo){
                        finalLine += splitted[i];
                    }
                    if (splitted[i].equals(")")){
                        if (counter>0) counter--;
                        if (counter==0) telo = false;
                        if ((i+1)<splitted.length){
                            if (splitted[i+1].equals(" ")) i++;
                        }
                    }
                }
                if (finalLine.isEmpty())continue;
                cleanArray.add(finalLine);
            }
            int def =-1;
            for (String s : cleanArray){
                String[] splitted = s.split(" ");
                for (int i = 0; i < splitted.length; i++) {
                    if (def==-1){
                        def=Integer.parseInt(splitted[i]);
                    }else {
                        enemies.add(
                                new Enemy(def,Integer.parseInt(splitted[i]))
                        );
                        def=-1;
                    }

                }
            }
            for (Enemy e : enemies){
                System.out.println(e);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return enemies;
    }

    static void init(){
        System.out.println("Prvni: " + loadInfo("simpleParse.txt").size());
        System.out.println("Druhy: " + loadInfo("ParseTest.txt").size());
        System.out.println("Treti: " + loadInfo("ParseMeIfYouCan.txt").size());
    }
}
class Enemy{
    int strength, intelligence;

    public Enemy(int strength, int intelligence) {
        this.strength = strength;
        this.intelligence = intelligence;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "strength=" + strength +
                ", intelligence=" + intelligence +
                '}';
    }
}

