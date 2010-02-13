package mcb.persistenz;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

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
		writer.write(VORNAME);
		writer.write(";");
		writer.write(NAME);
		writer.write(";");
		writer.write(EMAIL);
		writer.write(";");
		writer.write(STRASSE);
		writer.write(";");
		writer.write(LAND);
		writer.write(";");
		writer.write(PLZ);
		writer.write(";");
		writer.write(ORT);
		writer.write(";");
		writer.write(GESPANN);
		writer.write(";");
		writer.write(SOLO);
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
		landNameMap = new HashMap<String, String>();
		landNameMap.put("D", "");
		landNameMap.put("F", "Frankreich");
		landNameMap.put("NL", "Niederlande");
		landNameMap.put("A", "÷sterreich");
		landNameMap.put("B", "Belgien");
		landNameMap.put("CH", "Schweiz");
		landNameMap.put("CZ", "Tschechien");
		landNameMap.put("DK", "D‰nemark");
		landNameMap.put("E", "Spanien");
		landNameMap.put("FIN", "Finnland");
		landNameMap.put("FL", "Liechtenstein");
		landNameMap.put("GB", "Groﬂbritannien");
		landNameMap.put("GR", "Griechenland");
		landNameMap.put("H", "Ungarn");
		landNameMap.put("HR", "Kroatien");
		landNameMap.put("I", "Italien");
		landNameMap.put("IRL", "Irland");
		landNameMap.put("L", "Luxemburg");
		landNameMap.put("N", "Norwegen");
		landNameMap.put("P", "Portugal");
		landNameMap.put("PL", "Polen");
		landNameMap.put("S", "Schweden");
	}

	public Adresse() {
		super();
	}

	public void addAktuellesTreffen() {
		addTreffen(ApplicationData.getAktuellesTreffen());
	}

	public void addTreffen(Treffen treffen) {
		if (treffen == null) {
			return;
		}
		for (Besuch besuch : besuchteTreffen) {
			if (besuch.getTreffen().equals(treffen)) {
				return;
			}
		}
		if (!besuchteTreffen.contains(treffen)) {
			besuchteTreffen.add(new Besuch(this, treffen));
		}
	}

	private String convertMinusToEmpty(String string) {
		return "-".equals(string) ? "" : string;
	}

	public Besuch getAktuellesTreffen() {
		for (Besuch besuch : getBesuchteTreffen()) {
			if (besuch.getTreffen().isAktuell()) {
				return besuch;
			}
		}
		return null;
	}

	public List<Besuch> getBesuchteTreffen() {
		return besuchteTreffen;
	}

	public String getEmail() {
		return email;
	}

	public String getFehlergrund() {
		return fehlergrund;
	}

	private String getFullnameFor(String landText) {
		return landNameMap.get(landText).toUpperCase();
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public String getGeburtstagString() {
		if (geburtstag != null) {
			return ApplicationData.DATE_FORMAT.format(geburtstag);
		}
		return "";
	}

	public Long getId() {
		return id;
	}

	public String getLand() {
		return land;
	}

	public String getName() {
		return name;
	}

	public String getOrt() {
		return ort;
	}

	public String getPlz() {
		return plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public List<Besuch> getVergangeneTreffen() {
		List<Besuch> result = new ArrayList<Besuch>();
		for (Besuch besuch : getBesuchteTreffen()) {
			if (!besuch.getTreffen().isAktuell()) {
				result.add(besuch);
			}
		}
		Collections.sort(result);
		return result;
	}

	public String getVorname() {
		return vorname;
	}

	public boolean hatGueltigeEmail() {
		return email != null && email.trim().length() > 0 && !isEmailfehler();
	}

	public boolean isEmailfehler() {
		return getFehlergrund() != null && !getFehlergrund().equals("");
	}

	public boolean isGespann() {
		return gespann;
	}

	public boolean isSolo() {
		return solo;
	}

	public void parse(String line) {
		try {
			System.out.println(line);
			String[] tokens = line.split(";");
			System.out.println(tokens.length);
			String idString = tokens[0];
			if (idString.length() > 0) {
				id = Long.valueOf(idString);
			}
			setVorname(convertMinusToEmpty(tokens[1]));
			setName(convertMinusToEmpty(tokens[2]));
			setEmail(convertMinusToEmpty(tokens[3]));
			setStrasse(convertMinusToEmpty(tokens[4]));
			String land2 = tokens[5];
			setLand(land2.equals("-") ? "D" : land2);
			setPlz(convertMinusToEmpty(tokens[6]));
			setOrt(convertMinusToEmpty(tokens[7]));
			setGespann(tokens[8].equalsIgnoreCase("x"));
			setSolo(tokens[9].equalsIgnoreCase("x"));
			DateFormat format = DateFormat.getDateInstance();
			String tokenFuenf = tokens[10];
			if (!tokenFuenf.equals("-")) {
				try {
					setGeburtstag(format.parse(tokenFuenf));
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
		besuchteTreffen.remove(getAktuellesTreffen());
	}

	public void setEmail(String email) {
		String oldValue = getEmail();
		this.email = email.trim();
		firePropertyChange(EMAIL, oldValue, this.email);
	}

	public void setFehlergrund(String fehlergrund) {
		this.fehlergrund = fehlergrund;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	public void setGeburtstagString(String geburtstag) {
		try {
			String oldValue = getGeburtstagString();
			this.geburtstag = ApplicationData.DATE_FORMAT.parse(geburtstag);
			firePropertyChange(GEBURTSTAG, oldValue, getGeburtstagString());
		} catch (ParseException e) {
			firePropertyChange(GEBURTSTAG, null, getGeburtstagString());
		}
	}

	public void setGespann(boolean gespann) {
		boolean oldValue = isGespann();
		this.gespann = gespann;
		firePropertyChange(GESPANN, oldValue, this.gespann);
	}

	public void setLand(String land) {
		String oldValue = getLand();
		this.land = land;
		firePropertyChange(LAND, oldValue, this.land);
	}

	public void setName(String name) {
		String oldValue = getName();
		this.name = name;
		firePropertyChange(NAME, oldValue, this.name);
	}

	public void setOrt(String ort) {
		String oldValue = getOrt();
		this.ort = ort;
		firePropertyChange(ORT, oldValue, this.ort);
	}

	public void setPlz(String plz) {
		String oldValue = getPlz();
		this.plz = plz;
		firePropertyChange(PLZ, oldValue, this.plz);
	}

	public void setSolo(boolean solo) {
		boolean oldValue = isSolo();
		this.solo = solo;
		firePropertyChange(SOLO, oldValue, this.solo);
	}

	public void setStrasse(String strasse) {
		String oldValue = getStrasse();
		this.strasse = strasse;
		firePropertyChange(STRASSE, oldValue, this.strasse);
	}

	public void setVorname(String vorname) {
		String oldValue = getVorname();
		this.vorname = vorname;
		firePropertyChange(VORNAME, oldValue, this.vorname);
	}

	public boolean sollEinladungErhalten() {
		Treffen neuestesTreffen = ApplicationData.getNeuestesTreffen();
		if (neuestesTreffen.isGespann() && !isGespann()) {
			return false;
		}
		for (Besuch besuch : getBesuchteTreffen()) {
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
		return (vorname != null ? vorname : "") + " " + (name != null ? name : "");
	}

	public void writeOn(Writer writer, boolean alle) throws IOException {
		writer.write(getId().toString());
		writer.write(";");
		writer.write(toEmptyString(getVorname()));
		writer.write(";");
		writer.write(toEmptyString(getName()));
		writer.write(";");
		writer.write(toEmptyString(getEmail()));
		writer.write(";");
		writer.write(toEmptyString(getStrasse()));
		writer.write(";");
		String landText = toEmptyString(getLand());
		if (!alle) {
			landText = getFullnameFor(landText);
		}
		writer.write(landText);
		writer.write(";");
		writer.write(toEmptyString(getPlz()));
		writer.write(";");
		String ortText = toEmptyString(getOrt());
		if (!alle) {
			ortText = ortText.toUpperCase();
		}
		writer.write(ortText);
		writer.write(";");
		writer.write(isGespann() ? "x" : "");
		writer.write(";");
		writer.write(isSolo() ? "x" : "");
		writer.write(";");
		writer.write("\n");
	}
}
