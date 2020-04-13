package codeOholix.covid19.Home;

import lecho.lib.hellocharts.model.LineChartData;

public class Horizontal_Card_Attr {

    public String title;
    public String count;

    public LineChartData data;

    public LineChartData getData() {
        return data;
    }

    public void setData(LineChartData data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
