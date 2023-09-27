package dictionary.Controller;

import dictionary.Inputer;
import dictionary.Model.NormalDictionary;

public class Manager {
    public static NormalDictionary dictionary;

    /** Option 1: Exit the app. */
    public static void Exit() {
        System.out.println("--Thoat ung dung...");
    }

    /** Option 2: add words from command line. */
    public static void Add() {
        System.out.print("--Nhap so tu ban muon them: ");
        int n = Inputer.InputNextInt();
        Inputer.InputNextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("--Nhap ten cua tu: ");
            String target = Inputer.InputNextLine();
            System.out.print("--Nhap nghia cua tu: ");
            String meaning = Inputer.InputNextLine();
            if (!dictionary.addWord(target, meaning)) {
                System.out.println("Tu \'" + target + "\' da co trong tu dien.");
            } else {
                System.out.println("Da them tu thanh cong.");
            }
        }
        Inputer.pressEnterToContinue();
    }

    /** Option 3: remove a word from command line. */
    public static void Remove() {
        System.out.print("--Nhap ten tu ban muon xoa: ");
        String target = Inputer.InputNextLine();
        if (!dictionary.removeWord(target)) {
            System.out.println("Tu \'" + target + "\' khong co trong tu dien.");
        } else {
            System.out.println("Da xoa tu thanh cong.");
        }
    }

    /** Option 4: update meaning of a word from command line. */
    public static void Update() {
        System.out.print("--Nhap ten tu ban muon cap nhat: ");
        String target = Inputer.InputNextLine();
        System.out.print("--Nhap nghia moi cua tu: ");
        String meaning = Inputer.InputNextLine();
        if (!dictionary.updatewordMeaning(target, meaning)) {
            System.out.println("Tu \'" + target + "\' khong co trong tu dien.");
        } else {
            System.out.println("Da cap nhat thanh cong");
        }
    }

    public static void Display() {
        String result = dictionary.showAllWords();
        if (result.equals("Error")) {
            System.out.println("Tu dien hien tai chua co tu nao.");
        } else {
            System.out.print(dictionary.showAllWords());
        }
        Inputer.pressEnterToContinue();
    }

    public static void Lookup() {
        System.out.print("--Nhap ten tu ban muon tra cuu: ");
        String target = Inputer.InputNextLine();
        String result = dictionary.lookUpWord(target);
        if (result.equals("Error")) {
            System.out.println("Tu \'" + target + "\' khong co trong tu dien.");
        } else {
            System.out.println("Nghia cua tu \'" + target + "\' la: " + result);
        }
        Inputer.pressEnterToContinue();
    }

    public static void Search() {
    }

    public static void ImportFromFle() {
    }

    public static void ExportToFile() {
    }

}
