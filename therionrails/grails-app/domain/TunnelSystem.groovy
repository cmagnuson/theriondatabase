class TunnelSystem {

    static constraints = {
    	name()
    	color()
    }
    
    String name
    String color
    static hasMany = [surveys : Survey]

	public String toString(){
		return name;
	}    
}
