
package assignment_final;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Main_ATM {

    static Admin ad= new Admin();                       //create object class admin
    static Scanner in = new Scanner(System.in);         //create object class Scanner
    static ArrayList<String> load=new ArrayList<>();    //*****
    static ArrayList<String> tien=new ArrayList<>();    //itilize array to load infomation of user
    static ArrayList<User> use= new ArrayList<>();      //*****
    static int o = 0;
    static String ID="";                                //itilize first value for ID
    /**
     * @throws IOException 
     * request user log in into ATM
     * if right account then they will choose options
     * else, come back to menu
     */
    static void display() throws IOException{
        String co;
        boolean check=true;
        int ch,dem=0;
        in.nextLine();
        System.out.print("Enter ID: ");
        ID=in.nextLine();
        System.out.print("Enter PIN: ");
        co=in.nextLine();
        for(int i=0;i<load.size();i++){    
            if(load.get(i).equals(ID) && load.get(i+1).equals(co)){
                for(int j=0;j<tien.size();j++){
                    if(tien.get(j).equals(ID)){
                    //System.out.println("Balance"+tien.get(j+1));
                    break;
                }
                }
                dem=1;
                break;
            }
        }
        if(dem==1){
            /**
             * display menu
             * option1: user can get money from their account, absolutely, money they can get must be smaller than they have
             * option2: user can transfer their money to other user
             * option3: user can change their pin(password)
             * option4: before they exit, they will be asked 'do you want to see what did they do ?'
             * they must choose 1 option in this menu
             */
        do{
            System.out.println("-----Menu of User-----");
            System.out.println("1-Withdrawals");
            System.out.println("2-Transfers money.");
            System.out.println("3-Change PIN.");
            System.out.println("4-Close the menu.");
            ch=in.nextInt();
            switch(ch){
                case 1:
                  ruttien();
                 break;
                case 2:
                    chuyentien();
                   break;
                case 3:
                   doipin();
                    break;
                case 4:
                    baocao();
                    main_menu();
                default:
                    System.out.println("Please choose number in menu!");
            }
        }while(check);
        }
        else{
            System.out.println("Wrong pin or wrong account !");
        }
    }
    /**
     * showing user that they can see what did they do
     */
    static void baocao(){
        String check;
        System.out.println("Do you want to knows your deal information (yes/no)?");
        in.nextLine();
        check=in.nextLine();
        if(equalsIgnoreCase(check, "yes")){
            System.out.println("===Deal information===");
            System.out.println("======================");
            try{
                FileInputStream f = new FileInputStream("d:/a"+ID+".txt");
                int ch;
                StringBuffer buff=new StringBuffer();
                while((ch=f.read())>-1){
                buff.append((char)ch);
            }
                System.out.println(""+buff.toString());
            }catch(IOException e) {
            }
        }
    }
    /**
     * user can change their PIN(password) thought this method
     */
    static void doipin(){
        int dem=0;
        String pin,newpin,xnpin;
        //they must enter their old pin
        //and verify their new pin
        System.out.print("Enter old pin:");
        pin=in.nextLine();
        in.nextLine();
        System.out.print("Enter new pin:");
        newpin=in.nextLine();
        System.out.print("Verify pin:");
        xnpin=in.nextLine();
        if(newpin.equals(xnpin)){
            //load infomation of user 
            for(int i=0;i<load.size();i++){ 
                if(load.get(i).equals(ID) ){
                    load.set(i+1,newpin);
                    /**
                     * load all information from user.txt to khach.txt and change some row in file
                     * after that, delete user.txt and rename khach.txt to user.txt
                     */
                    try{
                        File file = new File("D://khach.txt");
                        File oldfile= new File("D:/user.txt");
                        file.createNewFile();
                        for(int j=0;j<load.size();j++){
                            FileWriter fs = new FileWriter(file.getAbsoluteFile(),true);
                            BufferedWriter as=new BufferedWriter(fs); 
                            as.write(load.get(j));
                            as.write("\n");
                            as.close();
                        }    
                        oldfile.delete();
                        file.renameTo(oldfile);
                        System.out.println("Succesful!!");
                    }catch (IOException e){
                        }   
                    dem=1;
                    break;
                }
            }
            if(dem==0){
                System.out.println("Wrong pin");
            }
    
        }else{
            System.out.println("Wrong Verification!!");
        }
     
    }
    /**
     * method to add money to user account
     * with this method, only Admin can add money to user account
     */
    static void naptien(){
        Calendar cal = Calendar.getInstance();
        String nap;
        String acc;
        int money;
        int t = 0;
        in.nextLine();
        System.out.print("Enter the account:");
        acc=in.nextLine();
        System.out.print("Enter money:");
        money=in.nextInt();
        for(int j=0;j<tien.size();j++){
            if(tien.get(j).equals(acc)){
                t = Integer.parseInt(tien.get(j+1))+money;
                tien.set(j+1,String.valueOf(t));
            }
        }
        /**
         * load all information from sodu.txt to tien.txt and update some row
         * after that, delete sodu.txt and rename tien.txt to sodu.txt
         */
        try{
            File file = new File("D://tien.txt");
            File oldfile= new File("D:/sodu.txt");
            file.createNewFile();
            for(int j=0;j<tien.size();j++){
                FileWriter fs = new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter as=new BufferedWriter(fs); 
                as.write(tien.get(j));
                as.write("\n");
                as.close();
            }
            oldfile.delete();
            file.renameTo(oldfile);
            System.out.println("Succesful!!");
        }catch (IOException e){
        }
        try{
            Date date1 = cal.getTime();
            File f2 = new File("d:/infor.txt");
            FileWriter fw2 = new FileWriter(f2.getAbsoluteFile(),true);
            BufferedWriter bw2= new BufferedWriter(fw2);
            nap="TRANSFER\n"+acc+" received: "+money+"\nOn: "+date1+"\nAccount balance: "+t+"\n-------------------------";
            bw2.write(nap);
            bw2.write("\n");
            bw2.close();
            fw2.close();  
        }catch(IOException b){
        }
    }
    /**
     * user can take real money from their account
     * absolutely, they must take real money smaller than virtual money from ATM
     */
    static void ruttien(){
        Calendar cal = Calendar.getInstance();
        int money;
        int t;
        int j=0;
        String rut;
        for(j=0;j<tien.size();j++){
            if(tien.get(j).equals(ID)){
                break;
            }
        } 
        do{
            System.out.print("Enter money (<="+tien.get(j+1)+"):");
            money=in.nextInt(); 
        }while(money<0||money>Integer.parseInt(tien.get(j+1)));
        t=Integer.parseInt(tien.get(j+1))-money;
        tien.set(j+1,String.valueOf(t));
        try{
            File file = new File("D://tien.txt");
            File oldfile= new File("D:/sodu.txt");
            file.createNewFile();
            for( j=0;j<tien.size();j++){
                FileWriter fs = new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter as=new BufferedWriter(fs); 
                as.write(tien.get(j));
                as.write("\n");
                as.close();
            } 
            oldfile.delete();
            file.renameTo(oldfile);
            System.out.println("Succesful!!");
        }  catch (IOException e){
        } 
        /**
         * create new file with user name .txt to store their action with their account
         */
        try{
            Date date1 = cal.getTime();
            File f1 = new File("d:/a"+ID+".txt");
            File f2= new File("D:/infor.txt");
            FileWriter fw1 = new FileWriter(f1.getAbsoluteFile(),true);
            BufferedWriter bw1= new BufferedWriter(fw1);
            FileWriter fw2 = new FileWriter(f2.getAbsoluteFile(),true);
            BufferedWriter bw2= new BufferedWriter(fw2);
            rut="Withdrawal\n"+ID+" took: "+money+"\nOn: "+date1+"\nAccount balance: "+t+"\n-------------------------";
            bw1.write(rut);
            bw1.write("\n");
            bw1.close();
            fw1.close();
            bw2.write(rut);
            bw2.write("\n");
            bw2.close();
            fw2.close();
        }catch(IOException b){
        }
    }
    /**
     * user can transfer their money to other account
     * absolutely, they must transfer money smaller than they have
     */
    static void chuyentien(){
        Calendar cal = Calendar.getInstance();
        int money;
        String IDchuyen,chuyen;
        int t;
        int j=0,k=0;
       for(j=0;j<tien.size();j++){
            if(tien.get(j).equals(ID)){
                break;
            }
        }
        System.out.print("Enter account get money:");
        in.nextLine();
        IDchuyen=in.nextLine();
        do{
            System.out.print("Enter money (<="+tien.get(j+1)+"):");
            money=in.nextInt(); 
        }while(money<0||money>Integer.parseInt(tien.get(j+1)));
        for(k=0;k<tien.size();k++){
            if(tien.get(k).equals(IDchuyen)){
                break;
            }
        }
        //subtact their money when they trasfer money
        t=Integer.parseInt(tien.get(j+1))-money;
        tien.set(j+1,String.valueOf(t));
        t=t=Integer.parseInt(tien.get(k+1))+money;
        tien.set(k+1,String.valueOf(t));
        try{
            File file = new File("D://tien.txt");
            File oldfile= new File("D:/sodu.txt");
            file.createNewFile();
            for( j=0;j<tien.size();j++){
                FileWriter fs = new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter as=new BufferedWriter(fs); 
                as.write(tien.get(j));
                as.write("\n");
                as.close();
            } 
            oldfile.delete();
            file.renameTo(oldfile);
            System.out.println("Succesful!!");
        }  catch (IOException e){
        }
        /**
         * write into username .txt what did they do
         */
        try{
             Date date = cal.getTime();
                File f1 = new File("d:/a"+ID+".txt");
                File f2= new File("D:/infor.txt");
                FileWriter fw1 = new FileWriter(f1.getAbsoluteFile(),true);
                BufferedWriter bw1= new BufferedWriter(fw1);
                FileWriter fw2 = new FileWriter(f2.getAbsoluteFile(),true);
                BufferedWriter bw2= new BufferedWriter(fw2);
                chuyen="CHUYEN TIEN\nChuyen tu tai khoan: "+ID+"\nDen tai khoan: "+IDchuyen+"\nSo tien: "+money+"\nVao: "+date+"\nSo du tai khoan:"+t+"\n-------------------------";
                bw1.write(chuyen);
                bw1.write("\n");
                bw1.close();
                fw1.close();
                bw2.write(chuyen);
                bw2.write("\n");
                bw2.close();
                fw2.close();
        }catch(IOException b){
            }
    }
    /**
     * @throws FileNotFoundException 
     * thought this method, only Admin can create new account for user
     */
     static void createAccount() throws FileNotFoundException{
        String taikhoan;
        String matkhau;
        String name;
        String sdt;
        String gioitinh;
        String diachi;
        String sodu;
        boolean flag;
        int check=0;
        in.nextLine();
        //check, if account is existed so enter again
        do{
            check=0;
            System.out.print("Enter new account : ");
            taikhoan = in.nextLine();
            for(int j=0;j<tien.size();j++){
                if(tien.get(j).equals(taikhoan)){
                    check=1;
                    System.out.println("Account exist!!");
                    break;
                }
            }
        }while(check==1);
        System.out.print("PIN: ");
        /**
         * check PIN
         * if PIN is existed so random new PIN
         */
        do{
            flag = true;
            Random rd = new Random(); 
            matkhau = ""+rd.nextInt(10000);
            if(matkhau.matches("\\d{4}") == true){
                flag = true;
                System.out.println(matkhau);
            }
            else{
                flag = false;
            }
            flag = ad.check(matkhau);
        }while(flag == false);
        System.out.print("Enter your name : ");
        name = in.nextLine();
        /**
         * Admin must enter gender of user is 'male' or 'female'
         */
        do{
            flag = true;
            System.out.print("Enter user gender: ");
            gioitinh = in.nextLine();
            if(gioitinh.equalsIgnoreCase("male")){
                flag = true;
            }
             else if(gioitinh.equalsIgnoreCase("female")){
                flag = true;
            }
            else {
                flag = false;
            }
            if(flag == false){
                System.out.println("Gender is male or female !");
            }
        }while(flag == false);
        /**
         * check phone number must begin by 0 and its have 10 digits
         */
        do{
            flag = true;
            System.out.print("Enter user phone number:");
            sdt = in.nextLine();
            flag = sdt.matches("^[0]\\d{9}");
            if(flag == false){
                System.out.println("Phone number must be 10 digit and become by 0 !");
            }
        }while(flag == false);
        System.out.print("Enter your address:");
        diachi=in.nextLine();
        sodu="0";
        //add information into file
        use.add(new User(taikhoan,matkhau,name,sdt,gioitinh,diachi,sodu));
        nhap();
     o++;
    }
     /**
      * add all information of user into file name user.txt
      */
    static void nhap(){
        User data = use.get(o);
        try{
        File file= new File("D:/user.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter as;  
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    File f= new File("D:/sodu.txt");
                    FileWriter fs = new FileWriter(f.getAbsoluteFile(),true);
                    as = new BufferedWriter(fs);
                    bw.write(data.getTaikhoan());
                    bw.write("\n");
                    as.write(data.getTaikhoan());
                    as.write("\n");
                    bw.write(data.getMatkhau());
                    bw.write("\n");
                    bw.write(data.getName());
                    bw.write("\n");
                    bw.write(data.getSdt());
                    bw.write("\n");
                    bw.write(data.getGioitinh());
                    bw.write("\n");
                    bw.write(data.getDiachi());bw.write("\n");
                    as.write(data.getsodu());as.write("\n");
                }
     as.close();
     }
     catch(IOException e){
     }
    }
    /**
     * load all information in file into two ArrayList is 'load' and 'tien'
     */
    static void search(){
        load.removeAll(load);
        tien.removeAll(tien);
        int k=0,j=0;
        try { 
            FileInputStream f = new FileInputStream("D:/user.txt");
            BufferedReader mn;       
            try (BufferedReader br = new BufferedReader(new InputStreamReader(f))) {
                FileInputStream st = new FileInputStream("D:/sodu.txt");
                mn = new BufferedReader(new InputStreamReader(st));
                do{
                    load.add(br.readLine());//luu tung dong cua file vao arraylist
                    j++;
                }while(load.get(j-1)!=null);
                do{
                    tien.add(mn.readLine());
                    k++;
                }while(tien.get(k-1)!=null);
                load.remove(j-1);//xoa bo gia tri null
                tien.remove(k-1);
            }
        mn.close();
    }catch(IOException e) {
    }catch(Exception ex){
    } 
    }
    static void input(){
        for(int i=0;i<load.size();i++){  
            System.out.println(""+ load.get(load.size()-1));
        }
    }
    /**
     * @throws FileNotFoundException
     * @throws IOException 
     * menu of admin
     * admin can create new account for user
     * display all informations of user
     * display all actions of user
     * and add money to account
     */
    static void menu() throws FileNotFoundException, IOException {
         int nhap;
        do{ 
        System.out.println("-----Menu of Admin------");
        System.out.println("1. Create user account");
        System.out.println("2. Report user information");
        System.out.println("3. Report user action");
        System.out.println("4. Withdrawal to user account");
        System.out.println("5. Log out admin account");
        System.out.print("Enter choice: ");
        nhap=in.nextInt();
        switch(nhap)
        {
            case 1:
                createAccount();
                break;
            case 2:
                ad.search2();
                break;
            case 3:
                ad.action();
                break;
            case 4:
                naptien();
                break;
            case 5:
                main_menu();
                break;
        }
      }while(true);
    }
    /**
     * @throws IOException 
     * this is main menu to call all method of ATM
     */
    protected static void main_menu() throws IOException{
        int ch;
        do{
            search();
            System.out.println("*****MENU*****");
            System.out.println("1. Login account admin.");
            System.out.println("2. Login account user.");
            System.out.println("3. Exit ATM.");
            System.out.print("Your choice is: ");
            ch=in.nextInt();
            switch(ch){
                case 1:             
                    ad.dangnhap();
                    
                    break;
                case 2:
                    display();
                    break;
                case 3:
                   exit(0);
                default:
                   System.out.println("Choose number in menu !");
            }
        }while(true);
    }
}
