package pdp_ödev_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * B231210089
 * Mustafa Arkan
 * 26.04.2025
 * 
 * Dosya okuma işlemleri: gezegen, uzay aracı, kişi verilerini okur.
 */

public class DosyaOkuma {

    public static List<Gezegen> gezegenleriOku(String dosyaYolu) throws IOException {
        List<Gezegen> gezegenler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaYolu));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            String ad = parcalar[0].trim();
            int gunSaat = Integer.parseInt(parcalar[1].trim());
            String tarih = Zaman.tarihFormatla(parcalar[2].trim());
            gezegenler.add(new Gezegen(ad, gunSaat, tarih));
        }
        br.close();
        return gezegenler;
    }

    public static List<UzayAracı> araclariOku(String dosyaYolu) throws IOException {
        List<UzayAracı> uzayAraclari = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaYolu));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            String ad = parcalar[0].trim().replace(" ", ""); // ad boşluk temizleme
            String cikisGezegeni = parcalar[1].trim();
            String varisGezegeni = parcalar[2].trim();
            String cikisTarihi = Zaman.tarihFormatla(parcalar[3].trim());
            int mesafeSaat = Integer.parseInt(parcalar[4].trim());
            uzayAraclari.add(new UzayAracı(ad, cikisGezegeni, varisGezegeni, cikisTarihi, mesafeSaat));
        }
        br.close();
        return uzayAraclari;
    }

    public static List<Kişi> kisileriOku(String dosyaYolu) throws IOException {
        List<Kişi> kisiler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaYolu));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            String isim = parcalar[0].trim();
            int yas = Integer.parseInt(parcalar[1].trim());
            int kalanOmur = Integer.parseInt(parcalar[2].trim());
            String uzayAraciAdi = parcalar[3].trim().replace(" ", "");
            kisiler.add(new Kişi(isim, yas, kalanOmur, uzayAraciAdi));
        }
        br.close();
        return kisiler;
    }
}
