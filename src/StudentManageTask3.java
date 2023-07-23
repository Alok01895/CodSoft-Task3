import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentManageTask3 extends Application {


    private VBox root;
    private Management manage;
    private Button add;
    private Button remove;
    private Button search;
    private Button update;
    private Button all;
    private Button exit;

    // textfield for student data
    private TextField classNo;
    private TextField name;
    private TextField roll;
    private TextField grade;
    private TextField remarks;
    private Button submit;
    private TextField result;
    private Label label;
    private Label label2;
    private Label studentLabel;

    private TextField sName;
    private TextField sClass;
    private TextField sGrade;
    private TextField sRoll;
    private TextField sRemarks;

    private Student previousStudentData;

    private TableView<Student> tableView;


    public void setCoicesVisibility(boolean value)
    {
        label.setVisible(value);
        label2.setVisible(value);
        add.setVisible(value);
        remove.setVisible(value);
        search.setVisible(value);
        update.setVisible(value);
        all.setVisible(value);

        label.setManaged(value);
        label2.setManaged(value);
        add.setManaged(value);
        remove.setManaged(value);
        search.setManaged(value);
        update.setManaged(value);
        all.setManaged(value);
        exit.setManaged(true);
    }

    public void setupButtons()
    {
        label = new Label("Hey! there" + "," + " What can I do for you today?..");
        label.getStyleClass().add("label");
        label2 = new Label("Enter Your Choice");
        add=new Button("Add student");
        remove=new Button("Remove student");
        search=new Button("Search student");
        update=new Button("Update student");
        all=new Button("Display all students");
        exit=new Button("Home");
        add.getStyleClass().add("button");
        exit.setVisible(false);
        

    }


    public void showResult(String value,boolean response)
    {
        result.setVisible(true);
        result.setManaged(true);
        if(response)
        {
            result.setText("Student " + value + " Succesfully...");
        }
        else{
            result.setText("Sorry! Student couldn't be " + value);
        }
    }


    public void addingStudent()
    {
        int classVal=Integer.parseInt(classNo.getText());
        String nameVal=name.getText();
        int rollVal=Integer.parseInt(roll.getText());
        String gradeval=grade.getText();
        String remarksVal=remarks.getText();
        studentDetailsVisibility(false);
        Student newStudent = new Student(classVal, rollVal, nameVal,  gradeval,remarksVal);
        boolean response=manage.addStudent(newStudent);
        showResult("added",response);
    }

    public void studentDetailsVisibility(boolean value){
        classNo.setVisible(value);
        name.setVisible(value);
        roll.setVisible(value);
        grade.setVisible(value);
        remarks.setVisible(value);
        submit.setVisible(value);
        studentLabel.setVisible(value);
        

        classNo.setManaged(value);
        name.setManaged(value);
        roll.setManaged(value);
        grade.setManaged(value);
        remarks.setManaged(value);
        submit.setManaged(value);
        studentLabel.setManaged(value);
        

    }

    public void addStudentDetails()
    {

        setCoicesVisibility(false);
        exit.setVisible(true);

        // Taking up details
        studentLabel=new Label("Enter the Student details...");
        classNo = new TextField();
        classNo.getStyleClass().add("text");
        classNo.setPromptText("Enter Class");
        name = new TextField();
        name.getStyleClass().add("text");
        name.setPromptText("Enter Name");
        roll = new TextField();
        roll.getStyleClass().add("text");
        roll.setPromptText("Enter Roll Number");
        grade = new TextField();
        grade.getStyleClass().add("text");
        grade.setPromptText("Enter Grade");
        remarks = new TextField();
        remarks.getStyleClass().add("text");
        remarks.setPromptText("Enter Remarks");
        submit = new Button("Submit");
        result=new TextField("");
        result.getStyleClass().add("text");
        result.setVisible(false);
        result.setManaged(false);
        result.setEditable(false);
        submit.setOnAction(e->addingStudent());
        
        root.getChildren().addAll(studentLabel, classNo, name, roll, grade, remarks, submit, result);


    }

    public void removingStudent()
    {

        detailSingleStudentVisibility(false);
        int classVal=Integer.parseInt(classNo.getText());
        int rollVal=Integer.parseInt(roll.getText());
        boolean response=manage.removeStudent(classVal,rollVal);
        showResult("removed", response);
    }

    public void detailSingleStudentVisibility(boolean value)
    {
        classNo.setVisible(value);
        roll.setVisible(value);
        submit.setVisible(value);
        studentLabel.setVisible(value);

        studentLabel.setManaged(value);
        classNo.setManaged(value);
        roll.setManaged(value);
        submit.setManaged(value);

        
    }


    public void removeStudent()
    {
        exit.setVisible(true);
        setCoicesVisibility(false);
        studentLabel=new Label("Enter the Student details which you want to remove...");
        classNo = new TextField();
        classNo.getStyleClass().add("text");
        classNo.setPromptText("Enter Class");
        roll = new TextField();
        roll.getStyleClass().add("text");
        roll.setPromptText("Enter Roll Number");
        result=new TextField("");
        result.getStyleClass().add("text");
        result.setVisible(false);
        result.setManaged(false);
        result.setEditable(false);
        submit = new Button("Submit");
        submit.setOnAction(e->removingStudent());
        root.getChildren().addAll(studentLabel, classNo, roll,submit, result);
        
    }



    public boolean searchingStudent(boolean value)
    {
        detailSingleStudentVisibility(false);
        int classVal=Integer.parseInt(classNo.getText());
        int rollVal=Integer.parseInt(roll.getText());
        Student student=manage.searchStudent(classVal,rollVal);
        if(student!=null)
        {
            previousStudentData=student;
            Label info=new Label("Student Information");
            info.setVisible(!value);
            info.setManaged(!value);
            sName=new TextField("Name- " + student.getName());
            sName.getStyleClass().add("text");
            sName.setEditable(value);
            sClass=new TextField("Class- " + String.valueOf(student.getClassNo()));
            sClass.getStyleClass().add("text");
            sClass.setEditable(value);
            sRoll=new TextField("Roll Number- " + String.valueOf(student.getRollNumber()));
            sRoll.getStyleClass().add("text");
            sRoll.setEditable(value);
            sGrade=new TextField("Grade- " + student.getGrade());
            sGrade.getStyleClass().add("text");
            sGrade.setEditable(value);
            sRemarks=new TextField("Remarks- " +student.getRemarks());
            sRemarks.getStyleClass().add("text");
            sRemarks.setEditable(value);
            root.getChildren().addAll(info,sName, sClass, sRoll,sGrade, sRemarks);
            return true;
        }
        else{
            TextField info=new TextField("Oops!.. No such student exists.");
            info.getStyleClass().add("text");
            info.setEditable(false);
            root.getChildren().addAll(info);
            return false;
        }

    }

    public void searchStudent()
    {
        exit.setVisible(true);
        setCoicesVisibility(false);
        studentLabel=new Label("Enter the Student details to be searched...");
        classNo = new TextField();
        classNo.getStyleClass().add("text");
        classNo.setPromptText("Enter Class");
        roll = new TextField();
        roll.getStyleClass().add("text");
        roll.setPromptText("Enter Roll Number");
        submit = new Button("Submit");
        submit.setOnAction(e->searchingStudent(false));
        root.getChildren().addAll(studentLabel, classNo, roll,submit);
    }

    public void updatingListVisibility(boolean value)
    {
        sClass.setVisible(value);
        sClass.setManaged(value);

        sName.setVisible(value);
        sName.setManaged(value);

        sRoll.setVisible(value);
        sRoll.setManaged(value);

        sGrade.setVisible(value);
        sGrade.setManaged(value);

        sRemarks.setVisible(value);
        sRemarks.setManaged(value);

        submit.setVisible(value);
        submit.setManaged(value);




    }

    public void updatingStudent() {
        updatingListVisibility(false);
        int oldDataClass = previousStudentData.getClassNo();
        int oldDataRoll = previousStudentData.getRollNumber();
    
        boolean response = manage.removeStudent(oldDataClass, oldDataRoll);
        if (response) {
            String name = sName.getText().substring(sName.getText().indexOf(' ') + 1);
            int classVal = Integer.parseInt(sClass.getText().replaceAll("\\D+", "").trim());
            int roll = Integer.parseInt(sRoll.getText().replaceAll("\\D+", "").trim());
            String grade = sGrade.getText().substring(sGrade.getText().indexOf(' ') + 1);
            String remarks = sRemarks.getText().substring(sRemarks.getText().indexOf(' ') + 1);
            Student updatedStudent = new Student(classVal, roll, name,  grade,  remarks);
            manage.addStudent(updatedStudent);
        }
        result=new TextField("");
        result.setVisible(false);
        result.setManaged(false);
        result.setEditable(false);
        showResult("updated", response);
        root.getChildren().addAll(result);
    }
    
    
    


    public void gatheringDataStudent()
    {
        detailSingleStudentVisibility(false);
        Label updateHead=new Label("Update the required fields!..");
        root.getChildren().add(updateHead);
        boolean student=searchingStudent(true);
        if(student)
        {
            submit=new Button("Submit");
            submit.setOnAction(e->updatingStudent());
            root.getChildren().add(submit);
        }
    }

    public void updateStudent()
    {
        exit.setVisible(true);
        setCoicesVisibility(false);
        studentLabel=new Label("Enter the Student details to be updated...");
        classNo = new TextField();
        classNo.getStyleClass().add("text");
        classNo.setPromptText("Enter Class");
        roll = new TextField();
        roll.getStyleClass().add("text");
        roll.setPromptText("Enter Roll Number");
        submit = new Button("Submit");
        submit.setOnAction(e->gatheringDataStudent());
        root.getChildren().addAll(studentLabel, classNo, roll,submit);
    }

    public void setDisplayFieldsVisibility(boolean value)
    {
        classNo.setVisible(value);
        submit.setVisible(value);
        studentLabel.setVisible(value);

        studentLabel.setManaged(value);
        classNo.setManaged(value);
        submit.setManaged(value);
    }


    public void  displayingAll()
    {
        setDisplayFieldsVisibility(false);
        int classVal=Integer.parseInt(classNo.getText());
        tableView = new TableView<>();
        

        TableColumn<Student, Integer> classColumn = new TableColumn<>("Class");
        classColumn.setCellValueFactory(new PropertyValueFactory<>("classNo"));
        
        TableColumn<Student, Integer> rollColumn = new TableColumn<>("Roll Number");
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Student, String> remarksColumn = new TableColumn<>("Remarks");
        remarksColumn.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        ArrayList<Student> students=manage.displayAllStudents(classVal);

        classColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15)); // Set the desired width as a fraction of the table's width
        rollColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        nameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        gradeColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        remarksColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));


        tableView.getColumns().addAll(classColumn, rollColumn, nameColumn,  gradeColumn,  remarksColumn);
        ObservableList<Student> data = FXCollections.observableArrayList(students);
    
        tableView.setItems(data);
        root.getChildren().add(tableView);

    }

    public void displayAll()
    {
        exit.setVisible(true);
        setCoicesVisibility(false);
        studentLabel=new Label("Which class's data you want?..");
        classNo = new TextField();
        classNo.getStyleClass().add("text");
        classNo.setPromptText("Enter Class");
        submit = new Button("Submit");
        submit.setOnAction(e->displayingAll());
        root.getChildren().addAll(studentLabel, classNo,submit);
    }


    @Override
    public void start(Stage primaryStage) {
        manage = new Management("src/studentsData.dat");
        setupButtons();
        
        

        add.setOnAction(e->addStudentDetails());
        remove.setOnAction(e->removeStudent());
        search.setOnAction(e->searchStudent());
        update.setOnAction(e->updateStudent());
        all.setOnAction(e->displayAll());

        exit.setOnAction(e->start(primaryStage));
        root = new VBox(10);
        root.setPadding(new Insets(0)); 
        root.getChildren().addAll(label, label2,add, remove, update, search, all,  exit);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Student Data Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

