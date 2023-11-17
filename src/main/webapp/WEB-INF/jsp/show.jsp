<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>test</title>
</head>
<body>
<script type="text/javascript" src='/js/jquery.min.js'></script>
<script type="text/javascript" src='/js/bootstrap.min.js'></script>
<script type="text/javascript">
    function getTranscript() {
        var path = window.location.pathname;
        console.log(path);
        // var url="https://script.google.com/macros/s/AKfycbzqOWlC9bT6TtLp1QJLzAkwDZJKTcCZYnoDhN4JIMXTo5lEvtPruYb-3vrILj__yO_A/exec?streamName=WeTech";
        // $.ajax({
        //     url: url,
        //     type: "GET",
        //     success: function (result) {
        //         console.log(result)
        //     }
        // });
    }
    // function getTranscript() {
    //     var url="http://localhost:8080" + "/i18n/getTranscript";
    //     // var url="https://script.google.com/macros/s/AKfycbzqOWlC9bT6TtLp1QJLzAkwDZJKTcCZYnoDhN4JIMXTo5lEvtPruYb-3vrILj__yO_A/exec?streamName=WeTech";
    //     $.getJSON(
    //         url,
    //         function (a) {
    //             var json = JSON.stringify(a);
    //             console.log(json)
    //         }
    //     );
    // }
    // async function getTranscript() {
    //     $.ajax({
    //         // url: "https://script.google.com/macros/s/AKfycbzqOWlC9bT6TtLp1QJLzAkwDZJKTcCZYnoDhN4JIMXTo5lEvtPruYb-3vrILj__yO_A/exec?streamName=WeTech",
    //         url: "http://localhost:8080" + "/i18n/getTranscriptDemo",
    //         type: "GET",
    //         dataType: "json",
    //         headers: {'Content-Type': 'application/json'},
    //         success: function (result) {
    //             console.log(result)
    //         }
    //     });
    // }
</script>
<button onclick="getTranscript()">click</button>
</body>
</html>