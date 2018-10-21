import java.net.*

public class APIWrapper {
    public static final String HOST = "35.231.12.22";

    public static final int PORT = 8080;

    public static final String URL = HOST + ":" + PORT;

    public static int getQueueSize() {
        URL url = new URL(URL + "/rr/size");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                  new InputStreamReader(con.getInputStream()));
        con.disconnect();

        if (status == 200) {
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject reader = new JSONObject(inputLine);
            return reader.getInt("response");
        } else
            return -1;
    }

    public static int getQueueStatus(String token) {
        URL url = new URL(URL + "/rr/status/" + token);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                  new InputStreamReader(con.getInputStream()));
        con.disconnect();

        if (status == 200) {
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject reader = new JSONObject(inputLine);
            return reader.getInt("response");
        } else
            return -1;
    }

    public static boolean enqueue(String token) {
        URL url = new URL(URL + "/rr/dequeue");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        JSONObject json = new JSONObject();
        json.put("response", token);

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(json.toString());
        out.flush();
        out.close()

        con.setRequestMethod("POST");

        return con.getResponseCode() == 200;
    }

    public static boolean enqueue(String token) {
        URL url = new URL(URL + "/rr/enqueue");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        JSONObject json = new JSONObject();
        json.put("response", token);

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(json.toString());
        out.flush();
        out.close()

        con.setRequestMethod("POST");

        return con.getResponseCode() == 200;
    }

    public static String getSeat(String token) {
        URL url = new URL(URL + "/seats/" + token);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                  new InputStreamReader(con.getInputStream()));
        con.disconnect();

        if (status == 200) {
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject reader = new JSONObject(inputLine);
            return reader.getString("response");
        } else
            return null;
    }
}
