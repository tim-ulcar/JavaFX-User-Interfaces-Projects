package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    public TextField ime;
    public TextField priimek;
    public TextField ulica;
    public TextField hisnaStevilka;
    public TextField kraj;
    public TextField postnaStevilka;
    public DatePicker datumRojstva;
    public RadioButton mladVoznik;
    public RadioButton izkusenVoznik;
    public ChoiceBox vrstaVozila;
    public Spinner steviloSedezev;
    public TextField znamka;
    public TextField oznaka;
    public Spinner moc;
    public Spinner prostornina;
    public TextField barva;
    public TextField gorivo;
    public RadioButton ao;
    public RadioButton aoPlus;
    public RadioButton polno;
    public RadioButton odbitnaFaza;
    public RadioButton brez;
    public CheckBox stekel;
    public CheckBox potnikov;
    public CheckBox tretjeOsebe;
    public CheckBox gum;
    public CheckBox protiKraji;
    public CheckBox protiToci;
    public DatePicker datumPrveRegistracije;
    public Button naloziUporabnika;
    public TextField registrskaOznacba;
    public Button shrani;
    public Button pobrisiVse;
    public TextField krajPrveRegistracije;
    public Button izberiPodatkovnoBazo;
    public File datoteka;
    public Label stanje;
    public Label podatkovnaBaza;
    public Label imeLabel;
    public Label priimekLabel;
    public Label ulicaLabel;
    public Label hisnaStevilkaLabel;
    public Label krajLabel;
    public Label postnaStevilkaLabel;
    public Label znamkaLabel;
    public Label oznakaLabel;
    public Label barvaLabel;
    public Label gorivoLabel;
    public Label registrskaOznacbaLabel;
    public Label krajPrveRegistracijeLabel;
    public Label napakeLabel;
    public GridPane gridPane;
    public Label steviloSedezevLabel;
    public Label mocLabel;
    public Label prostorninaLabel;
    public Label datumRojstvaLabel;
    public Label datumPrveRegistracijeLabel;
    public Label podatkiOZavarovancuLabel;
    public Label podatkiOVoziluLabel;
    public Label podatkiOZavarovanjuLabel;
    public Label podatkiORegistracijiLabel;

    private DoubleProperty fontSize = new SimpleDoubleProperty(10);

    public void shraniCB(ActionEvent actionEvent) {
        pobarvajVseLabeleCrno();
        boolean somethingIsEmpty = false;
        String errors = "";
        if (ime.getText().isEmpty()) {
            imeLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            imeLabel.setStyle("-fx-text-fill: #000000");
        }
        if (priimek.getText().isEmpty()) {
            priimekLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            priimekLabel.setStyle("-fx-text-fill: #000000");
        }
        if (ulica.getText().isEmpty()) {
            ulicaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            ulicaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (hisnaStevilka.getText().isEmpty()) {
            hisnaStevilkaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else if (!hisnaStevilka.getText().matches("\\d+ *[a-zA-Z]*")) {
            hisnaStevilkaLabel.setStyle("-fx-text-fill: #de1d1d");
            errors = errors + "Hišna številka - Dovoljeno je le število in morebitna ena črka. ";
            somethingIsEmpty = true;
        }
        else {
            hisnaStevilkaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (kraj.getText().isEmpty()) {
            krajLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            krajLabel.setStyle("-fx-text-fill: #000000");
        }
        if (postnaStevilka.getText().isEmpty()) {
            postnaStevilkaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else if (!postnaStevilka.getText().matches("\\d+") ) {
            postnaStevilkaLabel.setStyle("-fx-text-fill: #de1d1d");
            errors = errors + "Poštna številka - Dovoljena so le števila. ";
            somethingIsEmpty = true;
        }
        else {
            postnaStevilkaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (znamka.getText().isEmpty()) {
            znamkaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            znamkaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (oznaka.getText().isEmpty()) {
            oznakaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            oznakaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (barva.getText().isEmpty()) {
            barvaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            barvaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (gorivo.getText().isEmpty()) {
            gorivoLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            gorivoLabel.setStyle("-fx-text-fill: #000000");
        }
        if (registrskaOznacba.getText().isEmpty()) {
            registrskaOznacbaLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            registrskaOznacbaLabel.setStyle("-fx-text-fill: #000000");
        }
        if (krajPrveRegistracije.getText().isEmpty()) {
            krajPrveRegistracijeLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else {
            krajPrveRegistracijeLabel.setStyle("-fx-text-fill: #000000");
        }
        if (!steviloSedezev.getValue().toString().matches("\\d+")) {
            steviloSedezevLabel.setStyle("-fx-text-fill: #de1d1d");
            errors = errors + "Število sedežev - Dovoljena so le števila. ";
            somethingIsEmpty = true;
        }


        if (!datumRojstva.getEditor().getText().matches("\\d+\\. \\d+\\. \\d+")) {
            datumRojstvaLabel.setStyle("-fx-text-fill: #de1d1d");
            errors = errors + "Datum rojstva - izberite pravilen datum. ";
            somethingIsEmpty = true;
        }
        else {
            String[] razbitje = datumRojstva.getEditor().getText().split(". ");
            String datumUrejen = razbitje[2] + "-" + razbitje[1] + "-" + razbitje[0];
            if (!isValidDate(datumUrejen)) {
                datumRojstvaLabel.setStyle("-fx-text-fill: #de1d1d");
                errors = errors + "Datum rojstva - izberite pravilen datum. ";
                somethingIsEmpty = true;
            }
        }
        if (!datumPrveRegistracije.getEditor().getText().matches("\\d+\\. \\d+\\. \\d+")) {
            datumPrveRegistracijeLabel.setStyle("-fx-text-fill: #de1d1d");
            errors = errors + "Datum prve registracije - izberite pravilen datum. ";
            somethingIsEmpty = true;
        }
        else {
            String[] razbitje = datumPrveRegistracije.getEditor().getText().split(". ");
            String datumUrejen = razbitje[2] + "-" + razbitje[1] + "-" + razbitje[0];
            if (!isValidDate(datumUrejen)) {
                datumPrveRegistracijeLabel.setStyle("-fx-text-fill: #de1d1d");
                errors = errors + "Datum prve registracije - izberite pravilen datum. ";
                somethingIsEmpty = true;
            }
        }

        if (!errors.isEmpty()) {
            napakeLabel.setText("Napake: " + errors);
            napakeLabel.setOpacity(1.0);
        }
        else {
            napakeLabel.setOpacity(0.0);
        }
        if (somethingIsEmpty) {
            stanje.setText("Stanje: neuspešno shranjevanje - manjkajo rdeče označeni podatki.");
            return;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(datoteka, true))) {
            bufferedWriter.append("ime:"+ime.getText()+"\n");
            bufferedWriter.append("priimek:"+priimek.getText()+"\n");
            bufferedWriter.append("ulica:"+ulica.getText()+"\n");
            bufferedWriter.append("hisnaStevilka:"+hisnaStevilka.getText()+"\n");
            bufferedWriter.append("kraj:"+kraj.getText()+"\n");
            bufferedWriter.append("postnaStevilka:"+postnaStevilka.getText()+"\n");
            bufferedWriter.append("datumRojstva:"+datumRojstva.getValue().toString()+"\n");
            bufferedWriter.append("mladVoznik:"+ mladVoznik.isSelected()+"\n");
            bufferedWriter.append("izkusenVoznik:"+ izkusenVoznik.isSelected()+"\n");
            bufferedWriter.append("vrstaVozila:"+vrstaVozila.getValue().toString()+"\n");
            bufferedWriter.append("steviloSedezev:"+ steviloSedezev.getValue()+"\n");
            bufferedWriter.append("znamka:"+znamka.getText()+"\n");
            bufferedWriter.append("oznaka:"+oznaka.getText()+"\n");
            bufferedWriter.append("moc:"+moc.getValue()+"\n");
            bufferedWriter.append("prostornina:"+prostornina.getValue()+"\n");
            bufferedWriter.append("barva:"+barva.getText()+"\n");
            bufferedWriter.append("gorivo:"+gorivo.getText()+"\n");
            bufferedWriter.append("ao:"+ao.isSelected()+"\n");
            bufferedWriter.append("aoPlus:"+aoPlus.isSelected()+"\n");
            bufferedWriter.append("polno:"+polno.isSelected()+"\n");
            bufferedWriter.append("odbitnaFaza:"+odbitnaFaza.isSelected()+"\n");
            bufferedWriter.append("brez:"+brez.isSelected()+"\n");
            bufferedWriter.append("stekel:"+stekel.isSelected()+"\n");
            bufferedWriter.append("potnikov:"+potnikov.isSelected()+"\n");
            bufferedWriter.append("tretjeOsebe:"+tretjeOsebe.isSelected()+"\n");
            bufferedWriter.append("gum:"+gum.isSelected()+"\n");
            bufferedWriter.append("protiKraji:"+protiKraji.isSelected()+"\n");
            bufferedWriter.append("protiToci:"+protiToci.isSelected()+"\n");
            bufferedWriter.append("datumPrveRegistracije:"+datumPrveRegistracije.getValue().toString()+"\n");
            bufferedWriter.append("registrskaOznacba:"+registrskaOznacba.getText()+"\n");
            bufferedWriter.append("krajPrveRegistracije:"+krajPrveRegistracije.getText()+"\n");
            bufferedWriter.append("\n");

            bufferedWriter.close();
            stanje.setText("Stanje: Podatki za " + ime.getText() + " " + priimek.getText() + " uspešno shranjeni.");
        } catch (Exception e) {
            stanje.setText("Stanje: Napaka pri shranjevanju datoteke.");
        }

    }


    public static boolean isValidDate(String input) {
        String formatString = "yyyy-MM-dd";

        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(input);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }


    public void pobrisiVseCB(ActionEvent actionEvent) {

        Stage stage = (Stage) gridPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("");
        alert.setHeaderText("Ali res želite izbrisati vse vnose?");
        alert.setTitle("Potrdi brisanje vnosov");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Pobriši");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Prekliči");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            return;
        }

        ime.setText("");
        priimek.setText("");
        ulica.setText("");
        hisnaStevilka.setText("");
        kraj.setText("");
        postnaStevilka.setText("");
        datumRojstva.setValue(LocalDate.of(1980, 1, 1));
        mladVoznik.selectedProperty().set(true);
        izkusenVoznik.selectedProperty().set(false);
        vrstaVozila.setValue("Osebni avto");
        steviloSedezev.getValueFactory().setValue(5);
        znamka.setText("");
        oznaka.setText("");
        moc.getValueFactory().setValue(100);
        prostornina.getValueFactory().setValue(3000);
        barva.setText("");
        gorivo.setText("");
        ao.selectedProperty().set(true);
        aoPlus.selectedProperty().set(false);
        polno.selectedProperty().set(true);
        odbitnaFaza.selectedProperty().set(false);
        brez.selectedProperty().set(false);
        stekel.selectedProperty().set(false);
        potnikov.selectedProperty().set(false);
        tretjeOsebe.selectedProperty().set(false);
        gum.selectedProperty().set(false);
        protiKraji.selectedProperty().set(false);
        protiToci.selectedProperty().set(false);
        datumPrveRegistracije.setValue(LocalDate.of(2010, 1, 1));
        registrskaOznacba.setText("");
        krajPrveRegistracije.setText("");

        pobarvajVseLabeleCrno();

        napakeLabel.setOpacity(0.0);
        stanje.setText("Stanje: uspešno izbrisal vse podatke in nastavil privzete vrednosti.");
    }

    public void pobarvajVseLabeleCrno() {
        imeLabel.setStyle("-fx-text-fill: #000000");
        priimekLabel.setStyle("-fx-text-fill: #000000");
        ulicaLabel.setStyle("-fx-text-fill: #000000");
        hisnaStevilkaLabel.setStyle("-fx-text-fill: #000000");
        krajLabel.setStyle("-fx-text-fill: #000000");
        postnaStevilkaLabel.setStyle("-fx-text-fill: #000000");
        znamkaLabel.setStyle("-fx-text-fill: #000000");
        oznakaLabel.setStyle("-fx-text-fill: #000000");
        barvaLabel.setStyle("-fx-text-fill: #000000");
        gorivoLabel.setStyle("-fx-text-fill: #000000");
        registrskaOznacbaLabel.setStyle("-fx-text-fill: #000000");
        krajPrveRegistracijeLabel.setStyle("-fx-text-fill: #000000");
        datumRojstvaLabel.setStyle("-fx-text-fill: #000000");
        datumPrveRegistracijeLabel.setStyle("-fx-text-fill: #000000");
    }

    public void izberiPodatkovnoBazoCB(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Izberi podatkovno bazo");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            datoteka = file;
            podatkovnaBaza.setText("Podatkovna baza: " + datoteka.getName());
        }
    }

    public void zapriCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void avtorCB(ActionEvent actionEvent) {
        stanje.setText("Avtor: Tim Ulčar Pertot");
    }

    public void naloziUporabnikaCB(ActionEvent actionEvent) {

        if (ime.getText().isBlank() || priimek.getText().isBlank()) {
            stanje.setText("Stanje: Vpiši ime in priimek v polji za vnos za nalaganje uporabnika.");
            return;
        }

        try {
            boolean uporabnikNajden = false;
            Scanner sc = new Scanner(datoteka);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                String[] razbitje = line.split(":");
                String key = razbitje[0];
                String value = razbitje[1];
                HashMap<String, String> slovar = new HashMap<>();
                if (key.equals("ime")) {
                    slovar.put(key, value);
                    for (int i = 0; i < 30; i++) {
                        line = sc.nextLine();
                        razbitje = line.split(":");
                        key = razbitje[0];
                        value = razbitje[1];
                        slovar.put(key, value);
                    }
                }

                if (slovar.get("ime").equals(ime.getText()) && slovar.get("priimek").equals(priimek.getText())) {
                    uporabnikNajden = true;
                    ulica.setText(slovar.get("ulica"));
                    hisnaStevilka.setText(slovar.get("hisnaStevilka"));
                    kraj.setText(slovar.get("kraj"));
                    postnaStevilka.setText(slovar.get("postnaStevilka"));
                    datumRojstva.setValue(LocalDate.parse(slovar.get("datumRojstva")));
                    mladVoznik.selectedProperty().set(Boolean.parseBoolean(slovar.get("mladVoznik")));
                    izkusenVoznik.selectedProperty().set(Boolean.parseBoolean(slovar.get("izkusenVoznik")));
                    vrstaVozila.setValue(slovar.get("vrstaVozila"));
                    steviloSedezev.getValueFactory().setValue(Integer.parseInt(slovar.get("steviloSedezev")));
                    znamka.setText(slovar.get("znamka"));
                    oznaka.setText(slovar.get("oznaka"));
                    moc.getValueFactory().setValue(Integer.parseInt(slovar.get("moc")));
                    prostornina.getValueFactory().setValue(Integer.parseInt(slovar.get("prostornina")));
                    barva.setText(slovar.get("barva"));
                    gorivo.setText(slovar.get("gorivo"));
                    ao.selectedProperty().set(Boolean.parseBoolean(slovar.get("ao")));
                    aoPlus.selectedProperty().set(Boolean.parseBoolean(slovar.get("aoPlus")));
                    polno.selectedProperty().set(Boolean.parseBoolean(slovar.get("polno")));
                    odbitnaFaza.selectedProperty().set(Boolean.parseBoolean(slovar.get("odbitnaFaza")));
                    brez.selectedProperty().set(Boolean.parseBoolean(slovar.get("brez")));
                    stekel.selectedProperty().set(Boolean.parseBoolean(slovar.get("stekel")));
                    potnikov.selectedProperty().set(Boolean.parseBoolean(slovar.get("potnikov")));
                    tretjeOsebe.selectedProperty().set(Boolean.parseBoolean(slovar.get("tretjeOsebe")));
                    gum.selectedProperty().set(Boolean.parseBoolean(slovar.get("gum")));
                    protiKraji.selectedProperty().set(Boolean.parseBoolean(slovar.get("protiKraji")));
                    protiToci.selectedProperty().set(Boolean.parseBoolean(slovar.get("protiToci")));
                    datumPrveRegistracije.setValue(LocalDate.parse(slovar.get("datumPrveRegistracije")));
                    registrskaOznacba.setText(slovar.get("registrskaOznacba"));
                    krajPrveRegistracije.setText(slovar.get("krajPrveRegistracije"));

                    break;
                }
            }
            sc.close();
            if (uporabnikNajden) {
                stanje.setText("Stanje: Uspešno naloženi podatki za " + ime.getText() + " " + priimek.getText());
            }
            else {
                stanje.setText("Stanje: Uporabnika " + ime.getText() + " " + priimek.getText() + " ni v podatkovni bazi.");
            }
        } catch (Exception e) {
            stanje.setText("Stanje: Napaka pri branju datoteke.");
        }
    }

    public void makeFontResponsive() {
        Scene scene = gridPane.getScene();
        fontSize.bind(scene.widthProperty().add(scene.heightProperty()).divide(130));
        gridPane.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        stanje.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-style: italic;"));
        podatkovnaBaza.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-style: italic;"));
        podatkiOZavarovancuLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));
        podatkiOVoziluLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));
        podatkiOZavarovanjuLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));
        podatkiORegistracijiLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datoteka = new File("zavarovanja2021.txt");

        datumRojstva.setValue(LocalDate.of(1980, 1, 1));
        vrstaVozila.getItems().addAll("Osebni avto", "Motor", "Tovornjak", "Traktor", "Avtobus");
        vrstaVozila.setValue("Osebni avto");
        steviloSedezev.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 5));
        moc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 100));
        prostornina.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999, 3000));
        datumPrveRegistracije.setValue(LocalDate.of(2010, 1, 1));

        Platform.runLater(()->makeFontResponsive());
    }
}
