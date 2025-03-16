package com.karat.getConnected;

import java.util.*;

/**
One of the fun features of Aquaintly is that users can rate movies they have seen from 1 to 5. We want to use these ratings to make movie recommendations.

Ratings will be provided in the following format:
  [Member Name, Movie Name, Rating]

We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.

A movie should be recommended to a user if:
- They haven't rated the movie
- A user with similar taste has rated the movie as 4 or 5

Example:
ratings = [
    ["Alice", "Frozen", "5"],
    ["Bob", "Mad Max", "5"],
    ["Charlie", "Lost In Translation", "4"],
    ["Charlie", "Inception", "4"],
    ["Bob", "All About Eve", "3"],
    ["Bob", "Lost In Translation", "5"],
    ["Dennis", "All About Eve", "5"],
    ["Dennis", "Mad Max", "4"],
    ["Charlie", "Topsy-Turvy", "2"],
    ["Dennis", "Topsy-Turvy", "4"],
    ["Alice", "Lost In Translation", "1"],
    ['Franz', 'Lost In Translation', '5'],
    ['Franz', 'Mad Max', '5']
]

If we want to recommend a movie to Charlie, we would recommend "Mad Max" because:
- Charlie has not rated "Mad Max"
- Charlie and Bob have similar taste as they both rated "Lost in Translation" 4 or 5
- Bob rated "Mad Max" a 5

Write a function that takes the name of a user and a collection of ratings, and returns a collection of all movie recommendations that can be made for the given user.

All test cases:
recommendations("Charlie", ratings) => ["Mad Max"]
recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
recommendations("Dennis", ratings) => ["Lost In Translation"]
recommendations("Alice", ratings) => []
recommendations("Franz", ratings) => ["Inception", "All About Eve", "Topsy-Turvy"]

Complexity Variable:
R = number of ratings
M = number of movies
U = number of users
 */
public class MovieRecommendations {

    public static void main(String[] args) {
        String[][] ratings = {
                {"Alice", "Frozen", "5"},
                {"Bob", "Mad Max", "5"},
                {"Charlie", "Lost In Translation", "4"},
                {"Charlie", "Inception", "4"},
                {"Bob", "All About Eve", "3"},
                {"Bob", "Lost In Translation", "5"},
                {"Dennis", "All About Eve", "5"},
                {"Dennis", "Mad Max", "4"},
                {"Charlie", "Topsy-Turvy", "2"},
                {"Dennis", "Topsy-Turvy", "4"},
                {"Alice", "Lost In Translation", "1"},
                {"Franz", "Lost In Translation", "5"},
                {"Franz", "Mad Max", "5"}
        };
        System.out.println(movieReccomendation(ratings, "Charlie"));
        System.out.println(movieReccomendation(ratings, "Bob"));
        System.out.println(movieReccomendation(ratings, "Dennis"));
        System.out.println(movieReccomendation(ratings, "Alice"));
        System.out.println(movieReccomendation(ratings, "Franz"));
    }

    private static Set<String> movieReccomendation(String[][] ratings, String user) {
        Set<String> recommendedMovies = new HashSet<>();
        Map<String, Set<String>> userToMoviesMap = new HashMap<>();
        Map<String, Set<String>> movieToUserMap = new HashMap<>();
        Set<String> existingUserMovies = new HashSet<>();
        for(var rating: ratings){
            int rate = Integer.parseInt(rating[2]);
            if(rate >3 ){
                userToMoviesMap.computeIfAbsent(rating[0], k ->new HashSet<>()).add(rating[1]);
                movieToUserMap.computeIfAbsent(rating[1], k ->new HashSet<>()).add(rating[0]);
            }
            if(rating[0].equals(user)){
                existingUserMovies.add(rating[1]);
            }
        }
//        System.out.println(userToMoviesMap);
//        System.out.println(movieToUserMap);
        Set<String> movies = userToMoviesMap.get(user);
        for(String movie: movies){
            Set<String> users = movieToUserMap.getOrDefault(movie, new HashSet<>());
//            System.out.println("users:"+users+" movie:"+movie);
            for(var existingUser:  users){
                Set<String> newUserMovies = userToMoviesMap.get(existingUser);
                for(String newUserMovie: newUserMovies){
                    if(!existingUser.equals(user) && !existingUserMovies.contains(newUserMovie)){
                        recommendedMovies.add(newUserMovie);
                    }
                }
            }
        }

        return recommendedMovies;
    }
}
