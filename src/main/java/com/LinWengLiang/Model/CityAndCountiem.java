package com.LinWengLiang.Model;

/**
 * @author linwengliang
 * @Title: CityAndCounti
 * @Description: TODO
 * @date 2020/1/33:40 PM
 */
public class CityAndCountiem {
    private String cityName;
    private String count;

    public void set(String cityName, String count) {
        this.cityName = cityName;
        this.count = count;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
