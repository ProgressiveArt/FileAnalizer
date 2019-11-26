package Modules;


import java.io.File;
import java.util.*;

public class CatalogModule implements IModule {

    public boolean checkingFileExtension(String fileExtension) {
        return fileExtension.equals("directory");
    }


    public void functionDefinition() {
        System.out.println("1 - Get list files");
        System.out.println("2 - Get list files with size");
        System.out.println("3 - Get catalog tree");
    }


    public void func(File file, int numFunc) throws Exception {
        CatalogModule.class.getMethod("func" + numFunc, File.class).invoke(this, file);
    }

    public void func1(File path) {
        StringBuilder attrFiles = new StringBuilder();
        File[] files = path.listFiles();

        for (File file : files) {

            attrFiles.append(file.getName())
                    .append("\n");
        }

        System.out.println(attrFiles);
    }

    public void func2(File path) {
        StringBuilder attrFiles = new StringBuilder();
        double sum = 0;
        File[] files = path.listFiles();
        for (File file : files) {
            double kilobytes = (double) file.length() / 1024;
            attrFiles.append(file.getName())
                    .append(" - ")
                    .append(String.format("%.2fn", kilobytes))
                    .append(" kilobytes")
                    .append("\n");

            sum += kilobytes;
        }
        System.out.println(attrFiles);
        System.out.println();
        System.out.println("Summary = " + String.format("%.2f", sum) + " kilobytes");
    }

    public void func3(File path) {
        StringBuilder attrFiles = new StringBuilder();
        List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, Objects.requireNonNull(path.listFiles()));

        while (!fileTree.isEmpty())
        {
            File currentFile = fileTree.remove();
            if(currentFile.isDirectory()){
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));

            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }

        for(String s : result) {
            attrFiles.append(s)
                    .append("\n");
        }
        System.out.println(attrFiles);
    }
}

