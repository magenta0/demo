// indexMapping.java
package com.example.demo;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.web.bind.annotation.RestController;

import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;


import java.security.Principal;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping({"/"})
class indexMapping {
    @GetMapping
    @NotNull
    public String mapping(@RequestParam(value = "list",defaultValue = "main",required = false) @NotNull String showMe, @RequestParam(value = "height",defaultValue = "",required = false) @NotNull String height, @RequestParam(value = "hash",defaultValue = "",required = false) @NotNull String hash, @RequestParam(value = "address",defaultValue = "",required = false) @NotNull String address, @RequestParam(value = "timestamp",defaultValue = "",required = false) @NotNull String timestamp, @NotNull Model model) {
        Intrinsics.checkParameterIsNotNull(showMe, "showMe");
        Intrinsics.checkParameterIsNotNull(height, "height");
        Intrinsics.checkParameterIsNotNull(hash, "hash");
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(timestamp, "timestamp");
        Intrinsics.checkParameterIsNotNull(model, "model");
        model.addAttribute("navis", struct.INSTANCE.getNavis());
        model.addAttribute("listOptions", struct.INSTANCE.getList());
        model.addAttribute("showMe", showMe);
        if (Intrinsics.areEqual(hash, "") ^ true) {
            model.addAttribute("hash", "hash=" + hash);
        } else if (Intrinsics.areEqual(address, "") ^ true) {
            model.addAttribute("hash", "address=" + address);
        } else if (Intrinsics.areEqual(timestamp, "") ^ true) {
            model.addAttribute("hash", "timestamp=" + timestamp);
        } else if (Intrinsics.areEqual(height, "") ^ true) {
            model.addAttribute("hash", "height=" + height);
        } else {
            model.addAttribute("hash", "");
        }

        return "index";
    }
}

