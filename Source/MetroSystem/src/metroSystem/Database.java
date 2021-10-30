package metroSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class Database {

    private volatile static Database uniqueInstance;
    private ArrayList<Station> allStations;
    private ArrayList<Edge> allEdges;
    private ArrayList<Line> allLines;
    private float[][] priceHK, priceSZ;
    private int stationsHK, stationsSZ, edgesHK, edgesSZ;
    private String base;

    private Database() {
        allStations = new ArrayList<>();
        allEdges = new ArrayList<>();
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
            base = System.getProperty("user.dir");
            System.out.println("Current workspace: " + base);
            File fileHK = new File(base + "/data/stations_HK.xlsx");
            File fileSZ = new File(base + "/data/stations_SZ.xlsx");
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

    public void loadEdges() {
        XSSFWorkbook workbookHK = null, workbookSZ = null, workbookBorder = null;
        try {
            File fileHK = new File(base + "/data/edges_HK.xlsx");
            File fileSZ = new File(base + "/data/edges_SZ.xlsx");
            File fileBorder = new File(base + "/data/edges_border.xlsx");
            InputStream inputStreamHK = new FileInputStream(fileHK);
            InputStream inputStreamSZ = new FileInputStream(fileSZ);
            InputStream inputStreamBorder = new FileInputStream(fileBorder);
            workbookHK = new XSSFWorkbook(inputStreamHK);
            workbookSZ = new XSSFWorkbook(inputStreamSZ);
            workbookBorder = new XSSFWorkbook(inputStreamBorder);
        }
        catch (Exception e) {
            System.out.println("Fail to load file!\nDetails: " + e);
        }
        XSSFSheet sheetHK = workbookHK.getSheetAt(0);
        XSSFSheet sheetSZ = workbookSZ.getSheetAt(0);
        XSSFSheet sheetBorder = workbookBorder.getSheetAt(0);
        for (Row row : sheetHK) {
            if (row.getRowNum() == 0)
                continue;
            Station st_station = allStations.get((int) row.getCell(0).getNumericCellValue() - 1);
            Station ed_station = allStations.get((int) row.getCell(1).getNumericCellValue() - 1);
            int time = (int) row.getCell(2).getNumericCellValue();
            allEdges.add(new Edge(allEdges.size() + 1, st_station, ed_station, time, AdministratorHK.getInstance()));
            allEdges.add(new Edge(allEdges.size() + 1, ed_station, st_station, time, AdministratorHK.getInstance()));
        }
        edgesHK = allEdges.size() / 2;
        for (Row row : sheetSZ) {
            if (row.getRowNum() == 0)
                continue;
            Station st_station = allStations.get(stationsHK + (int)row.getCell(0).getNumericCellValue() - 1);
            Station ed_station = allStations.get(stationsHK + (int)row.getCell(1).getNumericCellValue() - 1);
            int time = (int) row.getCell(2).getNumericCellValue();
            allEdges.add(new Edge(allEdges.size() + 1, st_station, ed_station, time, AdministratorSZ.getInstance()));
            allEdges.add(new Edge(allEdges.size() + 1, ed_station, st_station, time, AdministratorSZ.getInstance()));
        }
        edgesSZ = allEdges.size() / 2 - edgesHK;
        for (Row row : sheetBorder) {
            if (row.getRowNum() == 0)
                continue;
            Station st_station = allStations.get((int)row.getCell(0).getNumericCellValue() - 1);
            Station ed_station = allStations.get(stationsHK + (int)row.getCell(1).getNumericCellValue() - 1);
            boolean isOpen = ((int)row.getCell(2).getNumericCellValue()) == 0? false : true;
            int time = (int) row.getCell(3).getNumericCellValue();
            Edge tempEdge = new Edge(allEdges.size() + 1, st_station, ed_station, time, AdministratorBorder.getInstance());
            allEdges.add(tempEdge);
            tempEdge.setIsOpen(isOpen);
            tempEdge = new Edge(allEdges.size() + 1, ed_station, st_station, time, AdministratorBorder.getInstance());
            allEdges.add(tempEdge);
            tempEdge.setIsOpen(isOpen);
        }
    }

    public void loadLines() {
        XSSFWorkbook workbookHK = null, workbookSZ = null;
        try {
            File fileHK = new File(base + "/data/lines_HK.xlsx");
            File fileSZ = new File(base + "/data/lines_SZ.xlsx");
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
            ArrayList<Edge> tempEdges = new ArrayList<>();
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            int l = (int)row.getCell(3).getNumericCellValue(), r = (int)row.getCell(4).getNumericCellValue();
            for (int i = (l - 2) * 2; i <= (r - 2) * 2 + 1; i++)
                tempEdges.add(allEdges.get(i));
            allLines.add(new Line(allLines.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorHK.getInstance(), tempEdges));
        }
        for (Row row : sheetSZ) {
            if (row.getRowNum() == 0)
                continue;
            ArrayList<Edge> tempEdges = new ArrayList<>();
            String englishName = row.getCell(0).getStringCellValue();
            String traditionalChineseName = row.getCell(1).getStringCellValue();
            String simplifiedChineseName = row.getCell(2).getStringCellValue();
            int l = (int)row.getCell(3).getNumericCellValue() + edgesHK, r = (int)row.getCell(4).getNumericCellValue() + edgesHK;
            for (int i = (l - 2) * 2; i <= (r - 2) * 2 + 1; i++)
                tempEdges.add(allEdges.get(i));
            allLines.add(new Line(allLines.size() + 1, englishName, traditionalChineseName, simplifiedChineseName, AdministratorSZ.getInstance(), tempEdges));
        }
    }

    public void loadPrice() {
        XSSFWorkbook workbookHK = null, workbookSZ = null;
        try {
            File fileHK = new File(base + "/data/price_HK.xlsx");
            File fileSZ = new File(base + "/data/price_SZ.xlsx");
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

        Row row = sheetHK.getRow(sheetHK.getLastRowNum());
        int rows = sheetHK.getLastRowNum(), columns = row.getLastCellNum() - 1;

        priceHK = new float[stationsHK + 1][stationsHK + 1];
        for(int i = 1; i <= rows; i++) {
            row = sheetHK.getRow(i);
            for (int j = 1; j <= columns; j++) {
                float price = (float) row.getCell(j).getNumericCellValue();
                int startStationId = (int)sheetHK.getRow(i).getCell(0).getNumericCellValue();
                int endStationId = (int)sheetHK.getRow(0).getCell(j).getNumericCellValue();
                priceHK[startStationId][endStationId] = price;
            }
        }

        row = sheetSZ.getRow(sheetSZ.getLastRowNum());
        rows = sheetSZ.getLastRowNum();
        columns = row.getLastCellNum() - 1;

        priceSZ = new float[stationsSZ + 1][stationsSZ + 1];
        for(int i = 1; i <= rows; i++) {
            row = sheetSZ.getRow(i);
            for (int j = 1; j <= columns; j++) {
                float price = (float) row.getCell(j).getNumericCellValue();
                int startStationId = (int)sheetSZ.getRow(i).getCell(0).getNumericCellValue();
                int endStationId = (int)sheetSZ.getRow(0).getCell(j).getNumericCellValue();
                priceSZ[startStationId][endStationId] = price;
            }
        }
    }

    /**
     * Find a line according to its line name.
     * Involve {@code Line} to handle core searching.
     * @param name      The name of your target subway line
     * @param language  The language you are using
     * @return The reference of your target line
     * @throws ExLineNotFound    If the given name cannot be matching with any line in database
     */
    public Line getLineByName(String name, Language language) throws ExLineNotFound {
        Line l = Line.searchLineByName(allLines, name, language);
        if (l == null) {
            throw new ExLineNotFound();
        }
        return l;
    }

    /**
     * Find a station according to its station_name.
     * Involve {@code Station} to handle core searching.
     * @param name      The name of your target station
     * @param language  The language you are using
     * @param admin     The instance of the administrator of the station.
     * @return The reference of your target station
     * @throws ExStationNotFound    If the given name cannot be matching with any station in database
     */
    public Station getStationByName(String name, Language language, Administrator admin) throws ExStationNotFound {
        Station sta = Station.searchStationByName(allStations, name, language, admin);
        if (sta == null) {
            String exp = "Station with name " + name +" cannot be found in our database!";
            throw new ExStationNotFound(exp);
        }
        return sta;
    }

    /**
     * Find a station according to its station_id.
     * Involve {@code Station} to handle core searching.
     * @param id    The id of your target station
     * @return      The reference of your target station
     * @throws ExStationNotFound    If the given id cannot be matching with any station in database
     */
    public Station getStationById(int id) throws ExStationNotFound {
        Station sta = Station.searchStationById(allStations, id);
        if (sta == null) {
            String exp = "Station of id = " + id +" cannot be found in our database!";
            throw new ExStationNotFound(exp);
        }
        return sta;
    }

    public int getStationCount(){
        return allStations.size();
    }

    public ArrayList<Edge> getEdges(){
        return allEdges;
    }

    /**
     * Translate an array of station id to its station name
     * @param ids       An arraylist of station_id in integer format
     * @return An arraylist of station_name in your desired language with the original order
     */
    public ArrayList<String> translateId2Name(ArrayList<Integer> ids){
        ArrayList<String> names = new ArrayList<>();
        for (int id : ids) {
            String name = "?";
            try{
                name = getStationById(id).getName();
            }
            catch (ExStationNotFound e){
                System.out.println(e.getMessage());
            }
            finally {
                names.add(name);
            }
        }
        return names;
    }

    public float getPrice(int startStationId, int endStationId, Administrator adm) {
        if(adm == AdministratorHK.getInstance()) {
            return priceHK[startStationId][endStationId];
        }
        else if(adm == AdministratorSZ.getInstance()) {
            return priceSZ[startStationId - stationsHK][endStationId - stationsHK];
        }
        return -1;
    }
}