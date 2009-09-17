class Feature {

	static mapping = {
		metapostCode type:"text" 
		postMetapostCode type:"text" 
		evalScrapString type:"text" 
	}

    static constraints = {
     name()
     metapostCode()
     postMetapostCode()
     evalScrapString()
     lining(nullable:false)
    }
    
    boolean lining = false;
    String name;
    String metapostCode;  
    String postMetapostCode;
    String evalScrapString; //groovy code which for a method named evalScrap which takes a featureInstance as input
    						//in this way custom attributes can be used for different features without the need to
    						//create explicit classes for different features
    						
   	def generateScrapString(FeatureInstance fi){
   		GroovyShell shell = new GroovyShell();
   		Script script = shell.parse(evalScrapString)
	   	return script.invokeMethod("evalScrap", fi);
   	}	

	public String toString(){
		return name;
	}
    
}
