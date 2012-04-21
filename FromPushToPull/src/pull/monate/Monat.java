package pull.monate;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.chrono.ISOChronology;

import common.Umsatz;

public class Monat implements IMonat
{

	protected final YearMonth yearMonth;
	protected final IMonat vorgaengerMonat;
	protected final List<Umsatz> umsaetze = new ArrayList<Umsatz>();

	public Monat(LocalDate date, IMonat monat, List<Umsatz> umsaetze)
	{
		super();
		this.vorgaengerMonat = monat;
		this.yearMonth = new YearMonth(date);
		initUmsaetze(umsaetze);
	}

	private void initUmsaetze(List<Umsatz> umsaetze)
	{
		for (Umsatz umsatz : umsaetze)
		{
			if (yearMonth.equals(new YearMonth(umsatz.getDate())))
			{
				this.umsaetze.add(umsatz);
			}
		}
	}

	public YearMonth getYearMonth()
	{
		return yearMonth;
	}

	public int getBestand()
	{
		int result = vorgaengerMonat.getBestand();
		for (Umsatz umsatz : umsaetze)
		{
			result += umsatz.getUmsatz();
		}
		return result;
	}

	public int getDurschschnittsBestand()
	{
		int result = vorgaengerMonat.getBestand();
		for (Umsatz umsatz : umsaetze)
		{
			result += ermittleAnteil(umsatz);
		}

		return result;
	}

	protected double ermittleAnteil(Umsatz umsatz)
	{
		int gueltigeTage = anzahlTageImMonat() - umsatz.getDate().getDayOfMonth() + 1;
		double anteil = (double) gueltigeTage / anzahlTageImMonat();
		return (umsatz.getUmsatz() * anteil);
	}

	private int anzahlTageImMonat()
	{
		return ISOChronology.getInstance().dayOfMonth().getMaximumValue(yearMonth);
	}

}