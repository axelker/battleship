package players;
import java.util.*;
import games.Aleatoire;
import cell.*;

public class JoueurRandom extends Player {

    public JoueurRandom()
    {

    }
    @Override
    public int SaisieJoueur(Case [][] flotteEnnemi)
    {
        int ligne=A.NombreAleatoire(10);
        int colonne=A.NombreAleatoire(10);
        while(!super.isValid(ligne,colonne,flotteEnnemi))
        {
            ligne=super.A.NombreAleatoire(10);
            colonne=super.A.NombreAleatoire(10);
        }
        int place = super.A.Place(ligne,colonne);
        return place;
    }
    
                                
                
    @Override
    public String toString()
    {
        return " Joueur Random ";
    }

}