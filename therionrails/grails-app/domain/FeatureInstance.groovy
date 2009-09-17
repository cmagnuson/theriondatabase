class FeatureInstance {

    static constraints = {
    	feature(nullable:false)
    	rotation(min:-360, max:360, nullable:true)
    	centerOffset(nullable:true)
    	surveyConnection(nullable:true) //...was... required to make it easier to do things for station (if we know the direction...)
    	surveyStation(nullable:true)    //if this is true, we are operating on a connection, if not then on a station
    }
    
    SurveyConnection surveyConnection;
    SurveyStation surveyStation;
    Feature feature;
    int rotation = 0;
    double centerOffset = 0;
    def attributes = [:];
    
    def generateScrapString(){
		return feature.generateScrapString(this)
   	}	
    
    public String toString(){
    	return feature.toString()+"  SS:"+surveyStation+" SC:"+surveyConnection;
    }
    
    public double inferRotation(){
    	SurveyConnection con = surveyConnection;
    	if(con==null){
    		con = SurveyConnection.findByToStation(surveyStation)
    	}
    	if(con.compass<0){
    		return con.compass+360;
    	}
    	return con.compass;
    }
    
}
