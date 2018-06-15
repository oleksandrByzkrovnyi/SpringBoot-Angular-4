package com.ua.nure.TestHelper.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {


    public List<String> getCroppImages(MultipartFile originalImage) throws IOException {
        List<String> pathes = new ArrayList<>();
        int x = 50;
        int y = 141;
        BufferedImage image = ImageIO.read(originalImage.getInputStream());
        for (int i = 1; i <= 12; i++) {
            if (i == 7) {
                x = 406;
                y = 141;
            }
            String path = "C:\\Users\\Student-Alexandr\\Desktop\\non security\\PP-master\\TestHelperSpring\\src\\main\\resources\\images\\" + i + ".jpg";
            RenderedImage cropped = image.getSubimage(x, y, 279, 72);
            File savedFile = new File(path);
            ImageIO.write(cropped, "jpg", savedFile);
            y += 72;
            pathes.add(path);
        }
        return pathes;
    }
    public void getImage(MultipartFile or) throws IOException{
        BufferedImage image = ImageIO.read(or.getInputStream());
        System.out.println(Arrays.toString(image.getPropertyNames()));
    }

    private String sendRequest(String path) throws IOException {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://salty-retreat-16089.herokuapp.com/https://gateway.watsonplatform.net/visual-recognition/api/v3/classify?version=2018-03-19");
        String token = "apikey:T_xWLaWa5FUlmzuDZfjiengN7rwVwTNmbQMGaYOloGcX";
        String apiKey = new String(new Base64().encode(token.getBytes()));

        httppost.addHeader(HttpHeaders.ACCEPT, "application/json");
        httppost.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + apiKey);
        httppost.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "en");
        httppost.addHeader("origin", "*");

        File file = new File(path);

        FileBody image = new FileBody(file);
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("classifier_ids", new StringBody("DefaultCustomModel_254176265"));
        reqEntity.addPart("images_file", image);

        httppost.setEntity(reqEntity);

        HttpResponse response = httpclient.execute(httppost);
        //System.out.println(response);

        HttpEntity resEntity = response.getEntity();
        // System.out.println(resEntity); System.out.println(EntityUtils.toString(resEntity));

        String responeBody = EntityUtils.toString(resEntity);
        System.out.println(responeBody);
        EntityUtils.consume(resEntity);

        return responeBody;
    }

    public List<StudentsAnswer> getIDMAnswers(List<String> pathes) {
        List<StudentsAnswer> studentsAnswers = new ArrayList<>();
        for (String path : pathes) {
            try {
                StudentsAnswer s = new JSONParser().parseIBMResponse(sendRequest(path));
                studentsAnswers.add(s);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return studentsAnswers;
    }

}






