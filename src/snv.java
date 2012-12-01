

public class snv extends java.lang.Object{
    
    private java.lang.Double myVal;
    
    /**
     * Constructor using a simple Integer as a calling value
     */
    public snv(java.lang.Integer I, int lowerBound, int upperBound){
        if (I.intValue() < lowerBound || I.intValue() > upperBound){
            myVal = null;
        } else {
          myVal = new java.lang.Double( I.doubleValue() );
        }
        }
    /**
     * Constructor using a simple Double and a bounding range
     */
    public snv(java.lang.Double F, double lowerBound, double upperBound){
        if (F.doubleValue() < lowerBound || F.doubleValue() > upperBound){
            myVal = null;
        } else {
        myVal = F;
        }
    }
    
    public java.lang.Integer getInteger(){
        if (myVal != null)
            return new java.lang.Integer( myVal.intValue() );
        else
            return null;
    }
    public java.lang.Integer getInteger(int i){
      if (myVal != null)
          return new java.lang.Integer( myVal.intValue() );
      else 
          return new java.lang.Integer(i);
    }
    public java.lang.Double getDouble(){
     if (myVal != null)
         return myVal;
     else
         return null;
    }
    public java.lang.Double getTableValue(double d){
     if (myVal == null) return null;
     return myVal;
    }
    public java.lang.Integer getTableValue(int i){
     if (myVal == null) return null;
     return new java.lang.Integer( myVal.intValue() );
    }
    public String viewString(){
     return viewString("0");   
    }
    
    public String viewWindString(){
        java.text.DecimalFormat df = new java.text.DecimalFormat("0");
      if (myVal == null) return "M";
      if (myVal.intValue() == 0) return "Calm";
      return df.format(myVal.doubleValue() );
    }
    
    public String viewString(String format){
     java.text.DecimalFormat df = new java.text.DecimalFormat(format);
     if (myVal == null) return "M";
     else { 
         return df.format(myVal.doubleValue() );
     }
    }
}
