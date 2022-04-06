package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 겔라가 게임 메인(전체기능에서 스레드 기능을 제거한, 내 총에 에일리언이 삭제되는 기능만 구현)
 * @author kimilguk
 * 원본 https://github.com/dlgosla/galagaGame
 */
//소스를 입력하고 Ctrl+Shift+O를 누른다.
public class GalagaGame extends JPanel implements KeyListener {

	private boolean running = true;

	private ArrayList sprites = new ArrayList();//화면에 그려질 객체들을 저장할 배열
	private Sprite starship;

	private BufferedImage alienImage;
	private BufferedImage shotImage;
	private BufferedImage shipImage;

	public GalagaGame() {
		JFrame frame = new JFrame("Galaga Game");

		frame.setSize(800, 600);
		frame.add(this);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {//이미지를 파일에서 읽어옴
			shotImage = ImageIO.read(new File("./src/test/java/game/fire.png"));
			shipImage = ImageIO.read(new File("./src/test/java/game/starship.png"));
			alienImage = ImageIO.read(new File("./src/test/java/game/alien.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.requestFocus();
		this.initSprites();
		addKeyListener(this);

	}

	private void initSprites() {
		starship = new StarShipSprite(this, shipImage, 370, 550);//내 우주선 객체를 만듦
		sprites.add(starship);
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 12; x++) {
				Sprite alien = new AlienSprite(this, alienImage,
						100 + (x * 50), (50) + y * 30);
				sprites.add(alien);
			}
		}//에일리언들을 5행 12열로 생성함
	}

	private void startGame() {//게임이 시작되면
		sprites.clear();
		initSprites();
	}

	public void endGame() {//게임을 종료
		// System.exit(0);
	}

	public void removeSprite(Sprite sprite) {//객체를 지워주는 메소드
		sprites.remove(sprite);
	}

	public void fire() {//총알 불꽃객체 생성
		ShotSprite shot = new ShotSprite(this, shotImage, starship.getX() + 10,
				starship.getY() - 30);
		sprites.add(shot);
	}

	@Override
	public void paint(Graphics g) { //이미지를 화면에 그려줌
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		for (int i = 0; i < sprites.size(); i++) {
			Sprite sprite = (Sprite) sprites.get(i);
			sprite.draw(g);
		}
	}

	public void gameLoop() {

		while (running) {
			//i번째에 저장된 객체를 가져와서 sprite로 형변환하고 움직이게 함(아래)
			for (int i = 0; i < sprites.size(); i++) {
				Sprite sprite = (Sprite) sprites.get(i);
				sprite.move();
			}

			for (int p = 0; p < sprites.size(); p++) {
				for (int s = p + 1; s < sprites.size(); s++) {
					//모든 객체 사이를 따져봐서
					Sprite me = (Sprite) sprites.get(p);
					Sprite other = (Sprite) sprites.get(s);
					//어떤 두 객체에서 충돌이 발생했으면(아래)
					if (me.checkCollision(other)) {
						me.handleCollision(other);//내가 에일리언 총알에 충돌할 때 처리한다.
						other.handleCollision(me);//에이리언이 내 총알에 충돌할 때 처리함
					}
				}
			}

			repaint();
			try {
				Thread.sleep(10);//다시 그림
			} catch (Exception e) {
			}
		}
	}
	//키가 눌리면 움직임(아래)
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(-3);//왼쪽으로 이동
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			starship.setDx(+3);//오른쪽으로 이동
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			fire();//총알발사
	}
	//키에서 손을 떼면 움직임을 멈춤(아래)
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(0);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			starship.setDx(0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public static void main(String argv[]) {
		GalagaGame g = new GalagaGame();
		g.gameLoop();
	}
}
