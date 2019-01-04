package sample.model;

public class LicInfo {

    private String licFileName;
    private String fullLicFileName;
    private String regNumber;
    private String pinCode;
    private String regInfo;

    public LicInfo(String licFileName, String fullLicFileName, String regNumber, String pinCode, String regInfo) {
        this.licFileName = licFileName;
        this.fullLicFileName = fullLicFileName;
        this.regNumber = regNumber;
        this.pinCode = pinCode;
        this.regInfo = regInfo;
    }

    public String getLicFileName() {
        return licFileName;
    }

    public void setLicFileName(String licFileName) {
        this.licFileName = licFileName;
    }

    public String getFullLicFileName() {
        return fullLicFileName;
    }

    public void setFullLicFileName(String fullLicFileName) {
        this.fullLicFileName = fullLicFileName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getRegInfo() {
        return regInfo;
    }

    public void setRegInfo(String regInfo) {
        this.regInfo = regInfo;
    }
}
