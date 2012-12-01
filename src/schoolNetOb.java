/*
 * schoolNetOb.java
 *  Class to handle parsing and storing the observation from a schoolSite
 *  perhaps this isn't as good as it should be
 * Created on May 3, 2003, 10:44 PM
 */

/**
 *
 * @author  Daryl Herzmann
 */
public class schoolNetOb {
    
    public java.util.Date ts;
    public java.util.Hashtable data = new java.util.Hashtable(25);
    public String stationID = null;
    
    /** Creates a new instance of schoolNetOb */
    public schoolNetOb(String csv, String[] cols) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(csv, ",");
        //System.err.println( st.countTokens() );
        //System.err.println( csv);
        int i = 0;
        java.util.Hashtable h = new java.util.Hashtable(25);
        String vals[] = new java.lang.String[25];
        while(st.hasMoreTokens()){
            vals[i] = st.nextToken();
            if (vals[i].equalsIgnoreCase(" ")) vals[i] = "-99";
            h.put(cols[i], vals[i]);
            i = i + 1;
        }
        stationID = vals[0];
        data.put("tmpf", new snv( new java.lang.Integer( (String)h.get("tmpf") ), -30, 120 ) );
        data.put("sped", new snv( new java.lang.Integer( (String)h.get("sped") ), 0, 120 ));
        data.put("alti", new snv( new java.lang.Double( (String)h.get("alti") ), 28.00, 34.00));
        data.put("drctTxt", h.get("drctTxt") );
        data.put("feel", new snv( new java.lang.Integer( (String)h.get("feel") ), -60, 150) );
        data.put("pday", new snv( new java.lang.Double( (String)h.get("pday") ), 0.00, 15.00) );
        data.put("relh", new snv( new java.lang.Integer(  (String)h.get("relh") ), 1, 101) ); 
        data.put("max_speed", new snv( new java.lang.Integer( (String)h.get("max_sped") ), 0, 150) );
        data.put("max_drctTxt", h.get("max_drctTxt") );
        data.put("tmpf_max", new snv( new java.lang.Integer( (String)h.get("tmpf_max") ), -20, 120) );
        data.put("tmpf_min", new snv( new java.lang.Integer( (String)h.get("tmpf_min") ), -40, 100) );  
        data.put("dwpf", new snv( new java.lang.Integer( (String)h.get("dwpf") ), -30, 100) );
        data.put("ts", h.get("ts") );
    }
    
    public java.util.Date getTS() {
      long secs = new java.lang.Long(getTextTicks()).longValue();
      java.util.Date ts = new java.util.Date(secs * 1000);
      return ts;
    }
    public String getTextTicks(){
        return (String)this.data.get("ts");
    }
    public long getTicks(){
     return new java.lang.Long(getTextTicks()).longValue();   
    }
    /**
     * Accessor for the current Air Temperature
     * @return snv value for current Air Temperature
     */
    public snv getAirTemperature() {
     return (snv) data.get("tmpf");   
    }
    public snv getWindSpeed(){
     return (snv) data.get("sped");
    }
    /**
     * Accessor for the station pressure value
     */
    public snv getPressure(){
     return (snv) data.get("alti");
    }
    public String getWindDirectionText(){
        return (String) data.get("drctTxt");
    }
    /**
     * Accessor for the Feels Like temperature
     */
    public snv getFeelsLike(){
     return  (snv) data.get("feel");
    }
    /**
     * Accessor for Today's rainfall
     */
    public snv getTodayRainfall(){
      return  (snv) data.get("pday");
    }

   /**
    * Accessor for the current Humidity
    */    
    public snv getHumidity(){
     return   (snv) data.get("relh");
    }
   /**
    * Accessor for wind gust
    */
    public snv getGust(){
     return  (snv) data.get("max_speed");
    }
  
    public String getGustDirection(){
     return (String) data.get("max_drctTxt");   
    }
    /**
     * Accessor for Maximum Daily Air Temperature
     * @return snv value
     */
    public snv getMaxAirTemperature(){
        return (snv) data.get("tmpf_max");
          
    }
    /**
     * Accessor for Minimum Air Temperature
     */
    public snv getMinAirTemperature(){
     return  (snv) data.get("tmpf_min");
    }
    
    /**
     * Accessor for the Dew Point
     */
    public snv getDewPoint(){
     return   (snv) data.get("dwpf"); 
    }
}