@RestController
@RequestMapping({"/json/address"})
class jsonAddressMapping {
    @GetMapping({"/page"})
    @NotNull
    public HashMap mapping(@RequestParam(value = "address",defaultValue = "",required = false) @NotNull String address, @RequestParam(value = "page",defaultValue = "1",required = false) int page) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        JDBCExample25 jdbcExample25 = new JDBCExample25();
        jdbcExample25.getPropertieses().clear();
        JDBCExample25.getBlockData$default(jdbcExample25, "transactiondata", "toAddress = '" + address + "' OR fromAddress = '" + address + "'", "timestamp DESC", String.valueOf((page - 1) * 25), (String)null, 16, (Object)null);
        return jdbcExample25.getPropertieses();
    }

    @GetMapping({"/count"})
    @NotNull
    public HashMap count(@RequestParam(value = "address",defaultValue = "",required = false) @NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        JDBCExample jdbcExample = new JDBCExample();
        jdbcExample.getProperties().clear();
        jdbcExample.getBlockData("transactiondata", "toAddress = '" + address + "' OR fromAddress = '" + address + "'", "timestamp DESC", "0", "count(timestamp) as totalTx");
        return jdbcExample.getProperties();
    }

    @GetMapping({"/balance"})
    @NotNull
    public HashMap balance(@RequestParam(value = "address",defaultValue = "",required = false) @NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        JDBCBalance jdbcBalance = new JDBCBalance();
        jdbcBalance.getProperties().clear();
        jdbcBalance.getBalance(address);
        return jdbcBalance.getProperties();
    }
}
// jsonMapping.java
@RestController
@RequestMapping({"/json"})
class jsonMapping {
    @GetMapping
    @NotNull
    public Block mapping(@RequestParam(value = "dataset",defaultValue = "blockdata",required = false) @NotNull String dataSet, @RequestParam(value = "hash",defaultValue = "",required = false) @NotNull String hash, @RequestParam(value = "height",defaultValue = "",required = false) @NotNull String height, @RequestParam(value = "timestamp",defaultValue = "",required = false) @NotNull String timestamp, @RequestParam(value = "timestamp2",defaultValue = "",required = false) @NotNull String timestamp2, @RequestParam(value = "address",defaultValue = "",required = false) @NotNull String address, @RequestParam(value = "next",defaultValue = "0",required = false) @NotNull String next, @RequestParam(value = "balance",defaultValue = "0",required = false) @NotNull String balance, @RequestParam(value = "page",defaultValue = "0",required = false) @NotNull String page, @RequestParam(value = "find_child",defaultValue = "",required = false) @NotNull String find_child) {
        Intrinsics.checkParameterIsNotNull(dataSet, "dataSet");
        Intrinsics.checkParameterIsNotNull(hash, "hash");
        Intrinsics.checkParameterIsNotNull(height, "height");
        Intrinsics.checkParameterIsNotNull(timestamp, "timestamp");
        Intrinsics.checkParameterIsNotNull(timestamp2, "timestamp2");
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(next, "next");
        Intrinsics.checkParameterIsNotNull(balance, "balance");
        Intrinsics.checkParameterIsNotNull(page, "page");
        Intrinsics.checkParameterIsNotNull(find_child, "find_child");
        Block b = new Block();
        JDBCExample jdbcexaple = new JDBCExample();
        JDBCBalance jdbcbalance = new JDBCBalance();
        jdbcexaple.getProperties().clear();
        if (Intrinsics.areEqual(dataSet, "blockdata")) {
            if (Intrinsics.areEqual(next, "-1")) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "timestamp >= " + timestamp, "timestamp ASC", "1", (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(hash, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "hash = '" + hash + "'", "", next, (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(height, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "height = " + height, "", next, (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(timestamp, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "timestamp <= " + timestamp, "timestamp DESC", next, (String)null, 16, (Object)null);
            } else {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "", "timestamp DESC", next, (String)null, 16, (Object)null);
            }
        } else if (Intrinsics.areEqual(dataSet, "transactiondata")) {
            if (Intrinsics.areEqual(next, "-1")) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "timestamp >= " + timestamp, "timestamp ASC", "1", (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(balance, "1")) {
                jdbcbalance.getBalance(address);
            } else if (Intrinsics.areEqual(hash, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "hash = '" + hash + "'", "", next, (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(timestamp2, "") ^ true) {
                jdbcexaple.getBlockData(dataSet, "timestamp >= " + timestamp + " AND timestamp < " + timestamp2, "", "", "COUNT(timestamp) AS count");
            } else if (Intrinsics.areEqual(timestamp, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "timestamp <= " + timestamp, "timestamp DESC", next, (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(address, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "toAddress = '" + address + "' OR fromAddress = '" + address + "'", "timestamp DESC", next, (String)null, 16, (Object)null);
            } else {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "", "timestamp DESC", next, (String)null, 16, (Object)null);
            }
        } else if (Intrinsics.areEqual(dataSet, "block_prevhash")) {
            if (Intrinsics.areEqual(find_child, "1")) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "blockprevhash = '" + hash + "'", "", next, (String)null, 16, (Object)null);
            } else if (Intrinsics.areEqual(hash, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "blockhash = '" + hash + "'", "", next, (String)null, 16, (Object)null);
            } else {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "", "", next, (String)null, 16, (Object)null);
            }
        } else if (Intrinsics.areEqual(dataSet, "utxo")) {
            if (Intrinsics.areEqual(next, "-1")) {
                jdbcexaple.getBlockData(dataSet, "txid = '" + hash + "'", "", "0", "SUM(value) AS value");
            } else if (Intrinsics.areEqual(hash, "") ^ true) {
                JDBCExample.getBlockData$default(jdbcexaple, dataSet, "txid = '" + hash + "'", "", next, (String)null, 16, (Object)null);
            }
        } else if (Intrinsics.areEqual(dataSet, "exchange_coin")) {
            JDBCExample.getBlockData$default(jdbcexaple, dataSet, "", "", next, (String)null, 16, (Object)null);
        } else {
            Intrinsics.areEqual(dataSet, "");
        }

        b.getProperties().clear();
        if (jdbcbalance.getProperties().get("balance") != null) {
            Map var16 = (Map)jdbcbalance.getProperties();
            Iterator var15 = var16.entrySet().iterator();

            while(var15.hasNext()) {
                Entry mmap = (Entry)var15.next();
                if (Intrinsics.areEqual((String)mmap.getKey(), "balance")) {
                    b.getProperties().put("balance", mmap.getValue());
                }
            }
        }

        String hhh = (String)jdbcexaple.getProperties().get("hash");
        if (hhh != null && Intrinsics.areEqual(dataSet, "transactiondata")) {
            JDBCgetValue JDBCValue = new JDBCgetValue();
            JDBCValue.getBlockData(hhh);
            Map var18 = (Map)JDBCValue.getProperties();
            Iterator var17 = var18.entrySet().iterator();

            while(var17.hasNext()) {
                Entry mmap = (Entry)var17.next();
                if (Intrinsics.areEqual((String)mmap.getKey(), "value")) {
                    jdbcexaple.getProperties().put("amount", mmap.getValue());
                } else if (Intrinsics.areEqual((String)mmap.getKey(), "toAddress")) {
                    jdbcexaple.getProperties().put("toAddress", mmap.getValue());
                } else if (Intrinsics.areEqual((String)mmap.getKey(), "fromAddress")) {
                    jdbcexaple.getProperties().put("fromAddress", mmap.getValue());
                }
            }
        }

        Map var24 = (Map)jdbcexaple.getProperties();
        Iterator var23 = var24.entrySet().iterator();

        while(true) {
            while(var23.hasNext()) {
                Entry map = (Entry)var23.next();
                CharSequence var25 = (CharSequence)map.getValue();
                if (var25 == null || var25.length() == 0) {
                    b.getProperties().put(map.getKey(), "none");
                } else if (!Intrinsics.areEqual((String)map.getKey(), "hash") && !Intrinsics.areEqual((String)map.getKey(), "blockhash") && !Intrinsics.areEqual((String)map.getKey(), "blockprevhash") && !Intrinsics.areEqual((String)map.getKey(), "tr_hash") && !Intrinsics.areEqual((String)map.getKey(), "use_transaction_hash") && !Intrinsics.areEqual((String)map.getKey(), "txid")) {
                    if (!Intrinsics.areEqual((String)map.getKey(), "toAddress") && !Intrinsics.areEqual((String)map.getKey(), "fromAddress") && !Intrinsics.areEqual((String)map.getKey(), "address") && !Intrinsics.areEqual((String)map.getKey(), "p_address") && !Intrinsics.areEqual((String)map.getKey(), "mining_address") && !Intrinsics.areEqual((String)map.getKey(), "creator")) {
                        b.getProperties().put(map.getKey(), map.getValue());
                    } else {
                        b.getProperties().put(map.getKey(), b.addressTohash((String)map.getValue()));
                    }
                } else if (!Intrinsics.areEqual(dataSet, "transactiondata") && !Intrinsics.areEqual(dataSet, "exchange_coin") && !Intrinsics.areEqual(dataSet, "utxo")) {
                    b.getProperties().put(map.getKey(), b.hashTohash((String)map.getValue()));
                } else {
                    b.getProperties().put(map.getKey(), b.hashTotxhash((String)map.getValue()));
                }
            }

            return b;
        }
    }
}
// struct.java
final class struct {
    @NotNull
    private static final Map navis;
    @NotNull
    private static final String[] list;
    @NotNull
    private static final String lasthash = "0xabcdef012";
    public static final struct INSTANCE;

    @NotNull
    public final Map getNavis() {
        return navis;
    }

    @NotNull
    public final String[] getList() {
        return list;
    }

    @NotNull
    public final String getLasthash() {
        return lasthash;
    }

    private struct() {
    }

    static {
        struct var0 = new struct();
        INSTANCE = var0;
        navis = MapsKt.mapOf(new Pair[]{TuplesKt.to("main", "<a href=\"?list=main\" style=\"font-size: 18px\"><i><img class=\"nav-icon\" src=\"assets/img/그룹 241.svg\"></img></i>main </a>"),
                TuplesKt.to("DAG", "<a style=\"font-size: 18px\" href=\"?list=DAG\"><i><img class=\"nav-icon\" src=\"assets/img/그룹 242.svg\"></img></i>DAG </a>"),
                TuplesKt.to("Block_list", "<a style=\"font-size: 18px\" href=\"?list=Block_list\"><i><img class=\"nav-icon\" src=\"assets/img/그룹 243.svg\"></img></i>Block list </a>"),
                TuplesKt.to("Transaction_list", "<a style=\"font-size: 18px\" href=\"?list=Transaction_list\"><i><img class=\"nav-icon\" src=\"assets/img/그룹 244.svg\"></img></i>Transaction list </a>"),
                TuplesKt.to("address", "<a style=\"font-size: 18px\" href=\"?list=address\"><i><img class=\"nav-icon\" src=\"assets/img/그룹 244.svg\"></img></i>Transactions by address </a>"),
                TuplesKt.to("smartContract", "<a style=\"font-size: 18px\" href=\"?list=Smart_Contract\"><i><img class=\"nav-icon\" src=\"assets/img/그룹 245.svg\"></img></i>Smart Contract List </a>")});
        list = new String[]{"main", "Block_list", "Block_item", "DAG", "Transaction_list", "transaction_graph", "address", "Transaction_item", "Smart_Contract"};
    }
}

@Controller
@RequestMapping({"/address"})
class addressHtml {
    @GetMapping
    @NotNull
    public String mapping(@RequestParam(value = "address",defaultValue = "",required = false) @NotNull String address, @RequestParam(value = "page",defaultValue = "1",required = false) @NotNull String page, @NotNull Model model) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(page, "page");
        Intrinsics.checkParameterIsNotNull(model, "model");
        if (Intrinsics.areEqual(address, "") ^ true) {
            model.addAttribute("address", address);
        }

        if (Intrinsics.areEqual(page, "") ^ true) {
            model.addAttribute("page", page);
        }

        return "neoAddress";
    }
}
// GreetingController.java
@Controller
public class GreetingController {
    @GetMapping({"/user"})
    @ResponseBody
    @NotNull
    public String currentUserName(@NotNull Principal principal, @NotNull Model model) {
        Intrinsics.checkParameterIsNotNull(principal, "principal");
        Intrinsics.checkParameterIsNotNull(model, "model");
        model.addAttribute("name", principal.getName());
        String var10000 = principal.getName();
        Intrinsics.checkExpressionValueIsNotNull(var10000, "principal.getName()");
        return var10000;
    }

    @MessageMapping({"/hello"})
    @SendTo({"/topic/greetings"})
    @NotNull
    public Greeting greeting(@NotNull HelloMessage message) throws Exception {
        Intrinsics.checkParameterIsNotNull(message, "message");
        //Greeting var10000 = new Greeting();
        StringBuilder var10002 = (new StringBuilder()).append("Hello, ");
        String var10003 = message.getName();
        if (var10003 == null) {
            Intrinsics.throwNpe();
        }


        return new Greeting(var10002.append(HtmlUtils.htmlEscape(var10003)).append("!").toString());
    }

    @GetMapping({"/web_socket"})
    @NotNull
    public String index() {
        return "web_socket";
    }

    @GetMapping({"/getMessage"})
    @NotNull
    public String getMessage(@NotNull Model model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        model.addAttribute("message", "mmm");
        return "index";
    }
}
