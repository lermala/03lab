package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.PersonPack.Person;
import sample.PersonPack.Student;
import sample.PersonPack.Worker;

import java.net.URL;
import java.util.ResourceBundle;


public class AddController implements Initializable {

    @FXML
    private TextField txtName, txtAge, txtLevel, txtPlace, txtSalary;

    @FXML
    private RadioButton rbStudent, rbWorker;

    @FXML
    private Button buttonAdd;

    private boolean clickedOk = false;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Group
        ToggleGroup group = new ToggleGroup();

        rbStudent.setToggleGroup(group);
        rbWorker.setToggleGroup(group);
    }

    public void addPerson(){
        // проверяем на корректный ввод
        if (!checkInput().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Некорректный ввод");
            alert.setContentText(checkInput());

            alert.showAndWait();
            return;
        }

        clickedOk = true;
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.close();
    }

    public Person getPerson(){
        String name = txtName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String place = txtPlace.getText();
        Float salary = Float.parseFloat(txtSalary.getText());

        if (rbStudent.isSelected()){
            int lvl = Integer.parseInt(txtLevel.getText());
            return new Student(name, age, place, lvl, salary);

        } else {
            String position = txtLevel.getText();
            return new Worker(name, age, place, position, salary);
        }
    }

    public void setPerson(Person person){
        txtName.setText(person.getName());
        txtAge.setText(person.getAge() + "");

        if (person instanceof Worker){
            rbWorker.setSelected(true);
            Worker worker = (Worker) person;
            txtSalary.setText(worker.getSalary() + "");
            txtPlace.setText(worker.getPlaceOfWork() + "");
            txtLevel.setText(worker.getPosition());
        } else {
            rbStudent.setSelected(true);
            Student student = (Student) person;
            txtSalary.setText(student.getScholarship() + "");
            txtPlace.setText(student.getEducation());
            txtLevel.setText(student.getLevel() + "");
        }
    }

    /**
     * функция для проверки на возможность перевода строки в значение типа int
     */
    private static boolean isInteger(String str)
    {
        try
        {
            int d = Integer.parseInt(str); //преобразовываем
        }
        catch(NumberFormatException nfe) //если возникает исключение
        {
            return false;
        }
        return true;
    }

    /**
     * функция для проверки на возможность перевода строки в значение типа float
     */
    private static boolean isFloat(String str)
    {
        try
        {
            float d = Float.parseFloat(str); //преобразовываем
        }
        catch(NumberFormatException nfe) //если возникает исключение
        {
            return false;
        }
        return true;
    }

    private String checkInput(){
        String res = "";

        if (isEmpty(txtAge))
            res = "Введите возраст";
        else if (isEmpty(txtName))
            res = "Введите Имя";
        else if (isEmpty(txtLevel))
            res = "Введите курс/должность";
        else if (isEmpty(txtPlace))
            res = "Введите место учебы/работы";
        else if (isEmpty(txtSalary))
            res = "Введите стипендию/оклад";

        else if (!rbStudent.isSelected() && !rbWorker.isSelected())
            res = "Выберите роль";

        else if (!isFloat(txtSalary.getText()))
            res = "Некорректный ввод данных в стипендии/окладе";

        else if (rbStudent.isSelected() && !isInteger(txtLevel.getText()))
            res = "Введите курс числом";

        return res;
    }

    public boolean isClickedOk() {
        return clickedOk;
    }


    private boolean isEmpty(TextField txt){
        if (txt.getText().equals(""))
            return true;
        return false;
    }
}
