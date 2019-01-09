package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Extractor {

    private String fullDirectoryName;
    private String pinCode = null;
    private String regNumber = null;
    private String result;
    private String licFileName;
    private List<String> regDataList = new ArrayList<String>();


    public Extractor(String fullDirectoryName) {
        this.fullDirectoryName = fullDirectoryName;
        extract(fullDirectoryName);
    }

    private void extract(String fullDirectoryName) {

        List<String> results = new ArrayList<String>();
        File folder = new File(fullDirectoryName);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".lic")) {
                (results).add(file.getName());
            }
        }

        switch (results.size()) {
            case 0:
                result = "В директории отсутствуют файлы программных лицензий.";
                break;
            case 1:
                licFileName = results.get(0);
                extract(fullDirectoryName, results);
                break;
            default:
                result = "В директории должен быть только один файл \nпрограммной лицензии. \n\nОбнаружено файлов программных лицензий: " + results.size();
                break;
        }
    }

    private void extract(String fullDirectoryName, List<String> results) {

        getPinRegNumber(fullDirectoryName, licFileName);
        getRegDataInfo(fullDirectoryName, pinCode, regNumber);
    }

    private void getRegDataInfo(String fullDirectoryName, String pinCode, String regNumber) {

        try {
            String argument = "ring.cmd license info --name " + pinCode + "-" + regNumber + " --path " + fullDirectoryName;

            Process proc = Runtime.getRuntime().exec(argument);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream(), "windows-1251"));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");

            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                regDataList.add(s);

            }

//             read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            proc.waitFor();
            proc.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }

        formResult();
    }

    private void getPinRegNumber(String fullDirectoryName, String licFileName) {
        try {
            String argument = "ring.cmd license list --path " + fullDirectoryName;

            Process proc = Runtime.getRuntime().exec(argument);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            if ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                String pinReg[] = s.split("-");
                pinCode = pinReg[0];
                regNumber = pinReg[1];
            }

// read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            proc.waitFor();
            proc.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void formResult() {
        StringBuilder sb = new StringBuilder();
        sb.append("Файл: " + licFileName + "\r\n");
        sb.append("Пин-код: " + pinCode + "\r\n");
        sb.append("Рег.номер: " + regNumber + "\r\n");

        for (int i = 0; i < regDataList.size(); i++) {
            sb.append(regDataList.get(i) + "\r\n");
        }
        result = sb.toString();
    }

    public String getResult() {

        return result;
    }
}
