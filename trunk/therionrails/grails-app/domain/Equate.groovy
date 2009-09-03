class Equate {

    static constraints = {
    }
    
    static hasMany = [stations : SurveyStation]
    String note

	public String toString(){
		String ret = "";
		if(stations==null || stations.size()==0){
			return "No Stations";
		}
		else{
			for(SurveyStation s: stations){
				ret+=s.name+"."+s.survey.title+"<->";
			}
			return ret.substring(0, ret.size()-3);
		}
	}    
}
