package games;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class VueMerLabel extends JLabel implements EcouteurModel
{

    private Mer mer;
    public VueMerLabel(Mer mer)
    {
        super(mer.winner());
        this.mer=mer;
        mer.ajoutEcouteur(this);
    }


@Override
public void NouvelleAffichage(Object source)
{
    //Mettre a jours le texte 
    this.setText(mer.winner());
    System.out.println(mer.winner());
}

}