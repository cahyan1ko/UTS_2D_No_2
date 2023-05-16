package UTS_2D;

import org.json.*;

import java.io.*;
import java.util.Comparator;

public class No_2 {
    public static void main(String[] args) {
        try {
            File jsonSaya = new File("src/brazil_club.json");
            BufferedReader br = new BufferedReader(new FileReader(jsonSaya));

            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            br.close();
            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            JSONArray clubsArray = jsonObject.getJSONArray("clubs");
            Club[] clubs = new Club[clubsArray.length()];
            for (int i = 0; i < clubsArray.length(); i++) {
                JSONObject clubObject = clubsArray.getJSONObject(i);
                String name = clubObject.getString("name");
                String code = clubObject.optString("code", null);
                String country = clubObject.getString("country");
                clubs[i] = new Club(name, code, country);
            }
            bubbleSort(clubs);
            for (Club club : clubs) {
                System.out.println(club);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    private static void bubbleSort(Club[] clubs) {
        int n = clubs.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clubs[j].getName().compareTo(clubs[j + 1].getName()) > 0) {
                    // Tukar klub
                    Club temp = clubs[j];
                    clubs[j] = clubs[j + 1];
                    clubs[j + 1] = temp;
                }
            }
        }
    }
    static class Club {
        private String name;
        private String code;
        private String country;
        public Club(String name, String code, String country) {
            this.name = name;
            this.code = code;
            this.country = country;
        }
        public String getName() {
            return name;
        }
        public String getCode() {
            return code;
        }
        public String getCountry() {
            return country;
        }
        @Override
        public String toString() {
            return  "Club{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
