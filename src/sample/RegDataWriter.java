package sample;

import sample.model.LicInfo;

import java.io.File;
import java.io.FileWriter;

public class RegDataWriter {
    public LicInfo licInfo;
    public String fullDirectoryName;
    public String rezult;

    public RegDataWriter(String fullDirectoryName, String rezult) {
        this.fullDirectoryName = fullDirectoryName;
        this.rezult = rezult;
    }

    public void saveToFile(){
//        fullDirectoryName = "C:\\Temp\\LicTest\\777";
//        //TODO: Для тестов, потом убрать.
        String path = fullDirectoryName + "\\" + "LicData.txt";
        System.out.println("path = " + path);
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            fw.write(rezult);
            fw.flush();
            fw.close();
            System.out.println("File saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
