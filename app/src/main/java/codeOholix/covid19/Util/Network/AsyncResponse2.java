package codeOholix.covid19.Util.Network;

public interface AsyncResponse2<String> {

    void onSuccess(String output);
    void onFailure(Exception e);
}