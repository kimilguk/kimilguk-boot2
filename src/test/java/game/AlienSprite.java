package game;

import java.awt.Image;

/**
 * 에얼리언 객체의 동작을 다룰 클래스
 * @author kimilguk
 *
 */
public class AlienSprite extends Sprite { //에얼리언 객체의 동작을 다룰 클래스
	private GalagaGame game;
	private int img_no;
	
	public AlienSprite(GalagaGame game, Image image, int x, int y) { 
		super(image, x, y);
		this.game = game;
		this.img_no=img_no;
		dx = -3;
	}

	@Override
	public void move() {	
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 700))) { //왼쪽으로 움직이는데 x가 10보다 작거나 오른쪽으로 움직이는데 x가 700보다 커지면
			dx = -dx; 									//움직이는 방향을 반대로 바꿈
			if(img_no==1)								//총을 안쏘는 에얼리언은
				y += 30;								//점점 밑으로 내려오게함
				
			if (y > 600) {								//에얼리언이 화면을 벗어날 때 까지 못 잡으면
				game.endGame();							//게임오버
			}
		}
		super.move();
	}

}