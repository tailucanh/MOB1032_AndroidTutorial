package vn.edu.poly.ph26495_practiceoop;

import android.util.Log;



public class SinhVien {

    private String id;
    private String name;
    private String email;
    String TAG = "SinhVien zzzz";

    public SinhVien() {

    }

    public SinhVien(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void inThongTin(){
        Log.d(TAG," - id: "+this.id);
        Log.d(TAG," - Tên: "+this.name);
        Log.d(TAG," - Email: "+this.email);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
