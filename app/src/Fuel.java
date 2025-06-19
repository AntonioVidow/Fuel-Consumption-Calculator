import java.util.Date;

public class Fuel {
    private double fuelConsumption;
    private Date date;

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Fuel(double fuelConsumption, Date date) {
        this.fuelConsumption = fuelConsumption;
        this.date = date;
    }
}
