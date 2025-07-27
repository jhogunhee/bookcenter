package com.project.bookcenter.external.aladin;

import com.project.bookcenter.common.dao.CommonDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class AladinApiService {

    @Autowired
    CommonDao commonDao;

    @Value("${ALADIN_TTB_KEY}")
    private String ttbKey;

    private final String BASE_URL = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";

    public void fetchBooksByCategory(String categoryId) {
        commonDao.delete("BookMaster.deleteBook", new HashMap<>()); // 데이터 초기화

        int maxResults = 50;
        int maxPage = 6;

        for (int page = 1; page <= maxPage; page++) {
            String apiUrl = BASE_URL + "?ttbkey=" + ttbKey
                    + "&QueryType=ItemNewAll"
                    + "&MaxResults=" + maxResults
                    + "&start=" + page
                    + "&SearchTarget=Book"
                    + "&output=js"
                    + "&Version=20131101"
                    + "&CategoryId=" + categoryId;

            try {
                JSONArray items = callApiAndParse(apiUrl);
                Map<String, Object> param = new HashMap<>();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject book = items.getJSONObject(i);

                    param.put("ISBN", book.optString("isbn"));
                    param.put("TITLE", book.optString("title"));
                    param.put("AUTHOR", book.optString("author"));
                    param.put("PUBLISHER", book.optString("publisher"));
                    param.put("PUBLISH_DATE", book.optString("pubDate"));
                    param.put("COVER_IMAGE", book.optString("cover"));

                    commonDao.insert("BookMaster.insertBook", param);
                }
            } catch (Exception e) {
                System.out.println("API 호출 실패: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private JSONArray callApiAndParse(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8")
        );
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getJSONArray("item");
    }
}
