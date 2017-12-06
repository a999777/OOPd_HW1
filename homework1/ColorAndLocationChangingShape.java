package homework1;

import java.awt.*;
import java.util.Random;

/**
 * A ColorAndLocationChaningShape is a Shape that can change its location and color using its step()
 * method.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class ColorAndLocationChangingShape extends homework1.LocationChangingShape {

    //Abs. Function:
    // Represents a location changing shape that has a color this.color and has a bounding rectangle whose top left
    // corner is at this.location. The shape also has random (x,y) velocities. The color is changed upon a call to step
    // functions


    //Rep. Invariant:
    //  this.color mustn't be null
    //  this.location has to be a point with non-negative integer values
    //  this.velocityX and this.velocityY should be integers between 5 and -5 inclusive and different from zero


    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    ColorAndLocationChangingShape(Point location, Color color) {
        super(location, color);
        checkRep();
    }

    /**
     * @modifies this
     * @effects Changes the location of this as described in the specification
     *          of LocationChangingShape.step(Rectangle bound) and if the velocity of this needs to be changed
	 *			(as described in LocationChangingShape.step(Rectangle bound)), changes the color of this to a new
     *			random color;
	 *			else, does not change the color of this.
     */
    public void step(Rectangle bound) {
        checkRep();
        int velocityX = this.getVelocityX();
        int velocityY = this.getVelocityY();
        super.step(bound);

        //If we get a different speed after calling the parent class' step(), we also need to randomize a color
        if(this.getVelocityX() != velocityX || this.getVelocityY() != velocityY){
            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color color = new Color(r, g, b);
            this.setColor(color);
        }
        checkRep();
    }

    /**
     * @requires None
     * @modifies Nothing
     * @effects Throws AssertionError if one of the conditions required in the Rep. Invariant is violated
     */
    //TODO: check if need to add something to checkRep
    private void checkRep() {
        assert(this.size.width > 0):"Illegal width! Should be bigger than 0";
        assert(this.size.height > 0):"Illegal height! Should be bigger than 0";
    }

    //FIXME: Consider if to remove at a latter stage, since this clone is identical to the parent's clone
//    /**
//     * @effects Creates and returns a copy of this.
//     */
//    public ColorAndLocationChangingShape clone() {
//        checkRep();
//        ColorAndLocationChangingShape shape;
//        shape = (ColorAndLocationChangingShape)super.clone();
//        checkRep();
//        return shape;
//    }

}
