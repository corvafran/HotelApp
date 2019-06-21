package com.example.testhoteles

import android.content.Context
import com.example.testhoteles.data.model.Hotel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TestData {
    fun getTestHotel(): List<Hotel>{
        var gson = Gson()
        val listType = object : TypeToken<ArrayList<Hotel>>() {
        }.getType()

        var hotels = gson.fromJson<List<Hotel>>(hotels, listType)
        return hotels
    }
    const val hotels: String = "[\n" +
            "    {\n" +
            "        \"id\": \"364650\",\n" +
            "        \"stars\": 4,\n" +
            "        \"name\": \"DoubleTree by Hilton Ocean Pnt Rsrt & Spa North Miami Beach\",\n" +
            "        \"address\": \"17375 Collins Avenue\",\n" +
            "        \"main_picture\": \"http://ar.staticontent.com/media/pictures/7aca8010-51f6-4767-b484-e4c024df79ec\",\n" +
            "        \"rating\": 7.86,\n" +
            "        \"amenities\": [\n" +
            "          {\n" +
            "            \"id\": \"WIFI\",\n" +
            "            \"description\": \"WIFI gratis\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": \"PISCN\",\n" +
            "            \"description\": \"Piscina al Aire Libre (Todo el Año)\"\n" +
            "          }\n" +
            "        ],\n" +
            "      \"price\": {\n" +
            "        \"currency\": {\n" +
            "          \"code\": \"ARS\",\n" +
            "          \"mask\": \"\$\",\n" +
            "          \"ratio\": 0.1047668900394894\n" +
            "        },\n" +
            "        \"final_price\": false,\n" +
            "        \"base\": 2999,\n" +
            "        \"best\": 2999\n" +
            "      }\n" +
            "     \n" +
            "\n" +
            "},\n" +
            "{\n" +
            "    \"id\": \"520423\",\n" +
            "    \"stars\": 4,\n" +
            "    \"name\": \"Grand Beach Hotel Surfside\",\n" +
            "    \"address\": \"9449 Collins Avenue, Surfside, Miami Beach 33139, Florida United States\",\n" +
            "    \"main_picture\": \"http://ar.staticontent.com/media/pictures/1df13d77-bc9e-414d-9e3f-856bbd40cb5d\",\n" +
            "    \"rating\": 8.64,\n" +
            "    \"amenities\": [\n" +
            "      {\n" +
            "        \"id\": \"WIFI\",\n" +
            "        \"description\": \"WIFI gratis\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"PISCN\",\n" +
            "        \"description\": \"Piscina al Aire Libre (Todo el Año)\"\n" +
            "      }\n" +
            "    ],\n" +
            "  \"price\": {\n" +
            "    \"currency\": {\n" +
            "      \"code\": \"ARS\",\n" +
            "      \"mask\": \"\$\",\n" +
            "      \"ratio\": 0.1047668900394894\n" +
            "    },\n" +
            "    \"final_price\": false,\n" +
            "    \"base\": 4620,\n" +
            "    \"best\": 4620\n" +
            "  }\n" +
            "},\n" +
            "{\n" +
            "    \"id\": \"297729\",\n" +
            "    \"stars\": 3,\n" +
            "    \"name\": \"Hyatt house Miami Airport\",\n" +
            "    \"address\": \"5710 Blue Lagoon Dr\",\n" +
            "    \"main_picture\": \"http://ar.staticontent.com/media/pictures/8aa674a1-1190-4e7f-aee1-824317ce99b2\",\n" +
            "    \"rating\": 8.39,\n" +
            "    \"amenities\": [\n" +
            "      {\n" +
            "        \"id\": \"BREAKFST\",\n" +
            "        \"description\": \"Desayuno\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"WIFI\",\n" +
            "        \"description\": \"WIFI gratis\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"PARKING\",\n" +
            "        \"description\": \"Estacionamiento Gratis\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"PISCN\",\n" +
            "        \"description\": \"Piscina\"\n" +
            "      }\n" +
            "    ],\n" +
            "  \"price\": {\n" +
            "    \"currency\": {\n" +
            "      \"code\": \"ARS\",\n" +
            "      \"mask\": \"\$\",\n" +
            "      \"ratio\": 0.1047668900394894\n" +
            "    },\n" +
            "    \"final_price\": false,\n" +
            "    \"base\": 2186,\n" +
            "    \"best\": 2186\n" +
            "  }\n" +
            "\n" +
            "},\n" +
            "{\n" +
            "\n" +
            "    \"id\": \"298772\",\n" +
            "    \"stars\": 3,\n" +
            "    \"name\": \"Hampton Inn Miami Airport West\",\n" +
            "    \"address\": \"3620 NW 79th Avenue\",\n" +
            "    \"main_picture\": \"http://ar.staticontent.com/media/pictures/fe40b971-d1c8-4b6d-9303-b8cd0330e224\",\n" +
            "    \"rating\": 8.04,\n" +
            "    \"amenities\": [\n" +
            "      {\n" +
            "        \"id\": \"WIFI\",\n" +
            "        \"description\": \"WIFI gratis\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"PARKING\",\n" +
            "        \"description\": \"Estacionamiento Gratis\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"PISCN\",\n" +
            "        \"description\": \"Piscina al Aire Libre (Todo el Año)\"\n" +
            "      }\n" +
            "    ],\n" +
            "  \"price\": {\n" +
            "    \"currency\": {\n" +
            "      \"code\": \"ARS\",\n" +
            "      \"mask\": \"\$\",\n" +
            "      \"ratio\": 0.1047668900394894\n" +
            "    },\n" +
            "    \"final_price\": false,\n" +
            "    \"base\": 1453,\n" +
            "    \"best\": 1453\n" +
            "  }\n" +
            "}]"
}