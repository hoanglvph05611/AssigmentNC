package com.hoang.lvhco.assigmentnc.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hoang.lvhco.assigmentnc.R;
import com.hoang.lvhco.assigmentnc.news.Adapter;
import com.hoang.lvhco.assigmentnc.news.ReadNews;
import com.hoang.lvhco.assigmentnc.news.XMLDOMParser;
import com.hoang.lvhco.assigmentnc.news.webViewActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewFragment extends Fragment {
    WebView webView;
    ListView lv;

    EditText edtNhaplink;
    Button btnGo;
    final ArrayList<ReadNews> readNewsList =new ArrayList<ReadNews>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment,container,false);
        return view ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.lv);
        edtNhaplink = view.findViewById(R.id.edtNhapLink);
        btnGo = view.findViewById(R.id.btnGoUrl);


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readNewsList.clear();
                String link = edtNhaplink.getText().toString();
                new ReadXml().execute(link);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),webViewActivity.class);
                intent.putExtra("link", readNewsList.get(i).link);
                startActivity(intent);
            }
        });

    }

    public class ReadXml extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... strings) {
            String kq = getXMLFromURL(strings[0]);
            return kq;
        }
        @Override
        protected void onPostExecute(String s) {
//            Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            //--------Đọc XML----//
            XMLDOMParser parser = new XMLDOMParser();
            //----bỏ Xml vào biến doc---//
            Document doc = parser.getDocument(s);
            //---Lấy những gì có tên là item trong biến doc---//
            NodeList nodeListDecription;
            NodeList nodeList;
            nodeList = doc.getElementsByTagName("item");
            nodeListDecription = doc.getElementsByTagName("description");//coppy tên thẻ trong RSS để bảo đảm đúng tên
            //-----Duyệt item để lấy Title----//
            String title = "";
            String img = "";
            String link = "";
            for (int i=0; i <nodeList.getLength();i++){
                //i+1 vì bỏ 1 thằng Deription trong Rss không chứa img..
                String cData = nodeListDecription.item(i +1).getTextContent();
                //-----Đọc url của img
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cData);
                if (matcher.find()){ //nãy mới đc,  t làm rân cái hư mẹ lun =))
                    img = matcher.group(1);
                    Log.d("Img---",img);
                }
                Element e = (Element) nodeList.item(i);
                //------Tìm những gì cần show từ elemen-----//
                title = title + parser.getValue(e,"title");
                link = parser.getValue(e,"link");
                //----Add vào mảng----//
                readNewsList.add(new ReadNews(title,link,img));
            }
            adapterLv(lv);
//            Toast.makeText(MainActivity.this, kq    , Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);

        }

    }
    public void adapterLv(ListView listView){
        Adapter adapter = new Adapter(getContext(),android.R.layout.simple_list_item_1,readNewsList);
        listView.setAdapter(adapter);
    }


    private String getXMLFromURL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
