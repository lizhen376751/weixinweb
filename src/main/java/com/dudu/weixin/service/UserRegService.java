package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/21.
 */
@Service
public class UserRegService {
    public boolean regist(HttpServletRequest request)  {
        boolean flg = true;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String OpenId = request.getParameter("OpenId");
//        String shopcode = request.getParameter("shopcode");
//        System.out.println("==========regist shopcode:"+shopcode);
//        if(shopcode!=null && shopcode.length()>7) shopcode=shopcode.substring(0,7);
//        String XingMing = request.getParameter("XingMing");
//        String XingBie = request.getParameter("XingBie");
//        String CarId1 = request.getParameter("CarId");
//        String password = request.getParameter("password");
//        String CarId = CarId1.toUpperCase();
//        String Phone = request.getParameter("Phone");
//
//        String CarPaiLiang = request.getParameter("CarPaiLiang");
//        String appDate = request.getParameter("appDate");
//
//        String chexing = request.getParameter("chexing");
//
//        String CarPinPai = request.getParameter("CarPinPai");
//        CarPinPai = (CarPinPai != null && CarPinPai.length() == 0) ? null
//                : CarPinPai;
//        String Carxi = request.getParameter("Carxi");
//        Carxi = (Carxi != null && Carxi.length() == 0) ? null : Carxi;
//        String CarType = request.getParameter("CarType");
//        String newCarType = CarType;
//        newCarType = (newCarType != null && newCarType.length() == 0) ? null
//                : newCarType;
//
//        HttpSession session = request.getSession();
//
//        DBBase dbBase = new DBBase();
//        String sqlShop = "select datasource from DATA_shop where code = '"
//                + shopcode + "'";
//        HashMap shopMap = dbBase.queryOne(sqlShop);
//        String datasource = (String) shopMap.get("datasource");
//        session.setAttribute("datasource", datasource);
//        DBbd db = new DBbd();
//        DBShop DBShop = new DBShop();
//        try {
//            // �ж�һ���û��Ƿ����
//            StringBuffer sbSql = new StringBuffer();
//            sbSql.append("SELECT ID,UserPass FROM UserInfo WHERE UserName='" + CarId
//                    + "' AND shopcode='" + shopcode + "'");
//            ArrayList list = new ArrayList();
//            System.out.println("-----���ܼ�ע����֤�Ƿ��Ѵ���sql��"+sbSql.toString());
//            list = db.query(sbSql.toString());
//
//            if(shopcode!=null && shopcode.length()>7) shopcode=shopcode.substring(0,7);
//            if (list != null && list.size() > 0) {
////				throw new Exception("����ע�ᣬ�����ظ�������");
//
//
//                //ע���ʱ���ж����Ƿ��ڵ�ܼ��д���(��ܼҿ��ܻ�ӿͻ�������ɾ��),�����������
//                sbSql = new StringBuffer();
//                sbSql.append("select id from Data_Customer_Info where (shopcode='"+shopcode+"' or shopcode in (select shopcode2 from DATA_Shop_Shop where shopcode1='"+shopcode+"')) and CarHaoPai='"+CarId+"' ");
//                System.out.println("--------------ע��  ΢���û��Ѵ���  ����ܼ��Ƿ����sql��"+sbSql.toString());
//                ArrayList listShop = DBShop.query(sbSql.toString());
//                String sex="";
//                if("��".equals(XingBie)) sex="1";
//                if("Ů".equals(XingBie)) sex="0";
//                if (listShop != null && listShop.size() > 0) {
//                    sbSql = new StringBuffer();
//                    sbSql.append("update  Data_Customer_Info set "
//                            + " new_car_brand ='"+CarPinPai+"' "
//                            + " ,new_car_series='"+Carxi+"' "
//                            + " ,new_car_model='"+newCarType+"' "
//                            + ",CheXing='"+chexing+ "' "
//                            + " ,sex='" + sex+ "' "
//                            + " where CarHaoPai= '"+CarId+"' "
//                            + "and (shopcode='"+shopcode+"' or shopcode in (select shopcode2 from data_shop_shop where shopcode1='"+shopcode+"'))  ");
//                    System.out.println("----------�޸ĵ�ܼҿͻ���Ϣsql��"+sbSql.toString());
//                    DBShop.update(sbSql.toString());
//                } else {
//                    // System.out.println("((((((((((((((((((((((((((");
//                    GetAllBaseName baseName = new GetAllBaseName();
//                    String mainShopCode = baseName.getMainShopcode(DBShop, shopcode);
//                    sbSql = new StringBuffer();
//                    sbSql.append(
//                            "insert into Data_Customer_Info (shopcode,sex,CreatedBy,CustomerName,CustomerMobile,Notes,CarHaoPai,CarChexing,pailiang,CheXing,DengJiDate,new_car_brand,new_car_series,new_car_model,mainShopcode) values ")
//                            .append("('" + shopcode + "','"+sex+"','25','" + XingMing
//                                    + "','" + Phone + "','΢��ע��','" + CarId
//                                    + "','" + CarType + "','" + CarPaiLiang
//                                    + "','" + chexing + "','" + appDate + "',"
//                                    + CarPinPai + "," + Carxi + ","
//                                    + newCarType + ",'" + mainShopCode + "')");
//                    System.out.println("----------��ӵ�ܼҿͻ���Ϣsql��"+sbSql.toString());
//                    DBShop.update(sbSql.toString());
//                    // System.out.println("(((((((((((((((((((((((((())))))))))))))))))");
//                }
//
//
//                //��ע���ʱ���ж�userinfo���пͻ�״̬�Ƿ������ϣ�������ϸ�Ϊ�� UserInfo
//                db.update("update UserInfo set State='1' WHERE UserName='"+CarId+"' AND shopcode='"+shopcode+"' ");
//
//
//                session.setAttribute("DUDUCHEWANG_CarId", CarId);
//                session.setAttribute("DUDUCHEWANG_OpenId", OpenId);
//                session.setAttribute("DUDUCHEWANG_shopcode", shopcode);
//
//                flg = true;
//
//            } else {
//                // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                // ������ ����ע��
//                sbSql = new StringBuffer();
//                sbSql.append(
//                        "INSERT INTO UserInfo (UserName,UserPass,Mobile,Brand,RecordDate,OpenId,OpenU,shopcode,XingMing,XingBie,CarPaiLiang,updateTime")
//                        .append(") VALUES ('" + CarId + "','" + password
//                                + "','" + Phone + "','" + chexing + "','"
//                                + appDate + "','" + OpenId + "','" + OpenId
//                                + "',")
//                        .append("'" + shopcode + "','" + XingMing + "','"
//                                + XingBie + "','" + CarPaiLiang + "','"
//                                + format.format(new Date()) + "')");
//
//                db.update(sbSql.toString());
//
//                // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!222222222222222");
//                sbSql = new StringBuffer();
//                sbSql.append("SELECT TOP 1 ID FROM UserInfo WHERE shopcode='" + shopcode + "' and OpenId='" + OpenId + "' ORDER BY ID DESC");
//                HashMap hm = db.queryOne(sbSql.toString());
//                String strUserId = (String) hm.get("ID");
//                // �ж��û��Ƿ��ע�˹����˺�
//                if (strUserId != null && !"null".equals(strUserId)
//                        && !"".equals(strUserId)) {
//                    sbSql = new StringBuffer();
//                    sbSql.append("SELECT CODE FROM winData WHERE shopcode='"
//                            + shopcode + "' and token='" + OpenId
//                            + "' and userID='0'");
//
//                    ArrayList listWin = new ArrayList();
//                    listWin = db.query(sbSql.toString());
//                    if (listWin != null && listWin.size() > 0) {
//                        sbSql = new StringBuffer();
//                        sbSql.append("UPDATE winData SET userID='" + strUserId
//                                + "' WHERE shopcode='" + shopcode
//                                + "' AND token='" + OpenId + "' and userID='0'");
//                        db.update(sbSql.toString());
//                    }
//                }
//
//                ArrayList listShop = new ArrayList();
//                sbSql = new StringBuffer();
//                sbSql.append("select id from DATA_Shop_Shop where shopcode1='"
//                        + shopcode + "'");
//                ArrayList listshop = new ArrayList();
//                listshop = DBShop.query(sbSql.toString());
//
//                if (listshop != null && listshop.size() > 0) {
//                    sbSql = new StringBuffer();
//                    sbSql.append("select id from Data_Customer_Info where (shopcode='" + shopcode + "' or shopcode in (select shopcode2 from DATA_Shop_Shop where shopcode1='" + shopcode + "')) and CarHaoPai='" + CarId + "' ");
//
//                    listShop = DBShop.query(sbSql.toString());
//                } else {
//                    sbSql = new StringBuffer();
//                    sbSql.append("select id from Data_Customer_Info where shopcode='" + shopcode + "' and CarHaoPai='" + CarId + "' ");
//
//                    listShop = DBShop.query(sbSql.toString());
//                }
//
//
//
//                String sex="";
//                if("��".equals(XingBie)) sex="1";
//                if("Ů".equals(XingBie)) sex="0";
//                if (listShop != null && listShop.size() > 0) {
//                    sbSql = new StringBuffer();
//                    sbSql.append("update  Data_Customer_Info set "
//                            + " new_car_brand ='"+CarPinPai+"' "
//                            + " ,new_car_series='"+Carxi+"' "
//                            + " ,new_car_model='"+newCarType+"' "
//                            + ",CheXing='"+chexing+ "' "
//                            + " ,sex='" + sex+ "' "
//                            + " where CarHaoPai= '"+CarId+"' "
//                            + "and (shopcode='"+shopcode+"' or shopcode in (select shopcode2 from data_shop_shop where shopcode1='"+shopcode+"'))  ");
//                    DBShop.update(sbSql.toString());
//                } else {
//                    // System.out.println("((((((((((((((((((((((((((");
//                    GetAllBaseName baseName = new GetAllBaseName();
//                    String mainShopCode = baseName.getMainShopcode(DBShop, shopcode);
//                    sbSql = new StringBuffer();
//                    sbSql.append(
//                            "insert into Data_Customer_Info (shopcode,sex,CreatedBy,CustomerName,CustomerMobile,Notes,CarHaoPai,CarChexing,pailiang,CheXing,DengJiDate,new_car_brand,new_car_series,new_car_model,mainShopcode) values ")
//                            .append("('" + shopcode + "','"+sex+"','25','" + XingMing
//                                    + "','" + Phone + "','΢��ע��','" + CarId
//                                    + "','" + CarType + "','" + CarPaiLiang
//                                    + "','" + chexing + "','" + appDate + "',"
//                                    + CarPinPai + "," + Carxi + ","
//                                    + newCarType + ",'" + mainShopCode + "')");
//                    DBShop.update(sbSql.toString());
//                    // System.out.println("(((((((((((((((((((((((((())))))))))))))))))");
//                }
//
//                session.setAttribute("DUDUCHEWANG_CarId", CarId);
//                session.setAttribute("DUDUCHEWANG_OpenId", OpenId);
//                session.setAttribute("DUDUCHEWANG_shopcode", shopcode);
//
//                flg = true;
//            }
//        } catch (Exception e) {
//            throw new Exception(e);
//        } finally {
//            dbBase.close();
//            DBShop.close();
//            db.close();
//        }

        return flg;
    }

    /**
     * ȡ��Ʒ��
     *
     * @return
     * @throws Exception
     */
    public ArrayList getCarList()  {
//        ArrayList listCar = new ArrayList();
//        StringBuffer sbSQL = new StringBuffer();
//        sbSQL.append("select car_id,car_name from data_car_type where car_fid='0' ");
//        DBBase db = new DBBase();
//        try {
//            listCar = db.query(sbSQL.toString());
//            db.close();
//        } catch (Exception e) {
//            db.close();
//            throw new Exception(e);
//        } finally {
//            db.close();
//        }

//        return listCar;
        return null;
    }

    /**
     * ȡ�����ͺ�
     *
     * @return
     * @throws Exception
     */
    public ArrayList getCheXingList() {
//        ArrayList listChexing = new ArrayList();
//        StringBuffer sbSQL = new StringBuffer();
//        sbSQL.append("select car_id,car_name ,car_fid from data_car_type ");
//        DBBase db = new DBBase();
//        try {
//            listChexing = db.query(sbSQL.toString());
//            db.close();
//        } catch (Exception e) {
//            db.close();
//            throw new Exception(e);
//        } finally {
//            db.close();
//        }
//
//        return listChexing;
//    }
    return null;
}

}
