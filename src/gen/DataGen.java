package gen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class DataGen {

	
	public static class Pair{
		public String first;
		public String second;
		public Pair(String f, String s){
			this.first = f; 
			this.second = s;
		}		
		public String toString(){ return first + "-" + second;}
	}
	public static void main (String[] args){
		
		final int SIZE = 100000;
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String digits = "0123456789";
		
		final int MAX_DUR = 5;
		final int MAX_DELAY = 40;
		
		final int MAX_PAS = 180; 
		final int MIN_PAS = 90;
		final int MAX_CCREW = 4; 
		final int MIN_CCREW = 3;
		final int MAX_FCREW = 3; 
		final int MIN_FCREW = 2;
				
		final int FLEET_SIZE = 20;
		
		final String regPrefix = "XY-";
		
		final String[] airpotCodes = {"TIA","EVN","GRZ","INN","KLU","LNZ","SZG","VIE","GYD","MSQ","ANR","BRU","CRL","LGG","OST","SJJ","TZL","BOJ","SOF","VAR","DBV","PUY","SPU","ZAD","ZAG","LCA","PFO","BRQ","PRG","AAL","AAR","BLL","CPH","FAE","TLL","HEL","OUL","RVN","TMP","TKU","VAA","AJA","BIA","EGC","BIQ","BOD","BES","FSC","LIL","LYS","MRS","MPL","NTE","NCE","BVA","CDG","ORY","SXB","RNS","RUN","TLN","TLS","TBS","FMM","BER","SXF","TXL","BRE","CGN","DTM","DRS","DUS","FRA","HHN","FDH","HAM","HAJ","FKB","LEJ","MUC","FMO","NUE","PAD","STR","NRN","ATH","CHQ","CFU","HER","KGS","JMK","RHO","JTR","SKG","ZTH","BUD","DEB","KEF","ORK","DUB","NOC","KIR","SNN","AHO","AOI","BRI","BGY","BLQ","BDS","CAG","CTA","CIY","FLR","GOA","SUF","LIN","MXP","NAP","OLB","PMO","PEG","PSR","PSA","CIA","FCO","TPS","TSF","TRN","VCE","VRN","ALA","TSE","PRN","RIX","KUN","VNO","LUX","SKP","MLA","KIV","TGD","TIV","AMS","EIN","GRQ","MST","RTM","AES","BGO","BOO","HAU","KRS","OSL","TRF","SVG","TOS","TRD","GDN","KTW","KRK","POZ","WAW","WMI","WRO","FAO","LIS","FNC","PDL","OPO","OTP","CLJ","IAS","TSR","SVX","DME","SVO","VKO","OVB","LED","AER","BEG","INI","BTS","KSC","LJU","ALC","LEI","OVD","BCN","BIO","FUE","GRO","LPA","IBZ","XRY","SPC","ACE","MAD","AGP","MAH","PMI","RMU","REU","SDR","SCQ","SVQ","TFN","TFS","VLC","ZAZ","GOT","MMX","ARN","BMA","NYO","VST","BSL","BRN","GVA","LUG","ZRH","ADA","ESB","AYT","DLM","IST","SAW","ADB","BJV","TZX","KBP","IEV","LWO","ODS","ABZ","BHD","BFS","BHX","BRS","CWL","DSA","EMA","EDI","EXT","GLA","PIK","HUY","JER","LBA","LPL","LCY","LGW","LHR","LTN","SEN","STN","MAN","NCL","SOU"};
		
		final String[] delayCodesOptions = {"00","01","02","03","04","05","06","07","08","09","11","12","13","14","15","16","17","18","19","21","22","23","24","25","26","27","28","29","31","32","33","34","35","36","37","38","39","41","42","43","44","45","46","47","48","51","52","55","56","57","58","61","62","63","64","65","66","67","68","69","71","72","73","75","76","77","81","82","83","84","85","86","87","88","89","91","92","93","94","95","96","97","98","99"};
		
		final long offset = Timestamp.valueOf("2010-01-01 00:00:00").getTime();
		final long end = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
		
		final String[] slotKindOptions = {"Flight", "Maintenance"};		
		
		final String[] maintenanceEventOptions = {"Delay", "Safety", "AircraftOnGround", "Maintenance", "Revision"};
		
		
		final String[] ataCodes = {"1100","1210","1220","1230","1240","1400","1410","1420","1430","1497","1800","1810","1820","1897","2100","2110","2120","2121","2130","2131","2132","2133","2134","2140","2150","2160","2161","2162","2163","2170","2197","2200","2210","2211","2212","2213","2214","2215","2216","2220","2230","2250","2297","2300","2310","2311","2312","2320","2330","2340","2350","2360","2370","2397","2400","2410","2420","2421","2422","2423","2424","2425","2430","2431","2432","2433","2434","2435","2436","2437","2440","2450","2460","2497","2500","2510","2520","2530","2540","2550","2551","2560","2561","2562","2563","2564","2565","2570","2571","2572","2597","2600","2610","2611","2612","2613","2620","2621","2622","2697","2700","2701","2710","2711","2720","2721","2722","2730","2731","2740","2741","2742","2750","2751","2752","2760","2761","2770","2780","2781","2782","2797","2800","2810","2820","2821","2822","2823","2824","2830","2840","2841","2842","2843","2844","2897","2900","2910","2911","2912","2913","2914","2915","2916","2917","2920","2921","2922","2923","2925","2926","2927","2930","2931","2932","2933","2934","2997","3000","3010","3020","3030","3040","3050","3060","3070","3080","3097","3100","3110","3120","3130","3140","3150","3160","3170","3197","3200","3201","3210","3211","3212","3213","3220","3221","3222","3230","3231","3232","3233","3234","3240","3241","3242","3243","3244","3245","3246","3250","3251","3252","3260","3270","3297","3300","3310","3320","3330","3340","3350","3397","3400","3410","3411","3412","3413","3414","3415","3416","3417","3418","3420","3421","3422","3423","3424","3425","3430","3431","3432","3433","3434","3435","3436","3440","3441","3442","3443","3444","3445","3446","3450","3451","3452","3453","3454","3455","3456","3457","3460","3461","3497","3500","3510","3520","3530","3597","3600","3610","3620","3697","3700","3710","3720","3797","3800","3810","3820","3830","3840","3897","4500","4597","4900","4910","4920","4930","4940","4950","4960","4970","4980","4990","4997","5100","5101","5102","5200","5210","5220","5230","5240","5241","5242","5243","5244","5245","5246","5247","5248","5250","5260","5270","5280","5297","5300","5301","5302","5310","5311","5312","5313","5314","5315","5320","5321","5322","5323","5324","5330","5340","5341","5342","5343","5344","5345","5346","5347","5350","5397","5400","5410","5411","5412","5413","5414","5415","5420","5497","5500","5510","5511","5512","5513","5514","5520","5521","5522","5523","5524","5530","5531","5532","5533","5534","5540","5541","5542","5543","5544","5550","5551","5552","5553","5554","5597","5600","5610","5620","5630","5640","5697","5700","5710","5711","5712","5713","5714","5720","5730","5740","5741","5742","5743","5744","5750","5751","5752","5753","5754","5755","5797","6100","6110","6111","6112","6113","6114","6120","6121","6122","6123","6130","6140","6197","6200","6210","6220","6230","6240","6297","6300","6310","6320","6321","6322","6330","6340","6397","6400","6410","6420","6440","6497","6500","6510","6520","6540","6597","6700","6710","6711","6720","6730","6797","7100","7110","7111","7112","7120","7130","7160","7170","7197","7200","7210","7220","7230","7240","7250","7260","7261","7270","7297","7300","7310","7311","7312","7313","7314","7320","7321","7322","7323","7324","7330","7331","7332","7333","7334","7397","7400","7410","7411","7412","7413","7414","7420","7421","7430","7497","7500","7510","7520","7530","7531","7532","7540","7597","7600","7601","7602","7603","7620","7697","7700","7710","7711","7712","7713","7714","7720","7721","7722","7730","7731","7732","7740","7797","7800","7810","7820","7830","7897","7900","7910","7920","7921","7922","7923","7930","7931","7932","7933","7997","8000","8010","8011","8012","8097","8100","8110","8120","8197","8200","8297","8300","8397","8500","8510","8520","8530","8540","8550","8560","8570","8597"};
		final String[] workOrderKindOptions = {"Forecast", "TechnicalLogBook"};
		
		final String[] frequencyUnitsKindOptions = {"Flights", "Days", "Miles"};
		final String[] MELCathegoryOptions = {"A", "B", "C", "D"};
		final String[] reportKindOptions = {"PIREP", "MAREP"};
		final String[] aircraftModels = {"A319","A320 family","A320neo family","A321","A330","A330neo","A340","A350 XWB","737","747","767","777"};
		final String[] aircraftManufacturers= {"Airbus","Airbus","Airbus","Airbus","Airbus","Airbus","Airbus","Airbus","Boeing","Boeing","Boeing","Boeing"};
		
		final int MAX_ATTCH_SIZE = 1;
		
		final int MAX_WORK_ORDERS = 1;
		
		
		
		
		
		// Slots
		

		//aircraft registration
		
		// fleet 
		String[] fleet = new String[FLEET_SIZE];
		String[] MSNs = new String[FLEET_SIZE];
		String[] models = new String[FLEET_SIZE];
		String[] manufacturers = new String[FLEET_SIZE];
		
		Random r = new Random(System.currentTimeMillis());
		for (int i=0; i<FLEET_SIZE; i++){		
			fleet[i] = regPrefix + alphabet.charAt(r.nextInt(alphabet.length())) + alphabet.charAt(r.nextInt(alphabet.length())) + alphabet.charAt(r.nextInt(alphabet.length()));						
			MSNs[i] = "MSN "+ r.nextInt((9999 - 1000) + 1) + 1000;
			int k = r.nextInt(aircraftModels.length);
			models[i] = aircraftModels[k];
			manufacturers[i] = aircraftManufacturers[k];
		}
		
		///CSV fleet generation 
		System.out.println("FLEET:");
		System.out.println("aircraft_reg_code,manufacturer_serial_number,aircraft_model,manufacturer");
		for (int i=0; i<FLEET_SIZE; i++){
			System.out.println(fleet[i]+","+MSNs[i]+","+models[i]+","+manufacturers[i]);
		}
		
		
		String[] aircraftRegs = new String[SIZE];
		r = new Random(System.currentTimeMillis());
		for (int i=0; i<SIZE; i++){		
			aircraftRegs[i] = fleet[r.nextInt(fleet.length)];			
			
			
		}
//		
//		for (String ar: aircraftRegs)
////			System.out.println(ar);
//		
		//arrival,  departure, delayCode, canceled 
		
		long diff = end - offset + 1;
		
		Timestamp[] scheduledDepartures = new Timestamp[SIZE];
		Timestamp[] scheduledArrivals = new Timestamp[SIZE];
		Timestamp[] actualDepartures = new Timestamp[SIZE];
		Timestamp[] actualArrivals = new Timestamp[SIZE];
		int[] delays = new int[SIZE];
		
		
		
		try { 
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output100000-NEW.txt")));
			
		String[] delayCodes = new String[SIZE];
		
		boolean[] canceled = new boolean[SIZE];
		
		for (int i=0; i<SIZE; i++){	
			Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(rand.getTime());			
			Timestamp scheduledDeparture = new Timestamp(cal.getTimeInMillis());
			scheduledDepartures[i] = scheduledDeparture;
			
			cal.add(Calendar.HOUR_OF_DAY, r.nextInt(MAX_DUR));
			Timestamp scheduledArrival = new Timestamp(cal.getTimeInMillis());
			scheduledArrivals[i] = scheduledArrival;
			
			canceled[i] = r.nextBoolean();
			
			if (canceled[i]) continue;
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTimeInMillis(scheduledDeparture.getTime());
			int delay =  r.nextInt(MAX_DELAY);
			delays[i] = delay;
			if (delay > 0) delayCodes[i] = delayCodesOptions[r.nextInt(delayCodesOptions.length)];
			else  delayCodes[i] = null;
			cal1.add(Calendar.MINUTE, delay);
			Timestamp actualDeparture = new Timestamp(cal1.getTimeInMillis());
			actualDepartures[i] = actualDeparture;
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTimeInMillis(scheduledArrival.getTime());
			cal1.add(Calendar.MINUTE, delay);
			Timestamp actualArrival = new Timestamp(cal2.getTimeInMillis());
			actualArrivals[i] = actualArrival;
						
		}
		
//		for (int i=0; i<SIZE; i++){	
////			System.out.println(scheduledDepartures[i] + " - " + scheduledArrivals[i]+ "; " + canceled[i] + ", " + delays[i] + " #" + delayCodes[i] + " ;" + actualDepartures[i] + " - " + actualArrivals[i]);
//		}			
		
		//Slot kinds, origin and destination, passangers
		
		String[] slotKinds = new String[SIZE];
		
		String[] flightIDs = new String[SIZE];
				
		
		Pair[] originDest = new Pair[SIZE];
		HashMap<String, String> orgDestToFlightNo = new HashMap<String, String>();
		
		int[] passangers = new int[SIZE];
		int[] cabinCrew = new int[SIZE];
		int[] flightCrew = new int[SIZE];
		
		boolean[] programmed = new boolean[SIZE];
		
		int[] maintenanceID = new int[SIZE];
		String[] airportMaintenance = new String[SIZE];
		String[] subsystem = new String[SIZE];
		Timestamp[] starttimes = new Timestamp[SIZE];
		int[] days = new int[SIZE]; //days
		int[] hours = new int[SIZE]; //hours
		int[] minutes = new int[SIZE]; //minutes
		String[] maintenanceKinds = new String[SIZE];
		Date[] departure = new Date[SIZE];
		UUID[][] attachment_files = new UUID[SIZE][MAX_ATTCH_SIZE];
		String[][] attachment_events = new String[SIZE][MAX_ATTCH_SIZE];
		
		
		//work package variables
		int[] workPackageIDs = new int[SIZE];
		Date[] executionDates = new Date[SIZE];
		String[] executionPlaces = new String[SIZE];
		
		int[][] workOrderIDs = new int[SIZE][MAX_WORK_ORDERS];
		String[][] workOrder_aircraftRegs = new String[SIZE][MAX_WORK_ORDERS];
		Date[][] workOrder_executionDates = new Date[SIZE][MAX_WORK_ORDERS];
		String[][] workOrder_executionPlaces = new String[SIZE][MAX_WORK_ORDERS];
		int[][] workOrder_workPackageIDs = new int[SIZE][MAX_WORK_ORDERS];
		String[][] workOrder_workOrderKinds = new String[SIZE][MAX_WORK_ORDERS];
		Date[][] workOrder_deadlines = new Date[SIZE][MAX_WORK_ORDERS];
		Date[][] workOrder_plannedDates = new Date[SIZE][MAX_WORK_ORDERS];
		int[][] workOrder_frequencies = new int[SIZE][MAX_WORK_ORDERS];
		String[][] workOrder_frequencyUnits = new String[SIZE][MAX_WORK_ORDERS];
		int[][] workOrder_forecastedManHours = new int[SIZE][MAX_WORK_ORDERS];
		String[][] workOrder_reporteurClasses = new String[SIZE][MAX_WORK_ORDERS];
		int[][] workOrder_reporteurIDs = new int[SIZE][MAX_WORK_ORDERS];
		Date[][] workOrder_dueDates = new Date[SIZE][MAX_WORK_ORDERS];
		Date[][] workOrder_reportingDate = new Date[SIZE][MAX_WORK_ORDERS];
		boolean[][] workOrder_deferreds = new boolean[SIZE][MAX_WORK_ORDERS];
		String[][] workOrder_MELs = new String[SIZE][MAX_WORK_ORDERS];
		
		for (int i=0; i<SIZE; i++){	
			
			slotKinds[i] = slotKindOptions[r.nextInt(slotKindOptions.length)];
			
			//if maintenance
			if (slotKinds[i].equalsIgnoreCase("Maintenance") || delays[i]>0){
				programmed[i] = r.nextBoolean();
				maintenanceID[i] = r.nextInt(SIZE-250); 
				airportMaintenance[i] = airpotCodes[r.nextInt(airpotCodes.length)];
				subsystem[i] = ataCodes[r.nextInt(ataCodes.length)];
				starttimes[i] = scheduledDepartures[i]; 
				
				maintenanceKinds[i] = maintenanceEventOptions[r.nextInt(maintenanceEventOptions.length)];
				
				switch (maintenanceKinds[i]){
					case "Delay": {
						minutes[i] = r.nextInt(60); break;
					}
					case "Safety": {
						days[i] = r.nextInt(90); 
						hours[i] = r.nextInt(24);
						minutes[i] = r.nextInt(60);
						break;
					}
					case "AircraftOnGround": {
						days[i] = 0;
						hours[i] = r.nextInt(24);
						minutes[i] = r.nextInt(60);
						break;
					}
					case "Maintenance": {
						days[i] = r.nextInt(1);
						hours[i] = r.nextInt(24);
						minutes[i] = r.nextInt(60);
						break;
					}
					case "Revision": {
						days[i] = r.nextInt(31);
						hours[i] = r.nextInt(24);
						minutes[i] = r.nextInt(60);
						break;
					}
				}
				
				departure[i] = new Date(scheduledDepartures[i].getTime());
				
				
				for (int j=0 ; j<MAX_ATTCH_SIZE ; j++){
					attachment_files[i][j] = UUID.randomUUID();		
					attachment_events[i][j] = Integer.toString(maintenanceID[i]);					
				}
			
				
				//WorkPackages
				workPackageIDs[i] = r.nextInt(SIZE-250); 
				executionDates[i] = departure[i];
				executionPlaces[i] = airportMaintenance[i];
				
				for (int j=0 ; j<MAX_WORK_ORDERS ; j++){
					workOrderIDs[i][j] = r.nextInt(SIZE-250);
					workOrder_aircraftRegs[i][j] = aircraftRegs[i];
					workOrder_executionDates[i][j] =  departure[i];
					workOrder_executionPlaces[i][j] = airportMaintenance[i];
					workOrder_workPackageIDs[i][j] = workPackageIDs[i];
					workOrder_workOrderKinds[i][j] = workOrderKindOptions[r.nextInt(workOrderKindOptions.length)];
					
					
					Calendar c1 = Calendar.getInstance();
					c1.setTimeInMillis(departure[i].getTime());
					int dur = r.nextInt((250 - 20) + 1) + 20;
					c1.add(Calendar.DAY_OF_YEAR, dur);
					
					Calendar c2 = Calendar.getInstance();
					c2.setTimeInMillis(departure[i].getTime());
					dur = dur - r.nextInt((10 - 1) + 1) + 1;
					c2.add(Calendar.DAY_OF_YEAR, dur);
					
					
					workOrder_deadlines[i][j] = new Date(c1.getTimeInMillis());
					workOrder_plannedDates[i][j] = new Date(c2.getTimeInMillis());
					workOrder_frequencyUnits[i][j] = frequencyUnitsKindOptions[r.nextInt(frequencyUnitsKindOptions.length)];
					workOrder_frequencies[i][j] =  r.nextInt(100);
					workOrder_forecastedManHours[i][j] = r.nextInt(20);
					workOrder_reporteurClasses[i][j] = reportKindOptions[r.nextInt(reportKindOptions.length)];
					workOrder_reporteurIDs[i][j]  = r.nextInt(500);
					workOrder_dueDates[i][j] = new Date(c1.getTimeInMillis());
					
					
					workOrder_deferreds[i][j] = r.nextBoolean();
					workOrder_MELs[i][j] = MELCathegoryOptions[r.nextInt(MELCathegoryOptions.length)];
					
					 final java.util.Calendar cal = GregorianCalendar.getInstance();
					 cal.setTime( workOrder_dueDates[i][j] );
					
					 switch (workOrder_MELs[i][j]){
					 case "A": cal.add( GregorianCalendar.DAY_OF_YEAR, -3 ); break; 
					 case "B": cal.add( GregorianCalendar.DAY_OF_YEAR, -10 ); break; 
					 case "C": cal.add( GregorianCalendar.DAY_OF_YEAR, -30 ); break; 
					 case "D": cal.add( GregorianCalendar.DAY_OF_YEAR, -120 ); break; 
					 default: cal.add( GregorianCalendar.DAY_OF_YEAR, -5);
					 // date manipulation
					 
					 }
					 
//					 System.out.println(workOrder_MELs[i][j]);
//					 System.out.println("due date: " + workOrder_dueDates[i][j]);
					    
					workOrder_reportingDate[i][j] = cal.getTime();
					
//					System.out.println("reporting date: " + workOrder_reportingDate[i][j]);
				}
			
			
			}
			
			
			/////////////////////////
			
			//if flight
			//if (!slotKinds[i].equalsIgnoreCase("Flight")) continue;
						
			String origin = airpotCodes[r.nextInt(airpotCodes.length)];
			String dest = airpotCodes[r.nextInt(airpotCodes.length)];
			while (origin.equalsIgnoreCase(dest)){
				dest = airpotCodes[r.nextInt(airpotCodes.length)];
			}			
			originDest[i] = new Pair(origin, dest);
			if (!orgDestToFlightNo.containsKey(origin+"-"+dest)){
				String flightNo = digits.charAt(r.nextInt(10))+""+digits.charAt(r.nextInt(10))+digits.charAt(r.nextInt(10))+digits.charAt(r.nextInt(10));
				orgDestToFlightNo.put(origin+"-"+dest, flightNo);
			}	
			
			
			passangers[i] =  r.nextInt((MAX_PAS - MIN_PAS) + 1) + MIN_PAS;
			cabinCrew[i] =  r.nextInt((MAX_CCREW - MIN_CCREW) + 1) + MIN_CCREW;
			flightCrew[i] =  r.nextInt((MAX_FCREW - MIN_FCREW) + 1) + MIN_FCREW;
			
			
			String day = (scheduledDepartures[i].getDate()<10)?"0"+scheduledDepartures[i].getDate():""+scheduledDepartures[i].getDate(); 
			String month = (1+scheduledDepartures[i].getMonth()<10)?"0"+(1+scheduledDepartures[i].getMonth()):""+(1+scheduledDepartures[i].getMonth());
			String year = ((1900 + scheduledDepartures[i].getYear())+"").substring(2, 4);	
			
			//System.out.println("timestamp: "+scheduledDepartures[i]);
			//System.out.println("date1: "+scheduledDepartures[i].getDate()+" "+scheduledDepartures[i].getMonth()+" "+scheduledDepartures[i].getYear());
			//System.out.println("date2: "+day+" "+month+" "+year);
			
			flightIDs[i] =  day+month+year + "-" + originDest[i].first + "-" + originDest[i].second + "-" + orgDestToFlightNo.get(originDest[i].toString()) + "-" + aircraftRegs[i];
			//flightIDs[i] =  scheduledDepartures[i] + "-" + originDest[i].first + "-" + originDest[i].second + "-" + orgDestToFlightNo.get(originDest[i].toString()) + "-" + aircraftRegs[i];  
			
			
		}

		
//		for (String fID: flightIDs){
//			System.out.println(fID);
//		}
//		
//		for (Pair od: originDest){
//			System.out.print(od);System.out.print("; "); 
//			if (od != null) System.out.println(orgDestToFlightNo.get(od.toString()));
//		}
//		
//		
//
//		for (String s: slotKinds){
//			System.out.println(s);
//		}
//		
//			
//		for (int i=0; i<SIZE; i++){	
//			System.out.println(passangers[i] + ", "+ cabinCrew[i] + ", " + flightCrew[i]);
//		}
		
		
		
		//
		
		System.out.println("Starts writing inserts!");
		
		bw.append("SQL inserts"+"\n");
		
		String outputFlights = "";
		String outputMaintenances = "";
		for (int i=0; i<SIZE; i++){			
			if (slotKinds[i].equalsIgnoreCase("Flight"))			
				outputFlights += "("+"'"+aircraftRegs[i]+"'"+","+"'"+scheduledDepartures[i]+"'"+","+"'"+scheduledArrivals[i]+"'"+","+"'"+slotKinds[i]+"'"+","+"'"+flightIDs[i]+"'"+","+"'"+originDest[i].first+"'"+","+"'"+originDest[i].second+"'"+","+"'"+actualDepartures[i]+"'"+","+"'"+actualArrivals[i]+"'"+","+canceled[i]+","+"'"+delayCodes[i]+"'"+","+passangers[i]+","+cabinCrew[i]+","+flightCrew[i]+"),\n";
			else if (slotKinds[i].equalsIgnoreCase("Maintenance"))	
				outputMaintenances += "("+"'"+aircraftRegs[i]+"'"+","+"'"+scheduledDepartures[i]+"'"+","+"'"+scheduledArrivals[i]+"'"+","+"'"+slotKinds[i]+"'"+","+programmed[i]+"),\n";
				
		}
		bw.append("FLIGHTS"+"\n");
		bw.append(outputFlights.replaceAll("'null'", "null"));
		
		System.out.println("Flights finished!");
		
		bw.append("\n");
		bw.append("MAINTENANCES"+"\n");
		bw.append(outputMaintenances.replaceAll("'null'", "null"));
		
		System.out.println("Maintenance finished!");
		
		String outputOI = "";
		for (int i=0; i<SIZE; i++){	
			if (i%10 != 0) continue; 
			if (slotKinds[i].equalsIgnoreCase("Maintenance") || delays[i]>0)
				if (days[i]>0){
		
					for (int j=0;j<days[i];j++){
						
						java.util.Calendar c = java.util.Calendar.getInstance(); 
						c.setTime(starttimes[i]); 
						c.add(java.util.Calendar.DAY_OF_MONTH, j);
						c.set(java.util.Calendar.HOUR_OF_DAY, 0);
						c.set(java.util.Calendar.MINUTE, 0);
						c.set(java.util.Calendar.SECOND, 0);
						c.set(java.util.Calendar.MILLISECOND, 0);

						
						Timestamp newStarttime = new Timestamp(c.getTimeInMillis());
						
						outputOI += "("+maintenanceID[i]+"_"+newStarttime+","+"'"+aircraftRegs[i]+"'"+","+"'"+airportMaintenance[i]+"'"+","+"'"+subsystem[i]+"'"+
							       ","+"'"+newStarttime+"'"+","+"'1:0:0'"+","+"'"+maintenanceKinds[i]+"'"+","+
								      "'"+flightIDs[i]+"'"+","+"'"+departure[i]+"'"+","+"'"+delayCodes[i]+"'"+"),\n";
						
						if (j==0) 
							attachment_events[i][j] = maintenanceID[i]+"_"+newStarttime;
					}
					
					if (hours[i]>0 || minutes[i]>0){
						java.util.Calendar c = java.util.Calendar.getInstance(); 
						c.setTime(starttimes[i]); 
						c.add(java.util.Calendar.DAY_OF_MONTH, days[i]);
						c.set(java.util.Calendar.HOUR_OF_DAY, 0);
						c.set(java.util.Calendar.MINUTE, 0);
						c.set(java.util.Calendar.SECOND, 0);
						c.set(java.util.Calendar.MILLISECOND, 0);

						
						Timestamp newStarttime = new Timestamp(c.getTimeInMillis());
						
						//int dur = hours[i]*60 + minutes[i];
						
						outputOI += "("+maintenanceID[i]+"_"+newStarttime+","+"'"+aircraftRegs[i]+"'"+","+"'"+airportMaintenance[i]+"'"+","+"'"+subsystem[i]+"'"+
						       ","+"'"+newStarttime+"'"+","+"'0:"+hours[i]+":"+minutes[i]+"'"+","+"'"+maintenanceKinds[i]+"'"+","+
							      "'"+flightIDs[i]+"'"+","+"'"+departure[i]+"'"+","+"'"+delayCodes[i]+"'"+"),\n";
					}
					
					
				}
				else {
					outputOI += "("+maintenanceID[i]+"_"+starttimes[i]+","+"'"+aircraftRegs[i]+"'"+","+"'"+airportMaintenance[i]+"'"+","+"'"+subsystem[i]+"'"+","+"'"+starttimes[i]+"'"+","+"'"+days[i]+":"+hours[i]+":"+minutes[i]+"'"+","+"'"+maintenanceKinds[i]+"'"+","+"'"+flightIDs[i]+"'"+","+"'"+departure[i]+"'"+","+"'"+delayCodes[i]+"'"+"),\n";
				}
				
		}
		
		bw.append("\n");
		bw.append("OIs"+"\n");
		bw.append(outputOI.replaceAll("'null'", "null"));
		System.out.println("OIs finished");
		
		String attachments = "";
		for (int i=0; i<SIZE; i++){	
			if (slotKinds[i].equalsIgnoreCase("Maintenance") || delays[i]>0){
				for (int j=0; j<MAX_ATTCH_SIZE; j++){	
					attachments +=  "("+"'"+attachment_files[i][j]+"'"+","+attachment_events[i][j]+"),\n";
				}
				
			}
		}
		bw.append("\n");
		bw.append("ATTACHMENTS"+"\n");
		bw.append(attachments.replaceAll("'null'", "null"));
		System.out.println("Attachements finished!");
		
		
		String workPackages = "";
		
		String forecastedOrders = "";
		String technicalLogBookOrders = "";
		for (int i=0; i<SIZE; i++){	
			if (slotKinds[i].equalsIgnoreCase("Maintenance") || delays[i]>0){
				workPackages += "("+workPackageIDs[i]+","+"'"+executionDates[i]+"'"+","+"'"+executionPlaces[i]+"'"+"),\n";
				
				for (int j=0; j<MAX_WORK_ORDERS; j++){	
					if (workOrder_workOrderKinds[i][j].equalsIgnoreCase("Forecast"))
						forecastedOrders +=  "("+workOrderIDs[i][j]+","+"'"+workOrder_aircraftRegs[i][j]+"'"+","+"'"+workOrder_executionDates[i][j]+"'"+","+"'"+workOrder_executionPlaces[i][j]+"'"+","+workOrder_workPackageIDs[i][j]+","+"'"+workOrder_workOrderKinds[i][j]+"'"+","+"'"+workOrder_deadlines[i][j]+"'"+","+"'"+workOrder_plannedDates[i][j]+"'"+","+workOrder_frequencies[i][j]+","+"'"+workOrder_frequencyUnits[i][j]+"'"+","+workOrder_forecastedManHours[i][j]+"),\n";
					else if (workOrder_workOrderKinds[i][j].equalsIgnoreCase("TechnicalLogBook"))
						technicalLogBookOrders +=  "("+workOrderIDs[i][j]+","+"'"+workOrder_aircraftRegs[i][j]+"'"+","+"'"+workOrder_executionDates[i][j]+"'"+","+"'"+workOrder_executionPlaces[i][j]+"'"+","+workOrder_workPackageIDs[i][j]+","+"'"+workOrder_workOrderKinds[i][j]+"'"+","+"'"+workOrder_reporteurClasses[i][j]+"'"+","+workOrder_reporteurIDs[i][j]+","+"'"+workOrder_dueDates[i][j]+"'"+","+workOrder_deferreds[i][j]+","+"'"+workOrder_MELs[i][j]+"'"+","+"'"+workOrder_reportingDate[i][j]+"'"+"),\n";
				}
				
			}
		}
		bw.append("\n");
		bw.append("WORK PACKAGES"+"\n");
		bw.append(workPackages.replaceAll("'null'", "null"));
		System.out.println("Work packages finished!");
		
		bw.append("\n");
		bw.append("FORECAST ORDERS"+"\n");		
		bw.append(forecastedOrders.replaceAll("'null'", "null"));
		System.out.println("Forcast orders finished!");
		
		
		bw.append("\n");
		bw.append("TECHNICAL LOG BOOK ORDERS"+"\n");		
		bw.append(technicalLogBookOrders.replaceAll("'null'", "null"));
		System.out.println("Tech log bok orders finished!");
	
		bw.close();
		
		System.out.println("All done!");
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
