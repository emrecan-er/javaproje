import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VeterinerFonksiyon {
    public static void randevuAl(Scanner input, Sahip sahip) {
        System.out.println("Lütfen bir hayvanı seçin");
        sahip.hayvanlariGoruntule();
        ArrayList<Hayvan> hayvanlar= sahip.getHayvanlar();
        int index= input.nextInt()-1;
        if(index<=hayvanlar.size() && index>=0){
            sahip.randevuAl(hayvanlar.get(index));
        }
        else{
            System.out.println("Lütfen geçeri bir sayı girin");
        }
    }

    public static void hayvanEkle(Scanner input, Sahip sahip) {
        String ad;
        String tur;
        String cinsiyet;
        int yas;
        HashMap<String, Boolean> durumlar = new HashMap<>();
        ArrayList<String> gecmisIslemler = new ArrayList<>();
        System.out.println("Hayvanın adı:");
        ad = input.next();
        System.out.println("Hayvanın türü: ");
        tur = input.next();
        System.out.println("Hayvanın cinsiyeti: ");
        cinsiyet = input.next();
        System.out.println("Hayvanın yaşı: ");
        yas = input.nextInt();
        System.out.println("Hayvanın sağlığı iyi/kötü ");
        if (input.next().equalsIgnoreCase("iyi")) durumlar.put("hayvanSaglikDurumu", true);
        else durumlar.put("hayvanSaglikDurumu", false);
        System.out.println("Hayvan Kısırlaştırıldı mı?");
        if (input.next().equalsIgnoreCase("evet")) durumlar.put("kisirlastirma", true);
        else durumlar.put("kisirlastirma", false);
        System.out.println("Hayvan kuduz aşısı oldu mu?");
        if (input.next().equalsIgnoreCase("evet")) durumlar.put("Kuduz", true);
        else durumlar.put("Kuduz", false);
        durumlar.put("Ateş",false);
        durumlar.put("İshal",false);
        durumlar.put("Doğum",false);
        durumlar.put("Sakinleştirici",false);
        Hayvan hayvan = new Hayvan(0, ad, tur, cinsiyet, yas, durumlar, gecmisIslemler);
        sahip.hayvanEkle(hayvan);
        System.out.println("Hayvan kaydı oluşturuldu");
    }
    public static void veterinerEkrani(VeterinerAuthentication veterinerAuthentication, Scanner input) {
        System.out.println("Veteriner girişi yapmayı seçtiniz!!!\nLütfen kullanıcı adınızı giriniz: ");
        String kullaniciAdiAdmin= input.next();
        System.out.println("Parola: ");
        String parolaAdmin= input.next();
        boolean veterinerDurum= (veterinerAuthentication.logIn(kullaniciAdiAdmin, parolaAdmin) == 1);
        while(veterinerDurum) {
            Veteriner veteriner = veterinerAuthentication.veterinerCagir(kullaniciAdiAdmin, parolaAdmin);
            int order2 = 0;
            System.out.println("1-Hayvanları Görüntüle");
            System.out.println("2-Aşı Yap");
            System.out.println("3-Kısırlaştır");
            System.out.println("4-Diğer tedavi yöntemleri");
            System.out.println("5-Randevuları Görüntüle");
            System.out.println("6-Geçmiş tedavileri görüntüle");
            System.out.println("7-Taburcu Et");
            System.out.println("8-Çıkış yap");
            order2 = input.nextInt();
            if (order2 == 1) {
                //az satır kod var parçalamadık
                for (Sahip sahip : Veteriner.sahipler) {
                    System.out.println(sahip.kullaniciAdi + " adlı kullanıcı adına sahip kişinin dostları: ");
                    sahip.hayvanlariGoruntule();

                }

            }
            else if (order2 == 2) {
                asiYapmaIslemi(input, veteriner);

            }
            else if (order2 == 3) {
                kisirlastirmaIslemi(input, veteriner);

            }
            else if (order2 == 4) {
                digerTedaviler(input, veteriner);
            }
            else if (order2 == 5) {
                for (Sahip sahip : Veteriner.sahipler) {
                    System.out.println(sahip.kullaniciAdi + " adlı kullanıcı adına sahip kişinin randevuları:");
                    sahip.randevuSorgula();
                }
            }
            else if (order2 == 6) {
                randevuGecmisi();
            }
            else if (order2==7){
                Veteriner.taburcuEt();
            }

            else {
                break;
            }
        }
    }

    public static void randevuGecmisi() {
        for (int i = 0; i < Veteriner.sahipler.size(); i++)
            for (Sahip sahip : Veteriner.sahipler) {
                for (Hayvan hayvan : Veteriner.sahipler.get(i).getHayvanlar()) {
                    System.out.println(sahip.kullaniciAdi + " Kişisinin dostlarının randevu geçmişi");
                    hayvan.tedaviGecmisiGoruntule();
                }
            }
    }

    public static void asiYapmaIslemi(Scanner input, Veteriner veteriner) {
        for (Sahip sahip : Veteriner.sahipler) {
            System.out.println(sahip.kullaniciAdi + " adlı kullanıcı adına sahip kişinin dostları: ");
            sahip.hayvanlariGoruntule();
        }
        System.out.println("Kaçıncı kişinin hayvanına aşı yapmak istiyorsunuz");
        int sahipNumara = input.nextInt() - 1;

        System.out.println("Kaçıncı hayvana aşı yapmak istiyorsunuz");
        int sahipHayvan = input.nextInt() - 1;
        for (Asi asi : veteriner.asiTurleri) {
            System.out.println(asi.isim);
        }
        System.out.println("Kaçıncı aşıyı yapmak istiyorsunuz");
        int asiNumarasi = input.nextInt() - 1;
        veteriner.asiYap(Veteriner.sahipler.get(sahipNumara).getHayvanlar().get(sahipHayvan), veteriner.asiTurleri.get(asiNumarasi), veteriner.asiTurleri.get(asiNumarasi).isim);
        veteriner.tedaviGecmisiEkle(veteriner.asiTurleri.get(asiNumarasi).isim + " aşısı yapıldı", Veteriner.sahipler.get(sahipNumara).getHayvanlar().get(sahipHayvan));
    }

    public static void kisirlastirmaIslemi(Scanner input, Veteriner veteriner) {
        for (Sahip sahip : Veteriner.sahipler) {
            System.out.println(sahip.kullaniciAdi + " adlı kullanıcı adına sahip kişinin dostları: ");
            sahip.hayvanlariGoruntule();
        }
        System.out.println("Kaçıncı kişinin hayvanını kısırlarştırmak istiyorsunuz");
        int sahipNumara = input.nextInt() - 1;

        System.out.println("Kaçıncı hayvanı kısırlaştırmak istiyorsunuz");
        int sahipHayvan = input.nextInt() - 1;
        veteriner.kisirlastir(Veteriner.sahipler.get(sahipNumara).getHayvanlar().get(sahipHayvan), "Kısırlaştırma");
    }

    public static void digerTedaviler(Scanner input, Veteriner veteriner) {
        for (Sahip sahip : Veteriner.sahipler) {
            System.out.println(sahip.kullaniciAdi + " adlı kullanıcı adına sahip kişinin dostları: ");
            sahip.hayvanlariGoruntule();
        }
        System.out.println("Kaçıncı kişinin hayvanına tedavi uygulamak istiyorsunuz");
        int sahipNumara = input.nextInt() - 1;

        System.out.println("Kaçıncı hayvana tedavi uygulamak istiyorsunuz");
        int sahipHayvan = input.nextInt() - 1;
        System.out.println("Sorunu giriniz");
        input.nextLine();
        String sorun = input.nextLine();
        System.out.println("Tedavi yöntemini giriniz");

        String tedavi = sorun + input.nextLine();
        veteriner.tedaviGecmisiEkle(tedavi, Veteriner.sahipler.get(sahipNumara).getHayvanlar().get(sahipHayvan));
    }
}
