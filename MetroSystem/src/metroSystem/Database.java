package MetroSystem.src.metroSystem;

import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class Database {

    private volatile static Database uniqueInstance;
    private ArrayList<Station> allStations;
    private ArrayList<Line> allLines;
    private int stationsHK, stationsSZ;

    private Database() {
        allStations = new ArrayList<>();
        allLines = new ArrayList<>();
    }

    /**
     * Apply double-checked locking method to create this important singleton object.
     * @return  the unique Database instance
     * @since   Sept. 29, 2021
     */
    public static Database getInstance() {
        if(uniqueInstance == null) {
            synchronized (Database.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new Database();
                }
            }
        }
        return uniqueInstance;
    }

    public void loadStations() {
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
        stationsHK = allStations.size();
        for (Row row : sheetSZ) {
            if (row.getRowNum() == 0)
                continue;
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            allStations.add(new Station(allStations.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorSZ.getInstance()));
        }

        stationsSZ = allStations.size() - stationsHK;
    }

    public void loadLines() {
        XSSFWorkbook workbookHK = null, workbookSZ = null;
        try {
            File fileHK = new File("./data/lines_HK.xlsx");
            File fileSZ = new File("./data/lines_SZ.xlsx");
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

        ArrayList<Integer> tempStations = new ArrayList<>();
        int tempStationSize;

        for (Row row : sheetHK) {
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            tempStations.clear();
            tempStationSize = 3;
            while (row.getCell(tempStationSize) != null)
                tempStations.add((int)row.getCell(tempStationSize++).getNumericCellValue());
            allLines.add(new Line(allLines.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorHK.getInstance(), tempStations));
        }
        for (Row row : sheetSZ) {
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            tempStations.clear();
            tempStationSize = 3;
            while (row.getCell(tempStationSize) != null)
                tempStations.add((int)row.getCell(tempStationSize++).getNumericCellValue() + stationsHK);
            allLines.add(new Line(allLines.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorSZ.getInstance(), tempStations));
        }
    }

    public ArrayList<Station> getAllStations() {
        return allStations;
    }

    public Line getLineByName(String name, Language language) {
        for(Line l : allLines) {
            if(l.getNameInSpecificLanguage(language).equals(name))
                return l;
        }
        return null;
    }

    // Todo:
    //  Create the Adjacency Matrix according to the station data
    //  Not sure whether a {@code DBwithAdjMatrix} child class should be used
}