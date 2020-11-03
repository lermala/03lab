package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // нашу первую строчку мы расщепляем пополам
        // сначала формируем загрузчик и привязываем его к файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/sample.fxml"));
        // а затем уже непосредственно вызываем загрузку дерева разметки из файла
        Parent root = loader.load();

        // добавляем эту строчку, собственно ради чего мы первую строку и расщепили
        // тут мы вытаскиваем контроллер которые был создан при вызове метода load
        // и сохраняем ссылку на него в переменную
        Controller controller = loader.getController();

        // а тут привязываем событие закрытия приложения к нашей функции onStageClose
        primaryStage.setOnHidden(e -> controller.onStageClose());

        // это не трогаем
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
