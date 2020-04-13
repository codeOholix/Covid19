package codeOholix.covid19.Util.Network;


public interface AsyncResponse3<String> {

    void onSuccess(String output);
    void onFailure(Exception e);
}