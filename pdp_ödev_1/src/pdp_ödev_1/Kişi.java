package pdp_ödev_1; 

/**
 * B231210089
 * Mustafa Arkan
 * 26.04.2025
 * 
 * Bu sınıf, bir kişinin bilgilerini (isim, yaş, kalan ömür, bulunduğu uzay aracı) 
 * ve ömrünün bitip bitmediğini kontrol etmek için kullanılır.
 * 
 */

public class Kişi {
    private String isim;
    private int yas;
    private int kalanOmur; // saat cinsinden
    private String uzayAraciAdi; // Bulunduğu uzay aracı adı (String olarak tutuluyor)

    private boolean hayatta; // kişi hayatta mı? True = hayatta, False = ölü

    // Constructor
    public Kişi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
        this.hayatta = true; // Başlangıçta herkes hayatta
    }

    // Her saat sonunda çağrılacak: kalan ömür bir azalır
    public void saatGecir() {
        if (hayatta && kalanOmur > 0) {
            kalanOmur--;
            if (kalanOmur == 0) {
                hayatta = false; // ömür bitince hayatta false olur
            }
        }
    }

    // Getter'lar
    public String getIsim() {
        return isim;
    }

    public int getYas() {
        return yas;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }

    public boolean isHayatta() {
        return hayatta;
    }

    // Setter (arac değişimi için gerekebilir)
    public void setUzayAraciAdi(String uzayAraciAdi) {
        this.uzayAraciAdi = uzayAraciAdi;
    }

    @Override
    public String toString() {
        return isim + " (" + yas + " yaşında) - Kalan ömür: " + kalanOmur + " saat - Uzay Aracı: " + uzayAraciAdi + (hayatta ? " (Hayatta)" : " (Ölü)");
    }
}
