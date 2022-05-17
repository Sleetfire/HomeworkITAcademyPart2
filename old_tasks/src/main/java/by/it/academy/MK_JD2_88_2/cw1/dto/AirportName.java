package by.it.academy.MK_JD2_88_2.cw1.dto;

public class AirportName {

    private String en;
    private String ru;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
        return "AirportName{" +
                "en='" + en + '\'' +
                ", ru='" + ru + '\'' +
                '}';
    }
}
