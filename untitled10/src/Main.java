
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> sahipKullanici = new ArrayList<>();
        ArrayList<String> sahipParola = new ArrayList<>();
        ArrayList<String> veterinerKullanici=new ArrayList<>();
        ArrayList<String> veterinerParola=new ArrayList<>();
        veterinerKullanici.add("admin");
        veterinerParola.add("123456");
        VeterinerAuthentication veterinerAuthentication=new VeterinerAuthentication(veterinerKullanici,veterinerParola);
        OwnerAuthentication ownerAuthentication = new OwnerAuthentication();
        Scanner input = new Scanner(System.in);


        WelcomeScreenFonksiyon.welcomeScreen(veterinerAuthentication, ownerAuthentication, input);

    }








}
