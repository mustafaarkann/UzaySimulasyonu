package pdp_ödev_1;

import java.util.ArrayList;
import java.util.List;


/**
 *  B231210089
 *  Mustafa Arkan
 *  26.04.2025
 * 
 * Uzay aracı bilgilerini tutar, hareket ve imha kontrollerini yapar.
 * 
 */

public class UzayAracı {
    private String ad;
    private String cikisGezegeni;
    private String varisGezegeni;
    private String cikisTarihi;
    private int mesafeSaat;

    private boolean hareketEttiMi;
    private boolean imhaOlduMu;
    private int hedefeKalanSaat;

    private String hedefeVarisTarihi; //  Hareket başladığında hesaplanacak!
    
    private List<Kişi> yolcular = new ArrayList<>();

    public UzayAracı(String ad, String cikisGezegeni, String varisGezegeni, String cikisTarihi, int mesafeSaat) {
        this.ad = ad;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafeSaat = mesafeSaat;

        this.hareketEttiMi = false;
        this.imhaOlduMu = false;
        this.hedefeKalanSaat = mesafeSaat;
        this.hedefeVarisTarihi = "--"; // Başlangıçta "--" olsun
    }

    public void hareketBaslat(Gezegen varisGezegeni) {
        this.hareketEttiMi = true;
        // Hedefe varış tarihi hareket başlayınca hesaplanır
        this.hedefeVarisTarihi = Zaman.ileriTarihHesapla(varisGezegeni.getTarih(), varisGezegeni.getGunSaat(), hedefeKalanSaat);
    }
    
    public void yolcuEkle(Kişi kisi) {
        yolcular.add(kisi);
    }

    public void saatGecir() {
        if (hareketEttiMi && !imhaOlduMu && hedefeKalanSaat > 0) {
            hedefeKalanSaat--;
        }
    }

    public void imhaKontrolu() {
        boolean hepsiOlmus = true;
        for (Kişi kisi : yolcular) {
            if (kisi.isHayatta()) {
                hepsiOlmus = false;
                break;
            }
        }
        if (hepsiOlmus) {
            this.imhaOlduMu = true;
        }
    }
    
    


    // Getter'lar
    public String getAd() { return ad; }
    public String getCikisGezegeni() { return cikisGezegeni; }
    public String getVarisGezegeni() { return varisGezegeni; }
    public String getCikisTarihi() { return cikisTarihi; }
    public int getMesafeSaat() { return mesafeSaat; }
    public boolean isHareketEttiMi() { return hareketEttiMi; }
    public boolean isImhaOlduMu() { return imhaOlduMu; }
    public int getHedefeKalanSaat() { return hedefeKalanSaat; }
    public String getHedefeVarisTarihi() { return hedefeVarisTarihi; }
}
