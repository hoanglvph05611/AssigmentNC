package com.hoang.lvhco.assigmentnc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hoang.lvhco.assigmentnc.R;
import com.hoang.lvhco.assigmentnc.course.MyService;
import com.hoang.lvhco.assigmentnc.course.SignLearnActivity;
import com.hoang.lvhco.assigmentnc.course.ViewLearnActivity;

public class CourseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_fragment,container,false);
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.item,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dkLichHoc:
                Intent intent1 = new Intent(getContext(),SignLearnActivity.class);
                startActivity(intent1);
                break;
            case R.id.dsDangKy:

                Intent intent = new Intent(getContext(),ViewLearnActivity.class);
                startActivity(intent);
                break;
            case R.id.LichHoc:
                Toast.makeText(getContext(), "Phiên bản 1.0.1", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
