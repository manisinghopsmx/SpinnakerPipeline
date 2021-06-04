package com.spinnaker.Pipeline;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SpringBootApplication
public class PipelineApplication {
	private final String USER_AGENT = "Chrome/91.0.4472.77";
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PipelineApplication.class, args);
		PipelineApplication http = new PipelineApplication();
		http.sendingPostRequest(args);
	}

	private String sendingGetRequest(String pipelineName, String appName) throws Exception {
		String urlString = "http://localhost:8084/applications/"+appName+"/pipelineConfigs/"+pipelineName;
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("Sending get request : "+ url);
		System.out.println("Response code : "+ responseCode);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();
		Map<String, Object> retMap = new Gson().fromJson(
				response.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
		);
		return retMap.get("id").toString();

	}
	private void sendingPostRequest(String args[]) throws Exception {
		for(int j=1;j<args.length;j++){
			String id = sendingGetRequest(args[j], args[0]);
			for (int i = 2; i <= 12; i++) {
				File file = new File("piplineJson.json");
				try {
					JSONParser parser = new JSONParser();
					String name = i * 5 + "secTry"+j;
					JSONObject jsondata = (JSONObject) parser.parse(new FileReader(file.getAbsolutePath()));
					jsondata.put("name", name);
					String dataString = jsondata.toString();
					dataString.replace("728e434e-063b-4af5-86dd-3b915ccd36ae", id);
					dataString.replace("maniapp",args[0]);
					JSONParser parserString = new JSONParser();
					JSONObject data = (JSONObject) parser.parse(dataString);
					String url = "http://localhost:8084/pipelines";
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("User-Agent", USER_AGENT);
					con.setRequestProperty("Accept-Language", "en-IN,en-GB;q=0.9");
					con.setRequestProperty("Accept", "application/json");
					con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
					con.setDoOutput(true);
					String postJsonData = data.toJSONString();
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(postJsonData);
					wr.flush();
					wr.close();
					int responseCode = con.getResponseCode();
					String output = con.getResponseMessage();
					System.out.println(responseCode);
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String out;
					StringBuffer response = new StringBuffer();
					while ((out = in.readLine()) != null) {
						response.append(out);
					}
					in.close();
					String responseString = response.toString();
					System.out.println(responseString);
					if (responseCode == 200) {
						id = sendingGetRequest(name,args[0]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
