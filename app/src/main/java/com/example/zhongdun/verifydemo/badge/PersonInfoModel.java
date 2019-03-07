package com.example.zhongdun.verifydemo.badge;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity
public class PersonInfoModel {

    private int signInImage;
    private int IDImage;
    private Long id;
    private String ID;//记录ID,GUID
    private String EMPLOYEEID;//证件号码
    private float SIMILARITY;//比重人脸相似度
    private String ENTRYTIME;//人脸抓拍时间,yyyy-MM-dd HH:mm:ss
    private String DEVICENAME;//设备名称
    private String DEVICEID;//设备编号
    private String CREATETIME;//入库时间,yyyy-MM-dd HH:mm:ss
    private String NAME;//名字
    private String SEX;//性别
    private String BIRTHDAY;//出生日期
    private String NATION;//民族
    private String DEPARTMENT;//所属部门
    private String ZFSPATH;//内部人员证件照Url地址
    private String ZFSFACEPATH;//内部人员人脸抓拍Url地址
    private String NOTE;//人员备注信息
    private String DUBIOUS;//人员类型(0内部授权 ,1可疑人员)
    private String REPOID = "0";//人员库ID ,默认填0
    private boolean IDTODAY;
    @Generated(hash = 1286416066)
    public PersonInfoModel(int signInImage, int IDImage, Long id, String ID,
            String EMPLOYEEID, float SIMILARITY, String ENTRYTIME,
            String DEVICENAME, String DEVICEID, String CREATETIME, String NAME,
            String SEX, String BIRTHDAY, String NATION, String DEPARTMENT,
            String ZFSPATH, String ZFSFACEPATH, String NOTE, String DUBIOUS,
            String REPOID, boolean IDTODAY) {
        this.signInImage = signInImage;
        this.IDImage = IDImage;
        this.id = id;
        this.ID = ID;
        this.EMPLOYEEID = EMPLOYEEID;
        this.SIMILARITY = SIMILARITY;
        this.ENTRYTIME = ENTRYTIME;
        this.DEVICENAME = DEVICENAME;
        this.DEVICEID = DEVICEID;
        this.CREATETIME = CREATETIME;
        this.NAME = NAME;
        this.SEX = SEX;
        this.BIRTHDAY = BIRTHDAY;
        this.NATION = NATION;
        this.DEPARTMENT = DEPARTMENT;
        this.ZFSPATH = ZFSPATH;
        this.ZFSFACEPATH = ZFSFACEPATH;
        this.NOTE = NOTE;
        this.DUBIOUS = DUBIOUS;
        this.REPOID = REPOID;
        this.IDTODAY = IDTODAY;
    }
    @Generated(hash = 562760731)
    public PersonInfoModel() {
    }
    public int getSignInImage() {
        return this.signInImage;
    }
    public void setSignInImage(int signInImage) {
        this.signInImage = signInImage;
    }
    public int getIDImage() {
        return this.IDImage;
    }
    public void setIDImage(int IDImage) {
        this.IDImage = IDImage;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getEMPLOYEEID() {
        return this.EMPLOYEEID;
    }
    public void setEMPLOYEEID(String EMPLOYEEID) {
        this.EMPLOYEEID = EMPLOYEEID;
    }
    public float getSIMILARITY() {
        return this.SIMILARITY;
    }
    public void setSIMILARITY(float SIMILARITY) {
        this.SIMILARITY = SIMILARITY;
    }
    public String getENTRYTIME() {
        return this.ENTRYTIME;
    }
    public void setENTRYTIME(String ENTRYTIME) {
        this.ENTRYTIME = ENTRYTIME;
    }
    public String getDEVICENAME() {
        return this.DEVICENAME;
    }
    public void setDEVICENAME(String DEVICENAME) {
        this.DEVICENAME = DEVICENAME;
    }
    public String getDEVICEID() {
        return this.DEVICEID;
    }
    public void setDEVICEID(String DEVICEID) {
        this.DEVICEID = DEVICEID;
    }
    public String getCREATETIME() {
        return this.CREATETIME;
    }
    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }
    public String getNAME() {
        return this.NAME;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public String getSEX() {
        return this.SEX;
    }
    public void setSEX(String SEX) {
        this.SEX = SEX;
    }
    public String getBIRTHDAY() {
        return this.BIRTHDAY;
    }
    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }
    public String getNATION() {
        return this.NATION;
    }
    public void setNATION(String NATION) {
        this.NATION = NATION;
    }
    public String getDEPARTMENT() {
        return this.DEPARTMENT;
    }
    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }
    public String getZFSPATH() {
        return this.ZFSPATH;
    }
    public void setZFSPATH(String ZFSPATH) {
        this.ZFSPATH = ZFSPATH;
    }
    public String getZFSFACEPATH() {
        return this.ZFSFACEPATH;
    }
    public void setZFSFACEPATH(String ZFSFACEPATH) {
        this.ZFSFACEPATH = ZFSFACEPATH;
    }
    public String getNOTE() {
        return this.NOTE;
    }
    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }
    public String getDUBIOUS() {
        return this.DUBIOUS;
    }
    public void setDUBIOUS(String DUBIOUS) {
        this.DUBIOUS = DUBIOUS;
    }
    public String getREPOID() {
        return this.REPOID;
    }
    public void setREPOID(String REPOID) {
        this.REPOID = REPOID;
    }
    public boolean getIDTODAY() {
        return this.IDTODAY;
    }
    public void setIDTODAY(boolean IDTODAY) {
        this.IDTODAY = IDTODAY;
    }

    
}
