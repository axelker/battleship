package games;
import java.util.*;
import java.awt.*;


public class Aleatoire
{
    

    public Aleatoire()
    {
        
    }


    /**
     * 
     * @param alea
     * @returna random number in the range 0 | alea
     */
    /////// Fonction aléatoire ////////
    // Tirer un nombre aleatoire
    public int NombreAleatoire(int alea)
    {
        int nombre=0;
        Random r = new Random();
        nombre=r.nextInt(alea);
        return nombre;
    }

    /**
     * Converts an placement to a fleet line
     * @param place
     * @return line
     */
    
    ////Convertis un entier en une ligne de la grille //////
    public int Ligne(int place)
    {
        int ligne=0;int i=0;int debut=0;
        int fin=10;
        // incrémenter les lignes de 0 jusqu'a 10 
        while(i!=10)
        {   
            // Tester si le coup est entre deux valeurs 
            if(place>=debut && place<fin)
            {
                ligne=i;
                return ligne;
            }
            i++;
            debut+=10;
            fin+=10;

        }
        return ligne;    
    }

         
    /**
     * Converts an placement to a fleet row
     * @param place
     * @return row
     */
    public int Colonne(int place)
    {
        int colonne=0;
        
        return colonne = place%10;

    }
    /**
     * Convert a row and column to a place in the fleet
     * @param ligne
     * @param colonne
     * @return placement
     */
    public int Place(int ligne,int colonne)
    {
        int place=(ligne*10)+colonne;
        return place;
        
    }



       

/////////////////////////////////////////////////
}