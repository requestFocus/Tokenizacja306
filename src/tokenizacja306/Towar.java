
package tokenizacja306;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Towar {
    
    public Towar() {
        this.cena = 0.0;
        this.nazwa = " ";
        this.dataWydania = new GregorianCalendar().getTime();
    }

    public Towar(double cena, String nazwa) {
        this();
        this.cena = cena;
        this.nazwa = nazwa;
    }

    public Towar(double cena, String nazwa, int rok, int miesiac, int dzien) {
        this(cena, nazwa);
        GregorianCalendar kalendarz = new GregorianCalendar(rok, miesiac - 1, dzien);
        dataWydania = kalendarz.getTime();
    }
    
    public double pobierzCene() {
        return this.cena;
    }
    
    public String pobierzNazwe() {
        return this.nazwa;
    }
    
    public Date pobierzDate() {
        return dataWydania;
    }
    
    public void ustawCene(double cena) {
        this.cena = cena;
    }
    
    public void ustawNazwe(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public void ustawDate(int r, int m, int dz) {
        GregorianCalendar kalendarz = new GregorianCalendar(r, m - 1, dz);
        this.dataWydania = kalendarz.getTime();
    }
    
    public void ustawDate(Date data) {
        this.dataWydania = data;
    }
    
    public String toString() {
        GregorianCalendar kalendarz = new GregorianCalendar();
        kalendarz.setTime(this.pobierzDate());
        return this.cena + " zł; nazwa: " + this.nazwa + ", " + kalendarz.get(Calendar.YEAR) + " rok, " + (kalendarz.get(Calendar.MONTH)+1) + " miesiąc, " + kalendarz.get(Calendar.DAY_OF_MONTH) + " dzień"; 
    }

    public static void zapiszDoPliku(Towar[] towar, PrintWriter outS) {
        outS.println(towar.length);
        GregorianCalendar kalendarz = new GregorianCalendar();
        
        for (int i = 0; i < towar.length; i++) {
            kalendarz.setTime(towar[i].pobierzDate());                          // po to, żeby korzystać z obiektu typu GregorianCalendar dla dat w obiektach Towar
            outS.println(towar[i].pobierzCene() + "|" + towar[i].pobierzNazwe() + "|"+ kalendarz.get(Calendar.YEAR) + "|" + (kalendarz.get(Calendar.MONTH)+1) + "|" + kalendarz.get(Calendar.DAY_OF_MONTH));
        }
    }
    
    public static Towar[] odczytajZPliku(BufferedReader inS) throws IOException {
        
        int dlugosc = Integer.parseInt(inS.readLine());
        Towar[] towar = new Towar[dlugosc];
        
        for (int i = 0; i < dlugosc; i++) {
            String linia = inS.readLine();
            StringTokenizer tokeny = new StringTokenizer(linia, "|");
            
            double cena = Double.parseDouble(tokeny.nextToken());
            String nazwa = tokeny.nextToken();
            int rok = Integer.parseInt(tokeny.nextToken()); 
            int miesiac = Integer.parseInt(tokeny.nextToken()); 
            int dzien = Integer.parseInt(tokeny.nextToken()); 
            towar[i] = new Towar(cena, nazwa, rok, miesiac, dzien);
        }
        return towar;
    }
    
    private double cena;                                // automatycznie inicjuje na 0.0
    private String nazwa;
    private Date dataWydania;
    
}
