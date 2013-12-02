import java.awt.Color;
import java.util.Random;

public class Tegel {
	private int breedte;
	private int hoogte;
	private Color kleur;

	public Tegel(int breedte, int hoogte){
		this.breedte = breedte;
		this.hoogte = hoogte;
		this.kleur = new Color(new Random().nextInt(0x1000000));
	}

	public Tegel(int lengte){
		this(lengte, lengte);
	}

	public int hoogte(){
		return this.hoogte;
	}

	public int breedte(){
		return this.breedte;
	}

	public int size(){
		return this.breedte*this.hoogte;
	}

	public Color geefKleur(){
		return this.kleur;
	}
}
