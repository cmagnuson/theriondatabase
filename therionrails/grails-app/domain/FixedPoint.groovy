class FixedPoint {

    static constraints = {
    }
    
    static belongsTo = SurveyStation
    
    String note;
    SurveyStation station;
    double x;
    double y;
    double z;
    
    MeasurementMethod measurementMethod;
    
    public String toString(){
    	station.survey.toString()+station.toString()
    }
    
    def toTherionString(){
    	MeasurementMethod mm = measurementMethod;
    	return "fix "+station.name+" "+x+" "+y+" "+z+" "+mm.sd_x+" "+mm.sd_y+" "+mm.sd_z+" #"+note+"\n";
    }
}
