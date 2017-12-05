package homework1;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

    private Point location;
    private Color color;


    //Abs. Function:
    //  Represents a shape that has a color this.color and has a bounding rectangle whose top left corner is at
    //  this.location.

    //Rep. Invariant:
    //  this.color mustn't be null
    //  this.location has to be a point with non-negative integer values

    /**
     * @effects Initializes this with a a given location and color.
     */
    public Shape(Point location, Color color) {
        this.location = (Point)location.clone();
        this.color = color;
        checkRep();
    }

    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        checkRep();
        // Point pointToReturn = new Point(this.location);
        Point pointToReturn = (Point)this.location.clone();//FIXME: is it okay? or should we use new and really
        // create a new point?
        checkRep();
        return pointToReturn;
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     *          returns location after call has completed.
     */
    public void setLocation(Point location) {
        checkRep();
        this.location = (Point)location.clone();
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
    public abstract void setSize(Dimension dimension)
        throws homework1.ImpossibleSizeException;


    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();


    /**
     * @return true if the given point lies inside the bounding rectangle of
     *         this and false otherwise.
     */
    public boolean contains(Point point) {
        checkRep();
        return getBounds().contains(point);
    }


    /**
     * @return color of this.
     */
    public Color getColor() {
        checkRep();
        return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        checkRep();
        this.color = color;
        checkRep();
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
        // TODO (BOM): Implement this method
        checkRep();
        Shape clonedShape = null;
        // trying to perform clone
        try{
            clonedShape = (Shape)super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
        // clone succeded with shallow copy, need to update location
        clonedShape.location = (Point)location.clone();
        checkRep();
        return clonedShape;
    }

    /**
     * @requires None
     * @modifies Nothing
     * @effects Throws AssertionError if one of the conditions required in the Rep. Invariant is violated
     */
    private void checkRep() {
        assert(this.color != null && this.location.getX() >=0 && this.location.getY() >= 0);
    }
}
