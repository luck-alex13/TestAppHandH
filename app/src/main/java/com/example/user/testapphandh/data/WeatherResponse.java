package com.example.user.testapphandh.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class WeatherResponse {

    @SerializedName("id")
    @Expose
    private long cityId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private long visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private long dt;

    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherResponse() {
    }

    /**
     *
     * @param dt
     * @param clouds
     * @param coord
     * @param cityId
     * @param wind
     * @param visibility
     * @param name
     * @param base
     * @param weather
     * @param main
     */
    public WeatherResponse(long cityId, String name, Coord coord, List<Weather> weather, String base, Main main, long visibility, Wind wind, Clouds clouds, long dt) {
        super();
        this.cityId = cityId;
        this.name = name;
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long id) {
        this.cityId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cityId", cityId).append("name", name).append("coord", coord).append("weather", weather).append("base", base).append("main", main).append("visibility", visibility).append("wind", wind).append("clouds", clouds).append("dt", dt).toString();
    }

}