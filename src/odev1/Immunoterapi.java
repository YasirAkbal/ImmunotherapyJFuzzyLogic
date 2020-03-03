/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author YASIR
 */
public class Immunoterapi {
    static FIS fis;
    double age;
    double time;
    double now;
    double type;
    
    static{
        File dosya = null;
        try {
            dosya = new File(Immunoterapi.class.getResource("model.fcl").toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(Immunoterapi.class.getName()).log(Level.SEVERE, null, ex);
        }
        fis = FIS.load(dosya.getPath(), true);
    }
    
    public Immunoterapi()
    {
        
    }
    
    public Immunoterapi(double age, double time, double now, double type) {
        this.age = age;
        this.time = time;
        this.now = now;
        this.type = type;
        
        fis.setVariable("age", age);
        fis.setVariable("time", time);
        fis.setVariable("now", now);
        fis.setVariable("type", type);
        fis.evaluate(); 
    }
    
    public double dondur()
    {
        return Double.valueOf(fis.getVariable("sonuc").getValue());
    }

    @Override
    public String toString() {
        String cikti = "SONUC = " + fis.getVariable("sonuc").getValue();
        return cikti;
    }
}
