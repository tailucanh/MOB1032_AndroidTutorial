package vn.edu.poly.ph26495_practiceoop;

import android.util.Log;

public class SinhVienPoly extends SinhVien{
    private String hocbong;
    public SinhVienPoly() {
    }

    public SinhVienPoly(String id, String name, String email, String hocbong) {
        super(id, name, email);
        this.hocbong = hocbong;
    }

    @Override
    public void inThongTin(){
        super.inThongTin();
        Log.d(TAG," - Học bổng: "+this.hocbong);
    }

    public String getHocbong() {
        return hocbong;
    }

    public void setHocbong(String hocbong) {
        this.hocbong = hocbong;
    }
}


