package com.example.domtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textview);
    }

    public void onClick(View view) {
        GetXMLTask task = new GetXMLTask(this);
        task.execute("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
    }

    class GetXMLTask extends AsyncTask<String, Void, Document>{
        private Activity context;

        public GetXMLTask(Activity context){
            this.context=context;
        }
        @Override
        protected Document doInBackground(String... strings) {
            URL url;
            Document document = null;
            String s= null;
            byte[] buffer = new byte[10000];

            try {
                url=new URL(strings[0]);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                document=db.parse(new InputSource(url.openStream()));
                document.getDocumentElement().normalize();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            String s = "";
            NodeList nodeList = document.getElementsByTagName("data");

            for(int i=0;i<nodeList.getLength();i++){
                s+="" + i + ": 날씨정보 : ";
                Node node = nodeList.item(i);
                Element element = (Element) node;

                NodeList dayList = element.getElementsByTagName("day");
                Element dayElement = (Element) dayList.item(0);
                dayList = dayElement.getChildNodes();
                s+="일=" + ((Node)dayList.item(0)).getNodeValue()+", ";

                NodeList hourList = element.getElementsByTagName("hour");
                Element hourElement = (Element) hourList.item(0);
                hourList = hourElement.getChildNodes();
                s+="시=" + ((Node)hourList.item(0)).getNodeValue()+", ";

                NodeList nameList = element.getElementsByTagName("temp");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();
                s+="온도=" + ((Node)nameList.item(0)).getNodeValue()+", ";

                NodeList weatherList = element.getElementsByTagName("wfKor");
                Element weatherElement = (Element) weatherList.item(0);
                weatherList = weatherElement.getChildNodes();
                s+="날씨=" + ((Node)weatherList.item(0)).getNodeValue()+"\n";

//                NodeList wdList = element.getElementsByTagName("wdKor");
//                Element wdElement = (Element) wdList.item(0);
//                wdList = wdElement.getChildNodes();
//                s+="바람=" + ((Node)wdList.item(0)).getNodeValue()+"\n";
            }
            textView.setText(s);
        }
    }
}

