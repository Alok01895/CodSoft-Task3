import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Management {
    private ArrayList<Student> students;
    private String dataFilePath;

    public Management(String dataFilePath) {
    try {
        this.dataFilePath = new File(dataFilePath).getCanonicalPath();
    } catch (IOException e) {
        e.printStackTrace();
    }
        students = new ArrayList<>();
        loadStudentsFromDataFile();
    }

    // Method to add a new student
    public boolean addStudent(Student student) {
        Management manage=new Management(dataFilePath);
        if(manage.searchStudent(student.getClassNo(), student.getRollNumber())==null)
        {
            students.add(student);
            return saveStudentsToDataFile();
        }
        return false;
    }

    // Method to remove a student by roll number
    public boolean removeStudent(int classVal,int rollNumber) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == rollNumber  && students.get(i).getClassNo()==classVal) {
                students.remove(i);
                saveStudentsToDataFile();
                return true;
            }
        }
        return false;
    }

    // Method to search for a student by roll number
    public Student searchStudent(int classVal,int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber && student.getClassNo()==classVal) {
                return student;
            }
        }
        return null;
    }

    // Method to display all students
    public ArrayList<Student> displayAllStudents(int value) {
        ArrayList<Student> classStudents=new ArrayList<>();
        for(Student student: students)
        {
            if(student.getClassNo()==value)
            {
                classStudents.add(student);
            }
        }
        return classStudents;
    }

    // Method to read students from a data file
    private void loadStudentsFromDataFile() {
    File dataFile = new File(dataFilePath);

    if (dataFile.exists() && dataFile.length() > 0) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dataFile))) {
            students = (ArrayList<Student>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    } else {
        students = new ArrayList<>();
    }
}


    // Method to save students to a data file
    private boolean saveStudentsToDataFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {

            outputStream.writeObject(students);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

