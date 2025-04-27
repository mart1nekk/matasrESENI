
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Goodie oldie, v metode init implementovat vystupy definovane v ukolech
 * Doplnit metody ci kod
 */
public class Comparing {

    /**
     * Muze se hodit :)
     * @param music to, co chci vytisknout
     */
    static void printList(ArrayList<Song> music) {
        for (Song song : music)
            System.out.println(song);
    }
    static void loadMusic(String filepath,ArrayList<Song> music){
        try{
            String[] params;
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            for (String line : lines){
                params = line.split(";");
                music.add(new Song(params[0],Integer.parseInt(params[1]),Double.parseDouble(params[2])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void init() {
        String filePath = "Songs.txt";
        ArrayList<Song> music = new ArrayList<>();
        loadMusic(filePath,music);
        System.out.println("Spotify (Wish edition) at work, please stand by...");
        List<Song> sortedByName = music.stream()
                .sorted(Comparator.comparing(Song::getName))
                .toList();
       // System.out.println(sortedByName + "sorted by name up there");

        List<Song> sortedByYear = music.stream()
                .sorted(Comparator.comparingInt(Song::getYearOfRelease))
                .toList();
        //System.out.println(sortedByYear +" sorted by year up there");

        List<Song> sortedByRating = music.stream()
                .sorted(Comparator.comparingDouble(Song::getRating))
                .toList();
        //System.out.println(sortedByRating + "sorted by rating up there");

        List<Song> top3ByRating =music.stream()
                .sorted(Comparator.comparingDouble(Song::getRating).reversed())
                .limit(3)
                .toList();
        System.out.println(top3ByRating +" Top 3 here");



    }
}
class Song {
    String name;
    int yearOfRelease;
    double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Song(String name, int yearOfRelease, double rating) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + " " + yearOfRelease + ")" + ": " + rating;
    }

    final static Comparator<Song> BY_YEAR = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return Double.compare(o1.yearOfRelease, o2.yearOfRelease);
        }
    };
    final static Comparator<Song> BY_NAME = Comparator.comparing(Song::getName);
    final static Comparator<Song> BY_RATING = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return Double.compare(o1.rating, o2.rating);
        }
    };

}
