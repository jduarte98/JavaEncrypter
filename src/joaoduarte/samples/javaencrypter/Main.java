package joaoduarte.samples.javaencrypter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *@author João Duarte (https://github.com/jduarte98 | www.linkedin.com/in/joão-duarte-453bb9199)
 *@version 1
 *
 */
public class Main {
    private static Encryption encryption = new Encryption();
    private static boolean keepRunning = true;
    private static final Scanner keyboard = new Scanner(System.in);
    private static String salt = "";
    private static int opt = -1;

    /**
     * Main Function
     * @param args --
     */
    public static void main(String[] args) {
        System.out.println("### JAVA ENCRYPTER | " + new SimpleDateFormat("yyyy").format(new Date()) + " ###");
        System.out.println(" Tip:\n  - Secret salt is used to enhance the security of the encryption.\n  - Define the Salt on the next step.\n  - Remember that, when decrypting, you must use the correct Salt for correct results!\n");
        salt = readString(keyboard, "Define Secret Salt: ");
        while(keepRunning){
            mainMenu();
        }
    }

    /**
     * Presents the Main Menu to the User and performs the launch modules logic.
     */
    private static void mainMenu(){
        try{
            System.out.println(" Menu:\n     1 - Encrypt Text\n     2 - Decrypt Text\n     3 - Salt Settings\n     4 - Exit");
            opt = Integer.parseInt(readString(keyboard,"Option: "));
        }catch (NumberFormatException nfe){
            System.out.println("\n   !!! This field ONLY accepts NUMBERS! Please try again !!!\n");
            mainMenu();
        }
        switch(opt){
            case 1:{
                String encd = encryption.encrypt(readString(keyboard,"      String to Encrypt? "),salt);
                System.out.println("       Encrypted String: " + encd);
                break;
            }
            case 2:{
                String decd = encryption.decrypt(readString(keyboard,"      String to Decrypt? "), salt);
                System.out.println("       Decrypted String: " + decd);
                break;
            }
            case 3:{
                System.out.println("       Defined Salt: " + salt);
                String quest = readString(keyboard, "      Update Salt (Y/N) ? ");
                String oldSalt = salt;
                if(quest.equalsIgnoreCase("y") || quest.equalsIgnoreCase("yes")){
                    salt = readString(keyboard, "       New Salt? ");
                    System.out.println("       Old Salt: " + oldSalt + "\n       New Salt: " + salt);
                    quest = readString(keyboard, "       Save (Y/N) ? ");
                    if(quest.equalsIgnoreCase("n") || quest.equalsIgnoreCase("no")){
                        salt = oldSalt;
                        System.out.println("       Changes Discarded!\n       Old Salt: " + oldSalt + "\n       New Salt: " + salt);
                    }else{
                        System.out.println("       Changes Saved!\n       Old Salt: " + oldSalt + "\n       New Salt: " + salt);
                    }
                }
                break;
            }
            case 4: {
                keepRunning = false;
                System.out.println("### Process Finished! ###");
                break;
            }
            default:{
                System.out.println("\n   !!! Invalid Option , please try again !!!\n");
                mainMenu();
            }
        }
    }

    /**
     * Read User Keyboard Inputed Strings
     * @param aKeyboard Scanner Object
     * @param aMsg Message to Show when "requesting" the data
     * @return String - User Keyboard provided data
     */
    public static String readString(Scanner aKeyboard, String aMsg) {
        System.out.print(" " + aMsg);
        return aKeyboard.nextLine();
    }
}
