import	grails.util.GrailsUtil;
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;
import org.springframework.context.ApplicationContext;

class BootStrap {

	def init = { servletContext ->   

	def continuation = new Feature(name: "Continuation", metapostCode:"", postMetapostCode:"", evalScrapString: 
		"def evalScrap(Object fi){" +
		" return \"point \"+fi.surveyStation.scrapX+\" \"+fi.surveyStation.scrapY+\" continuation\";" +
	"}")
	updateFeature(continuation);

	def manhole = new Feature(name: "Manhole", metapostCode:"code metapost\n" +
			"def p_u_manhole (expr pos,theta,sc,al)=\n" + 
			"    U:=(.4u,.4u);\n" + 
			"    T:=identity aligned al rotated theta scaled sc shifted pos;\n" + 
			"    pickup PenA;\n" + 
			"    p := fullcircle scaled 1u; \n" + 
			"    thdraw p;\n" + 
			"enddef;\n" +
			"     initsymbol(\"p_u_manhole\");\n" + 
			" endcode\n",
			postMetapostCode: "text en \"point u:manhole\" \"manhole\" \n",
			evalScrapString:
				"def evalScrap(Object fi){" +
				" return \"point \"+fi.surveyStation.scrapX+\" \"+fi.surveyStation.scrapY+\" u:manhole  -orientation \"+fi.rotation+\" -place top\";" +
	"}")
	updateFeature(manhole);

	def interconnect = new Feature(name: "Interconnect", metapostCode:" code metapost\n" + 
			"     def p_u_interconnect (expr P,R,S,A)=\n" + 
			"       T:=identity aligned A rotated R scaled S shifted P;\n" + 
			"       thfill (.5u,.5u)--(-.5u,.5u)--(-.3u,.3u)--(.3u,.3u)--cycle;\n" + 
			"       thfill (.5u,-.5u)--(-.5u,-.5u)--(-.3u,-.3u)--(.3u,-.3u)--cycle;\n" + 
			"       thfill (.5u,.5u)--(.5u,-.5u)--(.3u,-.3u)--(.3u,.3u)--cycle;\n" + 
			"       thfill (-.5u,.5u)--(-.5u,-.5u)--(-.3u,-.3u)--(-.3u,.3u)--cycle;       \n" + 
			"     enddef;\n" + 
			"     initsymbol(\"p_u_interconnect\");\n" + 
			"\n" + 
			"   endcode\n",
			postMetapostCode:"text en \"point u:interconnect\" \"interconnect\" \n",
			evalScrapString:
				"def evalScrap(Object fi){" +
				" return \"point \"+fi.surveyStation.scrapX+\" \"+fi.surveyStation.scrapY+\" u:interconnect  -orientation \"+fi.rotation+\" -place top\";" +
	"}")
	updateFeature(interconnect);

	def cbWall = new Feature(name: "Cinderblock Wall", metapostCode: "\n code metapost\n" + 
			"     def p_u_cinderblock (expr P,R,S,A)=\n" + 
			"       U:=(.6u,.6u);  \n" + 
			"       T:=identity aligned A rotated R scaled S shifted P;\n" + 
			"       pickup PenA;\n" + 
			"       thdraw (.3u,.3u)--(.3u,-.3u)--(-.3u,-.3u)--(-.3u,.3u)--cycle;\n" + 
			"       thdraw (.9u,.3u)--(.9u,-.3u)--(.3u,-.3u)--(.3u,.3u)--cycle;\n" + 
			"       thdraw (-.9u,.3u)--(-.9u,-.3u)--(-.3u,-.3u)--(-.3u,.3u)--cycle;\n" + 
			"          enddef;\n" + 
			"     initsymbol(\"p_u_cinderblock\");\n" + 
			"\n" + 
			"   endcode\n",
			postMetapostCode:"text en \"point u:cinderblock\" \"cinderblock wall\" \n",
			evalScrapString:
				"def evalScrap(Object fi){" +
				" return \"point \"+fi.surveyStation.scrapX+\" \"+fi.surveyStation.scrapY+\" u:cinderblock  -orientation \"+fi.rotation+\" -place top\";" +
	"}")

	updateFeature(cbWall);

	def brickWall = new Feature(name: "Brick Wall", metapostCode: "\n code metapost\n" + 
			"     def p_u_brick (expr P,R,S,A)=\n" + 
			"       U:=(.6u,.6u);  \n" + 
			"       T:=identity aligned A rotated R scaled S shifted P;\n" + 
			"       pickup PenA;\n" + 
			"       thdraw (.3u,.3u)--(.3u,-.3u)--(-.3u,-.3u)--(-.3u,.3u)--cycle;\n" + 
			"       thdraw (.9u,.3u)--(.9u,-.3u)--(.3u,-.3u)--(.3u,.3u)--cycle;\n" + 
			"       thdraw (-.9u,.3u)--(-.9u,-.3u)--(-.3u,-.3u)--(-.3u,.3u)--cycle;\n" + 
			"		thdraw (-.9u, .01u)--(.9u,.01u);\n"+
			"          enddef;\n" + 
			"     initsymbol(\"p_u_brick\");\n" + 
			"\n" + 
			"   endcode\n",
			postMetapostCode:"text en \"point u:brick\" \"brick wall\" \n",
			evalScrapString:
				"def evalScrap(Object fi){" +
				" return \"point \"+fi.surveyStation.scrapX+\" \"+fi.surveyStation.scrapY+\" u:brick  -orientation \"+fi.inferRotation()+\" -place top\";" +
	"}")

	updateFeature(brickWall);

	def sandstoneWall = new Feature(name: "Sandstone Wall", metapostCode: "\n code metapost\n" + 				
			"     def l_u_sandstone (expr P)=\n" + 
			"       T:=identity;\n" +
			"if known ATTR_color: \n" + 
			" scantokens ATTR_color;\n" +
			"else: \n"+
			" scantokens \"color col; col:=black;\";\n"+
			"fi;\n"+
			"draw P withpen PenA withcolor col;\n" +
			"          enddef;\n\n" + 
			"    initsymbol(\"l_u_sandstone\");\n" + 
			"def l_u_sandstone_legend = \n" + 
			"l_u_sandstone(((.2,.2) -- (.8,.8)) inscale) \n" + 
			"enddef; \n" + 
			"   endcode\n",
			postMetapostCode:"text en \"line u:sandstone\" \"sandstone wall\" \n",
			lining: true,
			evalScrapString:
				"def evalScrap(Object fi){" +
				"return \"line u:sandstone -outline out -place top -attr color \\\"\"+fi.surveyConnection.survey.system.color+\"\\\"\" "+
	"}")
	updateFeature(sandstoneWall);

	def cbWallLine = new Feature(name: "Cinderblock Liner", metapostCode: "\n code metapost\n" + 				
			"     def l_u_cinderblock (expr P)=\n" + 
			"       T:=identity;\n" +
			"if known ATTR_color: \n" + 
			" scantokens ATTR_color;\n" +
			"else: \n"+
			" scantokens \"color col; col:=black;\";\n"+
			"fi;\n"+
			"  T:=identity;\n" + 
			"  cas := 0;\n" + 
			"  dlzka := arclength P;\n" + 
			"if dlzka > 0:\n" + 
			"  mojkrok:=adjust_step(dlzka, 1.5u);\n" + 
			"  pickup PenA;\n" + 
			"  forever:\n" + 
			"    t1 := arctime (cas ) of P;\n" + 
			"    t2 := arctime (cas + mojkrok) of P;\n" + 
			"    t3 := arctime (cas + mojkrok * 1/2) of P;\n"+
			"    q := ((point t1 of P) + .6u * unitvector(thdir(P,t1) rotated -90)) --\n" + 
			"         (subpath (t1,t2) of P) --\n" + 
			"         ((point t2 of P) + .6u * unitvector(thdir(P,t2) rotated -90)) --\n" +
			"         ((point t1 of P) + .6u * unitvector(thdir(P,t1) rotated -90));\n"+
			"    draw q withcolor col;\n" + 
			"    q := ((point t3 of P) + .6u * unitvector(thdir(P,t3) rotated -90)) --\n" +
			"         (point t3 of P);\n"+
			"    draw q withcolor col;\n"+
			"    cas := cas + mojkrok;\n" + 
			"    exitif cas > dlzka - (2*mojkrok/3); % for rounding errors\n" + 
			"  endfor;\n" + 
			"fi;\n" +
			"          enddef;\n\n" + 
			"    initsymbol(\"l_u_cinderblock\");\n" +
			"def l_u_cinderblock_legend = \n" +
			"l_u_cinderblock(((.2,.65) -- (.8,.65)) inscale) \n" + 
			"enddef; \n" + 
			"   endcode\n",
			lining: true,
			postMetapostCode:"text en \"line u:cinderblock\" \"cinderblock liner\" \n",
			evalScrapString:
				"def evalScrap(Object fi){" +
				"return \"line u:cinderblock -outline out -place top -attr color \\\"\"+fi.surveyConnection.survey.system.color+\"\\\"\" "+
	"}")
	updateFeature(cbWallLine);

	switch(GrailsUtil.environment) { 
	case "development": 
		initData()
		break 
	case "test": 
		initData()
		break 
	case "production": 
		break 
	}      

	}
	def destroy = {
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

	def initData(){
		//"color col; col:=red;"2
		def sysa = new TunnelSystem(color: "color col; col:=red;", name: "sysa");
		sysa.save();
		def sysb = new TunnelSystem(color: "color col; col:=blue;", name: "sysb");
		sysb.save();

		def mm = new MeasurementMethod(name: "GPS", sd_x:5, sd_y:5, sd_z:5);
		mm.save();

		def sura = new Survey(dateentered: new Date(), datesurveyed: new Date(), team: "team", title: "test survey", note: "note", system: sysa, surveyStations: []);
		sura.save();
		sysa.addToSurveys(sura);

		importSurvey(sura, 
				" 2   81   1.1     90    -2.7   .425 .425 2.1 0 \n" + 
				"       81   83  18.8     90    -2.7   .425 .425 2.1 0  \n" + 
				"       83   84   5      0     3    0.4  0.4  0.9  0.9\n" + 
				"    \n" + 
				"       83   86   1.7     90    -8   .425 .425 2.1 0\n" + 
				"       86  86.1  .75  90      0   0 0 0 0        \n" + 
				"       86   87  10    135     -8     .425 .425 2.1 0\n" + 
				"       87   88  8.9    180   -16    0.3  0.3  0.9  0.9     \n" + 
				"       87   89  1.2      0    16    0.3  0.3  0.9  0.9       \n" + 
				"       88   90   3.1    180    -16    0.3  0.3  0.9  0.9\n" + 
				"       \n" + 
				"     90 91 1.65 0 90  0 0 0 0\n" + 
				"       \n" + 
				"      \n" + 
				"       #note\n" + 
				"        0    2   1.7     0     0    0 0 0 0\n" + 
				"        1    2   1.85      0     -90 0 0 0 0\n" + 
				"        2   2.1  5    -90    2.2   .425 .425 2.1 0\n" + 
				"        2.1 2.2 9   0  3  .3  .3 .6 .6\n" + 
				"        2.2 2.3    1  -90 0 .2 .2 .5 .5\n" + 
				"        2.2 2.4   10  0 0 .3 .3 .6 .6\n" + 
				"        2.4 2.5 1 -90 0 .2 .2 .5 .5\n" + 
				"        \n" + 
				"        2.1 3   4.5   -90   2.2    .425 .425 2.1 0\n" + 
				"        3 3.1  12  180  3  .3 .3 .6 .6\n" + 
				"        \n" + 
				"        3   4   6.5  -90    2.2   .425 .425 2.1 0\n" + 
				"        4   5   4.5   -90     2.2   .425 .425 2.1 0\n" + 
				"        5  5.1 22.7   0       3    .3  .3  .7 .7\n" + 
				"        5.1 5.2 17.4 0      3    .3  .3  .7 .7\n" + 
				"        5.2 5.3  1.3 0  90  .2 .2 .2 .2 \n" + 
				"        5.1 5.4  5  45  3   .3 .3 .7 .7  \n" + 
				"        \n" + 
				"        5   6   9.7   -90     2.2    .425 .425 2.1 0\n" + 
				"        6   7   6  -90   2.2   .425 .425 2.1 0\n" + 
				"        7 7.1 17  180  3    .3 .3 .6 .6\n" + 
				"        7   8   .2   -90     2.2   .425 .425 2.1 0\n" + 
				"        8  8.1 15.5  0      3     .3 .3 .6 .6\n" + 
				"        8.1 8.2 5 90     3  .3 .3 .6 .6\n" + 
				"        8.1 8.3 1 0    3   .3 .3 .6 .6\n" + 
				"        8.3 8.4 3 -90 3  .3 .3 .6 .6\n" + 
				"        \n" + 
				"        8    9   6.2  -90     2.2     .425 .425 2.1 0  \n" + 
				"        9  9.1 37 0  3      .4 .4 .8 .8\n" + 
				"        \n" + 
				"        9   10   15.5    -90     2.2     .425 .425 2.1 0  \n" + 
				"        10 10.1 15  0       3         .4 .4 .8 .8\n" + 
				"        10.1 10.2 6  -30  3   .4 .4 .8 .8\n" + 
				"        10.1 10.3  6  -60 3  .4 .4 .8 .8\n" + 
				"        10  10.4 15  180 3  .4 .4 .8 .8\n" + 
				"        \n" + 
				"       10   12  15.4   -90     2.2     .425 .425 2.1 0\n" + 
				"       12   12.1  .6  0         0   0 0 0 0\n" + 
				"       12.1 12.4  1.9  0     90  0 0 0 0\n" + 
				"       \n" + 
				"       12 13    5.4       -90     2.2     .425 .425 2.1 0\n" + 
				"       \n" + 
				"     13 14    5.3 0 90 .4 .4 .4 .4   #TODO: actually measure this\n" + 
				"     14 14.1 1 0 0   .2 .2 .2 .2\n" + 
				"     14 15 6.6 0 90 .4 .4 .4 .4    #should be 35 or 38 feet up total\n" + 
				"        \n" + 
				"       13   60  23.3   0  1  .425 .425 2.1 0\n" + 
				"       60   61  21.2   0  1  .425 .425 2.1 0\n" + 
				"       61   62  10.4   0  1  .425 .425 2.1 0\n" + 
				"       62  62.1 2.9    90  0   .3 .3 2 0\n" + 
				"       62   63   4   0  1  .425 .425 2.1 0\n" + 
				"       63   63.01 1 -90 0 .2 .2 .3 .3\n" + 
				"       63  63.1  1.7 0 90 0 0 0 0\n" + 
				"       63.1 63.15 4.3 90 0 .5 .5 1 0\n" + 
				"       63.15 63.2 1  0  0   .1 .1 .1 0\n" + 
				"       63.15 63.3 2   90  0 .3 .3 .3 0\n" + 
				"       63   64  9.5   0  1  .425 .425 2.1 0\n" + 
				"       64  64.1 6  -90 1 .3 .3 .6 0\n" + 
				"       64   65   5.3   0  1  .425 .425 2.1 0\n" + 
				"       65  65.3 2.05  0  90  0 0 0 0 \n" + 
				"       65   66  10.1   0  1  .425 .425 2.1 0\n" + 
				"       66 66.1  11 -90 1 .3 .3 .6 0\n" + 
				"       66   67   5.1   0  1  .425 .425 2.1 0\n" + 
				"       67   68   3.5   0  1  .425 .425 2.1 0\n" + 
				"       \n" + 
				"       68  68.1 6.8  0 90 .3  .3  .3  .3 #to surface\n" + 
				"       68 68.2  0  0 -90 0 0 0 0 #in floor\n" + 
				"       \n" + 
				"       67   69  9.8  -45 1 .425 .425 2.1 0\n" + 
				"         \n" + 
				"       13   30   4    -90     2.7  .425 .425 2.1 0\n" + 
				"       30  30.1 7    0         -45  0.5 0.5 1 0\n" + 
				"       \n" + 
				"       30   31   5.4    -90     2.7  .425 .425 2.1 0  \n" + 
				"       31  31.1 6        0       0     .3   .3    1.2 0\n" + 
				"       \n" + 
				"       31   32   6.2       -90     2.7  .425 .425 2.1 0\n" + 
				"       32  32.1 10      0         0   .3    .3    1.2 0\n" + 
				"       32  32.2  9      180     0   .5    .5    2 0\n" + 
				"       32.2 32.3 42   180  0  0  .4   .4    1.8 \n" + 
				"       \n" + 
				"       32   33   5       -90     2.7 .425 .425 2.1 0\n" + 
				"       33  33.1 1       0         0     .3    .3    .6 0\n" + 
				"       \n" + 
				"       33   34   .6       -90     2.7  .425 .425 2.1 0 \n" + 
				"       34   35   4.8       -90     2.7 .425 .425 2.1 0  \n" + 
				"       35   36  8.2       -90     2.7  .425 .425 2.1 0  \n" + 
				"       36   37   4.6       -90     2.7  .425 .425 2.1 0  \n" + 
				"       37   38   4.2       -90     2.7  .425 .425 2.1 0  \n" + 
				"       38   39  13.5       -90     2.7  .425 .425 2.1 0  \n" + 
				"       39   40   3.2       -90     2.7  .425 .425 2.1 0  \n" + 
				"       40   41  8       -90     2.7  .425 .425 2.1 0\n" + 
				"       41   42   2.5       -90     2.7  .425 .425 2.1 0  \n" + 
				"       42   43  6.7       -90     2.7  .425 .425 2.1 0  \n" + 
				"       43   44   5.5       -90     2.7  .425 .425 2.1 0  \n" + 
				"       44   45   2.8       -90     2.7  .425 .425 2.1 0  \n" + 
				"       45   46   3.1       -90     2.7  .425 .425 2.1 0  \n" + 
				"       46   47   1.5       -90     2.7  .425 .425 2.1 0  \n" + 
				"       47   48   3.1       -90     2.7  .425 .425 2.1 0  \n" + 
				"       48   49   1.2    -90     2.7  .425 .425 2.1 0\n" + 
				"       \n" + 
				"       49   49.1  9.3   0   90  .3  .3  .3  .3   \n" + 
				"       \n" + 
				"       49   50   .75   -90  2.7  .4 .4  .8  .8\n" + 
				"       49   49.2  9 -135  2.7   .2  .2  1.5 0\n" + 
				"       49.2 49.3  8 180 2.7   .2    .2 1.5 0\n" + 
				"       \n" + 
				"         #using 101-120\n" + 
				"       13  101  15.9    180     2     .425 .425 2.1 0  \n" + 
				"      101  102   1.5   90     0    0    0    0    0  \n" + 
				"      101  103  21.9    180     2     .425 .425 2.1 0 \n" + 
				"      103  104   9    180     2     .425 .425 2.1 0\n" + 
				"      \n" + 
				"      103  105   3     90    2     .425 .425 1.7 0\n" + 
				"\n" + 
				"      105  106  38.7      0     0     .425 .425 2.1 0\n" + 
				"      106 12.4  .8   90  0  0 0 0 0\n" + 
				"      106  115   1.2   0 -90  0 0 0 0\n" + 
				"      115  116    3.7  0 0   .425 .425 2.1 0\n" + 
				"      116  117    .6    0 90  0 0 0 0  #this is hole to Cedar\n" + 
				"      105  107  13.4    180     0     .425 .425 2.1 0\n" + 
				"      107  107.1 1.4  0  90   .3 .3 .3 .3\n" + 
				"      107  108  7.9    180     0     .425 .425 2.1 0\n" + 
				"      108 108.1   2.8  -90     0    0 0 0 0   #approx 2.8\n" + 
		"      108  109  13.7  180  0   .425 .425 2.1 0\n");


		def surb = new Survey(dateentered: new Date(), datesurveyed: new Date(), team: "team2", title: "test survey2", note: "note2", surveyStations: []);	 
		surb.save();
		sysb.addToSurveys(surb);

		importSurvey(surb, 
				" 0   0.1 16    90     2   0.75 0.75 2  0\n" + 
				"        0.1 0.2  .6   0      0   0 0 0 0\n" + 
				"        0.1    1  49.8     90     2   0.75  0.75  2  0\n" + 
				"        1    2  1.3     90     2    0.75  0.75  2  0\n" + 
				"        2    3  13.9     90     2   0.75  0.75  2  0 \n" + 
				"        3  3.1  1.15  90   2    0.75  0.75  2  0\n" + 
				"        3.1    4   8.85     90 2    0.75  0.75  2  0\n" + 
				"        4  4.1  .4   180  0   0 0 0 0 \n" + 
				"        4.1  5   1.15   0 90 0 0 0 0\n" + 
				"        4    6.1   7.95   90  2   0.75 0.75 2 0\n" + 
				"        6.1 6.2   1.25   90  2   0.75 0.75 2 0\n" + 
				"        6.2    6   10.2     90     2    0.75  0.75  2  0\n" + 
				"        6  10  7     0       90    .5 .5 .5 .5          \n" + 
				"        6    7   6.6     90     2    0.75  0.75  2  0\n" + 
				"        7  7.1  .4       0       0     0 0 0 0\n" + 
				"        7.1 7.2  1.15  0  90  0 0 0 0\n" + 
				"        7    8  15.5     90     2    0.75  0.75  2  0\n" + 
		"        8 9 6.9 0 90 .5 .5 .5 .5  #estimated\n");


		def eq = new Equate(note: "test equate", stations:[]);	 
		eq.addToStations(SurveyStation.findAllBySurveyAndName(sura, "0").get(0));
		eq.addToStations(SurveyStation.findAllBySurveyAndName(surb, "5").get(0));
		eq.save();

		def fp = new FixedPoint(note: "test fp", station: SurveyStation.findAllBySurveyAndName(sura, "68.1").get(0),
				x: 492720.8, y: 4976885.5, z: 24.385, measurementMethod: mm);
		fp.save();

		def st1 = SurveyStation.findBySurveyAndName(sura, "68.1")
		def sc1 = SurveyConnection.findByToStation(st1)

		def fi = new FeatureInstance(feature:Feature.findByName("Manhole"), surveyConnection: sc1, surveyStation: st1);
		fi.save();

		def st2 = SurveyStation.findBySurveyAndName(sura, "0")
		def sc2 = SurveyConnection.findByFromStation(st2)
		def fi2 = new FeatureInstance(feature:Feature.findByName("Interconnect"), surveyConnection: sc2, surveyStation: st2)
		fi2.save();

		def fi3 = new FeatureInstance(feature:Feature.findByName("Cinderblock Wall"),
				surveyStation: SurveyStation.findBySurveyAndName(sura, "117"))
		fi3.save()

		def fi4 = new FeatureInstance(feature:Feature.findByName("Brick Wall"),
				surveyStation: SurveyStation.findBySurveyAndName(sura, "108.1"))
		fi4.save()

		for(SurveyConnection sc: SurveyConnection.list()){
			if(sc.survey.system.id==sysb.id){
				continue;
			}
			def fi5 = new FeatureInstance(feature:Feature.findByName("Cinderblock Liner"),
					surveyConnection: sc)
			fi5.save()
		}
	}




	public void importSurvey(Survey surveyInstance, String rawData){
		Double FEET_TO_METERS =  1; //import test data in meters

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
				def ss = new SurveyStation(name:parts[0], note:"", timeMeasured:new Date())
				ss.save();
				surveyInstance.addToSurveyStations(ss);
				surveyInstance.save();
				ss.setSurvey(surveyInstance);
				ss.save();
				stations.put(parts[0], ss)
			}
			if(!stations.containsKey(parts[1])){
				def ss = new SurveyStation(name:parts[1], note:"", timeMeasured:new Date())
				ss.save();
				surveyInstance.addToSurveyStations(ss);
				surveyInstance.save();
				stations.put(parts[1], ss)
				ss.setSurvey(surveyInstance);
				ss.save();
			}

			def connection = new SurveyConnection(length:Double.valueOf(parts[2])*FEET_TO_METERS,
					compass:parts[3], clino:parts[4],
					left:Double.valueOf(parts[5])*FEET_TO_METERS, right:Double.valueOf(parts[6])*FEET_TO_METERS, up:Double.valueOf(parts[7])*FEET_TO_METERS, down:Double.valueOf(parts[8])*FEET_TO_METERS)
			connection.setSurvey(surveyInstance);
			connection.setFromStation(stations.get(parts[0]));
			connection.setToStation(stations.get(parts[1]));
			surveyInstance.addToSurveyConnections(connection);
			connection.save()
			surveyInstance.save();
		}

	}

} 