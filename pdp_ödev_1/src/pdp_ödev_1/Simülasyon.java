package pdp_ödev_1;
import pdp_ödev_1.UzayAracı;
import java.util.List;

/**
 *  B231210089
 *  Mustafa Arkan
 *  26.04.2025
 * 
 *  Simülasyonu yönetir. Zamanı ilerletir, kişileri ve uzay araçlarını günceller, ekranı temizler ve bilgileri yazdırır.
 * 
 */

public class Simülasyon {
    private List<Gezegen> gezegenler;
    private List<UzayAracı> uzayAraclari;
    private List<Kişi> kisiler;

    public Simülasyon(List<Gezegen> gezegenler, List<UzayAracı> uzayAraclari, List<Kişi> kisiler) {
        this.gezegenler = gezegenler;
        this.uzayAraclari = uzayAraclari;
        this.kisiler = kisiler;

        // Kişileri doğru araçlara atıyoruz
        for (Kişi kisi : kisiler) {
            UzayAracı arac = uzayAraciBul(kisi.getUzayAraciAdi());
            if (arac != null) {
                arac.yolcuEkle(kisi);
            }
        }
    }

    public void baslat() {
        boolean devam = true;

        while (devam) {
            temizleEkrani();

            // Gezegenlerde saat ilerlet
            for (Gezegen gezegen : gezegenler) {
                gezegen.saatIlerle();
            }

            // Her kişinin kalan ömrünü azalt
            for (Kişi kisi : kisiler) {
                kisi.saatGecir();
            }

            // Uzay araçlarının durumlarını güncelle
            for (UzayAracı arac : uzayAraclari) {
            	
            	

                Gezegen cikisGezegeni = gezegeniBul(arac.getCikisGezegeni());

                if (!arac.isHareketEttiMi() && Zaman.tarihlerEsitMi(cikisGezegeni.getTarih(), arac.getCikisTarihi())) {
                    Gezegen varisGezegeni = gezegeniBul(arac.getVarisGezegeni());
                    arac.hareketBaslat(varisGezegeni);
                }

                // Araç yoldaysa hedefe kalan saat azalsın
                arac.saatGecir();

                // Aracın içindeki kişilere bak, hepsi öldü mü? İmha kontrolü
                arac.imhaKontrolu();
            }

            // Durumu ekrana yaz
            ekranaDurumYaz();

            // Simülasyonun bitiş kontrolü
            devam = !tumAraclarVarisKontrol();

            try {
                Thread.sleep(50); // çok hızlı gitmesin diye
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nSimülasyon tamamlandı. Tüm araçlar hedeflerine vardı veya imha oldu.");
    }

    private Gezegen gezegeniBul(String ad) {
        for (Gezegen gezegen : gezegenler) {
            if (gezegen.getAd().equals(ad)) {
                return gezegen;
            }
        }
        return null;
    }

    private UzayAracı uzayAraciBul(String ad) {
        for (UzayAracı arac : uzayAraclari) {
            if (arac.getAd().equals(ad)) {
                return arac;
            }
        }
        return null;
    }

    private boolean tumAraclarVarisKontrol() {
        for (UzayAracı arac : uzayAraclari) {
            if (!arac.isImhaOlduMu() && arac.getHedefeKalanSaat() > 0) {
                return false;
            }
        }
        return true;
    }

    private void temizleEkrani() {
        try {
            // Windows için
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Temizleme başarısızsa 50 boş satır bastır
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }


    private void ekranaDurumYaz() {
        System.out.println("Gezegenler :\n");

        int sayac = 0;
        int grupBoyutu = 6; //  6'lı grup 

        while (sayac < gezegenler.size()) {
            // --- Gezegen İsimleri ---
            for (int i = sayac; i < sayac + grupBoyutu && i < gezegenler.size(); i++) {
                System.out.printf("%-15s", "---" + gezegenler.get(i).getAd() + "---");
            }
            System.out.println();

            // --- Tarihler ---
            System.out.println("Tarih :");
            for (int i = sayac; i < sayac + grupBoyutu && i < gezegenler.size(); i++) {
                System.out.printf("%-15s", gezegenler.get(i).getTarih());
            }
            System.out.println();

            // --- Nüfuslar ---
            System.out.println("Nüfus :");
            for (int i = sayac; i < sayac + grupBoyutu && i < gezegenler.size(); i++) {
                int sayi = 0;
                for (Kişi kisi : kisiler) {
                    if (kisi.isHayatta()) {
                        UzayAracı arac = uzayAraciBul(kisi.getUzayAraciAdi());
                        if (arac != null) {
                            if (!arac.isHareketEttiMi() && arac.getCikisGezegeni().equals(gezegenler.get(i).getAd())) {
                                sayi++;
                            } else if (arac.getHedefeKalanSaat() == 0 && arac.getVarisGezegeni().equals(gezegenler.get(i).getAd())) {
                                sayi++;
                            }
                        }
                    }
                }
                System.out.printf("%-15d", sayi);
            }
            System.out.println("\n");

            sayac += grupBoyutu; //  sonraki 6'lı gruba geç
        }

        System.out.println();

        // --- Uzay Araçları kısmı aynı kalıyor ---
        System.out.println("Uzay Araçları :\n");
        System.out.printf("%-10s %-12s %-10s %-10s %-20s %-20s\n", 
                "Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih");

        for (UzayAracı arac : uzayAraclari) {
            String durum;
            if (arac.isImhaOlduMu()) {
                durum = "İMHA";
            } else if (!arac.isHareketEttiMi()) {
                durum = "Bekliyor";
            } else if (arac.getHedefeKalanSaat() == 0) {
                durum = "VARDI";
            } else {
                durum = "Yolda";
            }

            String hedefeKalanSaatStr = arac.isImhaOlduMu() ? "--" : String.valueOf(arac.getHedefeKalanSaat());
            String hedefeVarisTarihStr = arac.isImhaOlduMu() ? "--" : arac.getHedefeVarisTarihi();

            System.out.printf("%-10s %-12s %-10s %-10s %-20s %-20s\n",
                    arac.getAd(), durum, arac.getCikisGezegeni(), arac.getVarisGezegeni(), hedefeKalanSaatStr, hedefeVarisTarihStr);
        }

        System.out.println("\n\n");
    }


}
