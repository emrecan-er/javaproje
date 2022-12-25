import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Veteriner extends Kisi{
    Asi asi1=new Asi("Kuduz","20CC");
    Asi asi2=new Asi("Ateş","10CC");
    Asi asi3=new Asi("İshal","14CC");
    Asi asi4=new Asi("Doğum","40CC");
    Asi asi5=new Asi("Sakinlştirici","10CC");
    ArrayList<Asi> asiTurleri=new ArrayList<>();
    HashMap<Asi,Integer> asiSayilari=new HashMap<>();

    static ArrayList<Sahip> sahipler=new ArrayList<>();

    public Veteriner(String kullaniciAdi, String sifre, ArrayList<Hayvan> hayvanlar){
        super(kullaniciAdi,sifre);

        asiSayilari.put(asi1,5);
        asiSayilari.put(asi2,10);
        asiSayilari.put(asi3,20);
        asiSayilari.put(asi4,15);
        asiSayilari.put(asi5,30);
        asiTurleri.add(asi1);
        asiTurleri.add(asi2);
        asiTurleri.add(asi3);
        asiTurleri.add(asi4);
        asiTurleri.add(asi5);
    }


    public void asiYap(Hayvan hayvan,Asi key,String durumu){

        if(!hayvan.durumlar.get(durumu)){
            if (asiSayilari.get(key)>0){
                hayvan.durumlar.put(durumu,true);
                asiSayilari.put(key,(asiSayilari.get(key)-1));
                System.out.println("Aşı yapıldı");
            }

        }
        else{
            System.out.println("Bu aşı zaten yapılmış");
        }
    }

    public void kisirlastir(Hayvan hayvan,String tedaviAciklamasi){
        if(!hayvan.durumlar.get("kisirlastirma")){
            hayvan.durumlar.put("kisirlastirma",true);
            System.out.println("Kısırlaştırma başarılı");
            tedaviGecmisiEkle(tedaviAciklamasi,hayvan);
        }
        else{
            System.out.println("Bu hayvan zaten kısırlaştırılmış");
        }
    }

    public void tedaviGecmisiEkle(String tedavi,Hayvan hayvan){
        Date simdikiZaman = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        String tarih=df.format(simdikiZaman);
        hayvan.gecmisIslemler.add((tarih+": "+tedavi));
    }
    static Scanner klavye=new Scanner(System.in);

    public static void taburcuEt(){
        for (Sahip sahip : Veteriner.sahipler) {
            System.out.println(sahip.kullaniciAdi + " adlı kullanıcı adına sahip kişinin dostları: ");
            sahip.hayvanlariGoruntule();
        }
        System.out.println("Kaçıncı kişinin hayvanını taburcu etmek istiyorsunuz");
        int taburcuSecim = klavye.nextInt() - 1;

        System.out.println("Kaçıncı hayvanı taburcu etmek istiyorsunuz");
        int sahipHayvan = klavye.nextInt() - 1;
      Veteriner.sahipler.get(taburcuSecim).getHayvanlar().get(sahipHayvan).durumlar.put("hayvanSaglikDurumu",true);
        System.out.println("TABURCU EDİLDİ,GEÇMİŞ OLSUN");

    }
}
