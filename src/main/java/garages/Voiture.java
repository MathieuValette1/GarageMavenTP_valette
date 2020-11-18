package garages;

import java.io.PrintStream;
import java.util.*;
import java.lang.Exception;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
        
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
                if (this.estDansUnGarage()){
                    throw new Exception("La voiture est déjà stationnée quelque part :/");
                }
                Stationnement s = new Stationnement(this, g);
                myStationnements.add(s);
        }

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (!myStationnements.isEmpty()){
                    
                    // On trouve le dernier stationnement de la voiture
                    Stationnement dernierStationnement = myStationnements.get(myStationnements.size()-1);
                    if (dernierStationnement.estEnCours()){
                        // On le termine si il est en cours
                        dernierStationnement.terminer();
                    }
                    else {
                        throw new Exception("La voiture n'est pas dans un garage :/");
                    }
                }
                else {
                    throw new Exception("La voiture n'est pas dans un garage :/");
                }
                }
	

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		
		Set<Garage> garagesVisites = new HashSet<>();
                Iterator<Stationnement> stationnementsIterator = myStationnements.iterator();
                while(stationnementsIterator.hasNext()){
                    garagesVisites.add(stationnementsIterator.next().getGarage());   
                }
                return garagesVisites;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// Cela reviens à vérifier si la voiture a un stationnement en cours
                if (!myStationnements.isEmpty()){
                    Stationnement dernierStationnement = myStationnements.get(myStationnements.size()-1);
                    if (dernierStationnement.estEnCours()){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else {
                    return false;
                }
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
            
                Iterator<Garage> garagesIterator = this.garagesVisites().iterator();                
                while (garagesIterator.hasNext()){
                    Iterator<Stationnement> stationnementIterator = myStationnements.iterator();
                    Garage g = garagesIterator.next();
                    out.println(g.toString()+":");
                    while (stationnementIterator.hasNext()){
                        Stationnement s = stationnementIterator.next();
                        if (g.equals(s.getGarage())) {
                            out.println("\t " + s.toString());
                        }
                    }
               
                }
                
        }
}
        


