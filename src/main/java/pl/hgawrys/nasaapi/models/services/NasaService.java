package pl.hgawrys.nasaapi.models.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.hgawrys.nasaapi.models.AsteroidModel;
import pl.hgawrys.nasaapi.models.PhotoModel;
import pl.hgawrys.nasaapi.models.Utils;

import java.util.LinkedList;
import java.util.List;

public class NasaService {
    private static NasaService ourInstance = new NasaService();

    public static NasaService getInstance() {
        return ourInstance;
    }

    private NasaService() {
    }

    public PhotoModel getPictureOfTheDay() {
        try {
            String nasaResponse = Utils.readNasaPictureApiContent("https://api.nasa.gov/planetary/apod?&api_key=DEMO_KEY");
            String title;
            String description;
            String url;

            JSONObject root = new JSONObject(nasaResponse);
            title = root.getString("title");
            description = root.getString("explanation");
            url = root.getString("url");

            return new PhotoModel.Builder()
                    .setTitle(title)
                    .setDescription(description)
                    .setUrl(url)
                    .build();
        } catch (JSONException e) {
            return new PhotoModel.Builder()
                    .setTitle("")
                    .setDescription("")
                    .setUrl("")
                    .build();
        }
    }

    public List<AsteroidModel> getAsteroids(String date) {
        List<AsteroidModel> asteroidModelList = new LinkedList<>();
        try {
            String nasaResponse = Utils.readNasaPictureApiContent("https://api.nasa.gov/neo/rest/v1/feed?start_date=" + date + "&end_date=" + date + "&api_key=DEMO_KEY");
            int count = 0;
            int number;
            String name;
            double distance = 0;
            double speed = 0;
            boolean hazardous;
            double avgDiameter;

            JSONObject root = new JSONObject(nasaResponse);
            count = root.getInt("element_count");
            if (!String.valueOf(count).equals("0")) {
                JSONObject nearEarthObjects = root.getJSONObject("near_earth_objects");
                JSONArray specificDate = nearEarthObjects.getJSONArray(date);
                for (int i = 0; i < count; i++) {
                    number = i;
                    JSONObject someAsteroid = specificDate.getJSONObject(i);
                    name = someAsteroid.getString("name");
                    JSONObject estimatedDiameter = someAsteroid.getJSONObject("estimated_diameter");
                    JSONObject kilometers = estimatedDiameter.getJSONObject("kilometers");
                    avgDiameter = (kilometers.getDouble("estimated_diameter_min")
                            + kilometers.getDouble("estimated_diameter_max")) / 2;
                    hazardous = someAsteroid.getBoolean("is_potentially_hazardous_asteroid");

                    JSONArray closeApproachData = someAsteroid.getJSONArray("close_approach_data");
                    for (int j = 0; j < closeApproachData.length(); j++) {
                        JSONObject specificApproachData = closeApproachData.getJSONObject(j);
                        JSONObject relativeVelocity = specificApproachData.getJSONObject("relative_velocity");
                        speed = relativeVelocity.getDouble("kilometers_per_hour");
                        JSONObject missDistance = specificApproachData.getJSONObject("miss_distance");
                        distance = missDistance.getDouble("kilometers");
                    }

                    asteroidModelList.add(new AsteroidModel.Builder()
                        .setNumber(number)
                        .setName(name)
                        .setAvgDiamater(avgDiameter)
                        .setDistance(distance)
                        .setHazardous(hazardous)
                        .setSpeed(speed)
                        .build());
                }
            }
        } catch (JSONException e) {
            asteroidModelList.add(new AsteroidModel.Builder()
                    .setNumber(-999)
                    .setName("")
                    .setAvgDiamater(0)
                    .setDistance(0)
                    .setHazardous(false)
                    .setSpeed(0)
                    .build());
        }
        if (asteroidModelList.isEmpty()) {
            asteroidModelList.add(new AsteroidModel.Builder()
                    .setNumber(0)
                    .setName("none")
                    .setAvgDiamater(0)
                    .setDistance(0)
                    .setHazardous(false)
                    .setSpeed(0)
                    .build());
        }
        return asteroidModelList;
    }
}
