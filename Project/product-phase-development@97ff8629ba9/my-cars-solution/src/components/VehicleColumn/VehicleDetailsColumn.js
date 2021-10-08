import React from 'react';
import { Link } from 'react-router-dom';
export default function VehicleDetailsColumn({ vehicle }) {
    var vehicleName = vehicle.brand + ' ' + vehicle.model;
    var vehicleFuelType = '';

    const addFuelType = () => {
        for (var index = 0; index < vehicle.fuelType.length; index++) {
            vehicleFuelType += vehicle.fuelType[index];
            if (index !== vehicle.fuelType.length - 1) {
                vehicleFuelType += ', ';
            }
        }
        return <>{vehicleFuelType}</>;
    };

    const checkIfNull = (value)=>{
        if(value==null){
            value = " - - "
        }
        return <>{value}</>;   
    }


    const checkBool = (valueToCheck) => {
        return (
            <>
                {valueToCheck && (
                    <svg
                        xmlns='http://www.w3.org/2000/svg'
                        width='16'
                        height='16'
                        fill='green'
                        className='bi bi-check-lg'
                        viewBox='0 0 16 16'
                    >
                        <path d='M13.485 1.431a1.473 1.473 0 0 1 2.104 2.062l-7.84 9.801a1.473 1.473 0 0 1-2.12.04L.431 8.138a1.473 1.473 0 0 1 2.084-2.083l4.111 4.112 6.82-8.69a.486.486 0 0 1 .04-.045z' />
                    </svg>
                )}

                {!valueToCheck && (
                    <svg
                        xmlns='http://www.w3.org/2000/svg'
                        width='16'
                        height='16'
                        fill='red'
                        className='bi bi-x-lg'
                        viewBox='0 0 16 16'
                    >
                        <path d='M1.293 1.293a1 1 0 0 1 1.414 0L8 6.586l5.293-5.293a1 1 0 1 1 1.414 1.414L9.414 8l5.293 5.293a1 1 0 0 1-1.414 1.414L8 9.414l-5.293 5.293a1 1 0 0 1-1.414-1.414L6.586 8 1.293 2.707a1 1 0 0 1 0-1.414z' />
                    </svg>
                )}
            </>
        );
    };

    return (
        <div>
            <table className='table table-borderless  text-center'>
                <tbody>
                    <tr>
                        <td className='h5'>
                            <Link to={'/vehicles/' + vehicle.id}>
                                {vehicleName}
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td>{vehicle.brand}</td>
                    </tr>

                    <tr>
                        <td>
                            <span>&#8377;</span> &nbsp;
                            {vehicle.price}/-
                        </td>
                    </tr>

                    <tr>
                        <td>{vehicle.vehicleType}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.year}</td>
                    </tr>

                    <tr>
                        <td>{addFuelType()}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.airConditioning)}</td>
                    </tr>
                </tbody>
            </table>
            <table className='table table-borderless  text-center'>
                <tbody>
                    <tr>
                        <td className='h5'>----------------</td>
                    </tr>
                    <tr>
                        <td>{vehicle.topSpeedLimit}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.unitsFuelConsumption}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.tankCapacity}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.wheelSpeed}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.ignitionTime}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.accelaration}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.powerTrainTorque}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.batteryLevel}</td>
                    </tr>
                </tbody>
            </table>
            <table className='table table-borderless  text-center'>
                <tbody>
                    <tr>
                        <td className='h5'>----------------</td>
                    </tr>
                    <tr>
                        <td>{checkBool(vehicle.theftAlarm)}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.airBagCount}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.nightMode)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.childSafetyLock)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.malfunctionIndicator)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.isABS)}</td>
                    </tr>
                </tbody>
            </table>
            <table className='table table-borderless  text-center'>
                <tbody>
                    <tr>
                        <td className='h5'>----------------</td>
                    </tr>
                    <tr>
                        <td>{vehicle.mediaInterface}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.tripMeter}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.transmissionGearType}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.steeringWheelType}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.horn}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.wheelRadius}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.odometer)}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.mirror}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.washerFluid)}</td>
                    </tr>

                    <tr>
                        <td>{vehicle.languageConfiguration}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.chime)}</td>
                    </tr>
                </tbody>
            </table>
            <table className='table table-borderless  text-center'>
                <tbody>
                    <tr>
                        <td className='h5'>----------------</td>
                    </tr>
                    <tr>
                        <td>{checkBool(vehicle.light.fog)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.light.hazard)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.light.parking)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.light.dynamicHighBeam)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.light.automaticHeadlights)}</td>
                    </tr>
                </tbody>
            </table>
            <table className='table table-borderless  text-center'>
                <tbody>
                    <tr>
                        <td className='h5'>----------------</td>
                    </tr>
                    <tr>
                        <td>{checkBool(vehicle.climate.climateControl)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.climate.sunroof)}</td>
                    </tr>

                    <tr>
                        <td>{checkBool(vehicle.climate.defrost)}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
  };