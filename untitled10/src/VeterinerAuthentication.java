import java.util.ArrayList;
import java.util.HashMap;

public class VeterinerAuthentication extends Authentication{
    ArrayList<String> kullaniciAdlari=new ArrayList<>();
    ArrayList<Veteriner> veterinerler=new ArrayList<>();
    ArrayList<String> parolalar=new ArrayList<>();



    ArrayList<Hayvan> hayvanlar=new ArrayList<>();


    VeterinerAuthentication(ArrayList<String> kullaniciAdlari,ArrayList<String> parolalar){
        this.kullaniciAdlari=kullaniciAdlari;
        this.parolalar=parolalar;
        for (Sahip sahip : Veteriner.sahipler) {
         for (Hayvan hayvan:sahip.getHayvanlar()){
             hayvanlar.add(hayvan);
         }
        }
    }

    @Override
    int logIn(String kullaniciAdi, String parola) {

        for (int i = 0; i < kullaniciAdlari.size(); i++) {
            if(kullaniciAdlari.get(i).equals(kullaniciAdi) && parolalar.get(i).equals(parola)){
                Veteriner veteriner=new Veteriner(kullaniciAdlari.get(i), parolalar.get(i),hayvanlar );
                veterinerler.add(veteriner);
                return 1;
            }
        }
        System.out.println("Böyle bir veteriner yok");
        return 0;
    }

    public Veteriner veterinerCagir(String kullaniciAdi,String parola){
        for (int i = 0; i < kullaniciAdlari.size(); i++) {
            if(kullaniciAdlari.get(i).equals(kullaniciAdi) && parolalar.get(i).equals(parola)){
                Veteriner veteriner=new Veteriner(kullaniciAdlari.get(i), parolalar.get(i),hayvanlar );
                return veteriner;
            }
        }
        System.out.println("Böyle bir veteriner yok");
        return null;


    }
}
