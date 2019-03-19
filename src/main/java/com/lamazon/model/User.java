package com.lamazon.model;

public class User {
	private String U_MOBILE_CERT_DATE;       // datetime
	private String U_CERTIFY_DATE;           // datetime
	private String U_REGISTER_DATE;          // datetime
	private String U_CONFIRM_DATE;           // datetime
	private String U_WITHDRAW_DATE;          // datetime
	private String U_MODIFY_DATE;            // datetime
	private String U_LOGINDATE;              // datetime
	private String MODATE;                   // datetime
	private int U_PK=-1;                        // int(11)
	private int U_NEWSLETTER=-1;                // int(11)
	private int U_TYPE=-1;                      // int(11)
	private int U_MOBILE_CERT=-1;               // int(11)
	private int U_SMS=-1;                       // int(11)
	private int U_COMPANY_CERITIFY=-1;          // int(11)
	private int U_STATUS=-1;                    // int(11)
	private int U_PROJ_NUM=-1;                  // int(11)
	private int U_PROJ_CONTRACT_NUM=-1;         // int(11)
	private int U_PORJ_ING_NUM=-1;              // int(11)
	private int U_PORJ_DONE_NUM=-1;             // int(11)
	private int U_PROJ_TOTAL_PRICE=-1;          // int(11)
	private int AGENT_ID=-1;                    // int(11)
	private String U_NAME;                   // varchar(255)
	private String U_ID;                     // varchar(255)
	private String U_PASSWD;                 // varchar(255)
	private String U_EMAIL;                  // varchar(255)
	private String U_SNS_TYPE;               // varchar(255)
	private String U_SNS_ID;                 // varchar(255)
	private String U_SEX;                    // varchar(255)
	private String U_BIRTHDAY;               // varchar(255)
	private String U_MOBILE;                 // varchar(255)
	private String U_PHONE;                  // varchar(255)
	private String U_ZONE1;                  // varchar(255)
	private String U_ZONE2;                  // varchar(255)
	private String U_ZIPCODE;                // varchar(255)
	private String U_ADDRESS;                // varchar(255)
	private String U_COMPANY;                // varchar(255)
	private String U_REGISTRATION_NUMBER;    // varchar(255)
	private String U_FOUNDER;                // varchar(255)
	private String U_DUTY;                   // varchar(255)
	private String U_DEPARTMENT;             // varchar(255)
	private String U_POSITION;               // varchar(255)
	private String U_PICTURE;                // varchar(255)
	private String INCODE;                   // varchar(255)
	private String MOCODE;                   // varchar(255)
	private String U_FROM;                   // varchar(45)
	private String U_FROM_ID;                // varchar(45)
	private String U_FROM_JOIN_DATE;         // varchar(45)
	private int C_IS_COMMISSION=-1;
	private String SESSION_ID;
	private int M_GRADE=-1;
	private String M_GRADE_NM;
	private String C_INFRA;
	private int C_PK=-1;
	private String C_ID;

