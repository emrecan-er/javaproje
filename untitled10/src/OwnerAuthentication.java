import java.util.ArrayList;

public class OwnerAuthentication extends Authentication{
    ArrayList<String> sahipKullaniciAdi=new ArrayList<>();
    ArrayList<String> sahipParola=new ArrayList<>();
    OwnerAuthentication(){

    }
    @Override
    int logIn(String kullaniciAdi,String parola) {
        for (int i = 0; i < Veteriner.sahipler.size(); i++) {
            if(kullaniciAdi.equals(Veteriner.sahipler.get(i).kullaniciAdi) && parola.equals(Veteriner.sahipler.get(i).sifre)){
                System.out.println("Başarıyla giriş yaptınız");
                return i;
            }
        }
        System.out.println("Böyle bir kullanıcı yok");
        return -1;
    }

    @Override
    void signIn(String kullaniciAdi,String parola) {
        sahipKullaniciAdi.add(kullaniciAdi);
        sahipParola.add(parola);
        System.out.println("Kullanıcı oluşturma başarılı");

    }
}
