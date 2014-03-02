package com.meet.ahha;

/**
 * Created by tommy on 3/1/14.
 */
public class Car {
    String Year;
    String Manufacturer;
    String Model;
    String Vehicle_Class;
    float Engine_Size;
    int Cylinder;
    String Transmission;
    String FuelType;
    float City_Fuel_Consumption;
    float HWY_Fuel_Consumption;
    int Fuel_Per_Year;
    int CO2Emission;

    public Car (String year, String manufacturer, String model, String vehicle_Class, float engine_Size, int cylinder, String transmission,
                String fuelType, float city_Fuel_Consumption, float hwy_Fuel_Consumption, int fuel_Per_Year, int co2Emission)
    {
        this.Year=year;
        this.Manufacturer=manufacturer;
        this.Model=model;
        this.Vehicle_Class=vehicle_Class;
        this.Engine_Size=engine_Size;
        this.Cylinder=cylinder;
        this.Transmission=transmission;
        this.FuelType=fuelType;
        this.City_Fuel_Consumption=city_Fuel_Consumption;
        this.HWY_Fuel_Consumption=hwy_Fuel_Consumption;
        this.Fuel_Per_Year=fuel_Per_Year;
        this.CO2Emission=co2Emission;
    }
    //why can't java create getters and setters as easy as C#?

    String getYear()
    {
        return this.Year;
    }
    String getManufacturer()
    {
        return this.Manufacturer;
    }
    String getModel()
    {
        return this.Model;
    }
    String getVehicle_Class()
    {
        return this.Vehicle_Class;
    }
    float getEngine_size ()
    {
        return this.Engine_Size;
    }
    int getCylinder ()
    {
        return this.Cylinder;
    }
    String getTransmission ()
    {
        return this.Transmission;
    }
    String getFuelType ()
    {
        return this.FuelType;
    }
    float getCity_FuelConsumption()
    {
        return this.City_Fuel_Consumption;
    }
    float getHWY_FuelConsumption()
    {
        return this.HWY_Fuel_Consumption;
    }
    int getFuel_Per_Year()
    {
        return this.Fuel_Per_Year;
    }
    int getCO2Emission()
    {
        return this.CO2Emission;
    }


}
