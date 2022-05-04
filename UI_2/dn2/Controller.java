package dn2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public HTMLEditor htmlEditor;
    public TextArea textEditor;
    public TextArea log;
    public Label obvestila;
    public Accordion accordion;
    public TitledPane titledPaneHTML;
    public TitledPane titledPaneBesedilo;
    public TextField najdiTF;
    public TextField zamenjajTF;

    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Izberi HTML datoteko");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                htmlEditor.setHtmlText(stringBuilder.toString());
                textEditor.setText(stringBuilder.toString());
                log.appendText("Prebral si datoteko: " + file.getAbsolutePath() + " Velikost: "+ file.length() + " bajtov\n");
                obvestila.setText("Prebral si datoteko: " + file.getAbsolutePath() + " Velikost: "+ file.length() + " bajtov");
            } catch (Exception e) {
                log.appendText("Napaka pri odpiranju datoteke " + file.getAbsolutePath() + "\n");
                obvestila.setText("Napaka pri odpiranju datoteke " + file.getAbsolutePath());
            }
        }
    }

    public void shraniCB(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Izberi datoteko za shranjevanje");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(htmlEditor.getHtmlText());
                String fileName = file.getAbsolutePath();
                bufferedWriter.close();
                File writtenFile = new File(fileName);
                log.appendText("Shranil si datoteko: " + file.getAbsolutePath() + " Velikost: "+ writtenFile.length() + " bajtov\n");
                obvestila.setText("Shranil si datoteko: " + file.getAbsolutePath() + " Velikost: "+ writtenFile.length() + " bajtov");
            } catch (Exception e) {
                log.appendText("Napaka pri shranjevanju datoteke " + file.getAbsolutePath() + "\n");
                obvestila.setText("Napaka pri shranjevanju datoteke " + file.getAbsolutePath());
            }
        }
    }

    public void izhodCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void najdiCB(ActionEvent actionEvent) {
        accordion.setExpandedPane(titledPaneBesedilo);
        textEditor.requestFocus();
        String iskalniNiz = najdiTF.getText();
        String text = textEditor.getText();
        int position = text.indexOf(iskalniNiz);
        if (position != -1) {
            textEditor.positionCaret(position);
            obvestila.setText("Prva pozicija niza '" + iskalniNiz + "' je najdena na indeksu " + position);
            log.appendText("Iskal niz '" + iskalniNiz + "'. Niz našen na indeksu " + position + ".\n");
        }
        else {
            textEditor.positionCaret(0);
            obvestila.setText("Iskalni niz '" + iskalniNiz + "' se ne pojavi v besedilu.");
            log.appendText("Iskal niz '" + iskalniNiz + "'. Niza ni bilo v besedilu.\n");
        }
    }

    public void najdiVseInZamenjajCB(ActionEvent actionEvent) {
        String iskalniNiz = najdiTF.getText();
        String zamenjalniNiz = zamenjajTF.getText();

        if (iskalniNiz.equals("")) {
            obvestila.setText("Vpiši iskalni niz.");
            log.appendText("Želel zamenjati vse s praznim iskalnim nizom.");
            najdiTF.requestFocus();
            return;
        }

        String text = textEditor.getText();
        if (text.equals("")) {
            obvestila.setText("Ni besedila.");
            log.appendText("Želel zamenjati vse s praznim besedilom.");
            return;
        }

        accordion.setExpandedPane(titledPaneBesedilo);
        textEditor.requestFocus();

        String newText = text.replaceAll(iskalniNiz, zamenjalniNiz);
        int numberOfOccurences = getNumberOfOccurencesOfSubstringInString(text, iskalniNiz);
        if (numberOfOccurences > 0) {
            textEditor.setText(newText);
            obvestila.setText("Zamenjal vseh " + numberOfOccurences + " pojavitev niza '" + iskalniNiz + "'.");
            log.appendText("Zamenjal vseh " + numberOfOccurences + " pojavitev niza '" + iskalniNiz + "'.\n");
        }
        else {
            obvestila.setText("Iskalni niz '" + iskalniNiz + "' se ne pojavi v besedilu.");
            log.appendText("Želel zamenjati vse ponovitve niza '" + iskalniNiz + "'. Niza ni bilo v besedilu.\n");
        }
    }

    public int getNumberOfOccurencesOfSubstringInString(String string, String substring) {
        String temp = string.replace(substring, "");
        int occurences = (string.length() - temp.length()) / substring.length();
        return occurences;
    }

    public void avtorCB(ActionEvent actionEvent) {
        obvestila.setText("Avtor aplikacije je Tim Ulčar Pertot.");
        log.appendText("Izpisal si avtorja aplikacije.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accordion.setExpandedPane(titledPaneHTML);

        Platform.runLater(()->setUpListeners());
    }

    public void setUpListeners() {
        textEditor.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                updateHTMLEditor();
            }
        });

        htmlEditor.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                updateTextEditor();
            }
        });

        textEditor.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (titledPaneHTML.isExpanded()) {
                    updateTextEditor();
                }
                else if (titledPaneBesedilo.isExpanded()) {
                    updateHTMLEditor();
                }
            }
        });
    }

    public void updateHTMLEditor() {
        String newText = textEditor.getText();
        htmlEditor.setHtmlText(newText);
    }

    public void updateTextEditor() {
        String newText = htmlEditor.getHtmlText();
        textEditor.setText(newText);
    }

    public void naslednjiCB(ActionEvent actionEvent) {
        accordion.setExpandedPane(titledPaneBesedilo);
        textEditor.requestFocus();
        String iskalniNiz = najdiTF.getText();
        String text = textEditor.getText();

        if (text.contains(iskalniNiz)) {
            String[] razbitje = text.split(iskalniNiz);
            int[] indeksi = new int[razbitje.length - 1];
            int naslednjiIndeks = 0;
            for (int i = 0; i < razbitje.length - 1; i++) {
                String string = razbitje[i];
                if (i == 0) {
                    naslednjiIndeks = string.length();
                }
                else {
                    naslednjiIndeks = naslednjiIndeks + iskalniNiz.length() + string.length();
                }
                indeksi[i] = naslednjiIndeks;
            }

            if (text.substring(text.length() - iskalniNiz.length()).equals(iskalniNiz)) {
                int[] indeksi2 = new int[indeksi.length + 1];
                for (int i = 0; i < indeksi.length; i++) {
                    indeksi2[i] = indeksi[i];
                }
                indeksi2[indeksi2.length - 1] = text.length() - iskalniNiz.length();
                indeksi = indeksi2;
            }

            int caretPosition = textEditor.getCaretPosition();
            int i = 0;
            int nextCaretPosition = indeksi[i];
            while (nextCaretPosition <= caretPosition) {
                i++;
                if (i < indeksi.length) {
                    nextCaretPosition = indeksi[i];
                }
                else {
                    i = 0;
                    nextCaretPosition = indeksi[i];
                    break;
                }
            }
            textEditor.positionCaret(nextCaretPosition);

            obvestila.setText("Premaknil se na naslednjo pozicijo niza '" + iskalniNiz + "' na indeksu " + indeksi[i]);
            log.appendText("Premaknil se na naslednjo pozicijo niza '" + iskalniNiz + "' na indeksu " + indeksi[i] + ".\n");
        }
        else {
            textEditor.positionCaret(0);
            obvestila.setText("Iskalni niz '" + iskalniNiz + "' se ne pojavi v besedilu.");
            log.appendText("Iskal niz '" + iskalniNiz + "'. Niza ni bilo v besedilu.\n");
        }
    }

    public void prejsnjiCB(ActionEvent actionEvent) {
        accordion.setExpandedPane(titledPaneBesedilo);
        textEditor.requestFocus();
        String iskalniNiz = najdiTF.getText();
        String text = textEditor.getText();

        if (text.contains(iskalniNiz)) {
            String[] razbitje = text.split(iskalniNiz);
            int[] indeksi = new int[razbitje.length - 1];
            int naslednjiIndeks = 0;
            for (int i = 0; i < razbitje.length - 1; i++) {
                String string = razbitje[i];
                if (i == 0) {
                    naslednjiIndeks = string.length();
                }
                else {
                    naslednjiIndeks = naslednjiIndeks + iskalniNiz.length() + string.length();
                }
                indeksi[i] = naslednjiIndeks;
            }

            if (text.substring(text.length() - iskalniNiz.length()).equals(iskalniNiz)) {
                int[] indeksi2 = new int[indeksi.length + 1];
                for (int i = 0; i < indeksi.length; i++) {
                    indeksi2[i] = indeksi[i];
                }
                indeksi2[indeksi2.length - 1] = text.length() - iskalniNiz.length();
                indeksi = indeksi2;
            }

            int caretPosition = textEditor.getCaretPosition();
            int i = indeksi.length - 1;
            int nextCaretPosition = indeksi[i];
            while (nextCaretPosition >= caretPosition) {
                i--;
                if (i >= 0) {
                    nextCaretPosition = indeksi[i];
                }
                else {
                    i = indeksi.length - 1;;
                    nextCaretPosition = indeksi[i];
                    break;
                }
            }
            textEditor.positionCaret(nextCaretPosition);

            obvestila.setText("Premaknil se na prejšnjo pozicijo niza '" + iskalniNiz + "' na indeksu " + indeksi[i]);
            log.appendText("Premaknil se na prejšnjo pozicijo niza '" + iskalniNiz + "' na indeksu " + indeksi[i] + ".\n");
        }
        else {
            textEditor.positionCaret(0);
            obvestila.setText("Iskalni niz '" + iskalniNiz + "' se ne pojavi v besedilu.");
            log.appendText("Iskal niz '" + iskalniNiz + "'. Niza ni bilo v besedilu.\n");
        }
    }
}
