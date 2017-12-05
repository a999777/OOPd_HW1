package homework1;

import java.awt.*;

/**
 * //TODO: add spec
 */

public class LocationChangingNumberedOval extends  homework1.LocationChangingOval{

    private static int counter = 0;
    private Integer idNumber;

    LocationChangingNumberedOval(Point location, Color color, Dimension dimension){
        super(location, color, dimension);
        this.counter++;
        this.idNumber = this.counter;
        checkRep();

    }

    /**
     * @requires None
     * @modifies Nothing
     * @effects Throws AssertionError if one of the conditions required in the Rep. Invariant is violated
     */

    private void checkRep() {
        assert(this.idNumber >= 1);
    }

    //TODO: spec
    @Override
    public  void draw(Graphics g){
        checkRep();
        super.draw(g);
        Rectangle r = this.getBounds();
        int xCenter = (int)((r.getX() + r.getWidth())/2);
        int yCenter = (int)((r.getY() + r.getHeight())/2);
        g.drawString(idNumber.toString(), xCenter, yCenter);
    }

    /**
     * @effects Creates and returns a copy of this.
     */
    //TODO: implement clone
    public LocationChangingNumberedOval clone() {
        checkRep();
        LocationChangingNumberedOval oval;
        oval = (LocationChangingNumberedOval)super.clone();
        oval.idNumber = this.idNumber;
        checkRep();
        return oval;
    }
}
