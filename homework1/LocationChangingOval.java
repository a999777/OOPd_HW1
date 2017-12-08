package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChangingOval is an oval shape that can change its location using its step() method.
 * As an oval, it has a width and height that determines its shape.
 * A LocationChangingOval also has a velocity property that determines the speed of location changing.
 * Thus, a typical LocationChangingOval consists of the following set of
 * properties: {location, color, size, velocity}
 */
public class LocationChangingOval extends LocationChangingShape{

    //Abs. Function:
    //  Represents a location changing oval that has a color this.color and has a bounding rectangle whose top left
    //  corner is at this.location. The oval has a width and height which are stored in this.size.
    //  In addition, the oval has a velocity both in the x and y axes (represented by this.velocityX and this.velocityY)

    //Rep. Invariant:
    //  this.color mustn't be null
    //  this.location has to be a point with non-negative integer values
    //  this.velocityX and this.velocityY should be integers between 5 and -5 inclusive and different from zero
    //  this.size.width and this.size.height should be non negative integers.

    private Dimension size;

    /**
     * @effects Initializes an oval with bounding rectangle at location, color color, width dimension.width and height
     *          dimension.height.
     */
    LocationChangingOval(Point location, Color color, Dimension dimension) {
        super(location, color);
        //this.size = (Dimension)dimension.clone(); FIXME no more clones
        this.size = new Dimension(dimension);
        checkRep();
    }


    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified dimension.
     *          If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *           supported by this).
     */
    @Override
    public  void setSize(Dimension dimension) throws homework1.ImpossibleSizeException{
        checkRep();
        if(dimension.getHeight() <= 0 || dimension.getWidth() <= 0) {
            throw new ImpossibleSizeException(this.size);           //Fixme make sure default dimension is ok
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
    protected void checkRep() {

        assert(this.size.width > 0):"Illegal width for an oval!";
        assert(this.size.height > 0):"Illegal height for an oval!";
    }


    /**
     * @effects Creates and returns a copy of this.
     */
    public LocationChangingOval clone() {
        checkRep();
        LocationChangingOval oval;
        oval = (LocationChangingOval)super.clone();
        oval.size = new Dimension(this.size);
        checkRep();
        return oval;
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public  void draw(Graphics g){
        checkRep();

        //Get parameters ready to be drawn
        g.setColor(this.getColor());
        int x, y, width, height;
        x = (int)getLocation().getX();
        y = (int)getLocation().getY();
        width = (int)size.getWidth();
        height = (int)size.getHeight();
        g.setColor(this.randomizeTextColor(g));
        //Use library function do draw the oval to g
        g.fillOval(x, y, width, height);
        checkRep();
    }

    /**
     * @requires The Graphics is not null
     * @modifies Nothing
     * @effects Returns the color we want to set our text to
     */
    private Color randomizeTextColor(Graphics g) {
        //Setting the default text color to black
        Color textColor = new Color(0, 0, 0);
        //Making sure the text color is different from the oval's color
        while(textColor.equals(this.getColor())) {
            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            textColor = new Color(r, g, b);
        }
        return textColor;
    }

}

