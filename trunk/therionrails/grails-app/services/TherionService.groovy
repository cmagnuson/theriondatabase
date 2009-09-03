import org.springframework.beans.factory.InitializingBean
import org.codehaus.groovy.grails.commons.ApplicationHolder 

class TherionService implements InitializingBean
{
	def grailsApplication
	def setting
	def servletContext
	def PATH

	void afterPropertiesSet()
	{
		this.setting = grailsApplication.config.setting
		this.servletContext = ApplicationHolder.getApplication().getParentContext().getServletContext()
		this.PATH = servletContext.getRealPath("/")+"/generated/source/"
	}

	def void exportSurvey(){
		createThconfig()
		createLayout()
		createMain()
		createLevels()
		createSurveys()
	}

	def void processInTherion(){


	}

	private void createSurveys(){
		for(Survey s: Survey.list()){
			String output = "encoding  utf-8\n" + 
			" \n";
			output+=" survey "+s.system.name+"-"+s.id+"  \\\n";
			output+="   -title \""+s.title+"\" \\\n\n";
			output+="   centerline\n" + 
			" units length 1 meter\n" + 
			"     team \""+s.team+"\"\n" + 
			"     date "+s.datesurveyed+"\n" + 
			"     walls on\n" + 
			"     cs UTM15\n\n" +
			"# "+s.note+"\n";

			for(SurveyStation ss: s.surveyStations){
				if(ss.note!=null && ss.note.trim().size()>0){
					output+="# "+ss.name+" - "+ss.note+"\n";
				}
			}

			output+="\n sd length .05 meter        \n" + 
			"  sd compass .5 degrees\n" + 
			"  sd clino .5 degree  \n" + 
			"  data normal from to length compass clino left right up down\n";

			for(SurveyConnection sc: s.surveyConnections){
				output+=sc.fromStation+" "+sc.toStation+" "+sc.length+" "+sc.compass+" "+sc.clino+" "+sc.left+" "+sc.right+" "+sc.up+" "+sc.down+"\n";
			}
			output+="      \n" + 
			"   endcenterline\n" + 
			" \n" + 
			" endsurvey\n";
			File f = new File(PATH+"levels/"+s.tunnelSystem.name+"-"+s.name+".th");
			f.setText(output);
		}
	}

	private void createLevels(){
		String level = "encoding  utf-8\n" + 
		"\n" + 
		"survey main \n" + 
		"\n";
		for(TunnelSystem ts: TunnelSystem.list()){
			level+=ts.name+".th\n";
		}
		level+="endsurvey\n";

		File f = new File(PATH+"levels/levels.th");
		f.setText(level);

		for(TunnelSystem ts: TunnelSystem.list()){
			String output = "encoding  utf-8\n" + 
			" survey "+ts.name+"  \\\n" + 
			"   -title \""+ts.name+"\" \\\n" + 
			"   -attr system "+ts.name+" \\\n" + 
			"   -attr "+ts.name+" 1\n" + 
			"   \n";
			for(Survey s: ts.surveys){
				output+="input "+ts.name+"-"+s.id+"\n";
			}
			output+= "\n" + 
			" endsurvey\n" + 
			"\n";
			f = new File(PATH+"levels/"+ts.name+".th");
			f.setText(output);
		}
	}

	private createMain(){
		String main = "encoding  utf-8\n" + 
		" \n" + 
		" survey Main  \n" + 
		"   \n" + 
		"   input levels/levels\n" + 
		"   endmap\n" + 
		"   \n" + 
		" endsurvey\n";
		File f = new File(PATH+"main.th");
		f.setText(main);   
	}

	private createLayout(){
		String layout = "encoding  utf-8\n" + 
		"layout lab-header\n" + 
		" \n" + 
		"  map-header 0 100 sw\n" + 
		"   \n" + 
		"  units imperial\n" + 
		"  legend on #off\n" + 
		"  color-legend on\n" + 
		"  symbol-show point flag:continuation \n" + 
		"  statistics explo all\n" + 
		"  scale-bar 50 ft\n" + 
		"  surface bottom\n" + 
		"  surface-opacity 90\n" + 
		"  scale 1 250\n" + 
		"  grid-size 1 1 1 m\n" + 
		"\n" + 
		"transparency on\n" + 
		"  colour map-fg [80 80 80]\n" + 
		"  colour map-bg [70 90 70]\n" + 
		"\n" + 
		"  code metapost\n" + 
		"\n" + 
		"  def l_survey_cave (expr p) =\n" + 
		"    draw p withpen PenD withcolor (0.5,0.3,0.3);\n" + 
		"  enddef;\n" + 
		"  \n" + 
		"  code metapost \n" + 
		"def p_continuation(expr pos,theta,sc,al) = \n" + 
		"% draw default continuation symbol \n" + 
		"p_continuation_UIS(pos,theta,sc,al); \n" + 
		"% if text attribute is set \n" + 
		"if known(ATTR__text) and picture(ATTR__text): \n" + 
		"% set labeling color to light orange \n" + 
		"push_label_fill_color(1.0, 0.9, 0.8); \n" + 
		"% draw filled label with text next to ? \n" + 
		"p_label.urt(ATTR__text,(.5u,-.25u) transformed T,0.0,8); \n" + 
		"% restore original labeling color \n" + 
		"pop_label_fill_color; \n" + 
		"fi; \n" + 
		"enddef; \n" + 
		"endcode \n"
		File f = new File(PATH+"layout.th");
		f.setText(layout);
	}

	private void createThconfig(){
		String config = "encoding  utf-8\n" + 
		"\n" + 
		"input layout.th\n" + 
		"source main.th\n" + 
		"\n" + 
		"source          # additional source data \n" + 
		"map all_map     # map that contains centerline and scrap map \n" + 
		" #                 note@Main     # notes\n" + 
		"      Main             # survey centerline data below scraps \n" + 
		"endmap          # end of map \n" + 
		"endsource       # end of additional source data \n" + 
		"select all_map  # select this all_map for output \n" + 
		"export model -format esri -o generated/output/esri\n" + 
		"export model -format kml -o generated/output/cave.kml\n" + 
		"export model -format dxf -o generated/output/cave.dxf\n" + 
		"export model -format vrml -o generated/output/cave.vrml\n" + 
		"export map   -format kml  -o  generated/output/cavemap.kml\n" + 
		"export model -o generated/output/lab.lox\n" + 
		"#export map  -projection plan -o generted/output/cave_m.pdf \n" + 
		"export continuation-list -o generated/output/continuation.txt\n" + 
		"#export atlas -projection plan -o generated/output/cave_a.pdf\n" + 
		"export map -projection plan -layout lab-header -output generated/output/cave.pdf\n" + 
		"\n" + 
		"layout xvi-export\n" + 
		"  scale 1 100\n" + 
		"  grid-size 1 1 1 m\n" + 
		"endlayout\n" + 
		"export map -fmt xvi -layout xvi-export -o generated/output/cave.xvi\n";
		File f = new File(PATH+"thconfig");
		f.setText(config); 
	}
}