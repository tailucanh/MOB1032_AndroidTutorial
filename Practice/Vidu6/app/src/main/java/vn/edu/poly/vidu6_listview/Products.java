package vn.edu.poly.vidu6_listview;

public class Products {
     int id;
    String name;
    double price;
    int img_res;  //Id của ảnh trong drawable

    //Tạo hàm khởi tạo

    public Products() {

    }

    public Products(int id, String name, double price, int img_res) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img_res = img_res;
    }
}
