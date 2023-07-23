import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private int classNo;
    private String remarks;


    public Student(int classNo,int rollNumber, String name, String grade,String remarks) {
        this.classNo=classNo;
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
        this.remarks=remarks;
    }

    // Getters and setters (optional)

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo){
        this.classNo=classNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setRemarks(String remarks)
    {
        this.remarks=remarks;
    }

    public String getRemarks(){
        return remarks;
    }
}
