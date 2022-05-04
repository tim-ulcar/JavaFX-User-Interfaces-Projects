package sample;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PorociloController {
    public Label imeInPriimek;
    public Label naslov;
    public Label telefon;
    public Label email;
    public Label starost;
    public Label casOdPridobitveIzpita;
    public Label casPrevzetja;
    public Label krajPrevzetja;
    public Label casVrnitve;
    public Label krajVrnitve;
    public Label velikost;
    public Label model;
    public Label menjalnik;
    public Label gorivo;
    public Label dodatnoZavarovanje;
    public Label cena;
    public Label nacinPlacila;
    public Label stevilkaKartice;
    public GridPane gridPane;

    String imeShranjeno;
    String priimekShranjeno;
    String naslovShranjeno;
    String telefonShranjeno;
    String emailShranjeno;
    int starostShranjeno;
    int casOdPridobitveIzpitaShranjeno;
    LocalDate datumPrevzetjaShranjeno;
    LocalDate datumVrnitveShranjeno;
    String uraPrevzetjaShranjeno;
    String uraVrnitveShranjeno;
    String minutaPrevzetjaShranjeno;
    String minutaVrnitveShranjeno;
    String krajPrevzetjaShranjeno;
    String krajVrnitveShranjeno;
    String velikostShranjeno;
    String modelShranjeno;
    boolean samodejniShranjeno;
    boolean rocniShranjeno;
    boolean bencinShranjeno;
    boolean dizelShranjeno;
    boolean daShranjeno;
    boolean neShranjeno;
    String cenaShranjeno;
    boolean gotovinaObPrevzetjuShranjeno;
    boolean kreditnaKarticaShranjeno;
    String stevilkaKarticeShranjeno;

    File datotekaShranjeno;
    Scene primarySceneShranjeno;

    public void nastaviPodatke(String imeVpisano, String priimekVpisano, String naslovVpisano, String telefonVpisano, String emailVpisano, int starostVpisano,
                               int casOdPridobitveIzpitaVpisano, LocalDate datumPrevzetjaVpisano, LocalDate datumVrnitveVpisano,
                               String uraPrevzetjaVpisano, String uraVrnitveVpisano, String minutaPrevzetjaVpisano, String minutaVrnitveVpisano,
                               String krajPrevzetjaVpisano, String krajVrnitveVpisano, String velikostVpisano, String modelVpisano, boolean samodejniVpisano,
                               boolean rocniVpisano, boolean bencinVpisano, boolean dizelVpisano, boolean daVpisano, boolean neVpisano, String cenaVpisano, boolean gotovinaObPrevzetjuVpisano,
                               boolean kreditnaKarticaVpisano, String stevilkaKarticeVpisano, File datoteka, Scene primaryScene) {

        imeInPriimek.setText(imeVpisano + " " + priimekVpisano);
        naslov.setText(naslovVpisano);
        telefon.setText(telefonVpisano);
        email.setText(emailVpisano);
        starost.setText("Starost: " + Integer.toString(starostVpisano) + " let");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");
        String datumPrevzetjaFormatiran = datumPrevzetjaVpisano.format(formatter);
        String datumVrnitveFormatiran = datumVrnitveVpisano.format(formatter);

        casOdPridobitveIzpita.setText("Čas od pridobitve izpita: " + Integer.toString(casOdPridobitveIzpitaVpisano) + " let");
        casPrevzetja.setText("Čas prevzetja: " + datumPrevzetjaFormatiran + " ob " + uraPrevzetjaVpisano + ":" + minutaPrevzetjaVpisano);
        krajPrevzetja.setText("Kraj prevzetja: " + krajPrevzetjaVpisano);
        casVrnitve.setText("Čas vrnitve: " + datumVrnitveFormatiran + " ob " + uraVrnitveVpisano + ":" + minutaVrnitveVpisano);
        krajVrnitve.setText("Kraj vrnitve: " + krajVrnitveVpisano);
        velikost.setText("Velikost: " + velikostVpisano);
        model.setText("Model: " + modelVpisano);
        if (samodejniVpisano) {
            menjalnik.setText("Menjalnik: samodejni");
        }
        else {
            menjalnik.setText("Menjalnik: ročni");
        }
        if (bencinVpisano) {
            gorivo.setText("Gorivo: bencin");
        }
        else {
            gorivo.setText("Gorivo: dizel");
        }
        if (daVpisano) {
            dodatnoZavarovanje.setText("Dodatno zavarovanje: da");
        }
        else {
            dodatnoZavarovanje.setText("Dodatno zavarovanje: ne");
        }
        cena.setText("Cena: " + cenaVpisano);
        if (gotovinaObPrevzetjuVpisano) {
            nacinPlacila.setText("Način plačila: gotovina ob prevzetju");
            stevilkaKartice.setText("");
        }
        else {
            nacinPlacila.setText("Način plačila: kreditna kartica");
            stevilkaKartice.setText("Številka kartice: XXXX-XXXX-XXXX-" + stevilkaKarticeVpisano.split("-")[3]);
        }


        imeShranjeno = imeVpisano;
        priimekShranjeno  = priimekVpisano;
        naslovShranjeno = naslovVpisano;
        telefonShranjeno = telefonVpisano;
        emailShranjeno  = emailVpisano;
        starostShranjeno = starostVpisano;
        casOdPridobitveIzpitaShranjeno = casOdPridobitveIzpitaVpisano;
        datumPrevzetjaShranjeno = datumPrevzetjaVpisano;
        datumVrnitveShranjeno = datumVrnitveVpisano;
        uraPrevzetjaShranjeno = uraPrevzetjaVpisano;
        uraVrnitveShranjeno = uraVrnitveVpisano;
        minutaPrevzetjaShranjeno = minutaPrevzetjaVpisano;
        minutaVrnitveShranjeno = minutaVrnitveVpisano;
        krajPrevzetjaShranjeno = krajPrevzetjaVpisano;
        krajVrnitveShranjeno = krajVrnitveVpisano;
        velikostShranjeno = velikostVpisano;
        modelShranjeno = modelVpisano;
        samodejniShranjeno = samodejniVpisano;
        rocniShranjeno = rocniVpisano;
        bencinShranjeno = bencinVpisano;
        dizelShranjeno = dizelVpisano;
        daShranjeno = daVpisano;
        neShranjeno = neVpisano;
        cenaShranjeno = cenaVpisano;
        gotovinaObPrevzetjuShranjeno = gotovinaObPrevzetjuVpisano;
        kreditnaKarticaShranjeno = kreditnaKarticaVpisano;
        stevilkaKarticeShranjeno = stevilkaKarticeVpisano;

        datotekaShranjeno = datoteka;
        primarySceneShranjeno = primaryScene;
    }

    public void zapisiVBazo(ActionEvent actionEvent) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(datotekaShranjeno, true))) {
            bufferedWriter.append("ime:"+imeShranjeno+"\n");
            bufferedWriter.append("priimek:"+priimekShranjeno+"\n");
            bufferedWriter.append("naslov:"+naslovShranjeno+"\n");
            bufferedWriter.append("telefon:"+telefonShranjeno+"\n");
            bufferedWriter.append("email:"+emailShranjeno+"\n");
            bufferedWriter.append("starost:"+starostShranjeno+"\n");
            bufferedWriter.append("casOdPridobitveIzpita:"+casOdPridobitveIzpitaShranjeno+"\n");
            bufferedWriter.append("datumPrevzetja:"+datumPrevzetjaShranjeno+"\n");
            bufferedWriter.append("datumVrnitve:"+datumVrnitveShranjeno+"\n");
            bufferedWriter.append("uraPrevzetja:"+uraPrevzetjaShranjeno+"\n");
            bufferedWriter.append("uraVrnitve:"+uraVrnitveShranjeno+"\n");
            bufferedWriter.append("minutaPrevzetja:"+minutaPrevzetjaShranjeno+"\n");
            bufferedWriter.append("minutaVrnitve:"+minutaVrnitveShranjeno+"\n");
            bufferedWriter.append("krajPrevzetja:"+krajPrevzetjaShranjeno+"\n");
            bufferedWriter.append("krajVrnitve:"+krajVrnitveShranjeno+"\n");
            bufferedWriter.append("velikost:"+velikostShranjeno+"\n");
            bufferedWriter.append("model:"+modelShranjeno+"\n");
            bufferedWriter.append("samodejni:"+samodejniShranjeno+"\n");
            bufferedWriter.append("rocni:"+rocniShranjeno+"\n");
            bufferedWriter.append("bencin:"+bencinShranjeno+"\n");
            bufferedWriter.append("dizel:"+dizelShranjeno+"\n");
            bufferedWriter.append("da:"+daShranjeno+"\n");
            bufferedWriter.append("ne:"+neShranjeno+"\n");
            bufferedWriter.append("gotovinaObPrevzetju:"+gotovinaObPrevzetjuShranjeno+"\n");
            bufferedWriter.append("kreditnaKartica:"+kreditnaKarticaShranjeno+"\n");
            if (kreditnaKarticaShranjeno) {
                bufferedWriter.append("stevilkaKartice:XXXX-XXXX-XXXX-"+stevilkaKarticeShranjeno.split("-")[3]+"\n");
            }
            else {
                bufferedWriter.append("stevilkaKartice:XXXX-XXXX-XXXX-XXXX\n");
            }
            bufferedWriter.append("\n");

            bufferedWriter.close();

            Label stanje = (Label) primarySceneShranjeno.lookup("#stanje");
            stanje.setText("Stanje: Podatki za " + imeShranjeno + " " + priimekShranjeno + " uspešno shranjeni.");
        } catch (Exception e) {
            Label stanje = (Label) primarySceneShranjeno.lookup("#stanje");
            stanje.setText("Stanje: Napaka pri shranjevanju datoteke.");
        }

        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
    }

    public void zapriPovzetek(ActionEvent actionEvent) {
        Label stanje = (Label) primarySceneShranjeno.lookup("#stanje");
        stanje.setText("Stanje: Preklical shranjevanje datoteke.");
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
    }
}
