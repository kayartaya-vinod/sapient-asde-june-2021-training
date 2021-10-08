import EditVehicleDetails from "./EditVehicleDetails";
import {
  render,
  fireEvent,
  act,
  screen,
  waitFor,
  cleanup,
  getByLabelText,
} from "@testing-library/react";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import axiosMock from "axios";
import { renderWithRouter } from "../../utils/testUtils";

jest.mock("axios");
const id = 1;
describe("Edit Vehicle Details tests", () => {
  const origConsole = console.error;

  const history = createMemoryHistory();

  beforeEach(() => {
    console.error = jest.fn();
  });

  afterEach(() => {
    console.error = origConsole;
    cleanup();
  });

  it("Should render the component", async () => {
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
    const { getByTestId } = render(
      <Router history={history}>
        <EditVehicleDetails />
      </Router>
    );
    await waitFor(() => {
      expect(getByTestId("vehicle-details1")).toBeInTheDocument();
      expect(screen.getByText(/Model/i)).toBeInTheDocument();
      expect(screen.getByText(/Color/i)).toBeInTheDocument;
      expect(screen.getByTestId("more-specification")).toBeInTheDocument;
      expect(screen.getByTestId("sunroof")).toBeInTheDocument;
      expect(screen.getByTestId("fog")).toBeInTheDocument;
      expect(screen.getByTestId("climateControl")).toBeInTheDocument;
      expect(screen.getByText(/Child Lock/i)).toBeInTheDocument;
    });
  });

  it("Testing submit button", async () => {
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
    axiosMock.put.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });
    act(() => {
      const { getByTestId } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      fireEvent.click(screen.getByTestId("update-vehicleDetails-test"));
    });
    await waitFor(() => {
      expect(screen.getByTestId("update-vehicleDetails-test")).not.toBeNull;
    });
  });

  it("Testing update", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: {
          id: "vehicle123",
          dealerId: "4sad46d64zc",
          tankCapacity: "10L",
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
          airConditioning: true,
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
    axiosMock.put.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });
    act(() => {
      const { getByTestId } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      fireEvent.click(screen.getByTestId("update-vehicleDetails-test"));
    });
    await waitFor(() => {
      expect(screen.getByTestId("update-vehicleDetails-test")).not.toBeNull;
    });
  });

  it("Testing success false", async () => {
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
    axiosMock.put.mockResolvedValueOnce({
      data: {
        success: false,
      },
    });
    act(() => {
      const { getByTestId } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      fireEvent.click(screen.getByTestId("update-vehicleDetails-test"));
    });
    await waitFor(() => {
      expect(screen.getByTestId("update-vehicleDetails-test")).not.toBeNull;
    });
  });

  it("Testing handle fuel", async () => {
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

    act(() => {
      const { getByTestId } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      fireEvent.click(screen.getByTestId("handle-fuel"));
      fireEvent.click(screen.getByTestId("handle-fuel1"));
    });
    await waitFor(() => {
      expect(screen.getByTestId("handle-fuel")).not.toBeNull;
    });
  });

  it("Testing plus button", async () => {
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
    act(() => {
      const { getByText } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      const doneBtn = screen.getByTestId("doneBtn");
      expect(doneBtn).toBeInTheDocument();

      const firstInputBox = screen.getByTestId("Testinput-0");
      fireEvent.change(firstInputBox, { target: { value: "testing" } });
      fireEvent.blur(firstInputBox);
      fireEvent.click(doneBtn);
    });
  });

  it("Testing climate control radio button", async () => {
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
          mirror: "automatic",
          childSafetyLock: true,
          topSpeedLimit: "100km/h",
          climate: {
            climateControl: false,
            sunroof: true,
            defrost: true,
          },
          chime: true,
        },
      },
    });
    act(() => {
      const { getByTestId } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      let radioRespNo = screen.getByTestId("climateControlNo");
      expect(radioRespNo).toBeChecked();
      let radioRespYes = screen.getByTestId("climateControlYes");
      expect(radioRespYes).not.toBeChecked();
      fireEvent.click(radioRespYes);
      expect(radioRespYes).toBeChecked();
    });
  });

  it("Testing light radio", async () => {
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
          mirror: "automatic",
          childSafetyLock: true,
          topSpeedLimit: "100km/h",
          climate: {
            climateControl: false,
            sunroof: true,
            defrost: true,
          },
          chime: true,
        },
      },
    });
    act(() => {
      const { getByTestId } = renderWithRouter(<EditVehicleDetails {...id} />);
    });
    await waitFor(() => {
      let radioResp = screen.getByTestId("handle-light");
      fireEvent.click(radioResp);
      expect(radioResp).toBeChecked();
      fireEvent.click(radioResp);
      expect(radioResp).toBeChecked();
      radioResp = screen.getByTestId("handle-light-yes");
      fireEvent.click(radioResp);
      expect(radioResp).toBeChecked();
    });
  });
});

describe("Please Wait", () => {
  it("renders without crashing", () => {
    window.scrollTo = jest.fn();
  });
});
