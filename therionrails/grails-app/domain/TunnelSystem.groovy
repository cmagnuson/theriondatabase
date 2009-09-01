class TunnelSystem {

    static constraints = {
    }
    
    String name
    String color
    static hasMany = [surveys : Survey]

	public String toString(){
		return name;
	}    
}
