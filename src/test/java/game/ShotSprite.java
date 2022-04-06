package game;

import java.awt.Image;

/**
 * 불꽃 발사를 다루기 위한 클래스
 * @author kimilguk
 *
 */
public class ShotSprite extends Sprite { 
	private GalagaGame game;

	public ShotSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dy = -3;
		
	}

	@Override
	public void move() { //불꽃의 움직임을 처리
		super.move();  
		if (y < -100) { 
			game.removeSprite(this); 
		}
	}

	@Override
	public void handleCollision(Sprite other) { //충돌이 발생하면 호출
		if (other instanceof AlienSprite) { //이 객체와 충돌한 것이 에얼리언객체면
			game.removeSprite(other); 		//에얼리언 객체를 지우고
			game.removeSprite(this);		//불꽃 이미지도 지움
			//game.score+=10;			
		}
	}
}