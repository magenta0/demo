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
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class JDBCgetValue {
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

    public final void getBlockData(@NotNull String where) {
        Intrinsics.checkParameterIsNotNull(where, "where");
        Connection conn = (Connection)null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
            Statement stmt = conn.createStatement();
            String sql = " select utxof.* from transactiondata tr join ( select tr.hash as txid,ut.address as recv_address,ut.value,ut.p_address as senderAddress from transactiondata tr join utxo ut on tr.hash = ut.txid ) utxof on tr.hash=utxof.txid and recv_address != senderAddress where hash = '" ;
            sql = sql + where + "'";
            ResultSet rss = stmt.executeQuery(sql);
            String AddrOne = "";
            String AddrTwo = "";
            BigDecimal var10000 = BigDecimal.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(var10000, "BigDecimal.ZERO");
            BigDecimal ValOne = var10000;
            var10000 = BigDecimal.ZERO;
            Intrinsics.checkExpressionValueIsNotNull(var10000, "BigDecimal.ZERO");
            BigDecimal ValTwo = var10000;

            while(true) {
                int i;
                int var11;
                do {
                    if (!rss.next()) {
                        if (ValOne.compareTo(BigDecimal.ZERO) > 0) {
                            this.properties.put("toAddress", AddrOne);
                            this.properties.put("fromAddress", AddrTwo);
                            this.properties.put("value", ValOne.toString());
                        } else if (ValOne.compareTo(BigDecimal.ZERO) < 0) {
                            this.properties.put("fromAddress", AddrOne);
                            this.properties.put("toAddress", AddrTwo);
                            this.properties.put("value", ValTwo.toString());
                        }

                        rss.close();
                        return;
                    }

                    i = 1;
                    Intrinsics.checkExpressionValueIsNotNull(rss, "rss");
                    ResultSetMetaData var25 = rss.getMetaData();
                    Intrinsics.checkExpressionValueIsNotNull(var25, "rss.metaData");
                    var11 = var25.getColumnCount();
                } while(i > var11);

                while(true) {
                    if (Intrinsics.areEqual(AddrOne, "") || Intrinsics.areEqual(AddrTwo, "")) {
                        String var26;
                        if (Intrinsics.areEqual("recv_address", rss.getMetaData().getColumnName(i))) {
                            var26 = rss.getString(i);
                            Intrinsics.checkExpressionValueIsNotNull(var26, "rss.getString(i)");
                            AddrOne = var26;
                        } else if (Intrinsics.areEqual("senderAddress", rss.getMetaData().getColumnName(i))) {
                            var26 = rss.getString(i);
                            Intrinsics.checkExpressionValueIsNotNull(var26, "rss.getString(i)");
                            AddrTwo = var26;
                        }
                    }

                    BigDecimal var13;
                    if (Intrinsics.areEqual("recv_address", rss.getMetaData().getColumnName(i))) {
                        if (Intrinsics.areEqual(AddrOne, rss.getString(i))) {
                            var10000 = rss.getBigDecimal("value");
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "rss.getBigDecimal(\"value\")");
                            var13 = var10000;
                            var10000 = ValOne.add(var13);
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "this.add(other)");
                            ValOne = var10000;
                        } else if (Intrinsics.areEqual(AddrTwo, rss.getString(i))) {
                            var10000 = rss.getBigDecimal("value");
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "rss.getBigDecimal(\"value\")");
                            var13 = var10000;
                            var10000 = ValTwo.add(var13);
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "this.add(other)");
                            ValTwo = var10000;
                        }
                    } else if (Intrinsics.areEqual("senderAddress", rss.getMetaData().getColumnName(i))) {
                        if (Intrinsics.areEqual(AddrOne, rss.getString(i))) {
                            var10000 = rss.getBigDecimal("value");
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "rss.getBigDecimal(\"value\")");
                            var13 = var10000;
                            var10000 = ValOne.subtract(var13);
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "this.subtract(other)");
                            ValOne = var10000;
                        } else if (Intrinsics.areEqual(AddrTwo, rss.getString(i))) {
                            var10000 = rss.getBigDecimal("value");
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "rss.getBigDecimal(\"value\")");
                            var13 = var10000;
                            var10000 = ValTwo.subtract(var13);
                            Intrinsics.checkExpressionValueIsNotNull(var10000, "this.subtract(other)");
                            ValTwo = var10000;
                        }
                    }

                    if (i == var11) {
                        break;
                    }

                    ++i;
                }
            }
        } catch (SQLException var22) {
            var22.printStackTrace();
        } catch (Exception var23) {
            var23.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var21) {
                var21.printStackTrace();
            }

        }

    }

    // $FF: synthetic method
    public static void getBlockData$default(JDBCgetValue var0, String var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = "";
        }

        var0.getBlockData(var1);
    }
}
