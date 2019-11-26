package Services;

import Modules.IModule;

import java.io.File;
import java.util.Scanner;

public class Service {

    private static int command;

    public void operationChoice(IModule module, File file) throws Exception {

        while (chooseCommand(module) != 0) {

            try {
                commandProcessing(command, module, file);
            } catch (NoSuchMethodException e) {
                System.out.println("Uncknown comand");
            }
        }
    }

    private static int chooseCommand(IModule module) {
        System.out.println();
        System.out.println("0 for exit");
        module.functionDefinition();
        Scanner scan = new Scanner(System.in);
        return command = scan.nextInt();
    }

    private static void commandProcessing(int command, IModule module, File file) throws Exception {
        module.func(file, command);
    }
}

