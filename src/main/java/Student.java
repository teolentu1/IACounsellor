
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author teo
 */
public class Student implements Serializable {

    private String usName;
    private String passWrd;
    public LinkedList<Meeting> meetList;
    public LinkedList<String> uniList;
    public static LinkedList<Student> stuList = new LinkedList<Student>();
    public Meeting cancelledMeet;

    public Student(String usName, String passWrd) {
        this.usName = usName;
        this.passWrd = passWrd;
        this.meetList = new LinkedList<Meeting>();
        this.uniList = new LinkedList<String>();
        stuList.add(this);
    }

    public String getUsName() {
        return this.usName;
    }

    public String getPassWrd() {
        return this.passWrd;
    }

    public void addMeet(Meeting meet) {
        meetList.add(meet);
        Collections.sort(meetList);
    }

    public static synchronized void saveToFile() {
        File db = new File("StuList8.txt");
        if (db.exists()) {
            db.delete();
        }

        FileOutputStream f = null;
        ObjectOutputStream o = null;

        try {
            // CREATE FILE STREAM
            f = new FileOutputStream(db);
            o = new ObjectOutputStream(f);
            o.writeObject(stuList);

            // CLOSE FILE STREAM
            o.close();
            f.close();
            System.out.println("Saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        } finally {
            try {
                if (o != null) {
                    o.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException e) {
                System.out.println("Could not close output streams.");
            }
            System.out.println("Finished saving to file.");
        }
    }

    public static synchronized void loadFromFile() {
        File db = new File("StuList8.txt");
        if (!db.exists() || !db.isFile()) {
            return;
        }

        FileInputStream f = null;
        ObjectInputStream o = null;
        try {
            f = new FileInputStream(db);
            o = new ObjectInputStream(f);
            Student.stuList = (LinkedList<Student>) o.readObject();
        } catch (IOException e) {
            System.out.println("Could not read student list from file.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (o != null) {
                    o.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException e) {
                System.out.println("Could not close input streams.");
            }
        }
    }

}
