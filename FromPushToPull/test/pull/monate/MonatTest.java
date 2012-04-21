package pull.monate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

import common.Umsatz;

public class MonatTest
{
	private static final LocalDate APRIL_ULTIMO = new LocalDate(2010, 4, 30);
	private static final LocalDate MAI_ULTIMO = new LocalDate(2010, 5, 31);

	@Test
	public void einMonatOhneUmsaetzeHatKeinenBestand()
	{
		Monat monat = new Monat(APRIL_ULTIMO, new DummyMonat(), new ArrayList<Umsatz>());
		Assert.assertEquals(0, monat.getBestand());
		Assert.assertEquals(0, monat.getDurschschnittsBestand());
	}

	@Test
	public void einMonatMitEinemUmsatzHatDessenUmsatzAlsBestand()
	{
		List<Umsatz> umsaetze = new ArrayList<Umsatz>();
		umsaetze.add(new Umsatz(new LocalDate(2010, 4, 1), 100));
		Monat monat = new Monat(APRIL_ULTIMO, new DummyMonat(), umsaetze);
		Assert.assertEquals(100, monat.getBestand());
		Assert.assertEquals(100, monat.getDurschschnittsBestand());
	}

	@Test
	public void einMonatMitZweiUmsaetzenHatDieSummeAlsBestand()
	{
		List<Umsatz> umsaetze = new ArrayList<Umsatz>();
		umsaetze.add(new Umsatz(new LocalDate(2010, 4, 1), 100));
		umsaetze.add(new Umsatz(new LocalDate(2010, 4, 16), 100));
		Monat monat = new Monat(APRIL_ULTIMO, new DummyMonat(), umsaetze);
		Assert.assertEquals(200, monat.getBestand());
		Assert.assertEquals(150, monat.getDurschschnittsBestand());
	}

	@Test
	public void zweiMonateMitZweiUmsaetzenHatDieSummeAlsBestand()
	{
		List<Umsatz> umsaetze = new ArrayList<Umsatz>();
		umsaetze.add(new Umsatz(new LocalDate(2010, 4, 16), 100));
		umsaetze.add(new Umsatz(new LocalDate(2010, 5, 16), 200));

		Monat april = new Monat(APRIL_ULTIMO, new DummyMonat(), umsaetze);
		Monat mai = new Monat(MAI_ULTIMO, april, umsaetze);
		Assert.assertEquals(300, mai.getBestand());
		Assert.assertEquals(203, mai.getDurschschnittsBestand());
	}
}
