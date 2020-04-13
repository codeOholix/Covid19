package codeOholix.covid19.Util.Network;


public interface AsyncResponse1<String> {

    void onSuccess(String output);
    void onFailure(Exception e);
}