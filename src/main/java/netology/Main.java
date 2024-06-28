package netology;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CrptApi crptApi = new CrptApi(TimeUnit.SECONDS, 5);


        Object document = new Object();
        String signature = "123456";


        crptApi.createDocument(document, signature);
    }
    }
