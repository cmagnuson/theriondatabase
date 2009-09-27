import java.io.File;
import org.springframework.beans.factory.InitializingBean;

import sun.util.logging.resources.logging;
import org.codehaus.groovy.grails.commons.ApplicationHolder;


class FeatureImportService implements InitializingBean {
	
	boolean transactional = true
	
	def grailsApplication
	def setting
	def servletContext
	def PATH
	
	void afterPropertiesSet()
	{
		this.setting = grailsApplication.config.setting
		this.servletContext = ApplicationHolder.getApplication().getParentContext().getServletContext()
		this.PATH = servletContext.getRealPath("/")+"/WEB-INF/features/"
	}
	
	def importFromFiles(){
		File folder = new File(PATH);
		for(File f: folder.listFiles()){
			importFile(f);
		}
	}
	
	def importFile(File file){
		def text = file.getText();
		Feature f = new Feature();
		int start = 0;
		int end = 0;
		while(end<text.size()){
			start=end;
			end += calcEnd(text.substring(start, text.size()));
			log.trace "Start/End cut "+start+" "+end
			setProp(f, text.substring(start, end));
			end++;
		}
		
		log.info "Feature loaded from file: "+ f.toString();
		updateFeature(f)
	}

	private void setProp(Feature f, text){
		int propEnd = text.indexOf("=");
		String prop = text.substring(0,propEnd).trim();
		String value = text.substring(propEnd+1, text.size()).replaceAll("\\*","").trim();
		f[prop]=value;
	}
	
	private int calcEnd(String text){
		return text.indexOf("*", text.indexOf("*")+1);
	}

	def updateFeature(f){
		if(Feature.findByName(f.name)!=null){
			Feature fet = Feature.findByName(f.name);
			fet.properties = f.properties;
			fet.save()
		}
		else{
			f.save()
		}
	}
	
}
