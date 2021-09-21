package metroSystem;
import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;


public class Database {

    private static Database instance = new Database();
    private ArrayList<Station> allStations;

    private Database() {
        allStations = new ArrayList<>();
        loadStations();
    }

    public static Database getInstance() {
        return instance;
    }

    private void loadStations() {
        XSSFWorkbook workbookHK = null, workbookSZ = null;
        try {
            System.out.println("Current workspace: " + System.getProperty("user.dir"));
            File fileHK = new File("./data/stations_HK.xlsx");
            File fileSZ = new File("./data/stations_SZ.xlsx");
            InputStream inputStreamHK = new FileInputStream(fileHK);
            InputStream inputStreamSZ = new FileInputStream(fileSZ);
            workbookHK = new XSSFWorkbook(inputStreamHK);
            workbookSZ = new XSSFWorkbook(inputStreamSZ);
        }
        catch (Exception e) {
            System.out.println("Fail to load file!\nDetails: " + e);
        }
        XSSFSheet sheetHK = workbookHK.getSheetAt(0);
        XSSFSheet sheetSZ = workbookSZ.getSheetAt(0);
        for (Row row : sheetHK) {
            if (row.getRowNum() == 0)
                continue;
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            allStations.add(new Station(allStations.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorHK.getInstance()));
        }
        for (Row row : sheetSZ) {
            if (row.getRowNum() == 0)
                continue;
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            allStations.add(new Station(allStations.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorSZ.getInstance()));
        }
    }

    public ArrayList<Station> getAllStations() {
        return allStations;
    }
}