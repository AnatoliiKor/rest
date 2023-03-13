package com.rest.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("INPUT JSON = " + input + "; Function Name = " + context.getFunctionName());
        logger.log("Parse body as string");

        try {
            String inputBody = (String) input.get("body");
            logger.log("INPUT Body = " + inputBody);
            JSONObject jsonBody = new JSONObject(inputBody);
            String urlPath = jsonBody.getString("url");
            logger.log("INPUT URL = " + urlPath);
            URL url = new URL(urlPath);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(jsonBody.getString("method"));
            int status = con.getResponseCode();
            Reader streamReader;
            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }
            BufferedReader in = new BufferedReader(streamReader);
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            String response = "RESPONSE: Status code = " + status + ". Body = " + content.toString();
            logger.log(response);
            return response;
        } catch (IOException e) {
            logger.log(e.getMessage());
            return e.getMessage();
        }
    }
}
