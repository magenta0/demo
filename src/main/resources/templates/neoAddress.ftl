<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>address</title>
    <script src="jquery-3.4.1.min.js"></script>
    <style>
    </style>
    <script>
        var isFirstPage = 0;
        var isLastPage = 0;

        $(document).ready(function () {
            loadToId('json/address/balance?address=${address}', 'balance')
            loadToTotal('json/address/count?address=${address}', 'totalTx')
            loadTableData('json/address/page?address=${address}&page=${page}', 'table')
        });

        function loadToId(itemUrl, id) {
            $.get(itemUrl, function (data, status) {
                $('#' + id).append(data[id])
            })
        }

        function loadToTotal(itemUrl, id){
            $.get(itemUrl, function (data, status) {
                $('#totalTx').append(data[id])

                var totalpage = parseInt(data[id] / 25 + 1)
                $('#totalPage').append(totalpage)

                //pagging

                $('#one').append('<a href="address?address=${address}&page=' + (${page} - 1) + '">' + (${page} - 1) + '</a>')
                $('#three').append('<a href="address?address=${address}&page=' + (${page} + 1) + '">' + (${page} + 1) + '</a>')

                if (totalpage == '${page}')
                    $('#three').remove()
                if ('1' == '${page}')
                    $('#one').remove()
            })
        }


        function loadTableData(itemUrl, id) {
            $.get(itemUrl, function (data, status) {

                // hash, age, from, from/to, to, value

                if (jQuery.isEmptyObject(data)) return;

                var lastD = 0;
                for (d in data) {

                    var str = ''
                    var date = new Date();
                    date -= data[d]['timestamp'];

                    if (data[d]['fromAddress'] == '${address}') {
                    str += '<tr>' +
                    '<td>' + data[d]['hash'] + '</td>' +
                    '<td>' + getTime(date) + '</td>' +
                    '<td>' + data[d]['fromAddress'] + '</td>' +
                    '<td>' + 'from' + '</td>' +
                    '<td><a href="#">' + data[d]['toAddress']+ '</a></td>' +
                    '<td>' + data[d]['amount'] + '</td>' +
                    '</tr>'
                    }
                    else if (data[d]['toAddress'] == '${address}') {
                    str += '<tr>' +
                    '<td>' + data[d]['hash'] + '</td>' +
                    '<td>' + getTime(date) + '</td>' +
                    '<td><a href=""#>' + data[d]['fromAddress'] + '</a></td>' +
                    '<td>' + 'to' + '</td>' +
                    '<td>' + data[d]['toAddress']+ '</td>' +
                    '<td>' + data[d]['amount'] + '</td>' +
                    '</tr>'
                    }

                    $('#table').append(str)
                    lastD = d;
                }
                $('#count').append(parseInt(lastD) + 1);

            })
        }

        function getTime(date) {
            var mins = parseInt (date / ( 1000 * 60 ) )
            var hours = parseInt (mins / 60)
            mins %= 60
            if ( hours > 0 )
                return hours + ' hrs ' + mins + ' mins ago';
            else
                return mins + ' mins ago';
        }



                            function getToFrom(to, from) {
                if (to == '{address}')
                    return 1;
                else if (from == '{address}')
                    return 2;
                else
                    return 0;
            }

            function from(tofrom) {
                if (tofrom == 1)
                    return '<a href="#">' + data[d]['fromAddress'] + '</a>'
                else if (tofrom == 2)
                    return data[d]['fromAddress']
                else
                    return '';
            }

            function tofrom(tofrom) {
                if (tofrom == 1)
                    return 'to';
                else if (tofrom == 2)
                    return 'from';
                else
                    return '';
            }

            function to(tofrom){
                if (tofrom == 1)
                    return data[d]['toAddress']
                else if (tofrom == 2)
                    return '<a href="#">' + data[d]['toAddress'] + '</a>'
                else
                    return '';
            }

    </script>
    <script>
    </script>
</head>
<body>
    <div>
        <h2>Address</h2>
        <p>${address}</p>
    </div>

    <table>
        <thead></thead>
        <tbody>
        <tr>
            <td>
                balance :
                <br><p id="balance"></p>
            </td>
        </tr>
        </tbody>
    </table>
    <p><span id="count"></span> out of <a id="totalTx" href="#"></a> transactions</p>
    <p>page ${page} of <span id="totalPage"></span></p>
    <table>
        <thead></thead>
        <tbody id="table">
        <tr>
            <th>hash</th>
            <th>age</th>
            <th>from</th>
            <th> </th>
            <th>to</th>
            <th>value</th>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
    <ul>
        <li id="one"></li>
        <li id="two">${page}</li>
        <li id="three"></li>
    </ul>
</body>

</html>