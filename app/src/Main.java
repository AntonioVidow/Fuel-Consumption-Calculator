import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File data = createFile();

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
        String formatedFuelConsumption = String.format("%.2f", fuelConsumption);
        String toBeWritten = formatedFuelConsumption + " : " + date;

        ArrayList<Double> fuelToAverage = new ArrayList<>();
        double averageFuelConsumption = 0;
        double sum = 0;
        ArrayList<String> readData = readFile(data, fuelToAverage);
        boolean flag = readData.isEmpty();
        if(flag){
            writeFile(data, toBeWritten, flag);
        }else{
            System.out.println(readData);
            for (double a: fuelToAverage) {
                sum += a;
            }
            averageFuelConsumption = sum / fuelToAverage.size();
            String formatedAverageConsumption = String.format("%.2f", averageFuelConsumption);
            writeFile(data, toBeWritten, flag);

        }
    }

    public static LocalDate inputDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd.MM.yyyy.");
        }
        return date;
    }

    public static File createFile(){
        File data = null;
        try{
            data = new File("fuel_consumption.txt");
            if(data.createNewFile()){
                System.out.println("File created!");
                return data;
            }else{
                System.out.println("File already exist.");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<String> readFile(File file, ArrayList<Double> allfuel){
        ArrayList<String> done = new ArrayList<>();
        try{
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                done.add(line);
                String fuelOfLine = line.substring(0, 4);
                double fuel = Double.parseDouble(fuelOfLine);
                allfuel.add(fuel);
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return done;
    }

    public static void writeFile(File file, String message, boolean flag){
        try{
            BufferedWriter writer = new BufferedWriter( new FileWriter(file.getAbsolutePath(), true));
            if(flag){
                writer.write(message);
            }else{
                writer.append("\n");
                writer.append(message);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}