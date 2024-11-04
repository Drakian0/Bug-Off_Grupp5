package common;

import java.util.Scanner;

import decathlon.Deca100M;
import decathlon.Deca1500M;
import decathlon.Deca110MHurdles;
import decathlon.Deca400M;
import decathlon.DecaDiscusThrow;
import decathlon.DecaHighJump;
import decathlon.DecaJavelinThrow;
import decathlon.DecaLongJump;
import decathlon.DecaShotPut;
import decathlon.DecaPoleVault;

import heptathlon.Hep100MHurdles;
import heptathlon.Hep200M;
import heptathlon.Hep800M;
import heptathlon.HeptHightJump;
import heptathlon.HeptJavelinThrow;
import heptathlon.HeptLongJump;
import heptathlon.HeptShotPut;

public class SelectDiscipline {

    int disciplineSelected;
    InputResult inputResult = new InputResult();
    Scanner sc = new Scanner(System.in);
    Deca100M dec100M = new Deca100M();
    Deca400M dec400M = new Deca400M();
    Deca1500M dec1500M = new Deca1500M();
    Deca110MHurdles dec110MHurdles = new Deca110MHurdles();
    DecaLongJump decLongJump = new DecaLongJump();
    DecaHighJump decHighJump = new DecaHighJump();
    DecaPoleVault decPoleVault = new DecaPoleVault();
    DecaDiscusThrow decDiscusThrow = new DecaDiscusThrow();
    DecaJavelinThrow decJavelinThrow = new DecaJavelinThrow();
    DecaShotPut decShotPut = new DecaShotPut();

    Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
    Hep200M hep200M = new Hep200M();
    Hep800M hep800M = new Hep800M();
    HeptJavelinThrow hepJavelinThrow = new HeptJavelinThrow();
    HeptHightJump hepHighJump = new HeptHightJump();
    HeptLongJump hepLongJump = new HeptLongJump();
    HeptShotPut hepShotPut = new HeptShotPut();

    //Receive input	of selection of discipline.
    public void inputSelection() {
        System.out.println("Select discipline.");
        printDisciplines();

        try {
            disciplineSelected = Integer.parseInt(sc.nextLine());
            makeSelection();

        } catch (Exception e) {
            System.out.println("Invalid input, try again.");
            System.out.println("");
            inputSelection();
        }

    }

    // Check input of discipline.
    public void makeSelection() {
        switch (disciplineSelected) {
            case 1:
                dec100M.calculateResult(inputResult.enterResult());
                break;
            case 2:
                dec400M.calculateResult(inputResult.enterResult());
                break;
            case 3:
                dec1500M.calculateResult(inputResult.enterResult());
                break;
            case 4:
                dec110MHurdles.calculateResult(inputResult.enterResult());
                break;
            case 5:
                decLongJump.calculateResult(inputResult.enterResult());
                break;
            case 6:
                decHighJump.calculateResult(inputResult.enterResult());
                break;
            case 7:
                decPoleVault.calculateResult(inputResult.enterResult());
                break;
            case 8:
                decDiscusThrow.calculateResult(inputResult.enterResult());
                break;
            case 9:
                decJavelinThrow.calculateResult(inputResult.enterResult());
                break;
            case 10:
                decShotPut.calculateResult(inputResult.enterResult());
                break;
            case 11:
                hep100MHurdles.calculateResult(inputResult.enterResult());
                break;
            case 12:
                hep200M.calculateResult(inputResult.enterResult());
                break;
            case 13:
                hep800M.calculateResult(inputResult.enterResult());
                break;
            case 14:
                hepJavelinThrow.calculateResult(inputResult.enterResult());
                break;
            case 15:
                hepHighJump.calculateResult(inputResult.enterResult());
                break;
            case 16:
                hepLongJump.calculateResult(inputResult.enterResult());
                break;
            case 17:
                hepShotPut.calculateResult(inputResult.enterResult());
                break;
            default:
                System.out.println("Invalid input, try again.");
                System.out.println("");
                inputSelection();
                break;
        }
    }

    // Needs more stuff.
    public void printDisciplines() {
        System.out.println("1. Decathlon 100 meters. (Measured in seconds)");
        System.out.println("2. Decathlon 400 meters. (Measured in seconds)");
        System.out.println("3. Decathlon 1500 meters. (Measured in minutes and seconds)");
        System.out.println("4. Decathlon 110 meters hurdles. (Measured in seconds)");
        System.out.println("5. Decathlon Long Jump. (Measured in centimeters)");
        System.out.println("6. Decathlon High Jump. (Measured in centimeters)");
        System.out.println("7. Decathlon Pole Vault. (Measured in centimeters)");
        System.out.println("8. Decathlon Discus Throw. (Measured in meters)");
        System.out.println("9. Decathlon Javelin Throw. (Measured in meters)");
        System.out.println("10. Decathlon Shot Put. (Measured in meters)");
        System.out.println("11. Heptathlon 100 meters hurdles. (Measured in seconds)");
        System.out.println("12. Heptathlon 200 meters. (Measured in seconds)");
        System.out.println("13. Heptathlon 800 meters. (Measured in seconds)");
        System.out.println("14. Heptathlon Javelin Throw. (Measured in meters)");
        System.out.println("15. Heptathlon High Jump. (Measured in centimeters)");
        System.out.println("16. Heptathlon Long Jump. (Measured in centimeters)");
        System.out.println("17. Heptathlon Shot Put. (Measured in meters)");
    }

}