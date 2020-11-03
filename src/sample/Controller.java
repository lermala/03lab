package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.PersonPack.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; // добавили импорт интерфейса
import sample.Settings;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private ArrayList<Person> people = new ArrayList<>();

    @FXML
    private ListView<Person> listView;

    public void showDialogAdd(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/addingPerson.fxml"));
        // а затем уже непосредственно вызываем загрузку дерева разметки из файла
        Parent root = loader.load();

        AddController addController = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("Добавить");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.showAndWait();

        if (addController.isClickedOk()) { // если нажали Добавить
            Person personForAdding = addController.getPerson(); // get per from adding form
            people.add(personForAdding); // add to list

            listView.getItems().add(personForAdding); //add to listview
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // добавляем
        try {
            // создаем поток для чтения из файла
            FileInputStream fis = new FileInputStream("settings.xml");
            // создаем xml декодер из файла
            XMLDecoder decoder = new XMLDecoder(fis);

            /**
             * С помощью decoder.readObject() читаем объект из файла
             * а так как джава сама не может определить, что в файле
             * мы ей подсказываем, указывая в скобочках (Settings)
             * ну и просто загоняем в переменную settings
             */
            Settings settings = (Settings) decoder.readObject();

            // а теперь заполняем форму
            people = settings.people;
            listView.setItems(
                    FXCollections.observableArrayList(settings.people));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * УДАЛЕНИЕ
     */
    public void onClickRemove(){
        Person selectedPerson = listView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            System.out.println(selectedIdx);
            listView.getItems().remove(selectedIdx);
            people.remove(selectedIdx);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ничего не выбрано");
            alert.setContentText("Выберите в таблице человека.");

            alert.showAndWait();
        }

    }

    /**
     * Изменение
     * @param event
     * @throws IOException
     */
    public void onClickChange(ActionEvent event) throws IOException{
        Person selectedPerson = listView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
            listView.getItems().remove(selectedIdx);
            people.remove(selectedIdx);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addingPerson.fxml"));
            // а затем уже непосредственно вызываем загрузку дерева разметки из файла
            Parent root = loader.load();

            AddController addController = loader.getController();
            addController.setPerson(selectedPerson);

            stage.setScene(new Scene(root));
            stage.setTitle("Добавить");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node)event.getSource()).getScene().getWindow() );
            stage.showAndWait();

            Person changedPerson = addController.getPerson(); // get per from adding form

            listView.getItems().add(selectedIdx, changedPerson);
            people.add(selectedIdx, changedPerson);

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ничего не выбрано");
            alert.setContentText("Выберите в таблице человека.");

            alert.showAndWait();
        }
    }

    public void onStageClose() {
        // создали экземпляр класса
        Settings settings = new Settings();
        settings.people = people;

        // добавляем
        try {
            // создаем поток для записи в файл experiment.xml
            FileOutputStream fos = new FileOutputStream("settings.xml");
            // создали энкодер, которые будет писать в поток
            XMLEncoder encoder = new XMLEncoder(fos);

            // записали настройки
            encoder.writeObject(settings);

            // закрыли энкодер и поток для записи
            // если не закрыть, то файл будет пустой
            encoder.close();
            fos.close();
        } catch (Exception e) {
            // на случай ошибки
            e.printStackTrace();
        }
    }

    /**
     * Запрос
     * @param actionEvent
     */
    public void onClickRequest(ActionEvent actionEvent) {
        Person selectedPerson = listView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            final int selectedIdx = listView.getSelectionModel().getSelectedIndex();

            RequestInterface requestInterface = people.get(selectedIdx);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Запрос");
            alert.setHeaderText("Сумма выплаты");
            alert.setContentText("" + requestInterface.getPayoutAmount());

            alert.showAndWait();

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ничего не выбрано");
            alert.setContentText("Выберите в таблице человека.");

            alert.showAndWait();
        }
    }
}
