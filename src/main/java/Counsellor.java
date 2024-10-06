
import java.util.LinkedList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author teo
 */
public class Counsellor {
    private String usName = "Ms. Perky";
    private String passWrd = "Padua High";
    
    public Counsellor(String usName, String passWrd) {
        this.usName = usName;
        this.passWrd = passWrd;
    }
    
    public String getUsName() {
        return this.usName;
    }
    
    public String getPassWrd() {
        return this.passWrd;
    }
}
