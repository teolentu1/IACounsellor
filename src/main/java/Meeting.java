
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
 *
 * @author teo
 */
public class Meeting implements Comparable<Meeting>, Serializable {
    private Student stu;
    private String notes;
    private String time;
    private String date;
    private String status;
    public static LinkedList<Meeting> allMeet = new LinkedList<Meeting>();

    
    public Meeting(Student stu, String date, String time, String notes) {
        this.stu = stu;
        this.notes = notes;
        this.date = date;
        this.time = time;
        this.status = "pending";

        allMeet.add(this);
        Collections.sort(allMeet);
    }

    public String getNotes() {
        return notes;
    }

    public Student getStu() {
        return stu;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
    
    public void approve() {
        this.status = "approved";
    }
    
    public void cancel() {
        this.status = "cancelled";
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int compareTo(Meeting that) {
        return this.date.compareTo(that.date);
    }
    
    public static synchronized void saveToFile() {
        File db = new File("AllMeetList8.txt");
        if (db.exists()) {
            db.delete();
        }

        FileOutputStream f = null;
        ObjectOutputStream o = null;

        try {
            // CREATE FILE STREAM
            f = new FileOutputStream(db);
            o = new ObjectOutputStream(f);
            o.writeObject(allMeet);

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
        File db = new File("AllMeetList8.txt");
        if (!db.exists() || !db.isFile()) {
            return;
        }

        FileInputStream f = null;
        ObjectInputStream o = null;
        try {
            f = new FileInputStream(db);
            o = new ObjectInputStream(f);
            Meeting.allMeet = (LinkedList<Meeting>) o.readObject();
        } catch (IOException e) {
            System.out.println("Could not read meeting list from file.");
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
