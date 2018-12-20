package model;

import java.util.Vector;

public class AlgoritmosTPA {
	
	public static Vector<String> Binaria(String PacientesOrdenados[], String PacienteBusqueda) {
		Vector <String> PacienteTecnico = new Vector<String>();
		int izq=0; 
		int der=PacientesOrdenados.length-1;
	if(der==-1) {
		 PacienteTecnico.addElement("NOCONSIGUIO");
		 return PacienteTecnico;
	}
		while((izq<=der)) {
			int cen=izq+(der-1)/2;
			while(der!=0) {
				 PacienteTecnico.addElement(PacientesOrdenados[der].toString());
					der--;
				}
				
				while(cen!=0) {
					PacienteTecnico.addElement(PacientesOrdenados[der].toString());
					cen--;
				}
				while(izq!=0) {
					PacienteTecnico.addElement(PacientesOrdenados[der].toString());
					izq--;
				}
			if((int)PacienteBusqueda.charAt(0) == (int)PacientesOrdenados[der].charAt(der)){
				if(!PacienteTecnico.contains(PacientesOrdenados[der].toString())) {
				 PacienteTecnico.addElement(PacientesOrdenados[der].toString());
				}
				 return PacienteTecnico;
				
			}
			if((int)PacienteBusqueda.charAt(0)>(int)PacientesOrdenados[cen].charAt(0)){
					izq=cen+1;
				}     
			
		}
			
		
		return PacienteTecnico;
	}
	
	public static Vector<String> bubble(Vector<String> pacienteTecnico) {
		String temporal;
		String[] temporal1=pacienteTecnico.toArray(new String[pacienteTecnico.size()]);
		Vector<String> pacienteTecnico2 = new Vector<String>();
		for (int t = 0; t < temporal1.length - 1; t++) {
			for (int i= 0; i < temporal1.length - t -1; i++) {
				if(temporal1[i+1].compareTo(temporal1[i])<0) {
					temporal = temporal1[i];
					temporal1[i] = temporal1[i + 1];
					temporal1[i + 1] = temporal;
				}
			}
		}
		for (int i = 0; i < temporal1.length; i++) {
			pacienteTecnico2.add(temporal1[i]);
		}
		return pacienteTecnico2;
	}

}
