package com.example.a7invensun.verifydemo.judgeNetworkConnected.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;

import com.example.a7invensun.verifydemo.logdemo.util.LogUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 更多网络信息，请参考
 * https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppNetworkMgr.java
 * Created by 7invensun on 2018/8/17.
 */

public class NetworkStateUtil {
    private static final int NETWORK_NONE = -1;
    //中国移动CMNET网络(中国移动GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)
    private static final int TYPE_MOBILE_CMNET = 1;
    //中国移动CMWAP网络(中国移动GPRS接入方式之一,主要为手机WAP上网而设立)
    private static final int TYPE_MOBILE_CMWAP = 2;
    //中国联通UNIWAP网络(中国联通划分GPRS接入方式之一, 主要为手机WAP上网而设立)
    private static final int TYPE_MOBILE_UNIWAP = 3;
    //中国联通3GWAP网络
    public static final int TYPE_MOBILE_3GWAP = 4;
    //中国联通3HNET网络
    public static final int TYPE_MOBLIE_3GNET = 5;
    //中国联通UNINET网络(中国联通划分GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)
    public static final int TYPE_MOBILE_UNINET = 6;
    //中国电信CTWAP网络
    public static final int TYPE_MOBILE_CTWAP = 7;
    //中国电信CTNET网络
    public static final int TYPE_MOBILE_CTNET = 8;
    //WIFI网络
    public static final int TYPE_WIFI = 10;


