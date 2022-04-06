package game;

import java.awt.Image;
/**
 * 우주선의 동작을 다룰 클래스
 * @author kimilguk
 *
 */
public class StarShipSprite extends Sprite {
	private GalagaGame game;

	public StarShipSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = 0;
		dy = 0;
	}

	@Override
	public void move() {
		if ((dx < 0) && (x < 10)) {
			return;
		}
		if ((dx > 0) && (x > 800)) {
			return;
		}
		super.move();
	}

	@Override
	public void handleCollision(Sprite other) {  //충돌이 발생하면 호출
		if (other instanceof AlienSprite) { //에얼리언이 총알에 맞으면
			game.endGame();			//게임오버
		}
	}
	
}