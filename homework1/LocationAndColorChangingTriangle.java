package homework1;

import java.awt.*;

public class LocationAndColorChangingTriangle extends  homework1.ColorAndLocationChangingShape{

    // TODO (BOM): Write Abstraction Function

    // TODO (BOM): Write Representation Invariant
    private Dimension size;

    /**
     * //TODO: add spec
     */
    LocationAndColorChangingTriangle(Point location, Color color, Dimension dimension) {
        super(location, color);
        this.size = (Dimension)dimension.clone();
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
    public LocationAndColorChangingTriangle clone() {
        checkRep();
        LocationAndColorChangingTriangle triangle;
        triangle = (LocationAndColorChangingTriangle)super.clone();
        triangle.size = new Dimension(this.size);
        checkRep();
        return triangle;
    }
    //TODO: spec
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
