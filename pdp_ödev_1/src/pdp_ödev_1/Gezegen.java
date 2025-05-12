package pdp_ödev_1; 

/**
 *  B231210089
 *  Mustafa Arkan
 *  26.04.2025
 * 
 * Gezegen bilgilerini (ad, günün kaç saat olduğu, tarih) tutar ve gezegen zamanı ilerletilir.
 * 
 */

public class Gezegen {
    private String ad;
    private int gunSaat; // 1 gün kaç saatten oluşuyor
    private String tarih; // (gg.aa.yyyy) formatında

    private int saat; // 0'dan başlayacak her gezegende (0-23 gibi ama gün saatine göre değişir)

    // Constructor
    public Gezegen(String ad, int gunSaat, String tarih) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.tarih = tarih;
        this.saat = 0; // Başlangıçta her gezegenin saati 00:00 olacak
    }

    // Bir saat ilerlet
    public void saatIlerle() {
        saat++;
        if (saat >= gunSaat) {
            // Yeni bir gün başladı
            saat = 0;
            tarihIlerle();
        }
    }

    // Tarihi bir gün ileri al
    private void tarihIlerle() {
        String[] parts = tarih.split("\\.");
        int gun = Integer.parseInt(parts[0]);
        int ay = Integer.parseInt(parts[1]);
        int yil = Integer.parseInt(parts[2]);

        gun++;

        int[] ayGunSayisi = {31,28,31,30,31,30,31,31,30,31,30,31};

        if (gun > ayGunSayisi[ay - 1]) {
            gun = 1;
            ay++;
            if (ay > 12) {
                ay = 1;
                yil++;
            }
        }

        // DÜZELTME: Gün, Ay, Yıl her zaman çift haneli yazılacak!!
        this.tarih = String.format("%02d.%02d.%04d", gun, ay, yil);
    }


    // Getter'lar
    public String getAd() {
        return ad;
    }

    public int getGunSaat() {
        return gunSaat;
    }

    public String getTarih() {
        return tarih;
    }

    public int getSaat() {
        return saat;
    }

    @Override
    public String toString() {
        return ad + " - Tarih: " + tarih + " Saat: " + saat + ":00";
    }
}
