/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jFuzzyLogic.plot.*;

/**
 *
 * @author YASIR
 */
public class Odev1 {

       
    public static void main(String[] args) {    
        double mse = 0;
        ArrayList<TutucuSinif> dizi = new ArrayList<>();
        int girdiSayisi = 0;
        
        FileReader fileReader = null;
        try {
            String line;
            File file = null;
            try {
                file = new File((new Odev1()).getClass().getResource("veriler.txt").toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(Odev1.class.getName()).log(Level.SEVERE, null, ex);
            }
            fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            try {
                while ((line = br.readLine()) != null) {
                    
                    String[] ayrac = line.split("\\s+");
                    dizi.add(new TutucuSinif(Integer.parseInt(ayrac[0]),Integer.parseInt(ayrac[1]),Double.parseDouble(ayrac[2]),
                            Integer.parseInt(ayrac[3]),Integer.parseInt(ayrac[4]),Integer.parseInt(ayrac[5]),Integer.parseInt(ayrac[6]),Integer.parseInt(ayrac[7]))); 
                }
            } catch (IOException ex) {
                Logger.getLogger(Odev1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Odev1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Odev1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //System.out.format("%7s%7s%7s%7s%7s%7s%7s%7s%8s\n","Sex","Age","Time","Now","Type","Area","Indur","Result","Bulanik");
        for(TutucuSinif tutucu : dizi)
        {
            Immunoterapi im =  new Immunoterapi(tutucu.age,tutucu.time,tutucu.now,tutucu.type);
            mse += Math.pow((tutucu.result - im.dondur()), 2);
            girdiSayisi++;
            //System.out.format("%7d%7d%7.2f%7d%7d%7d%7d%7d%8.3f\n", tutucu.sex,tutucu.age,tutucu.time,tutucu.now,tutucu.type,tutucu.area,tutucu.induration,tutucu.result,im.dondur());
        }   
        
        mse = mse / girdiSayisi;
        System.out.println("MSE = " + mse); 
        //JFuzzyChart.get().chart(Immunoterapi.fis);     
    }   
}
