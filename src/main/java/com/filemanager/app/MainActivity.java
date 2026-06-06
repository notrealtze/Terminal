package com.filemanager.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    private ListView fileListView;
    private TextView pathTextView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> fileList;
    private File currentDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileListView = findViewById(R.id.fileListView);
        pathTextView = findViewById(R.id.pathTextView);
        Button backButton = findViewById(R.id.backButton);
        Button homeButton = findViewById(R.id.homeButton);

        fileList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileList);
        fileListView.setAdapter(adapter);

        currentDirectory = Environment.getExternalStorageDirectory();
        loadFiles();

        backButton.setOnClickListener(v -> goBack());
        homeButton.setOnClickListener(v -> goHome());

        fileListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedFile = fileList.get(position);
            File selected = new File(currentDirectory, selectedFile);
            if (selected.isDirectory()) {
                currentDirectory = selected;
                loadFiles();
            } else {
                Toast.makeText(MainActivity.this, "File: " + selectedFile, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFiles() {
        fileList.clear();
        pathTextView.setText("📂 " + currentDirectory.getAbsolutePath());

        File[] files = currentDirectory.listFiles();
        if (files != null) {
            ArrayList<String> dirs = new ArrayList<>();
            ArrayList<String> fileNames = new ArrayList<>();

            for (File file : files) {
                if (file.getName().startsWith(".")) continue;
                if (file.isDirectory()) {
                    dirs.add("📁 " + file.getName());
                } else {
                    fileNames.add("📄 " + file.getName());
                }
            }

            Arrays.sort(dirs.toArray(new String[0]));
            Arrays.sort(fileNames.toArray(new String[0]));

            fileList.addAll(dirs);
            fileList.addAll(fileNames);
        }

        adapter.notifyDataSetChanged();
    }

    private void goBack() {
        File parent = currentDirectory.getParentFile();
        if (parent != null) {
            currentDirectory = parent;
            loadFiles();
        } else {
            Toast.makeText(this, "Already at root", Toast.LENGTH_SHORT).show();
        }
    }

    private void goHome() {
        currentDirectory = Environment.getExternalStorageDirectory();
        loadFiles();
    }
}
