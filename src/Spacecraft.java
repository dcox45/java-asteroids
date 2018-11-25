/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dylan
 */
import java.awt.*;



public class Spacecraft extends VectorSprite{
    
    public Spacecraft(){
        shape = new Polygon();
        shape.addPoint(25, 0);
        shape.addPoint(-10,15);
        shape.addPoint(-10,-15);
        drawShape = new Polygon();
        drawShape.addPoint(20, 0);
        drawShape.addPoint(-10,15);
        drawShape.addPoint(-10,-15);
        xposition = 450;
        yposition = 300;
        ROTATION = 0.15;
        THRUST = 0.3; 
        active = true;
    }
    
    public void accelerate()
    {
        xspeed += Math.cos(angle)*THRUST;
        yspeed += Math.sin(angle)*THRUST;
    }
    
    public void rotateLeft(){
        angle -= ROTATION;
    }
    
    public void rotateRight(){
        angle += ROTATION;
    }
    
    public void hit(){
        active = false;
        counter = 0;
    }
    
    public void reset(){
        xposition = 450;
        yposition= 300;
        xspeed = 0;
        yspeed = 0;
        angle = Math.PI*2;
        active = true; 
    }
}
