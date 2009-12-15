/*
 * $Id$
 * $URL$
 *
 *
 *==================================================================================
 * Copyright (c) 2009 the copyright is held jointly by the individual
 * authors. See the file AUTHORS for the list of authors.
 *
 * This file is part of jsbml, the pure java SBML library. Please visit
 * http://sbml.org for more information about SBML, and http://jsbml.sourceforge.net/
 * to get the latest version of jsbml.
 *
 * jsbml is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jsbml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jsbml.  If not, see <http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html>.
 *
 *===================================================================================
 *
 */

package org.sbml.jsbml.xml.stax;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 *
 * Format time to W3DTF
 *
 * @author Chen Li
 * @since 2.0
 *
 */
public class DateProcessor {

    public final static DateProcessor instance;

    static {

	instance = new DateProcessor();
    }

    /**
     * ISO 8601 [W3CDTF] date format for GMT
     */
    public final static SimpleDateFormat ISO_8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    private Pattern datePattern
            = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2}(\\.\\d{1,})?)?(\\+|-)\\d{2}(:\\d{2})?");
    
    private Pattern datePatternWithoutTimezoneInfo
            = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{1,2}(\\.\\d{1,})?)?");
    
    private Pattern datePatternWithMilitaryTimezone
            = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{1,2}(\\.\\d{1,})?)[A-Z]");
    
    private String[][] militaryTimezones = {
        {"A", "+01:00"},
        {"B", "+02:00"},
        {"C", "+03:00"},
        {"D", "+04:00"},
        {"E", "+05:00"},
        {"F", "+06:00"},
        {"G", "+07:00"},
        {"H", "+08:00"},
        {"I", "+09:00"},
        {"J", "+10:00"},
        {"K", "+11:00"},
        {"L", "+12:00"},
        {"M", "+13:00"},
        {"N", "-01:00"},
        {"O", "-02:00"},
        {"P", "-03:00"},
        {"Q", "-04:00"},
        {"R", "-05:00"},
        {"S", "-06:00"},
        {"T", "-07:00"},
        {"U", "-08:00"},
        {"V", "-09:00"},
        {"W", "-10:00"},
        {"X", "-11:00"},
        {"Y", "-12:00"},
        {"Z", "-00:00"}
    };

    /**
     *
     * Format datetime string to <a href="http://www.w3.org/TR/NOTE-datetime">W3CDTF</a>.
     *
     * @param datetime Datetime string.
     * @return datetime string in W3CDTF.
     *
     */
    public final String formatToW3CDTF(String datetime) {
        
        if ( datetime == null || datetime.trim().length() == 0 )
            throw new NullPointerException("Datetime can not be NULL.");

        if ( datePattern.matcher(datetime).matches() ) {
            return datetime;
        }

        if ( datePatternWithoutTimezoneInfo.matcher(datetime).matches() ) {
            return datetime + "+00:00";
        }

        if ( datePatternWithMilitaryTimezone.matcher(datetime).matches() ) {
            String militaryTimezone = datetime.substring( datetime.length() - 1 );
            for ( int i = 0; i < militaryTimezones.length; i++ ) {
                if ( militaryTimezone.equals(militaryTimezones[i][0]) ) {
                    return datetime.substring(0, datetime.length() - 1) + militaryTimezones[i][1];
                }
            }
        }

        try {
        	
        	System.out.println("DateProcessor : formatToW3CDTF(String) : date to parse : " + datetime);
        	
            Date date = ISO_8601_DATE_FORMAT.parse(datetime);
            return formatToW3CDTF(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Format datetime to <a href="http://www.w3.org/TR/NOTE-datetime">W3CDTF</a>.
     *
     * @param datetime Datetime.
     * @return datetime string in W3CDTF.
     *
     */
    public final String formatToW3CDTF(Timestamp datetime) {
        String datetimeStr = ISO_8601_DATE_FORMAT.format(datetime);
        return datetimeStr.substring(0, datetimeStr.lastIndexOf("00")) + ":00";
    }

    /**
     *
     * Format date to <a href="http://www.w3.org/TR/NOTE-datetime">W3CDTF</a>.
     *
     * @param datetime Datetime.
     * @return datetime string in W3CDTF.
     *
     */
    public final String formatToW3CDTF(Date datetime) {
    	
    	System.out.println("DateProcessor : formatToW3CDTF(Date) : date to parse : " + datetime);
    	
        String datetimeStr = ISO_8601_DATE_FORMAT.format(datetime);
        return datetimeStr.substring(0, datetimeStr.lastIndexOf("00")) + ":00";
    }
    
    /**
     *
     * Convert timezone of datetime string to GMT.
     *
     * @param datetime Datetime.
     * @return datetime string with GMT timezone.
     *
     */
    public final String convertToGMT(String datetime) {

        datetime = formatToW3CDTF(datetime);
        datetime = datetime.substring(0, datetime.lastIndexOf(":00")) + "00";
        ISO_8601_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            ISO_8601_DATE_FORMAT.format(ISO_8601_DATE_FORMAT.parse(datetime));
            return formatToW3CDTF(datetime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    
    /**
     *
     * Convert timezone of datetime string to GMT.
     *
     * @param datetime Datetime.
     * @return datetime string with GMT timezone.
     *
     */
    public final String convertToGMT(Timestamp datetime) {

        return convertToGMT( formatToW3CDTF(datetime) );

    }
    
    /**
     *
     * Convert timezone of datetime string to GMT.
     *
     * @param datetime Datetime.
     * @return datetime string with GMT timezone.
     *
     */
    public final String convertToGMT(Date datetime) {

        return convertToGMT( formatToW3CDTF(datetime) );

    }
    
    /**
     *
     * Convert timezone of datetime string to GMT.
     *
     * @param datetime Datetime.
     * @return datetime string with GMT timezone.
     *
     */
    public final Timestamp stringToTimestamp(String datetime) {
        
        datetime = convertToGMT(datetime);
        datetime = datetime.substring(0, datetime.lastIndexOf(":00")) + "00";
        
        try {

            Timestamp ts = new java.sql.Timestamp(ISO_8601_DATE_FORMAT.parse(datetime).getTime());
            return ts;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
