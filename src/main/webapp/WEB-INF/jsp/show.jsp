<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>WeTech Alliance- Event Live Captioning, powered by BeAware</title>
    <meta name="description" content="WeTech Event Live Captioning, powered by BeAware">
    <meta charset="utf-8">
    <!--[if IE]>
    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="canonical" href="https://www.conferencecaptioning.com/wetech">
    <meta property="og:site_name" content="WeTech Event Live-Captioning by BeAware">
    <!-- Twitter Meta Tags -->
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:title" content="WeTech Event Live-Captioning By BeAware">
    <meta name="twitter:description"
          content="Native Conference Live-Captioning for serving Deaf & Hard of Hearing attendees">
    <meta name="twitter:image" content="https://conferencecaptioning.com/images/author.png">
    <!-- Google / Search Engine Tags -->
    <meta itemprop="name" content="WeTech Event Live-Captioning By BeAware">
    <meta itemprop="description" content="WeTech Event Live Captioning for serving Deaf & Hard of Hearing attendees">
    <meta itemprop="image" content="https://conferencecaptioning.com/images/author.png">
    <!-- Facebook Open Graph -->
    <meta property="og:url" content="https://conferencecaptioning.com">
    <meta property="og:type" content="website">
    <meta property="og:title" content="WeTech Event Live-Captioning by BeAware">
    <meta property="og:description" content="WeTech Event Live-Captioning for serving Deaf & Hard of Hearing attendees">
    <meta property="og:image" content="https://conferencecaptioning.com/images/author.png">
    <meta name="robots" content="noodp,noydir">
    <!-- Facebook Open Graph end -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body style="width: 100%; margin: 0">
<!-- partial:index.partial.html -->
<div id="header" class="customPadding" style="justify-content: center;">
    <!-- <div style="display:flex"> -->
    <img style="height:24px; width:24px; justify-content: center;" src="/images/x.png" alt="x.png"/>
    <img style="height: 43px; width:122px; justify-content: center;" src="/images/logo-wetech.png" alt="logo">
    <h1 id="caption-header" class="caption-header" style="font-size: 24px; justify-content: center;">Event Live
        Captioning</h1>
    <!-- </div> -->
</div>
<div class="holder">
    <div style="display: flex; justify-content: center; padding: 0 0 10px 0">
        <button id="get-live-caption" class="center ios-button ios-button-text">Get Live Captions</button>
    </div>
    <div style="height: 400px; border: solid #1d3b78;">
        <div id="live-caption-empty" class="scroller scroller-empty">Transcription will display here</div>
        <div id="live-caption" class="scroller"></div>
        <div style="display: flex; justify-content: center;">
            <i id="#arrow" class="fa-solid fa-arrow-down fa-fade center"></i>
        </div>
    </div>
    <div style="display: flex; justify-content: center; padding:20px">
        <i class="fa fa-language" aria-hidden="true" style="padding: 0 10px 0 10px; color:#1d3b78"></i>
        <a href="javascript:void(0)">
            <div id="eng" style="font-size: 18px; padding: 0 10px 0 10px;" class="enable">English</div>
        </a>
        <a href="javascript:void(0)">
            <div id="french" style="font-size: 18px; padding: 0 10px 0 10px;" class="disabled">Français</div>
        </a>
    </div>
    <div>
  <span id="hotmail"
        style="justify-content: center; padding: 0; font-size: 18px; color:#000000; text-decoration: none;">
    PS: I love you. Get your free live-event transcription at
  </span>
        <a href="https://conferencecaptioning.com/?src=wetech"
           style="justify-content: center; padding: 0; font-size: 18px; text-decoration:underline;color:#0000ff;">
            ConferenceCaptioning
        </a>
    </div>
</div>

<script type="text/javascript" src='/js/jquery.min.js'></script>
<script type="text/javascript" src='/js/bootstrap.min.js'></script>
<script type="text/javascript" src="/js/script.js"></script>
<input type="hidden" id="basePath"
       value="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()%>"/>
</body>
</html>