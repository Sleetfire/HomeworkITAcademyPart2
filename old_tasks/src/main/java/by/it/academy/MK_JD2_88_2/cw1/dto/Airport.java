package by.it.academy.MK_JD2_88_2.cw1.dto;

public class Airport {

    private String airportCode;
    private AirportName airportName;
    private AirportCity airportCity;
    private Coordinates coordinates;
    private String timezone;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public AirportName getAirportName() {
        return airportName;
    }

    public void setAirportName(AirportName airportName) {
        this.airportName = airportName;
    }

    public AirportCity getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(AirportCity airportCity) {
        this.airportCity = airportCity;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", airportName=" + airportName +
                ", airportCity=" + airportCity +
                ", coordinates=" + coordinates +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
