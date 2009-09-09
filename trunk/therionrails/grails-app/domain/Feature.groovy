class Feature {

    static constraints = {
     name()
     metapostCode()
     postMetapostCode()
     evalScrapString()
    }
    
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
