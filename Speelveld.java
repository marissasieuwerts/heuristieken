
public class Speelveld {
	protected Tegel[][] speelveld;
	protected static int hoogte, breedte;
	private int x,y;

	public Speelveld(int breedte, int hoogte){
		Speelveld.breedte = breedte;
		Speelveld.hoogte = hoogte;
		speelveld = new Tegel[breedte][hoogte];
	}

	public Speelveld(Speelveld src){
		this(Speelveld.breedte, Speelveld.hoogte);
		this.speelveld = src.speelveld.clone();
	}

	/**
	 * Deze methode plaatst de meegeven tegel op de positie x, y
	 * @param tegel
	 * @param x
	 * @param y
	 * @return true als het plaatsen gelukt is
	 * 			false als het niet gelukt is
	 */
	public boolean plaats(Tegel tegel, int x, int y){
		if ((Speelveld.breedte-x >= tegel.breedte()) && (Speelveld.hoogte-y >= tegel.hoogte())){
			this.x = x;
			this.y = y;
			
			for (int i=this.x; i < (this.x + tegel.breedte()); i++){
				for (int j=this.y; j < (this.y + tegel.hoogte()); j++){
					this.speelveld[i][j] = tegel;
				}
			}
						
			return true;
		}
		return false;
	}

	/**
	 * Deze methode maakt de vorige tegelzetting ongedaan
	 * Je kunt alleen de laatstgeplaatste steen ongedaan maken
	 * 
	 * @param src
	 */
	public void undo(Tegel src){
		for (int i=this.x; i < (this.x + src.breedte()); i++){
			for (int j=this.y; j < (this.y + src.hoogte()); j++){
				this.speelveld[i][j] = null;
			}
		}
	}
}
