package mcb.persistenz;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jgoodies.binding.beans.Model;

public class Adresse extends Model {

	private static final long serialVersionUID = -6192828255610806898L;

	public static final String NAME = "name";
	public static final String VORNAME = "vorname";
	public static final String ORT = "ort";
	public static final String STRASSE = "strasse";
	public static final String PLZ = "plz";
	public static final String LAND = "land";
	public static final String GEBURTSTAG = "geburtstagString";
	public static final String GESPANN = "gespann";
	public static final String SOLO = "solo";
	public static final String EMAIL = "email";
	public static final String FEHLERGRUND = "fehlergrund";

	public static final String VERGANGENE_TREFFEN = "vergangeneTreffen";

	public static void writeHeaderOn(Writer writer) throws IOException {
		writer.write("id");
		writer.write(";");
		writer.write(Adresse.VORNAME);
		writer.write(";");
		writer.write(Adresse.NAME);
		writer.write(";");
		writer.write(Adresse.EMAIL);
		writer.write(";");
		writer.write(Adresse.STRASSE);
		writer.write(";");
		writer.write(Adresse.LAND);
		writer.write(";");
		writer.write(Adresse.PLZ);
		writer.write(";");
		writer.write(Adresse.ORT);
		writer.write(";");
		writer.write(Adresse.GESPANN);
		writer.write(";");
		writer.write(Adresse.SOLO);
		writer.write(";");
		writer.write("\n");
	}

	private Long id;
	private String name;
	private String vorname;
	private String strasse;
	private String ort;
	private String plz;
	private String land = "D";
	private Date geburtstag;
	private boolean gespann;
	private boolean solo;
	private String email;
	private String fehlergrund;
	private List<Besuch> besuchteTreffen = new ArrayList<Besuch>();

	private static Map<String, String> landNameMap;

	static {
		Adresse.landNameMap = new HashMap<String, String>();
		Adresse.landNameMap.put("D", "");
		Adresse.landNameMap.put("F", "Frankreich");
		Adresse.landNameMap.put("NL", "Niederlande");
		Adresse.landNameMap.put("A", "÷sterreich");
		Adresse.landNameMap.put("B", "Belgien");
		Adresse.landNameMap.put("CH", "Schweiz");
		Adresse.landNameMap.put("CZ", "Tschechien");
		Adresse.landNameMap.put("DK", "D‰nemark");
		Adresse.landNameMap.put("E", "Spanien");
		Adresse.landNameMap.put("FIN", "Finnland");
		Adresse.landNameMap.put("FL", "Liechtenstein");
		Adresse.landNameMap.put("GB", "Groﬂbritannien");
		Adresse.landNameMap.put("GR", "Griechenland");
		Adresse.landNameMap.put("H", "Ungarn");
		Adresse.landNameMap.put("HR", "Kroatien");
		Adresse.landNameMap.put("I", "Italien");
		Adresse.landNameMap.put("IRL", "Irland");
		Adresse.landNameMap.put("L", "Luxemburg");
		Adresse.landNameMap.put("N", "Norwegen");
		Adresse.landNameMap.put("P", "Portugal");
		Adresse.landNameMap.put("PL", "Polen");
		Adresse.landNameMap.put("S", "Schweden");
	}

	public Adresse() {
		super();
	}

	public void addAktuellesTreffen() {
		this.addTreffen(ApplicationData.getAktuellesTreffen());
	}

	public void addTreffen(Treffen treffen) {
		if (treffen == null) {
			return;
		}
		for (Besuch besuch : this.besuchteTreffen) {
			if (besuch.getTreffen().equals(treffen)) {
				return;
			}
		}
		if (!this.besuchteTreffen.contains(treffen)) {
			this.besuchteTreffen.add(new Besuch(this, treffen));
		}
	}

	private String convertMinusToEmpty(String string) {
		return "-".equals(string) ? "" : string;
	}

	public Besuch getAktuellesTreffen() {
		for (Besuch besuch : this.getBesuchteTreffen()) {
			if (besuch.getTreffen().isAktuell()) {
				return besuch;
			}
		}
		return null;
	}

