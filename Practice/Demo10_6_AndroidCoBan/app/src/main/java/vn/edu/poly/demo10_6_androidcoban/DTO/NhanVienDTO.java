package vn.edu.poly.demo10_6_androidcoban.DTO;

public class NhanVienDTO {
    private  int id;
    private String userName;
    private String passWord;
    private String fullName;
    private String birthday;
    private double salary;

    public NhanVienDTO() {

    }

    public NhanVienDTO(int id, String userName, String passWord, String fullName, String birthday, double salary) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.birthday = birthday;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
