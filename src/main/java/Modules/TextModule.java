package Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class TextModule implements IModule {

    public boolean checkingFileExtension(String fileExtension) {
        return fileExtension.equals("txt");
    }


    public void functionDefinition() {
        System.out.println("1 - Get character occurence rate");
        System.out.println("2 - Get count of lines");
        System.out.println("3 - Get count of words");
    }


    public void func(File file, int numFunc) throws Exception {
        TextModule.class.getMethod("func" + numFunc, File.class).invoke(this, file);
    }


    public void func1(File file) {
        int totalCharacters = 0;
        Scanner scanner = new Scanner(file.getPath());
        Hashtable<Character, ArrayList<Character>> dictionary = new Hashtable<>();

        while (scanner.hasNext()) {
            char[] str = scanner.next().toLowerCase().toCharArray();

            for (char c : str) {
                //if (!Character.isLetter(c)) continue;
                if (!dictionary.containsKey(c)) dictionary.put(c, new ArrayList<>());

                ArrayList<Character> list = dictionary.get(c);
                list.add(c);

                totalCharacters++;
            }
        }

        System.out.println("Symbols count - " + totalCharacters);

        for (ArrayList<Character> list : dictionary.values()) {
            float percent = ((float) list.size() * 100) / totalCharacters;
            System.out.println("Probability of encountering a symbol - '" + list.get(0) +
                    "' = " + String.format("%.2f", percent) + "%");
        }
    }

    public void func2(File file) throws FileNotFoundException {
        int linesCount = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            linesCount++;
        }
        System.out.println("Lines count: " + linesCount);

    }

    public void func3(File file) throws FileNotFoundException {
        int wordscount = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            wordscount += str.split(" +").length;
            wordscount++;
        }
        System.out.println("Words count: " + wordscount);

    }
}
