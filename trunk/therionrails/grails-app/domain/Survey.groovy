class Survey {

    static constraints = {
    	video(nullable:true, maxSize:2097152000)
    }
   static mapping = { 
    //  columns { 
    //      video type:'blob' 
    //  } 
   } 
    
	Date dateentered;
	Date datesurveyed;
	String team;
	String title;
	String note;
	TunnelSystem system;
	byte[] video;
	
    static hasMany = [surveyStations : SurveyStation, surveyConnections : SurveyConnection]

	public String toString(){
		return system.toString()+": "+title;
	}
}
