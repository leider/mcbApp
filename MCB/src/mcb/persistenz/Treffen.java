package mcb.persistenz;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.jgoodies.binding.beans.Model;

public class Treffen extends Model implements Comparable<Treffen> {

	private static Calendar cal = Calendar.getInstance();

	public static final String NAME = "name";
	public static final String LETZTER_TAG = "letzterTagString";
	public static final String ERSTER_TAG = "ersterTagString";
	public static final String EMAIL_TEXT = "emailText";
	public static final String BESCHREIBUNG = "beschreibung";

	private static final long serialVersionUID = -2962721510670946939L;

	private Long id;
	private String name;
	private Date ersterTag;
	private Date letzterTag;
	private String emailText;
	private String beschreibung;

	public Treffen() {
		super();
		setErsterTagString("01.01.2000");
		setLetzterTagString("01.01.2001");
	}

	public int compareTo(Treffen o) {
		return o.getErsterTag().compareTo(getErsterTag());
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public String getEmailPreviewText() {
		return getEmailPreviewText("Vorname");
	}

	public String getEmailPreviewText(String vorname) {
		return String.format(emailText, getVonBisString(), getBeschreibung(), vorname);
	}

	public String getEmailText() {
		return emailText;
	}

	public Date getErsterTag() {
		return ersterTag;
	}

	public String getErsterTagString() {
		if (ersterTag != null) {
			return ApplicationData.DATE_FORMAT.format(ersterTag);
		}
		return "";
	}

	public Long getId() {
		return id;
	}

	public int getJahr() {
		cal.setTime(ersterTag);
		return cal.get(Calendar.YEAR);
	}

	public Date getLetzterTag() {
		return letzterTag;
	}

	public String getLetzterTagString() {
		if (letzterTag != null) {
			return ApplicationData.DATE_FORMAT.format(letzterTag);
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public String getVonBisString() {
		return getErsterTagString() + " - " + getLetzterTagString();
	}

	public boolean isAktuell() {
		try {
			String heuteString = ApplicationData.DATE_FORMAT.format(new Date());
			Date heute = ApplicationData.DATE_FORMAT.parse(heuteString);
			if (heute.compareTo(getErsterTag()) < 0 || heute.compareTo(getLetzterTag()) > 0) {
				return false;
			}
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isGespann() {
		try {
			String ersterJuliString = "01.07." + getJahr();
			Date ersterJuli = ApplicationData.DATE_FORMAT.parse(ersterJuliString);
			return getErsterTag().compareTo(ersterJuli) < 0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}

	public void setErsterTag(Date ersterTag) {
		this.ersterTag = ersterTag;
	}

	public void setErsterTagString(String erster) {
		try {
			String oldValue = getErsterTagString();
			ersterTag = ApplicationData.DATE_FORMAT.parse(erster);
			firePropertyChange(ERSTER_TAG, oldValue, getErsterTagString());
		} catch (ParseException e) {
			firePropertyChange(ERSTER_TAG, null, getErsterTagString());
		}
	}

	public void setLetzterTag(Date letzterTag) {
		this.letzterTag = letzterTag;
	}

	public void setLetzterTagString(String letzter) {
		try {
			String oldValue = getLetzterTagString();
			letzterTag = ApplicationData.DATE_FORMAT.parse(letzter);
			firePropertyChange(LETZTER_TAG, oldValue, getLetzterTagString());
		} catch (ParseException e) {
			firePropertyChange(LETZTER_TAG, null, getLetzterTagString());
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
