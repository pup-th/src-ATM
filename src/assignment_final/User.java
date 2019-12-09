
package assignment_final;

/**
 *
 * @author Asus
 */
public class User {
         public String Taikhoan;
         public String matkhau;
         public String name;
         public String sdt;
         public String gioitinh;
         public String diachi;
         public String sodu;
//initialize contructor
    public User(String Taikhoan, String matkhau, String name, String sdt, String gioitinh, String diachi,String sodu) {
        this.Taikhoan = Taikhoan;
        this.matkhau = matkhau;
        this.name = name;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sodu= sodu;
    }  
/**
 * 
 * @param Taikhoan 
 * initialize contructor 
 */
    public void setTaikhoan(String Taikhoan) {
        this.Taikhoan = Taikhoan;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTaikhoan() {
        return Taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getName() {
        return name;
    }

    public String getSdt() {
        return sdt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }
    public void setsodu(String sodu){
        this.sodu=sodu;
    }
     public String getsodu() {
        return sodu;
    }
}
