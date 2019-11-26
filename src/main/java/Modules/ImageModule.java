package Modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageModule implements IModule {

    public boolean checkingFileExtension(String fileExtension) {
        return fileExtension.equals("jpg");
    }


    public void functionDefinition() {
        System.out.println("1 - Get exif information");
        System.out.println("2 - Get image resolution");
        System.out.println("3 - Get data precision");
    }

    @Override
    public void func(File file, int numFunc) throws Exception {
        ImageModule.class.getMethod("func" + numFunc, File.class).invoke(this, file);
    }


    public void func1(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        System.out.println("EXIF information:");
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }

    public void func2(File file) throws IOException {
        BufferedImage bimg = ImageIO.read(file);
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        System.out.println("Image resolution: " + width + "x" + height + "px");
    }

    public void func3(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        for (Directory directory : metadata.getDirectories()) {
            if (directory.getName().equals("JPEG")) {
                System.out.println("Data percision: " + directory.getDescription(0));
            }
        }
    }
}
