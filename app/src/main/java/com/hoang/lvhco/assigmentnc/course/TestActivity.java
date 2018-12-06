package com.hoang.lvhco.assigmentnc.course;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hoang.lvhco.assigmentnc.R;
import com.hoang.lvhco.assigmentnc.dao.TestDAO;
import com.hoang.lvhco.assigmentnc.model.CheckTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



public class TestActivity extends AppCompatActivity {
    private List<CheckTest> mLearn;
    private ListView list_test;
    TestDAO testDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test );
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.action_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        list_test = findViewById(R.id.list_test);
        testDAO = new TestDAO(TestActivity.this);
        final List<CheckTest> students = testDAO.getTestAll();
        CustomPrs customPrs = new CustomPrs(this,students);
        list_test.setAdapter(customPrs);
        list_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
                String strDate = mdformat.format(calendar.getTime());
                builder.setTitle("Thời gian thi");
                builder.setMessage("Môn "+students.get(position).getSubjectTest()+" thi vào lúc "+strDate +" ngày "+students.get(position).getDateTest());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }
    class CustomPrs extends BaseAdapter
    {
        LayoutInflater inflater;
        List<CheckTest> student;
        CustomPrs(Context context, List<CheckTest> students)
        {
            inflater = (LayoutInflater)(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            student = students;
        }
        @Override
        public int getCount() {
            return student.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v =  inflater.inflate(R.layout.custom_test,null);
            TextView stt = v.findViewById(R.id.idtest);
            TextView date = v.findViewById(R.id.datetest);
            TextView sub = v.findViewById(R.id.subtest);
            String mstt = String.valueOf(student.get(position).getIdTest());
            stt.setText(mstt);
            date.setText(student.get(position).getDateTest());
            sub.setText(student.get(position).getSubjectTest());
            return v;
        }
    }
}
