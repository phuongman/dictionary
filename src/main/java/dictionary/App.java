package dictionary;

import dictionary.Controller.*;
import dictionary.Model.NormalDictionary;

public class App {

    public static Integer a = 10;
    public static Integer b = 5;
    private static final String[] arrayChoices = { "Exit", "Add", "Remove", "Update", "Display", "Lookup", "Search",
            "Game", "Import from file", "Export to file" };

    public static void PrintChoices() {
        for (int i = 0; i < arrayChoices.length; i++) {
            System.out.println("[" + i + "] " + arrayChoices[i]);
        }
        return;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Manager.dictionary = new NormalDictionary();

        System.out.println("Welcome to My Application!");
        boolean exit = false;
        while (exit == false) {
            PrintChoices();
            System.out.print("Your action (0 - 9): ");

            String choice = Inputer.InputNextLine();
            switch (choice) {
                case "0":
                    Manager.Exit();
                    exit = true;
                    break;
                case "1":
                    Manager.Add();
                    break;
                case "2":
                    Manager.Remove();
                    break;
                case "3":
                    Manager.Update();
                    break;
                case "4":
                    Manager.Display();
                    break;
                case "5":
                    Manager.Lookup();
                    break;
                case "6":
                    Manager.Search();
                    break;
                case "7":
                    Game.Game();
                    break;
                case "8":
                    Manager.ImportFromFle();
                    break;
                case "9":
                    Manager.ExportToFile();
                    break;
                default:
                    System.out.println("Action not supported");
                    break;
            }
        }
    }

}
