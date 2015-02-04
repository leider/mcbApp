package mcb.persistenz;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Boolean;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import mcb.model.Adresse;

public class ExcelExporter {
  private static final int ID = 0;
  private static final int VORNAME = 1;
  private static final int NAME = 2;
  private static final int EMAIL = 3;
  private static final int STRASSE = 4;
  private static final int LAND = 5;
  private static final int PLZ = 6;
  private static final int ORT = 7;
  private static final int GESPANN = 8;
  private static final int SOLO = 9;

  private int row = 0;

  public void exportiereAdressen(File file, List<Adresse> adressenToExport) {
    try {
      WritableWorkbook workbook = Workbook.createWorkbook(file);
      WritableSheet sheet = workbook.createSheet("Report", 0);
      this.writeHeader(sheet);
      for (Adresse adresse : adressenToExport) {
        this.writeAdresse(adresse, sheet);
      }
      workbook.write();
      workbook.close();
    } catch (Exception e) {
      ImAndExporter.LOGGER.fatal(e.getMessage(), e);
      e.printStackTrace();
    }
  }

  private void writeAdresse(Adresse adresse, WritableSheet sheet) throws RowsExceededException, WriteException {
    this.row++;
    sheet.addCell(new Number(ExcelExporter.ID, this.row, adresse.getId()));
    sheet.addCell(new Label(ExcelExporter.VORNAME, this.row, adresse.getVorname()));
    sheet.addCell(new Label(ExcelExporter.NAME, this.row, adresse.getName()));
    sheet.addCell(new Label(ExcelExporter.EMAIL, this.row, adresse.getEmail()));
    sheet.addCell(new Label(ExcelExporter.STRASSE, this.row, adresse.getStrasse()));
    sheet.addCell(new Label(ExcelExporter.LAND, this.row, adresse.getLandAusgeschrieben()));
    sheet.addCell(new Label(ExcelExporter.PLZ, this.row, adresse.getPlz()));
    sheet.addCell(new Label(ExcelExporter.ORT, this.row, adresse.getOrt()));
    sheet.addCell(new Boolean(ExcelExporter.GESPANN, this.row, adresse.isGespann()));
    sheet.addCell(new Boolean(ExcelExporter.SOLO, this.row, adresse.isSolo()));
  }

  private void writeHeader(WritableSheet sheet) throws RowsExceededException, WriteException {
    sheet.addCell(new Label(ExcelExporter.ID, this.row, "id"));
    sheet.addCell(new Label(ExcelExporter.VORNAME, this.row, "vorname"));
    sheet.addCell(new Label(ExcelExporter.NAME, this.row, "name"));
    sheet.addCell(new Label(ExcelExporter.EMAIL, this.row, "email"));
    sheet.addCell(new Label(ExcelExporter.STRASSE, this.row, "strasse"));
    sheet.addCell(new Label(ExcelExporter.LAND, this.row, "land"));
    sheet.addCell(new Label(ExcelExporter.PLZ, this.row, "plz"));
    sheet.addCell(new Label(ExcelExporter.ORT, this.row, "ort"));
    sheet.addCell(new Label(ExcelExporter.GESPANN, this.row, "gespann"));
    sheet.addCell(new Label(ExcelExporter.SOLO, this.row, "solo"));
  }

}
