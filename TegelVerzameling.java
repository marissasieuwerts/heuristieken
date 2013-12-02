import java.util.ArrayList;

public class TegelVerzameling implements Cloneable {
	private ArrayList< ArrayList<Tegel> > tegelLijst;		//google for "java arraylist"
	private int aantalElementen;

	public TegelVerzameling(){
		this.aantalElementen=0;
		this.tegelLijst = new ArrayList< ArrayList<Tegel> >();
	}

	public TegelVerzameling(TegelVerzameling src){
		this.aantalElementen = src.aantalElementen;
		this.tegelLijst = new ArrayList<ArrayList<Tegel>>();
		for (int i=0;i < src.aantalVerschillendeTegels();i++)
			this.tegelLijst.add((ArrayList<Tegel>) src.tegelLijst.get(i).clone());
	}

	public int size(){
		return this.aantalElementen;
	}

	public int aantalVerschillendeTegels(){
		return this.tegelLijst.size();
	}

	private ArrayList<Tegel> nieuweTegelLijst(Tegel tegel){
		ArrayList <Tegel> temp =  new ArrayList<Tegel>();
		temp.add(tegel);
		this.aantalElementen++;
		return temp;
	}

	public void push(Tegel tegel){
		if (this.tegelLijst.size() == 0) { 	// indien de lijst nog leeg is:
			this.tegelLijst.add(this.nieuweTegelLijst(tegel));
		}else{ // anders zoeken naar juiste plaats
			int beginAantal = this.aantalElementen;
			for (int i=0; i < tegelLijst.size();i++){
				if (tegel.size() > tegelLijst.get(i).get(0).size()){ // indien het oppervlakte groter is, hier toevoegen
					this.tegelLijst.add(i, this.nieuweTegelLijst(tegel));
					break;
				}else if (tegel.breedte() == tegelLijst.get(i).get(0).breedte() && tegel.hoogte() == tegelLijst.get(i).get(0).hoogte() ){ // Indien gelijke breedte en hoogte, (dubbele tegels!)
					this.tegelLijst.get(i).add(tegel);
					this.aantalElementen++;
					break;
				}
			}
			if (beginAantal == this.aantalElementen){ // Indien alles kleiner is
				this.tegelLijst.add(this.nieuweTegelLijst(tegel));
			}
		}
	}

	public Tegel pop(int index){
		Tegel temp = this.tegelLijst.get(index).remove(0);
		this.aantalElementen--;
		if (this.tegelLijst.get(index).size() == 0) this.tegelLijst.remove(index);
		return temp;
	}
}
