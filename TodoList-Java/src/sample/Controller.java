package sample;
import java.sql.*;
import java.time.LocalDate;
import  java.util.*;
import TodoItems.TodoItems;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.lang.Math;


import java.util.List;

public class Controller {
    private List<TodoItems>items;
    @FXML
    private ListView<TodoItems> TodoListView;
    @FXML
    private TextArea itemsDescriptions;
    @FXML
    private TextArea notecontent;

@FXML
private Label time;
@FXML
private Button saveButton;
    @FXML
    private Button deleteButton;
@FXML
private TextField notetitle;

    public static void main(String[] args) {}

    @FXML
    public void save() {
        System.out.println(
                notetitle.getText() + notecontent.getText()
        );
        try (
                Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\panas\\IdeaProjects\\TodoList-Java\\out\\production\\TodoList-Java\\TodoItems.db");
                Statement statement = con.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS notes (title TEXT, description TEXT, deadline TEXT)");
            statement.execute("INSERT INTO notes (title, description, deadline)" + "VALUES('" + notetitle.getText() + "', '" + notecontent.getText() + "', '" + LocalDate.now() + "')");
            statement.execute("SELECT * FROM notes");
            items = new ArrayList<TodoItems>();
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(results.getString("title") + "" + results.getInt("description") + " " + LocalDate.now());
                items.add(new TodoItems(results.getString("title"), results.getString("description"), LocalDate.now()));
                TodoListView.getItems().setAll(items);
                TodoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                System.out.println(items);
            }

            results.close();
            statement.close();
            con.close();
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(){

        TodoItems items = TodoListView.getSelectionModel().getSelectedItem();
        String descriptionToBeDeleted = items.getDescription();
        System.out.println(descriptionToBeDeleted);
        save();
        try (
                Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\panas\\IdeaProjects\\TodoList-Java\\out\\production\\TodoList-Java\\TodoItems.db");
                Statement statement = con.createStatement()) {
            statement.execute("DELETE FROM notes WHERE description=" + "'"+ descriptionToBeDeleted + "'"+ "");
            System.out.println("DELETE FROM notes WHERE description=" + "'"+ descriptionToBeDeleted + "'"+ "");
            ResultSet results = statement.getResultSet();

            results.close();
            statement.close();
            con.close();

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void print(){
        System.out.println(items);
        System.out.println(LocalDate.now());
    }
@FXML
    public void showDescriptions(){
        TodoItems items = TodoListView.getSelectionModel().getSelectedItem();
        itemsDescriptions.setText("Date: " + items.getDeadline()+  "\n" + "\n" +  items.getDescription());
    }
    public void showdate(){
    time.setText(LocalDate.now().toString());
    }
}
