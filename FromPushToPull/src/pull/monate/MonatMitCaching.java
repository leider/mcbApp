package pull.monate;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import common.Umsatz;

public class MonatMitCaching extends Monat
{

	private Integer bestand = null;
	private Integer durchschnittsBestand = null;

	public MonatMitCaching(LocalDate date, IMonat monat, List<Umsatz> umsaetze)
	{
		super(date, monat);
	}

	@Override
	public YearMonth getYearMonth()
	{
		return yearMonth;
	}

	@Override
	public int getBestand()
	{
		if (bestand == null)
		{
			bestand = Integer.valueOf(ermittleBestand());
		}
		return bestand.intValue();
	}

	@Override
	public int getDurschschnittsBestand()
	{
		if (durchschnittsBestand == null)
		{
			durchschnittsBestand = Integer.valueOf(ermittleDurchschnittsBestand());
		}
		return durchschnittsBestand.intValue();
	}

	private int ermittleBestand()
	{
		int result = vorgaengerMonat.getBestand();
		for (Umsatz umsatz : umsaetze)
		{
			result += umsatz.getUmsatz();
		}
		return result;
	}

	private int ermittleDurchschnittsBestand()
	{
		int result = vorgaengerMonat.getBestand();
		for (Umsatz umsatz : umsaetze)
		{
			result += ermittleAnteil(umsatz);
		}

		return result;
	}

}
