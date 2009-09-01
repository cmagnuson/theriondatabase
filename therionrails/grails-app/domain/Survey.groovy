class Survey {

    static constraints = {
    }
    
	Date dateentered;
	Date datesurveyed;
	String team;
	String title;
	String note;
	TunnelSystem system;
	
    static hasMany = [surveyStation : SurveyStation]

	public String toString(){
		return system.toString()+": "+title;
	}
}
