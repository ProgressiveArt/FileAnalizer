package Modules;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Mp3Module implements IModule {

    public boolean checkingFileExtension(String fileExtension) {
        return fileExtension.equals("mp3");
    }


    public void functionDefinition() {
        System.out.println("1 - Get rack duration");
        System.out.println("2 - Get track Name");
        System.out.println("3 - Play");
    }


    public void func(File file, int numFunc) throws Exception {
        Mp3Module.class.getMethod("func" + numFunc, File.class).invoke(this, file);
    }



    public void func1(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = (fileFormat).properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000);
//            int min = (mili / 1000) / 60;
            System.out.println("Duration = " + sec + " seconds");
        }
    }

    public void func2(File file) throws IOException, ImageProcessingException {
        Metadata metadata = Mp4MetadataReader.readMetadata(file);

        for (Directory directory : metadata.getDirectories()) {
            if (directory.getName().equals("File")) {
                System.out.println("Track name: " + directory.getDescription(1));
            }
        }
    }

    public void func3(File file) throws  IOException {
        Desktop.getDesktop().open(file);
    }
}
