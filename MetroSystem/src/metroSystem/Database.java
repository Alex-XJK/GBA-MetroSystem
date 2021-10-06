package MetroSystem.src.metroSystem;

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
    private int stationsHK, edgesHK, edgesSZ;

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
            System.out.println("Current workspace: " + System.getProperty("user.dir"));
            File fileHK = new File("./MetroSystem/data/stations_HK.xlsx");
            File fileSZ = new File("./MetroSystem/data/stations_SZ.xlsx");
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
    }

    public void loadEdges() {
        XSSFWorkbook workbookHK = null, workbookSZ = null, workbookBorder = null;
        try {
            File fileHK = new File("./MetroSystem/data/edges_HK.xlsx");
            File fileSZ = new File("./MetroSystem/data/edges_SZ.xlsx");
            File fileBorder = new File("./MetroSystem/data/edges_border.xlsx");
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
            allEdges.add(new Edge(allEdges.size() + 1, st_station, ed_station, AdministratorHK.getInstance()));
            allEdges.add(new Edge(allEdges.size() + 1, ed_station, st_station, AdministratorHK.getInstance()));
        }
        edgesHK = allEdges.size() / 2;
        for (Row row : sheetSZ) {
            if (row.getRowNum() == 0)
                continue;
            Station st_station = allStations.get(stationsHK + (int)row.getCell(0).getNumericCellValue() - 1);
            Station ed_station = allStations.get(stationsHK + (int)row.getCell(1).getNumericCellValue() - 1);
            allEdges.add(new Edge(allEdges.size() + 1, st_station, ed_station, AdministratorSZ.getInstance()));
            allEdges.add(new Edge(allEdges.size() + 1, ed_station, st_station, AdministratorSZ.getInstance()));
        }
        edgesSZ = allEdges.size() / 2 - edgesHK;
        for (Row row : sheetBorder) {
            if (row.getRowNum() == 0)
                continue;
            Station st_station = allStations.get((int)row.getCell(0).getNumericCellValue() - 1);
            Station ed_station = allStations.get(stationsHK + (int)row.getCell(1).getNumericCellValue() - 1);
            boolean isOpen = ((int)row.getCell(2).getNumericCellValue()) == 0? false : true;
            Edge tempEdge = new Edge(allEdges.size() + 1, st_station, ed_station, AdministratorBorder.getInstance());
            allEdges.add(tempEdge);
            tempEdge.setIsOpen(isOpen);
            tempEdge = new Edge(allEdges.size() + 1, ed_station, st_station, AdministratorBorder.getInstance());
            allEdges.add(tempEdge);
            tempEdge.setIsOpen(isOpen);
        }
    }

    /*
    public void loadLinesOld() {
        XSSFWorkbook workbookHK = null, workbookSZ = null;
        try {
            File fileHK = new File("./data/lines_HK_old.xlsx");
            File fileSZ = new File("./data/lines_SZ_old.xlsx");
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
     */

    public void loadLines() {
        XSSFWorkbook workbookHK = null, workbookSZ = null;
        try {
            File fileHK = new File("./MetroSystem/data/lines_HK.xlsx");
            File fileSZ = new File("./MetroSystem/data/lines_SZ.xlsx");
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

    public Line getLineByName(String name, Language language) {
        for(Line l : allLines) {
            if(l.getNameInSpecificLanguage(language).equals(name))
                return l;
        }
        return null;
    }

    public Station getStationByName(String name, Language language, Administrator admin) {
        for(Station s : allStations) {
            if(s.getNameInSpecificLanguage(language).equals(name) && s.getAdmin() == admin)
                return s;
        }
        return null;
    }

    /***
     * Find a station according to its station_id.
     * @param id    The id of your target station
     * @return      The reference of your target station
     * @throws ExStationNotFound    If the given id cannot be matching with any station in database
     */
    public Station getStationById(int id) throws ExStationNotFound {
        for(Station s : allStations) {
            if(s.getId() == id) {
                return s;
            }
        }
        String exp = "Station of id = " + id +" cannot be found in our database!";
        throw new ExStationNotFound(exp);
    }

    public int getStationCount(){
        return allStations.size();
    }

    public ArrayList<Edge> getEdges(){
        return allEdges;
    }

    /***
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

}