package com.sapient.asde.batch5.vehicledataservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.Climate;
import com.sapient.asde.batch5.vehicledataservice.entity.Light;
import com.sapient.asde.batch5.vehicledataservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleDownload;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.util.Pair;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApacheCommonsCsvUtils {
    private static String csvExtension = "csv";

    public static List<String> s2a(String str) throws ServiceException {
        if(str.equals("null"))
            return null;
        if (!str.contains("[") && str.contains("]")) {
            throw new ServiceException("Array Expected, Check [] paranthesis are closed correctly");
        }
        String newString = str.substring(1, str.length() - 1);
        String ws = newString.replace(" ", "");
        String[] arr = ws.split(",");
        List<String> convertedList = new ArrayList<>(Arrays.asList(arr));
        return convertedList;
    }

    public static Light lightConverter(String str) throws ServiceException {
        String ws = str.replace(" ", "");
        String[] arr = ws.split(",");
        if(arr.length!=5)
        {
            throw new ServiceException("More or Less attributes found for Light -> Check the given format");
        }
        Light light = new Light(Boolean.parseBoolean(arr[0]), Boolean.parseBoolean(arr[1]),
                Boolean.parseBoolean(arr[2]), Boolean.parseBoolean(arr[3]), Boolean.parseBoolean(arr[4]));
        return light;
    }

    public static Climate climateConverter(String str) throws ServiceException {
        String ws = str.replace(" ", "");
        String[] arr = ws.split(",");
        if (arr.length != 3) {
            throw new ServiceException("More or Less attributes found for Climate -> Check the given format");
        }
        Climate climate = new Climate(Boolean.parseBoolean(arr[0]), Boolean.parseBoolean(arr[1]),
                Boolean.parseBoolean(arr[2]));
        return climate;
    }

    public static void vehiclesToCsv(Writer writer, List<VehicleDownload> vehicles) throws IOException {

        try (CSVPrinter csvPrinter = new CSVPrinter(writer,
                CSVFormat.DEFAULT.withHeader(
                    "id",
                    "tankCapacity",
                    "mediaInterface",
                    "theftAlarm",
                    "pictureUrls",
                    "tripMeter",
                    "airBagCount",
                    "description",
                    "price",
                    "color",
                    "vin",
                    "brand",
                    "model",
                    "year",
                    "vehicleType",
                    "fuelType",
                    "unitsFuelConsumption",
                    "transmissionGearType",
                    "steeringWheelType",
                    "vehicleSpeed",
                    "horn",
                    "powerTrainTorque",
                    "accelaration",
                    "nightMode",
                    "isABS",
                    "wheelRadius",
                    "wheelSpeed",
                    "light",
                    "ignitionTime",
                    "odometer",
                    "washerFluid",
                    "malfunctionIndicator",
                    "batteryLevel",
                    "airConditioning",
                    "languageConfiguration",
                    "mirror",
                    "childSafetyLock",
                    "topSpeedLimit",
                    "climate",
                    "chime"
                    ));) {
            for (VehicleDownload vehicle : vehicles) {
                List<String> data = Arrays.asList(
                        vehicle.getId(),
                        vehicle.getTankCapacity(),
                        vehicle.getMediaInterface(), 
                        String.valueOf(vehicle.getTheftAlarm()),
                        String.valueOf(vehicle.getPictureUrls()),
                        vehicle.getTripMeter(),
                        String.valueOf(vehicle.getAirBagCount()),
                        vehicle.getDescription(),
                        String.valueOf(vehicle.getPrice()),
                        vehicle.getColor(),
                        vehicle.getVin(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        String.valueOf(vehicle.getYear()),
                        vehicle.getVehicleType(),
                        String.valueOf(vehicle.getFuelType()),
                        vehicle.getUnitsFuelConsumption(),
                        vehicle.getTransmissionGearType(),
                        vehicle.getSteeringWheelType(),
                        vehicle.getVehicleSpeed(),
                        vehicle.getHorn(),
                        vehicle.getPowerTrainTorque(),
                        vehicle.getAccelaration(),
                        String.valueOf(vehicle.getNightMode()),
                        String.valueOf(vehicle.getIsABS()),
                        vehicle.getWheelRadius(),
                        vehicle.getWheelSpeed(),
                        vehicle.getLight().toString(),
                        vehicle.getIgnitionTime(),
                        String.valueOf(vehicle.getOdometer()),
                        String.valueOf(vehicle.getWasherFluid()),
                        String.valueOf(vehicle.getMalfunctionIndicator()),
                        String.valueOf(vehicle.getBatteryLevel()),
                        String.valueOf(vehicle.getAirConditioning()),
                        String.valueOf(vehicle.getLanguageConfiguration()),
                        vehicle.getMirror(),
                        String.valueOf(vehicle.getChildSafetyLock()),
                        vehicle.getTopSpeedLimit(),
                        vehicle.getClimate().toString(),
                        String.valueOf(vehicle.getChime())
                    );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static Pair<Integer,List<Vehicle>> parseCsvFile(InputStream is,String dealer) {
        BufferedReader fileReader = null;
        CSVParser csvParser = null;
        Integer count = 0;
        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<String> csvHeaders = csvParser.getHeaderNames();
            log.info("CSV Header:{}",csvHeaders);
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            if(csvHeaders.contains("id"))
            {
                 for (CSVRecord csvRecord : csvRecords) {
                log.info("ID:{}", csvRecord.get("id"));
                List<String> pictureUrls = s2a(csvRecord.get("pictureUrls"));
                List<String> fuelType = s2a(csvRecord.get("fuelType"));
                List<String> languageConfiguration = s2a(csvRecord.get("languageConfiguration"));

                Light light = lightConverter(csvRecord.get("light"));
                Climate climate = climateConverter(csvRecord.get("climate"));

                Vehicle vehicle = new Vehicle(csvRecord.get("id"), dealer,
                        csvRecord.get("tankCapacity"), csvRecord.get("mediaInterface"),
                        Boolean.parseBoolean(csvRecord.get("theftAlarm")), pictureUrls, csvRecord.get("tripMeter"),
                        Integer.parseInt(csvRecord.get("airBagCount")), csvRecord.get("description"),
                        Double.parseDouble(csvRecord.get("price")), csvRecord.get("color"), csvRecord.get("vin"),
                        csvRecord.get("brand"), csvRecord.get("model"), Integer.parseInt(csvRecord.get("year")),
                        csvRecord.get("vehicleType"), fuelType, csvRecord.get("unitsFuelConsumption"),
                        csvRecord.get("transmissionGearType"), csvRecord.get("steeringWheelType"),
                        csvRecord.get("vehicleSpeed"), csvRecord.get("horn"), csvRecord.get("powerTrainTorque"),
                        csvRecord.get("accelaration"), Boolean.parseBoolean(csvRecord.get("nightMode")),
                        Boolean.parseBoolean(csvRecord.get("isABS")), csvRecord.get("wheelRadius"),
                        csvRecord.get("wheelSpeed"), light, csvRecord.get("ignitionTime"),
                        Boolean.parseBoolean(csvRecord.get("odometer")),
                        Boolean.parseBoolean(csvRecord.get("washerFluid")),
                        Boolean.parseBoolean(csvRecord.get("malfunctionIndicator")),
                        Integer.parseInt(csvRecord.get("batteryLevel")),
                        Boolean.parseBoolean(csvRecord.get("airConditioning")), languageConfiguration,
                        csvRecord.get("mirror"), Boolean.parseBoolean(csvRecord.get("childSafetyLock")),
                        csvRecord.get("topSpeedLimit"), climate, Boolean.parseBoolean(csvRecord.get("chime")));

                vehicles.add(vehicle);
                count = count+1;
            }
            }
            else{
                log.info("Without Id");
                for (CSVRecord csvRecord : csvRecords) {
                    List<String> pictureUrls = s2a(csvRecord.get("pictureUrls"));
                    List<String> fuelType = s2a(csvRecord.get("fuelType"));
                    List<String> languageConfiguration = s2a(csvRecord.get("languageConfiguration"));

                    Light light = lightConverter(csvRecord.get("light"));
                    Climate climate = climateConverter(csvRecord.get("climate"));

                    Vehicle vehicle = new Vehicle(
                            dealer,
                            csvRecord.get("tankCapacity"), csvRecord.get("mediaInterface"),
                            Boolean.parseBoolean(csvRecord.get("theftAlarm")), pictureUrls, csvRecord.get("tripMeter"),
                            Integer.parseInt(csvRecord.get("airBagCount")), csvRecord.get("description"),
                            Double.parseDouble(csvRecord.get("price")), csvRecord.get("color"), csvRecord.get("vin"),
                            csvRecord.get("brand"), csvRecord.get("model"), Integer.parseInt(csvRecord.get("year")),
                            csvRecord.get("vehicleType"), fuelType, csvRecord.get("unitsFuelConsumption"),
                            csvRecord.get("transmissionGearType"), csvRecord.get("steeringWheelType"),
                            csvRecord.get("vehicleSpeed"), csvRecord.get("horn"), csvRecord.get("powerTrainTorque"),
                            csvRecord.get("accelaration"), Boolean.parseBoolean(csvRecord.get("nightMode")),
                            Boolean.parseBoolean(csvRecord.get("isABS")), csvRecord.get("wheelRadius"),
                            csvRecord.get("wheelSpeed"), light, csvRecord.get("ignitionTime"),
                            Boolean.parseBoolean(csvRecord.get("odometer")),
                            Boolean.parseBoolean(csvRecord.get("washerFluid")),
                            Boolean.parseBoolean(csvRecord.get("malfunctionIndicator")),
                            Integer.parseInt(csvRecord.get("batteryLevel")),
                            Boolean.parseBoolean(csvRecord.get("airConditioning")), languageConfiguration,
                            csvRecord.get("mirror"), Boolean.parseBoolean(csvRecord.get("childSafetyLock")),
                            csvRecord.get("topSpeedLimit"), climate, Boolean.parseBoolean(csvRecord.get("chime")));

                    vehicles.add(vehicle);
                    count = count + 1;
                }
           

        }} catch (Exception e) {
            log.error("Reading CSV Error!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (Exception e) {
                log.error("Closing fileReader/csvParser Error!");
                e.printStackTrace();
            }
        }

        return new Pair<>(count,vehicles);
    }


    public static boolean isCSVFile(MultipartFile file) {
        String extension = file.getOriginalFilename().split("\\.")[1];

        if (!extension.equals(csvExtension)) {
            return false;
        }

        return true;
    }

}