	public String getU_MOBILE_CERT_DATE() {
		return U_MOBILE_CERT_DATE;
	}
	public void setU_MOBILE_CERT_DATE(String u_MOBILE_CERT_DATE) {
		U_MOBILE_CERT_DATE = u_MOBILE_CERT_DATE;
	}
	public String getU_CERTIFY_DATE() {
		return U_CERTIFY_DATE;
	}
	public void setU_CERTIFY_DATE(String u_CERTIFY_DATE) {
		U_CERTIFY_DATE = u_CERTIFY_DATE;
	}
	public String getU_REGISTER_DATE() {
		return U_REGISTER_DATE;
	}
	public void setU_REGISTER_DATE(String u_REGISTER_DATE) {
		U_REGISTER_DATE = u_REGISTER_DATE;
	}
	public String getU_CONFIRM_DATE() {
		return U_CONFIRM_DATE;
	}
	public void setU_CONFIRM_DATE(String u_CONFIRM_DATE) {
		U_CONFIRM_DATE = u_CONFIRM_DATE;
	}
	public String getU_WITHDRAW_DATE() {
		return U_WITHDRAW_DATE;
	}
	public void setU_WITHDRAW_DATE(String u_WITHDRAW_DATE) {
		U_WITHDRAW_DATE = u_WITHDRAW_DATE;
	}
	public String getU_MODIFY_DATE() {
		return U_MODIFY_DATE;
	}
	public void setU_MODIFY_DATE(String u_MODIFY_DATE) {
		U_MODIFY_DATE = u_MODIFY_DATE;
	}
	public String getU_LOGINDATE() {
		return U_LOGINDATE;
	}
	public void setU_LOGINDATE(String u_LOGINDATE) {
		U_LOGINDATE = u_LOGINDATE;
	}
	public String getMODATE() {
		return MODATE;
	}
	public void setMODATE(String mODATE) {
		MODATE = mODATE;
	}
	public int getU_PK() {
		return U_PK;
	}
	public void setU_PK(int u_PK) {
		U_PK = u_PK;
	}
	public int getU_NEWSLETTER() {
		return U_NEWSLETTER;
	}
	public void setU_NEWSLETTER(int u_NEWSLETTER) {
		U_NEWSLETTER = u_NEWSLETTER;
	}
	public int getU_TYPE() {
		return U_TYPE;
	}
	public void setU_TYPE(int u_TYPE) {
		U_TYPE = u_TYPE;
	}
	public int getU_MOBILE_CERT() {
		return U_MOBILE_CERT;
	}
	public void setU_MOBILE_CERT(int u_MOBILE_CERT) {
		U_MOBILE_CERT = u_MOBILE_CERT;
	}
	public int getU_SMS() {
		return U_SMS;
	}
	public void setU_SMS(int u_SMS) {
		U_SMS = u_SMS;
	}
	public int getU_COMPANY_CERITIFY() {
		return U_COMPANY_CERITIFY;
	}
	public void setU_COMPANY_CERITIFY(int u_COMPANY_CERITIFY) {
		U_COMPANY_CERITIFY = u_COMPANY_CERITIFY;
	}
	public int getU_STATUS() {
		return U_STATUS;
	}
	public void setU_STATUS(int u_STATUS) {
		U_STATUS = u_STATUS;
	}
	public int getU_PROJ_NUM() {
		return U_PROJ_NUM;
	}
	public void setU_PROJ_NUM(int u_PROJ_NUM) {
		U_PROJ_NUM = u_PROJ_NUM;
	}
	public int getU_PROJ_CONTRACT_NUM() {
		return U_PROJ_CONTRACT_NUM;
	}
	public void setU_PROJ_CONTRACT_NUM(int u_PROJ_CONTRACT_NUM) {
		U_PROJ_CONTRACT_NUM = u_PROJ_CONTRACT_NUM;
	}
	public int getU_PORJ_ING_NUM() {
		return U_PORJ_ING_NUM;
	}
	public void setU_PORJ_ING_NUM(int u_PORJ_ING_NUM) {
		U_PORJ_ING_NUM = u_PORJ_ING_NUM;
	}
	public int getU_PORJ_DONE_NUM() {
		return U_PORJ_DONE_NUM;
	}
	public void setU_PORJ_DONE_NUM(int u_PORJ_DONE_NUM) {
		U_PORJ_DONE_NUM = u_PORJ_DONE_NUM;
	}
	public int getU_PROJ_TOTAL_PRICE() {
		return U_PROJ_TOTAL_PRICE;
	}
	public void setU_PROJ_TOTAL_PRICE(int u_PROJ_TOTAL_PRICE) {
		U_PROJ_TOTAL_PRICE = u_PROJ_TOTAL_PRICE;
	}
	public int getAGENT_ID() {
		return AGENT_ID;
	}
	public void setAGENT_ID(int aGENT_ID) {
		AGENT_ID = aGENT_ID;
	}
	public String getU_NAME() {
		return U_NAME;
	}
	public void setU_NAME(String u_NAME) {
		U_NAME = u_NAME;
	}
	public String getU_ID() {
		return U_ID;
	}
	public void setU_ID(String u_ID) {
		U_ID = u_ID;
	}
	public String getU_PASSWD() {
		return U_PASSWD;
	}
	public void setU_PASSWD(String u_PASSWD) {
		U_PASSWD = u_PASSWD;
	}
	public String getU_EMAIL() {
		return U_EMAIL;
	}
	public void setU_EMAIL(String u_EMAIL) {
		U_EMAIL = u_EMAIL;
	}
	public String getU_SNS_TYPE() {
		return U_SNS_TYPE;
	}
	public void setU_SNS_TYPE(String u_SNS_TYPE) {
		U_SNS_TYPE = u_SNS_TYPE;
	}
	public String getU_SNS_ID() {
		return U_SNS_ID;
	}
	public void setU_SNS_ID(String u_SNS_ID) {
		U_SNS_ID = u_SNS_ID;
	}
	public String getU_SEX() {
		return U_SEX;
	}
	public void setU_SEX(String u_SEX) {
		U_SEX = u_SEX;
	}
	public String getU_BIRTHDAY() {
		return U_BIRTHDAY;
	}
	public void setU_BIRTHDAY(String u_BIRTHDAY) {
		U_BIRTHDAY = u_BIRTHDAY;
	}
	public String getU_MOBILE() {
		return U_MOBILE;
	}
	public void setU_MOBILE(String u_MOBILE) {
		U_MOBILE = u_MOBILE;
	}
	public String getU_PHONE() {
		return U_PHONE;
	}
	public void setU_PHONE(String u_PHONE) {
		U_PHONE = u_PHONE;
	}
	public String getU_ZONE1() {
		return U_ZONE1;
	}
	public void setU_ZONE1(String u_ZONE1) {
		U_ZONE1 = u_ZONE1;
	}
	public String getU_ZONE2() {
		return U_ZONE2;
	}
	public void setU_ZONE2(String u_ZONE2) {
		U_ZONE2 = u_ZONE2;
	}
	public String getU_ZIPCODE() {
		return U_ZIPCODE;
	}
	public void setU_ZIPCODE(String u_ZIPCODE) {
		U_ZIPCODE = u_ZIPCODE;
	}
	public String getU_ADDRESS() {
		return U_ADDRESS;
	}
	public void setU_ADDRESS(String u_ADDRESS) {
		U_ADDRESS = u_ADDRESS;
	}
	public String getU_COMPANY() {
		return U_COMPANY;
	}
	public void setU_COMPANY(String u_COMPANY) {
		U_COMPANY = u_COMPANY;
	}
	public String getU_REGISTRATION_NUMBER() {
		return U_REGISTRATION_NUMBER;
	}
	public void setU_REGISTRATION_NUMBER(String u_REGISTRATION_NUMBER) {
		U_REGISTRATION_NUMBER = u_REGISTRATION_NUMBER;
	}
	public String getU_FOUNDER() {
		return U_FOUNDER;
	}
	public void setU_FOUNDER(String u_FOUNDER) {
		U_FOUNDER = u_FOUNDER;
	}
	public String getU_DUTY() {
		return U_DUTY;
	}
	public void setU_DUTY(String u_DUTY) {
		U_DUTY = u_DUTY;
	}
	public String getU_DEPARTMENT() {
		return U_DEPARTMENT;
	}
	public void setU_DEPARTMENT(String u_DEPARTMENT) {
		U_DEPARTMENT = u_DEPARTMENT;
	}
	public String getU_POSITION() {
		return U_POSITION;
	}
	public void setU_POSITION(String u_POSITION) {
		U_POSITION = u_POSITION;
	}
	public String getU_PICTURE() {
		return U_PICTURE;
	}
	public void setU_PICTURE(String u_PICTURE) {
		U_PICTURE = u_PICTURE;
	}
	public String getINCODE() {
		return INCODE;
	}
	public void setINCODE(String iNCODE) {
		INCODE = iNCODE;
	}
	public String getMOCODE() {
		return MOCODE;
	}
	public void setMOCODE(String mOCODE) {
		MOCODE = mOCODE;
	}
	public String getU_FROM() {
		return U_FROM;
	}
	public void setU_FROM(String u_FROM) {
		U_FROM = u_FROM;
	}
	public String getU_FROM_ID() {
		return U_FROM_ID;
	}
	public void setU_FROM_ID(String u_FROM_ID) {
		U_FROM_ID = u_FROM_ID;
	}
	public String getU_FROM_JOIN_DATE() {
		return U_FROM_JOIN_DATE;
	}
	public void setU_FROM_JOIN_DATE(String u_FROM_JOIN_DATE) {
		U_FROM_JOIN_DATE = u_FROM_JOIN_DATE;
	}
	public int getC_IS_COMMISSION() {
		return C_IS_COMMISSION;
	}
	public void setC_IS_COMMISSION(int c_IS_COMMISSION) {
		C_IS_COMMISSION = c_IS_COMMISSION;
	}
	public String getSESSION_ID() {
		return SESSION_ID;
	}
	public void setSESSION_ID(String sESSION_ID) {
		SESSION_ID = sESSION_ID;
	}
	public int getM_GRADE() {
		return M_GRADE;
	}
	public void setM_GRADE(int m_GRADE) {
		M_GRADE = m_GRADE;
	}
	public String getM_GRADE_NM() {
		return M_GRADE_NM;
	}
	public void setM_GRADE_NM(String m_GRADE_NM) {
		M_GRADE_NM = m_GRADE_NM;
	}
	public String getC_INFRA() {
		return C_INFRA;
	}
	public void setC_INFRA(String c_INFRA) {
		C_INFRA = c_INFRA;
	}
	public int getC_PK() {
		return C_PK;
	}
	public void setC_PK(int c_PK) {
		C_PK = c_PK;
	}
	public String getC_ID() {
		return C_ID;
	}
	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}
	@Override
	public String toString() {
		return "User [U_MOBILE_CERT_DATE=" + U_MOBILE_CERT_DATE + ", U_CERTIFY_DATE=" + U_CERTIFY_DATE
				+ ", U_REGISTER_DATE=" + U_REGISTER_DATE + ", U_CONFIRM_DATE=" + U_CONFIRM_DATE + ", U_WITHDRAW_DATE="
				+ U_WITHDRAW_DATE + ", U_MODIFY_DATE=" + U_MODIFY_DATE + ", U_LOGINDATE=" + U_LOGINDATE + ", MODATE="
				+ MODATE + ", U_PK=" + U_PK + ", U_NEWSLETTER=" + U_NEWSLETTER + ", U_TYPE=" + U_TYPE
				+ ", U_MOBILE_CERT=" + U_MOBILE_CERT + ", U_SMS=" + U_SMS + ", U_COMPANY_CERITIFY=" + U_COMPANY_CERITIFY
				+ ", U_STATUS=" + U_STATUS + ", U_PROJ_NUM=" + U_PROJ_NUM + ", U_PROJ_CONTRACT_NUM="
				+ U_PROJ_CONTRACT_NUM + ", U_PORJ_ING_NUM=" + U_PORJ_ING_NUM + ", U_PORJ_DONE_NUM=" + U_PORJ_DONE_NUM
				+ ", U_PROJ_TOTAL_PRICE=" + U_PROJ_TOTAL_PRICE + ", AGENT_ID=" + AGENT_ID + ", U_NAME=" + U_NAME
				+ ", U_ID=" + U_ID + ", U_PASSWD=" + U_PASSWD + ", U_EMAIL=" + U_EMAIL + ", U_SNS_TYPE=" + U_SNS_TYPE
				+ ", U_SNS_ID=" + U_SNS_ID + ", U_SEX=" + U_SEX + ", U_BIRTHDAY=" + U_BIRTHDAY + ", U_MOBILE="
				+ U_MOBILE + ", U_PHONE=" + U_PHONE + ", U_ZONE1=" + U_ZONE1 + ", U_ZONE2=" + U_ZONE2 + ", U_ZIPCODE="
				+ U_ZIPCODE + ", U_ADDRESS=" + U_ADDRESS + ", U_COMPANY=" + U_COMPANY + ", U_REGISTRATION_NUMBER="
				+ U_REGISTRATION_NUMBER + ", U_FOUNDER=" + U_FOUNDER + ", U_DUTY=" + U_DUTY + ", U_DEPARTMENT="
				+ U_DEPARTMENT + ", U_POSITION=" + U_POSITION + ", U_PICTURE=" + U_PICTURE + ", INCODE=" + INCODE
				+ ", MOCODE=" + MOCODE + ", U_FROM=" + U_FROM + ", U_FROM_ID=" + U_FROM_ID + ", U_FROM_JOIN_DATE="
				+ U_FROM_JOIN_DATE + ", C_IS_COMMISSION=" + C_IS_COMMISSION + ", SESSION_ID=" + SESSION_ID
				+ ", M_GRADE=" + M_GRADE + ", M_GRADE_NM=" + M_GRADE_NM + ", C_INFRA=" + C_INFRA + ", C_PK=" + C_PK
				+ ", C_ID=" + C_ID + "]";
	}
}
