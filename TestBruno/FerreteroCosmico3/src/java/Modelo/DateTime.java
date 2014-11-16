/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 *
 * @author usuario
 */
public class DateTime
{
    //atributo auxiliar
    private Date Fecha;
    final private Calendar DiaOK;
    
    
    public DateTime(String DiaFormateado, String Formato)
    {
        /*
        G 	Era designator 	Text 	AD
        y 	Year 	Year 	1996; 96
        Y 	Week year 	Year 	2009; 09
        M 	Month in year 	Month 	July; Jul; 07
        w 	Week in year 	Number 	27
        W 	Week in month 	Number 	2
        D 	Day in year 	Number 	189
        d 	Day in month 	Number 	10
        F 	Day of week in month 	Number 	2
        E 	Day name in week 	Text 	Tuesday; Tue
        u 	Day number of week (1 = Monday, ..., 7 = Sunday) 	Number 	1
        a 	Am/pm marker 	Text 	PM
        H 	Hour in day (0-23) 	Number 	0
        k 	Hour in day (1-24) 	Number 	24
        K 	Hour in am/pm (0-11) 	Number 	0
        h 	Hour in am/pm (1-12) 	Number 	12
        m 	Minute in hour 	Number 	30
        s 	Second in minute 	Number 	55
        S 	Millisecond 	Number 	978
        z 	Time zone 	General time zone 	Pacific Standard Time; PST; GMT-08:00
        Z 	Time zone 	RFC 822 time zone 	-0800
        X 	Time zone 	ISO 8601 time zone 	-08; -0800; -08:00
        */
        this.DiaOK = GregorianCalendar.getInstance();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(Formato);
        this.Fecha=sdf.parse(DiaFormateado, new ParsePosition(0));
        DiaOK.setTime(Fecha);
    }

    public DateTime() {
        this.DiaOK = GregorianCalendar.getInstance();
        this.Fecha = new Date();
        DiaOK.setTime(Fecha);
    }
    
    public DateTime(Date Nuevo)
    {
        this.DiaOK = GregorianCalendar.getInstance();
        DiaOK.setTime(Nuevo);
    }
    

    public Date getFecha() 
    {
        return DiaOK.getTime();
    }
    public void setFecha(Date Fecha) 
    {
        DiaOK.setTime(Fecha);
    }
    /*
    public Date addDias (int Dias)
    {       
        DiaOK.add(Calendar.DATE, Dias);
        Date retorno=DiaOK.getTime();
        DiaOK.setTime(Fecha);
        return retorno;
    }
    
    public int getAño()
    {
        int año= DiaOK.get(Calendar.YEAR);
        return año;
    }
    */
    public String TimeFormat (String format)
    {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String ret=sdf.format(DiaOK.getTime());
        return ret;
    }

    @Override
    public String toString() 
    {
        String ret;
        DiaOK.setTime(Fecha);
        int Dia=DiaOK.get(Calendar.DATE);
        Locale arg= new Locale("ES","AR");
        String Mes=DiaOK.getDisplayName(Calendar.MONTH, Calendar.LONG, arg);
        int Año=DiaOK.get(Calendar.YEAR);
        ret= Dia + " de " + Mes + " de " + Año;
        return ret;
    }
    
    

}
