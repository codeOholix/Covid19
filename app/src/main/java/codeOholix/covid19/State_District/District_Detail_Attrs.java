package codeOholix.covid19.State_District;

public class District_Detail_Attrs {

    String district;
    String confirmed;

    public District_Detail_Attrs(String district,String confirmed)
    {
        this.district=district;
        this.confirmed=confirmed;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

}
