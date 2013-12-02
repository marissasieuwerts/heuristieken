//Settings used for making the java files: verdana 8pt font and tabsize=4

public class TegelZetter {

	private TegelVerzameling tegelVerzameling;
	private Speelveld speelveld;
	private TegelZetterFrame frame;
	private int aantalStappen, teller;
	private int VELD_BREEDTE, VELD_HOOGTE, VERTRAGING;
	private int[] ptr;
	private int bottomX;

	TegelZetter() {
		ptr = new int[2];
		ptr[0] = 0;
		ptr[1] = 0;
	}

	private int[] pointer() {
		// Deze methode geeft de coordinaten waar het beste de volgende tegel
		// geplaatst kan worden


		return ptr;
	}

	public void updatePointer(Tegel tegel) {
		if (ptr[1] == 0) {
			bottomX += tegel.breedte();
		}
		if (ptr[1] + tegel.hoogte() >= Speelveld.hoogte) {
			ptr[0] = bottomX;
			ptr[1] = 0;
		} else {
			ptr[1] += tegel.hoogte();
		}

		System.out.println("Pointer is now: " + ptr[0] + ", " + ptr[1]);
	}

	private boolean maakTegelOplossing(Speelveld veld, TegelVerzameling tegels) {

		// Deze methode gaat recursief een oplossing zoeken.
		// Stop hier je algoritme!
		
		// Roep na het plaatsen, of verwijderen van een tegel frame.repaint(); aan zodat het scherm
		// de wijzigingen goed weergeeft.

		Tegel tegel = tegels.pop(0);
        veld.plaats(tegel, 0, 0);
        updatePointer(tegel);
        tegel = tegels.pop(0);
        veld.plaats(tegel, ptr[0],ptr[1]);
		return true;
	}

	// initialiseert het speelveld en de grafische output, called by start()
	public void init(int veld_breedte, int veld_hoogte, int vertraging,
			int SCALE) {
		this.speelveld = new Speelveld(veld_breedte, veld_hoogte); // maakt een
																	// speelveld
																	// aan die
																	// een
																	// extentie
																	// is van de
																	// visuele
																	// class
																	// canvas!
		this.frame = new TegelZetterFrame(this.speelveld, SCALE);
		this.aantalStappen = this.teller = 0;
	}

	// ---------------

	// Deze methode maakt de verzameling aan die hoort bij opdracht 1
	private TegelVerzameling genereerTegelVerzamelingOpdracht1() {
		TegelVerzameling verzameling = new TegelVerzameling();

		verzameling.push(new Tegel(2)); // tegel: 2x2

		for (int i = 0; i < 7; i++)
			verzameling.push(new Tegel(3)); // 7 tegels van 3x3

		verzameling.push(new Tegel(5)); // tegel: 5x5
		verzameling.push(new Tegel(5));
		verzameling.push(new Tegel(5));
		verzameling.push(new Tegel(7));
		verzameling.push(new Tegel(7));
		verzameling.push(new Tegel(7));
		return verzameling;
	}

	// Deze methode maakt de verzameling aan die hoort bij opdracht 2
	private TegelVerzameling genereerTegelVerzamelingOpdracht2() {
		TegelVerzameling verzameling = new TegelVerzameling();

		for (int i = 1; i < 11; i++)
			verzameling.push(new Tegel(i)); // tegels 1x1 .. 10x10

		verzameling.push(new Tegel(8)); // tegel 8x8
		verzameling.push(new Tegel(9, 8));
		verzameling.push(new Tegel(7, 6)); // oorspronkelijk 6x7
		verzameling.push(new Tegel(4, 10));
		verzameling.push(new Tegel(3, 6));

		return verzameling;
	}

	// Deze methode maakt de verzameling aan die hoort bij opdracht 3
	private TegelVerzameling genereerTegelVerzamelingOpdracht3() {
		TegelVerzameling verzameling = new TegelVerzameling();

		for (int i = 1; i < 21; i++) {
			verzameling.push(new Tegel(i, i + 1));
		}

		return verzameling;
	}

	// ---------------

	// initialiseert het veld en de tegelset voor opgave1
	public void initOpgave1() {

		VELD_BREEDTE = 17;
		VELD_HOOGTE = 17;
		VERTRAGING = 100; // in milliseconden, vertraagt het programma zodat je
							// alle stappen kan zien, zet op 0 voor geen
							// vertraging!
		int SCALE = 20; // de schaal van de vakjes
		this.tegelVerzameling = genereerTegelVerzamelingOpdracht1();
		init(VELD_BREEDTE, VELD_HOOGTE, VERTRAGING, SCALE);

	}

	// initialiseert het veld en de tegelset voor opgave2
	public void initOpgave2() {

		VELD_BREEDTE = 23;
		VELD_HOOGTE = 27;
		VERTRAGING = 0;
		int SCALE = 20;
		this.tegelVerzameling = genereerTegelVerzamelingOpdracht2();
		init(VELD_BREEDTE, VELD_HOOGTE, VERTRAGING, SCALE);

	}

	// initialiseert het veld en de tegelset voor opgave3
	public void initOpgave3() {

		VELD_BREEDTE = 55;
		VELD_HOOGTE = 56;
		VERTRAGING = 0;
		int SCALE = 10;
		this.tegelVerzameling = genereerTegelVerzamelingOpdracht3();
		init(VELD_BREEDTE, VELD_HOOGTE, VERTRAGING, SCALE);

	}

	// ---------------

	public void start() {

		/*
		 * Settings Zet de vertraging hierboven naar een waarde > 0 en je ziet
		 * de oplossing verschijnen. Er worden dan ook allemaal output
		 * printregels geactiveerd die handig zijn voor het debuggen. Mocht je
		 * het programma sneller willen laten werken voor opgave3, dan kun je
		 * alle grafische regels uit het programma halen. Je mist dan wel de
		 * uiteindelijke grafische oplossing.
		 */

		initOpgave1();
		// initOpgave2();
		// initOpgave3();

		System.out.printf("Aantal tegels beschikbaar in verzameling: %d \n",
				this.tegelVerzameling.size());
		System.out.printf("Aantal soorten tegels in verzameling:     %d \n",
				this.tegelVerzameling.aantalVerschillendeTegels());

		System.out.printf("\n Bezig met zoeken naar oplossing...\n");
		this.maakTegelOplossing(this.speelveld, new TegelVerzameling(
				this.tegelVerzameling)); // dit zoekt de oplossing
	}

	public static void main(String[] args) {
		new TegelZetter().start();
	}

}
