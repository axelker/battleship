package main;
import games.*;
import players.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Main{
    

     public static void main(String[] args)
    {
        Humain Joueur = new Humain();
        JoueurRandom JoueurRand = new JoueurRandom();
        Mer mer= new Mer(Joueur,JoueurRand);
        ArrayList<Integer>Liste = new ArrayList<Integer>();
        Aleatoire A = new Aleatoire();
        mer.initFlotte();
        //Tirage bateau + stockage des place dans les listes 
        Joueur.setlisteCase(mer.InitMer(Joueur));
        JoueurRand.setlisteCase(mer.InitMer(JoueurRand)); 
         MerGui windows = new MerGui(mer);  
        
        System.out.println(mer.toString());
        //RAJOUTER PEUT ETRE LE CHOIX DE JOURER SUR 'INTERFACE OU CLAVIER '
        
        while(!mer.isOver())
        {
           
            Player Courrant = mer.getJoueurCourrant();
            Player NonCourrant = mer.getOtherJoueur(Courrant);
            int place = mer.getJoueurCourrant().SaisieJoueur(NonCourrant.getFlotte());
            mer.play(A.Ligne(place),A.Colonne(place),Courrant);
            
        }
        System.exit(0);

    }
}