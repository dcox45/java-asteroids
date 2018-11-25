/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Dylan
 */
public class Asteroids extends Applet implements KeyListener, ActionListener{
    Image offscreen;
    Graphics offg;
    Spacecraft ship;
    Timer timer; 
    ArrayList<Asteroid> asteroidList;
    
    boolean upKey, leftKey, rightKey;
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.setSize(900,600);
        this.addKeyListener(this);
        ship = new Spacecraft();
        timer = new Timer(20, this);
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        asteroidList = new ArrayList();
        for (int i = 0; i < 6; i++){
            asteroidList.add(new Asteroid()); 
        }
    }
    
    public void start(){
        timer.start();
    }
    
    public void stop(){
        timer.stop();
    }
    
    public void actionPerformed(ActionEvent e){
        keyCheck();
        respawnShip();
        ship.updatePosition();
        for (int i = 0; i < asteroidList.size(); i++){
            asteroidList.get(i).updatePosition();
        }
        checkCollision();
        
    }
    
    public void paint(Graphics g) {
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, 900, 600);
        offg.setColor(Color.GREEN);
        if (ship.active) {
            ship.paint(offg);
        }
        for (int i = 0; i < asteroidList.size(); i++){
            asteroidList.get(i).paint(offg);
        }
        g.drawImage(offscreen, 0, 0, this);

        repaint();
        
    }
    
    public void update(Graphics g){
        paint(g);
    }
    
    public boolean collision(VectorSprite thing1, VectorSprite thing2) {
        int x, y; 
        for (int i = 0; i < thing1.drawShape.npoints; i++){
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];
            if (thing2.drawShape.contains(x,y) ) {
                return true;
            }
        }
        
        for (int i = 0; i < thing2.drawShape.npoints; i++){
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];
            if (thing1.drawShape.contains(x,y) ) {
                return true;
            }
        }
        
            return false; 
    }
    
    public void checkCollision(){
        for (int i = 0; i < asteroidList.size(); i++){
            if ( collision(ship, asteroidList.get(i)) ){
                ship.hit();
            }
        }
    }
    
    public boolean isRespawnSafe(){
        int x, y, h; 
        
        for (int i = 0; i < asteroidList.size(); i++){
            x = (int) (asteroidList.get(i).xposition - 450);
            y = (int) (asteroidList.get(i).yposition - 300);
            h = (int) Math.sqrt(x*x + y*y);
            
            if ( h < 100 ){
                return false;
            }

        }
        return true;
    }
    
    public void respawnShip(){
        if ( ship.active == false && ship.counter > 50  && isRespawnSafe() ){
           ship.reset();
            
        }
    }
    
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKey = true; 
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = true; 
        }       
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upKey = true; 
        }
    }
    
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            upKey = false;
        }
    }
    
    public void keyCheck() {
        if(upKey){
            ship.accelerate();
        }
        if(leftKey){
            ship.rotateLeft();
        }
        if(rightKey){
            ship.rotateRight();
        }
        
    }
    
    public void keyTyped(KeyEvent e){
        
    }
    
    
    
    
    
//    public void keyTyped(KeyEvent e)
//        
//    }
    // TODO overwrite start(), stop() and destroy() methods
}