    /**
     * 获取当前手机连接网络状态
     *
     * @param context
     * @return
     */
    public static int getNetworkState(Context context) {
        int type = 0;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {

            //获取网络类型
            int currentNetWork = networkInfo.getType();
            //手机网络类型
            if (currentNetWork == ConnectivityManager.TYPE_MOBILE) {
                if (networkInfo.getExtraInfo() != null) {//获取附加信息
                    if (networkInfo.getExtraInfo().equals("cmnet")) {
                        LogUtils.i("当前网络为中国移动CMNET网络");
                        return TYPE_MOBILE_CMNET;
                    }
                    if (networkInfo.getExtraInfo().equals("cmwap")) {
                        LogUtils.i("当前网络为中国移动CMWAP网络");
                        return TYPE_MOBILE_CMWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("uniwap")) {
                        LogUtils.i("当前网络为中国联通UNIWAP网络");
                        return TYPE_MOBILE_UNIWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("3gwap")) {
                        LogUtils.i("当前网络为中国联通3GWAP网络");
                        return TYPE_MOBILE_3GWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("3gnet")) {
                        LogUtils.i("当前网络为中国联通3GNET网络");
                        return TYPE_MOBLIE_3GNET;
                    }
                    if (networkInfo.getExtraInfo().equals("uninet")) {
                        LogUtils.i("当前网络为中国联通UNINET网络");
                        return TYPE_MOBILE_UNINET;
                    }
                    if (networkInfo.getExtraInfo().equals("ctwap")) {
                        LogUtils.i("当前网络为中国电信CTWAP网络");
                        return TYPE_MOBILE_CTWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("ctnet")) {
                        LogUtils.i("当前网络为中国电信CTNET网络");
                        return TYPE_MOBILE_CTNET;
                    }
                }
            } else if (currentNetWork == ConnectivityManager.TYPE_WIFI) {
                LogUtils.i("当前网络为WIFI网络");
                return TYPE_WIFI;
            }
        }
        return NETWORK_NONE;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();
            //获取连接对象
            if (mNetworkInfo != null) {
                //判断是TYPE_MOBILE网络
                if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                    LogUtils.i("网络连接类型为：TYPE_MOBILE");
                    //判断移动网络连接状态
                    State STATE_MOBILE = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                    if (STATE_MOBILE == State.CONNECTED) {
                        LogUtils.i("网络连接类型为：TYPE_MOBILE, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
                //判断是TYPE_WIFI网络
                if (ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType()) {
                    LogUtils.i("网络连接类型为：TYPE_WIFI");
                    //判断WIFI网络状态
                    State STATE_WIFI = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                    if (STATE_WIFI == State.CONNECTED) {
                        LogUtils.i("网络连接类型为：TYPE_WIFI, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断网络是否连接
     *
     * @param activity Activity
     * @return boolean 网络连接状态
     */
    public static boolean isNetworkConnected(Activity activity) {
        boolean falg = false;
        ConnectivityManager manager = (ConnectivityManager) activity.getApplicationContext().getSystemService("connectivity");
        if (manager == null){
            return falg;
        }
        NetworkInfo[] arrayOfNetworkInfo = manager.getAllNetworkInfo();
        if (arrayOfNetworkInfo != null){
            for (int j = 0; j < arrayOfNetworkInfo.length; j++){
                if (arrayOfNetworkInfo[j].getState() == State.CONNECTED){
                    falg = true;
                    break;
                }
            }
        }
        return falg;
    }

    /**
     * 打开网络设置界面
     * @param activity Activity
     */
    public static void openNetSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 获取当前网络的类型
     *
     * @param context 上下文
     * @return 当前网络的类型。具体类型可参照ConnectivityManager中的TYPE_BLUETOOTH、TYPE_MOBILE、TYPE_WIFI等字段。当前没有网络连接时返回NetworkUtils.NETWORK_NONE
     */
    public static int getCurrentNetworkType(Context context) {
        NetworkInfo networkInfo
                = ((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null
                ? networkInfo.getType()
                : NETWORK_NONE;
    }


    /**
     * 判断当前网络的类型是否是移动网络
     *
     * @param context 上下文
     * @return 当前网络的类型是否是移动网络。false：当前没有网络连接或者网络类型不是移动网络
     */
    public static boolean isMobileByType(Context context) {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 判断当前网络的类型是否是Wifi
     *
     * @param context 上下文
     * @return 当前网络的类型是否是Wifi。false：当前没有网络连接或者网络类型不是wifi
     */
    public static boolean isWifiByType(Context context) {
        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取Wifi的状态，需要ACCESS_WIFI_STATE权限
     *
     * @param context 上下文
     * @return 取值为WifiManager中的WIFI_STATE_ENABLED、WIFI_STATE_ENABLING、WIFI_STATE_DISABLED、WIFI_STATE_DISABLING、WIFI_STATE_UNKNOWN之一
     * @throws Exception 没有找到wifi设备
     */
    public static int getWifiState(Context context) throws Exception {
        WifiManager wifiManager = ((WifiManager) context.getSystemService(
                Context.WIFI_SERVICE));
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        else {
            throw new Exception("wifi device not found!");
        }
    }


    /**
     * 判断Wifi是否打开，需要ACCESS_WIFI_STATE权限
     *
     * @param context 上下文
     * @return true：打开；false：关闭
     */
    public static boolean isWifiOpen(Context context) throws Exception {
        int wifiState = getWifiState(context);
        return wifiState == WifiManager.WIFI_STATE_ENABLED ||
                wifiState == WifiManager.WIFI_STATE_ENABLING
                ? true
                : false;
    }

    /**
     * 设置Wifi，需要CHANGE_WIFI_STATE权限
     *
     * @param context 上下文
     * @param enable wifi状态
     * @return 设置是否成功
     */
    public static boolean setWifi(Context context, boolean enable)
            throws Exception {
        //如果当前wifi的状态和要设置的状态不一样
        if (isWifiOpen(context) != enable) {
            ((WifiManager) context.getSystemService(
                    Context.WIFI_SERVICE)).setWifiEnabled(enable);
        }
        return true;
    }

    /**
     * 判断移动网络是否打开，需要ACCESS_NETWORK_STATE权限
     *
     * @param context 上下文
     * @return true：打开；false：关闭
     */
    public static boolean isMobileNetworkOpen(Context context) {
        return (((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE)).isConnected();
    }

    /**
     * 获取本机IP地址
     *
     * @return null：没有网络连接
     */
    public static String getIpAddress() {
        try {
            NetworkInterface nerworkInterface;
            InetAddress inetAddress;
            for (Enumeration<NetworkInterface> en
                 = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                nerworkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr
                     = nerworkInterface.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {
                    inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
