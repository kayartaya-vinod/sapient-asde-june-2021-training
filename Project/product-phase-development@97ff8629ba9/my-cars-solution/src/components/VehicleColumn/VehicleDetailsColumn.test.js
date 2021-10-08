import {  screen } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import VehicleDetailsColumn from "./VehicleDetailsColumn";
 

const vehicleData = {
          id: "vehicle123",
          dealerId: "4sad46d64zc",
          tankCapacity: "10L",
          mediaInterface: "buttons",
          theftAlarm: true,
          pictureUrls: [
            "https://imgd.aeplcdn.com/600x337/n/z3amk4a_1524181.jpg?q=85",
            "https://o.aolcdn.com/images/dims3/GLOB/legacy_thumbnail/800x450/format/jpg/quality/85/https://s.aolcdn.com/os/ab/_cms/2021/04/19140802/Honda-SUV-e-prototype-Shanghai-2021-01.jpg",
          ],
          tripMeter: "analog",
          airBagCount: "5",
          description: "This is a car",  
          price: "2700000",
          color: "red",
          vin: "12234",
          brand: "Honda",
          model: "C100",
          year: "2010",
          vehicleType: "SUV",
          fuelType: ["diesel", "petrol"],
          unitsFuelConsumption: "15km/l",
          transmissionGearType: "auto",
          steeringWheelType: "power",
          vehicleSpeed: "100km/h",
          horn: "air horn",
          powerTrainTorque: "100Nm",
          accelaration: "10m/s2",
          nightMode: true,
          isABS: true,
          wheelRadius: "50cm",
          wheelSpeed: "10km/h",
          light: {
            fog: true,
            hazard: true,
            parking: true,
            dynamicHighBeam: true,
            automaticHeadlights: true,
          },
          ignitionTime: "5s",
          odometer: true,
          washerFluid: true,
          malfunctionIndicator: true,
          batteryLevel: 40,
          airConditioning: false,
          languageConfiguration: "English, Hindi",
          mirror: "automatic",
          childSafetyLock: true,
          topSpeedLimit: "100km/h",
          climate: {
            climateControl: true,
            sunroof: true,
            defrost: true,
          },
          chime: true,
        }
describe("VehicleDetailsColumn test",()=>{
    it("should render VehicleDetailsColumn",async ()=>{
         
        renderWithReduxAndRouter(<VehicleDetailsColumn vehicle={vehicleData}/>);
        
           expect(screen.getAllByText(/honda c100/i)).toBeInTheDocument;
           expect(screen.getAllByText(/diesel, petrol/i)).toBeInTheDocument;
         
 
    });
});