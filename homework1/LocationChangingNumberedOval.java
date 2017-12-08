package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChangingNumberedOval is an oval that can change its location using its step()
 * method and also has a distinct id number. It has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingNumberedOval consists of the following set of
 * properties: {location, color, shape, size, velocity, idNumber}
 */
public class LocationChangingNumberedOval extends  homework1.LocationChangingOval{

    //Abs. Function:
    //  Represents a location changing oval that has a color this.color and has a bounding rectangle whose top left
    //  corner is at this.location. This oval is also numbered with a unique Id. The oval has a width and height which
    //  are stored in this.size. In addition, the oval has a velocity both in the x and y axes (represented by
    //  this.velocityX and this.velocityY)

    //Rep. Invariant:
    //  this.color mustn't be null
    //  this.location has to be a point with non-negative integer values
    //  this.velocityX and this.velocityY should be integers between 5 and -5 inclusive and different from zero
    //  this.size.width and this.size.height should be non negative integers.
    //  this.idNumber and this.counter has to be bigger than 0.


    //We save a static counter in order to know the serial number we need to allocate next
    private static int counter = 0;
    private Integer idNumber;

    /**
     * @effects Creates a new LocationChangingNumberedOval whose bounding rectangle top left corner is at location,
     *          whose color is color, whose height is dimension.height and width is dimension.width. The oval also
     *          has a distinct id number.
     */
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
        assert(this.idNumber >= 1):"Illegal Id number! Must be bigger than 1";
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g){
        checkRep();
        super.draw(g);
        Rectangle r = this.getBounds();
        //Calculating the location where the number should be displayed(center of the oval)
        int xCenter = (int)((r.getX() + r.getWidth()/2));
        int yCenter = (int)((r.getY() + r.getHeight()/2));
        //Changing the color so the number will be printed in a different color
        g.setColor(this.randomizeTextColor(g));
        g.drawString(idNumber.toString(), xCenter, yCenter);
        checkRep();
    }


    /**
     * @effects Creates and returns a copy of this.
     */
    public LocationChangingNumberedOval clone() {
        checkRep();
        LocationChangingNumberedOval oval;
        oval = (LocationChangingNumberedOval)super.clone();
        oval.idNumber = this.idNumber;
        checkRep();
        return oval;
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
            float red = random.nextFloat();
            float green = random.nextFloat();
            float blue = random.nextFloat();
            textColor = new Color(red, green, blue);
        }
        return textColor;
    }
}
