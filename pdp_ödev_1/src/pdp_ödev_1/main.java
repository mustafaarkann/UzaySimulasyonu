package pdp_ödev_1; 
import java.io.IOException;
import java.util.List;


/**
 *  B231210089
 *  Mustafa Arkan
 *  26.04.2025
 * 
 * Programın başlangıç noktasıdır. Dosyaları okur ve simülasyonu başlatır.
 * 
 */

public class main {
    public static void main(String[] args) throws IOException {
        // Dosyaları oku
        List<Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("data/Gezegenler.txt");
        List<UzayAracı> uzayAraclari = DosyaOkuma.araclariOku("data/Araclar.txt");
        List<Kişi> kisiler = DosyaOkuma.kisileriOku("data/Kisiler.txt");

        // Simülasyon nesnesini oluştur
        Simülasyon simulasyon = new Simülasyon(gezegenler, uzayAraclari, kisiler);

        // Simülasyonu başlat
        simulasyon.baslat();
    }
}
