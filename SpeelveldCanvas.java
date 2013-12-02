import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class SpeelveldCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544621875608225004L;
	private final static int MARGINLEFT = 50, MARGINTOP = 20;
	private Speelveld speelveld;
	private int scale;
	
	public SpeelveldCanvas(Speelveld speelveld, int scale){
		this.speelveld = speelveld;
		this.scale = scale;
	}
	
	public void paint(Graphics g){
		Image bufferImage = createImage(this.getWidth(),this.getHeight());
		Graphics g2 = bufferImage.getGraphics();
		
		setBackground(Color.white);
	    g2.setColor(Color.DARK_GRAY);
	    g2.drawRect(SpeelveldCanvas.MARGINLEFT-1, SpeelveldCanvas.MARGINTOP-1, (Speelveld.breedte*scale)+2, (scale*Speelveld.hoogte)+2);
	    g2.drawRect(SpeelveldCanvas.MARGINLEFT-2, SpeelveldCanvas.MARGINTOP-2, (Speelveld.breedte*scale)+4, (scale*Speelveld.hoogte)+4);
	    String opp = "Oppervlak: "+Speelveld.breedte+" x " +Speelveld.hoogte;
	    g2.drawString(opp,SpeelveldCanvas.MARGINLEFT, SpeelveldCanvas.MARGINTOP-5);
	    this.tekenVeld(g2);
	    
	    g.drawImage(bufferImage, 0, 0, this);
	}
	
	// Deze methode tekent het hele veld
	private void tekenVeld(Graphics g){
		for (int i=0; i < Speelveld.breedte; i++){
			for (int j=0; j < Speelveld.hoogte; j++){
				if (speelveld.speelveld[i][j] != null){
					Color kleur = speelveld.speelveld[i][j].geefKleur();
					g.setColor(kleur);
					g.fillRect((i*scale)+SpeelveldCanvas.MARGINLEFT,(j*scale)+SpeelveldCanvas.MARGINTOP, scale, scale);
					g.setColor(kleur.darker());
					g.drawRect((i*scale)+SpeelveldCanvas.MARGINLEFT,(j*scale)+SpeelveldCanvas.MARGINTOP, scale, scale);
				}else{
					g.setColor(Color.LIGHT_GRAY);
					g.drawRect((i*scale)+SpeelveldCanvas.MARGINLEFT,(j*scale)+SpeelveldCanvas.MARGINTOP, scale, scale);
				}
			}
		}
	}
}
