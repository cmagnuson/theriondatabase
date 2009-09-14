import java.io.BufferedReader;
import java.io.File;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.InitializingBean
import org.codehaus.groovy.grails.commons.ApplicationHolder 

class TherionService implements InitializingBean 
{

	public static final Double FEET_TO_METERS =  0.3048;

	def grailsApplication
	def setting
	def servletContext
	def PATH
	def OUTPUT_PATH

	void afterPropertiesSet()
	{
		this.setting = grailsApplication.config.setting
		this.servletContext = ApplicationHolder.getApplication().getParentContext().getServletContext()
		this.PATH = servletContext.getRealPath("/")+"/generated/source/"
		this.OUTPUT_PATH = "../output/";
	}

	def void exportSurvey(){
		checkDirectories()

		assignAllSurveyCords()
		createScraps()

		createThconfig()
		createLayout()
		createMain()
		createLevels()
		createSurveys()

		compile()
	}

	def String getSurveyXvi(Survey s){
		checkDirectories()

		saveSurvey(s, PATH+"singlesurvey.th")
		createSingleThconfig(s)		

		compile()

		//read xvi file from disk
		File f = new File(PATH+OUTPUT_PATH+"singlesurvey.xvi");
		return f.getText();
	}

	def void assignAllSurveyCords(){
		for(Survey s: Survey.list()){
			if(s.surveyConnections.size()>0 && s.surveyStations.size()>0){
				assignSurveyCords(s);
			}
		}
	}

	def void assignSurveyCords(Survey s){
		String xvi = getSurveyXvi(s);
		xvi = xvi.split("set XVIshots")[0];

		for(String line: xvi.split("\n")){
			line = line.trim();
			if(!line.startsWith("{")){
				continue;
			}
			line = line.replaceAll("\\{", "");
			line = line.replaceAll("\\}", "");
			line = line.trim();

			String[] parts = line.split("\\s+")
			if(parts==null || parts.length!=3)
				continue;

			double x = Double.valueOf(parts[0]);
			double y = Double.valueOf(parts[1]);
			String station = parts[2];

			SurveyStation ss = SurveyStation.findBySurveyAndName(s, station);
			ss.scrapX = x;
			ss.scrapY = y;
			ss.save();
		}
	}

	def void importSurvey(Survey surveyInstance, String rawData){
		String[] lines = rawData.split("\n")
		HashMap stations = new HashMap();
		for(String line: lines){
			line = line.trim();
			if(line.startsWith("#") || line.size()==0){
				continue;
			}
			String[] parts = line.split("\\s+")

			//add station to unique list of stations if not already in there
			if(!stations.containsKey(parts[0])){
				def ss = new SurveyStation(survey:surveyInstance, name:parts[0], note:"", timeMeasured:new Date())
				ss.save()
				stations.put(parts[0], ss)
			}
			if(!stations.containsKey(parts[1])){
				def ss = new SurveyStation(survey:surveyInstance, name:parts[1], note:"", timeMeasured:new Date())
				ss.save();
				stations.put(parts[1], ss)
			}

			def connection = new SurveyConnection(survey:surveyInstance, fromStation:stations.get(parts[0]), toStation:stations.get(parts[1]), length:Double.valueOf(parts[2])*FEET_TO_METERS,
					compass:parts[3], clino:parts[4],
					left:Double.valueOf(parts[5])*FEET_TO_METERS, right:Double.valueOf(parts[6])*FEET_TO_METERS, up:Double.valueOf(parts[7])*FEET_TO_METERS, down:Double.valueOf(parts[8])*FEET_TO_METERS)
			connection.save()
		}

	}


	def void checkDirectories(){
		(new File(PATH)).mkdirs()
		(new File(PATH+"levels/")).mkdirs()
		(new File(PATH+OUTPUT_PATH)).mkdirs()		
		(new File(PATH+OUTPUT_PATH+"indivsurveys/")).mkdirs()
	}


