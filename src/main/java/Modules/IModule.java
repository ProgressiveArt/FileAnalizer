package Modules;

import java.io.File;

public interface IModule {
    boolean checkingFileExtension(String fileExtension);
    void functionDefinition ();
    void func(File file, int numFunc) throws Exception;
}
