package com.example.aaron.todolist2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by aaron on 12/15/15.
 */
public class TaskFragment extends Fragment implements View.OnClickListener {

    View view;
    public EditText taskEditText;
    private String userTask;
    private ListView listview;
    private int listPosition;
    private CustomAdapter customAdapter;
    private DialogFragment datePicker;

    private int itemPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.task_fragment, container, false);
        taskEditText = (EditText) view.findViewById(R.id.add_task);
        customAdapter = new CustomAdapter(getActivity());
        listview = (ListView) view.findViewById(R.id.list_view);
        listview.setOnItemClickListener(onItemClickListener);
        listview.setOnItemLongClickListener(onItemLongClickListener);

        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String value = (String) listview.getItemAtPosition(position);
            taskEditText.setText(value);
            taskEditText.setSelection(taskEditText.length());
            listPosition = customAdapter.list.indexOf(value);
        }
    };

    private AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("flow", "item was long clicked");
            showDatePickerDialog();
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rubiks_cube:
                Log.d("flow", "rubik's cube was clicked");
//                String onCubeClickValue = (String) listview.;
//                listPosition = customAdapter.list.indexOf(onCubeClickValue);
//                customAdapter.deleteToDoItem(listPosition);
//                customAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addTask:
                Log.d("flow", "action_addTask clicked");
                userTask = taskEditText.getText().toString();
                taskEditText.getText().clear();
                Log.d("flow", "String is passed:" + userTask);
                customAdapter.addToDoItem(userTask);
                listview.setAdapter(customAdapter);
                return true;
            case R.id.action_updateTask:
                Log.d("flow", "action_updateItem clicked");
                userTask = taskEditText.getText().toString();
                taskEditText.getText().clear();
                Log.d("flow", "String is passed:" + userTask);
                customAdapter.editToDoItem(userTask, listPosition);
                customAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDatePickerDialog() {
        datePicker = new DatePickerFragment();
        datePicker.show(getFragmentManager(), "datePicker");
    }
}
