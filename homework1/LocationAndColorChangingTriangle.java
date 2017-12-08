package homework1;

import java.awt.*;

/**
 * A LocationAndColorChangingTriangle is a triangle that can change its location using its step()
 * method. A LocationAndColorChangingTriangle has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationAndColorChangingTriangle consists of the following set of
 * properties: {location, color, size, velocity}
 */

public class LocationAndColorChangingTriangle extends  homework1.ColorAndLocationChangingShape{

    //Abs. Function:
    // Represents a location and color changing triangle that has a color this.color and has a bounding rectangle whose
    // top left corner is at this.location. The triangle also has random (x,y) velocities. The color is changed upon
    // a call to step functions


    //Rep. Invariant:
    //  this.color mustn't be null
    //  this.location has to be a point with non-negative integer values
    //  this.velocityX and this.velocityY should be integers between 5 and -5 inclusive and different from zero
    //  this.size must have positive height and width
    private Dimension size;

    /**
     * @effects Initializes this with a a given location, color and dimension. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          i!=0, i is greater or equal then -5 and smaller or equal to 5
     */
    LocationAndColorChangingTriangle(Point location, Color color, Dimension dimension) {
        super(location, color);
        this.size = this.size = new Dimension(dimension);
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
            throw new ImpossibleSizeException(this.size);//Fixme make sure default dimension is ok
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
     * @requires None
     * @modifies Nothing
     * @effects Creates and returns a copy of this.
     */
    public LocationAndColorChangingTriangle clone() {
        checkRep();
        LocationAndColorChangingTriangle triangle;
        triangle = (LocationAndColorChangingTriangle)super.clone();
        triangle.size = new Dimension(this.size);
        checkRep();
        return triangle;
    }

    /**
     * @requires None
     * @modifies g
     * @effects Draws this onto g - the color is this.color, triangle's right angle is at this.location
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
        int xPoints[] = {x, x+width, x};
        int yPoints[] = {y, y, y+height};
        int numOfPoints = 3;
        g.fillPolygon(xPoints, yPoints, numOfPoints);
        checkRep();
    }

}
