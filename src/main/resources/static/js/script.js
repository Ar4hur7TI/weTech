let currentLanguage = "eng"; // "french" is the other choice
let localization = "";
let transcript = "";
let interval = null;
eng = document.getElementById("eng");
french = document.getElementById("french");
let isStreamingCaptions = false;
let BASE_URL = "http://localhost:8080";
let eventName = "WeTech";

function translate(language) {
    currentLanguage = language
    loadLang(language)
}

$(document).ready(function () {
    let str = $("#basePath").val();
    if (str) BASE_URL = str;
    let path = window.location.pathname;
    let parts = path.split('/');
    let indexPosition = parts.indexOf("index.jsp");
    if (indexPosition > 0 && indexPosition - 1 < parts.length) {
        let beforeIndex = parts[indexPosition - 1];
        if (beforeIndex) {
            eventName = beforeIndex;
        }
    }
    loadLang("eng")
    $("#french").click(function () {
        translate("french");
    });
    $("#eng").click(function () {
        translate("eng");
    });
    $("#get-live-caption").on("click", buttonTapped);
});

function buttonTapped() {
    isStreamingCaptions = !isStreamingCaptions;
    $("#get-live-caption").html(isStreamingCaptions ? localization['get-live-caption-stop'] : localization['get-live-caption']);
    if (isStreamingCaptions) {
        getTranscript();
        interval = setInterval(() => {
            if (transcript) {
                $('#live-caption-empty').hide();
                $("#live-caption").html(transcript);
            } else {
                $('#live-caption-empty').show();
            }
            if (isStreamingCaptions) {
                getTranscript();
            }
        }, 1000);
    } else {
        clearInterval(interval);
    }
}

function loadLang(lang) {
    $.ajax({
        url: BASE_URL + "/i18n/switch",
        type: "POST",
        dataType: "json",
        headers: {'Content-Type': 'application/json'},
        data: JSON.stringify({language: lang}),
        success: function (result) {
            if (result.code === 200) {
                $.getJSON(BASE_URL + "/i18n/loadLang", (text) => {
                    transcript = ''
                    $("#live-caption").html(transcript);
                    $('#live-caption-empty').show();
                    localization = text
                    document.getElementById("caption-header").innerText = text['caption-header'];
                    if (isStreamingCaptions) {
                        document.getElementById("get-live-caption").innerText = text['get-live-caption-stop'];
                    } else {
                        document.getElementById("get-live-caption").innerText = text['get-live-caption'];
                    }
                    document.getElementById("live-caption-empty").innerText = text['live-caption-empty'];
                    document.getElementById("hotmail").innerText = text['hotmail'];
                    document.getElementById("eng").innerText = text['english-language'];
                    document.getElementById("french").innerText = text['french-language'];
                });
            }
        }
    })
}

// function getTranscript() {
//     // let url = "https://script.google.com/macros/s/AKfycbzqOWlC9bT6TtLp1QJLzAkwDZJKTcCZYnoDhN4JIMXTo5lEvtPruYb-3vrILj__yO_A/exec?streamName=WeTech";
//     let url = "https://script.google.com/macros/s/AKfycbzqOWlC9bT6TtLp1QJLzAkwDZJKTcCZYnoDhN4JIMXTo5lEvtPruYb-3vrILj__yO_A/exec?streamName=" + eventName;
//     // let url = BASE_URL + "/i18n/getTranscript";
//     $.getJSON(
//         url,
//         function (a) {
//             // let json = JSON.stringify(a);
//             if (a && a.Transcript && a.Transcript != "") {
//                 if (currentLanguage === "french") {
//                     transcript = a.Transcript_FR;
//                 } else {
//                     transcript = a.Transcript;
//                 }
//                 isStreamingCaptions = a.IsActivelyStreaming
//             }
//         }
//     );
// }

// This function is for Demo purpose only
function getTranscript() {
    $.ajax({
        url: BASE_URL + "/i18n/getTranscriptDemo",
        type: "GET",
        dataType: "json",
        headers: {'Content-Type': 'application/json'},
        success: function (result) {
            console.log(result)
            transcript = result.msg;
        }
    });
}