package games;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public abstract class AbstractModelEcoutable {
	
     // Liste des differentes vue    
     private ArrayList<EcouteurModel>ecouteurs;
    public AbstractModelEcoutable()
    {
        ecouteurs=new ArrayList<>();
    }
/**
 * Add the parameter e to the list ecouteurs.
 * @param e
 */
    public void ajoutEcouteur(EcouteurModel e)
    {
        ecouteurs.add(e);
    }
    /**
     * Remove the parameter e in the list ecouteurs.
     * @param e
     */
    public void retraitEcouteur(EcouteurModel e)
    {
        ecouteurs.remove(e);
    }
    /**
     * Cycle through the list and call the display method for each item retrieved from the list.This is an update of the views.
     */
    protected void MiseAjourAffichage()
    {
        for(EcouteurModel e : ecouteurs)
        {
            e.NouvelleAffichage(this);
        }
    }


}