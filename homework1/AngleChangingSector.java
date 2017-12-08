package homework1;

import java.awt.*;

/**
 * A AngleChangingSector is an oval that can change its sector angle using its step()
 * method. Thus, a typical AngleChangingSector consists of the following set of
 * properties: {location, color, size, initialDeg, currentDeg}
 */
public class AngleChangingSector extends Shape implements Animatable {

    //Abs. Function:
    // Represents an angle changing oval that has a color this.color and has a bounding rectangle whose
    // top left corner is at this.location.


    //Rep. Invariant:
    //  this.color mustn't be null
    //  this.location has to be a point with non-negative integer values
    //  this.size must have positive height and width
    private Dimension size;
    private int initialDeg, currentDeg;

    /**
     * @effects Initializes this with a a given location, color, dimension and initial degree.
     */
    AngleChangingSector(Point location, Color color, Dimension dimension, int initialDeg, int currentDeg) {
        super(location, color);
        this.size = (Dimension)dimension.clone();
        this.initialDeg = initialDeg;
        this.currentDeg = currentDeg;
        checkRep();
    }

    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension.
     *          If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *           supported by this).
     */
    @Override
    public  void setSize(Dimension dimension) throws homework1.ImpossibleSizeException{
        checkRep();
        if(dimension.getHeight() <= 0 || dimension.getWidth() <= 0) {
            throw homework1.ImpossibleSizeException(this.size);//Fixme make sure default dimension is ok
        }
        this.size = new Dimension(dimension);
        checkRep();
    }
    /**
     * @return the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds() {
        checkRep();
        return new Rectangle(this.getLocation().x, this.getLocation().y, size.width, size.height);
    }

    /**
     * @requires None
     * @modifies Nothing
     * @effects Throws AssertionError if one of the conditions required in the Rep. Invariant is violated
     */

    private void checkRep() {
        assert(this.size.width > 0 && this.size.height > 0);
    }

    /**
     * @effects Creates and returns a copy of this.
     */
    public AngleChangingSector clone() {
        checkRep();
        AngleChangingSector sector;
        sector = (AngleChangingSector)super.clone();
        sector.size = new Dimension(this.size);
        sector.currentDeg = this.currentDeg;
        sector.initialDeg = this.initialDeg;
        checkRep();
        return sector;
    }

    /**
     * @modifies this
     * @effects Updates the state of this to the appropriate value for the
     *          next animation step. The argument bound indicates the area
     *          within which this is allowed to move.
     */
    public void step(Rectangle bound){
        checkRep();
        this.currentDeg = (this.currentDeg + 1)%360;
        checkRep();
    }

    /**
     * @requires None
     * @modifies g
     * @effects Draws this onto g - the color is this.color, ovals initial degree and current degree
     *
     */
    @Override
    public  void draw(Graphics g){
        checkRep();
        g.setColor(this.getColor());
        int x, y, width, height;
        x = (int)getLocation().getX();
        y = (int)getLocation().getY();
        width = (int)size.getWidth();
        height = (int)size.getHeight();
        g.fillArc(x, y, width, height, this.initialDeg, this.currentDeg);
        checkRep();
    }
}
