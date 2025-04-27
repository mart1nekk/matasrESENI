

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Odkomentovat vse v metode main().
 * Pravidla jednoducha:
 * 1. Do metody main() nesmi byt vlozen zadny try-catch blok
 * 2. zadna metoda nesmi mit v halvicce throws,
 * 3. kod nesmite mazat/modifikovat, pouze pridavat try-catch bloky (vyjma readMeIfYouCan)
 *  AD 3: Ne, neni povolena catch(Exception ...), don't you even try it
 *  AD 3#2: Ne, ani v adresari si nemuzete vytvaret nove soubory
 */
public class Exceptional {

    static int saharaSandCounter(int countdown){
        if (countdown  < Integer.MAX_VALUE - 42)
            return 42; //Counted everything, threfore...
        System.out.println("Speck of sand #" + countdown);
        return saharaSandCounter(countdown + 1);
    }
    static void subSaharaCounter(){
        System.out.println("Amount of sand in sahara counted as specks from 0:");
        try {
            System.out.println(saharaSandCounter(0));
        }catch (StackOverflowError e){}

    }

    static void readMeIfYouCan(String path)  {//odstranit z hlavicky
        System.out.println("Reading file " + path + "...");
        try {
            List<String> rolls = Files.readAllLines(Paths.get(path));
            for (String line : rolls)
                System.out.println(line);
        }catch (IOException e){

        }


    }

    static void twoAtOnceWinkWink(String name, int index){
        String[] names = {"Karel", "Karel1", "Karel2", "Karel3", "Karel4"};
        System.out.println("Controlling whether or not our very original name database contains you... ");
        try {
            if (name.matches(names[index])){
                System.out.println("Found ya!");
            }
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){}

    }

    static void mathGenius(String number){
        System.out.println("Hopefully you gave a good number or this is going tits up");
        try{
            int mathematikMachtFrei = 42 / Integer.parseInt(number);
            System.out.println(mathematikMachtFrei);
        }catch (ArithmeticException | NumberFormatException e){
            System.out.println("Arithmetic exception");
        }
        System.out.println("Yep...it did");
    }

    static void mathGenius(int number){
        System.out.println("Hopefully you gave a good number or this is going tits up");
        try {
            int mathematikMachtFrei = 42 / number;
            System.out.println(mathematikMachtFrei);
        }catch (ArithmeticException e){}

        System.out.println("Yep...it did");
    }

    static void wellWhyNot(){
        System.out.println("Parry this");
        class MyOwn extends ArithmeticException{};
        try {
            throw new MyOwn();
        }catch (MyOwn e){};

    }

    static void init(){
        mathGenius("0");
        mathGenius("cTyRiceTDva, to urcite vyjde Jedna");
        mathGenius(4-8/2);

        System.out.println("Counting down...");
        subSaharaCounter();

        //nezapomenout na hlavicku metody
        readMeIfYouCan("neverGonnaGiveYouUp.RickRoll");

        wellWhyNot();

        twoAtOnceWinkWink(null, 2);
        twoAtOnceWinkWink("Karel3", 42);
//


        System.out.println("Nothing works, everything is fucked, therefore...");
        System.out.println("Happy ending!");
    }
}