	def void compile(){
		ProcessBuilder pb = new ProcessBuilder("therion");
		pb.directory(new File(PATH));
		Process p = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String input = "";
		String compileOutput = "";
		while((input=br.readLine())!=null){
			log.debug input
			compileOutput+=input+"\n"
		}
		p.waitFor();

		if(p.exitValue()!=0){
			log.error "Compile failed, exit value: "+p.exitValue();
			throw new CompileException(compileOutput);
		}
	}

	private void createScraps(){
		for(Survey s: Survey.list()){
			if(s.surveyConnections.size()>0 && s.surveyStations.size()>0){
				createScrap(s, PATH+"levels/"+s.system.name+"-"+s.id+".th2")
			}
		}
	}

	private void createScrap(Survey s, String filename){
		String output = "encoding  utf-8\n" + 
		" \n";
		output+="scrap "+s.system.name+"-"+s.id+"-th2\n\n"

		for(SurveyStation ss: s.surveyStations){
			output+="point "+ss.scrapX+" "+ss.scrapY+" station -name "+ss.name+"@"+s.system.name+"-"+s.id+"\n";
		}

		output+="\n";

		for(SurveyStation ss: s.surveyStations){
			for(FeatureInstance fi: FeatureInstance.findAllBySurveyStation(ss)){
				output+=fi.generateScrapString()+"\n";
			}
		}

		for(SurveyConnection sc: s.surveyConnections){

			SurveyStation lower, upper, right, left;
//			if(sc.toStation.scrapY>sc.fromStation.scrapY){
				lower = sc.fromStation;
				upper = sc.toStation;
//			}
//			else{
//				lower = sc.toStation;
//				upper = sc.fromStation;
//			}
			double angle = sc.compass
//			double angle = Math.atan2(Math.abs(sc.fromStation.scrapY-sc.toStation.scrapY), Math.abs(sc.fromStation.scrapX-sc.toStation.scrapX))*180/Math.PI;
//			double leftAngle = angle-90;
//			double rightAngle = angle+90;
			double scl = sc.length==0 ? 0 : Math.sqrt((sc.fromStation.scrapX-sc.toStation.scrapX)*(sc.fromStation.scrapX-sc.toStation.scrapX)+(sc.fromStation.scrapY-sc.toStation.scrapY)*(sc.fromStation.scrapY-sc.toStation.scrapY))/sc.length

			double leftRight = Math.cos(angle)*scl;
			double upDown = Math.sin(angle)*scl;
			output+="line wall:blocks -place top -attr color \""+sc.survey.system.color+"\"\n";

//			log.error sc.toString() + " "+angle;
//			if(angle<0 || angle>180){
//				lower = sc.toStation;
//				upper = sc.fromStation;
//			}
			
			output+=(lower.scrapX+sc.left*leftRight)+" "+(lower.scrapY+sc.left*upDown)+"\n";
			output+=(upper.scrapX+sc.left*leftRight)+" "+(upper.scrapY+sc.left*upDown)+"\n";			
			
			output+="endline\n";

			output+="line wall:blocks -place top -attr color \""+sc.survey.system.color+"\"\n";

			output+=(lower.scrapX-sc.right*leftRight)+" "+(lower.scrapY-sc.right*upDown)+"\n";
			output+=(upper.scrapX-sc.right*leftRight)+" "+(upper.scrapY-sc.right*upDown)+"\n";			
			
			output+="endline\n";

		}

		output+="endscrap\n";

		File f = new File(filename);
		f.setText(output);
	}

	private void createSurveys(){
		for(Survey s: Survey.list()){
			if(s.surveyConnections.size()>0 && s.surveyStations.size()>0){
				saveSurvey(s, PATH+"levels/"+s.system.name+"-"+s.id+".th")
			}
		}
	}

