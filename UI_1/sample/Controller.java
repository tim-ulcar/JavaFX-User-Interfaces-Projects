package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField textfield;
    public ToggleGroup radiobuttons;
    public Button button;
    public ComboBox combobox;
    public Spinner spinner;
    public CheckBox checkbox;
    public Label sporocila;
    public Label status;
    public RadioButton dodajRB;
    public RadioButton odstraniPrvegaRB;
    public RadioButton odstraniIzbranegaRB;
    public Spinner spinnerAdding;

    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi datoteko");
        File f = fc.showOpenDialog(null);
    }

    public void pobrisiCB(ActionEvent actionEvent) {
        sporocila.setText("");
        status.setText("");
    }

    public void izhodCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addT() {
        status.setText(status.getText() + "T");
    }
    public void addI() {
        status.setText(status.getText() + "I");
    }
    public void addM() {
        status.setText(status.getText() + "M");
    }
    public void addU() {
        status.setText(status.getText() + "U");
    }
    public void addL() {
        status.setText(status.getText() + "L");
    }
    public void addČ() {
        status.setText(status.getText() + "Č");
    }
    public void addA() {
        status.setText(status.getText() + "A");
    }
    public void addR() {
        status.setText(status.getText() + "R");
    }
    public void pomagajMi() {
        status.setText("Pomagaj si sam.");
    }


    public void izvediAkcijo() {
        if (dodajRB.isSelected()) {
            String vpisano = textfield.getText();
            if (vpisano.isEmpty()) {
                return;
            }
            if (combobox.getItems().size() >= 20) {
                sporocila.setText("Dodajam \n"+ "Seznam je že poln - dovoljenih je samo 20 elementov.");
                return;
            }
            if (!checkbox.isSelected() || !combobox.getItems().contains(vpisano)) {
                textfield.setText("");
                combobox.getItems().add((int)spinnerAdding.getValue(), vpisano);
                adjustSpinnerAdding();
                sporocila.setText("Dodajam \n'" + vpisano + "' je bil uspešno dodan.");
            }
            else {
                sporocila.setText("Dodajam \n'" + vpisano + "' je že na seznamu - element ni bil dodan.");
            }
        }
        else if (odstraniIzbranegaRB.isSelected()) {
            Object item = combobox.getSelectionModel().getSelectedItem();
            if (!(item == null)) {
                combobox.getItems().remove(item);
                adjustSpinnerAdding();
                sporocila.setText("Odstranjujem izbranega \n'" + item + "' je bil izbrisan.");
            }
            else {
                sporocila.setText("Odstranjujem izbranega \nNoben element ni izbran.");
            }
        }
        else {
            if (!combobox.getItems().isEmpty()) {
                Object item = combobox.getItems().get(0);
                combobox.getItems().remove(0);
                adjustSpinnerAdding();
                sporocila.setText("Odstranjujem prvega \n'" + item + "' je bil izbrisan.");
            }
            else {
                sporocila.setText("Odstranjujem prvega \nNi elementov v seznamu.");
            }
        }
        textfield.requestFocus();
    }

    public void sporociloDodaj(ActionEvent actionEvent) {
        sporocila.setText("Dodajam");
    }

    public void sporociloOdstraniIzbranega(ActionEvent actionEvent) {
        sporocila.setText("Odstranjujem izbranega");
    }

    public void sporociloOdstraniPrvega(ActionEvent actionEvent) {
        sporocila.setText("Odstranjujem prvega");
    }

    public void adjustSpinnerAdding() {
        spinnerAdding.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, combobox.getItems().size(), combobox.getItems().size()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combobox.getItems().addAll("Ljubljana", "Maribor", "Koper");

        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        spinner.valueProperty().addListener((observable, oldV, newV) -> {
            int newValue = (int) newV;
            if (newValue < combobox.getItems().size()) {
                sporocila.setText((String) combobox.getItems().get(newValue));
            }
            else {
                sporocila.setText("Ni elementa");
            }
        });

        adjustSpinnerAdding();
        Platform.runLater(()->textfield.requestFocus());
    }

}
