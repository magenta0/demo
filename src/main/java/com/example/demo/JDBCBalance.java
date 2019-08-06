package com.example.demo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class JDBCBalance {
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
    public final String getJDBC_DRIVER$demo_main() {
        return this.JDBC_DRIVER;
    }

    @NotNull
    public final String getDB_URL$demo_main() {
        return this.DB_URL;
    }

    @NotNull
    public final String getUSER$demo_main() {
        return this.USER;
    }

    @NotNull
    public final String getPASS$demo_main() {
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

    public final void getBalance(@NotNull String where) {
        Intrinsics.checkParameterIsNotNull(where, "where");
        Connection conn = (Connection)null;

        String var3;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            var3 = "Connecting to a selected database...";
            System.out.println(var3);
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
            var3 = "Connected database successfully...";
            System.out.println(var3);
            Statement stmt = conn.createStatement();
            String sql = "SELECT value FROM utxo where address = '";
            String sqlsender = "SELECT value FROM utxo where p_address = '";
            sql = sql + where + "'";
            sqlsender = sqlsender + where + "'";
            ResultSet rss = stmt.executeQuery(sql);
            BigDecimal var10000 = BigDecimal.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(var10000, "BigDecimal.ZERO");
            BigDecimal sum = var10000;
            var10000 = BigDecimal.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(var10000, "BigDecimal.ZERO");
            BigDecimal sums = var10000;

            label153:
            while(true) {
                int i;
                do {
                    ResultSetMetaData var31;
                    if (!rss.next()) {
                        rss.close();
                        ResultSet rsss = stmt.executeQuery(sqlsender);

                        while(true) {
                            int var11;
                            do {
                                if (!rsss.next()) {
                                    rsss.close();
                                    var10000 = sum.subtract(sums);
                                    Intrinsics.checkExpressionValueIsNotNull(var10000, "this.subtract(other)");
                                    String var30 = var10000.toString();
                                    System.out.println(var30);
                                    String var15 = "balance";
                                    HashMap var14 = this.properties;
                                    var10000 = sum.subtract(sums);
                                    Intrinsics.checkExpressionValueIsNotNull(var10000, "this.subtract(other)");
                                    BigDecimal var16 = var10000;
                                    var14.put(var15, var16.toString());
                                    break label153;
                                }

                                i = 1;
                                Intrinsics.checkExpressionValueIsNotNull(rsss, "rsss");
                                var31 = rsss.getMetaData();
                                Intrinsics.checkExpressionValueIsNotNull(var31, "rsss.metaData");
                                var11 = var31.getColumnCount();
                            } while(i > var11);

                            while(true) {
                                var10000 = rsss.getBigDecimal(i);
                                Intrinsics.checkExpressionValueIsNotNull(var10000, "rsss.getBigDecimal(i)");
                                BigDecimal var13 = var10000;
                                var10000 = sums.add(var13);
                                Intrinsics.checkExpressionValueIsNotNull(var10000, "this.add(other)");
                                sums = var10000;
                                if (i == var11) {
                                    break;
                                }

                                ++i;
                            }
                        }
                    }

                    i = 1;
                    Intrinsics.checkExpressionValueIsNotNull(rss, "rss");
                    var31 = rss.getMetaData();
                    Intrinsics.checkExpressionValueIsNotNull(var31, "rss.metaData");
                    i = var31.getColumnCount();
                } while(i > i);

                while(true) {
                    var10000 = rss.getBigDecimal(i);
                    Intrinsics.checkExpressionValueIsNotNull(var10000, "rss.getBigDecimal(i)");
                    BigDecimal var12 = var10000;
                    var10000 = sum.add(var12);
                    Intrinsics.checkExpressionValueIsNotNull(var10000, "this.add(other)");
                    sum = var10000;
                    if (i == i) {
                        break;
                    }

                    ++i;
                }
            }
        } catch (SQLException var25) {
            var25.printStackTrace();
        } catch (Exception var26) {
            var26.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var24) {
                var24.printStackTrace();
            }

        }

        var3 = "Goodbye!";
        System.out.println(var3);
    }

    // $FF: synthetic method
    public static void getBalance$default(JDBCBalance var0, String var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = "";
        }

        var0.getBalance(var1);
    }
}
