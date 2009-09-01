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
}
