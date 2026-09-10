package ee.sandra.veebipood.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronService {

    // CRON - kindlal kellaajal käivituv päring
    // näited:
    // andmebaasipuhastus kell 6 hommikul
    // kuu viimasel tööpäeval raporti tegemine
    // kuu viimasel päeval müükide kokkuvõte
    // iga täistund võta 24h pärast algavad broneeringud ja saada neile meeldetuletus
    // finantsrakendus -> võta iga 15 minuti tagant uuenenud hinnad

    // * --> sekundid 0-59
    // * * --> minutid 0-59
    // * * * --> tunnid 0-23
    // * * * * --> kuupäev 1-31
    // * * * * * --> kuu 1-12
    // * * * * * * --> nädalapäev 0-7, kus nii 0 kui ka 7 on pühapäev

    @Scheduled(cron = "0 0 9-17 * * 1-5")
    public void printEveryWorkdayHour() {
        System.out.println("Iga tööpäeva tund käivitub");
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void printAt6AM() {
        System.out.println("Iga päev andmebaasi puhastus kl 6 hommikul");
    }

    @Scheduled(cron = "0 0 18 LW * *")
    public void printAt18EveryLastWorkingDayOfMonth() {
        System.out.println("Kuu viimasel tööpäeval raporti tegemine");
    }

    @Scheduled(cron = "0 0 18 L * *")
    public void printAtEveryLastDayOfMonth() {
        System.out.println("Kuu viimasel päeval müükide kokkuvõte");
    }

    @Scheduled(cron = "0 0 8-20 * * 1-5")
    public void sendReminder() {
        System.out.println("Otsin andmebaasist kõik broneeringud üles ja saadan neile meeldetuletuse...");
    }

    @Scheduled(cron = "0 0 9-15 * * 6")
    public void sendReminder2() {
        System.out.println("Otsin andmebaasist kõik broneeringud üles ja saadan neile meeldetuletuse...");
    }

    @Scheduled(cron = "0 */15 10-16 * * *")
    public void takeFinancialData() {
        System.out.println("Võtan finantsandmed ja teen nedega midagi...(saadan front-endile)");
    }
}
