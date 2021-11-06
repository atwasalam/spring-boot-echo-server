package com.spring.echoserver.test.stepdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EchoServerTestStepDefs {

    CloseableHttpClient httpClient = HttpClients.createDefault();
    @When("User want to do rest API with Custom header")
    public void userWantToDoRestAPIWithCustomHeader() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/echo-server");
        // add request headers
        try {
            // add request headers
            request.addHeader("custom-key", "customValue");
            request.addHeader(HttpHeaders.USER_AGENT, "CustAgent");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
                System.out.println( response.getAllHeaders().toString());
                for (Header header:response.getAllHeaders()){
                    System.out.println(header.getName()+","+header.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    @Then("Custom Header Appear in Response")
    public void customHeaderAppearInResponse() {
    }
}
