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

public class Asteroid extends VectorSprite{
    
    public Asteroid(){
        shape = new Polygon();
        shape.addPoint(30, 3);
        shape.addPoint(5, 35);
        shape.addPoint(-25,10);
        shape.addPoint(-17,-15);
        shape.addPoint(20,-35);
        drawShape = new Polygon();
        drawShape.addPoint(30, 3);
        drawShape.addPoint(5,35);
        drawShape.addPoint(-25,10);
        drawShape.addPoint(-17,-15);
        drawShape.addPoint(20,-35);
        
        ROTATION = 0.05;
        THRUST = 0.5; 
        active = true; 
        double h, a;
        h= Math.random()*400 +100;
        a = Math.random() * 2*Math.PI;
        xposition = Math.cos(a)*h+450;
        yposition = Math.sin(a)*h+300;
        h= Math.random() + 1;
        a = Math.random()* 2*Math.PI;
        xspeed = Math.cos(a)*h;
        yspeed = Math.sin(a)*h;
    }
    
    public void updatePosition(){
        angle += ROTATION;
        super.updatePosition();
    }
    
}
