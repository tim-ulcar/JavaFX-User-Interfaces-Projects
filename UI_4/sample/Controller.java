package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    public Label imeLabel;
    public TextField ime;
    public Label telefonLabel;
    public TextField telefon;
    public Spinner starost;
    public Label priimekLabel;
    public TextField priimek;
    public Label emailLabel;
    public TextField email;
    public Label naslovLabel;
    public TextField naslov;
    public Spinner casOdPridobitveIzpita;
    public DatePicker datumPrevzetja;
    public ChoiceBox uraPrevzetja;
    public ChoiceBox minutaPrevzetja;
    public ChoiceBox krajPrevzetja;
    public DatePicker datumVrnitve;
    public ChoiceBox uraVrnitve;
    public ChoiceBox minutaVrnitve;
    public ChoiceBox krajVrnitve;
    public ChoiceBox velikost;
    public ChoiceBox model;
    public RadioButton samodejni;
    public RadioButton rocni;
    public RadioButton bencin;
    public RadioButton dizel;
    public RadioButton da;
    public RadioButton ne;
    public RadioButton gotovinaObPrevzetju;
    public RadioButton kreditnaKartica;
    public Label stevilkaKarticeLabel;
    public TextField stevilkaKartice;
    public Label ccvLabel;
    public TextField ccv;
    public Label stanje;
    public Label podatkovnaBaza;
    public File datoteka;
    public Label cena;
    public GridPane gridPane;
    public Label napakaNaslov;
    public Label napakaTelefon;
    public Label napakaEmail;
    public Label napakaStevilkaKartice;
    public Label napakaCCV;
    public Label stranka;
    public Label casInKrajPrevzetjaTerVrnitve;
    public Label avtomobil;
    public Label nacinPlacilaNaslov;
    public Button naloziStrankoButton;
    public Button shraniButton;
    public Button pobrisiVseButton;
    public Button podatkovnaBazaButton;

    private DoubleProperty fontSize = new SimpleDoubleProperty(10);


    public void shraniCB(ActionEvent actionEvent) throws Exception {
        pobarvajVseLabeleCrno();
        boolean somethingIsEmpty = false;

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

        if (naslov.getText().isEmpty()) {
            naslovLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else if (!naslov.getText().matches("[a-zA-ZčšžČŠŽ ]+ \\d+")) {
            naslovLabel.setStyle("-fx-text-fill: #de1d1d");
            napakaNaslov.setVisible(true);
            somethingIsEmpty = true;
        }
        else {
            naslovLabel.setStyle("-fx-text-fill: #000000");
            napakaNaslov.setVisible(false);
        }

        if (telefon.getText().isEmpty()) {
            telefonLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else if (!telefon.getText().matches("\\+*\\d+")) {
            telefonLabel.setStyle("-fx-text-fill: #de1d1d");
            napakaTelefon.setVisible(true);
            somethingIsEmpty = true;
        }
        else {
            telefonLabel.setStyle("-fx-text-fill: #000000");
            napakaTelefon.setVisible(false);
        }

        if (email.getText().isEmpty()) {
            emailLabel.setStyle("-fx-text-fill: #de1d1d");
            somethingIsEmpty = true;
        }
        else if (!email.getText().matches("[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+.[a-zA-Z0-9_.-]+") ) {
            emailLabel.setStyle("-fx-text-fill: #de1d1d");
            napakaEmail.setVisible(true);
            somethingIsEmpty = true;
        }
        else {
            emailLabel.setStyle("-fx-text-fill: #000000");
            napakaEmail.setVisible(false);
        }

        if (kreditnaKartica.isSelected()) {
            if (stevilkaKartice.getText().isEmpty()) {
                stevilkaKarticeLabel.setStyle("-fx-text-fill: #de1d1d");
                somethingIsEmpty = true;
            }
            else if (!stevilkaKartice.getText().matches("[1-9]{4}-[1-9]{4}-[1-9]{4}-[1-9]{4}")) {
                stevilkaKarticeLabel.setStyle("-fx-text-fill: #de1d1d");
                napakaStevilkaKartice.setVisible(true);
                somethingIsEmpty = true;
            }
            else {
                stevilkaKarticeLabel.setStyle("-fx-text-fill: #000000");
                napakaStevilkaKartice.setVisible(false);
            }

            if (ccv.getText().isEmpty()) {
                ccvLabel.setStyle("-fx-text-fill: #de1d1d");
                somethingIsEmpty = true;
            }
            else if (!ccv.getText().matches("[1-9]{3}")) {
                ccvLabel.setStyle("-fx-text-fill: #de1d1d");
                napakaCCV.setVisible(true);
                somethingIsEmpty = true;
            }
            else {
                ccvLabel.setStyle("-fx-text-fill: #000000");
                napakaCCV.setVisible(false);
            }
        }


        if (somethingIsEmpty) {
            stanje.setText("Stanje: neuspešno shranjevanje - manjkajo rdeče označeni podatki.");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("porocilo.fxml"));
        Parent root = fxmlLoader.load();

        PorociloController porociloController = fxmlLoader.getController();
        porociloController.nastaviPodatke(ime.getText(), priimek.getText(), naslov.getText(), telefon.getText(), email.getText(),
                (int) starost.getValue(), (int) casOdPridobitveIzpita.getValue(), datumPrevzetja.getValue(), datumVrnitve.getValue(),
                (String) uraPrevzetja.getValue(), (String) uraVrnitve.getValue(), (String) minutaPrevzetja.getValue(), (String) minutaVrnitve.getValue(),
                (String) krajPrevzetja.getValue(), (String) krajVrnitve.getValue(), (String) velikost.getValue(), (String) model.getValue(), samodejni.isSelected(),
                rocni.isSelected(), bencin.isSelected(), dizel.isSelected(), da.isSelected(), ne.isSelected(), cena.getText().split(" ")[1], gotovinaObPrevzetju.isSelected(),
                kreditnaKartica.isSelected(), stevilkaKartice.getText(), datoteka, gridPane.getScene());

        Stage stage = new Stage();
        stage.setTitle("Povzetek izposoje");
        stage.setScene(new Scene(root, 300, 550));
        stage.show();
    }


    public void zapisiVBazo() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(datoteka, true))) {
            bufferedWriter.append("ime:"+ime.getText()+"\n");
            bufferedWriter.append("priimek:"+priimek.getText()+"\n");
            bufferedWriter.append("naslov:"+naslov.getText()+"\n");
            bufferedWriter.append("telefon:"+telefon.getText()+"\n");
            bufferedWriter.append("email:"+email.getText()+"\n");
            bufferedWriter.append("starost:"+starost.getValue()+"\n");
            bufferedWriter.append("casOdPridobitveIzpita:"+casOdPridobitveIzpita.getValue()+"\n");
            bufferedWriter.append("datumPrevzetja:"+datumPrevzetja.getValue().toString()+"\n");
            bufferedWriter.append("datumVrnitve:"+datumVrnitve.getValue().toString()+"\n");
            bufferedWriter.append("uraPrevzetja:"+uraPrevzetja.getValue()+"\n");
            bufferedWriter.append("uraVrnitve:"+uraVrnitve.getValue()+"\n");
            bufferedWriter.append("minutaPrevzetja:"+minutaPrevzetja.getValue()+"\n");
            bufferedWriter.append("minutaVrnitve:"+minutaVrnitve.getValue()+"\n");
            bufferedWriter.append("krajPrevzetja:"+krajPrevzetja.getValue()+"\n");
            bufferedWriter.append("krajVrnitve:"+krajVrnitve.getValue()+"\n");
            bufferedWriter.append("velikost:"+velikost.getValue()+"\n");
            bufferedWriter.append("model:"+model.getValue()+"\n");
            bufferedWriter.append("samodejni:"+samodejni.isSelected()+"\n");
            bufferedWriter.append("rocni:"+rocni.isSelected()+"\n");
            bufferedWriter.append("bencin:"+bencin.isSelected()+"\n");
            bufferedWriter.append("dizel:"+dizel.isSelected()+"\n");
            bufferedWriter.append("da:"+da.isSelected()+"\n");
            bufferedWriter.append("ne:"+ne.isSelected()+"\n");
            bufferedWriter.append("gotovinaObPrevzetju:"+gotovinaObPrevzetju.isSelected()+"\n");
            bufferedWriter.append("kreditnaKartica:"+kreditnaKartica.isSelected()+"\n");
            if (kreditnaKartica.isSelected()) {
                bufferedWriter.append("stevilkaKartice:XXXX-XXXX-XXXX-"+stevilkaKartice.getText().split("-")[3]+"\n");
            }
            else {
                bufferedWriter.append("stevilkaKartice:XXXX-XXXX-XXXX-XXXX\n");
            }

            bufferedWriter.close();
            stanje.setText("Stanje: Podatki za " + ime.getText() + " " + priimek.getText() + " uspešno shranjeni.");
        } catch (Exception e) {
            stanje.setText("Stanje: Napaka pri shranjevanju datoteke.");
        }
    }


    public void naloziCB(ActionEvent actionEvent) {
        odstraniNapake();
        pobarvajVseLabeleCrno();

        if (ime.getText().isBlank() || priimek.getText().isBlank()) {
            stanje.setText("Stanje: Vpiši ime in priimek v polji za vnos za nalaganje stranke.");
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
                    for (int i = 0; i < 25; i++) {
                        line = sc.nextLine();
                        razbitje = line.split(":");
                        key = razbitje[0];
                        value = razbitje[1];
                        slovar.put(key, value);
                    }
                }

                if (slovar.get("ime").equals(ime.getText()) && slovar.get("priimek").equals(priimek.getText())) {
                    uporabnikNajden = true;
                    naslov.setText(slovar.get("naslov"));
                    telefon.setText(slovar.get("telefon"));
                    email.setText(slovar.get("email"));
                    starost.getValueFactory().setValue(Integer.parseInt(slovar.get("starost")));
                    casOdPridobitveIzpita.getValueFactory().setValue(Integer.parseInt(slovar.get("casOdPridobitveIzpita")));
                    datumPrevzetja.setValue(LocalDate.parse(slovar.get("datumPrevzetja")));
                    datumVrnitve.setValue(LocalDate.parse(slovar.get("datumVrnitve")));
                    uraPrevzetja.setValue(slovar.get("uraPrevzetja"));
                    uraVrnitve.setValue(slovar.get("uraVrnitve"));
                    minutaPrevzetja.setValue(slovar.get("minutaPrevzetja"));
                    minutaVrnitve.setValue(slovar.get("minutaVrnitve"));
                    krajPrevzetja.setValue(slovar.get("krajPrevzetja"));
                    krajVrnitve.setValue(slovar.get("krajVrnitve"));
                    velikost.setValue(slovar.get("velikost"));
                    model.setValue(slovar.get("model"));
                    samodejni.selectedProperty().set(Boolean.parseBoolean(slovar.get("samodejni")));
                    rocni.selectedProperty().set(Boolean.parseBoolean(slovar.get("rocni")));
                    bencin.selectedProperty().set(Boolean.parseBoolean(slovar.get("bencin")));
                    dizel.selectedProperty().set(Boolean.parseBoolean(slovar.get("dizel")));
                    da.selectedProperty().set(Boolean.parseBoolean(slovar.get("da")));
                    ne.selectedProperty().set(Boolean.parseBoolean(slovar.get("ne")));
                    gotovinaObPrevzetju.selectedProperty().set(Boolean.parseBoolean(slovar.get("gotovinaObPrevzetju")));
                    kreditnaKartica.selectedProperty().set(Boolean.parseBoolean(slovar.get("kreditnaKartica")));
                    if (kreditnaKartica.isSelected()) {
                        stevilkaKartice.setText(slovar.get("stevilkaKartice"));
                    }
                    else {
                        stevilkaKartice.setText("");
                    }
                    ccv.setText("");

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
            stanje.setText("Stanje: preklical brisanje.");
            return;
        }

        ime.setText("");
        priimek.setText("");
        naslov.setText("");
        telefon.setText("");
        email.setText("");
        starost.getValueFactory().setValue(40);
        casOdPridobitveIzpita.getValueFactory().setValue(20);
        datumPrevzetja.setValue(LocalDate.now());
        datumVrnitve.setValue(LocalDate.now());
        uraPrevzetja.setValue("8");
        uraVrnitve.setValue("20");
        minutaPrevzetja.setValue("00");
        minutaVrnitve.setValue("00");
        krajPrevzetja.setValue("Ljubljana");
        krajVrnitve.setValue("Ljubljana");
        velikost.setValue("Srednji");
        model.setValue("MB C180");
        samodejni.selectedProperty().set(true);
        rocni.selectedProperty().set(false);
        bencin.selectedProperty().set(true);
        dizel.selectedProperty().set(false);
        da.selectedProperty().set(true);
        ne.selectedProperty().set(false);
        gotovinaObPrevzetju.selectedProperty().set(true);
        kreditnaKartica.selectedProperty().set(false);
        stevilkaKartice.setText("");
        ccv.setText("");

        pobarvajVseLabeleCrno();
        nastaviCeno();

        stanje.setText("Stanje: uspešno izbrisal vse podatke in nastavil privzete vrednosti.");
    }


    public void pobarvajVseLabeleCrno() {
        imeLabel.setStyle("-fx-text-fill: #000000");
        priimekLabel.setStyle("-fx-text-fill: #000000");
        naslovLabel.setStyle("-fx-text-fill: #000000");
        telefonLabel.setStyle("-fx-text-fill: #000000");
        emailLabel.setStyle("-fx-text-fill: #000000");
        stevilkaKarticeLabel.setStyle("-fx-text-fill: #000000");
        ccvLabel.setStyle("-fx-text-fill: #000000");
    }

    public void odstraniNapake() {
        napakaNaslov.setVisible(false);
        napakaTelefon.setVisible(false);
        napakaEmail.setVisible(false);
        napakaStevilkaKartice.setVisible(false);
        napakaCCV.setVisible(false);
    }


    public void povzetekIzposojeCB(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("porocilo.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Povzetek izposoje");
        stage.setScene(new Scene(root, 300, 550));
        stage.show();
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


    public void avtorCB(ActionEvent actionEvent) {
        stanje.setText("Stanje: Avtor aplikacije je Tim Ulčar Pertot.");
    }


    public void izhodCB(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void nastaviCeno() {
        String izbiraVelikosti = (String) velikost.getSelectionModel().getSelectedItem();
        boolean izbranoDodatnoZavarovanje = da.isSelected();
        int steviloDni = 1 + Math.toIntExact(ChronoUnit.DAYS.between(datumPrevzetja.getValue(), datumVrnitve.getValue()));
        int cenaNaDan;
        int koncnaCena;
        if (izbiraVelikosti.equals("Majhen")) {
            cenaNaDan = 80;
        }
        else if (izbiraVelikosti.equals("Srednji")) {
            cenaNaDan = 100;
        }
        else {
            cenaNaDan = 120;
        }

        if (izbranoDodatnoZavarovanje) {
            koncnaCena = steviloDni * cenaNaDan + steviloDni * 2;
            cena.setText("Cena: " + koncnaCena + "€ (" + cenaNaDan + "€/dan za avto in 2€/dan za dodatno zavarovanje)");
        }
        else {
            koncnaCena = steviloDni * cenaNaDan;
            cena.setText("Cena: " + koncnaCena + "€ (" + cenaNaDan + "€/dan za avto)");
        }
    }


    public void makeFontResponsive() {
        Scene scene = gridPane.getScene();
        fontSize.bind(scene.heightProperty().divide(70));
        gridPane.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        stanje.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-style: italic;"));
        podatkovnaBaza.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-style: italic;"));
        stranka.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));
        casInKrajPrevzetjaTerVrnitve.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));
        avtomobil.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));
        nacinPlacilaNaslov.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";", "-fx-font-weight: bold;"));

        naloziStrankoButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        shraniButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        pobrisiVseButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        podatkovnaBazaButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));

        ime.prefWidthProperty().bind(scene.widthProperty().divide(6));
        ime.prefHeightProperty().bind(scene.heightProperty().divide(40));
        priimek.prefWidthProperty().bind(scene.widthProperty().divide(6));
        priimek.prefHeightProperty().bind(scene.heightProperty().divide(40));
        naslov.prefWidthProperty().bind(scene.widthProperty().divide(6));
        naslov.prefHeightProperty().bind(scene.heightProperty().divide(40));
        telefon.prefWidthProperty().bind(scene.widthProperty().divide(5));
        telefon.prefHeightProperty().bind(scene.heightProperty().divide(40));
        email.prefWidthProperty().bind(scene.widthProperty().divide(5));
        email.prefHeightProperty().bind(scene.heightProperty().divide(40));
        starost.prefWidthProperty().bind(scene.widthProperty().divide(10));
        starost.prefHeightProperty().bind(scene.heightProperty().divide(40));
        casOdPridobitveIzpita.prefWidthProperty().bind(scene.widthProperty().divide(10));
        casOdPridobitveIzpita.prefHeightProperty().bind(scene.heightProperty().divide(40));
        datumPrevzetja.prefWidthProperty().bind(scene.widthProperty().divide(5));
        datumPrevzetja.prefHeightProperty().bind(scene.heightProperty().divide(40));
        datumVrnitve.prefWidthProperty().bind(scene.widthProperty().divide(5));
        datumVrnitve.prefHeightProperty().bind(scene.heightProperty().divide(40));
        uraPrevzetja.prefWidthProperty().bind(scene.widthProperty().divide(15));
        uraPrevzetja.prefHeightProperty().bind(scene.heightProperty().divide(40));
        uraVrnitve.prefWidthProperty().bind(scene.widthProperty().divide(15));
        uraVrnitve.prefHeightProperty().bind(scene.heightProperty().divide(40));
        minutaPrevzetja.prefWidthProperty().bind(scene.widthProperty().divide(15));
        minutaPrevzetja.prefHeightProperty().bind(scene.heightProperty().divide(40));
        minutaVrnitve.prefWidthProperty().bind(scene.widthProperty().divide(15));
        minutaVrnitve.prefHeightProperty().bind(scene.heightProperty().divide(40));
        krajPrevzetja.prefWidthProperty().bind(scene.widthProperty().divide(5));
        krajPrevzetja.prefHeightProperty().bind(scene.heightProperty().divide(40));
        krajVrnitve.prefWidthProperty().bind(scene.widthProperty().divide(5));
        krajVrnitve.prefHeightProperty().bind(scene.heightProperty().divide(40));
        velikost.prefWidthProperty().bind(scene.widthProperty().divide(5));
        velikost.prefHeightProperty().bind(scene.heightProperty().divide(40));
        model.prefWidthProperty().bind(scene.widthProperty().divide(5));
        model.prefHeightProperty().bind(scene.heightProperty().divide(40));
        stevilkaKartice.prefWidthProperty().bind(scene.widthProperty().divide(5));
        stevilkaKartice.prefHeightProperty().bind(scene.heightProperty().divide(40));
        ccv.prefWidthProperty().bind(scene.widthProperty().divide(15));
        ccv.prefHeightProperty().bind(scene.heightProperty().divide(40));
        naloziStrankoButton.prefWidthProperty().bind(scene.widthProperty().divide(7));
        naloziStrankoButton.prefHeightProperty().bind(scene.heightProperty().divide(40));
        shraniButton.prefWidthProperty().bind(scene.widthProperty().divide(10));
        shraniButton.prefHeightProperty().bind(scene.heightProperty().divide(40));
        pobrisiVseButton.prefWidthProperty().bind(scene.widthProperty().divide(8));
        pobrisiVseButton.prefHeightProperty().bind(scene.heightProperty().divide(40));
        podatkovnaBazaButton.prefWidthProperty().bind(scene.widthProperty().divide(6));
        podatkovnaBazaButton.prefHeightProperty().bind(scene.heightProperty().divide(40));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datoteka = new File("najemi2021.txt");

        starost.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 120, 40));
        casOdPridobitveIzpita.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 20));

        datumPrevzetja.setValue(LocalDate.now());
        datumVrnitve.setValue(LocalDate.now());
        uraPrevzetja.getItems().addAll("8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");
        uraPrevzetja.setValue("8");
        uraVrnitve.getItems().addAll("8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21");
        uraVrnitve.setValue("20");
        minutaPrevzetja.getItems().addAll("00", "10", "20", "30", "40", "50");
        minutaPrevzetja.setValue("00");
        minutaVrnitve.getItems().addAll("00", "10", "20", "30", "40", "50");
        minutaVrnitve.setValue("00");
        krajPrevzetja.getItems().addAll("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto",
                                            "Murska Sobota", "Jesenice", "Portorož", "Letališče Brnik", "Letališče Maribor");
        krajPrevzetja.setValue("Ljubljana");
        krajVrnitve.getItems().addAll("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto",
                "Murska Sobota", "Jesenice", "Portorož", "Letališče Brnik", "Letališče Maribor");
        krajVrnitve.setValue("Ljubljana");

        velikost.getItems().addAll("Majhen", "Srednji", "Velik");
        velikost.setValue("Srednji");
        model.getItems().addAll("MB C180", "Audi A5", "Toyota Avensis", "Honda Accord", "Škoda Octavia");
        model.setValue("MB C180");

        velikost.getSelectionModel().selectedItemProperty().addListener((ObservableValue ov, Object old_val, Object new_val) -> {
            if (new_val.equals("Majhen")) {
                model.getItems().remove(0, 5);
                model.getItems().addAll("Suzuki Swift", "Mazda 2", "Volkswagen Polo", "Ford Fiesta", "Peugeot 208");
                model.setValue("Suzuki Swift");

                nastaviCeno();
            }
            else if (new_val.equals("Srednji")) {
                model.getItems().remove(0, 5);
                model.getItems().addAll("MB C180", "Audi A5", "Toyota Avensis", "Honda Accord", "Škoda Octavia");
                model.setValue("MB C180");

                nastaviCeno();
            }
            else {
                model.getItems().remove(0, 5);
                model.getItems().addAll("Kia Sorento", "Volvo XC90", "Audi Q7", "Peugeot 5008", "BMW X7");
                model.setValue("Kia Sorento");

                nastaviCeno();
            }
        });

        datumPrevzetja.valueProperty().addListener((ov, oldValue, newValue) -> {
            nastaviCeno();
        });
        datumVrnitve.valueProperty().addListener((ov, oldValue, newValue) -> {
            nastaviCeno();
        });
        da.selectedProperty().addListener((ov, oldValue, newValue) -> {
            nastaviCeno();
        });

        stevilkaKarticeLabel.setVisible(false);
        stevilkaKartice.setVisible(false);
        ccvLabel.setVisible(false);
        ccv.setVisible(false);

        kreditnaKartica.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasSelected, Boolean isSelected) {
                if (isSelected) {
                    stevilkaKarticeLabel.setVisible(true);
                    stevilkaKartice.setVisible(true);
                    ccvLabel.setVisible(true);
                    ccv.setVisible(true);
                }
                else {
                    stevilkaKarticeLabel.setVisible(false);
                    stevilkaKartice.setVisible(false);
                    ccvLabel.setVisible(false);
                    ccv.setVisible(false);
                }
            }
        });

        nastaviCeno();

        odstraniNapake();

        Platform.runLater(()->makeFontResponsive());
    }
}
