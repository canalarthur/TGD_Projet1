package fr.projectiles.subtypes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.projectiles.Projectile;
import fr.util.Rectangle;

public class SinShots extends Projectile implements Rectangle {
// Ce projectile avance selon l'angle donne
// en oscillant (avec une periode de p).
	
	private int amplitude;// Amplitude du sinus. (1 marche bien)
	private int period;// Periode du sinus. (16 marche bien)
	private double altX;// X alternatif (dans le repere tourne de angle)
	private double altY;// Y alternatif
	private double distance;// Distance par rapport au point de tir.
	private double speed;	
	
	public SinShots(double x, double y, double speed, int angle, int period, int amplitude, boolean allied){
		super(x, y, speed, angle, allied);
		altX = 0;
		altY = 0;
		distance = 0;
		this.period = period;
		this.amplitude = amplitude;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,(float)x,(float)y);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		speed = Math.sqrt(Math.pow(speedY, 2)+Math.pow(speedX, 2));
		distance += speed*delta;
		//En dessous, c'est les projections du sinus du repere tourne de l'angle voulu dans le repere d'origine.
		//Je sais que c'est moche, mais ca marche, et c'est mathematiquement correct, en plus. (les deux points etant probablement lies)
		speedX = speed*(Math.cos((angle-90)*2*Math.PI/360.0)-amplitude*Math.sin(distance*2*Math.PI/period)*Math.sin((angle-90)*2*Math.PI/360.0));
		speedY = speed*(Math.sin((angle-90)*2*Math.PI/360.0)-amplitude*Math.sin(distance*2*Math.PI/period)*Math.cos((angle-90)*2*Math.PI/360.0));
		moveY(delta);
		moveX(delta);
		sprite.rotate((float) (angle + 2*Math.PI*distance/period));
	}
}
