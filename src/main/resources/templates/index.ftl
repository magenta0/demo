<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Block Info</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
    <script src="jquery-3.4.1.min.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>

    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
</head>
<body>



<div id="wrapper">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="adjust-nav">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".sidebar-collapse">
                    <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a href="https://www.moaplatform.io"> <img src="assets/img/그룹 23.svg" />
                </a>

            </div>

            <span class="logout-spn"> <a href="#" style="color: #fff;"></a>

				</span>
        </div>
    </div>
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
            <!--
                <li class="active-link">
                    <a href="wallet.html"><i class="fa fa-desktop "></i>지갑<span class="badge">Included</span></a>
                </li>
            -->
            <#list navis as key, val>
                <#if showMe == key>
                <li class="active-link">
            <#else>
                <li style="font-size: 18px">
            </#if>
                    ${val}
                </li>
            </#list>

            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">

        <script>
            var filter = ['all'];
            var Tfilter = ['toAddress', 'fromAddress', 'timestamp', 'hash', 'amount'];

            var nodeCount = 20;
            var nodePlusCount = 5;

            var TnodeCount = 20;
            var TnodePlusCount = 5;
        </script>


        <#if showMe != "main">
        <div id="page-inner" style="overflow-x: scroll; padding: 0;">
            <div class="row">
                <div class="col-lg-12">
                    <h1 style="
                        font-weight: 700;
                        margin-top: 50px;
                        font-size: 24px;
                        color: #1D4670;
                    ">${showMe}</h1>
                    <h3 style="
                        font-size: 18px;
                        color: #1D4670;
                    ">${hash}</h3>
                </div>
            </div>
            <hr />
        <#list listOptions as Op>
        <#if showMe == "MVPdata">
            <#include "MVPindex/" + showMe + ".html">
        <#elseif showMe == Op>
            <#include "index/" + showMe + ".html">
        </#if>
        </#list>
        </div>
        <#else>
        <#include "index/" + showMe + ".html">
        </#if>

    </div>
    <!-- /. PAGE INNER  -->
</div>
<!-- /. PAGE WRAPPER  -->

<!-- Footer -->
<div class="footer">


    <div class="row" style="margin: 0;">
        <div class="row" style="height: auto; padding: 0; border: 0; margin: 0;">
            <div class="col-sm-3 col-sm-push-9" style= "padding: 0; border: 0; margin: 0; margin-top:12px">
                <div style="
                                    position: absolute;
                                    right: 10px;
                                    display: flex;
                                    flex-wrap: nowrap;
                                    -webkit-flex-wrap: nowrap; /* Safari 6.1+ */">
                    <a href="mailto:support@moaplatform.io">
                    <img class="imglink" src="assets/img/img_05.png" />
                    </a>
                    <a href="https://www.facebook.com/MoaPlat/">
                    <img class="imglink" src="assets/img/img_04.png" />
                    </a>
                    <a href="https://twitter.com/moaplatform">
                    <img class="imglink" src="assets/img/img_03.png" />
                    </a>
                    <a href="https://t.me/moablockchain">
                    <img class="imglink" src="assets/img/img_02.png" />
                    </a>
                    <a href="https://open.kakao.com/o/gHnObsrb">
                    <img class="imglink" src="assets/img/img_01.png" />
                    </a>
                </div>
            </div>
            <div class="col-sm-9 col-sm-pull-3" style= "padding: 0; border: 0; margin: 0;
                                    display: flex;
                                    flex-wrap: nowrap;
                                    -webkit-flex-wrap: nowrap; /* Safari 6.1+ */">
                    <div style= "float:left; padding: 0; border: 0; width:  ;">
                        <a>
                        <img src="assets/img/그룹 118.svg" />
                        </a>
                    </div>
            </div>
        </div>
        <div class="row" style= "padding: 0; border: 0; margin: 0;">
            <hr style = "border-width: 2px;" />
        </div>
        <div class="row" style= "padding: 0; border: 0; margin: 0;">
            <div class="copyright">Copyright ⓒ MOA GLOBAL LIMITED</div>
        </div>
    </div>
</div>
<style>
.copyright {
    line-height:40px;
    font-size:16px;
    color: #C6C6C6;
    text-align:right;
}
.imglink {
    height: 40px;
    width: 40px;
    float:right;
    margin-left: 30px;
}
.col-sm-pull-3 > img{
    height: 46.71px;
}
@media(max-width:768px) {
    .copyright {
        text-align:center;
        font-size: 13px;
    }
    .col-sm-push-9 {
        height: 72px;
    }
    .col-sm-push-9 > div {
        width: 100%;
    }
    .col-sm-push-9 > div a{
            margin: auto;
        }
    .col-sm-pull-3 > div{
        margin: auto;
    }
    .col-sm-pull-3 img{
        height: 34px;
    }
    .footer {
        padding: 25px 40px;
    }
    .footer hr {
        margin-bottom: 0;
    }
    .imglink {
        height: 30px;
        width: 30px;
    }
    .navbar-default {
        position: fixed;
        z-index: 1;
        width: 100%;
    }
}
</style>
</body>
</html>