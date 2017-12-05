package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChangingShape is a Shape that can change its location using its step()
 * method. A LocationChangingShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends homework1.Shape implements homework1.Animatable {

    private static final int MIN_VELOCITY = -5;
    private static final int MAX_VELOCITY = 5;
    private static final int NO_VELOCITY = 0;
    private int velocityX;
    private int velocityY;

    //Abs. Function:
    //  Represents a location changing shape that has a color this.color and has a bounding rectangle whose top left
    //  corner is at this.location. The shape also has random (x,y) velocities stored in this.velocityX and
    //  this.velocityY.

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
    LocationChangingShape(Point location, Color color) {
        super(location, color);

        //Initializing a random object and randomly picking velocities for X and Y
        Random random = new Random();
        this.velocityX = random.nextInt(MAX_VELOCITY - MIN_VELOCITY + 1) + MIN_VELOCITY;
        this.velocityY = random.nextInt(MAX_VELOCITY - MIN_VELOCITY + 1) + MIN_VELOCITY;

        //Making sure that x and y velocities are not equal to zero, if they are we keep drawing
        while(this.velocityY == NO_VELOCITY){
            this.velocityY = random.nextInt(MAX_VELOCITY - MIN_VELOCITY + 1) + MIN_VELOCITY;
        }
        while(this.velocityX == NO_VELOCITY){
            this.velocityX = random.nextInt(MAX_VELOCITY - MIN_VELOCITY + 1) + MIN_VELOCITY;
        }
        checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        checkRep();
        return this.velocityX;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        checkRep();
        return this.velocityY;
    }


    /**
     * @requires -5 <= velocityX <= 5 && -5 <= velocityY <= 5 && velocityX != 0 && velocityY != 0
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     *          vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        checkRep();
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        checkRep();
    }


    /**
     * @modifies this
     * @effects Let p = location
     *              v = (vx, vy) = velocity
     *              r = the bounding rectangle of this
     *          If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     *              If adding v to p would move r horizontally outside of bound,
     *                  vx = -vx
     *              If adding v to p would move r vertically outside of bound,
     *                  vy = -vy
     *          }
     *          p = p + v
     */
    public void step(Rectangle bound) {
        checkRep();
        Point p = getLocation();
        //FIXME: r is unused because of our way of calculating. Maybe remove it?
        Rectangle r = getBounds();
        if(p.getX() + this.velocityX > bound.getX() + bound.getWidth() || p.getX() + this.velocityX < bound.getX()){
            this.setVelocity(-this.getVelocityX(), this.getVelocityY());
        }
        if(p.getY() + this.velocityY < bound.getY() || p.getY() + this.velocityY > bound.getY() + bound.getHeight()){
            this.setVelocity(this.getVelocityX(), -this.getVelocityY());
        }
        Point newLocation = (Point)this.getLocation().clone();
        newLocation.setLocation(p.getX() + this.velocityX, p.getY() + this.velocityY);
        this.setLocation(newLocation);
        checkRep();
    }


    /**
     * @requires None
     * @modifies Nothing
     * @effects Throws AssertionError if one of the conditions required in the Rep. Invariant is violated
     */
    private void checkRep() {
        assert(_checkVelocity(this.velocityX)):"The X velocity is illegal!";
        assert(_checkVelocity(this.velocityY)):"The Y velocity is illegal!";
    }


    /**
     * @requires None
     * @modifies Nothing
     * @effects Return true if -5 <= velocity <=5 && velocity != 0, false otherwise
     */
    private boolean _checkVelocity(int velocity){
        return velocity != NO_VELOCITY && velocity >= MIN_VELOCITY && velocity <= MAX_VELOCITY;
    }


    /**
     * @requires None
     * @modifies Nothing
     * @effects Creates and returns a copy of this.
     */
    public LocationChangingShape clone() {
        checkRep();
        LocationChangingShape shape;
        shape = (LocationChangingShape)super.clone();
        shape.setVelocity(this.velocityX, this.velocityY);
        checkRep();
        return shape;
    }
}
