package pdp_ödev_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  B231210089
 *  Mustafa Arkan
 *  26.04.2025
 * 
 * Zaman hesaplamaları için kullanılır. İki tarih arasındaki farkı bulur, tarih karşılaştırmaları yapar.
 * 
 */

public class Zaman {
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    // İki tarih arasındaki farkı saat cinsinden verir
    public static int ikiTarihFarkiSaat(String baslangic, String bitis, int gunSaat) {
        try {
            Date baslangicTarihi = format.parse(baslangic);
            Date bitisTarihi = format.parse(bitis);

            long farkMillisaniye = bitisTarihi.getTime() - baslangicTarihi.getTime();
            long farkGun = farkMillisaniye / (1000 * 60 * 60 * 24);

            return (int)(farkGun * gunSaat);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // İki tarihi karşılaştırır
    // Eğer tarih1 == tarih2 ise true döner (saat bilgisi dikkate alınmaz)
    public static boolean tarihlerEsitMi(String tarih1, String tarih2) {
        return tarih1.equals(tarih2);
    }

    // Bir tarih üzerine verilen saat miktarına göre ileri tarih döner
    public static String ileriTarihHesapla(String baslangicTarihi, int gunSaat, int saatEklenecek) {
        try {
            String[] parts = baslangicTarihi.split("\\.");
            int gun = Integer.parseInt(parts[0]);
            int ay = Integer.parseInt(parts[1]);
            int yil = Integer.parseInt(parts[2]);
            
            int toplamSaat = saatEklenecek;
            int[] ayGunSayisi = {31,28,31,30,31,30,31,31,30,31,30,31};

            while (toplamSaat >= gunSaat) {
                toplamSaat -= gunSaat;
                gun++;

                if (gun > ayGunSayisi[ay - 1]) {
                    gun = 1;
                    ay++;
                    if (ay > 12) {
                        ay = 1;
                        yil++;
                    }
                }
            }
            return String.format("%02d.%02d.%04d", gun, ay, yil);
        } catch (Exception e) {
            e.printStackTrace();
            return baslangicTarihi;
        }
    }
    
    public static String tarihFormatla(String tarih) {
        try {
            String[] parts = tarih.split("\\.");
            int gun = Integer.parseInt(parts[0]);
            int ay = Integer.parseInt(parts[1]);
            int yil = Integer.parseInt(parts[2]);
            
            return String.format("%02d.%02d.%04d", gun, ay, yil);
        } catch (Exception e) {
            e.printStackTrace();
            return tarih; // Hatalıysa olduğu gibi döndürür.
        }
    }


}
