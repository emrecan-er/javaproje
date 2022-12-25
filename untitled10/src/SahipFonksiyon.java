import java.util.Scanner;

public class SahipFonksiyon {
    public static void sahipEkrani(OwnerAuthentication ownerAuthentication, Scanner input) {
        System.out.println("Kullanıcı adınız: ");
        String kullaniciAdi = input.next();
        System.out.println("Parolanız: ");
        String parola = input.next();
        int sahiplerIndex= ownerAuthentication.logIn(kullaniciAdi, parola);
        if (sahiplerIndex!=-1) {//index donecek -1 ise hatali giris
            Sahip sahip=Veteriner.sahipler.get(sahiplerIndex);
            System.out.println("Hoşgeldiniz " + kullaniciAdi);
            Boolean state = true;
            while (true) {
                int order1;
                System.out.println("1-Hayvan ekle\n2-Hayvanlarını Görüntüle\n3-Randevu Al\n4-Randevu Sorgula\n5-Çıkış Yap");
                order1 = input.nextInt();
                if (order1<0 || order1>5) {
                    System.out.println("Geçerli bir sayı giriniz");
                }
                if (order1 == 1) {
                    //uzun satır kod olduğu için kodu parçaladık
                    VeterinerFonksiyon.hayvanEkle(input, sahip);
                }
                if(order1==2){
                    //burada 2 satır kod olduğu için kodu parçalamadık
                    System.out.println("size kayıtlı dostlarınız");
                    sahip.hayvanlariGoruntule();
                }
                if(order1==3){
                    VeterinerFonksiyon.randevuAl(input, sahip);

                }
                if(order1==4){
                    sahip.randevuSorgula();
                }
                if(order1==5){
                    break;
                }

            }
        }
    }

    public static void sahipGiris() {
        System.out.println("1-Kullanıcı girişi yap");
        System.out.println("2-Veteriner girişi yap");
        System.out.println("3-Kullanıcı kayıt ol");
        System.out.println("4-Çıkış Yap");
    }

}
