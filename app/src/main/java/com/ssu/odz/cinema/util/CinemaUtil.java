package com.ssu.odz.cinema.util;

import android.content.Context;

import com.google.firebase.example.fireeats.R;
import com.ssu.odz.cinema.model.Cinema;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CinemaUtil {

    private static final List<String> LIST_OF_CINEMA_IMAGES = List.of(
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-1.jpg?alt=media&token=3caec9de-7bd0-4c20-a5bf-653832b05b8c",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-10.jpg?alt=media&token=353a1ede-65f9-46b6-8cf4-078ae1ca25f1",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-11.jpg?alt=media&token=0e20da8b-0239-4e99-bf28-7179059630d5",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-12.jpg?alt=media&token=f92ff109-66d6-41d0-a73b-855d3a64ea99",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-2.jpg?alt=media&token=75f88d16-d740-4e86-87fa-b5b55691778c",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-3.jpg?alt=media&token=796b2492-fde0-4d5d-9674-9e23bd5e4163",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-4.jpg?alt=media&token=00561636-3d76-48e6-a919-cc0d4662dd6a",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-5.jpg?alt=media&token=3b215107-e055-4860-b40b-a24aabc41fad",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-6.jpg?alt=media&token=f9b50d00-1622-4771-b36c-197a5259198c",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-7.jpg?alt=media&token=507745a3-e908-4bcc-bde1-047ab9e07615",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-8.jpg?alt=media&token=5953383d-c2a1-423e-aaa1-6e3143787627",
            "https://firebasestorage.googleapis.com/v0/b/restrauntsrating.appspot.com/o/cinema-9.jpg?alt=media&token=0bea32e9-440d-441b-bc97-b02178fd08aa"
    );
    private static final int MAX_IMAGE_NUM = 12;

    private static final String[] NAME_FIRST_WORDS = {
            "Top",
            "Bar",
            "Random",
            "Qux",
            "AMC",
            "Planeta kino",
            "World Famous",
            "Google",
            "The Best",
    };

    private static final String[] NAME_SECOND_WORDS = {
            "Premium",
            "Luxury",
            "Default",
            "Indoor",
            "Outdoor",
            "Drive in",
            "Some other",
    };

    public static Cinema getRandom(Context context) {
        Cinema cinema = new Cinema();
        Random random = new Random();

        // Cities (first elemnt is 'Any')
        String[] cities = context.getResources().getStringArray(R.array.cities);
        cities = Arrays.copyOfRange(cities, 1, cities.length);

        // Categories (first element is 'Any')
        String[] categories = context.getResources().getStringArray(R.array.categories);
        categories = Arrays.copyOfRange(categories, 1, categories.length);

        int[] prices = new int[]{1, 2, 3};

        cinema.setName(getRandomName(random));
        cinema.setCity(getRandomString(cities, random));
        cinema.setCategory(getRandomString(categories, random));
        cinema.setPhoto(getRandomImageUrl(random));
        cinema.setPrice(getRandomInt(prices, random));
        cinema.setNumRatings(random.nextInt(20));

        // Note: average rating intentionally not set

        return cinema;
    }


    /**
     * Get a random image.
     */
    private static String getRandomImageUrl(Random random) {
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        int id = random.nextInt(MAX_IMAGE_NUM) + 1;

        return LIST_OF_CINEMA_IMAGES.get(id);
    }

    /**
     * Get price represented as dollar signs.
     */
    public static String getPriceString(Cinema cinema) {
        return getPriceString(cinema.getPrice());
    }

    /**
     * Get price represented as dollar signs.
     */
    public static String getPriceString(int priceInt) {
        switch (priceInt) {
            case 1:
                return "$";
            case 2:
                return "$$";
            case 3:
            default:
                return "$$$";
        }
    }

    private static String getRandomName(Random random) {
        return getRandomString(NAME_FIRST_WORDS, random) + " "
                + getRandomString(NAME_SECOND_WORDS, random);
    }

    private static String getRandomString(String[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

    private static int getRandomInt(int[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

}
