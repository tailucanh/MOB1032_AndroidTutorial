package com.spx.crudlopsinhvien.DTO;

public class TbLop {
    int id_lop;
    String ten_lop;
    String nganh;

    public static final String TB_NAME = "tb_lop";
    public static final String COL_NAME_ID = "id_lop";
    public static final String COL_NAME_TEN_LOP = "ten_lop";
    public static final String COL_NAME_NGANH = "nganh";

    public int getId_lop() {
        return id_lop;
    }

    public void setId_lop(int id_lop) {
        this.id_lop = id_lop;
    }

    public String getTen_lop() {
        return ten_lop;
    }

    public void setTen_lop(String ten_lop) {
        this.ten_lop = ten_lop;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }


}
