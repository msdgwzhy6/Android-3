package com.ikok.changefont;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * 显示页面的TextView控件
     */
    private TextView mContentText;
    /**
     * 主页面
     */
    private DrawerLayout drawer;
    /**
     * 改变 home 返回图标，drawer打开关闭，图标的动画效果
     */
    private ActionBarDrawerToggle toggle;
    /**
     * 抽屉布局侧边栏
     */
    private NavigationView navigationView;
    /**
     * 红色的email图标
     */
    private FloatingActionButton fab;
    /**
     * 顶部的toolbar
     */
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContentText = (TextView) findViewById(R.id.content_text);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 从底部弹出的信息框
                 * setAction() 可以设置事件
                 */
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                createDialog();
//                            }
//                        }).show();

                createDialog();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        /**
         * 同步抽屉的打开或关闭状态
         */
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    /**
     * 返回键的事件
     * 如果抽屉打开的，则关闭
     * 如果没有打开，则进行系统的返回
     */
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * 创建 toolbar 的菜单
     * @param menu 菜单的资源文件
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * toolbar 的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            createDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * 抽屉导航的单项点击事件
     * 返回 true，再打开抽屉的时候，有一个被选中的样式
     * 返回 false，没有被选中的样式
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_camera) {
            // Handle the camera action

            fragment = new SimpleToComplex();

        } else if (id == R.id.nav_gallery) {

            fragment = new SimpleToHXW();

        } else if (id == R.id.nav_slideshow) {

            fragment = new ComplexToSimple();

//        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            fragment = new AboutMe();


//        } else if (id == R.id.nav_send) {
        }

        /**
         * 设置原来的显示页面的TextView不可见
         */
        mContentText.setVisibility(View.GONE);
        /**
         * 设置创建的Fragment替换帧布局里的内容
         * fragment为空时，自动显示简体转繁体的页面
         */
        if (fragment == null){
            fragment = new SimpleToComplex();
        }
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        /**
         * 关闭抽屉
         */
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 创建弹出的对话框
     */
    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("关于")
                .setMessage("Better life,better you!")
                .setPositiveButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
