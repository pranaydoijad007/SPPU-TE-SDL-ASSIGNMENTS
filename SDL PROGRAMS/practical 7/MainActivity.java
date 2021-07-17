package com.example.admin.musicplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    String[] items;
    ArrayList<File> mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.songlist);
        mySongs=findsongs(Environment.getExternalStorageDirectory());

        items=new String[mySongs.size()];

        for(int i=0;i<mySongs.size();i++)
        {
            items[i]=mySongs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
        }

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.song_layout,R.id.songcolor,items);
        lv.setAdapter(adp);

        lv.setOnItemClickListener(this);
    }

    public ArrayList<File> findsongs(File root)
    {
        File[] files=root.listFiles();
        ArrayList<File> al=new ArrayList<File>();
        for(File singleFile:files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                    al.addAll(findsongs(singleFile));
            }
            else
            {
                if(singleFile.getName().endsWith(".mp3")||singleFile.getName().endsWith(".wav"))
                {
                    al.add(singleFile);
                }
            }
        }
        return al;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        startActivity(new Intent(this,Player.class).putExtra("pos",position).putExtra("songlist",mySongs));
    }
}
