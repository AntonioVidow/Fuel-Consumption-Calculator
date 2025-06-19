import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        createFile();

        Scanner sc = new Scanner(System.in);
        System.out.println("Input date of refueling: ");
        String dateString = sc.next();
        LocalDate date = inputDate(dateString);
        System.out.println(date);

        System.out.println("Input distance that you have driven with this fuel: ");
        double distance = sc.nextDouble();
        System.out.println("Input the amount of fuel you refilled: ");
        double fuel = sc.nextDouble();
        double fuelConsumption = (fuel / distance) * 100;
        fuelConsumption = Math.round((fuelConsumption * 100.0) / 100.0);

        
    }

    public static LocalDate inputDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, formatter);
            System.out.println("Parsed date: " + date);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd.MM.yyyy.");
        }

        return date;
    }

    public static void createFile(){
        try{
            File data = new File("fuel_consumption.txt");
            if(data.createNewFile()){
                System.out.println("File created!");
            }else{
                System.out.println("File already exist.");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}