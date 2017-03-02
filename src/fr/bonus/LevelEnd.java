package fr.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.util.Collisions;

public class LevelEnd extends Bonus {

	private boolean destructed;
	
	public LevelEnd(double x,double y,double width,double height,Player player)
	{
		this.x =x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.player=player;
		destructed = false;
	}
	
	
	@Override
	public void comportment(Player player){
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(Collisions.intersect(this,player) && !destructed)
		{
			player.loose(game);
			this.destructed=true;
		}
		
	}
	
}