	def saveSurvey(Survey s, String filename){

		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd@hh:mm:ss.00");

		String output = "encoding  utf-8\n" + 
		" \n";
		output+=" survey "+s.system.name+"-"+s.id+"  \\\n";
		output+="   -title \""+s.title+"\" \\\n\n";
		output+="   centerline\n" + 
		" units length 1 meter\n" + 
		"     team \""+s.team+"\"\n"+ 
		"     date "+format.format(s.datesurveyed)+"\n" + 
		"     walls on\n" + 
		"     cs UTM15\n\n" +
		"# "+s.note+"\n";

		for(SurveyStation ss: s.surveyStations){
			if(ss.note!=null && ss.note.trim().size()>0){
				output+="# "+ss.name+" - "+ss.note+"\n";
			}
		}

		for(FixedPoint fp: FixedPoint.list()){
			if(fp.station.survey.id==s.id){
				output+=fp.toTherionString()
			}
		}

		output+="\n sd length .05 meter        \n" + 
		"  sd compass .5 degrees\n" + 
		"  sd clino .5 degree  \n" + 
		"  data normal from to length compass clino left right up down\n\n";

		for(SurveyConnection sc: s.surveyConnections){
			output+=sc.fromStation.name+" "+sc.toStation.name+" "+sc.length+" "+sc.compass+" "+sc.clino+" "+sc.left+" "+sc.right+" "+sc.up+" "+sc.down+"\n";
		}
		output+="      \n" + 
		"   endcenterline\n" + 
		" \n" + 
		" endsurvey\n";
		File f = new File(filename)
		f.setText(output);
	}

	private void createLevels(){
		String level = "encoding  utf-8\n" + 
		"\n" + 
		"survey main \n" + 
		"\n";
		for(TunnelSystem ts: TunnelSystem.list()){
			level+="input "+ts.name+".th\n";
		}
		level+="\n";

		for(Equate e: Equate.list()){
			if(e.stations==null || e.stations.size()<2){
				continue;
			}
			else{
				SurveyStation first;
				for(SurveyStation s: e.stations){
					if(first==null){
						first = s;
						continue;
					}
					TunnelSystem ts = first.survey.system
					TunnelSystem ts2 = s.survey.system
					level+= "equate "+first.name+"@"+ts.name+"-"+first.survey.id+"."+first.survey.system.name+" "+s.name+"@"+ts2.name+"-"+s.survey.id+"."+s.survey.system.name+"\n";
				}
			}
		}

		level+="\nendsurvey\n";

		File f = new File(PATH+"levels/levels.th")
		f.setText(level);

		for(TunnelSystem ts: TunnelSystem.list()){
			String output = "encoding  utf-8\n" + 
			" survey "+ts.name+"  \\\n" + 
			"   -title \""+ts.name+"\" \\\n" + 
			"   -attr system "+ts.name+" \\\n" + 
			"   -attr "+ts.name+" 1\n" + 
			"   \n";
			for(Survey s: ts.surveys){
				if(s.surveyConnections.size()>0 && s.surveyStations.size()>0){
					output+="input "+ts.name+"-"+s.id+"\n";
					output+="input "+ts.name+"-"+s.id+".th2\n";
				}
			}
			output+="\n"
				output+="map "+ts.name+"-map\n"
				for(Survey s: ts.surveys){
					if(s.surveyConnections.size()>0 && s.surveyStations.size()>0){
						output+=ts.name+"-"+s.id+"-th2\n"
					}
				}
			output+="endmap\n"

				output+= "\n"; 			
			output+= " endsurvey\n" + 
			"\n";
			f = new File(PATH+"levels/"+ts.name+".th")
			f.setText(output);
		}
	}

