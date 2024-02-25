package com.cric.apis.crickinfoBackend.services;

import com.cric.apis.crickinfoBackend.Entities.Cricket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MatchService {

    //get All Matches
    List<Cricket> getAllMatches();

    //getLive matches
    List<Cricket> getLiveMatches();


    // get point Table
    List<List<String>>getPointTable() ;
}
