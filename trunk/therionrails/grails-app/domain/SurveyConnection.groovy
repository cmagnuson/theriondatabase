class SurveyConnection {

    static constraints = {
    }
    
    static mapping = {
    	left column: 'leftDist'
    	right column: 'rightDist'
    }
    
    SurveyStation fromStation;
    SurveyStation toStation;
    static belongsTo =  [survey: Survey]
    double length;
    double compass;
    double clino;
    double left;
    double right;
    double up;
    double down;
    
    public String toString(){
    	fromStation.toString() +"->"+ toStation.toString()
    }
}
