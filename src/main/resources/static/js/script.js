let currentLanguage = "eng"; // "french" is the other choice
let localization = "";
let transcript = "";
let interval = null;
eng = document.getElementById("eng");
french = document.getElementById("french");
let isStreamingCaptions = false;
let BASE_URL = "http://localhost:8080";

function translate(language) {
    currentLanguage = language
    loadLang(language)
}

$(document).ready(function () {
    let str = $("#basePath").val();
    if (str) BASE_URL = str;
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

function getTranscript() {
    console.log("getTranscript");
    $.ajax({
        url: BASE_URL + "/i18n/getTranscript",
        type: "POST",
        dataType: "json",
        headers: {'Content-Type': 'application/json'},
        success: function (result) {
            console.log(result)
            transcript = result.msg;
        }
    });
}