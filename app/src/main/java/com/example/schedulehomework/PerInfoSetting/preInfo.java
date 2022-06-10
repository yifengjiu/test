package com.example.schedulehomework.PerInfoSetting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.schedulehomework.MainActivity;
import com.example.schedulehomework.controller.Controller;
import com.example.schedulehomework.entity.Activitytasks;
import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.utils.IOutils;
import com.example.schedulehomework.R;
import com.example.schedulehomework.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class preInfo extends AppCompatActivity {

    ListView lv,lv2;
    ImageView iv;
    private User user=new User();
    private String name=" ";
    private int ID=0;
    private TextView nameView=null;
    private TextView IDView=null;
    private Button button;
    private Button button2;
    Controller con=Controller.getController();
    ArrayList<Learningtasks> learningtasks=new ArrayList<>();
    ArrayList<Activitytasks> activitytasks=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initView();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_info);

        iv=findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.cat);
        nameView=findViewById(R.id.name);
        IDView=findViewById(R.id.ID);
        nameView.setText(name);
        IDView.setText("账号ID："+ID);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(preInfo.this, MainActivity.class));
            }
        });

        ListView lv = findViewById(R.id.listview1);
        ArrayList AL = new ArrayList();
        HashMap map = new HashMap();
        map.put("text","个人信息");
        map.put("content","");
        AL.add(map);

        map = new HashMap();
        map.put("text","检查更新");
        map.put("content","");
        AL.add(map);


        map = new HashMap();
        map.put("text","信息统计");
        map.put("content","");
        AL.add(map);

        String[] from = {"text","content"};
        int[] to = {R.id.textView,R.id.content};
        SimpleAdapter adapter = new SimpleAdapter(this, AL,R.layout.listitemselect,from,to);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    AlertDialog.Builder info = new AlertDialog.Builder(preInfo.this);
                    info.setTitle("个人信息");
                    info.setMessage("账号："+user.getName()+"ID："+user.getID());
                    info.show();

                }else if(position == 1){
                    if(false/*TODO:检查版本*/){

                    }else{
                        AlertDialog.Builder info = new AlertDialog.Builder(preInfo.this);
                        info.setTitle("检查更新");
                        info.setMessage("当前为最新版本,请期待后续升级");
                        info.show();
                    }
                }
                else if(position == 2){
                    activitytasks=con.getActivitytasks();
                    learningtasks=con.getLearningtasks();
                    String pathle="C:\\Users\\YIFENG\\Desktop\\android\\learnTasks.txt";
                    String pathac="C:\\Users\\YIFENG\\Desktop\\android\\ActivityTask.txt";
                    String content1=JSON.toJSONString(learningtasks);;
                    String content2=JSON.toJSONString(activitytasks);;
                    //保存文件到本地并打开服务器
                    try {
                       // charOutStream(pathle,content1);
                        //charOutStream(pathac,content2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String url = "http://www.baidu.com/";
                }


            }
        });
    }

    public void initView(){
        ArrayList<User> users=new ArrayList<User>();
        try {
            users= (ArrayList<User>) IOutils.readFileData(this,"user.json",User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user1:users){
            user=user1;
        }
        name=user.getName();
        ID=user.getID();

    }
    void processOpenTest() throws IOException {
        ProcessBuilder proc = new ProcessBuilder("D:\\chrome.exe",
                "http://www.hao123.com");
        proc.start();
    }

}