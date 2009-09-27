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
    Date dateCreated;
    
    double scrapX
    double scrapY
    static hasMany = [featureInstances : FeatureInstance]

    
    public String toString(){
    	survey.title+" : "+name
    }
}