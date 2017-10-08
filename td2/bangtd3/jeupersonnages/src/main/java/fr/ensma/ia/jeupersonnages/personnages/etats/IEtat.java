package fr.ensma.ia.jeupersonnages.personnages.etats;

import fr.ensma.ia.jeupersonnages.personnages.Personnage;
import fr.ensma.ia.jeupersonnages.personnages.etats.exception.EtatPersonnageException;

public interface IEtat {
// def des transitions. throws EtatPersonnageException 

public void deplacer () throws EtatPersonnageException;
public void finDeplacement() throws EtatPersonnageException;

public void arret() throws EtatPersonnageException;
public void attaquer() throws EtatPersonnageException;

public void sortilege() throws EtatPersonnageException;
public void finSortilege() throws EtatPersonnageException;

public void niveauVie() throws EtatPersonnageException;

}
