class Equate {

    static constraints = {
    }
    
    static hasMany = [stations : SurveyStation]
    String note

	public String toString(){
		String ret = "";
		ret+=note+": ";
		if(stations==null || stations.size()==0){
			return ret+"No Stations";
		}
		else{
			for(SurveyStation s: stations){
				ret+=s.name+"."+s.survey.title+"<->";
			}
			return ret.substring(0, ret.size()-3);
		}
	}    
}
