/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.text.ParseException;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Bartek
 */
public class TimeFormat {

    public static XMLGregorianCalendar getDateFromString(String date) throws ParseException, DatatypeConfigurationException {

        
        String[] splitDate = date.split(" ");
        
        String daysDate = null;
        daysDate = splitDate[0];
        String[] splitDays = daysDate.split("-");

        String hourDate = null;
        hourDate = splitDate[1];
        String[] splitHour = hourDate.split(":");

        int year = Integer.parseInt(splitDays[0]);
        int month = (Integer.parseInt(splitDays[1]));
        int days = Integer.parseInt(splitDays[2]);

        int hour = Integer.parseInt(splitHour[0]);
        int minutes = Integer.parseInt(splitHour[1]);

        // dates go in properly
        GregorianCalendar dateConverted = new GregorianCalendar(year, month-1, days, hour, minutes, 0);
        XMLGregorianCalendar date2 = null;
        date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateConverted);
        
        
        return date2;
    }
}
