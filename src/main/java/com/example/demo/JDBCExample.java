package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class JDBCExample {
    @NotNull
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    @NotNull
    private final String DB_URL = "jdbc:mysql://kycluster-1.cluster-ce5udjo8bo5k.ap-northeast-2.rds.amazonaws.com:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    @NotNull
    private final String USER = "KYCMoa";
    @NotNull
    private final String PASS = "FpsxkfAhdk079";
    @NotNull
    private HashMap properties = new HashMap();

    @NotNull
    public final String getJDBC_DRIVER$demo() {
        return this.JDBC_DRIVER;
    }

    @NotNull
    public final String getDB_URL$demo() {
        return this.DB_URL;
    }

    @NotNull
    public final String getUSER$demo() {
        return this.USER;
    }

    @NotNull
    public final String getPASS$demo() {
        return this.PASS;
    }

    @NotNull
    public final HashMap getProperties() {
        return this.properties;
    }

    public final void setProperties(@NotNull HashMap var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.properties = var1;
    }

    public final void getBlockData(@NotNull String from, @NotNull String where, @NotNull String order, @NotNull String offset, @NotNull String select) {
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(where, "where");
        Intrinsics.checkParameterIsNotNull(order, "order");
        Intrinsics.checkParameterIsNotNull(offset, "offset");
        Intrinsics.checkParameterIsNotNull(select, "select");
        Connection conn = (Connection)null;

        String var7;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            var7 = "Connecting to a selected database...";
            //System.out.println(var7);
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
            var7 = "Connected database successfully...";
            //System.out.println(var7);
            Statement stmt = conn.createStatement();
            String sql = "SELECT " + select + " FROM " + from;
            if (Intrinsics.areEqual(where, "") ^ true) {
                sql = sql + " WHERE " + where;
            }

            if (Intrinsics.areEqual(order, "") ^ true) {
                sql = sql + " ORDER BY " + order;
            }

            if (Intrinsics.areEqual(offset, "") ^ true) {
                sql = sql + " LIMIT 1 OFFSET " + offset;
            }

            ResultSet rss = stmt.executeQuery(sql);

            while(rss.next()) {
                int i = 1;
                Intrinsics.checkExpressionValueIsNotNull(rss, "rss");
                ResultSetMetaData var10000 = rss.getMetaData();
                Intrinsics.checkExpressionValueIsNotNull(var10000, "rss.metaData");
                int var11 = var10000.getColumnCount();
                if (i <= var11) {
                    while(true) {
                        String var12 = rss.getMetaData().getColumnName(i);
                        //System.out.println(var12);
                        var12 = rss.getString(i);
                        //System.out.println(var12);
                        this.properties.put(rss.getMetaData().getColumnName(i), rss.getString(i));
                        if (i == var11) {
                            break;
                        }

                        ++i;
                    }
                }

                char var25 = '\n';
                //System.out.println(var25);
            }

            rss.close();
        } catch (SQLException var21) {
            var21.printStackTrace();
        } catch (Exception var22) {
            var22.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var20) {
                var20.printStackTrace();
            }

        }

        var7 = "Goodbye!";
        //System.out.println(var7);
    }

    // $FF: synthetic method
    public static void getBlockData$default(JDBCExample var0, String var1, String var2, String var3, String var4, String var5, int var6, Object var7) {
        if ((var6 & 1) != 0) {
            var1 = "blockdata";
        }

        if ((var6 & 2) != 0) {
            var2 = "";
        }

        if ((var6 & 4) != 0) {
            var3 = "timestamp DESC";
        }

        if ((var6 & 8) != 0) {
            var4 = "";
        }

        if ((var6 & 16) != 0) {
            var5 = "*";
        }

        var0.getBlockData(var1, var2, var3, var4, var5);
    }
}
