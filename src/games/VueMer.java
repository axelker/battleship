package games;
import players.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class VueMer extends JPanel implements EcouteurModel
{
    //Dimension forme
    private final static int dim = 30;
    private final static int distanceRondsCol = 15; 
    private final static int distanceRondsLigne =2;

    //RAJOUTER POUR SEULEMENT COMPRENDRE LE DESSIN
    private final static int longueur=1200;
    private final static int hauteur=600;
    //MER
    private Mer mer;
    public VueMer(Mer mer)
    {
        this.mer=mer;
        mer.ajoutEcouteur(this);
        // Dessine le rectangle 
        setPreferredSize(new Dimension(longueur,hauteur));
    }

    /**
     * Draw player fleets graphically. 
     */
    @Override
    public void paintComponent(Graphics g)
    {
       
        super.paintComponent(g);
        Graphics2D g2D=(Graphics2D) g;
        Font font = new Font("Arial",Font.ITALIC,20);
        g.setFont(font);
        
        g.drawRect(40,25,500,500);
        g.drawRect(635,25,500,500);
        
        int ligne=0;
        int cmpt=0;
        for(int i=0;i<10;i++)
        {
            //Compteur à incrementer 
            int cmpt2=620;
            cmpt=25;
            ligne+=23;

            //Chiffres ligne
            g.drawString(""+i,20,ligne*distanceRondsLigne+20);
            g.drawString(""+i,615,ligne*distanceRondsLigne+20);
            //Nom des mers
            g.drawString("Mer Joueur Random ",20,550);
            g.drawString("Mer Joueur",615,550);

            for(int k=0;k<10;k++)
            {
                cmpt+=dim+distanceRondsCol;
                cmpt2+=dim+distanceRondsCol;
                //Chiffres Colonne
                g.drawString(""+k,cmpt+10,dim-10);
                g.drawString(""+k,cmpt2+10,dim-10);
                //Dessin ronds et rectangle 
                Dessin(cmpt,ligne,mer.getRandom().getValFlotte(i,k).toString(),g,mer.getRandom());
                Dessin(cmpt2,ligne,mer.getJoueur().getValFlotte(i,k).toString(),g,mer.getJoueur());
            }
        }      
        
        
        
    }
    /**
     * Draw the circles used to represent the contents of the fleets.
     * @param cmpt
     * @param ligne
     * @param c
     * @param g
     * @param P
     */
    //Dessine les ronds selon le remplissage d'un tableau 
    public void Dessin(int cmpt,int ligne,String c,Graphics g,Player P)
    {
        /*if(!c.equals(" C "))
        {
            g.drawOval(cmpt,ligne*distanceRondsLigne,dim,dim);
        }*/
        if(P.equals(mer.getJoueur()))
        {
            if(c.equals(" - "))
            {
                g.setColor(Color.BLUE);
                g.drawRect(cmpt,ligne*distanceRondsLigne,dim+12,dim+12);
                g.setColor(Color.BLACK);
            }
        }

        if(c.equals(" X "))
        {
            g.setColor(Color.RED);
            g.fillOval(cmpt,ligne*distanceRondsLigne,dim,dim);
            g.setColor(Color.BLACK);
        }
        if(c.equals(" ! "))
        {
            g.setColor(Color.GREEN);
            g.fillOval(cmpt,ligne*distanceRondsLigne,dim,dim);
            g.setColor(Color.BLACK);
        } 
        if(c.equals(" C "))
        {
            g.setColor(Color.BLUE);
            g.fillRect(cmpt,ligne*distanceRondsLigne,dim+12,dim+12);
            g.setColor(Color.BLACK);
        }           
    
    }
    

    @Override
    public void NouvelleAffichage(Object source)
    {
        this.repaint();
        System.out.println(mer.toString());
    }
}