class SurveyStation {

    static constraints = {
    }
    
    static belongsTo = [survey:Survey]
	String name;
    String note;
    Date timeMeasured;
    
    
    public String toString(){
    	survey.title+" : "+name
    }
}