	private createMain(){
		String main = "encoding  utf-8\n" + 
		" \n" + 
		" survey Main  \\\n" + 
		"   -title \"MainMap\" \\\n\n" + 
		"   input levels/levels\n" + 
		"   \n" + 
		" endsurvey\n";
		File f = new File(PATH+"main.th")
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
//		"  surface bottom\n" + 
//		"  surface-opacity 90\n" + 
		"  scale 1 250\n" + 
		"  grid-size 1 1 1 m\n" + 
		"\n" + 
		"transparency on\n" + 
//		"  colour map-fg [80 80 80]\n" + 
		 " colour map-fg [80 80 80]\n"+
        " colour map-bg [70 90 70]\n"+
//		"  colour map-bg [70 90 70]\n" + 
		"\n" + 
		"  code metapost\n" + 
//		"\n" + 
		"  def l_survey_cave (expr p) =\n" + 
		"    draw p withpen PenD withcolor (0.5,0.3,0.3);\n" + 
		"  enddef;\n" + 
		"  endcode\n" + 
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
		"endcode \n";

		for(Feature f: Feature.list()){
			layout+="#"+f.name+"\n";
			layout+=f.metapostCode+"\n";
		}

		layout+="endlayout \n";

		for(Feature f: Feature.list()){
			layout+=f.postMetapostCode+"\n";
		}

		File f = new File(PATH+"layout.th")
		f.setText(layout);
	}

	private void createThconfig(){
		String config = "encoding  utf-8\n" + 
		"\n" + 
		"input layout.th \n" + 
		"source main.th \n" + 
		"\n" + 
		"export model -format esri -o "+OUTPUT_PATH+"esri\n" + 
		"export model -format kml -o "+OUTPUT_PATH+"cave.kml\n" + 
		"export model -format dxf -o "+OUTPUT_PATH+"cave.dxf\n" + 
		"export model -format vrml -o "+OUTPUT_PATH+"cave.vrml\n" + 
		"export map   -format kml  -o  "+OUTPUT_PATH+"cavemap.kml\n" + 
		"export model -o "+OUTPUT_PATH+"cave.lox\n" + 
		"#export map  -projection plan -o "+OUTPUT_PATH+"cave_m.pdf \n" + 
		"export continuation-list -o "+OUTPUT_PATH+"continuation.txt\n" + 
		"#export atlas -projection plan -o "+OUTPUT_PATH+"cave_a.pdf\n" + 
		"export map -layout lab-header -output "+OUTPUT_PATH+"cave2.pdf\n" +
		"export map -projection plan -layout lab-header -output "+OUTPUT_PATH+"cave.pdf\n" +
		"export map -projection plan -format svg -layout lab-header -output "+OUTPUT_PATH+"cave.svg\n" +
		"\n" + 
		"layout xvi-export\n" + 
		"  scale 1 100\n" + 
		"  grid-size 1 1 1 m\n" + 
		"endlayout\n" + 
		"export map -fmt xvi -layout xvi-export -o "+OUTPUT_PATH+"cave.xvi\n";
		File f = new File(PATH+"thconfig")
		f.setText(config); 
	}

	private void createSingleThconfig(Survey s){
		String config = "encoding  utf-8\n" + 
		"\n" + 
		"source singlesurvey.th \n" + 
		//"layout xvi-export\n" + 
		//"  scale 1 100\n" + 
		//"  grid-size 1 1 1 m\n" + 
		//"endlayout\n" + 
		//-layout xvi-export
		"layout names\n"+
		"debug station-names\n"+
		 " colour map-fg [80 80 80]\n"+
	        " colour map-bg [70 90 70]\n"+
		"endlayout\n"+
		"export map -fmt xvi -output "+OUTPUT_PATH+"singlesurvey.xvi\n"+
		"export map -layout names -output "+OUTPUT_PATH+"indivsurveys/"+s.id+".pdf\n"+
		"export map -layout names -format svg -output "+OUTPUT_PATH+"indivsurveys/"+s.id+".svg\n";
		File f = new File(PATH+"thconfig")
		f.setText(config); 
	}

}

class CompileException extends Exception{

	public CompileException(){
		super();
	}

	public CompileException(String text){
		super(text);
	}

}