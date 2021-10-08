import { fireEvent, screen ,waitFor} from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import VehicleComparisonMatrix from "./VehicleComparisonMatrix";

import AddToCompare from "../AddToCompare/AddToCompare";
import axiosMock from "axios";

jest.mock("axios"); 

describe("VehicleComparisonMatrix test", () => {
   xit("should render empty VehicleComparisonMatrix", () => {
     renderWithReduxAndRouter(<VehicleComparisonMatrix />);

   });
   
  xit("should render VehicleComparisonMatrix", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: {
          id: "vehicle123",
          dealerId: "4sad46d64zc",
          tankCapacity: "10L",
          mediaInterface: "buttons",
          theftAlarm: true,
          pictureUrls: [
            "https://imgd.aeplcdn.com/600x337/n/z3amk4a_1524181.jpg?q=85",
            "https://o.aolcdn.com/images/dims3/GLOB/legacy_thumbnail/800x450/format/jpg/quality/85/https://s.aolcdn.com/os/ab/_cms/2021/04/19140802/Honda-SUV-e-prototype-Shanghai-2021-01.jpg",
            "https://m.hondacarindia.com/content/mobile/images/home_banner/allnewamaze.jpg",
            "https://m.hondacarindia.com/content/mobile/images/home_banner/allnewamaze.jpg",
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
          airConditioning: true,
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
        },
      },
    }); 
    await waitFor(() => {
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id1"} />);
    });
      const checkBox = screen.getByTestId("addToCompareid1");
      fireEvent.click(checkBox);
      
       

    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: {
          id: "vehicle123",
          dealerId: "4sad46d64zc",
          tankCapacity: "10L",
          mediaInterface: "buttons",
          theftAlarm: true,
          pictureUrls: [
            "https://imgd.aeplcdn.com/600x337/n/z3amk4a_1524181.jpg?q=85",
            "https://o.aolcdn.com/images/dims3/GLOB/legacy_thumbnail/800x450/format/jpg/quality/85/https://s.aolcdn.com/os/ab/_cms/2021/04/19140802/Honda-SUV-e-prototype-Shanghai-2021-01.jpg",
            "https://m.hondacarindia.com/content/mobile/images/home_banner/allnewamaze.jpg",
            "https://m.hondacarindia.com/content/mobile/images/home_banner/allnewamaze.jpg",
          ],
          tripMeter: "analog",
          airBagCount: "5",
          description: "This is a car",
          price: "2700000",
          color: "red",
          vin: "12234",
          brand: "Suzuki",
          model: "Baleno",
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
          airConditioning: true,
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
        },
      },
    }); 
    await waitFor(() => {
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id2"} />);
    });
      const checkBox2 = screen.getByTestId("addToCompareid2");
      fireEvent.click(checkBox2);
    


    renderWithReduxAndRouter(<VehicleComparisonMatrix />);

     await waitFor(() => {
       expect(screen.getByText(/honda-c100 vs suzuki-baleno/i)).toBeInTheDocument;
     });
  });
 
});
