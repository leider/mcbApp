package mcb.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import mcb.persistenz.ApplicationData;

public class Treffen extends McbModel implements Comparable<Treffen> {

	private static Calendar cal = Calendar.getInstance();

	public static final String NAME = "name";
	public static final String LETZTER_TAG = "letzterTagString";
	public static final String ERSTER_TAG = "ersterTagString";
	public static final String EMAIL_TEXT = "emailText";
	public static final String BESCHREIBUNG = "beschreibung";
	public static final String EMAIL_PREVIEW_TEXT = "emailPreviewText";

	private static final long serialVersionUID = -2962721510670946939L;

	private String name;
	private Date ersterTag;
	private Date letzterTag;
	private String emailText;
	private String beschreibung;

	public Treffen() {
		super();
		this.setErsterTagString("01.01.2000");
		this.setLetzterTagString("01.01.2001");
	}

	public int compareTo(Treffen o) {
		return o.getErsterTag().compareTo(this.getErsterTag());
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public String getEmailPreviewText() {
		return this.getEmailPreviewText("Vorname");
	}

	public String getEmailPreviewText(String vorname) {
		String emailText2 = this.emailText == null ? "" : this.emailText;

		return String.format(emailText2, this.getVonBisString(), this.getBeschreibung(), vorname);
	}

	public String getEmailText() {
		return this.emailText;
	}

	public Date getErsterTag() {
		return this.ersterTag;
	}

	public String getErsterTagString() {
		if (this.ersterTag != null) {
			return ApplicationData.DATE_FORMAT.format(this.ersterTag);
		}
		return "";
	}

	public int getJahr() {
		Treffen.cal.setTime(this.ersterTag);
		return Treffen.cal.get(Calendar.YEAR);
	}

	public Date getLetzterTag() {
		return this.letzterTag;
	}

	public String getLetzterTagString() {
		if (this.letzterTag != null) {
			return ApplicationData.DATE_FORMAT.format(this.letzterTag);
		}
		return "";
	}

	public String getName() {
		return this.name;
	}

	public String getVonBisString() {
		return this.getErsterTagString() + " - " + this.getLetzterTagString();
	}

	public boolean isAktuell() {
		try {
			String heuteString = ApplicationData.DATE_FORMAT.format(new Date());
			Date heute = ApplicationData.DATE_FORMAT.parse(heuteString);
			if (heute.compareTo(this.getErsterTag()) < 0 || heute.compareTo(this.getLetzterTag()) > 0) {
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
			String ersterJuliString = "01.07." + this.getJahr();
			Date ersterJuli = ApplicationData.DATE_FORMAT.parse(ersterJuliString);
			return this.getErsterTag().compareTo(ersterJuli) < 0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public void setEmailText(String emailText) {
		String oldValue = this.getEmailText();
		this.emailText = emailText;
		this.firePropertyChange(Treffen.EMAIL_TEXT, oldValue, this.getEmailText());
		this.firePropertyChange(Treffen.EMAIL_PREVIEW_TEXT, oldValue, this.getEmailPreviewText());
	}

	public void setErsterTag(Date ersterTag) {
		this.ersterTag = ersterTag;
	}

	public void setErsterTagString(String erster) {
		try {
			String oldValue = this.getErsterTagString();
			this.ersterTag = ApplicationData.DATE_FORMAT.parse(erster);
			this.firePropertyChange(Treffen.ERSTER_TAG, oldValue, this.getErsterTagString());
		} catch (ParseException e) {
			this.firePropertyChange(Treffen.ERSTER_TAG, null, this.getErsterTagString());
		}
	}

	public void setLetzterTag(Date letzterTag) {
		this.letzterTag = letzterTag;
	}

	public void setLetzterTagString(String letzter) {
		try {
			String oldValue = this.getLetzterTagString();
			this.letzterTag = ApplicationData.DATE_FORMAT.parse(letzter);
			this.firePropertyChange(Treffen.LETZTER_TAG, oldValue, this.getLetzterTagString());
		} catch (ParseException e) {
			this.firePropertyChange(Treffen.LETZTER_TAG, null, this.getLetzterTagString());
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}