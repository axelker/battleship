package games;
import cell.*;
import java.util.*;
import players.*;

public class Mer extends AbstractModelEcoutable {

    protected Player Joueur = new Humain();
    protected Player JoueurRandom = new JoueurRandom();
    protected Player JoueurCourrant = new Humain();
    //Nombre de case bateau 
    
    protected Aleatoire A = new Aleatoire();
    

    public Mer(Player Joueur,Player JoueurRandom)
    {
        this.Joueur=Joueur;
        this.JoueurRandom=JoueurRandom;
        this.JoueurCourrant=Joueur;

    }
    /**
     * 
     * @return the current player
     */
    public Player getJoueurCourrant()
    {
        return this.JoueurCourrant;
    }
    /**
     * 
     * @return the human
     */
    public Player getJoueur()
    {
        return this.Joueur;
    }
    /**
     * 
     * @return the random player
     */
    public Player getRandom()
    {
        return this.JoueurRandom;
    }
    
    /**
     * Change the value of current player
     */
    public void ChangeJoueurCourrant()
    {
        if(JoueurCourrant.equals(this.Joueur))
        {
            this.JoueurCourrant=this.JoueurRandom;
        }
        else {
            this.JoueurCourrant=this.Joueur;
        }
    }

    /**
     * 
     * @param P
     * @return the player not equals to parameter P
     */
    public Player getOtherJoueur(Player P)
    {
        if(P.equals(this.Joueur))
        {
            return this.JoueurRandom;
        }
    
            return this.Joueur;
    }
   /**
    * Test if the value of to string of the fleet at the index row column is equal to "Case c".
    * @param ligne
    * @param colonne
    * @param P
    * @param c
    * @return
    */
   public boolean TestValeurCase(int ligne,int colonne,Player P,String c)
   {
       if(P.getValFlotte(ligne,colonne).equals(c))
       {
           return true;
       }
       return false;
   }

/**
 * Initializes player fleets with object  "CaseVide".
 */
    public void initFlotte()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                Case c = new CaseVide();
                this.Joueur.setFlotte(c,i,j);
                this.JoueurRandom.setFlotte(c,i,j);
            }
        }
    }
/**
 * Random placement of boats.
 * @param P
 * @return the list of boat placements.
 */
    public ArrayList<Integer> InitMer(Player P)
    {
        
        for(int i=0;i<5;i++)
        {
            //Tirage de la case aleatoire ; et la direction
            int compteurBateau=P.getnbBatindince(i);
            int cmptListe=0;
            int placement=A.NombreAleatoire(100);
            int direction = A.NombreAleatoire(2);
            int ligne=A.Ligne(placement);
            int colonne=A.Colonne(placement);
          


            // Remplir horizontal
            if(direction==0)
            {
                //Test ne pas tirer une case deja tirer 
                 while(P.testBateauAdjacent(placement,ligne,colonne,colonne,compteurBateau,direction)==true || P.getlisteCase().contains(placement))
                {
                    placement=A.NombreAleatoire(100);
                    ligne=A.Ligne(placement);
                    colonne=A.Colonne(placement);
                }
                P.TirageBateau(ligne,colonne,placement,colonne,compteurBateau,direction);
            }
            //Remplir vertical 
            if(direction==1)
            {
                //Test ne pas tirer une case deja tirer 
                while(P.testBateauAdjacent(placement,ligne,colonne,ligne,compteurBateau,direction)==true || P.getlisteCase().contains(placement))
                {
                    placement=A.NombreAleatoire(100);
                    ligne=A.Ligne(placement);
                    colonne=A.Colonne(placement);
                }
                P.TirageBateau(ligne,colonne,placement,ligne,compteurBateau,direction);
            
            }
            //REMPLISSAGE DE LA FLOTTE DU JOUEUR EN PARAMETRE EN UTILISANT LA LISTE DES CASE BATEAUX DE CELUI CI 
            for(int t = cmptListe;t<P.getlisteCase().size();t++)
            {
                int place = P.getlisteCase().get(t);
                Case bateau = new CaseBateau();
                P.setFlotte(bateau,A.Ligne(place),A.Colonne(place));
            }
            cmptListe+=compteurBateau;
        }
        return P.getlisteCase();
    }
   





/**
 * Perform a line-column clue shot on the player's fleet that is not in parameter.
 * @param ligne
 * @param colonne
 * @param P
 */
    public void play(int ligne,int colonne,Player P)
    {
        Player Ennemie = getOtherJoueur(P);

        //Case vide  
        if(TestValeurCase(ligne,colonne,Ennemie,"   "))
        {
            Case Nontoucher = new NonToucher();
            Ennemie.setFlotte(Nontoucher,ligne,colonne);
                
        }
        //Case contenant bateau
        else if(TestValeurCase(ligne,colonne,Ennemie," - ")){
            Case touche = new Toucher();
            Ennemie.setFlotte(touche,ligne,colonne);
            //decrementer 
            Ennemie.setcaseBateau();
        }
        Ennemie.VerifCouler();
        ChangeJoueurCourrant();
        MiseAjourAffichage();
    }
/**
 * Test if one of the two players has no boats
 * @return 0 it's a game in progress , 1 the Random win , 2 the human win. 
 */
    public int win()
    {
        // Retourner le joueur rand si le joueur n'a plu de bateau 
        if(Joueur.getcaseBateau()==0)
        {
            return 2;
        }
        else if (JoueurRandom.getcaseBateau()==0)
        {
            return 1;
        }
        // Partie en cours 
        return 0;
    }
   /**
    * Notify the state of the game.
    * @return The state of the game.
    */
    public String winner()
    {
        if(win()==0)
        {
            return "Partie en cours ...";
        }
        
        if(win()==1)
        {
            return "Vous avez gagné ! ";
        }
        
        return "Le joueur random à gagné ! ";
        
        
    }
    /**
     * Test if the game has a winner.
     * @return False if the game is not over. True if is over.
     */
    public boolean isOver()
    {
        if(win()==0)
        {
            return false;
        }
        return true;
    }
    /**
     * Represents a player's fleet by a character chain.
     * @param P
     * @return The display of the fleet player P.
     */
    public String Affichage(Player P)
    {
        String res = " ";
        int tmp=0;  

        for(int i=0;i<10;i++)
        { 
            res+=" " + i + " ";
        }
        for(int i=0;i<10;i++)
        {   
            res+=System.lineSeparator();
            res+=i; 
            for(int j=0;j<10;j++)
            {
                if(P.getValFlotte(i,j).equals(" - "))
                {
                    if(P.equals(this.Joueur))
                    {
                        res+=" - ";
                    }
                    else {
                        res +="   ";
                    }
                }
                else{
                res+=P.getValFlotte(i,j);
                }
            }
        }

    return res;
    }
/**
 * Concatenate the display of the fleets of both players.
 * @return game display on terminal.
 */
    public String AffichageMer()
    {
        String res="Mer Joueur " + System.lineSeparator();
        res+=Affichage(this.Joueur);
        res+=System.lineSeparator()+" Mer Joueur Random " + System.lineSeparator();
        res+=Affichage(this.JoueurRandom);
        return res;
    }

    @Override
    public String toString()
    {
        return AffichageMer();
    }
}