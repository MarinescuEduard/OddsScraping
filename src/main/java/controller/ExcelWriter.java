package controller;

import model.MatchModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    public void writeInExcel(List<MatchModel> matchesList, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Unibet-Esports");
        Row headerRow = sheet.createRow(0);

        String[] headers = {"Sport Group", "Sport Tournament", "Home Team", "Away Team", "Home Odds", "Away Odds"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (MatchModel currentMatch : matchesList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(currentMatch.getSportGroup());
            row.createCell(1).setCellValue(currentMatch.getSportTournament());
            row.createCell(2).setCellValue(currentMatch.getHomeTeam());
            row.createCell(3).setCellValue(currentMatch.getAwayTeam());
            row.createCell(4).setCellValue(currentMatch.getHomeOdds());
            row.createCell(5).setCellValue(currentMatch.getAwayOdds());
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }
}

