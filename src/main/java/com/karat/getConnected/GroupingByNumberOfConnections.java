package com.karat.getConnected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupingByNumberOfConnections {

    public static void main(String[] args) {
        String[][] events = {
                {"CONNECT","Alice","Bob"},
                {"DISCONNECT","Bob","Alice"},
                {"CONNECT","Alice","Charlie"},
                {"CONNECT","Dennis","Bob"},
                {"CONNECT","Pam","Dennis"},
                {"DISCONNECT","Pam","Dennis"},
                {"CONNECT","Pam","Dennis"},
                {"CONNECT","Edward","Bob"},
                {"CONNECT","Dennis","Charlie"},
                {"CONNECT","Alice","Nicole"},
                {"CONNECT","Pam","Edward"},
                {"DISCONNECT","Dennis","Charlie"},
                {"CONNECT","Dennis","Edward"},
                {"CONNECT","Charlie","Bob"}
        };
        System.out.println(groupByConnection(events, 3));
    }

    private static List<List<String>> groupByConnection(String[][] events, int numberOfConnection) {
        List<List<String>> connections = new ArrayList<>();
        List<String> lessConnections = new ArrayList<>();
        List<String> moreConnections = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String[] event: events){
            String connection = event[0];
            String user1 = event[1];
            String user2 = event[2];
            if(connection.equals("CONNECT")){
                map.put(user1, map.getOrDefault(user1, 0) + 1);
                map.put(user2, map.getOrDefault(user2, 0) + 1);
            }else{
                map.put(user1, map.getOrDefault(user1, 0) - 1);
                map.put(user2, map.getOrDefault(user2, 0) - 1);
            }
        }
        for(var entrySet: map.entrySet()){
            if(entrySet.getValue() < numberOfConnection){
                lessConnections.add(entrySet.getKey());
            }else{
                moreConnections.add(entrySet.getKey());
            }
        }
        connections.add(lessConnections);
        connections.add(moreConnections);
        return connections;
    }
}
