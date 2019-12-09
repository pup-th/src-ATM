
package assignment_final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Admin {
//create variable and contructor for it
    public Scanner in=new Scanner(System.in);
    static String ten_tk = new String();
    static String mk = new String();
    static ArrayList<String> information=new ArrayList<>();
    static ArrayList<String> in2=new ArrayList<>();
    public String getTen_tk() {
        return ten_tk;
    }

    public void setTen_tk(String ten_tk) {
        this.ten_tk = ten_tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }
    
/**
 * 
 * @throws FileNotFoundException
 * @throws java.io.IOException
 * @throws IOException 
 * method to admin can enter their account
 */
    protected void dangnhap() throws FileNotFoundException, java.io.IOException, IOException {
        boolean fla = true;
       String s;
       do{
            fla = true;
            System.out.println("-----Welcome to ATM-----");
            System.out.println("Enter account admin!");
            //account of admin is permanent
            //and its have been stored in file admin.txt
            do{
                System.out.print("Name account:  ");
                ten_tk = in.nextLine();
                if(ten_tk.matches("\\w{6}")){
                    fla = true;
                }
                else{
                    System.out.println("Name account include 6 words !");
                    System.out.println("Please enter again !");
                    fla = false;
                }
            }while(fla == false);
            //check admin account 
            System.out.print("Password: ");
            mk = in.nextLine();
            String chuoi = ten_tk+"$"+mk;
            FileReader fr = new FileReader("D:/admin.txt");
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br);
            while(sc.hasNext() == true){
                if( (sc.next().compareTo(chuoi)) == 0){
                    fla=true;
                    Main_ATM obj = new Main_ATM();
                    obj.menu();
                    sc.close();
                    br.close();
                    fr.close();
                }
                else {
                    fla = false;
                }
            }
            if(fla == false){
                System.out.println("Wrong Admin account !");
                System.out.println("Enter again !");
            }
        }while(fla == false);
    }
    /**
     * 
     * @param check is string entered to check is it existed in file 
     * @return true if check is existed, else, false
     * @throws FileNotFoundException 
     */
    protected static boolean check(String check) throws FileNotFoundException{
        boolean fla = true;
        File f =new File("D:/user.txt");
        boolean flag = f.isFile();
        if(flag ==true){
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader (fr);
            Scanner sc = new Scanner(br);
            while(sc.hasNext()){
                if(sc.next().matches(check)){
                    fla = false;
                }
            }
            return fla;
        }
        else{
            fla = true;
        }
        return fla;
    }
    public void search2(){
        information.removeAll(information);
        in2.removeAll(in2);
        int k=0,j=0;
        try { 
            FileInputStream f = new FileInputStream("D:/user.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(f));       
            FileInputStream st = new FileInputStream("D:/sodu.txt");
            BufferedReader mn = new BufferedReader(new InputStreamReader(st));      
            do{ 
                information.add(br.readLine());//luu tung dong cua file vao arraylist
                j++;
            }while(information.get(j-1)!=null); 
           do{ 
                in2.add(mn.readLine());
                k++;
            }while(in2.get(k-1)!=null);
            information.remove(j-1);//xoa bo gia tri null
            in2.remove(k-1);
            br.close();
            mn.close();
    }catch(java.io.IOException e) {
    }catch(Exception ex){
    } 
        output();
    }
    //output all informaiton of user
    static void output(){
    for(int i=0;i<information.size();i=i+6){
        System.out.println("++++++++++++++++++++++++++++++");
                System.out.println("Acount:"+information.get(i));
                System.out.println("Pin:"+information.get(i+1));
                System.out.println("name:"+information.get(i+2));
                System.out.println("Phonenumber:"+information.get(i+3));
                System.out.println("Gender:"+information.get(i+4));
                System.out.println("Address:"+information.get(i+5));
                for(int j=0;j<in2.size();j++){
                    if(in2.get(j).equals(information.get(i))){
                System.out.println("Balance:"+in2.get(j+1));
                }      
                }
            }
        }
    /**
     * 
     * @throws FileNotFoundException
     * @throws java.io.IOException 
     * write all actions of user into their name .txt
     */
    public void action() throws FileNotFoundException, java.io.IOException{
        System.out.println("===BIEN NHAN===");
        
        File file = new File("D:infor.txt");
        boolean check = file.isFile();
        if(check == true){
            FileInputStream f = new FileInputStream("d:/infor.txt");
            int ch;
            StringBuffer buff=new StringBuffer();
            while((ch=f.read())>-1){
                buff.append((char)ch);
            }
            System.out.println(""+buff.toString());
            }

        else{
                System.out.println("No action !");
        }
        System.out.println("===============");
    }
}
