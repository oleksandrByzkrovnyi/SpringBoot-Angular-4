package com.ua.nure.TestHelper.utils;



import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONParser {

    public StudentsAnswer parseIBMResponse(String response) throws IOException {
        Map<String, String> findAnswers = new HashMap<>();
        JsonFactory jfactory = new JsonFactory();

        JsonParser jParser = jfactory.createJsonParser(response);
        while (jParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jParser.getCurrentName();
            if ("classes".equals(fieldname)) {
                String findClass = "";
                String findScore = "";
                while (jParser.nextToken() != JsonToken.END_ARRAY) {
                    String className = jParser.getCurrentName();
                    if ("class".equals(className)) {
                        jParser.nextToken();
                        findClass = jParser.getText();
                    } else if ("score".equals(className)) {
                        jParser.nextToken();
                        findScore = jParser.getText();
                        if (!findClass.isEmpty() && !findScore.isEmpty()) {
                            findAnswers.put(findClass, findScore);
                            System.out.println(findAnswers);
                        }
                    }
                }
            }
        }
        // узнаем самый вероятный ответ
        double maxScore = 0;
        String maxName = "";
        for (Map.Entry<String, String> entry : findAnswers.entrySet()) {
            if (Double.parseDouble(entry.getValue()) > maxScore) {
                maxScore = Double.parseDouble(entry.getValue());
                maxName = entry.getKey();
            }
        }


        Pattern patternObject = Pattern.compile("\\d+(?=.jpg)|(?=.png)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = patternObject.matcher(response);

        while (matcher.find()) {
            if(IBMVaryAnswers.IBM_ANSWERS.containsKey(maxName)){
                StudentsAnswer studentsAnswer = new StudentsAnswer().setNum(matcher.group()).setAnswer(String.valueOf(IBMVaryAnswers.IBM_ANSWERS.get(maxName)));
                System.out.println(studentsAnswer);
                return studentsAnswer;
            }

        }
        return null;
    }
}
