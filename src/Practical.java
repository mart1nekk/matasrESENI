

import java.util.Scanner;

/**
 * PZOP PVS24
 * Jedina trida, ve ktere je spustitelna metoda main()
 * Main neocekava zadne argumenty
 * @author SignHere
 */
public class Practical {
    public static void main(String[] args) {
        //pomoci ktereho budu volit
        Scanner userInput;
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("for choosing WindowTask write 1, for Comparing write 2, for Exceptional write 3, for UglinessLoader write 4, for SongMerger write 5, if you want to end this program write END");
            String input = userInput.nextLine();
            if (input.equals("1")) WindowTask.init();
            else if (input.equals("2")) Comparing.init();
            else if (input.equals("3")) Exceptional.init();
            else if (input.equals("4")) UglinessLoader.init();
            else if (input.equals("5")) SongMerger.init();
            else if (input.equals("END")) break;
            else System.out.println("wrong command");
        }
        userInput.close();

        //Zvolim si moznost a zavolam, napriklad takto vypada volani ukolu Exception
        //ptam se tak dlouho, dokud uzivatel nezvoli moznost END
    }
}
