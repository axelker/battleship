package games;


public class PositionCoord{
    private int x;
    private int y;

    public PositionCoord()
    {

    }

    public int getX(){
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void setX(int ligne)
    {
        this.x=ligne;
    }
    public void setY(int colonne)
    {
        this.y=colonne;
    }
    
    /**
     * Test if the coordinates in parameter correspond to the position of a circle drawn in the graphic interface.
     * @param x
     * @param y
     * @param ligne
     * @param colonne
     * @return True if the position is find. Else false.
     */
    public boolean Test(int x,int y,int ligne,int colonne)
    {
        if((ligne<=y && ligne+50>=y) && (colonne<=x && colonne+50>=x))
        {
            return true;
        }
        return false;
    }
    /**
     * Finds the row and column indices of the player's fleet corresponding to the click made. x is modified by the value of "i" and y by "j".
     * @param x
     * @param y
     */
    public void rechercheCoord(int x,int y)
    {
        int ligne=25;
        for(int i=0;i<10;i++)
        {
            
            int colonne=40;
           
            for(int j=0;j<10;j++)
            {
                if(Test(x,y,ligne,colonne))
                {
                    setX(i);
                    setY(j);
                    break;
                }
                colonne+=50;
            }
            ligne+=50;
        }
    }
}