class SurveyStation {

	static transients = {
		["scrapX", "scrapY"]
	}
    static constraints = {
    	scrapX(nullable:true)
    	scrapY(nullable:true)
    }
    
    static belongsTo = [survey:Survey]
	String name;
    String note;
    Date timeMeasured;
    
    double scrapX
    double scrapY
    
    public String toString(){
    	survey.title+" : "+name
    }
}