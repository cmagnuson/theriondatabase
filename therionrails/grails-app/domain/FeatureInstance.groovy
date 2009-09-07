class FeatureInstance {

    static constraints = {
    	feature(nullable:false)
    	rotation(min:-360, max:360, nullable:false)
    	centerOffset(nullable:false)
    	surveyConnection(nullable:false) //required to make it easier to do things for station (if we know the direction...)
    	surveyStation(nullable:true)    //if this is true, we are operating on a connection, if not then on a station
    }
    
    SurveyConnection surveyConnection;
    SurveyStation surveyStation;
    Feature feature;
    int rotation = 0;
    double centerOffset = 0;
    def attributes = [:];
    
}
