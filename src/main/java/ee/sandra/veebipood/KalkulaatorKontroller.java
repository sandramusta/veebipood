package ee.sandra.veebipood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@RestController
public class KalkulaatorKontroller {

    List<Integer> integers = new ArrayList<>(Arrays.asList(31, 52, 24, 10, 85));

    @GetMapping("koik-numbrid") //See on veebiaadress
    public List<Integer> tagastaKoikNumbrid() {
        return integers;
    }

    @GetMapping("lisa-number/{uusNumber}")
    public List<Integer> uusNumber(@PathVariable Integer uusNumber) {
        integers.add(uusNumber); //Lisab numbri nimekirja
        return integers; //Tagastab nimekirja, et näeksin localhost serveris
    }

    @GetMapping("kustuta-number/{uusNumber}")
    public List<Integer> kustutaNumber(@PathVariable Integer uusNumber) {
        integers.remove(uusNumber); //Eemaldame numbri nimekirjast
        return integers; //Tagastab uuendatud nimekirja
    }

    @GetMapping("kustuta-koik")
    public List<Integer> kustutaKoik() {
        integers.clear(); //See käsk teeb listi täiesti tühjaks
        return integers;
    }

    @GetMapping("summa")
    public Integer arvutaSumma() {
        Integer summa = 0; //Alustame nullist
        for(Integer number : integers) { //Käime kõik numbrid läbi nimekirjas
            summa = summa + number; //Liidame kõik numbrid summale, mis on 0
        }
        return summa; //Tagastab ühe lõppsumma
    }

    @GetMapping("keskmine")
    public Double arvutaKeskmine() {
        double summa = 0;
        for (Integer number : integers) {
            summa = summa + number; //Arvutame kõigepealt numbrite summa
        }

        return summa / integers.size(); //Jagame selle summa numbrite arvuga
    }

    @GetMapping("numbrite-arv")
    public Integer mituNumbritOn() {
        return integers.size();
    }

    //Liitmine
    @GetMapping("liida/{arv1}/{arv2}")
    public Integer liida(@PathVariable Integer arv1, @PathVariable Integer arv2) {
        return arv1 + arv2;
    }

    //Lahutamine
    @GetMapping("lahuta/{arv1}/{arv2}")
    public Integer lahuta(@PathVariable Integer arv1, @PathVariable Integer arv2) {
        return arv1 - arv2;
    }

    //Korrutamine
    @GetMapping("korruta/{arv1}/{arv2}")
    public Integer korruta(@PathVariable Integer arv1, @PathVariable Integer arv2) {
        return arv1 * arv2;
    }

    //Jagamine
    @GetMapping("jaga/{arv1}/{arv2}")
    public Double jaga(@PathVariable Double arv1, @PathVariable Double arv2) {
        return arv1 / arv2;
    }

    //Juhuslike numbrite tagastus 10-50ni
    @GetMapping("suvaline-number")
    public Integer suvalineNumber() {
        Random kuju = new Random();
        int suvaline = kuju.nextInt(41) + 10;
        return suvaline;
    }

    //Juhuslikud numbrid minu poolt määratud numbrini
    @GetMapping("suvaline-kuni/{max}")
    public Integer suvalineKuni(@PathVariable Integer max) {
        Random kuju = new Random();
        return kuju.nextInt(max) + 1;
    }

}
