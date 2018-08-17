package com.example.a7invensun.verifydemo.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.greendao.model.bean.Student;
import com.example.a7invensun.verifydemo.greendao.model.db.DaoOpe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GreenDaoActivity extends AppCompatActivity {
    private static final String TAG = "GreenDaoActivity";
    private Unbinder bind;
    @BindView(R.id.add_greenDao)
    Button addButton;
    @BindView(R.id.del_greenDao)
    Button delButton;
    @BindView(R.id.update_greenDao)
    Button updButton;
    @BindView(R.id.find_greenDao)
    Button findButton;
    @BindView(R.id.btn_query_all)
    Button btn_query_all;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.tv_content)
    TextView tv_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        initData();

    }

    private List<Student> studentList = new ArrayList<>();
    private void initData() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student((long) i, "huang" + i, "sna"+i);
            studentList.add(student);
        }
    }
    int page;
    @OnClick({R.id.add_greenDao, R.id.del_greenDao, R.id.update_greenDao, R.id.find_greenDao, R.id.btn_query_all, R.id.button5, R.id.tv_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_greenDao:
                Log.e(TAG, "onClick: add_greenDao" );
                DaoOpe.insertData(GreenDaoActivity.this, studentList);
                break;
            case R.id.del_greenDao:
                Student student = new Student((long) 5, "haung" + 5,"sna"+5);
                Log.e(TAG, "onClick: del_greenDao" );
                /**
                 * 根据特定的对象删除
                 */
                DaoOpe.deleteData(GreenDaoActivity.this, student);
                /**
                 * 根据主键删除
                 */
//                DaoOpe.deleteByKeyData(GreenDaoActivity.this, 7);
//                DaoOpe.deleteAllData(GreenDaoActivity.this);

                break;

            case R.id.update_greenDao:
                Log.e(TAG, "onClick: update_greenDao" );
                student = new Student((long) 2, "caojin", "888888");
                DaoOpe.updateData(GreenDaoActivity.this, student);
                break;
            case R.id.find_greenDao:
                Log.e(TAG, "onClick: find_greenDao" );
                List<Student> students = DaoOpe.queryAll(GreenDaoActivity.this);
                tv_content.setText(students.toString());
                for (int i = 0; i < students.size(); i++) {
                    Log.i("Log", students.get(i).getName());
                }
                break;
            case R.id.btn_query_all:
                Log.e(TAG, "onClick: btn_query_all" );
                List<Student> students2 = DaoOpe.queryPaging(page, 20, this);

                if (students2.size() == 0) {
                    Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                for (Student st : students2) {
                    Log.e("TAG", "onViewClicked: ==" + st);
                    Log.e("TAG", "onViewClicked: == num = " + st.getName());
                    tv_content.setText(st.getName());
                }
                page++;

                break;
            case R.id.button5:
                Log.e(TAG, "onClick: button5" );
                DaoOpe.deleteAllData(GreenDaoActivity.this);
                break;
            case R.id.tv_content:
                Log.e(TAG, "onClick: 1" );
                break;
            default:
                break;
        }
    }
}
