package games;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;



public class MerGui extends JFrame implements MouseListener {
private Mer mer;
private  Aleatoire A = new Aleatoire();
    public MerGui(Mer mer)
    {
        super("Bataille-Navale");
        this.mer=mer;
        //Taille fenetre et quitter fenetre lors d'un clique 
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centrer la fentre 
        this.setLocationRelativeTo(null);
       
        Container cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        // Ajout des vues 
        VueMer vue = new VueMer(mer);
        vue.addMouseListener(this);
        cp.add(vue,BorderLayout.CENTER);
        cp.add(new VueMerLabel(mer),BorderLayout.SOUTH);
       
        this.pack();
        this.setVisible(true);
    }
/**
 * Test if the mouse click at the index x and y  is on the Random player's fleet.
 * @param x
 * @param y
 * @return True if the click is valid. Else False.
 */
    //Test si le clique souris est dans les bonnes coordonées
    public boolean CliqueValid(int x,int y)
    {
        if(x>=40 && x<=540 && y>=25 && y<=525)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Close the application if the game has a winner.
     */
    //Ferme la fenetre si la partie est terminé 
    public void FermetureInterface()
    {
        if(mer.isOver())
        {
            System.exit(0);
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
        if(CliqueValid(e.getX(),e.getY()))
        {
            PositionCoord P = new PositionCoord();
            P.rechercheCoord(e.getX(),e.getY());
            if(mer.getJoueur().isValid(P.getX(),P.getY(),mer.getRandom().getFlotte()))
            {
                //Coup joueur souris
                mer.play(P.getX(),P.getY(),mer.getJoueur()); 
                FermetureInterface();
                //Coup joueur random
                int place = mer.getJoueurCourrant().SaisieJoueur(mer.getJoueur().getFlotte());
                mer.play(A.Ligne(place),A.Colonne(place),mer.getRandom());
            }
            FermetureInterface();
        }  
        
    }

    @Override
    public void mouseEntered(MouseEvent e){

    }
        
    @Override
    public void mouseExited(MouseEvent e)
    {

    }
    @Override
    public void mousePressed(MouseEvent e)
    {

    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }
    
}