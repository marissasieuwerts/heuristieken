import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TegelZetterFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -360885512080963508L;
	private SpeelveldCanvas speelveldCanvas;

	public TegelZetterFrame(Speelveld speelveld, int scale){
		speelveldCanvas = new SpeelveldCanvas(speelveld, scale);
	 	// frame instellingen:
		setTitle("Heuristieken 2013 - Tegeltje!");
		setLayout(new BorderLayout());
		setSize(800,800);
		add(this.speelveldCanvas, BorderLayout.CENTER);
		setVisible(true);
				
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		
		this.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					System.exit(0);
				}
			}
			
		});
	}

	@Override
	public void repaint() {
		super.repaint();
		speelveldCanvas.repaint();
		//this.speelveldCanvas.validate();
	}
	
	
}
