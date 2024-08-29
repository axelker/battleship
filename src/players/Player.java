package players;
import java.util.*;
import games.Aleatoire;
import cell.*;



public abstract class Player{

    //Nombre de case bateau 
    private final static int [] nbBateau = {2,3,3,4,5};
    private int caseBateau=17;
    protected Aleatoire A = new Aleatoire();
    private Case [][] flotte = new Case [10][10];
    private ArrayList<Integer> listeCase = new ArrayList<Integer> ();
    private ArrayList<Integer> listeCouler = new ArrayList<Integer> ();
   

    public Player()
    {

    }
    /**
     * 
     * @param indice
     * @return The number in the final array nbBateau at the index in parameter
     */
    public int getnbBatindince(int indice)
    {
        return nbBateau[indice];
    }
    /**
     * 
     * @return  variable caseBateau
     */
    public int getcaseBateau()
    {
        return this.caseBateau;
    }
    /**
     * Decrement the caseBateau variable
     */
    public void setcaseBateau()
    {
        this.caseBateau--;
    }
    /**
     * 
     * @return listeCase
     */
    public ArrayList<Integer> getlisteCase()
    {
        return this.listeCase;
    }
    public void setlisteCase(ArrayList<Integer> liste)
    {
         this.listeCase=liste;
    }
    public ArrayList<Integer> getListeCouler()
    {
        return this.listeCouler;
    }
    public void setListeCouler(int valeur)
    {
        this.listeCouler.add(valeur);
    }
    public Case [][] getFlotte()
    {
        Case [][] f = new Case [10][10];
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                f[i][j]=this.flotte[i][j];
            }
        }
        return f;
    }
    /**
     * 
     * @param ligne
     * @param colonne
     * @return the toString value in the array "flotte" at the row ligne and column colonne
     */
    public String getValFlotte(int ligne,int colonne)
    {
        return flotte[ligne][colonne].toString();
    }
    /**
     * The array flotte at the row ligne and column colonne equals c
     * @param c
     * @param ligne
     * @param colonne
     */
    public void setFlotte(Case c,int ligne,int colonne)
    {
        this.flotte[ligne][colonne]=c;
    }
  /**
   * Test that the indices in parameter are in the interval of those of the array
   * @param ligne
   * @param colonne
   * @return True if the index are out of the array
   */
    public boolean SortiePlateau(int ligne,int colonne)
    {
        if(ligne<0 || ligne >9  || colonne >9 || colonne <0)
        {
            return true;
        }
        return false;
        
    }
    /**
     * Test if a shot on the enemy fleet is valid
     * @param ligne
     * @param colonne
     * @param flotteEnnemi
     * @return True if the shot is valid
     */
    public boolean isValid(int ligne,int colonne,Case[][] flotteEnnemi)
    {
        if(!SortiePlateau(ligne,colonne) && (flotteEnnemi[ligne][colonne].toString().equals("   ") || flotteEnnemi[ligne][colonne].toString().equals(" - ")))
        {
            return true;
        }
        return false;
    }
   
    /**
     *  Test if floats at index row column is empty
     * @param ligne
     * @param colonne
     * @return True if the float index is empty
     */
    public boolean testcaseVide(int ligne,int colonne)
    {
        if(this.flotte[ligne][colonne].toString().equals("   "))
        {
            return true;
        }
        return false;
    }

   
    /////////////////PLACEMENT BATEAU FLOTTE ////////////////////:
    /**
     * Take in parameter a "coup" which is a number drawn randomly. This number according to the direction increments its row or its column (indice in parameter). 
     * The placement of the fleet corresponding to these incremented indices are tested.
     * @param coup
     * @param ligne
     * @param colonne
     * @param indice
     * @param direction
     * @return coup or -1 if the fleet array is at the index coup incremented is not empty 
     */
    
    
    // Incrementer l'indice retourner -1 si la case n'est pas vide 
    public int testindice(int coup,int ligne,int colonne,int indice,int direction)
    {
        ///Incremente direction selon ligne ou colonne (direction 0 colonne et diretion 1 ligne) ///
        int valeurdirection=direction*10;
        // indice plus petit que 5 incrementer la case
        if(indice<=4)
        {
            coup=coup+((valeurdirection-direction)+1);
        }
        //Si plus grands 4 decrementer 
        if(indice>=5)
        {
            coup=coup-(valeurdirection);
            if(direction==0)
            {
                coup=coup-1;
            }
        }
        // Test qui retourne -1 si un coup est sur une case deja prise par rapport au colonne pour la direction 0 (bateau horizontale)//    
        if(testcaseVide(ligne,A.Colonne(coup))!=true && indice==colonne && direction==0)
        {
            return -1;
        }
        // Test qui retourne -1 si un coup est sur une case deja prise par rapport au ligne pour la direction 1 (bateau veritcal) //    
        if(testcaseVide(A.Ligne(coup),colonne)!=true && indice==ligne && direction==1)
        {
            return -1;
        }
        return coup;
    }
    /**
     * Test if the placement of a boat does not collide. 
     * @param coup
     * @param ligne
     * @param colonne
     * @param indice
     * @param nbcase
     * @param direction
     * @return True the boat are in collide. False the boat are not collide.
     */
    
    // Tester si des bateau se touche si -1 retourner vrai
    public boolean testBateauAdjacent(int coup,int ligne,int colonne,int indice,int nbcase,int direction)
    {
        
        for(int i=0;i<nbcase;i++)
        {
            coup=testindice(coup,ligne,colonne,indice,direction);
            if(coup==-1)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Add the location of the boats in the listCase.
     * @param ligne
     * @param colonne
     * @param coup
     * @param indice
     * @param nbcase
     * @param direction
     */
    //Met dans la liste en parametre l'emplacement des bateaux 
    public void TirageBateau(int ligne,int colonne,int coup,int indice,int nbcase,int direction)
    {
     
        //ajouter les case de l'array liste
        for(int i=0;i<nbcase;i++)
        {
            this.listeCase.add(coup);
            coup=testindice(coup,ligne,colonne,indice,direction);
        }

    }
/////////////////////////////////////////////////////////
///////////////////Bateau Couler ///////////////////
    
    /**
     * Add the location of a sunken boat's berth number to the BateauCouler list
     */
    public void VerifCouler()
    {
        int cmptListe=0;
        for(int i=0;i<5;i++)
        {
            int compteurBateau=getnbBatindince(i);
            int cmpt=0;
            
            for(int t = cmptListe;t<cmptListe+compteurBateau;t++)
            {
                
                int place = getlisteCase().get(t);
                if(getValFlotte(A.Ligne(place),A.Colonne(place)).equals(" X "))
                {
                    cmpt++;
                }
                if(cmpt==compteurBateau)
                {
                    BateauCouler(cmptListe,compteurBateau);
                }
                
            }
            cmptListe+=compteurBateau;
        }
    }
    
    /**
     * Modify the values of the fleet whose boats are sunk.
     * @param cmptListe
     * @param compteurBateau
     */
        public void BateauCouler(int cmptListe,int compteurBateau)
        {
            for(int i=cmptListe;i<cmptListe+compteurBateau;i++)
            {
                int place = getlisteCase().get(i);
                setListeCouler(place);
                Case bateau = new Couler();
                setFlotte(bateau,A.Ligne(place),A.Colonne(place));
            }
            
        }
 /////////////////////////////////////////////////////////////////////  
    /**
     * Method returning location of a Player shot
     * @param flotteEnnemi
     * @return
     */
    public abstract int SaisieJoueur(Case [][] flotteEnnemi);
}