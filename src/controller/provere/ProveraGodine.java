package controller.provere;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProveraGodine {

	public static Boolean proveri(String datum, int br) {
		
		String []date = datum.split("/");
		int dan = 0;
		int mesec = 0;
		int godina = 0;
		
		try {
			
			dan = Integer.parseInt(date[0]);
			mesec = Integer.parseInt(date[1]);
			godina = Integer.parseInt(date[2]);
			
			
		}catch(Exception e) {
			
			
			
		}
		
		int brCifara = String.valueOf(godina).length();
		
		if(brCifara!=4) {
			
			return false;
			
		}
		
		
		Boolean pDan= true;
		Boolean pMesec= true;
		Boolean pGodina= true;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
		LocalDateTime now = LocalDateTime.now();
		int trenutnaGod = Integer.parseInt(dtf.format(now));
		
		if(mesec<1 || mesec >12) {
			
			pMesec=false;	
		
		}
		if(dan<1) {
			
			pDan=false;
		
		}
		if(dan>31) {
			
			pDan= false;
			
		}
		if((dan==31 && (mesec==2 || mesec== 4 || mesec==6 || mesec==9 || mesec==11))){
			
			pDan= false;
		
		}
		if(dan == 29 && mesec == 2) {
			
			if(godina % 4 != 0)
				pDan= false;
		}
		
		if(dan == 30 && mesec == 2) {
			
			pDan = false;
			
		}
		
		if(br==2) {
			
			//provera da li je moguce da profesor ima barem master (sto se godina tice)
			
			if((godina>trenutnaGod) || (godina> trenutnaGod - 23)) {
				
				pGodina = false;
				
			}
			
		}

		if(pDan==false || pMesec==false || pGodina==false) {
			
			return false;
			
		}
		else
			return true;
		
	}
	
}
