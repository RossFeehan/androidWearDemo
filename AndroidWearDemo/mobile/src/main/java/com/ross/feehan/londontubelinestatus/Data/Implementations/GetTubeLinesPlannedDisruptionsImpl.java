package com.ross.feehan.londontubelinestatus.Data.Implementations;

import com.ross.feehan.londontubelinestatus.Data.Interfaces.GetTubeLinesPlannedDisruptionsInterface;
import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLinePlannedWork;
import com.ross.feehan.londontubelinestatus.Data.Retrofit.TflApiRetrofitInterface;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeLinesPlannedDisruptionsLogicInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public class GetTubeLinesPlannedDisruptionsImpl implements GetTubeLinesPlannedDisruptionsInterface{

    private static final String API_URL = "https://api.tfl.gov.uk";
    private TflApiRetrofitInterface tflApiRetrofitInterface;
    private Callback retrofitCallback;
    private GetTubeLinesPlannedDisruptionsLogicInterface listenerClass;
    private static final String NETWORKERROR = "NETWORK";

    //INTERFACE METHODS
    //GetTubeLinesPlannedDisruptionsInterface methods
    @Override
    public void getPlannedDisruptions(GetTubeLinesPlannedDisruptionsLogicInterface listenerClass, String startDate, String endDate) {
        this.listenerClass = listenerClass;

        setupRetrofit();

        //Make the api call, we use a callback so the api call will be async
        tflApiRetrofitInterface.getTubeLinePlannedDisruptions(startDate, endDate, retrofitCallback);
    }

    /*
    * Method to get Retrofit ready so the api calls can be made
    */
    private void setupRetrofit(){
        //set up the rest adapter and the methods it can call
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        //set up the interface to access the api calls
        tflApiRetrofitInterface = restAdapter.create(TflApiRetrofitInterface.class);

        //set up the retrofitCallback for when retrofit has finished communicating with the api
        retrofitCallback = setUpCallback();
    }

    /*Method that sets up the callback to be sent with the retrofit api request
    *Called when retrofit has finished communicating with api
    *The returned values from retrofit are returned to the calling class here
    */
    public Callback setUpCallback(){
        Callback callback = new Callback(){

            @Override
            public void success(Object o, Response response) {
                //Send the list of tube states back to the calling class
                List<TubeLinePlannedWork> tubesPlannedWork= (List<TubeLinePlannedWork>) o;
                listenerClass.receiveTubeLinesPlannedDisruptions(tubesPlannedWork);
            }

            @Override
            public void failure(RetrofitError error) {
                //Notify the calling class if there was a networking error
                if(error.getKind().equals(NETWORKERROR)){
                    listenerClass.noInternetConnection();
                }
                else{
                    //Notify the calling class that there was an error
                    listenerClass.somethingWentWrong("Sorry, something went wrong while fetching the tube line states");
                }
            }
        };
        return callback;
    }
}
