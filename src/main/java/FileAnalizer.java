import Modules.IModule;
import Services.Service;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Collection;

public class FileAnalizer {
    private static File file;
    private final Collection<IModule> modules;
    private String startupMessage;

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring.xml");

        FileAnalizer fa = ctx.getBean(FileAnalizer.class);
        Service service = ctx.getBean(Service.class);

        System.out.println(fa.startupMessage);
        String fileExtension = getFileExtension();

        for (IModule module : fa.modules) {
            if (module.checkingFileExtension(fileExtension))
                service.operationChoice(module, file);
        }
    }

    private static String getFileExtension() {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else if (file.isDirectory())
            return "directory";
        return "Unknown object";
    }

    public void setFile(String file) {
        FileAnalizer.file = new File(file);
    }

    public FileAnalizer(Collection<IModule> modules) {
        this.modules = modules;
    }

    public void setStartupMessage(String startupMessage) {
        this.startupMessage = startupMessage;
    }
}
