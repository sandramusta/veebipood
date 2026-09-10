package ee.sandra.veebipood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SonadeKontroller {

    //Sonade list loomine
    List<String> sonad = new ArrayList<>(Arrays.asList("Meta", "Shopify", "UGC", "Reklaam"));

    @GetMapping("koik-sonad")
    public List<String> kuvaKoikSonad() {
        return sonad;
    }

    //Lisame ühe sona juurde
    @GetMapping("lisa-sona/{uusSona}")
    public List<String> lisaSona(@PathVariable String uusSona) {
        sonad.add(uusSona);
        return sonad;
    }

    //Eemaldame sona
    @GetMapping("eemalda-sona/{sona}")
    public List<String> eemaldaSona(@PathVariable String sona) {
        sonad.remove(sona);
        return sonad;
    }

    //Tähemärkide kokku arvutamine
    @GetMapping("sonade-pikkus-kokku")
    public Integer arvutaSonadePikkusKokku() {
        int kokku = 0;
                for (String s : sonad) {
                    kokku = kokku + s.length();
                }
                return kokku;
    }

    //Keskmine tähemärkide arv
    @GetMapping("sonade-pikkus-keskmine")
    public Double arvutaKeskmineSonadePikkus() {
        if (sonad.isEmpty()) return 0.0;
        double kokku = 0;
        for (String s : sonad) {
            kokku += s.length();
        }
        return kokku / sonad.size();
    }
}
