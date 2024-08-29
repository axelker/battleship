package players;
import java.util.*;
import games.Aleatoire;
import cell.*;



public class Humain extends Player{

    public Humain()
    {

    }
    
   
    @Override
    //Retourne case saisie 
    public int SaisieJoueur(Case [][] flotteEnnemi)
    {
        
        Scanner scanner=new Scanner(System.in);
        int ligne=0;
        int colonne=0;
        //Affichage saisie du coup 
        
        System.out.println(" \n " + "Joueur Saisir ligne à jouer : ");
        ligne=scanner.nextInt();
        System.out.println("Saisir colonne à jouer : ");
        colonne=scanner.nextInt();

        //Tant que coup non valide 
        while(!super.isValid(ligne,colonne,flotteEnnemi))
        {
            System.out.println("Erreur ! Saisir ligne à jouer : ");
            ligne=scanner.nextInt();
            System.out.println("Saisir colonne à jouer : ");
            colonne=scanner.nextInt();
        } 

        // RETOURNE LIGNE COLONNE SOUS FORME DE CASE SUR FLOTTE 
        int place = A.Place(ligne,colonne);
        return place;

    }     

    @Override
    public String toString()
    {
        return " Humain ";
    }
}