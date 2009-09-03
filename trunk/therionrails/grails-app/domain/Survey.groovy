class Survey {

    static constraints = {
    }
    
	Date dateentered;
	Date datesurveyed;
	String team;
	String title;
	String note;
	TunnelSystem system;
	
    static hasMany = [surveyStations : SurveyStation, surveyConnections : SurveyConnection]

	public String toString(){
		return system.toString()+": "+title;
	}
}