	public List<Besuch> getBesuchteTreffen() {
		return this.besuchteTreffen;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFehlergrund() {
		return this.fehlergrund;
	}

	private String getFullnameFor(String landText) {
		return Adresse.landNameMap.get(landText).toUpperCase();
	}

	public Date getGeburtstag() {
		return this.geburtstag;
	}

	public String getGeburtstagString() {
		if (this.geburtstag != null) {
			return ApplicationData.DATE_FORMAT.format(this.geburtstag);
		}
		return "";
	}

	public Long getId() {
		return this.id;
	}

	public String getLand() {
		return this.land;
	}

	public String getName() {
		return this.name;
	}

	public String getOrt() {
		return this.ort;
	}

	public String getPlz() {
		return this.plz;
	}

	public String getStrasse() {
		return this.strasse;
	}

	public List<Besuch> getVergangeneTreffen() {
		List<Besuch> result = new ArrayList<Besuch>();
		for (Besuch besuch : this.getBesuchteTreffen()) {
			if (!besuch.getTreffen().isAktuell()) {
				result.add(besuch);
			}
		}
		Collections.sort(result);
		return result;
	}

	public String getVorname() {
		return this.vorname;
	}

	public boolean hatGueltigeEmail() {
		return this.email != null && this.email.trim().length() > 0 && !this.isEmailfehler();
	}

	public boolean isEmailfehler() {
		return this.getFehlergrund() != null && !this.getFehlergrund().equals("");
	}

	public boolean isGespann() {
		return this.gespann;
	}

	public boolean isSolo() {
		return this.solo;
	}

	public void parse(String line) {
		try {
			System.out.println(line);
			String[] tokens = line.split(";");
			System.out.println(tokens.length);
			String idString = tokens[0];
			if (idString.length() > 0) {
				this.id = Long.valueOf(idString);
			}
			this.setVorname(this.convertMinusToEmpty(tokens[1]));
			this.setName(this.convertMinusToEmpty(tokens[2]));
			this.setEmail(this.convertMinusToEmpty(tokens[3]));
			this.setStrasse(this.convertMinusToEmpty(tokens[4]));
			String land2 = tokens[5];
			this.setLand(land2.equals("-") ? "D" : land2);
			this.setPlz(this.convertMinusToEmpty(tokens[6]));
			this.setOrt(this.convertMinusToEmpty(tokens[7]));
			this.setGespann(tokens[8].equalsIgnoreCase("x"));
			this.setSolo(tokens[9].equalsIgnoreCase("x"));
			DateFormat format = DateFormat.getDateInstance();
			String tokenFuenf = tokens[10];
			if (!tokenFuenf.equals("-")) {
				try {
					this.setGeburtstag(format.parse(tokenFuenf));
				} catch (Exception e) {
					// dann halt nicht
				}
			}
			// setEmailfehler(tokens[11].equalsIgnoreCase("x"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeAktuellesTreffen() {
		this.besuchteTreffen.remove(this.getAktuellesTreffen());
	}

	public void setEmail(String email) {
		String oldValue = this.getEmail();
		this.email = email.trim();
		this.firePropertyChange(Adresse.EMAIL, oldValue, this.email);
	}

	public void setFehlergrund(String fehlergrund) {
		this.fehlergrund = fehlergrund;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	public void setGeburtstagString(String geburtstag) {
		try {
			String oldValue = this.getGeburtstagString();
			this.geburtstag = ApplicationData.DATE_FORMAT.parse(geburtstag);
			this.firePropertyChange(Adresse.GEBURTSTAG, oldValue, this.getGeburtstagString());
		} catch (ParseException e) {
			this.firePropertyChange(Adresse.GEBURTSTAG, null, this.getGeburtstagString());
		}
	}

	public void setGespann(boolean gespann) {
		boolean oldValue = this.isGespann();
		this.gespann = gespann;
		this.firePropertyChange(Adresse.GESPANN, oldValue, this.gespann);
	}

	public void setLand(String land) {
		String oldValue = this.getLand();
		this.land = land;
		this.firePropertyChange(Adresse.LAND, oldValue, this.land);
	}

	public void setName(String name) {
		String oldValue = this.getName();
		this.name = name;
		this.firePropertyChange(Adresse.NAME, oldValue, this.name);
	}

	public void setOrt(String ort) {
		String oldValue = this.getOrt();
		this.ort = ort;
		this.firePropertyChange(Adresse.ORT, oldValue, this.ort);
	}

	public void setPlz(String plz) {
		String oldValue = this.getPlz();
		this.plz = plz;
		this.firePropertyChange(Adresse.PLZ, oldValue, this.plz);
	}

	public void setSolo(boolean solo) {
		boolean oldValue = this.isSolo();
		this.solo = solo;
		this.firePropertyChange(Adresse.SOLO, oldValue, this.solo);
	}

	public void setStrasse(String strasse) {
		String oldValue = this.getStrasse();
		this.strasse = strasse;
		this.firePropertyChange(Adresse.STRASSE, oldValue, this.strasse);
	}

	public void setVorname(String vorname) {
		String oldValue = this.getVorname();
		this.vorname = vorname;
		this.firePropertyChange(Adresse.VORNAME, oldValue, this.vorname);
	}

	public boolean sollEinladungErhalten() {
		Treffen neuestesTreffen = ApplicationData.getNeuestesTreffen();
		if (neuestesTreffen.isGespann() && !this.isGespann()) {
			return false;
		}
		for (Besuch besuch : this.getBesuchteTreffen()) {
			if (neuestesTreffen.getJahr() - besuch.getTreffen().getJahr() < 5) {
				return true;
			}
		}
		return false;
	}

	private String toEmptyString(String string) {
		return string == null ? "" : string;
	}

	@Override
	public String toString() {
		return (this.vorname != null ? this.vorname : "") + " " + (this.name != null ? this.name : "");
	}

	public void writeOn(Writer writer, boolean alle) throws IOException {
		writer.write(this.getId().toString());
		writer.write(";");
		writer.write(this.toEmptyString(this.getVorname()));
		writer.write(";");
		writer.write(this.toEmptyString(this.getName()));
		writer.write(";");
		writer.write(this.toEmptyString(this.getEmail()));
		writer.write(";");
		writer.write(this.toEmptyString(this.getStrasse()));
		writer.write(";");
		String landText = this.toEmptyString(this.getLand());
		if (!alle) {
			landText = this.getFullnameFor(landText);
		}
		writer.write(landText);
		writer.write(";");
		writer.write(this.toEmptyString(this.getPlz()));
		writer.write(";");
		String ortText = this.toEmptyString(this.getOrt());
		if (!alle) {
			ortText = ortText.toUpperCase();
		}
		writer.write(ortText);
		writer.write(";");
		writer.write(this.isGespann() ? "x" : "");
		writer.write(";");
		writer.write(this.isSolo() ? "x" : "");
		writer.write(";");
		writer.write("\n");
	}
}
