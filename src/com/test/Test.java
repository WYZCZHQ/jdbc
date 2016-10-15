package com.test;
import java.sql.*;
/**
 * Created by ZhouQ on 2016/9/20.
 */
public class Test {
    public static void main( String [] args) {
        Connection con = null;// 创建一个数据库连接
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:zhq";
            con = DriverManager.getConnection(url, "SYSTEM", "123456");// 获取连接
            CallableStatement proc = con.prepareCall("{ call P_INSERT(?)}");//得到可调用的陈述
            proc.setString(1, "100");//设置第一参数
            proc.execute();//运行 调用存储过程
            proc.close();//关闭陈述
        } catch (Exception e) {
            e.printStackTrace();//把出错信息打印到控制台
        } finally {
            try {
                // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
                // 注意关闭的顺序，最后使用的最先关闭
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
